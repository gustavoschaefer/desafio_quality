package br.com.desafio_quality.controller;

import br.com.desafio_quality.entity.Comodo;
import br.com.desafio_quality.entity.Imovel;
import br.com.desafio_quality.service.ImovelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.net.URI;

@RestController
@RequestMapping("/imoveis")
public class ImovelController {
    @Autowired
    private ImovelService imovelService;

    //Cadastra um imovel na lista de Imóveis (ImovelService)
    @PostMapping("/cadastra")
    public ResponseEntity<Imovel> cadastra(@Valid @RequestBody Imovel imovel, UriComponentsBuilder uriComponentsBuilder) {
        URI uri = uriComponentsBuilder
                .path("/imoveis/{nome}")
                .buildAndExpand(imovel.getNome())
                .toUri();
        return ResponseEntity.created(uri).body(imovelService.cadastra(imovel));
    }

    //Endpoint que busca um imóvel por nome;
    @GetMapping("/{nome}")
    public ResponseEntity<Imovel> obtemImovel(@PathVariable String nome){

        return ResponseEntity.ok(imovelService.buscarImovel(nome));
    }

    //Endpoint que retorna o metragem de um imóvel;
    @GetMapping("/metragem/{nome}")
    public ResponseEntity<Double> obtemMetragemImovel(@PathVariable String nome){

        return ResponseEntity.ok(imovelService.buscarMetragemImovel(nome));
    }

    // b) Indicar o valor do imóvel tendo em conta que se toma como referência 800 USD por metro quadrado.
    @GetMapping("/valor/{nome}")
    public ResponseEntity<BigDecimal> obtemValorImovel(@PathVariable String nome ){

        return ResponseEntity.ok(imovelService.calcularValorImovel(nome));
    }

    //c) Retornar os dados do maior quarto (nome, largura e comprimento)
    @GetMapping("/maiorcomodo/{nome}")
    public ResponseEntity<Comodo> maiorComodo(@PathVariable String nome){

        return ResponseEntity.ok(imovelService.obtemMaiorComodo(nome));
    }
}
