package es.ugarrio.backendninja_proyecto.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import es.ugarrio.backendninja_proyecto.component.RequestTimeInterceptor;


// Clase de configuración de Spring. Se ejecuta al iniciar.
@Configuration
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {
	
	//Inyectamos la clase requestTimeInterceptor:
	@Autowired
	@Qualifier("requestTimeInterceptor")
	private RequestTimeInterceptor requestTimeInterceptor;
	
	// La añadimos a configuracion para que cada vez que se ejecute
	// un metodo del controlador, esta se ejecute
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(requestTimeInterceptor);
	}

	
	
}
