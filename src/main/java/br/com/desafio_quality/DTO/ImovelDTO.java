package br.com.desafio_quality.DTO;

import br.com.desafio_quality.entity.Bairro;
import br.com.desafio_quality.entity.Comodo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImovelDTO {
    private String nome;
    private Bairro bairro;
    private double tamanho;
    private List<Comodo> comodos;

}
