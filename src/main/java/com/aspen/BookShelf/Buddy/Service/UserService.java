package com.aspen.BookShelf.Buddy.Service;

import com.aspen.BookShelf.Buddy.Dto.Authentication.AuthenticationResponse;
import com.aspen.BookShelf.Buddy.Dto.Authentication.RegisterRequest;
import com.aspen.BookShelf.Buddy.Entity.Role;
import com.aspen.BookShelf.Buddy.Entity.User;
import com.aspen.BookShelf.Buddy.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public AuthenticationResponse registerUser(RegisterRequest request){
        Optional<User> existingUser = userRepo.findByEmail(request.getEmail());
        if(existingUser.isPresent()){
            throw new RuntimeException("User with this email already exists.");
        }

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        userRepo.save(user);

        String token = jwtService.generateToken(user.getEmail());
        return new AuthenticationResponse(token);
    }


}
