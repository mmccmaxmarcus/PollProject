package br.com.rest.dto;

import br.com.rest.model.Option;
import br.com.rest.model.Vote;

public class VoteDTO {

	private Long option_id;

	private Integer qty;

	public VoteDTO(Long option_id) {
		this.option_id = option_id;
	}
	
	public VoteDTO(Vote vote, Option option) {
		this.option_id = option.getOption_id();
		this.qty = vote.getQty();
	}

	public Long getOption_id() {
		return option_id;
	}

	public void setOption_id(Long option_id) {
		this.option_id = option_id;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}
}
