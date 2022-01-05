package br.com.desafio_quality.service;

import br.com.desafio_quality.entity.Comodo;
import br.com.desafio_quality.entity.Imovel;
import br.com.desafio_quality.repository.ImovelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ImovelService {

    @Autowired
    ImovelRepository imovelRepository;

    @Autowired
    ComodoService comodoService;

    List<Imovel> imovelList = new ArrayList<>();

    //Endpoint para cadastrar Imovel
    public Imovel cadastra(Imovel imovel) {
       try {
           imovelRepository.setImovel(imovel);
       }catch (IOException e){

       }
        return imovel;
    }

    //Busca imovel por nome
    public Imovel buscarImovel(String nome) {
        Imovel imovel = new Imovel();
        List<Imovel> imoveis = new ArrayList<>();

        try{
            imoveis = imovelRepository.listarImovel();

        }catch (IOException e){
            e.getMessage();
        }
        Optional<Imovel> optional = imoveis.stream()
                .filter(u -> u.getNome().equals(nome))
                .findFirst();
        imovel = optional.orElse(new Imovel());
        return imovel;
    }

    //Busca por nome do imovel retornando apenas o tamanho do mesmo.
    public Double buscarMetragemImovel(String nome) {
        try{
            Optional<Imovel> optional = imovelRepository.listarImovel().stream()
                    .filter(u -> u.getNome().equals(nome))
                    .findFirst();

            return optional.get().getTamanho();
        }catch (IOException e){

        }
        return null;
    }

    //Busca por nome do imovel retornando o valor do mesmo
    public BigDecimal calcularValorImovel(String nome) {
        try{
            Optional<Imovel> optional = imovelRepository.listarImovel().stream()
                    .filter(u -> u.getNome().equals(nome))
                    .findFirst();
            BigDecimal valorImovel = optional.get().getBairro().getValorMetroQuadrado()
                    .multiply(new BigDecimal(optional.get().getTamanho()));
            return valorImovel;
        }catch (IOException e){

        }
        return null;
    }

    //Busca maior quarto
    public Comodo obtemMaiorQuarto(String nome) {
            try{
                Optional<Imovel> optional = imovelRepository.listarImovel().stream()
                        .filter(u -> u.getNome().equals(nome))
                        .findFirst();
              return   comodoService.comparaComodos(optional.get());

            }catch (IOException e){

            }
        return null;
    }
}
