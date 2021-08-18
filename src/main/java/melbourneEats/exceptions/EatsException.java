package melbourneEats.exceptions;

import java.io.Serial;

public class EatsException extends Exception {
    @Serial
    private static final long serialVersionUID = 1;

    public EatsException(String message) {
        super(message);
    }
}
