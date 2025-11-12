package org.example.cli;

import org.example.config.AppConstants;
import org.example.core.TaskList;
import org.example.storage.PropertiesManager;
import org.example.storage.TaskListStorageJson;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

public class TaskListProgram {
    private final TaskList taskList;
    private final Terminal terminal;
    private final LineReader lineReader;
    private final PrintWriter printWriter;
    private final TaskListStorageJson taskListStorageJson;
    private final Properties appProperties;
    public TaskListProgram() throws IOException {
        this.taskListStorageJson = new TaskListStorageJson();
        this.taskList = new TaskList(taskListStorageJson.loadTaskList());
        this.terminal = TerminalBuilder.builder().system(true).build();
        this.lineReader = LineReaderBuilder.builder().terminal(terminal).build();
        this.printWriter = terminal.writer();
        this.appProperties = readAppProperties();
    }


    private void startProgram() throws IOException {
        printWriter.println(AppConstants.WELCOME_LOGO);
        if(this.appProperties != null){
            printWriter.println("App Version: " + this.appProperties.getProperty(PropertiesManager.APP_VERSION));
        }
        while (true) {
            String line = lineReader.readLine("prompt> ");

            // Exit if requested
            if ("exit".equalsIgnoreCase(line)) {
                break;
            }

            // Echo the line back to the user
            terminal.writer().println("You entered: " + line);
            terminal.flush();
        }
        closeProgram();
    }
    private Properties readAppProperties() {
        Properties properties = null;
        try {
            properties = PropertiesManager.readPropertiesFile();
        } catch (IOException e) {
            try {
                PropertiesManager.createPropertiesFile();
                properties = PropertiesManager.readPropertiesFile();
            } catch (IOException ex) {
                System.err.println("CRITICAL: Failed to create and/or read properties file.");
            }
        }
        return properties;
    }

    private void closeProgram() throws IOException {
        terminal.writer().println("Goodbye!");
        terminal.close();
        this.taskListStorageJson.save(this.taskList);
    }

    public static void main(String[] args){
        try {
            TaskListProgram taskListProgram = new TaskListProgram();
            taskListProgram.startProgram();
        } catch (IOException e){
            System.out.println("Podczas działania programu wystąpił błąd :(");
            System.out.println(e);
        }
    }


}
