## JMH (Java Microbenchmark Harness): First steps 

[Oficial documentation](https://github.com/openjdk/jmh#basic-considerations)

### Tutorials
[JMH: First steps](https://hyperskill.org/learn/step/22307)  
[JMH: Deep dive](https://hyperskill.org/learn/step/23179)


### Step 1. Building the benchmarks 
After the project is generated, you can build it with the following
Maven command:

    $ mvn clean verify

### Step 2. Running the benchmarks
After the build is done, you will get the self-contained executable JAR,
which holds your benchmark, and all essential JMH infrastructure code:

    $ java -jar target/benchmarks.jar
    $ java -jar target/benchmarks.jar testMethodDefaultSettings
    $ java -jar target/benchmarks.jar testMethodCustomSettings

In this benchmark, there are 2 forks: each will perform 2 warmup iterations and 3 measurement iterations:

    $ java -jar target/benchmarks.jar testMethodCustomSettings -f 2 -i 3 -wi 2 

