package ru.job4j.work;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import ru.job4j.serializable.stackoverflow.B;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;

public class Server {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 10);
        server.createContext("/", new MyHttpHandler());
        server.setExecutor(null);
        server.start();
    }

    private static class MyHttpHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
           URI uri = exchange.getRequestURI();
           if (uri.toString().equals("/")) {
               byte[] str = Files.readAllBytes(Path.of("./src/main/java/ru/job4j/work/index.html"));
               exchange.sendResponseHeaders(200, str.length);
               OutputStream os = exchange.getResponseBody();
               os.write(str);
               os.flush();
               os.close();
           } else if (uri.toString().equals("/welcome")) {
               Gson gson = new Gson();
               InputStream in = exchange.getRequestBody();
               int str;
               StringBuilder stringBuilder = new StringBuilder();
               while ((str = in.read()) != -1) {
                   stringBuilder.append((char) str);
               }
               // а так работает! смотри на названия переменных password passport!!!!
               Passport passport = gson.fromJson(stringBuilder.toString(), Passport.class);
 //!!! так не работает!!!!              Passport passport1 = gson.fromJson(new BufferedReader(new InputStreamReader(exchange.getRequestBody())), Passport.class);
           }
        }
    }
}
