package br.com.desafio_quality.service.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import br.com.desafio_quality.entity.Comodo;

public class ComodoTest {
	
	@Test
	public void deveRetornarTamanhoComodo() {
		
		Comodo comodo = Comodo.builder().nome("Quarto1").largura(3).comprimento(4).build();		
		Assertions.assertEquals(comodo.getTamanho(), 12.0);
	}
	
	@Test
	public void deveRetornarTamanhoIncorreto() {
		Comodo comodo = Comodo.builder().nome("Quarto1").largura(5).comprimento(4).build();
		Assertions.assertNotEquals(comodo.getTamanho(), 12.0);		
	}
}