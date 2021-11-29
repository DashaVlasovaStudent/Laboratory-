package exceptions;

import java.io.Serial;

public class InterpolationException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -4507929550391638261L;

    public InterpolationException() {
        super();
    }

    public InterpolationException(String message) {
        super(message);
    }
}
