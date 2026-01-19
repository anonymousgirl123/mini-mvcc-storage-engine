package com.minimvcc.benchmark;

import java.io.FileWriter;
import java.io.IOException;

public class BenchmarkRunner {

    public static void main(String[] args) throws IOException {
        long start = System.nanoTime();

        // simple CPU + allocation pressure
        for (int i = 0; i < 5_000_000; i++) {
            String s = "v" + i;
        }

        long end = System.nanoTime();
        long ms = (end - start) / 1_000_000;

        try (FileWriter fw = new FileWriter("benchmark-results.txt")) {
            fw.write("Simple allocation benchmark\n");
            fw.write("Duration(ms): " + ms + "\n");
        }

        System.out.println("Benchmark completed in " + ms + " ms");
    }
}
