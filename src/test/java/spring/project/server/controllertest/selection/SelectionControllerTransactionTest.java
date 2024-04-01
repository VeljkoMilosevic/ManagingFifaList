/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.project.server.controllertest.selection;

import org.assertj.core.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import spring.project.server.controllertest.AbstractTest;
import spring.project.server.model.Match;

import java.util.List;

/**
 * @author Veljko
 */
public class SelectionControllerTransactionTest extends AbstractTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void removeSelectionAndMatches() throws Exception {
        List<Match> matches = getMatchesOfSelection();
        Assertions.assertEquals(matches.size(), 2);
        deleteSelection();
        matches = getMatchesOfSelection();
        Assertions.assertEquals(matches.size(), 0);
    }

    private void deleteSelection() throws Exception {
        final String uri = "/api/selections/" + PathVariable.TRANSACTION_SELECTION;
        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                        .delete(uri)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        final int status = mvcResult.getResponse().getStatus();
    }

    private List<Match> getMatchesOfSelection() throws Exception {
        final String uri = "/api/selections/" + PathVariable.TRANSACTION_SELECTION + "/matches";
        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                        .get(uri)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        final String content = mvcResult.getResponse().getContentAsString();
        final List<Match> matches = (List) Arrays.asList(super.mapFromJson(content, Match[].class));
        return matches;
    }

}
