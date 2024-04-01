package spring.project.server.controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import spring.project.server.exceptions.BusyUsernameException;
import spring.project.server.exceptions.UserNotFoundException;
import spring.project.server.exceptions.WrongCredentials;
import spring.project.server.model.User;
import spring.project.server.services.UserService;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Veljko
 */
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/users")
@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveUser(@Valid @RequestBody final User user) {
        userService.saveUser(user);
    }


    @GetMapping("{id}")
    public User getUserById(@PathVariable(value = "id") final int id) {
        final User user = userService.getUserById(id);
        if (user == null) {
            throw new UserNotFoundException("User with id " + id + " does not exists.");
        }
        return user;
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable(value = "id") final int id) {
        userService.deleteUser(id);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUser(@PathVariable final int id, @Valid @RequestBody final User user) {
        userService.updateUser(id, user);
    }

    @GetMapping()
    public List<User> getAllUsers() {
        return userService.getAll();
    }

    @PostMapping("login")
    public User login(@RequestBody final User userRequest) {
        final User user = userService.login(userRequest);
        if (user == null) {
            throw new WrongCredentials("Username or password are wrong. Please, try again.");
        }
        return user;
    }


    @PostMapping("username")
    public User findByUsername(@RequestBody final User requestUser) {
        final User user = userService.findByUsername(requestUser);
        if (user != null) {
            throw new BusyUsernameException("User with username " + requestUser.getUsername() + "exists.");
        }
        return requestUser;
    }

    @PutMapping("update")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUsers(@RequestBody final List<User> users) {
        userService.updateUsers(users);
    }
}
