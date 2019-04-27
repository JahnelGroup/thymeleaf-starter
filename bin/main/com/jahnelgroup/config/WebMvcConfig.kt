package com.jahnelgroup.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebMvcConfig : WebMvcConfigurer{

    override fun addViewControllers(registry: ViewControllerRegistry) {
        // Add direct routes, no controller needed
        registry.addViewController("/login.html")
        registry.addViewController("/denied.html")
    }

}