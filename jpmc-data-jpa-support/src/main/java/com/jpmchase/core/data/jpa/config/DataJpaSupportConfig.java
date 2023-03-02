//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jpmchase.core.data.jpa.config;

import com.jpmchase.core.data.jpa.repository.helper.RepositoryHelper;
import com.jpmchase.core.data.jpa.repository.helper.RepositoryHelperTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ComponentScan.Filter;

@Configuration
@ComponentScan(
    basePackages = {"com.jpmchase.core.data.jpa.repository.helper", "com.jpmchase.core.data.jpa.beans"},
    excludeFilters = {@Filter(
    type = FilterType.REGEX,
    pattern = {".*Test.*"}
)}
)
public class DataJpaSupportConfig {
    public DataJpaSupportConfig() {
    }

    @Bean
    RepositoryHelper repositoryHelper() {
        return new RepositoryHelperTemplate();
    }
}
