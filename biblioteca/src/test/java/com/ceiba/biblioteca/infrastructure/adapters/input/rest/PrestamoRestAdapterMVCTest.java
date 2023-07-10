package com.ceiba.biblioteca.infrastructure.adapters.input.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PrestamoRestAdapterMVCTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void validarCrearPrestamoYConsultarPrestamo() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/prestamo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"isbn\": \"1232425\",\n" +
                                "    \"identificacionUsuario\": \"1036683384\",\n" +
                                "    \"tipoUsuario\": 1\n" +
                                "}")
                )
                .andExpect(MockMvcResultMatchers.status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.get("/prestamo/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void validarCrearPrestamoYaExistenteUsuarioInvitado() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/prestamo")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\n" +
                                        "    \"isbn\": \"1232425\",\n" +
                                        "    \"identificacionUsuario\": \"1036683384\",\n" +
                                        "    \"tipoUsuario\": 3\n" +
                                        "}")
                )
                .andExpect(MockMvcResultMatchers.status().isOk());

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/prestamo")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\n" +
                                        "    \"isbn\": \"1232425\",\n" +
                                        "    \"identificacionUsuario\": \"1036683384\",\n" +
                                        "    \"tipoUsuario\": 3\n" +
                                        "}")
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.mensaje").value("El usuario con identificación 1036683384 ya tiene un libro prestado por lo cual no se le puede realizar otro préstamo"));
    }

    @Test
    public void validarTipoUsuarioNoValido() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/prestamo")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\n" +
                                        "    \"isbn\": \"1232425\",\n" +
                                        "    \"identificacionUsuario\": \"1036683384\",\n" +
                                        "    \"tipoUsuario\": 6\n" +
                                        "}")
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.mensaje").value("Tipo de usuario no permitido en la biblioteca"));

    }

    @Test
    public void validarPrestamoNoEncontrado() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.get("/prestamo/1").contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isBadRequest())
            .andExpect(MockMvcResultMatchers.jsonPath("$.mensaje").value("Prestamo No encontrado"));
    }
}