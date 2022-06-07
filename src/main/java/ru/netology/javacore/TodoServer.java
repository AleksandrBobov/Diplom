package ru.netology.javacore;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TodoServer {
  private int port;
  private Todos todos;

  public TodoServer(int port, Todos todos) {
    this.port = port;
    this.todos = todos;
  }

  public void start() throws IOException {
    System.out.println("Starting server at " + port + "...");
    ServerSocket serverSocket = new ServerSocket(port);
    JSONParser parser = new JSONParser();

    while (true) {
      try (Socket clientSocket = serverSocket.accept();
           PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
           BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
        Object obj = parser.parse(in.readLine());
        JSONObject jsonObject = (JSONObject) obj;
        String typeOperation = (String) jsonObject.get("type");
        String task = (String) jsonObject.get("task");

        if (typeOperation.equals("ADD")) {
          todos.addTask(task);
          out.println("Задача: " + task + " добавлена. Актуальные задачи: " + todos.getAllTasks());
        }

        if (typeOperation.equals("REMOVE")) {
          todos.removeTask(task);
          out.println("Задача " + task + " удалена. Актуальные задачи: " + todos.getAllTasks());
        }
      } catch (Exception exception) {
        System.out.println(exception.getMessage());
      }
    }
  }
}
