package functions;

import exceptions.InterpolationException;

public class LinkedListTabulatedFunction extends AbstractTabulatedFunction {
    private int count;
    private Node head;

    public LinkedListTabulatedFunction(double[] xValues, double[] yValues) {
        head = null;
        for (int i = 0; i < xValues.length; i++) {
            addNode(xValues[i], yValues[i]);
        }
    }

    public LinkedListTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        if (xFrom >= xTo) {
            throw new IllegalArgumentException("Недопустимые значения границ!!");
        }

        head = null;
        double steps = (xTo - xFrom) / (count - 1);// интервалы
        for (int i = 0; i < count; i++) {
            double value = xFrom + i * steps;
            addNode(value, source.apply(value));
        }
    }

    public boolean isEmpty() {
        return head == null;
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
        if (index < 0 | index >= count) {
            throw new IllegalArgumentException("неправильный индекс!!");
        }
        return getNode(index).x;
    }

    @Override
    public double getY(int index) {
        if (index < 0 | index >= count) {
            throw new IllegalArgumentException("неправильный индекс!!");
        }
        return getNode(index).y;
    }

    @Override
    public void setY(int index, double value) {
        if (index < 0 | index >= count) {
            throw new IllegalArgumentException("неправильный индекс!!");
        }
        getNode(index).y = value;
    }

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

    public double leftBound() {
        return head.x;
    }

    public double rightBound() {
        return head.prev.x;
    }

    private Node getNode(int index) {
        if (index < 0 | index >= count) {
            throw new IllegalArgumentException("неправильный индекс!!");
        }
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    protected double extrapolateLeft(double x) {
        return super.interpolate(x, head.x, head.next.x, head.y, head.next.y);
    }

    protected double extrapolateRight(double x) {
        return super.interpolate(x, head.prev.prev.x, head.prev.x, head.prev.prev.y, head.prev.y);
    }

    protected double interpolate(double x, int floorIndex) {
        Node left = getNode(floorIndex);
        Node right = getNode(floorIndex).next;
        if (x < left.x || x > right.x){
            throw new InterpolationException();
        }

            return super.interpolate(x, left.x, right.x, left.y, right.y);
    }

    protected int floorIndexOfX(double x) {
        if (x < leftBound()) {
            throw new IllegalArgumentException("х меньше левой границы");
        }
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

    public static class Node {
        public Node next;
        public Node prev;
        public double x;
        public double y;

        public Node(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }
}