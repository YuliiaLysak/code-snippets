package edu.lysak.pipeline.decrypt;

import org.apache.beam.sdk.options.Default;
import org.apache.beam.sdk.options.Description;
import org.apache.beam.sdk.options.PipelineOptions;

public interface Options extends PipelineOptions {

    @Description("Path to the input file")
    @Default.String("./apache-beam/src/main/resources/decrypt/input.txt")
    String getInputFile();
    void setInputFile(String value);

    @Description("Path to the output file")
    @Default.String("./apache-beam/src/main/resources/decrypt/output.txt")
    String getOutputFile();
    void setOutputFile(String value);
}
