package io;

import functions.Point;
import functions.TabulatedFunction;
import functions.factory.TabulatedFunctionFactory;

import java.awt.*;
import java.io.*;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;

public final class FunctionsIO {
    private FunctionsIO(){
        throw new UnsupportedOperationException();
    }
    static void writeTabulatedFunction (BufferedOutputStream outputStream, TabulatedFunction function) throws IOException{
        DataOutputStream outputStream1 = new DataOutputStream(outputStream);
        int count = function.getCount();
        outputStream1.writeInt(count);
        for (Point point : function){
            outputStream1.writeDouble(point.x);
            outputStream1.writeDouble(point.y);
        }
    }
    static TabulatedFunction readTabulatedFunction(BufferedInputStream inputStream, TabulatedFunctionFactory factory) throws IOException{
        DataInputStream inStream = new DataInputStream(inputStream);
        int count = inStream.readInt();

        double[] xValues = new double[count];
        double[] yValues = new double[count];

        for (int i=0; i<inStream.readInt(); i++ ){
            xValues[i]= inStream.readDouble();
            yValues[i]= inStream.readDouble();
        }
        return factory.create(xValues, yValues);
    }
}
