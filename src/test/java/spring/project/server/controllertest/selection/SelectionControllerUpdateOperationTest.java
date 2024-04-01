/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.project.server.controllertest.selection;


import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import spring.project.server.controllertest.AbstractTest;
import spring.project.server.helpers.SimpleIDGenerator;
import spring.project.server.helpers.SimpleStringGenerator;
import spring.project.server.model.Confederation;
import spring.project.server.model.Selection;
import spring.project.server.model.User;

import static spring.project.server.controllertest.selection.PathVariable.INVALID_ID;
import static spring.project.server.controllertest.selection.PathVariable.UPDATE;


/**
 * @author Veljko
 */
public class SelectionControllerUpdateOperationTest extends AbstractTest {


    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void updateSelection() throws Exception {
        final String uri = "/api/selections/" + UPDATE;
        final Selection testSelection = new Selection();
        testSelection.setId(UPDATE);
        testSelection.setName(SimpleStringGenerator.generate(10));
        final Confederation confederation = new Confederation();
        confederation.setId(SimpleIDGenerator.generate(5));
        testSelection.setConfederation(confederation);
        final User user = new User();
        user.setId(1);
        testSelection.setUser(user);
        String testSelectionString = super.mapToJson(testSelection);
        testSelectionString = testSelectionString.substring(0, testSelectionString.length() - 1);
        testSelectionString = testSelectionString + ",\"hostMatches\":[],\"awayMatches\":[]}";
        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                        .put(uri)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(testSelectionString)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        final int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(204, status);
    }

    @Test
    public void updateSelectionWithNonexistentID() throws Exception {
        final String uri = "/api/selections/" + INVALID_ID;
        final Selection testSelection = new Selection();
        testSelection.setId(INVALID_ID);
        testSelection.setName(SimpleStringGenerator.generate(10));
        final Confederation confederation = new Confederation();
        confederation.setId(SimpleIDGenerator.generate(5));
        testSelection.setConfederation(confederation);
        final User user = new User();
        user.setId(4);
        testSelection.setUser(user);
        String testSelectionString = super.mapToJson(testSelection);
        testSelectionString = testSelectionString.substring(0, testSelectionString.length() - 1);
        testSelectionString = testSelectionString + ",\"hostMatches\":[],\"awayMatches\":[]}";
        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                        .put(uri)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(testSelectionString)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        final int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(404, status);
    }

    @Test
    public void updateSelectionWithNonUniqueName() throws Exception {
        final String uri = "/api/selections/" + UPDATE;
        final Selection testSelection = new Selection();
        testSelection.setId(UPDATE);
        testSelection.setName("Serbia");
        final Confederation confederation = new Confederation();
        confederation.setId(SimpleIDGenerator.generate(5));
        testSelection.setConfederation(confederation);
        final User user = new User();
        user.setId(4);
        testSelection.setUser(user);
        String testSelectionString = super.mapToJson(testSelection);
        testSelectionString = testSelectionString.substring(0, testSelectionString.length() - 1);
        testSelectionString = testSelectionString + ",\"hostMatches\":[],\"awayMatches\":[]}";
        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                        .put(uri)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(testSelectionString)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        final int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(404, status);
    }
}

    

