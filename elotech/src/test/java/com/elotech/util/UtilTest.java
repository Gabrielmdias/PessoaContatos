package com.elotech.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class UtilTest {

	@Test
	void isCpfValidTest() {
		String cpf = "089.793.479-28";
		boolean result = Util.isCpfValid(cpf);
		assertTrue(result);
		cpf = "111.111.111-11";
		result = Util.isCpfValid(cpf);
		assertFalse(result);
	}
	
	@Test
	void isDateValidTest() {
		LocalDate data = LocalDate.now();
		boolean result = Util.isDateValid(data.plusDays(1));
		assertFalse(result);
		data = LocalDate.of(2020, 2, 28);
		result = Util.isDateValid(data);
		assertTrue(result);
	}
	
	@Test
	void isEmailValidTest() {
		boolean result = Util.isEmailValid("gabrieltest@gmail.com");
		assertTrue(result);
		result = Util.isCpfValid("gabrieltest@gmail");
		assertFalse(result);
		result = Util.isCpfValid("gabrieltest@");
		assertFalse(result);
	}
	
	
}
