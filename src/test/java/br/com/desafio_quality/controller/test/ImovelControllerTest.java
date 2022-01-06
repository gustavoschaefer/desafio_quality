package br.com.desafio_quality.controller.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class ImovelControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void deveCadastrarUmImovel() throws Exception{

        String payload = "{\n" +
                "    \"nome\": \"Casa1\",\n" +
                "    \"bairro\": {\n" +
                "        \"nome\": \"Bairro1\",\n" +
                "        \"valorMetroQuadrado\": 100.00\n" +
                "    },\n" +
                "    \"comodos\": [\n" +
                "        {\n" +
                "            \"nome\": \"Quarto1\",\n" +
                "            \"largura\": 25.00,\n" +
                "            \"comprimento\": 33.00\n" +
                "        },\n" +
                "        {\n" +
                "            \"nome\": \"Quarto2\",\n" +
                "            \"largura\": 4.0,\n" +
                "            \"comprimento\": 4.0\n" +
                "        },\n" +
                "        {\n" +
                "            \"nome\": \"Cozinha\",\n" +
                "            \"largura\": 6.0,\n" +
                "            \"comprimento\": 5.0\n" +
                "        },\n" +
                "        {\n" +
                "            \"nome\": \"Sala\",\n" +
                "            \"largura\": 7.0,\n" +
                "            \"comprimento\": 5.0\n" +
                "        },\n" +
                "        {\n" +
                "            \"nome\": \"Banheiro\",\n" +
                "            \"largura\": 2.0,\n" +
                "            \"comprimento\": 3.0\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        mockMvc
                .perform(MockMvcRequestBuilders
                    .post("/imoveis/cadastra")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(payload))
                .andExpect(MockMvcResultMatchers.status().isCreated());

    }

    @Test
    public void deveObterUmImovel() throws Exception{
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders
                .get("/imoveis/Casa1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    public void deveObterMetragemImovel() throws Exception{
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/imoveis/metragem/Casa1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    public void deveObterValorImovel() throws Exception{
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/imoveis/valor/Casa1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    public void deveobterMaiorComodo() throws Exception{
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/imoveis/maiorcomodo/Casa1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        System.out.println(result.getResponse().getContentAsString());
    }
}
