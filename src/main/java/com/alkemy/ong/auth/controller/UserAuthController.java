package com.alkemy.ong.auth.controller;


import com.alkemy.ong.domain.dto.*;
import com.alkemy.ong.auth.service.UserDetailsCustomService;
import com.alkemy.ong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class UserAuthController {
    @Autowired
    UserService userService;

    @Autowired
    UserDetailsCustomService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<BasicUserDTO> signup(@Valid @RequestBody UserDTO user) throws Exception {
        BasicUserDTO result = this.userDetailsService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);

    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> signIn(@Valid @RequestBody AuthenticationRequest authRequest) throws Exception {
        String username= userDetailsService.getUsername(authRequest);

        return ResponseEntity.ok(new AuthenticationResponse(username));
    }

    @GetMapping("/me")
    public ResponseEntity<UserProfileDTO> getProfile(HttpServletRequest request) {

        UserProfileDTO dto = userService.getUserProfile(request);
        return ResponseEntity.ok().body(dto);
    }


}
