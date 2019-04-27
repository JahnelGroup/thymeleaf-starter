@file:Suppress("SpringJavaInjectionPointsAutowiringInspection")

package com.jahnelgroup.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect
import javax.sql.DataSource

@Configuration
@EnableWebSecurity
class WebSecurityConfig(var dataSource: DataSource) : WebSecurityConfigurerAdapter() {

    /**
     * This would normally come from:
     *      org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl.DEF_GROUP_AUTHORITIES_BY_USERNAME_QUERY
     *
     * but MySQL doesn't support a table with the name `groups` so we've renamed to `user_groups`
     */
    val DEF_GROUP_AUTHORITIES_BY_USERNAME_QUERY = ("select g.id, g.group_name, ga.authority "
            + "from user_groups g, user_group_members gm, user_group_authorities ga "
            + "where gm.username = ? " + "and g.id = ga.group_id "
            + "and g.id = gm.group_id")

    @Value("\${app.security.password-encoder.security-strength}")
    var securityStrength: Int = -1

    // Enabled sec: tag for Thymeleaf
    // https://github.com/thymeleaf/thymeleaf-extras-springsecurity/issues/61
    // https://github.com/thymeleaf/thymeleaf-extras-springsecurity#configuration
    @Bean
    fun securityDialect(): SpringSecurityDialect {
        return SpringSecurityDialect()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder(securityStrength)
    }

    @Throws(Exception::class)
    override fun configure(auth: AuthenticationManagerBuilder) {
        // https://docs.spring.io/spring-security/site/docs/current/reference/htmlsingle/#appendix-schema
        // To debug set breakpoints in JdbcDaoImpl.java
        var jdbcAuth = auth.jdbcAuthentication().groupAuthoritiesByUsername(DEF_GROUP_AUTHORITIES_BY_USERNAME_QUERY)
        jdbcAuth.dataSource(dataSource).passwordEncoder(passwordEncoder())
    }

    /**
     * This is needed to prevent the InMemoryDatabase auto-configuration from loading. This only occurs when @EnableGlobalSecurity is turned on.
     */
    @Bean
    fun authenticationManagerBean2() = super.authenticationManagerBean()

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
                .antMatchers("/css/**", "/js/**", "/images/**", "/login*").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().fullyAuthenticated()
        .and()
                .csrf().ignoringAntMatchers("/api/**")
                // https://docs.spring.io/spring-security/site/docs/current/reference/htmlsingle/#csrf-logout
                // .csrf().disable()
                .and()
        .formLogin()
                // TODO: remove .html
            .loginPage("/login.html")
            .defaultSuccessUrl("/")
            .failureHandler { request, response, exception ->
                var errMsg = "Something went wrong."
                if (exception::class.java.isAssignableFrom(BadCredentialsException::class.java)) {
                    errMsg = "Invalid username or password."
                }
                request.getSession().setAttribute("loginMessage", errMsg)
                response.sendRedirect("/login.html")
            }

        .and()
            .logout()
            .clearAuthentication(true)
            .invalidateHttpSession(true)
            .logoutSuccessUrl("/")
                // TODO: remove .html
        .and().exceptionHandling().accessDeniedPage("/denied.html")
    }
}