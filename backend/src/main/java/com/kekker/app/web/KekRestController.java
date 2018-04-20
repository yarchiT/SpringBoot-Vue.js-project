package com.kekker.app.web;

import com.kekker.app.model.Kek;
import com.kekker.app.repository.KekRepository;
import com.kekker.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api")
public class KekRestController {
    private final KekRepository kekRepository;

    private EntityManager em;
    @Autowired
    KekRestController(KekRepository kekRepository)  {
        this.kekRepository = kekRepository;
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
    public Collection<Kek> getUserKeks(@PathVariable("nickname") String nickname) {
        return kekRepository.findKeksForOwner(nickname);
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
}
