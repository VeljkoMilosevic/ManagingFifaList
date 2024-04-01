/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.project.server.controllertest.match;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import spring.project.server.controllertest.AbstractTest;
import spring.project.server.model.Confederation;
import spring.project.server.model.Match;
import spring.project.server.model.MatchType;
import spring.project.server.model.Selection;
import spring.project.server.model.User;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Veljko
 */
public class MatchControllerCreateOperationTest extends AbstractTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void createMatch() throws Exception {
        final String uri = "/api/matches";

        final Match match = new Match();

        final Confederation confederation = new Confederation();
        confederation.setId(1);

        final Selection host = new Selection();
        host.setId(1);
        host.setConfederation(confederation);
        match.setHost(host);

        final Selection away = new Selection();
        away.setId(3);
        away.setConfederation(confederation);
        match.setAway(away);


        match.setHostGoals(0);
        match.setAwayGoals(4);

        final User user = new User();
        user.setId(1);
        match.setUser(user);

        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy.");
        final Date date = simpleDateFormat.parse("2.2.2020.");
        match.setDate(date);

        final MatchType matchType = new MatchType();
        matchType.setId(3);
        match.setMatchType(matchType);

        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                        .post(uri)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(super.mapToJson(match))
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        final int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(201, status);
    }

}
