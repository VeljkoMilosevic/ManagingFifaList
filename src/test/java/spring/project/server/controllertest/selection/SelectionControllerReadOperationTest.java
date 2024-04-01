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
import spring.project.server.model.Selection;

import static spring.project.server.controllertest.selection.PathVariable.INVALID_ID;
import static spring.project.server.controllertest.selection.PathVariable.SELECTION_SERBIA;

/**
 * @author Veljko
 */
public class SelectionControllerReadOperationTest extends AbstractTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void getSelectionById() throws Exception {
        final String uri = "/api/selections/" + SELECTION_SERBIA;
        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                        .get(uri)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        final int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(200, status);
        final String content = mvcResult.getResponse().getContentAsString();
        final Selection restSelection = super.mapFromJson(content, Selection.class);
        final Selection testSelection = new Selection();
        testSelection.setId(1);
        testSelection.setName("Serbia");
        Assertions.assertEquals(testSelection, restSelection);
    }

    @Test
    public void getNonexistentSelectionById() throws Exception {
        final String uri = "/api/selections/" + INVALID_ID;
        final MvcResult mvcResult = mvc.perform(
                        MockMvcRequestBuilders
                                .get(uri)
                                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        final int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(404, status);
        Assertions.assertEquals("Selection with id " + INVALID_ID + " does not exist.", mvcResult.getResolvedException().getMessage());
    }
}