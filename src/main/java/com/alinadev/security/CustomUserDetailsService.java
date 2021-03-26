package com.alinadev.security;

import com.alinadev.model.User;
import com.alinadev.model.UserAttempts;
import com.alinadev.model.UserProfile;
import com.alinadev.service.UserAttemptsService;
import com.alinadev.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * Created by: Alina Ditu
 * Date: 6/11/17
 */
@Component
public class CustomUserDetailsService implements UserDetailsService {

    static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    private static final Integer MAX_FAIL_ATTEMPTS = 2;

    @Resource
    private UserService userService;

    @Resource
    private UserAttemptsService userAttemptsService;

    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String ssoId) throws UsernameNotFoundException {

        User user = userService.findBySSO(ssoId);
        logger.info("User : {}", user);

        if (user == null) {
            logger.info("User not found");
            throw new UsernameNotFoundException("Username not found");
        }

        Boolean userNonLocked = Boolean.TRUE;
        Optional<UserAttempts> userAttempts = userAttemptsService.retrieveByUsername(ssoId);

        if(userAttempts.isPresent()) {
            Date now = new Date() ;
            long diff = now.getTime() - userAttempts.get().getLastModified().getTime();

            if(TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) >= 1)  {
                 userAttemptsService.resetUserAttempts(ssoId);
            }

            if(userAttempts.get().getAttempts() > MAX_FAIL_ATTEMPTS) {
                userNonLocked = Boolean.FALSE;
            }
        }

        return new org.springframework.security.core.userdetails.User(user.getSsoId(), user.getPassword(),
                true, true, true, userNonLocked, getGrantedAuthorities(user));
    }


    private List<GrantedAuthority> getGrantedAuthorities(User user) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        for (UserProfile userProfile : user.getUserProfiles()) {
            logger.info("UserProfile : {}", userProfile);
            authorities.add(new SimpleGrantedAuthority("ROLE_" + userProfile.getType()));
        }
        logger.info("authorities : {}", authorities);
        return authorities;
    }

}

