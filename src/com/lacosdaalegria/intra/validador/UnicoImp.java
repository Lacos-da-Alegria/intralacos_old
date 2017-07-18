package com.lacosdaalegria.intra.validador;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.lacosdaalegria.intra.dao.VoluntarioDAO;
import com.lacosdaalegria.intra.model.Voluntario;

public class UnicoImp implements ConstraintValidator<UnicoInt, String> {

	@Override
	public void initialize(UnicoInt constraintAnotation) {
	}

	public boolean isValid(Voluntario value, ConstraintValidatorContext context) throws ClassNotFoundException {
		return eValido(value);
	}

	private boolean eValido(Voluntario value) throws ClassNotFoundException {

		VoluntarioDAO dao = new VoluntarioDAO();
		if (dao.login(value)) {
			return false;
		}
		return true;
	}

	@Override
	public boolean isValid(String arg0, ConstraintValidatorContext arg1) {
		// TODO Auto-generated method stub
		return false;
	}

}
