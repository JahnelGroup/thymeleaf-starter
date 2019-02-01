package com.jahnelgroup.start.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry

@Configuration
class WebMvcConfig : WebMvcConfigurer{

    override fun addViewControllers(registry: ViewControllerRegistry) {
        // Add direct routes, no controller needed
        registry.addViewController("/index.html")
        registry.addViewController("/login.html")
        registry.addViewController("/accessDenied.html")
    }

}