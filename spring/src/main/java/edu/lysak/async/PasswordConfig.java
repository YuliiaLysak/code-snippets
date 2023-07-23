package edu.lysak.async;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
public class PasswordConfig implements AsyncConfigurer {
    private static final String ALPHA = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUMERIC = "0123456789";
    private static final String SPECIAL_CHARS = "!@#$%^&*_=+-/";

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();

        taskExecutor.setCorePoolSize(4);
        taskExecutor.setMaxPoolSize(4);
        taskExecutor.setQueueCapacity(50);
        taskExecutor.setThreadNamePrefix("RunnerThread::");
        taskExecutor.initialize();

        return taskExecutor;
    }

//    @Bean(name = "threadPoolTaskExecutor")
//    public Executor threadPoolTaskExecutor() {
//        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//
//        executor.setCorePoolSize(4);
//        executor.setMaxPoolSize(4);
//        executor.setQueueCapacity(15);
//        executor.setThreadNamePrefix("RunnerThread::");
//        executor.initialize();
//
//        return executor;
//    }

    @Bean
    public PasswordAlphabet allCharacters() {
        return new PasswordAlphabet(ALPHA + NUMERIC + SPECIAL_CHARS);
    }

    static class PasswordAlphabet {
        private final String characters;

        public PasswordAlphabet(String characters) {
            this.characters = characters;
        }

        public String getCharacters() {
            return characters;
        }
    }
}
