package edu.lysak.patterns.facade;

import java.util.ArrayList;
import java.util.List;

class LogFilter {
    /**
     * The method filters input logs, that matches an expression value
     *
     * @param lines is a list of input logs
     * @param expr  is an expression value
     * @return logs, that contain an expression value
     */
    public List<String> filter(List<String> lines, String expr) {
        List<String> matches = new ArrayList<>();
        for (String line : lines) {
            if (line.contains(expr)) {
                matches.add(line);
            }
        }

        return matches;
    }
}
