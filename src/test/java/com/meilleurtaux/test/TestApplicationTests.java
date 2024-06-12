package com.meilleurtaux.test;

import org.assertj.core.api.Assertions;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.meilleurtaux.test.dto.CommuneDto;
import com.meilleurtaux.test.exception.CommuneNotFoundException;
import com.meilleurtaux.test.service.ApiGouvService;



@SpringBootTest
class TestApplicationTests {

	@Autowired
	ApiGouvService apiGouvService;

	@Test
	void testCommuneNotFoundException() {
		assertThrows(CommuneNotFoundException.class, () -> apiGouvService.getCommunes("99999"));
	}
	
	
	@Test
	void testParisResults() {
		List<CommuneDto> communes = apiGouvService.getCommunes("75020");
		 Assertions.assertThat(communes)
         .isNotNull()
         .element(0)
         .hasFieldOrPropertyWithValue("nom","Paris");

	}
	
	@Test
	void testRombachResults() {		
		List<CommuneDto> communes = apiGouvService.getCommunes("68660");
		Assertions.assertThat(communes)
        .isNotNull().hasSize(2);

	}
}
