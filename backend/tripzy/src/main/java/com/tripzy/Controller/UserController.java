package com.tripzy.Controller;

import com.tripzy.DTOs.JwtResponseDTO;
import com.tripzy.DTOs.UserDTO;
import com.tripzy.DTOs.UserNoPasswordDTO;
import com.tripzy.Entities.User;
import com.tripzy.Repository.UserRepository;
import com.tripzy.Service.Interface.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<UserNoPasswordDTO> registerUser(@RequestBody UserDTO userDTO){
        User user=userService.registerUser(userDTO);
        return new ResponseEntity<>(mapToUserDTO(user), HttpStatus.CREATED);
    }

    private UserNoPasswordDTO mapToUserDTO(User user) {
        UserNoPasswordDTO ans= new UserNoPasswordDTO();
        ans.setName(user.getName());
        ans.setSurname(user.getSurname());
        ans.setPhone(user.getPhone());
        ans.setEmail(user.getEmail());
        ans.setDateOfBirth(user.getDateOfBirth());
        ans.setId(user.getId());
        return ans;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponseDTO>login(@RequestParam String email, @RequestParam String password){
        JwtResponseDTO ans=userService.login(email,password);
        if(ans==null)return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(ans,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserNoPasswordDTO> updateUser(@PathVariable("id") Long id,@RequestBody UserNoPasswordDTO userNoPasswordDTO){
        User user=userRepository.findById(id).orElseThrow(()->new RuntimeException("User not found"));
        user=userService.updateUser(userNoPasswordDTO,id);
        return new ResponseEntity<>(mapToUserDTO(user),HttpStatus.OK);
    }

    @PostMapping("/auth/requestReset")
    public ResponseEntity<String> requestPasswordReset(@RequestParam String email){
        if(userService.requestPasswordReset(email))return new ResponseEntity<>("Password reset request sent",HttpStatus.OK);
        return new ResponseEntity<>("User Not Found with Given Email ",HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/auth/reset")
    public ResponseEntity<String> resetPassword(@RequestParam Long userId,@RequestParam String token,@RequestParam String newPassword){
        if(userService.resetPassword(userId,token,newPassword))return new ResponseEntity<>("Password Reset Successful",HttpStatus.OK);
        return new ResponseEntity<>("Token invalid or Expired",HttpStatus.BAD_REQUEST);
    }
}
