package com.demo;

import java.io.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.*;

public class CommandScheduler {
	private final String inputFilePath;
	private final String outputFilePath;
	private final List<ScheduledCommand> commands = new ArrayList<>();

	public CommandScheduler(String inputFilePath, String outputFilePath) {
		this.inputFilePath = inputFilePath;
		this.outputFilePath = outputFilePath;
	}

	public void start() {
		loadCommands();
		ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
		scheduler.scheduleAtFixedRate(this::checkAndExecuteCommands, 0, 1, TimeUnit.MINUTES);
	}

	private void loadCommands() {
		try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
			String line;
			while ((line = br.readLine()) != null) {
				ScheduledCommand cmd = ScheduledCommand.parse(line.trim());
				if (cmd != null) {
					commands.add(cmd);
				}
			}
		} catch (IOException e) {
			System.err.println("Error reading command file: " + e.getMessage());
		}
	}

	private void checkAndExecuteCommands() {
		LocalDateTime now = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);

		for (ScheduledCommand cmd : commands) {
			try {
				if (cmd.type == ScheduledCommand.Type.ONCE && !cmd.executed) {
					if (cmd.scheduleTime.equals(now)) {
						executeCommand(cmd.command);
						cmd.executed = true;
					}
				} else if (cmd.type == ScheduledCommand.Type.RECURRING) {
					long minutes = now.getHour() * 60 + now.getMinute();
					if (minutes % cmd.intervalMinutes == 0) {
						executeCommand(cmd.command);
					}
				}
			} catch (Exception e) {
				System.err.println("Error executing command: " + e.getMessage());
			}
		}
	}

	private void executeCommand(String command) {
		
			 try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath, true))) {
		            writer.write("Timestamp: " + LocalDateTime.now() + "\n");
		            writer.write("Command Execution:\n");
		            writer.write(command + "\n");
		            writer.write("----\n");
		       
		} catch (IOException e) {
			System.err.println("Failed to execute command: " + command);
			e.printStackTrace();
		}
	}
}