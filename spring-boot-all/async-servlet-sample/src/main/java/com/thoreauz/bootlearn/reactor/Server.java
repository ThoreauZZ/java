package com.thoreauz.bootlearn.reactor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 2018/5/8 下午2:35.
 *
 * @author zhaozhou
 * @date 2018/05/08
 */
public class Server implements Runnable{
    public static void main(String[] args) throws InterruptedException {
        new Thread(new Server()).start();
        Thread.sleep(10000);
    }
    @Override
    public void run() {
        ServerSocket ss = null;
        try {
            ss = new ServerSocket(30000);
            while (!Thread.interrupted()){
                //创建新线程来handle
                new Thread(new Handler(ss.accept())).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private class Handler implements Runnable {
        final Socket socket;
        Handler(Socket s) { socket = s; }
        @Override
        public void run() {
            try {
                byte[] input = new byte[1024];
                socket.getInputStream().read(input);
                byte[] output = process(input);
                socket.getOutputStream().write(output);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        private byte[] process(byte[] input) {
            System.out.println(new String(input));
            return "hh".getBytes();
        }

    }
}
