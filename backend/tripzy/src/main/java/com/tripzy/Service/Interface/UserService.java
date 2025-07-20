package com.tripzy.Service.Interface;

import com.tripzy.DTOs.JwtResponseDTO;
import com.tripzy.DTOs.UserDTO;
import com.tripzy.DTOs.UserNoPasswordDTO;
import com.tripzy.Entities.User;

import java.util.Optional;

public interface UserService {
    User registerUser(UserDTO userDTO);
    User updateUser(UserNoPasswordDTO userNoPasswordDTO, Long id);
    boolean requestPasswordReset(String email);
    boolean resetPassword(Long id,String token,String newPassword);
    JwtResponseDTO login(String email, String password);
    Optional<User> findByEmail(String email);
    Optional<User> findById(Long id);
}
