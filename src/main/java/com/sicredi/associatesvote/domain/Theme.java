package com.sicredi.associatesvote.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "THEME")
public class Theme implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String description;
	private String result;
	private Long positiveVotesAmount;
	private Long negativeVotesAmount;
	private Integer status;

	@JsonIgnore
	@OneToMany(mappedBy = "theme")
	private List<VoteSession> voteSessions = new ArrayList<VoteSession>();

	public Theme() {
	}

	public Theme(String description) {
		this.description = description;
		this.status = ThemeStatusEnum.OPENED.getId();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Long getPositiveVotesAmount() {
		return positiveVotesAmount;
	}

	public void setPositiveVotesAmount(Long positiveVotesAmount) {
		this.positiveVotesAmount = positiveVotesAmount;
	}

	public Long getNegativeVotesAmount() {
		return negativeVotesAmount;
	}

	public void setNegativeVotesAmount(Long negativeVotesAmount) {
		this.negativeVotesAmount = negativeVotesAmount;
	}

	public ThemeStatusEnum getStatus() {
		return ThemeStatusEnum.toEnum(status);
	}

	public void setStatus(ThemeStatusEnum status) {
		this.status = status.getId();
	}

	public List<VoteSession> getVoteSessions() {
		return voteSessions;
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
		Theme other = (Theme) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
