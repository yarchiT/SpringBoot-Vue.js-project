package com.kekker.app.repository;

import com.kekker.app.model.Kek;
import com.kekker.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

public interface UserRepository extends JpaRepository<User,Long>{
     User findByNickName(String nickName);
     User findByEmail(String nickName);

     @Transactional
     void deleteByNickName(String nickName);}

     /*List<User> findFollowersByNickName(String nickName);*/

     @Query("select u from User u join u.userFollowers f where f.nickName =:nickName")
     List<User> findFollowersByNickName(@Param("nickName")String nickName);
}
