package edu.lysak.pipeline.decrypt;

import org.apache.beam.sdk.transforms.PTransform;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.values.PCollection;

public class Decrypt extends PTransform<PCollection<String>, PCollection<String>> {

    @Override
    public PCollection<String> expand(PCollection<String> line) {
        return line.apply(ParDo.of(new DecryptFn()));
    }
}
