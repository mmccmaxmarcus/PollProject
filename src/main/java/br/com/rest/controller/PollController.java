package br.com.rest.controller;

import java.util.List;

import org.aspectj.weaver.patterns.TypePatternQuestions.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.rest.dto.PollDTO;
import br.com.rest.dto.StatsDTO;
import br.com.rest.dto.VoteDTO;
import br.com.rest.model.Option;
import br.com.rest.model.Poll;
import br.com.rest.model.Stats;
import br.com.rest.model.Vote;
import br.com.rest.repository.OptionRepository;
import br.com.rest.repository.PollRepository;
import br.com.rest.repository.StatsRepository;
import br.com.rest.repository.VoteRepository;

@RestController
@RequestMapping(value = "/poll")
public class PollController {

	@Autowired
	private PollRepository pollRepository;

	@Autowired
	private OptionRepository optionRepository;

	@Autowired
	private VoteRepository voteRepository;

	@Autowired
	private StatsRepository statsRepository;

	@GetMapping(value = "", produces = "application/json")
	public ResponseEntity<List<Poll>> init() {
		return new ResponseEntity<List<Poll>>((List<Poll>) pollRepository.findAll(), HttpStatus.OK);
	}

	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Poll> findPollById(@PathVariable(value = "id") Long poll_id) {
		
		if (!pollRepository.existsById(poll_id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID não identificado");
		}

		statsRepository.updateStatistc(poll_id);

		return new ResponseEntity<Poll>(pollRepository.findById(poll_id).get(), HttpStatus.OK);
	}

	@PostMapping(value = "", produces = "application/json")
	public ResponseEntity<PollDTO> savePoll(@RequestBody Poll poll) {
		Stats stats = new Stats();
		for (int i = 0; i < poll.getOptions().size(); i++) {
			poll.getOptions().get(i).setPoll(poll);

		}

		Poll returnedPoll = pollRepository.save(poll);

		returnedPoll.getOptions().forEach(option -> {
			Vote vote = new Vote();
			vote.setQty(0);
			vote.setOption(option);
			voteRepository.save(vote);
		});

		if (statsRepository.findPoll(returnedPoll.getPoll_id()) == null) {
			stats.setPoll(returnedPoll);
			stats.setViews(0);
			statsRepository.save(stats);
		}

		return new ResponseEntity<PollDTO>(new PollDTO(returnedPoll), HttpStatus.OK);
	}

	@PostMapping(value = "/{id}/vote")
	public ResponseEntity<String> voteQuestion(@PathVariable(value = "id") Long poll_id,
			@RequestBody Option optionBody) {
		
		Option optionReturned = optionRepository.findPollOption(poll_id, optionBody.getOption_id());
		
		if (optionReturned == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID não identificado");
		}

		Vote voteHasOptionId = voteRepository.findOption(optionReturned.getOption_id());

		voteRepository.updateVote(voteHasOptionId.getOption().getOption_id());

		return new ResponseEntity<String>("Voto realizado com sucesso", HttpStatus.OK);

	}

	@GetMapping(value = "/{id}/stats")
	public ResponseEntity<StatsDTO> pollStatistic(@PathVariable(value = "id") Long poll_id) {
		Stats stats = statsRepository.findPoll(poll_id);
		if (stats == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID não identificado");
		}

		List<Vote> votes = voteRepository.findVoteByStatist(poll_id);
		StatsDTO statsDTO = new StatsDTO(stats);
		statsDTO.setVotes(votes);

		return new ResponseEntity<StatsDTO>(statsDTO, HttpStatus.OK);
	}

}
