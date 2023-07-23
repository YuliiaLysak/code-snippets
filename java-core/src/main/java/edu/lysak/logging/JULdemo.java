package edu.lysak.logging;

import java.io.IOException;
import java.util.logging.*;

public class JULdemo {
    public static void main(String[] args) throws IOException {
        Logger logger = Logger.getLogger(JULdemo.class.getName());

        Handler fileHandler = new FileHandler("default.log");
        logger.addHandler(fileHandler);
        fileHandler.setFormatter(new SimpleFormatter());
//        Handler consoleHandler = new ConsoleHandler();
//        logger.addHandler(consoleHandler);
//        consoleHandler.setFormatter(new XMLFormatter());


//        Filter filter = new FilterExample();
//        logger.setFilter(filter);

        logger.severe("Severe Log");
        logger.getLevel();
        logger.info("Info Log");
        logger.warning("Warning Log");
        logger.log(Level.SEVERE, "Severe");
    }
}
