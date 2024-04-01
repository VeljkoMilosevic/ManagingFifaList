/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.project.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import spring.project.server.model.Match;

import java.util.List;

/**
 * @author Veljko
 */
@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface MatchRepository extends JpaRepository<Match, Integer> {

    @Query("SELECT u FROM Match u WHERE u.host.id = ?1")
    List<Match> findAllMatchesHost(int id);

    @Query("SELECT u FROM Match u WHERE u.away.id = ?1")
    List<Match> findAllMatchesAway(int id);

}
