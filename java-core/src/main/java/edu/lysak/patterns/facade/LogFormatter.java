package edu.lysak.patterns.facade;

import java.util.ArrayList;
import java.util.List;

class LogFormatter {
    /**
     * The method applies a transformation logic to each element of an input list.
     * If an element has ': '
     * substring the transformation logic splits the element into 2 parts by this delimiter and takes the second part
     * Otherwise the method takes an element as is
     *
     * @param logs is a list of input elements
     * @return a transformed list
     */
    public List<String> format(List<String> logs) {
        List<String> formattedLogs = new ArrayList<>();
        for (String log : logs) {
            String[] levelAndMessage = log.split(": ");
            String formattedLog = levelAndMessage.length > 1 ? levelAndMessage[1] : log;
            formattedLogs.add(formattedLog);
        }

        return formattedLogs;
    }
}
