package br.com.desafio_quality.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Bairro {

    @NotNull
    @NotEmpty(message = "O nome do bairro é obrigatório.")
    @Size(max = 45, message = "O tamanho do nome do bairro máximo é 45.")
    private String nome;

    @NotNull
    @DecimalMin(value = "0.00", inclusive = false)
    @Digits(integer=13, fraction=2)
    private BigDecimal valorMetroQuadrado;
}
