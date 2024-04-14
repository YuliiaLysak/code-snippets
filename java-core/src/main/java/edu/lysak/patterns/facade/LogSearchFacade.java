package edu.lysak.patterns.facade;

import java.util.List;

class LogSearchFacade {
    private final LogCollector collector;
    private final LogFilter filter;
    private final LogFormatter formatter;

    public LogSearchFacade(LogCollector collector, LogFilter filter, LogFormatter formatter) {
        this.collector = collector;
        this.filter = filter;
        this.formatter = formatter;
    }

    public List<String> search(String expr) {
        // construct the code of the facade method
        List<String> logs = collector.collect();
        List<String> matches = filter.filter(logs, expr);
        return formatter.format(matches);
    }
}
