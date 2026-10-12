package com.tata.customerservice.model;

import com.tata.customerservice.types.Gender;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientEntityTest {

    @Test
    void createClientEntityWithPersonAttributes() {
        // Arrange & Act
        ClientEntity client = ClientEntity.builder()
                .personId(1L)
                .clientId(1L)
                .name("Jose Lema")
                .gender(Gender.MALE)
                .age(39)
                .identification("1717")
                .address("Otavalo sn y principal")
                .phone("098254785")
                .password("1234")
                .status(true)
                .build();

        // Assert
        assertNotNull(client);
        assertEquals(1L, client.getClientId());
        assertEquals("Jose Lema", client.getName());
        assertEquals(Gender.MALE, client.getGender());
        assertEquals(39, client.getAge());
        assertEquals("1717", client.getIdentification());
        assertEquals("Otavalo sn y principal", client.getAddress());
        assertEquals("098254785", client.getPhone());
        assertEquals("1234", client.getPassword());
        assertTrue(client.getStatus());
    }
}
