package br.com.rest.repository;



import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.rest.model.Vote;

@Repository
@Transactional
public interface VoteRepository extends CrudRepository<Vote, Long>{
	
	@Modifying
	@Query(value = "update vote set qty = qty + 1 where option_id_fk = :id_option", nativeQuery = true)
	public void updateVote(@Param("id_option") Long id_option);
	
	@Query(value = "select * from vote where option_id_fk = :id_option", nativeQuery = true)
	public Vote findOption(@Param("id_option") Long id_option);
	
	
	@Query(value = "SELECT op.option_id, vo.qty, vo.vote_id, vo.option_id_fk\n" + 
			"	FROM public.vote as vo join public.option as op\n" + 
			"	on vo.option_id_fk = op.option_id where op.poll_id = :id_poll", nativeQuery = true)
	public List<Vote> findVoteByStatist(@Param("id_poll") Long id_poll);
	
}
