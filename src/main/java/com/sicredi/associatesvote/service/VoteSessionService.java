package com.sicredi.associatesvote.service;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sicredi.associatesvote.domain.Theme;
import com.sicredi.associatesvote.domain.VoteSession;
import com.sicredi.associatesvote.dto.CreateVoteSessionRequestDTO;
import com.sicredi.associatesvote.mapper.VoteSessionMapper;
import com.sicredi.associatesvote.repository.VoteSessionRepository;
import com.sicredi.associatesvote.service.exception.ObjectNotFoundException;

@Service
public class VoteSessionService {

	@Autowired
	private VoteSessionRepository voteSessionRepository;

	@Autowired
	private ThemeService themeService;

	@Autowired
	private VoteSessionMapper voteSessionMapper;

	public Long create(CreateVoteSessionRequestDTO dto) {
		Theme theme = themeService.canCreateVoteSession(dto.getThemeId());
		VoteSession entity = voteSessionMapper.map(dto, theme);
		validateVotingTime(entity);
		entity = voteSessionRepository.save(entity);
		return entity.getId();
	}

	public VoteSession findById(Long id) {
		return voteSessionRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
				VoteSession.class.getName().concat(" not found! Searched for id: ".concat(id.toString()))));
	}

	private void validateVotingTime(VoteSession voteSession) {
		if (null == voteSession.getVotingOpenTime()) {
			throw new IllegalArgumentException("Open session vote time must not be null.");
		}

		if (null == voteSession.getVotingCloseTime()) {
			voteSession.setVotingCloseTime(voteSession.getVotingOpenTime().plusMinutes(1));
		}

		if (!voteSession.getVotingCloseTime().isAfter(voteSession.getVotingOpenTime())) {
			throw new IllegalArgumentException("Close session vote time must be after open time.");
		}
	}

	protected void validateVoteSessionTime(VoteSession voteSession) {
		LocalDateTime now = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));

		if (null == voteSession) {
			throw new IllegalArgumentException("The voting session must be informed.");			
		}
		
		if (now.isBefore(voteSession.getVotingOpenTime())) {
			throw new IllegalArgumentException("The voting session has not yet opened.");						
		}
		
		if (now.isAfter(voteSession.getVotingCloseTime())) {
			throw new IllegalArgumentException("Session already closed.");
		}
	}

}
