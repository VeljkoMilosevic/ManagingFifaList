/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.project.server.controllertest.confederation;

import org.assertj.core.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import spring.project.server.controllertest.AbstractTest;
import spring.project.server.model.Confederation;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Veljko
 */
public class ConfederationControllerTest extends AbstractTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void getConfederations() throws Exception {
        final String uri = "/api/confederations";
        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        final int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(200, status);
        final String content = mvcResult.getResponse().getContentAsString();
        final List<Confederation> confederations = (List) Arrays.asList(super.mapFromJson(content, Confederation[].class));
        final ConfederataionComparator confederataionComparator = new ConfederataionComparator();
        Collections.sort(confederations, confederataionComparator);

        final List<Confederation> testConfederations = createAllConfederations();
        Collections.sort(testConfederations, confederataionComparator);
        Assertions.assertEquals(confederations, testConfederations);
    }

    private List<Confederation> createAllConfederations() {
        final List<Confederation> confederations = new LinkedList<>();

        final Confederation EUROPE = new Confederation();
        EUROPE.setId(1);
        EUROPE.setName("EUROPE");
        EUROPE.setStrenght(1);
        confederations.add(EUROPE);

        final Confederation SOUTHAMERICA = new Confederation();
        SOUTHAMERICA.setId(2);
        SOUTHAMERICA.setName("SOUTHAMERICA");
        SOUTHAMERICA.setStrenght(1);
        confederations.add(SOUTHAMERICA);

        final Confederation AFRICA = new Confederation();
        AFRICA.setId(3);
        AFRICA.setName("AFRICA");
        AFRICA.setStrenght(0.86);
        confederations.add(AFRICA);

        final Confederation ASIA = new Confederation();
        ASIA.setId(4);
        ASIA.setName("ASIA");
        ASIA.setStrenght(0.86);
        confederations.add(ASIA);

        final Confederation NORTHAMERICA = new Confederation();
        NORTHAMERICA.setId(5);
        NORTHAMERICA.setName("NORTHAMERICA");
        NORTHAMERICA.setStrenght(0.84);
        confederations.add(NORTHAMERICA);

        final Confederation OCEANIA = new Confederation();
        OCEANIA.setId(6);
        OCEANIA.setName("OCEANIA");
        OCEANIA.setStrenght(0.84);
        confederations.add(OCEANIA);

        return confederations;
    }

    class ConfederataionComparator implements Comparator<Confederation> {

        @Override
        public int compare(final Confederation a, final Confederation b) {
            return a.getName().compareTo(b.getName());
        }
    }

}
