package br.com.rest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.rest.model.Option;

@Repository
public interface OptionRepository extends CrudRepository<Option, Long> {
	@Query(value = "from option where poll_id = :paramPoll and option_id = :paramQuestion")
	public Option findPollOption(@Param("paramPoll") Long id_poll, @Param("paramQuestion") Long id_question);
	
	@Query(value = "from option where option_id = :paramPoll")
	public List<Option> findOptionsByPolls(@Param("paramPoll") Long id_poll);
	
	
	

}
