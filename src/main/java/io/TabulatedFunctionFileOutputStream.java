package io;

import functions.LinkedListTabulatedFunction;
import functions.TabulatedFunction;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class TabulatedFunctionFileOutputStream {
    public static void main(String[] args) {
        double[] xValues = {1, 6, 78, 100};
        double[] yValues = {5, 60, 79, 101};


        File linkedListFile = new File("output/linked list function.bin");
        TabulatedFunction linkedListFunction = new LinkedListTabulatedFunction(xValues, yValues);

        try (BufferedOutputStream streamOutLinked = new BufferedOutputStream(new FileOutputStream(linkedListFile))) {
            FunctionsIO.writeTabulatedFunction(streamOutLinked, linkedListFunction);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
