package org.traveloffice.spring.travelofficespring;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.traveloffice.spring.travelofficespring.repository.AddressRepository;
import org.traveloffice.spring.travelofficespring.repository.CustomerRepository;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc

public class TravelofficespringApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AddressRepository addressRepository;

    @Before
    public void deleteAllCustomersBeforeTests()
    {
        customerRepository.deleteAll();
    }
    @Before
    public void deleteAllAddressesBeforeTests()
    {
        addressRepository.deleteAll();
    }

    @Test
    public void createEntityCustomer() throws Exception {
        mockMvc.perform(post("/customers").content(
                "{\"name\": \"Szymon\"}")).andExpect(
                        status().isCreated()).andExpect(
                                header().string("Location",containsString("customers/")));
    }

    @Test
    public void showEntityCustomer() throws Exception {
        MvcResult mvcResult = (MvcResult) mockMvc.perform(post("/customers").content(
                "{\"name\": \"Szymon\"}")).andExpect(status().isCreated()).andReturn();

        String location = mvcResult.getResponse().getHeader("Location");

        mockMvc.perform(get(location)).andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Szymon"));

    }

    @Test
    public void deleteEntityCustomer() throws Exception {
        MvcResult mvcResult = mockMvc.perform(post("/customers").content(
                "{\"name\": \"Szymon\"}")).andExpect(
                status().isCreated()).andReturn();
        String location = mvcResult.getResponse().getHeader("Location");
        mockMvc.perform(delete(location)).andExpect(status().isNoContent());
        mockMvc.perform(get(location)).andExpect(status().isNotFound());
    }

    @Test
    public void createEntityAddress() throws Exception {
        mockMvc.perform(post("/addresses").content(
                "{\"street\": \"ulica\", \"zip\": \"90-000\", \"city\": \"Zgierz\"}")).andExpect(
                status().isCreated()).andExpect(
                header().string("Location",containsString("addresses/")));
    }

    @Test
    public void showEntityAddress() throws Exception {
        MvcResult mvcResult = (MvcResult) mockMvc.perform(post("/addresses").content(
                "{\"street\": \"ulica\", \"zip\": \"90-000\", \"city\": \"zgierz\"}")).andExpect(status().isCreated()).andReturn();

        String location = mvcResult.getResponse().getHeader("Location");

        mockMvc.perform(get(location)).andExpect(status().isOk())
                .andExpect(jsonPath("$.street").value("ulica"))
                .andExpect(jsonPath("$.zip").value("90-000"))
                .andExpect(jsonPath("$.city").value("zgierz"));

    }

    @Test
    public void deleteEntityAddress() throws Exception {
        MvcResult mvcResult = mockMvc.perform(post("/addresses").content(
                "{\"street\": \"ulica\", \"zip\": \"90-000\", \"city\": \"Zgierz\"}")).andExpect(
                status().isCreated()).andReturn();
        String location = mvcResult.getResponse().getHeader("Location");
        mockMvc.perform(delete(location)).andExpect(status().isNoContent());
        mockMvc.perform(get(location)).andExpect(status().isNotFound());
    }

}