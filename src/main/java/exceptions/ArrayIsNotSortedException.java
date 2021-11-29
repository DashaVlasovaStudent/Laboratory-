package exceptions;

import java.io.Serial;

public class ArrayIsNotSortedException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -1408542045257113640L;

    public ArrayIsNotSortedException() {
        super();
    }

    public ArrayIsNotSortedException(String string) {
        super(string);
    }
}
