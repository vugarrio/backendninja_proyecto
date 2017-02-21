package es.ugarrio.backendninja_proyecto.component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


// Esta clase se va ejecutar siempre que se llame al controlador que la inyectemos.
// Con los mentodos podremos capturar el antes y despues.

// En este ejmplo la usamos para medir el tiempo de ejecucion del controlador.  
// Pero tambien se puede usar para guardar obtener y guardar en bbdd la ip del usuario, etc...

@Component("requestTimeInterceptor")
public class RequestTimeInterceptor extends HandlerInterceptorAdapter {
	
	private static final Log LOGGER = LogFactory.getLog(RequestTimeInterceptor.class);
		
	//Se ejecuta antes de entrar en el metodo del controlador
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		request.setAttribute("startTime", System.currentTimeMillis());
		// Al entrar en el controlador, guanda en el request una varaible nueva con el tiempo
		// actual
		return true;
		
	}
	
	
	//Se ejecuta antes de escupir la vista al navegador, al salir del metodo
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
		long startTime = (long)request.getAttribute("startTime");
		LOGGER.info("Url to: '" + request.getRequestURI().toString() + "' in '" + (System.currentTimeMillis() - startTime) + "' ms");
		
	}

}
