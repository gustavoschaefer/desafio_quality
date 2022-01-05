package br.com.desafio_quality.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Imovel {

    @NotNull
    @NotEmpty(message = "O nome do imovel é obrigatório.")
    @Size(max = 30, message = "O tamanho do nome do imovel máximo é 30.")
    @Pattern(regexp = "[A-Z].*", message = "O nome do imovel deve começar com a letra maiúscula.")
    private String nome;

    @Valid
    private Bairro bairro;
    private double tamanho;

    @Valid
    private List<Comodo> comodos;

    public double getTamanho() {
        tamanho = 0;
        comodos.forEach(comodo -> tamanho += comodo.getTamanho());
        return tamanho;
    }
}
