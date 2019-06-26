package completableFuture;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureCase {

    public static void main(String[] args) {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(CompletableFutureCase::process1);
    }

    public static void mainThread (String[] args) {

        Thread t1 = new Thread(() -> {
            System.out.println(CompletableFutureCase.process1());
        });
        Thread t2 = new Thread(() -> {
            System.out.println(CompletableFutureCase.process2());
        });
        Thread t3 = new Thread(() -> {
            System.out.println(CompletableFutureCase.process3());
        });

        t3.start();
        t2.start();
        t1.start();
    }

    public static String process1 () {

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "process1";
    }

    public static String process2 () {

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "process2";
    }

    public static String process3 () {

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "process3";
    }

}
