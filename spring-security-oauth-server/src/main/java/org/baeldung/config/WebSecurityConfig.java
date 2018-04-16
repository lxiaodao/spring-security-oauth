package org.baeldung.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
//@EnableAutoConfiguration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
	
	
	/*@Primary
	@ConfigurationProperties(prefix = "sso.datasource")
	public DataSourceProperties ssoDataSourceProperties() {
		return new DataSourceProperties();
	}

	@Primary
    @Bean(name = "ssoDataSource")
	public DataSource ssoDataSource() {
		// return DataSourceBuilder.create().build();
		// .type(HikariDataSource.class)
		return ssoDataSourceProperties().initializeDataSourceBuilder().build();
	}*/
	
	@Autowired
	private DataSource dataSource;
	//TODO: 相关AuthenticationManager的实现要使用数据库的操作方式替换
    @Autowired
    public void globalUserDetails(final AuthenticationManagerBuilder auth) throws Exception {
        // @formatter:off
    
	auth.inMemoryAuthentication()
	  .withUser("john").password("123").roles("USER").and()
	  .withUser("tom").password("111").roles("ADMIN").and()
	  .withUser("user1").password("pass").roles("USER").and()
	  .withUser("admin").password("nimda").roles("ADMIN");
	
	
    
    }// @formatter:on
    
    
   /* @Autowired
    @Bean
	public AuthenticationManager configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
    	
        auth.jdbcAuthentication().dataSource(dataSource)
		.usersByUsernameQuery("select username,password, enabled from user where username=?")
		.authoritiesByUsernameQuery("select username, role from user_role where username=?");
        return auth.build();
    }
 */
   

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
    
    
  

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        // @formatter:off
		http.authorizeRequests().antMatchers("/login").permitAll()
		.antMatchers("/oauth/token/revokeById/**").permitAll()
		.antMatchers("/tokens/**").permitAll()
		.anyRequest().authenticated()
		.and().formLogin().permitAll()
		.and().csrf().disable();
		// @formatter:on
    }

}
