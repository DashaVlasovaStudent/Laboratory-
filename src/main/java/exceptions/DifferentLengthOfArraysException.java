package exceptions;

import java.io.Serial;

public class DifferentLengthOfArraysException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 6118427772521685564L;

    public DifferentLengthOfArraysException() {
        super();
    }

    public DifferentLengthOfArraysException(String string) {
        super(string);
    }
}
