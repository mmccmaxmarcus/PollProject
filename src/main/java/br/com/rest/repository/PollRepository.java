package br.com.rest.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.rest.model.Poll;

public interface PollRepository extends CrudRepository<Poll, Long>{

}
