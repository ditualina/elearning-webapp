package com.alinadev.security;

/**
 * Created by: Alina Ditu
 * Date: 7/5/17
 */
import com.alinadev.service.UserAttemptsService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@Component("authenticationProvider")
public class LimitLoginAuthenticationProvider extends DaoAuthenticationProvider {

    @Resource
    private UserAttemptsService userAttemptsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        try {

            Authentication auth = super.authenticate(authentication);

            //if reach here, means login success, else exception will be thrown
            //reset the user_attempts
            userAttemptsService.resetUserAttempts(authentication.getName());

            return auth;

        } catch (BadCredentialsException e) {

            //invalid login, update to user_attempts
            userAttemptsService.updateFailAttempts(authentication.getName());
            throw e;

        } catch (LockedException e) {

            String error = "User account is locked! ";

            throw new LockedException(error);
        }

    }

}