package edu.lysak.pipeline.dataflow;

import org.apache.beam.runners.dataflow.DataflowRunner;
import org.apache.beam.runners.dataflow.options.DataflowPipelineOptions;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.Create;

import java.util.Arrays;
import java.util.List;

public class DataflowPipeline {
    public static final String PATH = "gs://example/results/demo2";

    public static void main(String[] args) {
        DataflowPipelineOptions options = PipelineOptionsFactory.as(DataflowPipelineOptions.class);
        options.setJobName("job");
        options.setProject("project");
        options.setRegion("australia-southeast1");
        options.setRunner(DataflowRunner.class);
        options.setGcpTempLocation("gs://example/tmp");

        Pipeline pipeline = Pipeline.create(options);

        List<String> input = Arrays.asList("First", "second", "third");
        pipeline.apply(Create.of(input))
                .apply(TextIO.write().to(PATH)
                        .withSuffix(".txt"));
        pipeline.run();
    }

}
