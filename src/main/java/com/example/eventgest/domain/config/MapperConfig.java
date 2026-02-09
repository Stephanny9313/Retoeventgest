package com.example.eventgest.domain.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper(); // ← crear la instancia
        mapper.getConfiguration().setSkipNullEnabled(true);
        mapper.getConfiguration().setMatchingStrategy(org.modelmapper.convention.MatchingStrategies.STRICT);
        return mapper;
    }
}
