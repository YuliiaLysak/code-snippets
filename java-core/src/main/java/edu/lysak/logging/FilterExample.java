package edu.lysak.logging;

import java.util.logging.Filter;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public class FilterExample implements Filter {
    public boolean isLoggable(LogRecord record) {
        return record.getLevel() == Level.INFO;
    }
}
