package io;

import functions.TabulatedFunction;
import functions.factory.LinkedListTabulatedFunctionFactory;
import operations.TabulatedDifferentialOperator;

import java.io.*;

public class TabulatedFunctionFileInputStream {
    public static void main(String[] args) {
        File inputFile = new File("input/binary function.bin");
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(inputFile));

            System.out.println("Please, enter function's size and values");
            TabulatedFunction func = FunctionsIO.readTabulatedFunction(bufferedInputStream, new LinkedListTabulatedFunctionFactory());
            TabulatedDifferentialOperator diffLinked = new TabulatedDifferentialOperator(new LinkedListTabulatedFunctionFactory());
            System.out.println(diffLinked.derive(func).toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
