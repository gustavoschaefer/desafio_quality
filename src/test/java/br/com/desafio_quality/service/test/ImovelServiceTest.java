package br.com.desafio_quality.service.test;

import br.com.desafio_quality.entity.Bairro;
import br.com.desafio_quality.entity.Comodo;
import br.com.desafio_quality.entity.Imovel;
import br.com.desafio_quality.repository.ImovelRepository;
import br.com.desafio_quality.service.BairroService;
import br.com.desafio_quality.service.ComodoService;
import br.com.desafio_quality.service.ImovelService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.server.ResponseStatusException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        ImovelService imovelService = new ImovelService(mock, mockBairro,null);

        Imovel imovelRegistro = imovelService.cadastra(imovel);

        Assertions.assertEquals(imovelRegistro.getTamanho(), 99.0);
    }

    @Test
    public void deveObterMaiorComodo() throws IOException {

        List<Imovel> imoveis = new ArrayList<>();

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

        imoveis.add(imovel);

        ImovelRepository mock = Mockito.mock(ImovelRepository.class);
        Mockito.when(mock.listarImovel()).thenReturn(imoveis);

        ComodoService comodoServiceMock = Mockito.mock(ComodoService.class);
        Comodo comodoTeste = Comodo.builder().nome("Sala").largura(7).comprimento(5).build();

        Mockito.when(comodoServiceMock.comparaComodos(imovel)).thenReturn(comodoTeste);

        ImovelService imovelService = new ImovelService(mock,null,comodoServiceMock);

        Comodo comodo = imovelService.obtemMaiorComodo(imovel.getNome());

        Assertions.assertEquals(comodo.getNome(), "Sala");
    }

    @Test
    public void buscarImovelException() throws IOException{
        ImovelRepository imovelRepositoryMock = Mockito.mock(ImovelRepository.class);
        Mockito.when(imovelRepositoryMock.listarImovel()).thenThrow(new IOException());

        ImovelService imovelService = new ImovelService(imovelRepositoryMock, null,null);

        ResponseStatusException thrown = Assertions.assertThrows(
                ResponseStatusException.class,
                () -> imovelService.buscarImovel("CasaTeste"));

        Assertions.assertTrue(thrown.getMessage().contains("Imovel não registrado."));

    }

}