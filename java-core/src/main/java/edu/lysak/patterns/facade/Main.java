package edu.lysak.patterns.facade;

import java.util.List;

class Main {
    public static void main(String[] args) {
        LogSearchFacade logSearcher = new LogSearchFacade(
            new LogCollector(),
            new LogFilter(),
            new LogFormatter()
        );
        List<String> foundLogs = logSearcher.search("ERROR");

        for (String log : foundLogs) {
            System.out.println(log);
        }
    }
}

