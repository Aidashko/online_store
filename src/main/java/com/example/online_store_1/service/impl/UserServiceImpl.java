package com.example.online_store_1.service.impl;

import com.example.online_store_1.mapper.UserMapper;
import com.example.online_store_1.models.Basket;
import com.example.online_store_1.models.User;
import com.example.online_store_1.models.dto.ResponseDto;
import com.example.online_store_1.models.dto.UserDto;
import com.example.online_store_1.repository.UserRepo;
import com.example.online_store_1.service.BasketService;
import com.example.online_store_1.service.RoleService;
import com.example.online_store_1.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final BasketService basketService;
    private final UserMapper userMapper;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User saveUser(User user) {
        User newUser = null;
        if (findByEmail(user.getEmail()).isPresent()) {
            System.out.println("User already exists");
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Set.of(roleService.findByName("USER")));
            newUser = userRepo.save(user);
            basketService.save(new Basket(findByEmail(user.getEmail()).get()));
        }
        return newUser;
    }

    @Override
    public UserDto findById(Long id) {
        return userMapper.toDto(userRepo.findById(id).orElse(null));
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    @Override
    public String delete(User user) {
        return null;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public User update(User user) {
        return userRepo.save(user);
    }

    @Override
    public User getById(Long userId) {
        return userRepo.findById(userId).get();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    private void test(ResponseDto responseDto) {
        responseDto.getUser().setUsername("sdafsdf");
        responseDto.getGood().get(0);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> container = userRepo.findByUsername(username);
        if (container.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }
        User user = container.get();
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).toList()
        );
    }
//    private PasswordEncoder passwordEncoder() {
//        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//        return encoder;
//    }
}
