package app.annotation.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DniValidator implements ConstraintValidator<Dni, String>{

	@Override
	public boolean isValid(String dni, ConstraintValidatorContext context) {
		
		return  (dni != null && dni.matches("\\d{8}[A-HJ-NP-TV-Z]")) ? true : false;
	}

}
