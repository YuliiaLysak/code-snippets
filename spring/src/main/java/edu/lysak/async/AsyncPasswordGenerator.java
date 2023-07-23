package edu.lysak.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
@EnableAsync
public class AsyncPasswordGenerator {
    private final PasswordGenerator passwordGenerator;

    public AsyncPasswordGenerator(PasswordGenerator passwordGenerator) {
        this.passwordGenerator = passwordGenerator;
    }

    @Async
    public void generateLong() throws InterruptedException {
        System.out.println("A long password: " + passwordGenerator.generate(10));
        System.out.println(Thread.currentThread());
    }

    @Async
    public void generateShort() throws InterruptedException {
        System.out.println("A short password: " + passwordGenerator.generate(5));
        System.out.println(Thread.currentThread());
    }

    @Async
    public CompletableFuture<String> getLongPassword() throws InterruptedException {
        System.out.println(Thread.currentThread());
        return CompletableFuture.completedFuture(("A long password: " + passwordGenerator.generate(10)));
    }

    @Async
    public CompletableFuture<String> getShortPassword() throws InterruptedException {
        System.out.println(Thread.currentThread());
        return CompletableFuture.completedFuture("A short password: " + passwordGenerator.generate(5));
    }
}
