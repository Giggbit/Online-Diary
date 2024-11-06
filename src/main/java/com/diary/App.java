package com.diary;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.server.ResourceConfig;

public class App {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");

        ResourceConfig config = new ResourceConfig();
        config.packages("com.diary.controllers");

        ServletHolder servlet = new ServletHolder(new ServletContainer(config));
        context.addServlet(servlet, "/api/*");

        server.setHandler(context);
        server.start();
        server.join();
    }
}
