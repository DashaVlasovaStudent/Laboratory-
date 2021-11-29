package io;

import functions.Point;
import functions.TabulatedFunction;
import functions.factory.TabulatedFunctionFactory;

import java.awt.*;
import java.io.*;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;

public final class FunctionsIO {
    private FunctionsIO() {
        throw new UnsupportedOperationException();
    }

    public static TabulatedFunction deserialize(BufferedInputStream stream) throws IOException, ClassNotFoundException {
        return (TabulatedFunction) new ObjectInputStream(stream).readObject();
    }

    public static void serialize(BufferedOutputStream stream, TabulatedFunction function) throws IOException {
        ObjectOutputStream outputStream = new ObjectOutputStream(stream);
        outputStream.writeObject(function);
        outputStream.flush();
    }


    public static void writeTabulatedFunction(BufferedOutputStream outputStream, TabulatedFunction function) throws IOException {
        DataOutputStream outputStream1 = new DataOutputStream(outputStream);
        int count = function.getCount();
        outputStream1.writeInt(count);
        for (Point point : function) {
            outputStream1.writeDouble(point.x);
            outputStream1.writeDouble(point.y);
        }
        outputStream1.flush();
    }

    public static TabulatedFunction readTabulatedFunction(BufferedInputStream inputStream, TabulatedFunctionFactory factory) throws IOException {
        DataInputStream inStream = new DataInputStream(inputStream);
        int count = inStream.read();

        double[] xValues = new double[count];
        double[] yValues = new double[count];

        for (int i = 0; i < count; i++) {
            xValues[i] = inStream.readDouble();
            yValues[i] = inStream.readDouble();
        }
        return factory.create(xValues, yValues);
    }
}
