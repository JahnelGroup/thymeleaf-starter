package com.jahnelgroup.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.data.repository.query.SecurityEvaluationContextExtension

@Configuration
class JpaExtensionConfig {

    // https://www.baeldung.com/spring-data-security
    // https://docs.spring.io/spring-security/site/docs/5.0.5.RELEASE/reference/html5/#data-configuration
    @Bean
    fun securityEvaluationContextExtension() = SecurityEvaluationContextExtension()

}