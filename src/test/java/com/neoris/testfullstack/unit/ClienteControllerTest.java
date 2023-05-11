package com.neoris.testfullstack.unit;

import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.neoris.testfullstack.Controller.ClienteController;
import com.neoris.testfullstack.Entity.Cliente;
import com.neoris.testfullstack.Service.ClienteService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ClienteController.class)
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteService clienteService;

    @Test
    public void testCrearCliente() throws Exception {
        // Creamos un objeto cliente para enviar en el cuerpo de la petición
        Cliente cliente = new Cliente();
        cliente.setIdentificacion("123456789");
        cliente.setNombre("Pepito Perez");
        cliente.setGenero(true);
        cliente.setEdad((byte) 20);
        cliente.setDireccion("Calle x # 10-5");
        cliente.setTelefono("3121112222");
        cliente.setContrasenia("password");
        cliente.setEstado(true);

        // Configuramos el comportamiento del mock de ClienteService
        when(clienteService.save(cliente)).thenReturn(cliente);

        // Enviamos la petición POST al endpoint de crear cliente
        mockMvc.perform(post("/api/cliente")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(cliente)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.identificacion", is("123456789")))
                .andExpect(jsonPath("$.data.nombre", is("Pepito Perez")))
                .andExpect(jsonPath("$.data.genero", is(true)))
                .andExpect(jsonPath("$.data.edad", is(20)))
                .andExpect(jsonPath("$.data.direccion", is("Calle x # 10-5")))
                .andExpect(jsonPath("$.data.telefono", is("3121112222")))
                .andExpect(jsonPath("$.data.contrasenia", is("password")))
                .andExpect(jsonPath("$.data.estado", is(true)));

    }

    // Utilidad para convertir objetos Java a JSON
    private static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}