package com.kekker.app.repository;

import com.kekker.app.model.Kek;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface KekRepository extends JpaRepository<Kek, Long>{

   //@Query("select k, c from Kek k left join k.comments c where k.owner.nickName=?1")
   //@Query("select new map(k.text as text, k.imageUrl as img, k.createDate as date, c as comments, r as reacts) from Kek k left join k.comments c left join k.reactions r where k.owner.nickName=?1")
    //@Query(value = "select k.text, c.owner.firstName, c.owner.nickName" +
           // "  from Kek k left join k.comments c inner join c.owner o where k.owner.nickName=?1")
    @Query("select new com.kekker.app.view.KekDto(k) from Kek k where k.owner.nickName=?1")
    Collection<?> findUserKeks(String nickname);

  //  Collection<Kek> findUserKeks(String nickname);

}
