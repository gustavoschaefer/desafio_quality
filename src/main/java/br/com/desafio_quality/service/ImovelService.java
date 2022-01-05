package br.com.desafio_quality.service;

import br.com.desafio_quality.entity.Comodo;
import br.com.desafio_quality.entity.Imovel;
import br.com.desafio_quality.repository.BairroRepository;
import br.com.desafio_quality.repository.ImovelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ImovelService {

    ImovelRepository imovelRepository;

    @Autowired
    ComodoService comodoService;

    BairroService bairroService;

    List<Imovel> imovelList = new ArrayList<>();

    public ImovelService(ImovelRepository imovelRepository, BairroService bairroService) {
        this.imovelRepository = imovelRepository;
        this.bairroService = bairroService;
    }

    //Endpoint para cadastrar Imovel
    public Imovel cadastra(Imovel imovel) {
       try {
           if (bairroService.bairroExiste(imovel.getBairro())) {
               imovelRepository.setImovel(imovel);
           }
           else {
               throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Bairro não registrado.");
           }

       }catch (IOException e){}
        return imovel;
    }

    //Busca imovel por nome
    public Imovel buscarImovel(String nome) {
        Imovel imovel = new Imovel();
        try{
            Optional<Imovel> optional = imovelRepository.listarImovel().stream()
                    .filter(u -> u.getNome().equals(nome))
                    .findFirst();
            imovel = optional.orElse(new Imovel());
        }catch (IOException e){
            //TODO
        }
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
    public Comodo obtemMaiorComodo(String nome) {
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
