package com.tripzy.Service.Implementation;

import com.tripzy.Entities.PasswordResetToken;
import com.tripzy.Entities.User;
import com.tripzy.Repository.PasswordResetTokenRepository;
import com.tripzy.Repository.UserRepository;
import com.tripzy.Service.Interface.PasswordResetService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;



@Service
@AllArgsConstructor
public class PasswordResetServiceImpl implements PasswordResetService {
    private final PasswordResetTokenRepository passwordResetTokenRepository;
    private final UserRepository userRepository;
    @Override
    public PasswordResetToken createToken(Long userId) {
        User user=userRepository.findById(userId).orElseThrow(()->new RuntimeException("User not found"));
        PasswordResetToken token=new PasswordResetToken();
        token.setUser(user);
        token.setExpiryDate(LocalDateTime.now().plusMinutes(10));
        token.setUsed(false);
        token.setToken(UUID.randomUUID().toString());
        passwordResetTokenRepository.save(token);
        return token;
    }

    @Override
    public boolean validateToken(String tokenValue) {
        Optional<PasswordResetToken> actual= passwordResetTokenRepository.findByToken(tokenValue);
        if(actual.isEmpty())return false;
        if(actual.get().isUsed())return false;
        if(actual.get().getExpiryDate().isBefore(LocalDateTime.now()))return false;
        return true;
    }

    @Override
    public boolean marksTokenAsUsed(String token) {
        passwordResetTokenRepository.findByToken(token).ifPresent(tempToken->{
            tempToken.setUsed(true);
            passwordResetTokenRepository.save(tempToken);
        });
        return true;
    }
}
