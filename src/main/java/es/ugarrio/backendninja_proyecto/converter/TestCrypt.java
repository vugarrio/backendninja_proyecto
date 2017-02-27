package es.ugarrio.backendninja_proyecto.converter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestCrypt {
	
	
	//Aqui podemos ver cualquier password encriptada de cualquier cadena
	public static void main(String[] args) {
		String password = "user";
		BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
		System.out.println(password + " = " + pe.encode(password));
	}
	
}
