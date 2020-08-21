package com.fayzal.springboot.datajpaactual.app;

import javax.sql.DataSource;

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
	/*Se pasa a la clase de configuracion principal y se inyectara aca
	 * @Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}*/
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	//Se inyecta el datasource para realizar la conexion a la base de datos
	@Autowired
	private DataSource dataSource;
	
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
		
		//codificador de la contraseña del usuario se mueve a la clase principal
		//PasswordEncoder encoder = this.passwordEncoder();
		PasswordEncoder encoder = passwordEncoder;
		
		//metodo de codificacion de la contraseña del usuario, se usa expresion lambda con los metodos ecoder::encode
		/*Este seria el metodo completo
		 * UserBuilder userL = User.builder().passwordEncoder(password ->{
		return encoder.encode(password);
		});*/
		/*Esto se remplaza para realizar la conexion por medio de la base de datos
		 * UserBuilder user = User.builder().passwordEncoder(encoder::encode);
		
		builder.inMemoryAuthentication()
		.withUser(user.username("admin").password("1234").roles("ADMIN","USER"))
		.withUser(user.username("fayzal").password("1234").roles("USER"));
		*/
		
		//Con esto se hace la conexion a la base de datos y se obtienen los usuarios y los roles
		builder
		.jdbcAuthentication()
		.dataSource(dataSource)
		.passwordEncoder(encoder)
		.usersByUsernameQuery("select username,password, enable from users where username=?")
		.authoritiesByUsernameQuery("select u.username, a.authority from authorities a inner join users u on (u.id=a.user_id) where u.username=?");
	}
	
}
