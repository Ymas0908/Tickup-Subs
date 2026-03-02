package org.tickup.adapters.config;

import org.tickup.domain.ddd.DomaineService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(basePackages = {"org.tickup.domain"},
        includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {DomaineService.class})})
public class DomainConfiguration {
}
