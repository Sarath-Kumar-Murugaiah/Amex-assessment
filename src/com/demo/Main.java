package com.demo;

public class Main {
    public static void main(String[] args) {
        String commandFilePath = "commands.txt";
        String outputFilePath = "sample-output.txt";

        CommandScheduler scheduler = new CommandScheduler(commandFilePath, outputFilePath);
        scheduler.start();

        System.out.println("Command Scheduler started and running...");

        // Add shutdown hook to catch termination (Ctrl+C or JVM exit)
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Command Scheduler stopped. Exiting application.");
        }));
    }
}