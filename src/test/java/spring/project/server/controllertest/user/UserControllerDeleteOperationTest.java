/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.project.server.controllertest.user;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import spring.project.server.controllertest.AbstractTest;

/**
 * @author Veljko
 */
public class UserControllerDeleteOperationTest extends AbstractTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void deleteUser() throws Exception {
        final String uri = "/api/users/" + PathVariables.DELETE;
        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                        .delete(uri)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        final int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(204, status);
    }

    @Test
    public void deleteNonExistentUser() throws Exception {
        final String uri = "/api/users/" + PathVariables.INVALID_ID;
        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                        .delete(uri)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        final int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(404, status);
    }
}
