package com.SCM.Smart_Contact_Manager.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.SCM.Smart_Contact_Manager.services.UserDetailServiceSecurity;


@Configuration
public class SecurityConfig {

    @Autowired
    private UserDetailServiceSecurity userDetailServiceSecurity;


    // password encoder for decrypting my password that comes from database 
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

    @Autowired
    // it is my class that impelements oauth successfull handler 
    private oAuthSuccessHandler oAuthSuccessHandler;

    @Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		

	    // setting security configuration of my application 
		  http.csrf((csrf) -> csrf.disable()); // disable csrf 
		  http.authorizeHttpRequests((request) -> request
				  .requestMatchers("/User/**").authenticated()
				  .anyRequest().permitAll());  // enable security on some endppoint and free some find endpoits tfrom security with then help of this 
		  http.httpBasic(org.springframework.security.config.Customizer.withDefaults()); // osme basic authentication are using by default




          // setup customy login page and some aother setting to authenticate my user in login page 
          http.formLogin(formLogin ->{
            formLogin.loginPage("/login")  // setup my custom login page 
            .loginProcessingUrl("/authenticate")  // using default authenticate end Point to authenticate my user 
            .defaultSuccessUrl("/User/Dashboard" , true) // setting up my after successfull authentication login url that opens after login
            .failureUrl("/login?error=true") // if fails then it goes with this error endpoint 
            .usernameParameter("email") 
            .passwordParameter("password");

          });
          


          // enable my security to login with different multi platforms with the help of OAuth2 
          http.oauth2Login(oauth ->{
            oauth.loginPage("/login");
            oauth.successHandler(oAuthSuccessHandler); // object of my class
          });
           


          // enabling my security to login with my logout 
          http.logout(formLogout ->{
            formLogout.logoutUrl("/do-logout");
            formLogout.logoutSuccessUrl("/login?logout=true");

          });
		   
		    return http.build();
		 }


     // creating a hand coded user for practice only niothing some important 
//     @Bean
//     public UserDetailsService userDetailsService()
//     {
//         UserDetails user = User.withDefaultPasswordEncoder().username("shyam").password("shyam1234").build();
//         InMemoryUserDetailsManager inMemoryUserDetailsMAnager = new InMemoryUserDetailsManager(user);
//         return inMemoryUserDetailsMAnager;
//     }


@Bean
public AuthenticationProvider authenticationProvider()
{
     DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
     provider.setPasswordEncoder(bCryptPasswordEncoder);
     provider.setUserDetailsService(userDetailServiceSecurity);
     return provider;
}




}
