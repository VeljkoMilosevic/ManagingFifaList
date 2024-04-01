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
import spring.project.server.helpers.SimpleStringGenerator;
import spring.project.server.model.User;

/**
 * @author Veljko
 */
public class UserControllerUpdateOperationTest extends AbstractTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void updateUser() throws Exception {
        final String uri = "/api/users/" + PathVariables.UPDATE;
        final User testUser = new User();
        testUser.setUsername(SimpleStringGenerator.generate(6));
        testUser.setPassword(SimpleStringGenerator.generate(6));
        final MvcResult mvcResult = mvc.perform(
                        MockMvcRequestBuilders
                                .put(uri)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(super.mapToJson(testUser))
                                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        final int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(204, status);
    }

    @Test
    public void updateUserWithNonUniqueUsername() throws Exception {
        final String uri = "/api/users/" + PathVariables.UPDATE;
        final User testUser = new User();
        testUser.setUsername("user_test");
        testUser.setPassword(SimpleStringGenerator.generate(6));
        final MvcResult mvcResult = mvc.perform(
                        MockMvcRequestBuilders
                                .put(uri)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(super.mapToJson(testUser))
                                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        final int status = mvcResult.getResponse().getStatus();
        ;
        Assertions.assertEquals(400, status);
    }

    @Test
    public void updateUserWithShorUsernameAndPassword() throws Exception {
        final String uri = "/api/users/" + PathVariables.UPDATE;
        final User testUser = new User();
        testUser.setUsername(SimpleStringGenerator.generate(0));
        testUser.setPassword(SimpleStringGenerator.generate(0));
        final MvcResult mvcResult = mvc.perform(
                        MockMvcRequestBuilders
                                .put(uri)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(super.mapToJson(testUser))
                                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        final int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(400, status);
        Assertions.assertTrue(mvcResult.getResolvedException().getMessage().contains("Validation failed"));
    }

    @Test
    public void updateUserWithShortUsername() throws Exception {
        final String uri = "/api/users/" + PathVariables.UPDATE;
        final User testUser = new User();
        testUser.setUsername(SimpleStringGenerator.generate(0));
        testUser.setPassword(SimpleStringGenerator.generate(6));
        final MvcResult mvcResult = mvc.perform(
                        MockMvcRequestBuilders
                                .put(uri)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(super.mapToJson(testUser))
                                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        final int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(400, status);
        Assertions.assertTrue(mvcResult.getResolvedException().getMessage().contains("Validation failed"));
    }

    @Test
    public void updateUserWithShortPassword() throws Exception {
        final String uri = "/api/users/" + PathVariables.UPDATE;
        final User testUser = new User();
        testUser.setUsername(SimpleStringGenerator.generate(6));
        testUser.setPassword(SimpleStringGenerator.generate(0));
        final MvcResult mvcResult = mvc.perform(
                        MockMvcRequestBuilders
                                .put(uri)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(super.mapToJson(testUser))
                                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        final int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(400, status);
        Assertions.assertTrue(mvcResult.getResolvedException().getMessage().contains("Validation failed"));
    }

    @Test
    public void updateNonexistentUserById() throws Exception {
        final String uri = "/api/users/" + PathVariables.INVALID_ID;
        final User testUser = new User();
        testUser.setUsername(SimpleStringGenerator.generate(6));
        testUser.setPassword(SimpleStringGenerator.generate(6));
        final MvcResult mvcResult = mvc.perform(
                        MockMvcRequestBuilders
                                .put(uri)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(super.mapToJson(testUser))
                                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        final int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(404, status);
        Assertions.assertEquals("User with id " + PathVariables.INVALID_ID + " does not exists.", mvcResult.getResolvedException().getMessage());
    }

}
