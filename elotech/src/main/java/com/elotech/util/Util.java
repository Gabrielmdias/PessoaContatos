package com.elotech.util;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.caelum.stella.validation.CPFValidator;

public class Util {

	//NAO HA MAIS NECESSIDADE DE USAR ESSA CLASSE A VALIDACAO FOI REALIZADA PELO JPA e HIBERNATE VALIDATE
	
	private Util() {
		throw new IllegalStateException("Util class");
	}

	public static final Pattern VALID_EMAIL_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
			Pattern.CASE_INSENSITIVE);

	public static boolean isCpfValid(String cpf) {
		cpf = cpf.replaceAll("\\D", "");
		CPFValidator cpfValidator = new CPFValidator();
		return cpfValidator.invalidMessagesFor(cpf).isEmpty();
	}

	public static boolean isDateValid(LocalDate data) {
		LocalDate hoje = LocalDate.now();
		return data.compareTo(hoje) <= 0;
	}

	public static boolean isEmailValid(String email) {
		Matcher matcher = VALID_EMAIL_REGEX.matcher(email);
		return matcher.find();
	}

}
