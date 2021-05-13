package ru.job4j.io.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(65371, 90000, InetAddress.getByName("0.0.1"))) {
            while (!server.isClosed()) {
                System.out.println("Start server. Listining");
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String str = in.readLine();
                    String msg;
                    while (!str.isEmpty()) {
                        if (str.contains("GET /?msg=")) {
                            msg = str.split(" ")[1].split("=")[1];
                            if ("Exit".equals(msg)) {
                                msg = "Bye";
                                out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                                out.write(msg.getBytes());
                                out.flush();
                                server.close();
                                break;
                            } else if ("Hello".equals(msg)) {
                                msg = "Hello, friend";
                            }
                            out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                            out.write(msg.getBytes());
                            out.flush();
                        }
                        System.out.println(str);
                        str = in.readLine();
                    }
                }
            }
        } catch (IOException e) {
            LOG.error("IO exception", e);
        }
    }
}
