package iw.graph.lights_out.core.exception_handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;

@RestControllerAdvice
public class GlobalErrorHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        var path = ((ServletWebRequest)request).getRequest().getRequestURI();

        body = Error.builder()
                .status(statusCode.value())
                .descricao(ex.getMessage())
                .path(path)
                .timestamp(OffsetDateTime.now())
                .build();

        return super.handleExceptionInternal(ex, body, headers, statusCode, request);
    }
}
