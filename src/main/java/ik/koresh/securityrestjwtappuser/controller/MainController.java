package ik.koresh.securityrestjwtappuser.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class MainController {
    @GetMapping("/unsecured")
    public ResponseEntity<Object> unsecuredDate(){
        return ResponseEntity.ok("Unsecured date");
    }

    @GetMapping("/secured")
    public ResponseEntity<Object> securedDate(){
        return ResponseEntity.ok("Secured date");
    }

    @GetMapping("/admin")
    public ResponseEntity<Object> adminDate(){
        return ResponseEntity.ok("Admin date");
    }

    @GetMapping("/info")
    public ResponseEntity<Object> unsecuredDate(Principal principal){
        return ResponseEntity.ok(principal.getName());
    }

}
