package iw.graph.lights_out.domain.exception;

public class ObjectNotFoundException extends LightsOutException {
    public ObjectNotFoundException(String message) {
        super(message);
    }

    public ObjectNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
