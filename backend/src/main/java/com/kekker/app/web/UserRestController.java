package com.kekker.app.web;

import com.kekker.app.exception.CustomException;
import com.kekker.app.model.User;
import com.kekker.app.repository.UserRepository;
import com.kekker.app.service.UserService;
import com.kekker.app.view.UserRegisterDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class UserRestController {

    private final UserRepository userRepository;
    private UserService userService;

    @Autowired
    UserRestController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    //Get all
    @GetMapping("/users")
    @CrossOrigin
    public Collection<User> findAll() {
        return userRepository.findAll();
    }

    //Get by id
    @GetMapping("/users/{nickName}")
    public User getUserByNickName(@PathVariable("nickName") String nickName) {
        return userRepository.findByNickName(nickName);
    }

    @PostMapping("/signin")
    public ResponseEntity<String> signin(@RequestParam String nickName,@RequestParam String password) {
        try {
            String token = userService.signin(nickName, password);
            return ResponseEntity.ok(token);
        } catch (CustomException ex)
        {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("");
        }
    }

    //todo(max): use modelmapping
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@Valid @RequestBody UserRegisterDto user) {
        if (userRepository.findByNickName(user.getNickName()) != null) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Nickname is taken");
        }

        if (userRepository.findByEmail(user.getEmail()) != null) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Email is taken");
        }

        User registeredUser = new User();
        registeredUser.setPassword(user.getPassword());
        registeredUser.setNickName(user.getNickName());
        registeredUser.setEmail(user.getEmail());
        registeredUser.setAvatarUrl(null);
        registeredUser.setFirstName(user.getFirstName());
        registeredUser.setLastName(user.getLastName());
        return ResponseEntity.ok(userService.signup(registeredUser));
    }

    //Get by id
    @GetMapping("/following/{nickName}")
    public List<User> getUsersFollowings(@PathVariable("nickName") String nickName) {
        return userRepository.findFollowersByNickName(nickName);
    }

    //Create new
    @PostMapping("/users")
    public User createUser(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }

    @PostMapping("/users/login")
    public ResponseEntity<User> login(@RequestParam("nickName") String nickName,
                               @RequestParam("password") String pass)
    {
        if ("".equals(nickName) || "".equals(pass))
        {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        User user = getUserByNickName(nickName);

        if (user != null && user.getPassword().equals(pass))
        {
            return ResponseEntity.status(HttpStatus.OK).body(user);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    //Update
    @PutMapping("/users/{nickName}")
    public User update(@PathVariable("nickName") String nickName, @Valid @RequestBody User userDetails){
        User user = getUserByNickName(nickName);
        if (user == null)
            return null;

        userDetails.setNickName(nickName);
        return userRepository.save(userDetails);
    }

}

