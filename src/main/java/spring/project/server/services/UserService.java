/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.project.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.project.server.exceptions.UserNotFoundException;
import spring.project.server.model.User;
import spring.project.server.repositories.UserRepository;
import spring.project.server.security.UserWrapper;

import java.util.List;

/**
 * @author Veljko
 */
@Service
@Transactional
public class UserService implements UserDetailsService {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setPasswordEncoder(final PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setUserRepository(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(final int id) {
        return userRepository.findById(id).orElse(null);
    }

    public void deleteUser(final int id) {
        userRepository.deleteById(id);
    }

    public void saveUser(final User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void updateUser(final int id, final User updatedUser) {
        final User user = getUserById(id);
        if (user == null) {
            throw new UserNotFoundException("User with id " + id + " does not exists.");
        }
        user.setUsername(updatedUser.getUsername());
        user.setPassword(updatedUser.getPassword());
        user.setActive(updatedUser.isActive());
        user.setAdministator(updatedUser.isAdministator());
        userRepository.save(user);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User login(final User user) {
        final User databaseUser = userRepository.findByUsername(user.getUsername());
        if (databaseUser == null || !passwordEncoder.matches(user.getPassword(), databaseUser.getPassword())) {
            return null;
        }
        return databaseUser;
    }

    public User findByUsername(final User user) {
        return userRepository.findByUsername(user.getUsername());
    }

    public void updateUsers(final List<User> users) {
        users.forEach((user) -> {
            userRepository.save(user);
        });
    }

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        final User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found.");
        }
        return new UserWrapper(user);
    }

}
