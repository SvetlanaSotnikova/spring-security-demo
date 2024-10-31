package ru.gb.api;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/resource")
public class ResourceController {

  @GetMapping
  public ResponseEntity<String> resource() {
    return ResponseEntity.status(HttpStatus.OK)
            .header(HttpHeaders.SET_COOKIE, "key=value")
            .body("Public Resource - доступно всем");
  }

  @GetMapping("/auth")
  @PreAuthorize("isAuthenticated()")
  public String auth() {
    return "Authorized Resource - доступно авторизованным пользователям";
  }

  @GetMapping("/user")
  @PreAuthorize("hasAuthority('reader')")
  public String user() {
    return "Reader Resource - доступно только для роли 'reader'";
  }

  @GetMapping("/admin")
  @Secured("admin")
  public String admin() {
    return "Admin Resource - доступно только для роли 'admin'";
  }
}
