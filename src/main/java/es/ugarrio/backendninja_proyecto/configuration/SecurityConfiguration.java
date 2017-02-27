package es.ugarrio.backendninja_proyecto.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity // Con esta anotación ACTIVADMOS la Seguridad Web
@EnableGlobalMethodSecurity(prePostEnabled=true)  // Indicamos que utilizaremos anotaciones para securizar metodos en los controles
                                                  // como @PreAutorize
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	//Inyectamos el sercio de segurdiad
	@Autowired
	@Qualifier("userService")
	private UserDetailsService userService;
	
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception  {
		auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		    .antMatchers("/css/*", "/img/*").permitAll() // El contendio de los direcotrios se pueden mostrar a todos./
		
		    .anyRequest().authenticated()    // El resto de las request tienen que esatr autorizadas
		    
		    .and()
		    
				 .formLogin().loginPage("/login")     // La pagina que contiene el login
							 .loginProcessingUrl("/logincheck")  // Pagina que procesa el login
							 .usernameParameter("username")
							 .passwordParameter("password")
							 .defaultSuccessUrl("/loginsuccess")  // Url a la que se redirige despues de logarse
							 .permitAll()  // El acceso al login estan permitidos todos
			 
			 .and()
			 
				 .logout().logoutUrl("/logout")        // Url donde se procesa el logout
				          .logoutSuccessUrl("/login?logout")   // Url donde se redirige despues de hacer logout
				          .permitAll();  // El acceso al logout estan permitidos todos
	} 
	
	
	
	

}
