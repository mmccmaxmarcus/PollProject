package br.com.rest.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.rest.model.Option;
import br.com.rest.model.Stats;
import br.com.rest.model.Vote;

public class StatsDTO {
	private Integer views;
	private VoteDTO voteDTO;

	private List<VoteDTO> votes;

	public StatsDTO(Stats stats) {
		this.views = stats.getViews();
	}

	public void setVotes(List<Vote> voteReturner) {
		votes = new ArrayList<>();

		voteReturner.forEach(voteReturned -> {
			voteDTO = new VoteDTO(voteReturned, voteReturned.getOption());
			this.votes.add(voteDTO);
		});

	}

	public List<VoteDTO> getVotes() {
		return votes;
	}

	public Integer getViews() {
		return views;
	}

	public void setViews(Integer views) {
		this.views = views;
	}

}
