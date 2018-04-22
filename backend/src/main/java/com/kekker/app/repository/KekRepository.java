package com.kekker.app.repository;

import com.kekker.app.model.Kek;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface KekRepository extends JpaRepository<Kek, Long>{

    @Query("select k from Kek k where k.owner.nickName=?1")
    Collection<Kek> findKeksForOwner(String nickName);

  //  Collection<Kek> findUserKeks(String nickname);

}
