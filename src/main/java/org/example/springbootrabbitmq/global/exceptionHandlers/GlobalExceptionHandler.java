package org.example.springbootrabbitmq.global.exceptionHandlers;

import lombok.RequiredArgsConstructor;
import org.example.springbootrabbitmq.global.exceptions.GlobalException;
import org.example.springbootrabbitmq.global.rq.Rq;
import org.example.springbootrabbitmq.global.rsData.RsData;
import org.example.springbootrabbitmq.standard.base.Empty;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    private final Rq rq;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex) {
        // if (!rq.isApi()) throw ex;

        return handleApiException(ex);
    }

    private ResponseEntity<Object> handleApiException(Exception ex) {
        Map<String, Object> body = new LinkedHashMap<>();

        int statusCode;

        if (ex instanceof AccessDeniedException) {
            statusCode = 403;

            body.put("resultCode", statusCode + "-1");
            body.put("statusCode", statusCode);
        } else {
            statusCode = 500;

            body.put("resultCode", statusCode + "-1");
            body.put("statusCode", statusCode);
        }

        body.put("msg", ex.getLocalizedMessage());

        LinkedHashMap<String, Object> data = new LinkedHashMap<>();
        body.put("data", data);

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        data.put("trace", sw.toString().replace("\t", "    ").split("\\r\\n"));

        String path = rq.getCurrentUrlPath();
        data.put("path", path);

        body.put("success", false);
        body.put("fail", true);

        return new ResponseEntity<>(body, HttpStatusCode.valueOf(statusCode));
    }

    @ExceptionHandler(GlobalException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @RequestMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<RsData<Empty>> handle(GlobalException ex) {
        HttpStatus status = HttpStatus.valueOf(ex.getRsData().getStatusCode());
        rq.setStatusCode(ex.getRsData().getStatusCode());

        return new ResponseEntity<>(ex.getRsData(), status);
    }
}
