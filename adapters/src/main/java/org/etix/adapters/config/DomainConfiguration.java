package org.etix.adapters.config;

import org.etix.domain.ddd.DomaineService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(basePackages = {"org.etix.domain"},
        includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {DomaineService.class})}
)
public class DomainConfiguration {
}