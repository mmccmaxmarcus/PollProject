package br.com.rest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "vote")
public class Vote {

	@Id
	@Column(name = "vote_id", columnDefinition = "serial")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long vote_id;

	@Column(columnDefinition = "int default 0")
	private Integer qty;

	@JsonIgnore
	@OneToOne(optional = false)
	@JoinColumn(name = "option_id_fk")
	private Option option;

	public Long getVote_id() {
		return vote_id;
	}

	public void setVote_id(Long vote_id) {
		this.vote_id = vote_id;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public Option getOption() {
		return option;
	}

	public void setOption(Option option) {
		this.option = option;
	}

}
