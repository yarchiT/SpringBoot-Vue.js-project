package com.kekker.app.repository;

import com.kekker.app.model.Kek;
import com.kekker.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface UserRepository extends JpaRepository<User,Long>{
     User findByNickName(String nickName);
}
