package language_detection.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.NoSuchAlgorithmException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GeneralException.class)
    public @ResponseBody ExceptionResponse handle404(GeneralException ge) {
        ExceptionResponse error = new ExceptionResponse();
        error.setMessage(ge.getMessage());
        error.setStatus("error");
        error.setResponseCode(HttpStatus.BAD_REQUEST);
        return error;
    }

    @ExceptionHandler(NoSuchAlgorithmException.class)
    public @ResponseBody ExceptionResponse handleBadAlgorithm(NoSuchAlgorithmException nsa) {
        ExceptionResponse error = new ExceptionResponse();
        error.setMessage(nsa.getMessage());
        error.setStatus("error");
        error.setResponseCode(HttpStatus.BAD_REQUEST);
        return error;
    }

    @ExceptionHandler(AuthenticationException.class)
    public @ResponseBody ExceptionResponse handleBadApiKey(AuthenticationException ae) {
        ExceptionResponse error = new ExceptionResponse();
        error.setMessage(ae.getMessage());
        error.setStatus("invalid");
        error.setResponseCode(HttpStatus.UNAUTHORIZED);
        return error;
    }
}
