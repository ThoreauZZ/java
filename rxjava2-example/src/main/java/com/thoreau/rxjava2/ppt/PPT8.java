package com.thoreau.rxjava2.ppt;

import com.sun.tools.javac.util.Pair;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

/**
 * 2018/6/3 下午4:23.
 *
 * @author zhaozhou
 */
@Slf4j
public class PPT8 {


    @Test
    public void test() {
        long l1 = System.currentTimeMillis();
        Observable<Flight> flight = rxLookupFlight("LOT 783");
        Observable<Passenger> passenger = rxFindPassenger(42);
        Observable<Ticket> ticket = flight
                .zipWith(passenger, (f, p) -> bookTicket(f, p));
        ticket.subscribe(System.out::println);
        long l2 = System.currentTimeMillis();
        assertTrue((l2 - l1) > 3000);


    }

    @Test
    public void test2() {
        long l1 = System.currentTimeMillis();
        Observable<Flight> flight = rxLookupFlight("LOT 783")
                .subscribeOn(Schedulers.io())
                .timeout(3000, TimeUnit.MILLISECONDS);

        Observable<Passenger> passenger = rxFindPassenger(42);
        Observable<Ticket> ticket = flight
                .zipWith(passenger, (f, p) -> bookTicket(f, p));
        ticket.subscribe(System.out::println);
        long l2 = System.currentTimeMillis();
        assertTrue((l2 - l1) < 3000);

    }

    @Test
    public void test3() {
        // rxBookTicket也是异步的情况
        long l1 = System.currentTimeMillis();
        Observable<Flight> flight = rxLookupFlight("LOT 783")
                .subscribeOn(Schedulers.io())
                .timeout(3000, TimeUnit.MILLISECONDS);

        Observable<Passenger> passenger = rxFindPassenger(42);
        flight.zipWith(passenger, (Flight f, Passenger p) -> Pair.of(f, p))
              .flatMap(pair -> rxBookTicket(pair.fst, pair.snd))
              .subscribe();
        long l2 = System.currentTimeMillis();
        assertTrue((l2 - l1) < 3000);


    }
    Observable<Flight> rxLookupFlight(String flightNo) {
        return Observable.defer(() ->
                Observable.just(lookupFlight(flightNo)));
    }

    Observable<Passenger> rxFindPassenger(long id) {
        return Observable.defer(() ->
                Observable.just(findPassenger(id)));
    }

    Flight lookupFlight(String flightNo) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Flight(1);
    }

    Passenger findPassenger(long id) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Passenger("xx");
    }

    Ticket bookTicket(Flight flight, Passenger passenger) {
        Ticket ticket = new Ticket();
        ticket.setFlightId(flight.getId());
        ticket.setPassengerName(passenger.getName());
        return ticket;
    }

    Observable<Ticket> rxBookTicket(Flight flight, Passenger passenger) {
        Ticket ticket = new Ticket();
        ticket.setFlightId(flight.getId());
        ticket.setPassengerName(passenger.getName());
        return Observable.just(ticket);
    }

    @Data
    class Flight {
        Flight(int id) {
            this.id = id;
        }

        private int id;

    }

    @Data
    class Passenger {
        public Passenger(String name) {
            this.name = name;
        }

        private String name;
    }

    @Data
    class Ticket {
        private int flightId;
        private String passengerName;
    }
}
