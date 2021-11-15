package io;

import functions.Point;
import functions.TabulatedFunction;

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
}
