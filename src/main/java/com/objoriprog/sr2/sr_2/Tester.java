package com.objoriprog.sr2.sr_2;

import java.util.Random;

public class Tester {
    String false_out(int count) {
        StringBuilder result = new StringBuilder("Умова хибна\n");
        double x;
        for (float i = 0; i < count; i++) {
            Random rand = new Random();
            x = rand.nextDouble() % Math.PI;
            result.append(String.format("%.3f", x)).append("\t\t").append(String.format("%.3f", (1 / Math.tan(Math.PI * x)))).append("\n");
        }
        return result.toString();
    }

    String mid_out() {
        return "Граничний випадок\n" + String.format("%.3f", Math.PI) + "\t\t" + String.format("%.3f", Math.sin(Math.PI * Math.PI)) + "\n";
    }

    String true_out(int count) {
        StringBuilder result = new StringBuilder("Умова істинна:\n");
        Random rand = new Random();
        double x;
        double fx;
        for (float i = 0; i < count; i++) {
            x = rand.nextDouble() % Math.PI;
            fx = Math.sin(Math.PI * x);
            result.append(String.format("%.3f", x)).append("\t\t").append(String.format("%.3f", fx)).append("\n");
        }
        return result.toString();
    }

}
