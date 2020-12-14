package com.sicredi.associatesvote.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sicredi.associatesvote.domain.VoteSession;

@Repository
public interface VoteSessionRepository extends JpaRepository<VoteSession, Long> {

}
