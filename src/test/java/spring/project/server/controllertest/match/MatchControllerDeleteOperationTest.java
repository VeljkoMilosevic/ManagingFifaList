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

import static spring.project.server.controllertest.user.PathVariables.DELETE;
import static spring.project.server.controllertest.user.PathVariables.INVALID_ID;

/**
 * @author Veljko
 */
public class MatchControllerDeleteOperationTest extends AbstractTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void deleteMatch() throws Exception {
        final String uri = "/api/matches/" + DELETE;
        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                        .delete(uri)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        final int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(204, status);
    }

    @Test
    public void deleteNonExistentMatch() throws Exception {
        final String uri = "/api/matches/" + INVALID_ID;
        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                        .delete(uri)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        final int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(404, status);
    }
}

