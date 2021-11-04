package app.annotation;

import app.annotation.constraint.Dni;

public interface AnnotationService {

	public String dnitester(@Dni String dniTest);
	
}
