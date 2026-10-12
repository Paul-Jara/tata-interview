package com.tata.customerservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tata.customerservice.dto.ClientDTO;
import com.tata.customerservice.model.ClientEntity;
import com.tata.customerservice.repository.ClientRepository;
import com.tata.customerservice.types.Gender;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DisplayName("ClientController Integration Tests")
class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private ClientRepository clientRepository;

    @AfterEach
    void tearDown() {
        clientRepository.deleteAll();
    }

    @Test
    @DisplayName("POST /api/clientes - Debe crear un cliente exitosamente y retornar HTTP 201 Created")
    void shouldCreateClientSuccessfully() throws Exception {
        // Arrange
        final ClientDTO clientDTO = ClientDTO.builder()
                .name("Marianela Montalvo")
                .gender(Gender.FEMALE)
                .age(28)
                .identification("0987654321")
                .address("Amazonas y NNUU")
                .phone("097548965")
                .password("5678")
                .status(true)
                .build();

        // Act & Assert
        mockMvc.perform(post("/api/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clientDTO)))
                .andExpect(status().isCreated());

        // Verificar que realmente se persistió en la base de datos
        final List<ClientEntity> clients = clientRepository.findAll();
        assertEquals(1, clients.size());
        assertEquals("Marianela Montalvo", clients.get(0).getName());
        assertEquals("0987654321", clients.get(0).getIdentification());
        assertTrue(clients.get(0).getStatus());
    }

    @Test
    @DisplayName("POST /api/clientes - Debe retornar HTTP 400 Bad Request cuando los datos no son válidos")
    void shouldReturnBadRequestWhenClientDataIsInvalid() throws Exception {
        // Arrange: DTO con campos inválidos (edad menor a 18 y nombre nulo)
        final ClientDTO invalidClientDTO = ClientDTO.builder()
                .name(null)
                .age(15)
                .identification("123")
                .build();

        // Act & Assert
        mockMvc.perform(post("/api/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidClientDTO)))
                .andExpect(status().isBadRequest());
    }
}
