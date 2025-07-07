package com.ven.security.auth.jwtroleauth.bootstrap;

import com.ven.security.auth.jwtroleauth.dto.RegisterUserDto;
import com.ven.security.auth.jwtroleauth.entities.Role;
import com.ven.security.auth.jwtroleauth.entities.RoleEnum;
import com.ven.security.auth.jwtroleauth.entities.User;
import com.ven.security.auth.jwtroleauth.repository.RoleRepository;
import com.ven.security.auth.jwtroleauth.repository.UserRepository;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AdminSeeder implements ApplicationListener<ContextRefreshedEvent> {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    public AdminSeeder(UserRepository userRepository,
                       RoleRepository roleRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        this.createAdmins();
    }

    private void createAdmins() {
        RegisterUserDto adminDto = new RegisterUserDto();
        adminDto.setFullName("Super Admin").setEmail("super.admin@email.com")
                .setPassword("123456");

        Optional<Role> optionalRole = roleRepository.findByName(RoleEnum.SUPER_ADMIN);
        Optional<User> optionalUser = userRepository.findByEmail(adminDto.getEmail());

        if (optionalRole.isEmpty() || optionalUser.isPresent()) {
            return;
        }
        User user = new User()
                .setFullName(adminDto.getFullName())
                .setEmail(adminDto.getEmail())
                .setPassword(passwordEncoder.encode(adminDto.getPassword()))
                .setRole(optionalRole.get());
        userRepository.save(user);
    }
}
