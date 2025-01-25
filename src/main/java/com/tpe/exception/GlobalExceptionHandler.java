package com.tpe.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
// uygulamada meydana gelen hataları veya istisnaları global düzeyde ele almak için kullanılır
//burada try-catch durumunu direk global olarak yapiyoruz tek tek yapmak yerine
public class GlobalExceptionHandler {

    @ExceptionHandler({BookNotFoundException.class, OwnerNotFoundException.class})
    public ResponseEntity<Map<String, Object>> handleEntityNotFoundException(Exception ex, HttpServletRequest request){
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("error message", ex.getMessage());
        body.put("path", request.getRequestURI());
        body.put("status", HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND); //404
    }

    //Practice: ConfilictException'i handle edelim : status BAD-REQUEST

}
