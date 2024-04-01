/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.project.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import spring.project.server.model.MatchType;

/**
 * @author Veljko
 */
@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface MatchTypeRepository extends JpaRepository<MatchType, Integer> {

}
