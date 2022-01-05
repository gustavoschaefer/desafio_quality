package br.com.desafio_quality.service.test;


import br.com.desafio_quality.entity.Bairro;
import br.com.desafio_quality.entity.Comodo;
import br.com.desafio_quality.entity.Imovel;
import br.com.desafio_quality.repository.ImovelRepository;
import br.com.desafio_quality.service.BairroService;
import br.com.desafio_quality.service.ImovelService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;

public class ImovelServiceTest {

    @Test
    public void deveBuscarMetragemImovel() throws IOException {

        Bairro bairro = Bairro.builder()
                .nome("Bairro1")
                .valorMetroQuadrado(BigDecimal.valueOf(200.00)).build();

        BairroService mockBairro = Mockito.mock(BairroService.class);
        Mockito.when(mockBairro.bairroExiste(bairro)).thenReturn(true);

        Imovel imovel = Imovel.builder()
                .nome("Casa1")
                .bairro(Bairro.builder()
                        .nome("Bairro1")
                        .valorMetroQuadrado(BigDecimal.valueOf(200.00)).build())
                .comodos(Arrays.asList(
                        Comodo.builder().nome("Quarto1").largura(3).comprimento(4).build(),
                        Comodo.builder().nome("Quarto2").largura(4).comprimento(4).build(),
                        Comodo.builder().nome("Cozinha").largura(6).comprimento(5).build(),
                        Comodo.builder().nome("Sala").largura(7).comprimento(5).build(),
                        Comodo.builder().nome("Banheiro").largura(2).comprimento(3).build()
                )).build();

        ImovelRepository mock = Mockito.mock(ImovelRepository.class);
        Mockito.when(mock.setImovel(imovel)).thenReturn(imovel);
        ImovelService imovelService = new ImovelService(mock, mockBairro);

        Imovel imovelRegistro = imovelService.cadastra(imovel);

        Assertions.assertEquals(imovelRegistro.getTamanho(), 99.0);
    }
}
