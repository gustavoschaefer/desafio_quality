package br.com.desafio_quality.service;

import br.com.desafio_quality.entity.Bairro;
import br.com.desafio_quality.repository.BairroRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BairroService {

    private BairroRepository bairroRepository;

    List<Bairro> bairros = new ArrayList<>();

    public BairroService(BairroRepository bairroRepository) {
        this.bairroRepository = bairroRepository;
    }




    public boolean bairroExiste(Bairro bairro) {
        try {
            bairros = bairroRepository.getBairros();
        } catch (IOException e) {
            throw new RuntimeException("Erro ao buscar bairros.");
        }
        for (Bairro b : bairros) {
            if (b.getNome().equals(bairro.getNome())) {
                return true;
            }
        }
        return false;
    }
}
