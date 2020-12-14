package com.sicredi.associatesvote.config.environment;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sicredi.associatesvote.domain.Theme;
import com.sicredi.associatesvote.domain.Vote;
import com.sicredi.associatesvote.domain.VoteEnum;
import com.sicredi.associatesvote.domain.VoteSession;
import com.sicredi.associatesvote.repository.ThemeRepository;
import com.sicredi.associatesvote.repository.VoteRepository;
import com.sicredi.associatesvote.repository.VoteSessionRepository;

@Component
public class TestDBService {

	@Autowired
	private ThemeRepository themeRepository;

	@Autowired
	private VoteRepository voteRepository;

	@Autowired
	private VoteSessionRepository voteSessionRepository;

	public void instantiateTestDatabase() throws ParseException {
		Theme theme1 = new Theme("TEST 1");
		Theme theme2 = new Theme("TEST 2");

		themeRepository.saveAll(Arrays.asList(theme1, theme2));

		VoteSession voteSession1 = new VoteSession(theme1, LocalDateTime.now(), LocalDateTime.now().plusDays(2));
		VoteSession voteSession2 = new VoteSession(theme2, LocalDateTime.now(), LocalDateTime.now().plusMinutes(5));
		VoteSession voteSession3 = new VoteSession(theme2, LocalDateTime.now().plusHours(1),
				LocalDateTime.now().plusHours(5));

		voteSessionRepository.saveAll(Arrays.asList(voteSession1, voteSession2, voteSession3));

		Vote vote1 = new Vote(voteSession1, "54144676099", VoteEnum.YES);
		Vote vote2 = new Vote(voteSession1, "79063436033", VoteEnum.YES);
		Vote vote3 = new Vote(voteSession1, "33843211035", VoteEnum.YES);

		Vote vote4 = new Vote(voteSession2, "27646541036", VoteEnum.NO);
		Vote vote5 = new Vote(voteSession2, "60229139000", VoteEnum.NO);
		Vote vote6 = new Vote(voteSession3, "21697615007", VoteEnum.NO);
		Vote vote7 = new Vote(voteSession3, "33843211035", VoteEnum.YES);

		voteRepository.saveAll(Arrays.asList(vote1, vote2, vote3, vote4, vote5, vote6, vote7));
	}
}
