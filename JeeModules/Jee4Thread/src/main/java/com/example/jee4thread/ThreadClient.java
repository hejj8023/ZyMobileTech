package com.example.jee4thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadClient {
    private static int num;

    public static void main(String[] args) {
        ExecutorService threadExecutor = Executors.newSingleThreadExecutor();
        threadExecutor.submit(new Runnable() {
            @Override
            public void run() {
                Thread currentThread = Thread.currentThread();
                System.out.println("name =" + currentThread.getName() + " , id = " + currentThread);
                while (true) {
                    System.out.println("num = " + num);
                    num++;
                }
            }
        });
    }
}
