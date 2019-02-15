@file:Suppress("SpringJavaInjectionPointsAutowiringInspection")

package com.jahnelgroup.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import javax.sql.DataSource
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect

/**
 * Expression-Based Access Control
 *      https://docs.spring.io/spring-security/site/docs/current/reference/htmlsingle/#el-access
 */
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

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
                .antMatchers("/css/**", "/js/**", "/images/**", "/login*").permitAll()
                .anyRequest().fullyAuthenticated()
        .and()
            .csrf().disable()
        .formLogin()
            .loginPage("/login.html")
            .defaultSuccessUrl("/")
        .and()
            .logout()
            .clearAuthentication(true)
            .invalidateHttpSession(true)
            .logoutSuccessUrl("/")
        .and().exceptionHandling().accessDeniedPage("/accessDenied.html")
    }
}