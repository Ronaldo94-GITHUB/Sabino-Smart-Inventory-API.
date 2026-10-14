package com.sabino.inventory.controller;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@RestControllerAdvice
public class ApiExceptionHandler {
  @ExceptionHandler(IllegalArgumentException.class)
  ResponseEntity<Map<String,String>> invalido(IllegalArgumentException ex) {
    return ResponseEntity.badRequest().body(Map.of("erro", ex.getMessage()));
  }
  @ExceptionHandler(NoSuchElementException.class)
  ResponseEntity<Map<String,String>> ausente(NoSuchElementException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("erro", ex.getMessage()));
  }
  @ExceptionHandler(MethodArgumentNotValidException.class)
  ResponseEntity<Map<String,String>> validacao(MethodArgumentNotValidException ex) {
    return ResponseEntity.badRequest().body(Map.of("erro", "Dados de entrada inválidos"));
  }
  @ExceptionHandler(ArithmeticException.class)
  ResponseEntity<Map<String,String>> limite(ArithmeticException ex) {
    return ResponseEntity.badRequest().body(Map.of("erro", "Quantidade excede limite inteiro"));
  }
}
