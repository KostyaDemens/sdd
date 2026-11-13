package by.bsuir.kostyademens.sdd.veterinarian;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@ActiveProfiles("test")
class VeterinarianControllerTest {

    private static final String VALID_REQUEST = """
            {"fullName":"Dr. Maya Patel","specialization":"Surgery","phoneNumber":"+375291234567","email":"maya.patel@example.com"}
            """;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void supportsFullCrudLifecycle() throws Exception {
        String created = mockMvc.perform(post("/api/veterinarians")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(VALID_REQUEST))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.fullName").value("Dr. Maya Patel"))
                .andReturn().getResponse().getContentAsString();

        long id = ((Number) com.jayway.jsonpath.JsonPath.read(created, "$.id")).longValue();

        mockMvc.perform(get("/api/veterinarians/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("maya.patel@example.com"));

        mockMvc.perform(get("/api/veterinarians"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(id));

        mockMvc.perform(put("/api/veterinarians/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"fullName":"Dr. Maya Patel","specialization":"Dermatology","phoneNumber":"+375291234567","email":"maya.patel@example.com"}
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.specialization").value("Dermatology"));

        mockMvc.perform(delete("/api/veterinarians/{id}", id))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/veterinarians/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    void returnsValidationDetailsForInvalidRequest() throws Exception {
        mockMvc.perform(post("/api/veterinarians")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"fullName":" ","specialization":"","phoneNumber":"","email":"not-an-email"}
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors.fullName").exists())
                .andExpect(jsonPath("$.errors.email").exists());
    }

    @Test
    void returnsNotFoundForUpdateAndDeleteOfMissingVeterinarian() throws Exception {
        mockMvc.perform(put("/api/veterinarians/{id}", 999999L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(VALID_REQUEST))
                .andExpect(status().isNotFound());

        mockMvc.perform(delete("/api/veterinarians/{id}", 999999L))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.detail", containsString("999999")));
    }
}
