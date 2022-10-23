package dev.jpedrosnts.portifolio.config.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@Profile("prod")
public class WebSecurityConfigProd extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;

    public WebSecurityConfigProd(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/contato").permitAll()
                .antMatchers(HttpMethod.GET, "/admin/nova-senha").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().and()
                 .formLogin(form -> form
                 .loginPage("/admin/login")
                 .defaultSuccessUrl("/home", true)
                 .permitAll())
                 .logout(logout -> {
                 logout.logoutUrl("/admin/logout").logoutSuccessUrl("/admin/login");
                 })
                .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        auth.jdbcAuthentication()
                .dataSource(dataSource).usersByUsernameQuery(
                        "SELECT nm_email, nm_senha, TRUE FROM tb_usuario WHERE nm_email=?")
                .authoritiesByUsernameQuery(
                        "SELECT nm_email, 'ROLE_DEFAULT' FROM tb_usuario WHERE nm_email=?")
                .passwordEncoder(encoder);
    }

}
