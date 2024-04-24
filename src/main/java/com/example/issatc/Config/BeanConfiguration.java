package com.example.issatc.Config;

import com.example.issatc.Infrastructure.AuthenticationRepositoryImpl;
import com.example.issatc.Infrastructure.EntityMappers.Implementation.DataRepositoryImpl;
import com.example.issatc.Ports.AuthenticationServicePort;
import com.example.issatc.Ports.DataServicePort;
import com.example.issatc.UseCases.AuthenticationService;
import com.example.issatc.UseCases.DataService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    AuthenticationServicePort authenticationServiceAdapter(AuthenticationRepositoryImpl authenticationRepository) {
        return new AuthenticationService(authenticationRepository);
    }
@Bean
    DataServicePort dataServicePort(DataRepositoryImpl dataRepository){
        return new DataService(dataRepository);
}

}