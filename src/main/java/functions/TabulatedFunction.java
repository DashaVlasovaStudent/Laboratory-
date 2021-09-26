package functions;

public interface TabulatedFunction extends MathFunction {
     int getCount(); // получение еоличества табулированных значений
     double getX(int index);//получение значения аргумента по х номеру индекса
     double getY(int index); //получение значения аргумента по y номеру индекса
     void setY(int index, double value);//задание значения у по номеру индекса
     int indexOfX(double x);
     int indexOfY(double y);
     double leftBound();//самый левый х
     double rightBound();//самый правый х



}
