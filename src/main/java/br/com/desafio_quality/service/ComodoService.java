package br.com.desafio_quality.service;

import br.com.desafio_quality.entity.Comodo;
import br.com.desafio_quality.entity.Imovel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class ComodoService {

    public static List<Comodo> quartoList = new ArrayList<>();


    //Busca o maior quarto de um imovel
    public Comodo comparaQuartos(Imovel imovel){
        quartoList = imovel.getComodos();
        return quartoList.stream()
                .max(Comparator.comparing(Comodo::getTamanho)).get();
    }
}
