package br.com.desafio_quality.repository;

import br.com.desafio_quality.entity.Bairro;
import br.com.desafio_quality.entity.Imovel;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class BairroRepository {

    private List<Bairro> bairros = new ArrayList<>();
    private ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    private final String PATH = "src/main/java/br/com/desafio_quality/repository/bairros.json";

    public Bairro setBairro(Bairro bairro) throws IOException {
        bairros.add(bairro);
        objectMapper.writeValue(new File(PATH), bairros);
        return bairro;
    }

    public List<Bairro> getBairros() throws IOException {
        //bairros = objectMapper.readValue(new File(PATH), new TypeReference<ArrayList<Bairro>>() {});
        return bairros;
    }

}
