package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.payload.response.MessageResponse;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {


    @GetMapping
    @PreAuthorize("hasPermission('admin:read')")
    public String get() {
        return "GET:: admin controller";
    }
    @PostMapping
    @PreAuthorize("hasAuthority('admin:create')")
    @Hidden
    public String post() {
        return "POST:: admin controller";
    }
    @PutMapping
    @PreAuthorize("hasAuthority('admin:update')")
    @Hidden
    public String put() {
        return "PUT:: admin controller";
    }
    @DeleteMapping
    @PreAuthorize("hasAuthority('admin:delete')")
    @Hidden
    public String delete() {
        return "DELETE:: admin controller";
    }

    @GetMapping("/test1")
    @PreAuthorize("hasRole('USER') && authentication.principal.function_name == 'function'")
    public ResponseEntity<?> accessApi() throws IOException {
        return ResponseEntity.ok(new MessageResponse("Public api function successfully !"));
    }

    @GetMapping("/test")
    public ResponseEntity<?> test() throws IOException {
        return ResponseEntity.ok(new MessageResponse("Public api function successfully !"));
    }

//    @GetMapping("/test2")
//    @PreAuthorize(("hasRole('USER')"))
//    public String String(HttpServletRequest request) {
//        if (request.isUserInRole("MODERATOR")) {
//            return "200 ok";
//        }
//
//        return "Public Content.";
//    }
//    @GetMapping("/test2")
//    @PreAuthorize(("hasRole('USER')"))
//    public String String(HttpServletRequest request) {
//        if (request.isUserInRole("MODERATOR")) {
//            return "200 ok";
//        }

//        return "Public Content.";
//    }
}
