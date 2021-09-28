package functions;

public class LinkedListTabulatedFunction {
    protected int count;
    private Node head;
    private Node tail;

    public LinkedListTabulatedFunction(double[] xValues, double[] yValues) {
        head = null;
        for (int i = 0; i < xValues.length; i++) {
            addNode(xValues[i], yValues[i]);

        }
    }

    public LinkedListTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        head = null;
        double steps = (xTo - xFrom) / (count - 1);// интервалы
        for (int i = 0; i < count; i++) {
            double value = xFrom + i * steps;
            addNode(value, source.apply(value));
        }
    }

    public boolean isEmpty() {
        if (head == null)
            return true;
        return false;
    }

    private void addNode(double x, double y) {

        Node temp = new Node(x, y);
        if (isEmpty()) {
            head = temp;
            temp.next = temp;
            temp.prev = temp;
        } else {
            Node tail = head.prev; // ссылка на конец списка
            head.prev = temp;
            temp.prev = tail;
            temp.next = head;
            tail.next = temp;
        }
        count++;

    }

    public int getCount() {
        return count;
    }

    public double leftBound() {
        return head.x;
    }

    public double rightBound() {
        return head.prev.x;
    }

    private Node getNode(int index) {
        Node other = head;// ссылка на следующее значениев списке после value
        Node value = null;// значение, которое хотим получить
        for (int i = 0; i < index; i++) {
            other = other.next;
            value = other;
        }
        return value;
    }

    public double getX(int index) {
        return getNode(index).x;
    }

    public double getY(int index) {
        return getNode(index).y;
    }

    public void setY(int index, double value) {
        getNode(index).y = value;
    }

    public int indexOfX(double x) {// если не найден Х
        Node temp = head;
        for (int i = 0; i < count; i++) {
            if (temp.x == x) return i;
            temp = temp.next;
        }
        return -1;
    }

    public int indexOfY(double y) {// если не найден Y
        Node temp = head;
        for (int i = 0; i < count; i++) {
            if (temp.y == y) return i;
            temp = temp.next;
        }
        return -1;
    }

    public int floorIndexOfX(double x) {
        Node xX = head;
        double dif = 0;
        int minIndex = -1;

        for (int i = 0; i < count; i++) {
            if (xX.x == x) return i;// возвращает номер элемента в списке, равного х
            else {
                dif = x - xX.x;
                if (dif > 0) minIndex = i;
            }
            xX.x = xX.next.x;
        }

        if (minIndex == -1) return 0;// мин индекс не поменялся, следовательно все числа в списке больше х
        else{
            if (dif>0) return count;// вернется если все меньше х
            else return minIndex;
        }


    }

    private double extrapolateLeft(double x) {
        if (count == 1) return head.x;
        else {
            double tempX_ = x;// передаваемый х
            double tempX = head.x;// x 0
            double tempX1 = head.next.x;// x 1
            double tempY = head.y;// y 0
            double tempY1 = head.next.y;// y 1
            double tabulatedY = tempY + ((tempY1 - tempY) * (tempX_ - tempX)) / (tempX1 - tempX);
            return tabulatedY;
        }
    }

    private double extrapolateRight(double x) {
        if (count == 1) return head.x;
        else {
            double tempX_ = x;// передаваемый х
            double tempX = head.prev.x;// x 0
            double tempX1 = head.prev.prev.x;// x 1
            double tempY = head.prev.y;// y 0
            double tempY1 = head.prev.prev.y;// y 1
            double tabulatedY = tempY + ((tempY1 - tempY) * (tempX_ - tempX)) / (tempX1 - tempX);
            return tabulatedY;
        }
    }

    private double interpolate(double x, int floorIndex) {// передается минимальный индекс
        if (count == 1) return head.x;
        else {
            double tempX_ = x;// передаваемый х
            double tempX = getNode(floorIndex).x;// x k-1
            double tempX1 = getNode(floorIndex + 1).x;// x k
            double tempY = getNode(floorIndex).y;// k-1 Y
            double tempY1 = getNode(floorIndex + 1).y;//k Y
            double tabulatedY = tempY + ((tempY1 - tempY) * (tempX_ - tempX)) / (tempX1 - tempX);
            return tabulatedY;
        }
    }
}

