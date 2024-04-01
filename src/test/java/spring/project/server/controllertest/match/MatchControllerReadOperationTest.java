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
import spring.project.server.model.Match;
import spring.project.server.model.MatchType;
import spring.project.server.model.Selection;
import spring.project.server.model.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Veljko
 */
public class MatchControllerReadOperationTest extends AbstractTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void getMatchByID() throws Exception {
        final String uri = "/api/matches/" + PathVariables.GET_MATCH;
        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                        .get(uri)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        final int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(200, status);
        final String content = mvcResult.getResponse().getContentAsString();
        final Match restMatch = super.mapFromJson(content, Match.class);
        final Match testMatch = getMatch();
        Assertions.assertEquals(restMatch, testMatch);
    }

    @Test
    public void getNonexistentUserById() throws Exception {
        final String uri = "/api/matches/" + PathVariables.INVALID_ID;
        final MvcResult mvcResult = mvc.perform(
                        MockMvcRequestBuilders
                                .get(uri)
                                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        final int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(404, status);
        Assertions.assertEquals("Match with id " + PathVariables.INVALID_ID + " does not exists.", mvcResult.getResolvedException().getMessage());
    }

    private Match getMatch() throws ParseException {
        final Match match = new Match();
        match.setId(1);

        final Selection host = new Selection();
        host.setId(1);
        match.setHost(host);

        final Selection away = new Selection();
        away.setId(3);
        match.setAway(away);

        match.setHostGoals(3);
        match.setAwayGoals(1);

        final User user = new User();
        user.setId(1);
        match.setUser(user);

        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy.");
        final Date date = simpleDateFormat.parse("1.1.2021.");
        match.setDate(date);

        final MatchType matchType = new MatchType();
        matchType.setId(3);
        match.setMatchType(matchType);

        return match;
    }
}
