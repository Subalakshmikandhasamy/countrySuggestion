package com.backend.country;

import com.backend.country.controller.CountyController;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CountyApplicationTests {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ApplicationContext context;

  @InjectMocks
  private CountyController countyController;

  // Load data before all tests
  @BeforeAll
  public void setContext() throws Exception {
    // Get the CommandLineRunner bean from the Spring context
    CommandLineRunner dataLoader = context.getBean(CommandLineRunner.class);

    // Manually invoke the run method to load data
    dataLoader.run();
  }

  // Test controller method with valid name and state
  @Test
  void testControllerMethod_WithValidNameAndState() throws Exception {
    mockMvc.perform(get("/counties/suggest?q=cowlitz, wa"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].fips").value("53015"))
            .andExpect(jsonPath("$[0].state").value("WA"))
            .andExpect(jsonPath("$[0].name").value("Cowlitz"));
  }

  // Test controller method with partial name
  @Test
  void testControllerMethod_WithPartialName() throws Exception {
    mockMvc.perform(get("/counties/suggest?q=cowl"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].fips").value("20035"))
            .andExpect(jsonPath("$[0].state").value("KS"))
            .andExpect(jsonPath("$[0].name").value("Cowley"))
            .andExpect(jsonPath("$[1].fips").value("53015"))
            .andExpect(jsonPath("$[1].state").value("WA"))
            .andExpect(jsonPath("$[1].name").value("Cowlitz"));
  }

  // Test controller method with valid state
  @Test
  void testControllerMethod_WithValidState() throws Exception {
    mockMvc.perform(get("/counties/suggest?q=wa"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].fips").value("53001"))
            .andExpect(jsonPath("$[0].state").value("WA"))
            .andExpect(jsonPath("$[0].name").value("Adams"))
            .andExpect(jsonPath("$[1].fips").value("53003"))
            .andExpect(jsonPath("$[1].state").value("WA"))
            .andExpect(jsonPath("$[1].name").value("Asotin"))
            .andExpect(jsonPath("$[2].fips").value("53005"))
            .andExpect(jsonPath("$[2].state").value("WA"))
            .andExpect(jsonPath("$[2].name").value("Benton"))
            .andExpect(jsonPath("$[3].fips").value("53007"))
            .andExpect(jsonPath("$[3].state").value("WA"))
            .andExpect(jsonPath("$[3].name").value("Chelan"))
            .andExpect(jsonPath("$[4].fips").value("53009"))
            .andExpect(jsonPath("$[4].state").value("WA"))
            .andExpect(jsonPath("$[4].name").value("Clallam"));
  }

  // Test controller method with query parameter as null
  @Test
  void testControllerMethod_WithQueryParamAsNull() throws Exception {
    mockMvc.perform(get("/counties/suggest?q=null"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isEmpty());
  }

  // Test controller method with query parameter as empty
  @Test
  void testControllerMethod_WithQueryParamAsEmpty() throws Exception {
    mockMvc.perform(get("/counties/suggest?q="))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].fips").value("01001"))
            .andExpect(jsonPath("$[0].state").value("AL"))
            .andExpect(jsonPath("$[0].name").value("Autauga"))
            .andExpect(jsonPath("$[1].fips").value("01003"))
            .andExpect(jsonPath("$[1].state").value("AL"))
            .andExpect(jsonPath("$[1].name").value("Baldwin"))
            .andExpect(jsonPath("$[2].fips").value("01005"))
            .andExpect(jsonPath("$[2].state").value("AL"))
            .andExpect(jsonPath("$[2].name").value("Barbour"))
            .andExpect(jsonPath("$[3].fips").value("01007"))
            .andExpect(jsonPath("$[3].state").value("AL"))
            .andExpect(jsonPath("$[3].name").value("Bibb"))
            .andExpect(jsonPath("$[4].fips").value("01009"))
            .andExpect(jsonPath("$[4].state").value("AL"))
            .andExpect(jsonPath("$[4].name").value("Blount"));
  }

  // Test controller method with missing query parameter
  @Test
  void testControllerMethod_WithNoQueryParam() throws Exception {
    mockMvc.perform(get("/counties/suggest"))
            .andExpect(status().is5xxServerError());
  }
}
