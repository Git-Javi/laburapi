package app.annotation;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import app.annotation.constraint.Dni;

@Validated
@Service
public class AnnotationServiceImpl implements AnnotationService {

	@Override
	public String dnitester(@Dni String dniTest) {

		return dniTest;
	}

}
