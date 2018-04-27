package com.kekker.app.repository;

import com.kekker.app.model.Kek;
import com.kekker.app.model.User;
import com.kekker.app.view.UserView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

public interface UserRepository extends JpaRepository<User,Long>{
     User findByNickName(String nickName);
     User findByEmail(String nickName);

     @Transactional
     void deleteByNickName(String nickName);

     /*List<User> findFollowersByNickName(String nickName);*/

     @Query("select new com.kekker.app.view.UserView(u) from User u join u.userFollowers f where f.nickName =:nickName")
     Collection<?> findFollowersByNickName(@Param("nickName")String nickName);

     @Query("select new com.kekker.app.view.SearchUserDto(u) from User u where u.nickName like %?1%")
     Collection<?> findUsersNicknameContains(String wildCard);

     @Query("select new com.kekker.app.view.UserView(u) from User u join u.userFollowers f where f.nickName =:myNickName and u.nickName =:hisNickName")
     UserView findIfYouFollowUser(@Param("myNickName")String myNickName, @Param("hisNickName")String hisNickName);

     @Modifying
     @Query(value ="insert into user_followers (following_nick_name, user_nick_name) VALUES (:hisNickName,:myNickName)", nativeQuery = true)
     @Transactional
     void followGuy(@Param("myNickName")String myNickName, @Param("hisNickName")String hisNickName);

     @Modifying
     @Query(value ="DELETE FROM user_followers WHERE following_nick_name=:hisNickName and user_nick_name=:myNickName", nativeQuery = true)
     @Transactional
     void unfollowGuy(@Param("myNickName")String myNickName, @Param("hisNickName")String hisNickName);
}
