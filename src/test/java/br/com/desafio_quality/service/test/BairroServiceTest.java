package br.com.desafio_quality.service.test;

import br.com.desafio_quality.entity.Bairro;
import br.com.desafio_quality.repository.BairroRepository;
import br.com.desafio_quality.service.BairroService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BairroServiceTest {

	@Test
	public void bairroDeveExistir() throws IOException {

		List<Bairro> bairros = new ArrayList<>();		
		bairros.add(Bairro.builder().nome("Bairro1").valorMetroQuadrado(BigDecimal.valueOf(200.00)).build());
		bairros.add(Bairro.builder().nome("Bairro2").valorMetroQuadrado(BigDecimal.valueOf(190.00)).build());				
		
		BairroRepository bairroRepositoryMock = Mockito.mock(BairroRepository.class);
		Mockito.when(bairroRepositoryMock.getBairros()).thenReturn(bairros);
		
		BairroService bairroService = new BairroService(bairroRepositoryMock);
		boolean condicao = bairroService.bairroExiste(Bairro.builder().nome("Bairro2").valorMetroQuadrado(BigDecimal.valueOf(190.00)).build());

		Assertions.assertTrue(condicao);
	}
	
	@Test
	public void bairroNaoDeveExistir() throws IOException {

		List<Bairro> bairros = new ArrayList<>();		
		bairros.add(Bairro.builder().nome("Bairro1").valorMetroQuadrado(BigDecimal.valueOf(200.00)).build());
		bairros.add(Bairro.builder().nome("Bairro2").valorMetroQuadrado(BigDecimal.valueOf(190.00)).build());				
		
		BairroRepository bairroRepositoryMock = Mockito.mock(BairroRepository.class);
		Mockito.when(bairroRepositoryMock.getBairros()).thenReturn(bairros);
		
		BairroService bairroService = new BairroService(bairroRepositoryMock);
		boolean condicao = bairroService.bairroExiste(Bairro.builder().nome("Bairro55").valorMetroQuadrado(BigDecimal.valueOf(190.00)).build());

		Assertions.assertFalse(condicao);
	}

	@Test
	public void bairroExisteException() throws IOException{



		BairroRepository bairroRepositoryMock = Mockito.mock(BairroRepository.class);
		Mockito.when(bairroRepositoryMock.getBairros()).thenThrow(new IOException());

		BairroService bairroService = new BairroService(bairroRepositoryMock);

		RuntimeException thrown = Assertions.assertThrows(
				RuntimeException.class,
				() -> bairroService.bairroExiste(Bairro.builder().nome("Bairro1").valorMetroQuadrado(BigDecimal.valueOf(190.00)).build())
		);


		Assertions.assertTrue(thrown.getMessage().contains("Erro ao buscar bairros"));
	}
}