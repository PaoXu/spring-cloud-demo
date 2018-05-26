package demo.security;

//@Configuration
////@EnableOAuth2Sso  // OAuth2SsoDefaultConfiguration.class
//@EnableWebSecurity
public class WebSecurityConfig /*extends WebSecurityConfigurerAdapter*/ {
	
	
//    @Autowired
//    private JwtAuthenticationConfig config;
//
//    @Bean
//    public JwtAuthenticationConfig jwtConfig() {
//        return new JwtAuthenticationConfig();
//    }
    
//    @Override
//    protected void configure(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity
//            .authorizeRequests()
//                .antMatchers("/", "/home").permitAll()
//                .anyRequest().authenticated()
//                .and()    
//            .formLogin()
////                .loginPage("/login")
//                .permitAll()
//                .and()
//            .logout()
//                .permitAll()                   
//                .and()
//            .httpBasic();
    	
//    	 httpSecurity
//         .csrf().disable()
//         .logout().disable()
//         .formLogin().disable()
//         .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//         .and()
//             .anonymous()
//         .and()
//             .exceptionHandling().authenticationEntryPoint(
//                     (req, rsp, e) -> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED))
//         .and()
//             .addFilterAfter(new JwtTokenAuthenticationFilter(config),
//                     UsernamePasswordAuthenticationFilter.class)
//         .authorizeRequests()
//             .antMatchers(config.getUrl()).permitAll()
//             .antMatchers("/backend/admin").hasRole("ADMIN")
//             .antMatchers("/backend/user").hasRole("USER")
//.antMatchers("/backend/guest").permitAll();

//    }

//    // configure the global AuthenticationManager
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//            .inMemoryAuthentication()
//                .withUser("user").password("user").roles("USER")
//                .and().withUser("admin").password("admin").roles("USER","ADMIN");
//    }
}
