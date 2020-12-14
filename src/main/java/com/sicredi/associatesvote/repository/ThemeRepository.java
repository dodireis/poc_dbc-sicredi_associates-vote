package com.sicredi.associatesvote.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sicredi.associatesvote.domain.Theme;

@Repository
public interface ThemeRepository extends JpaRepository<Theme, Long> {

}
