package edu.lysak.pipeline.csv;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;

public class SimplePipelineCsv {
    public static void main(String[] args) {
        Pipeline pipeline = Pipeline.create();
        pipeline
            .apply(TextIO.read()
                .from("./apache-beam/src/main/resources/csv/cities.csv")
            )
            .apply(TextIO.write()
                .to("./apache-beam/src/main/resources/csv/cities-out")
                .withSuffix(".csv")
                .withNumShards(1)
            );

        pipeline.run().waitUntilFinish();
    }
}
