package com.fayzal.springboot.datajpaactual.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fayzal.springboot.datajpaactual.app.auth.handler.LoginSuccessHandler;
//Anotacion para habilitar anotaciones de seguridad a los metodos en vez de hacerlo en esta clase @Secured(value ={"ROLE_XX,ROLE_XX1"})
//Con el atributo prePostEnable, tambien se puede hacer autenticacion de roles, pero usan la anotacion @PreAuthorize(hasRole('ROLE_XXX'))
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true)
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter  {
	
	@Autowired
	private LoginSuccessHandler successHandler;
	
	//Bean para la configuracion del encrypt del password
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//Configuracion de permisos para las urls segun el perfil
		http.authorizeRequests().antMatchers("/","/css/**","/js/**","/images/**","/listar","/login").permitAll()
		/* Se comento para implementar la seguridad a nivel de los controlladores y accesos a las rutas en las clases
		 * Usando @EnableGlobalMethodSecurity(securedEnabled = true)
		 * .antMatchers("/ver/**").hasAnyRole("USER")
		.antMatchers("/uploads/**").hasAnyRole("USER")
		.antMatchers("/form/**").hasAnyRole("ADMIN")
		.antMatchers("/eliminar/**").hasAnyRole("ADMIN")
		.antMatchers("/factura/**").hasAnyRole("ADMIN")*/
		.anyRequest().authenticated()
		//COnfiguracion del formulario de login de la app yel logout
		.and()
		//si se desea poner pagina de login propia se crea el controlador y la vista y se mapea con .loginpage("ruta")
		.formLogin()
			.successHandler(successHandler)
			.loginPage("/login")
			.permitAll()
		//.formLogin()
		.and()
		.logout().permitAll()
		.and()
		.exceptionHandling().accessDeniedPage("/error_403");
		
	}
	
	/* metodo de implementacion de usuarios y roles en la plataforma, se cargan en memoria,
	 * se pueden implementar con jpa y jdbc*/
	@Autowired
	public void configurerGlobal(AuthenticationManagerBuilder builder) throws Exception{
		
		//codificador de la contraseña del usuario 
		PasswordEncoder encoder = this.passwordEncoder();
		
		//metodo de codificacion de la contraseña del usuario, se usa expresion lambda con los metodos ecoder::encode
		/*Este seria el metodo completo
		 * UserBuilder userL = User.builder().passwordEncoder(password ->{
		return encoder.encode(password);
		});*/
		UserBuilder user = User.builder().passwordEncoder(encoder::encode);
		
		builder.inMemoryAuthentication()
		.withUser(user.username("admin").password("1234").roles("ADMIN","USER"))
		.withUser(user.username("fayzal").password("1234").roles("USER"));
		
	}
	
}
