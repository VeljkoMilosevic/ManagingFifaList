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
import spring.project.server.exceptions.BusyUsernameException;
import spring.project.server.model.User;

import static spring.project.server.controllertest.user.PathVariables.INVALID_ID;
import static spring.project.server.controllertest.user.PathVariables.USER_TEST;

/**
 * @author Veljko
 */
public class UserControllerReadOperationTest extends AbstractTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void getUserById() throws Exception {
        final String uri = "/api/users/" + USER_TEST;
        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                        .get(uri)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        final int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(200, status);
        final String content = mvcResult.getResponse().getContentAsString();
        final User restUser = super.mapFromJson(content, User.class);
        final User testUser = new User();
        testUser.setUsername("user_test");
        Assertions.assertEquals(testUser, restUser);
    }

    @Test
    public void getNonexistentUserById() throws Exception {
        final String uri = "/api/users/" + INVALID_ID;
        final MvcResult mvcResult = mvc.perform(
                        MockMvcRequestBuilders
                                .get(uri)
                                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        final int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(404, status);
        Assertions.assertEquals("User with id " + INVALID_ID + " does not exists.", mvcResult.getResolvedException().getMessage());
    }

    @Test
    public void getUserByUsername() throws Exception {
        final String uri = "/api/users/username";
        final User testUser = new User();
        testUser.setUsername("user_test");
        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                        .post(uri)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(super.mapToJson(testUser))
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        final int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(406, status);
        Assertions.assertTrue(mvcResult.getResolvedException() instanceof BusyUsernameException);
    }

    @Test
    public void getNonexistentUserByUsername() throws Exception {
        final String uri = "/api/users/username";
        final User testUser = new User();
        testUser.setUsername("test_user");
        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                        .post(uri)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(super.mapToJson(testUser))
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        final int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(200, status);
    }
}
