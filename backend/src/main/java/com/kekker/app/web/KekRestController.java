package com.kekker.app.web;

import com.kekker.app.model.Comment;
import com.kekker.app.model.Kek;
import com.kekker.app.repository.CommentRepository;
import com.kekker.app.repository.KekRepository;
import com.kekker.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class KekRestController {
    private final KekRepository kekRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    @Autowired
    KekRestController(KekRepository kekRepository, UserRepository userRepository, CommentRepository commentRepository)  {
        this.kekRepository = kekRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }

    //Get all
    @GetMapping("/keks")
    public List<Kek> findAll() {
        return kekRepository.findAll();
    }

    //Get by id
    @GetMapping("/keks/{id}")
    public Kek getKekById(@PathVariable("id") long kekId) {
        return kekRepository.findOne(kekId);
    }

    //Get all keks for user
    @GetMapping("/users/{nickname}/keks")
    public Collection<?> getUserKeks(@PathVariable("nickname") String nickname) {
        Collection<?> temp = kekRepository.findUserKeks(nickname);
        return temp;
    }

    //Get by id
    @GetMapping("/users/{nickName}/timeline")
    public Collection<?> getUserTimelineKeks(@PathVariable("nickName") String nickName) {
        return kekRepository.findUserTimelineKeks(nickName);
    }

    //Create new
    @PostMapping("/keks")
    public Kek createKek(@Valid @RequestBody Kek kek) {
        return kekRepository.save(kek);
    }

    //Insert new
    @PutMapping("/keks/{id}")
    public Kek updateKek(@PathVariable long id, @Valid @RequestBody Kek kekDetails){
        Kek kek = getKekById(id);

        if (kek== null)
            return null;

        kekDetails.setId(id);
        return kekRepository.save(kekDetails);
    }

    //Create new
    @PostMapping("/kek/comment")
    public Comment addComment(@RequestParam long kekId,@RequestParam String text) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Comment comment = new Comment(text);
        comment.setKek(kekRepository.findOne(kekId));
        comment.setOwner(userRepository.findByNickName(authentication.getName()));
        comment.setCreationDate(new Date());
        return commentRepository.save(comment);
    }
}
