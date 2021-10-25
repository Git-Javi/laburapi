package app.annotation.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneValidator implements ConstraintValidator<Phone, String> {

	@Override
	public boolean isValid(String numTelefono, ConstraintValidatorContext context) {
		
		return (numTelefono == null || numTelefono.matches("^[6|7|8|9][0-9]{7,8}$")) ? true : false;
	}

}
