package com.tripzy.Service.Implementation;

import com.tripzy.DTOs.JwtResponseDTO;
import com.tripzy.DTOs.UserDTO;
import com.tripzy.DTOs.UserNoPasswordDTO;
import com.tripzy.Entities.PasswordResetToken;
import com.tripzy.Entities.User;
import com.tripzy.Repository.PasswordResetTokenRepository;
import com.tripzy.Repository.UserRepository;
import com.tripzy.Service.Interface.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordResetTokenRepository passwordResetTokenRepository;



    @Override
    public User registerUser(UserDTO userDTO) {
        User ans=new User();
        ans.setName(userDTO.getName());
        ans.setPhone(userDTO.getPhone());
        ans.setEmail(userDTO.getEmail());
        ans.setPassword(userDTO.getPassword());
        ans.setDateOfBirth(LocalDate.from(userDTO.getDateOfBirth()));
        ans.setSurname(userDTO.getSurname());
        return userRepository.save(ans);
    }

    @Override
    public User updateUser(UserNoPasswordDTO userDTO, Long id) {
        User user=userRepository.findById(id).get();
        if(userDTO.getName()!=null) user.setName(userDTO.getName());
        if(userDTO.getPhone()!=null) user.setPhone(userDTO.getPhone());
        if(userDTO.getEmail()!=null) user.setEmail(userDTO.getEmail());
        if(userDTO.getDateOfBirth()!=null) user.setDateOfBirth(LocalDate.from(userDTO.getDateOfBirth()));
        if(userDTO.getSurname()!=null) user.setSurname(userDTO.getSurname());
        return userRepository.save(user);
    }

    @Override
    public boolean requestPasswordReset(String email) {
        Optional<User> user=userRepository.findByEmail(email);
        if(user.isEmpty()){
            return false;
        }
        User ourUser=user.get();
        String token= UUID.randomUUID().toString();
        LocalDateTime expiryDate=LocalDateTime.now().plusMinutes(10);
        PasswordResetToken passwordResetToken=new PasswordResetToken();
        passwordResetToken.setExpiryDate(expiryDate);
        passwordResetToken.setToken(token);
        passwordResetToken.setUser(ourUser);
        passwordResetToken.setUsed(false);
        passwordResetTokenRepository.save(passwordResetToken);
        //SEND THE REQUEST TO THE EMAIL
        return true;
    }

    @Override
    public boolean resetPassword(Long id,String token, String newPassword) {
        Optional<User> userOptional=userRepository.findById(id);
        if(userOptional.isEmpty())return false;
        User user=userOptional.get();
        Optional<PasswordResetToken>passwordResetToken=passwordResetTokenRepository.findByUserId(id);
        if(passwordResetToken.isEmpty())return false;
        if(!passwordResetToken.get().getToken().equals(token) || passwordResetToken.get().isUsed() || passwordResetToken.get().getExpiryDate().isBefore(LocalDateTime.now()))return false;
        user.setPassword(newPassword);
        userRepository.save(user);
        passwordResetToken.get().setUsed(true);
        passwordResetTokenRepository.save(passwordResetToken.get());
        return true;
    }

    String generateToken(){
        return UUID.randomUUID().toString();
    }
    @Override
    public JwtResponseDTO login(String email, String password) {
        Optional<User> user=userRepository.findByEmail(email);
        if(user.isEmpty())return null;
        if(!password.equals(user.get().getPassword()))return null;
        JwtResponseDTO ans=new JwtResponseDTO();
        ans.setJwtToken(generateToken());
        ans.setUserId(user.get().getId());
        return ans;
    }


    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
}
