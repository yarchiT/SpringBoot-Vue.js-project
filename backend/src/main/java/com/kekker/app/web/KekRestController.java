package com.kekker.app.web;

import com.kekker.app.model.Kek;
import com.kekker.app.repository.KekRepository;
import com.kekker.app.view.MyKekDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class KekRestController {
    private final KekRepository kekRepository;

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
    public Collection<?> getUserKeks(@PathVariable("nickname") String nickname) {
        Collection<?> temp = kekRepository.findUserKeks(nickname);
        return temp;
        //return kekRepository.findUserKeks(nickname);
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
