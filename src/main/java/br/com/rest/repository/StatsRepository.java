package br.com.rest.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.rest.model.Stats;

@Repository
@Transactional
public interface StatsRepository extends CrudRepository<Stats, Long> {
	@Modifying
	@Query(value = "update stats set views = views + 1 where poll_id_fk = :id_poll", nativeQuery = true)
	public void updateStatistc(@Param("id_poll") Long id_poll);
	
	@Query(value = "select * from stats where poll_id_fk = :id_poll", nativeQuery = true)
	public Stats findPoll(@Param("id_poll") Long id_poll);
	
}
