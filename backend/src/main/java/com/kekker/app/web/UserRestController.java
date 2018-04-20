package com.kekker.app.web;

import com.kekker.app.model.User;
import com.kekker.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api")
class UserRestController {

    private final UserRepository userRepository;

    @Autowired
    UserRestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //Get all
    @GetMapping("/users")
    public Collection<User> findAll() {
        return userRepository.findAll();
    }

    //Get by id
    @GetMapping("/users/{nickName}")
    public User getUserByNickName(@PathVariable("nickName") String nickName) {
        return userRepository.findByNickName(nickName);
    }

    //Create new
    @PostMapping("/users")
    public User createUser(@Valid @RequestBody User user) {
        return userRepository.save(user);
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

