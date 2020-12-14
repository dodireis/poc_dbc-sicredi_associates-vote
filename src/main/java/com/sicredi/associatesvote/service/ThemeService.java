package com.sicredi.associatesvote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sicredi.associatesvote.domain.Theme;
import com.sicredi.associatesvote.domain.ThemeStatusEnum;
import com.sicredi.associatesvote.domain.Vote;
import com.sicredi.associatesvote.domain.VoteEnum;
import com.sicredi.associatesvote.domain.VoteSession;
import com.sicredi.associatesvote.dto.CreateThemeRequestDTO;
import com.sicredi.associatesvote.dto.ThemeResponseDTO;
import com.sicredi.associatesvote.mapper.ThemeMapper;
import com.sicredi.associatesvote.repository.ThemeRepository;
import com.sicredi.associatesvote.service.exception.ObjectNotFoundException;

@Service
public class ThemeService {

	@Autowired
	private ThemeRepository themeRepository;

	@Autowired
	private ThemeMapper themeMapper;

	public Long create(CreateThemeRequestDTO dto) {
		if (null == dto || null == dto.getDescription() || dto.getDescription().trim().isEmpty()) {
			throw new IllegalArgumentException("Theme must have a description.");
		}

		Theme entity = new Theme(dto.getDescription().trim());
		entity = themeRepository.save(entity);

		return entity.getId();
	}

	public ThemeResponseDTO closeTheme(Long themeId) {
		Theme entity = findById(themeId);
		countVotes(entity);
		entity.setStatus(ThemeStatusEnum.CLOSED);
		themeRepository.save(entity);
		return themeMapper.map(entity);
	}

	public ThemeResponseDTO getTheme(Long themeId) {
		Theme entity = findById(themeId);
		return themeMapper.map(entity);
	}

	protected Theme findById(Long id) {
		return themeRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
				Theme.class.getName().concat(" not found! Searched for id: ".concat(id.toString()))));
	}

	private void countVotes(Theme entity) {
		Long positeVotes = 0L;
		Long negativeVotes = 0L;

		for (VoteSession voteSession : entity.getVoteSessions()) {
			for (Vote vote : voteSession.getVotes()) {
				if (VoteEnum.YES.equals(vote.getVote())) {
					positeVotes = positeVotes + 1;
				} else {
					negativeVotes = negativeVotes + 1;
				}
			}
		}

		entity.setPositiveVotesAmount(positeVotes);
		entity.setNegativeVotesAmount(negativeVotes);

		if (positeVotes > negativeVotes) {
			entity.setResult("SIM");

		} else if (negativeVotes > positeVotes) {
			entity.setResult("NÃO");

		} else {
			entity.setResult("EMPATE");
		}
	}

	public Theme canCreateVoteSession(Long themeId) {
		Theme theme = findById(themeId);

		if (ThemeStatusEnum.CLOSED.equals(theme.getStatus())) {
			throw new IllegalArgumentException("Theme is already closed. No vote sessions can be created to this theme");
		}

		return theme;
	}

}
