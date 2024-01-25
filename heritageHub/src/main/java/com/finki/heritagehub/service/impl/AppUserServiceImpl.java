package com.finki.heritagehub.service.impl;

import com.finki.heritagehub.model.AppUser;
import com.finki.heritagehub.model.RoleUser;
import com.finki.heritagehub.model.exceptions.*;
import com.finki.heritagehub.repository.AppUserRepository;
import com.finki.heritagehub.service.AppUserService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;

@Service
public class AppUserServiceImpl implements AppUserService, UserDetailsService {
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
    public AppUserServiceImpl(AppUserRepository appUserRepository,PasswordEncoder passwordEncoder){
        this.appUserRepository=appUserRepository;
        this.passwordEncoder=passwordEncoder;
        create("admin",
                "admin",
                "admin",
                RoleUser.ROLE_ADMIN);
    }
    @Override
    public AppUser create(String username, String email, String password, RoleUser role) {
        if(!(appUserRepository.findByUsername(username).isEmpty() && appUserRepository.findByEmail(email).isEmpty())){
            throw new UserAlreadyExistsException();
        }

        return appUserRepository.save(new AppUser(
                username,
                email,
                passwordEncoder.encode(password),
                role));
    }

    @Override
    public AppUser findByUsername(String username) {
        return appUserRepository.findByUsername(username)
                .orElseThrow(InvalidAppUserUsernameException::new);
    }

    @Override
    public AppUser findByEmail(String email) {
        return appUserRepository.findByEmail(email).
                orElseThrow(InvalidAppUserEmailException::new);
    }

    @Override
    public List<AppUser> listAll() {
        return appUserRepository.findAll();
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        AppUser appUserUsername=appUserRepository.findByUsername(username).orElse(null);
//        AppUser appUserEmail=appUserRepository.findByEmail(username).orElse(null);
//        if(appUserEmail!=null) {
//            return new User(appUserEmail.getEmail(),
//                    appUserEmail.getPassword(),
//                    Collections.singleton(appUserEmail.getRole()));
//        }
//        else if(appUserUsername!=null){
//            return new User(appUserUsername.getUsername(),
//                    appUserUsername.getPassword(),
//                    Collections.singleton(appUserUsername.getRole()));
//        }
//        else {
//            throw new InvalidAppUserEmailException();
//        }
//    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = appUserRepository.findByUsername(username).orElseThrow(InvalidAppUserUsernameException::new);

        return new org.springframework.security.core.userdetails.User(
                appUser.getUsername(),
                appUser.getPassword(),
                Collections.singletonList(appUser.getRole())
        );
    }
    @Override
    public AppUser login(String username, String password) {
        if (username == null || password == null) {
            throw new InvalidArgumentsException();
        }

        return appUserRepository.findByUsernameAndPassword(username, passwordEncoder.encode(password))
                .orElseThrow(InvalidUserCredentialsException::new);
    }
}
