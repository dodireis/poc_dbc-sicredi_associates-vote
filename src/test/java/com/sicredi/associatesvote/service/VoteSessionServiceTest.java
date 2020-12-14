package com.sicredi.associatesvote.service;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.sicredi.associatesvote.domain.Theme;
import com.sicredi.associatesvote.domain.Vote;
import com.sicredi.associatesvote.domain.VoteSession;
import com.sicredi.associatesvote.dto.CreateVoteSessionRequestDTO;
import com.sicredi.associatesvote.mapper.VoteMapper;
import com.sicredi.associatesvote.mapper.VoteSessionMapper;
import com.sicredi.associatesvote.repository.VoteRepository;
import com.sicredi.associatesvote.repository.VoteSessionRepository;
import com.sicredi.associatesvote.util.AssociateInfo;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { VoteSessionService.class })
public class VoteSessionServiceTest {

	@Autowired
	private VoteSessionService voteSessionService;

	@MockBean
	private ThemeService themeService;

	@MockBean
	private VoteSessionMapper voteSessionMapper;
	
	@MockBean
	private VoteMapper voteMapper;

	@MockBean
	private VoteSessionRepository voteSessionRepository;

	@MockBean
	private AssociateInfo associateInfo;
	
	@MockBean
	private VoteRepository voteRepository;
	
	private VoteSession voteSessionMock;
	private Vote voteMock;
	private Theme themeMock;

	@BeforeEach
	void init() {
		themeMock = new Theme();
		voteMock = new Vote();

		when(voteSessionMapper.map(any(CreateVoteSessionRequestDTO.class), any(Theme.class))).thenCallRealMethod();
		when(themeService.findById(1L)).thenReturn(themeMock);
		when(voteRepository.save(any())).thenReturn(voteMock);
	}

	@Test
	public void givenNewVoteSession_whenCreateWithValidDto_thenSaveVoteSession() {
		Long expectedVoteSessionId = 1L;
		voteSessionMock = new VoteSession();
		voteSessionMock.setId(1L);

		CreateVoteSessionRequestDTO requestDTO = new CreateVoteSessionRequestDTO();
		requestDTO.setThemeId(1L);
		requestDTO.setVotingOpenTime(LocalDateTime.now());
		requestDTO.setVotingCloseTime(LocalDateTime.now().plusHours(1));

		when(voteSessionRepository.save(any())).thenReturn(voteSessionMock);

		Long actualVoteSessionId = voteSessionService.create(requestDTO);

		assertEquals(expectedVoteSessionId, actualVoteSessionId);
	}

	@Test
	public void givenNewVoteSession_whenCreateWithAEmptyCloseTime_thenSaveVoteSessionWithOneMinute() {
		Long expectedVoteSessionId = 1L;
		voteSessionMock = new VoteSession();
		voteSessionMock.setId(1L);

		CreateVoteSessionRequestDTO requestDTO = new CreateVoteSessionRequestDTO();
		requestDTO.setThemeId(1L);
		requestDTO.setVotingOpenTime(LocalDateTime.now());

		when(voteSessionRepository.save(any())).thenReturn(voteSessionMock);

		Long actualVoteSessionId = voteSessionService.create(requestDTO);

		assertEquals(expectedVoteSessionId, actualVoteSessionId);
	}

	@Test
	public void givenNewVoteSession_whenCreateWithInvalidOpenTime_thenThrowAnException() {
		String expectedErrorMessage = "Open session vote time must not be null.";

		CreateVoteSessionRequestDTO requestDTO = new CreateVoteSessionRequestDTO();
		requestDTO.setThemeId(1L);

		assertThatExceptionOfType(IllegalArgumentException.class)
				.isThrownBy(() -> voteSessionService.create(requestDTO)).withMessage(expectedErrorMessage);
	}

	@Test
	public void givenNewVoteSession_whenCreateWithACloseTimeBeforeTheOpenTime_thenThrowAnException() {
		String expectedErrorMessage = "Close session vote time must be after open time.";

		CreateVoteSessionRequestDTO requestDTO = new CreateVoteSessionRequestDTO();
		requestDTO.setThemeId(1L);
		requestDTO.setVotingOpenTime(LocalDateTime.now());
		requestDTO.setVotingCloseTime(LocalDateTime.now().minusHours(1));

		assertThatExceptionOfType(IllegalArgumentException.class)
				.isThrownBy(() -> voteSessionService.create(requestDTO)).withMessage(expectedErrorMessage);
	}
}
