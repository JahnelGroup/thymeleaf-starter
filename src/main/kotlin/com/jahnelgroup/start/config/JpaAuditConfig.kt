package com.jahnelgroup.start.config

import com.jahnelgroup.start.service.context.DateTimeService
import com.jahnelgroup.start.service.context.UserContextService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.auditing.DateTimeProvider
import org.springframework.data.domain.AuditorAware
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import java.util.*

@Configuration
@EnableJpaAuditing(
        auditorAwareRef = "userContextProvider",
        dateTimeProviderRef = "dateTimeProvider")
class JpaAuditConfig {

    @Bean
    fun dateTimeProvider(dateTimeService: DateTimeService): DateTimeProvider {
        return DateTimeProvider {
            Optional.of(dateTimeService.getInstant())
        }
    }

    @Bean
    fun userContextProvider(userContextService: UserContextService): AuditorAware<String> {
        return AuditorAware<String>{
            Optional.of(userContextService.currentUsername())
        }
    }

}