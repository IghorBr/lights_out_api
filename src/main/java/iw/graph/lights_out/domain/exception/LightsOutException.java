package iw.graph.lights_out.domain.exception;

public class LightsOutException extends RuntimeException {

    public LightsOutException(String message) {
        super(message);
    }

    public LightsOutException(String message, Throwable cause) {
        super(message, cause);
    }
}
