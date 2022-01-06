package br.com.desafio_quality.repository;


import br.com.desafio_quality.entity.Imovel;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ImovelRepository {

    private List<Imovel> imoveis = new ArrayList<>();
    private ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    private final String PATH = "src/main/java/br/com/desafio_quality/repository/imoveis.json";

    public Imovel setImovel(Imovel imovel) throws IOException {
        imoveis.add(imovel);
        objectMapper.writeValue(new File(PATH), imoveis);
        return imovel;
    }

    public List<Imovel> listarImovel() throws  IOException{

        imoveis = objectMapper.readValue(new File(PATH), new TypeReference<ArrayList<Imovel>>() {
        });
        return imoveis;
    }

}
