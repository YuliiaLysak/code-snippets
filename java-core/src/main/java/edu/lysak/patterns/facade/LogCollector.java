package edu.lysak.patterns.facade;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class LogCollector {
    /**
     * The method reads lines from a standard input
     *
     * @return a list of logs
     */
    public List<String> collect() {
        List<String> logs = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        for (int i = 0; i <= num; ++i) {
            String log = scanner.nextLine();
            logs.add(log);
        }

        return logs;
    }
}
