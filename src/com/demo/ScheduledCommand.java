package com.demo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScheduledCommand {
    enum Type { ONCE, RECURRING }

    Type type;
    LocalDateTime scheduleTime; // For ONCE
    int intervalMinutes;        // For RECURRING
    String command;
    boolean executed = false;   // For ONCE type

    public static ScheduledCommand parse(String line) {
        try {
            if (line.startsWith("*/")) {
                // Recurring
                String[] parts = line.split(" ", 2);
                int interval = Integer.parseInt(parts[0].substring(2));
                return new ScheduledCommand(Type.RECURRING, null, interval, parts[1]);
            } else {
                // One-time
                String[] parts = line.split(" ", 6);
                int min = Integer.parseInt(parts[0]);
                int hour = Integer.parseInt(parts[1]);
                int day = Integer.parseInt(parts[2]);
                int month = Integer.parseInt(parts[3]);
                int year = Integer.parseInt(parts[4]);
                LocalDateTime dateTime = LocalDateTime.of(year, month, day, hour, min);
                return new ScheduledCommand(Type.ONCE, dateTime, 0, parts[5]);
            }
        } catch (Exception e) {
            System.err.println("Invalid command format: " + line);
            return null;
        }
    }

    private ScheduledCommand(Type type, LocalDateTime scheduleTime, int intervalMinutes, String command) {
        this.type = type;
        this.scheduleTime = scheduleTime;
        this.intervalMinutes = intervalMinutes;
        this.command = command;
    }
}