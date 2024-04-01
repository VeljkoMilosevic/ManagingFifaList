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
public class UserControllerCreateOperationTest extends AbstractTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void createUser() throws Exception {
        final String uri = "/api/users";
        final User testUser = new User();
        final String usernameAndPassword = SimpleStringGenerator.generate(8);
        testUser.setUsername(usernameAndPassword);
        testUser.setPassword(usernameAndPassword);
        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                        .post(uri)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(super.mapToJson(testUser))
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        final int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(201, status);
    }


    @Test
    public void createUserWithBusyUsername() throws Exception {
        final String uri = "/api/users";
        final User testUser = new User();
        testUser.setUsername("user_test");
        testUser.setPassword("user_test");
        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                        .post(uri)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(super.mapToJson(testUser))
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        final int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(400, status);
    }

    @Test
    public void createUserWithShortUsernameAndPassword() throws Exception {
        final String uri = "/api/users";
        final User testUser = new User();
        testUser.setUsername("test");
        testUser.setPassword("test");
        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                        .post(uri)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(super.mapToJson(testUser))
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        final int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(400, status);
        Assertions.assertTrue(mvcResult.getResolvedException().getMessage().contains("Validation failed"));
    }

    @Test
    public void createUserWithShortUsername() throws Exception {
        final String uri = "/api/users";
        final User testUser = new User();
        testUser.setUsername("test");
        testUser.setPassword(SimpleStringGenerator.generate(4));
        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                        .post(uri)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(super.mapToJson(testUser))
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        final int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(400, status);
        Assertions.assertTrue(mvcResult.getResolvedException().getMessage().contains("Validation failed"));
    }

    @Test
    public void createUserWithShortPassword() throws Exception {
        final String uri = "/api/users";
        final User testUser = new User();
        testUser.setUsername(SimpleStringGenerator.generate(4));
        testUser.setPassword("test");
        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                        .post(uri)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(super.mapToJson(testUser))
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        final int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(400, status);
        Assertions.assertTrue(mvcResult.getResolvedException().getMessage().contains("Validation failed"));
    }
}
