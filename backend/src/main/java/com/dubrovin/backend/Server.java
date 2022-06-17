package com.dubrovin.backend;

import com.sun.net.httpserver.HttpServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class Server {
    private InetSocketAddress inetSocketAddress;
    private HttpServer httpServer;

    public Server() throws IOException {
        inetSocketAddress = getInetSocketAddress();
        httpServer = HttpServer.create(inetSocketAddress, 0);

        httpServer.createContext("/", httpExchange -> {
            byte[] response = new Date().toString().getBytes(StandardCharsets.UTF_8);
            System.out.println(httpExchange.getRequestHeaders().entrySet());
            httpExchange.getResponseHeaders().add("Allow","GET");
            httpExchange.sendResponseHeaders(200,response.length);
            OutputStream outputStream = httpExchange.getResponseBody();
            outputStream.write(response);
            outputStream.close();
        });

        httpServer.start();
    }

    private InetSocketAddress getInetSocketAddress() throws IOException {
        InputStream properties = BackendApplication.class.getResourceAsStream("/application.properties");
        if(properties == null) throw new RuntimeException("properties not found");

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(properties));
        int s;
        StringBuilder str = new StringBuilder();
        while ((s = bufferedReader.read()) != -1) str.append((char) s);
        String[] address = str.toString().split("[\n\r ]+");
        return new InetSocketAddress(InetAddress.getByName(address[0]),Integer.parseInt(address[1]));
    }
}