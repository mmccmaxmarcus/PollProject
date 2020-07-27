package br.com.rest.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "option")
public class Option {

	@Id
	@Column(name = "option_id", columnDefinition = "serial")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long option_id;

	@Column(nullable = false, length = 255)
	private String option_description;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(nullable = false, name = "poll_id")
	private Poll poll;

	public Long getOption_id() {
		return option_id;
	}

	public void setOption_id(Long option_id) {
		this.option_id = option_id;
	}

	public String getOption_description() {
		return option_description;
	}

	public void setOption_description(String option_description) {
		this.option_description = option_description;
	}

	public Poll getPoll() {
		return poll;
	}

	public void setPoll(Poll poll) {
		this.poll = poll;
	}

}
