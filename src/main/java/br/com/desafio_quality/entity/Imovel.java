package br.com.desafio_quality.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Imovel {
    private String nome;
    private Bairro bairro;
    private double tamanho;
    private List<Comodo> comodos;
}
