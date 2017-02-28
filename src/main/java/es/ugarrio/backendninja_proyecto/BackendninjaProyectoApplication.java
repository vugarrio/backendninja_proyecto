package es.ugarrio.backendninja_proyecto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling          // Le indicamos que se active TAREAS PERIODICAS con SPRING BATCH
public class BackendninjaProyectoApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendninjaProyectoApplication.class, args);
	}
}
