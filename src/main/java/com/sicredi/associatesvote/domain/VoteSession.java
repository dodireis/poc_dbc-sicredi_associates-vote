package com.sicredi.associatesvote.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "VOTE_SESSION")
public class VoteSession implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "THEME_ID", foreignKey = @ForeignKey(name = "FK_VOTESESSION_THEME"))
	private Theme theme;
	private LocalDateTime votingOpenTime;
	private LocalDateTime votingCloseTime;

	@JsonIgnore
	@OneToMany(mappedBy = "voteSession")
	private List<Vote> votes = new ArrayList<Vote>();

	public VoteSession() {
	}

	public VoteSession(Theme theme, LocalDateTime votingOpenTime, LocalDateTime votingCloseTime) {
		this.theme = theme;
		this.votingOpenTime = votingOpenTime;
		this.votingCloseTime = votingCloseTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Theme getTheme() {
		return theme;
	}

	public void setTheme(Theme theme) {
		this.theme = theme;
	}

	public LocalDateTime getVotingOpenTime() {
		return votingOpenTime;
	}

	public void setVotingOpenTime(LocalDateTime votingOpenTime) {
		this.votingOpenTime = votingOpenTime;
	}

	public LocalDateTime getVotingCloseTime() {
		return votingCloseTime;
	}

	public void setVotingCloseTime(LocalDateTime votingCloseTime) {
		this.votingCloseTime = votingCloseTime;
	}

	public List<Vote> getVotes() {
		return votes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VoteSession other = (VoteSession) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
