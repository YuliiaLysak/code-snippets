package edu.lysak.pipeline.simple;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.Create;

import java.util.Arrays;
import java.util.List;

public class SimplePipeline {
    public static final String PATH = "./apache-beam/src/main/resources/simple/demo1";

    public static void main(String[] args) {
        PipelineOptions options = PipelineOptionsFactory.create();

        Pipeline pipeline = Pipeline.create(options);

        List<String> input = Arrays.asList("First", "second", "third");
        pipeline.apply(Create.of(input))
                .apply(TextIO.write().to(PATH)
                        .withSuffix(".txt"));
        pipeline.run();
    }

}
