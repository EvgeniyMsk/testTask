package com.axiomatika.test;

import com.axiomatika.test.dto.EquationDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
public class MainApplicationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testEquation() throws Exception {
        EquationDTO equationDTO = new EquationDTO(1.0, 3.0, 4.0);
        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(post("/api/task")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(equationDTO))).andDo(print())
                .andExpect(status().is(200));
    }
}
