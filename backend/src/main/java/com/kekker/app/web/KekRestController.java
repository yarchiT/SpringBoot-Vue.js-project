package com.kekker.app.web;

import com.kekker.app.model.Comment;
import com.kekker.app.model.Kek;
import com.kekker.app.repository.CommentRepository;
import com.kekker.app.repository.KekRepository;
import com.kekker.app.repository.UserRepository;
import com.kekker.app.utils.Utils;

import com.kekker.app.view.CommentDto;
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
    public Kek createKek(@RequestParam("text") String text) {
        Kek kek = new Kek();
        kek.setText(text);
        kek.setOwner(userRepository.findByNickName(Utils.getCurrentUserNickName()));
        kek.setCreateDate(new Date());
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

    @DeleteMapping("keks/{id}")
    public void deleteKek(@PathVariable long id)
    {
        kekRepository.delete(id);
    }

    //Create new
    @PostMapping("/kek/comment")
    public CommentDto addComment(@RequestParam long kekId, @RequestParam String text, @RequestParam String ownerNickName) {
        Comment comment = new Comment(text);
        comment.setKek(kekRepository.findOne(kekId));
        comment.setOwner(userRepository.findByNickName(ownerNickName));
        comment.setCreationDate(new Date());
        Comment createdComment = commentRepository.save(comment);
        return new CommentDto(createdComment);
    }
}
