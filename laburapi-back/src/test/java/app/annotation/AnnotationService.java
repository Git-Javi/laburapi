package app.annotation;

import app.annotation.constraint.Dni;
import app.annotation.constraint.Phone;

public interface AnnotationService {

	public String dnitester(@Dni String dniTest);
	
	public String telefonoTester(@Phone String telefonoTest);
	
}
