package functions;

public class LinkedListTabulatedFunction extends AbstractTabulatedFunction {
    protected int count;
    private Node head;
    private  Node tail = head.prev;


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
        if (head == null) {
            return true;
        }
        return false;
    }

    private void addNode(double x, double y) {

        Node temp = new Node(x, y);
        if (isEmpty()) {
            head = temp;
            temp.next = temp;
            temp.prev = temp;
        } else {
            Node tail = head.prev;
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

    @Override
    public double getX(int index) {
        return getNode(index).x;
    }

    @Override
    public double getY(int index) {
        return getNode(index).y;
    }

    @Override
    public void setY(int index, double value) {
        getNode(index).y = value;
    }

    @Override
    public int indexOfX(double x) {
        Node temp = head;
        for (int i = 0; i < count; i++) {
            if (temp.x == x) {
                return i;
            }
            temp = temp.next;
        }
        return -1;
    }

    @Override
    public int indexOfY(double y) {
        Node temp = head;
        for (int i = 0; i < count; i++) {
            if (temp.y == y) {
                return i;
            }
            temp = temp.next;
        }
        return -1;
    }

    @Override
    public double leftBound() {

        return head.x;
    }

    @Override
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

    @Override
    protected double extrapolateLeft(double x) {
        if (head.x == rightBound()){
            return head.y;
        }
        return interpolate(x, head.x, head.next.x, head.y, head.next.y);
    }

    @Override
    protected double extrapolateRight(double x) {
        if (head.x == rightBound()){
            return head.y;
        }
        return interpolate (x, tail.prev.x, tail.x, tail.prev.y, tail.y);
    }

    @Override
    protected double interpolate(double x, int floorIndex) {
        if (head.x == tail.x){
            return head.y;
        }
        return interpolate(x, getNode(floorIndex).x, getNode(floorIndex).next.x, getNode(floorIndex).y, getNode(floorIndex).next.x );
    }

    @Override
    protected int floorIndexOfX(double x) {
        Node temp = head;
        Node current = null;
        double dif = 0;
        for (int i = 0; i < count; i++) {
            if (temp.x == x) {
                return indexOfX(temp.x);
            }
            dif = x - temp.x;
            if (dif > 0) {
                current = temp;
            }
            temp = temp.next;
        }
        if (current == null) {
            if (dif < 0) {
                return 0;
            }
        } else if (dif > 0) {
            return count;
        }
        return indexOfX(current.x);


    }
}