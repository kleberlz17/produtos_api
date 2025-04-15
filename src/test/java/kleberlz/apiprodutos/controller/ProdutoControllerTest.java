package kleberlz.apiprodutos.controller;

import java.math.BigDecimal;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import kleberlz.apiprodutos.dto.ProdutoDTO;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
class ProdutoControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@WithMockUser(roles = "GERENTE")
	@Test
	void deveCriarProdutoComSucesso() throws Exception {
		UUID id = UUID.randomUUID();
		ProdutoDTO produtoDTO = new ProdutoDTO(id, "Monitor", new BigDecimal("1299.99"), "Monitor 4K UltraWide de altíssima resolução. ");
		
		mockMvc.perform(post("/produtos")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(produtoDTO)))
				.andExpect(status().isCreated())
				.andExpect(header().exists("Location"));
				
	}
}
