package edu.lysak.pipeline.decrypt;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.FileIO;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.ParDo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DecryptRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        Options options = PipelineOptionsFactory
                .fromArgs(args)
                .withoutStrictParsing()
                .as(Options.class);
        decrypt(options);
    }

    static void decrypt(Options options) {

        Pipeline p = Pipeline.create(options);

//        p.apply("Read text", TextIO.read().from(options.getInputFile()))
//                .apply(new Decrypt())
//                .apply("Write decrypted text", TextIO
//                        .write()
//                        .to(options.getOutputFile())
//                        .withNumShards(1)
//                );
        p.apply("Read text", FileIO.match().filepattern(options.getInputFile()))
                .apply(FileIO.readMatches())
                .apply(ParDo.of(new DecryptFileIOFn()))
                .apply("Write decrypted text", TextIO
                        .write()
                        .to(options.getOutputFile())
                        .withNumShards(1)
                );

        p.run().waitUntilFinish();
    }
}
