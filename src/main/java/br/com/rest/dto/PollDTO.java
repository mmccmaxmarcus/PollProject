package br.com.rest.dto;

import br.com.rest.model.Poll;

public class PollDTO {
	private Long poll_id;
	
	public PollDTO(Poll poll_id) {
		this.poll_id = poll_id.getPoll_id();
	}

	public Long getPoll_id() {
		return poll_id;
	}

	public void setPoll_id(Long poll_id) {
		this.poll_id = poll_id;
	}

	

}
