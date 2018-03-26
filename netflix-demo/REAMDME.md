##  queue
```java
 public Future<R> queue() {
        /*
         * The Future returned by Observable.toBlocking().toFuture() does not implement the
         * interruption of the execution thread when the "mayInterrupt" flag of Future.cancel(boolean) is set to true;
         * thus, to comply with the contract of Future, we must wrap around it.
         */
        // 委托
        final Future<R> delegate = toObservable().toBlocking().toFuture();
    	
        final Future<R> f = new Future<R>() {

            @Override
            public boolean cancel(boolean mayInterruptIfRunning) {
                if (delegate.isCancelled()) {
                    return false;
                }

                if (HystrixCommand.this.getProperties().executionIsolationThreadInterruptOnFutureCancel().get()) {
                    /*
                     * The only valid transition here is false -> true. If there are two futures, say f1 and f2, created by this command
                     * (which is super-weird, but has never been prohibited), and calls to f1.cancel(true) and to f2.cancel(false) are
                     * issued by different threads, it's unclear about what value would be used by the time mayInterruptOnCancel is checked.
                     * The most consistent way to deal with this scenario is to say that if *any* cancellation is invoked with interruption,
                     * than that interruption request cannot be taken back.
                     */
                    interruptOnFutureCancel.compareAndSet(false, mayInterruptIfRunning);
        		}

                final boolean res = delegate.cancel(interruptOnFutureCancel.get());

                if (!isExecutionComplete() && interruptOnFutureCancel.get()) {
                    final Thread t = executionThread.get();
                    if (t != null && !t.equals(Thread.currentThread())) {
                        t.interrupt();
                    }
                }

                return res;
			}

            @Override
            public boolean isCancelled() {
                return delegate.isCancelled();
			}

            @Override
            public boolean isDone() {
                return delegate.isDone();
			}

            @Override
            public R get() throws InterruptedException, ExecutionException {
                return delegate.get();
            }

            @Override
            public R get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
                return delegate.get(timeout, unit);
            }
        	
        };

        /* special handling of error states that throw immediately */
        if (f.isDone()) {
            try {
                f.get();
                return f;
            } catch (Exception e) {
                Throwable t = decomposeException(e);
                if (t instanceof HystrixBadRequestException) {
                    return f;
                } else if (t instanceof HystrixRuntimeException) {
                    HystrixRuntimeException hre = (HystrixRuntimeException) t;
                    switch (hre.getFailureType()) {
					case COMMAND_EXCEPTION:
					case TIMEOUT:
						// we don't throw these types from queue() only from queue().get() as they are execution errors
						return f;
					default:
						// these are errors we throw from queue() as they as rejection type errors
						throw hre;
					}
                } else {
                    throw Exceptions.sneakyThrow(t);
                }
            }
        }

        return f;
    }
```

```java
//doOnCompleted handler already did all of the SUCCESS work
//doOnError handler already did all of the FAILURE/TIMEOUT/REJECTION/BAD_REQUEST work
final Action0 terminateCommandCleanup = new Action0() {

    @Override
    public void call() {
        if (_cmd.commandState.compareAndSet(CommandState.OBSERVABLE_CHAIN_CREATED, CommandState.TERMINAL)) {
            handleCommandEnd(false); //user code never ran
        } else if (_cmd.commandState.compareAndSet(CommandState.USER_CODE_EXECUTED, CommandState.TERMINAL)) {
            handleCommandEnd(true);  //user code did run
        }
    }
};
```
```java
protected enum ≈ {
    NOT_STARTED,
    OBSERVABLE_CHAIN_CREATED,
    USER_CODE_EXECUTED, 
    UNSUBSCRIBED, 
    TERMINAL
}
```