package app;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(value = SpringRunner.class) // Habilita la ejecución de JUnit usando el soporte de prueba de Spring (Anotaciones etc...)
@SpringBootTest(classes = Application.class) // Carga el ApplicationContext que se utilizará en nuestras pruebas.
public class LaburapiApplicationTests {
	
	@Test
	public void contextLoads() {}
}
