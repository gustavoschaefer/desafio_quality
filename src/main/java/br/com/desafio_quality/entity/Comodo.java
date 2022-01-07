package br.com.desafio_quality.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comodo {

    @NotNull
    @NotEmpty(message = "O nome do comodo é obrigatório.")
    @Size(max = 30, message = "O tamanho do nome do comodo máximo é 30.")
    @Pattern(regexp = "[A-Z].*", message = "O nome do comodo deve começar com a letra maiúscula.")
    private String nome;

    @NotNull
    @DecimalMin(value = "0.00", inclusive = false)
    @DecimalMax(value = "25.00")
    @Digits(integer=2, fraction=2)
    private double largura;

    @NotNull
    @DecimalMin(value = "0.00", inclusive = false)
    @DecimalMax(value = "33.00")
    @Digits(integer=2, fraction=2)
    private double comprimento;

    private double tamanho;

    public double getTamanho() {
        tamanho = largura*comprimento;

        return tamanho;
    }

}
