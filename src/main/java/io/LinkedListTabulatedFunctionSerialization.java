package io;

import functions.LinkedListTabulatedFunction;
import functions.TabulatedFunction;
import functions.factory.LinkedListTabulatedFunctionFactory;
import operations.TabulatedDifferentialOperator;

import java.io.*;

public class LinkedListTabulatedFunctionSerialization {
    public static void main(String[] args) {
        TabulatedDifferentialOperator difOperator = new TabulatedDifferentialOperator(new LinkedListTabulatedFunctionFactory());
        double[] xValues = {1, 2, 3};
        double[] yValues = {6, 7, 8};
        LinkedListTabulatedFunction linkedListTabulatedFunction = new LinkedListTabulatedFunction(xValues, yValues);
        TabulatedFunction linkedListDifferentialOnceFunction = difOperator.derive(linkedListTabulatedFunction);
        TabulatedFunction linkedListDifferentialTwiceFunction = difOperator.derive(linkedListDifferentialOnceFunction);

        try (BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(//
                 "output/serialized linked list functions.bin"))) {
            FunctionsIO.serialize(outputStream, linkedListTabulatedFunction);
            FunctionsIO.serialize(outputStream, linkedListDifferentialOnceFunction);
            FunctionsIO.serialize(outputStream, linkedListDifferentialTwiceFunction);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(//
                "output/serialized linked list functions.bin"))){
            TabulatedFunction func = FunctionsIO.deserialize(inputStream);
            TabulatedFunction funcDiffOnce = FunctionsIO.deserialize(inputStream);
            TabulatedFunction funcDiffTwice = FunctionsIO.deserialize(inputStream);
            System.out.println(func.toString());
            System.out.println(funcDiffOnce.toString());
            System.out.println(funcDiffTwice.toString());
        } catch (IOException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }
    }
}