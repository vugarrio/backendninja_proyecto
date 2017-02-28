package es.ugarrio.backendninja_proyecto.batch;

import java.util.Date;


import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component("tareaPeriodicaComponent")
public class TareaPeriodicaComponent {
	
	private static final Logger LOGGER = Logger.getLogger(TareaPeriodicaComponent.class);
	
	@Scheduled(fixedDelay = 60000) // Le indicamos que lo ejecute con SPRING BATCH cada 60 segundos
	public void doTask() {
		LOGGER.info("TAREA PERIODICA EJECUTADA POR 'SPRING BATCH' -- TIME IS: " + new Date());
	}
	
}
