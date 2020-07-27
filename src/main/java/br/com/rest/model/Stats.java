package br.com.rest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "stats")
public class Stats {

	@Id
	@Column(name = "stats_id", columnDefinition = "serial")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long stat_id;

	@Column(nullable = false, columnDefinition = "int default 0")
	private Integer views;

	@JsonIgnore
	@ManyToOne(optional = false)
	@JoinColumn(name = "poll_id_fk")
	private Poll poll;

	public Long getStat_id() {
		return stat_id;
	}

	public void setStat_id(Long stat_id) {
		this.stat_id = stat_id;
	}

	public Integer getViews() {
		return views;
	}

	public void setViews(Integer views) {
		this.views = views;
	}

	public Poll getPoll() {
		return poll;
	}

	public void setPoll(Poll poll) {
		this.poll = poll;
	}

}
