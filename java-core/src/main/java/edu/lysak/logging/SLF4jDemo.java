package edu.lysak.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SLF4jDemo {
    private static final Logger logger = LoggerFactory.getLogger(SLF4jDemo.class);

    public static void main(String[] args) {
        logger.warn("Method 1 started with argument={}", 1);
        logger.debug("processTask id = ");
    }
}
