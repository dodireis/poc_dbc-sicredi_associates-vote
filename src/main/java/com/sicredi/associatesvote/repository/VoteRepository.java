package com.sicredi.associatesvote.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sicredi.associatesvote.domain.Vote;
import com.sicredi.associatesvote.domain.VoteSession;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

	Optional<Vote> findByVoteSessionAndAssociateDocument(VoteSession voteSession, String associateDocument);

}
