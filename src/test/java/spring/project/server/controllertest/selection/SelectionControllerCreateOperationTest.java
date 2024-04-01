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

/**
 * @author Veljko
 */
public class SelectionControllerCreateOperationTest extends AbstractTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void createSelection() throws Exception {
        final String uri = "/api/selections";
        final Selection testSelection = new Selection();
        testSelection.setName(SimpleStringGenerator.generate(10));
        final Confederation confederation = new Confederation();
        confederation.setId(SimpleIDGenerator.generate(5));
        testSelection.setConfederation(confederation);
        final User user = new User();
        user.setId(1);
        testSelection.setUser(user);
        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                        .post(uri)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(super.mapToJson(testSelection))
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        final int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(201, status);
    }

    @Test
    public void createSelectionWithNonUniqueName() throws Exception {
        final String uri = "/api/selections";
        final Selection testSelection = new Selection();
        testSelection.setName("Serbia");
        final Confederation confederation = new Confederation();
        confederation.setId(SimpleIDGenerator.generate(5));
        testSelection.setConfederation(confederation);
        final User user = new User();
        user.setId(4);
        testSelection.setUser(user);
        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                        .post(uri)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(super.mapToJson(testSelection))
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        final int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(400, status);
    }


    @Test
    public void createSelectionWithNoUser() throws Exception {
        final String uri = "/api/selections";
        final Selection testSelection = new Selection();
        testSelection.setName(SimpleStringGenerator.generate(6));
        final Confederation confederation = new Confederation();
        confederation.setId(SimpleIDGenerator.generate(5));
        testSelection.setConfederation(confederation);
        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                        .post(uri)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(super.mapToJson(testSelection))
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        final int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(400, status);
        Assertions.assertTrue(mvcResult.getResolvedException().getMessage().contains("Validation failed"));
    }

    @Test
    public void createSelectionWithNonConfederation() throws Exception {
        final String uri = "/api/selections";
        final Selection testSelection = new Selection();
        testSelection.setName(SimpleStringGenerator.generate(5));
        final User user = new User();
        user.setId(4);
        testSelection.setUser(user);
        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                        .post(uri)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(super.mapToJson(testSelection))
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        final int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(400, status);
        Assertions.assertTrue(mvcResult.getResolvedException().getMessage().contains("Validation failed"));
    }

    @Test
    public void createSelectionWithNegativePoints() throws Exception {
        final String uri = "/api/selections";
        final Selection testSelection = new Selection();
        testSelection.setName(SimpleStringGenerator.generate(10));
        final Confederation confederation = new Confederation();
        confederation.setId(SimpleIDGenerator.generate(5));
        testSelection.setConfederation(confederation);
        final User user = new User();
        user.setId(4);
        testSelection.setUser(user);
        testSelection.setPoints(-1);
        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                        .post(uri)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(super.mapToJson(testSelection))
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        final int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(400, status);
        Assertions.assertTrue(mvcResult.getResolvedException().getMessage().contains("Validation failed"));
    }

    @Test
    public void createSelectionWithNegativeRang() throws Exception {
        final String uri = "/api/selections";
        final Selection testSelection = new Selection();
        testSelection.setName(SimpleStringGenerator.generate(10));
        final Confederation confederation = new Confederation();
        confederation.setId(SimpleIDGenerator.generate(5));
        testSelection.setConfederation(confederation);
        final User user = new User();
        user.setId(4);
        testSelection.setUser(user);
        testSelection.setRang(-1);
        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                        .post(uri)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(super.mapToJson(testSelection))
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        final int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(400, status);
        Assertions.assertTrue(mvcResult.getResolvedException().getMessage().contains("Validation failed"));
    }

}
