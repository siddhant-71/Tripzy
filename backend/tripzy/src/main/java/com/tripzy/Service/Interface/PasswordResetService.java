package com.tripzy.Service.Interface;

import com.tripzy.Entities.PasswordResetToken;
import com.tripzy.Entities.User;

public interface PasswordResetService {
    PasswordResetToken createToken(Long userId);
    boolean validateToken(String token);
    boolean marksTokenAsUsed(String token);
    //DELETE EXPIRED TOKENS PERIODICALLY SCHEDULING
}
