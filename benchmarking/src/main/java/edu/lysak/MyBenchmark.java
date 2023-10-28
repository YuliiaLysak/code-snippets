/*
 * Copyright (c) 2014, Oracle America, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 *  * Neither the name of Oracle nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without
 *    specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */

package edu.lysak;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class MyBenchmark {

    // Run main() method or 'java -jar' from console (see README.md)
    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
            .include(MyBenchmark.class.getSimpleName())
//            .forks(5)
//            .measurementIterations(10)
//            .warmupIterations(5)
            .build();

        new Runner(opt).run();
    }

    @Benchmark
    public String testMethodDefaultSettings() {
        // This is a demo/sample template for building your JMH benchmarks. Edit as needed.
        // Put your benchmark code here.
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "A");

        return map.get(1);
    }

    @Benchmark
    @Fork(2)
    @Measurement(iterations = 3)
    @Warmup(iterations = 2)
    public String testMethodCustomSettings() {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "A");

        return map.get(1);
    }

    // Specifying the benchmarking mode

    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
    @Fork(1)
    @Warmup(iterations = 1)
    @Measurement(iterations = 2)
    public String testSingleShotTime() {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "A");

        return map.get(1);
    }

    @Benchmark
    @BenchmarkMode(Mode.SampleTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    @Fork(1)
    @Warmup(iterations = 5, time = 5)
    @Measurement(iterations = 2, time = 5)
    public String testSampleTime() {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "A");

        return map.get(1);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Fork(1)
    @Warmup(iterations = 1, time = 5)
    @Measurement(iterations = 2, time = 5)
    public String testAverageTime() {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "A");

        return map.get(1);
    }

    @Benchmark
    @BenchmarkMode(Mode.All)
    @Fork(1)
    @Warmup(iterations = 5)
    @Measurement(iterations = 2)
    public String testAll() {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "A");

        return map.get(1);
    }

    @Benchmark
    @Threads(4)
    public String testFourThreads() {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "A");

        return map.get(1);
    }

    @Benchmark
    @Threads(Threads.MAX) // Or @Threads(-1)
    public String testAllAvailableThreads() {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "A");

        return map.get(1);
    }

}
