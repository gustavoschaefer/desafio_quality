package br.com.desafio_quality.controller;

import br.com.desafio_quality.entity.Comodo;
import br.com.desafio_quality.entity.Imovel;
import br.com.desafio_quality.service.ImovelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;

@RestController
@RequestMapping("/imoveis")
public class ImovelController {
    @Autowired
    private ImovelService imovelService;

    //Cadastra um imovel na lista de Imóveis (ImovelService)
    @PostMapping("/cadastra")
    public Imovel cadastra(@Valid @RequestBody Imovel imovel) {
        return imovelService.cadastra(imovel);
    }

    //Endpoint que busca um imóvel por nome;
    @GetMapping("/{nome}")
    public Imovel obtemImovel(@PathVariable String nome){
        return imovelService.buscarImovel(nome);
    }

    //Endpoint que retorna o metragem de um imóvel;
    @GetMapping("/metragem/{nome}")
    public Double obtemMetragemImovel(@PathVariable String nome){
        return imovelService.buscarMetragemImovel(nome);
    }

    // b) Indicar o valor do imóvel tendo em conta que se toma como referência 800 USD por metro quadrado.
    @GetMapping("/valor/{nome}")
    public BigDecimal obtemValorImovel(@PathVariable String nome){
        return imovelService.calcularValorImovel(nome);
    }

    //c) Retornar os dados do maior quarto (nome, largura e comprimento)
    @GetMapping("/maiorcomodo/{nome}")
    public Comodo maiorComodo(@PathVariable String nome){
        return imovelService.obtemMaiorComodo(nome);
    }
}
