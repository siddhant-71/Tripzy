package com.tripzy.Controller;

import com.tripzy.Service.Interface.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/password")
@AllArgsConstructor
public class PasswordResetController {
    private final UserService userService;

    @PostMapping("/request")
    public ResponseEntity<String> requestPassword(@RequestParam String email){
        if(userService.requestPasswordReset(email))return ResponseEntity.ok("Password Reset Request Sent");
        return ResponseEntity.badRequest().body("User Not Found with Given Email");
    }

    @PostMapping("/reset")
    public ResponseEntity<String> resetPassword(@RequestParam String token,@RequestParam Long userId,@RequestParam String newPassword){
        if(userService.resetPassword(userId,token,newPassword))return ResponseEntity.ok("Password Reset Successfully");
        return ResponseEntity.badRequest().body("Token Invalid or Expired");
    }
}
