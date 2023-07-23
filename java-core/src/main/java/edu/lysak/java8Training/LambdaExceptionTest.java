package edu.lysak.java8Training;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LambdaExceptionTest {

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		Foo2 foo = BufferedReader::read;
        Foo3 foo3 = in -> {
            try {
                return in.read();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                throw new RuntimeException(e);
            }
        };
    }

}

interface Foo2 {
    int execute(BufferedReader in) throws IOException;
}

interface Foo3 {
    int execute(BufferedReader in);
}