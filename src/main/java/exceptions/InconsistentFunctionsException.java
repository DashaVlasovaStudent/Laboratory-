package exceptions;

import java.io.Serial;

public class InconsistentFunctionsException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 5841873192130389356L;
    public InconsistentFunctionsException() {
        super();
    }
    public InconsistentFunctionsException(String string) {
        super(string);
    }
}
