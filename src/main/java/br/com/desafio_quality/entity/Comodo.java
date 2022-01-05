package br.com.desafio_quality.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comodo {
    private String nome;
    private double largura;
    private double comprimento;

    public double getTamanho() {
        return largura*comprimento;
    }

}
