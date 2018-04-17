package com.kekker.app.repository;

import com.kekker.app.model.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReactionRepository extends JpaRepository<Reaction, Long>{
}
