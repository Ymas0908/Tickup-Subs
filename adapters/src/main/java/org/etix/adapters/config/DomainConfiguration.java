package org.etix.adapters.config;

import com.itcentrex.domain.ddd.DomaineService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(basePackages = {"com.itcentrex.domain"},
        includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {DomaineService.class})}
)
public class DomainConfiguration {
}