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
import spring.project.server.model.User;

/**
 * @author Veljko
 */
public class UserControllerLoginOperationTest extends AbstractTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void loginUser() throws Exception {
        final String uri = "/api/users/login";
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
        Assertions.assertEquals(200, status);
        final String content = mvcResult.getResponse().getContentAsString();
        final User restUser = super.mapFromJson(content, User.class);
        Assertions.assertEquals(testUser, restUser);
    }

    @Test
    public void loginUserWrongUsername() throws Exception {
        final String uri = "/api/users/login";
        final User testUser = new User();
        testUser.setUsername("test_user");
        testUser.setPassword("user_test");
        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                        .post(uri)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(super.mapToJson(testUser))
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        final int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(401, status);
        Assertions.assertEquals("Username or password are wrong. Please, try again.", mvcResult.getResolvedException().getMessage());
    }

    @Test
    public void loginUserWrongPassword() throws Exception {
        final String uri = "/api/users/login";
        final User testUser = new User();
        testUser.setUsername("user_test");
        testUser.setPassword("test_user");
        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                        .post(uri)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(super.mapToJson(testUser))
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        final int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(401, status);
        Assertions.assertEquals("Username or password are wrong. Please, try again.", mvcResult.getResolvedException().getMessage());
    }

    @Test
    public void loginUserWrongUsernameAndPassword() throws Exception {
        final String uri = "/api/users/login";
        final User testUser = new User();
        testUser.setUsername("test_user");
        testUser.setPassword("test_user");
        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                        .post(uri)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(super.mapToJson(testUser))
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        final int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(401, status);
        Assertions.assertEquals("Username or password are wrong. Please, try again.", mvcResult.getResolvedException().getMessage());
    }
}
