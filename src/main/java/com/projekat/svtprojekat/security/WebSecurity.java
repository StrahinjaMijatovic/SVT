package com.projekat.svtprojekat.security;

import com.projekat.svtprojekat.entity.User;
import com.projekat.svtprojekat.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class WebSecurity {

    @Autowired
    private UserService userService;

    public boolean checkClubId(Authentication authentication, HttpServletRequest request, int id) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userService.findUserByUsername(userDetails.getUsername());
        if(id == user.getId()) {
            return true;
        }
        return false;
    }
}
