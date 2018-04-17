package com.kekker.app.repository;

import com.kekker.app.model.Kek;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KekRepository extends JpaRepository<Kek, Long>{
}
