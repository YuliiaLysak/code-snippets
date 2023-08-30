package edu.lysak.cosmosdb;

import edu.lysak.cosmosdb.model.User;
import edu.lysak.cosmosdb.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Slf4j
@SpringBootApplication
public class CosmosDbApplication implements CommandLineRunner {

    private final UserRepository repository;

    public CosmosDbApplication(UserRepository repository) {
        this.repository = repository;
    }

    public static void main(String[] args) {
        SpringApplication.run(CosmosDbApplication.class, args);
    }

    public void run(String... var1) {
        repository.deleteAll().block();
        log.info("Deleted all data in container.");

        User testUser = new User(
            "testId",
            "testFirstName",
            "testLastName",
            "test address line one"
        );

        // Save the User class to Azure Cosmos DB database.
        Mono<User> saveUserMono = repository.save(testUser);

        Flux<User> firstNameUserFlux = repository.findByFirstName("testFirstName");

        //  Nothing happens until we subscribe to these Monos.
        //  findById won't return the user as user isn't present.
        Mono<User> findByIdMono = repository.findById(testUser.getId());
        User findByIdUser = findByIdMono.block();
        log.info("Fetched user - [{}]", findByIdUser);

        User savedUser = saveUserMono.block();
        log.info("Saved user - [{}]", savedUser);

        firstNameUserFlux.collectList().block();

        Optional<User> optionalUserResult = repository.findById(testUser.getId()).blockOptional();

        User result = optionalUserResult.get();
        log.info("Fetched user after mono/flux subscribing - [{}]", result);

        log.info("findOne in User collection get result: {}", result);
    }
}
