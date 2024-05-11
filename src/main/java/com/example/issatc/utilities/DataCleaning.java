package com.example.issatc.utilities;

import com.example.issatc.Infrastructure.EntityMappers.ResetPasswordMapper;
import com.example.issatc.Infrastructure.JpaResetPasswordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Component
public class DataCleaning {
    private  RetryTemplate retryTemplate;
    public double duration=1000*60*60*24*30;
    private final JpaResetPasswordRepository resetPasswordRepository;
public DataCleaning(JpaResetPasswordRepository jpaResetPasswordRepository){
    this.retryTemplate=new RetryTemplate();
    this.resetPasswordRepository=jpaResetPasswordRepository;
}
    @Scheduled(fixedDelay = 30,timeUnit = TimeUnit.DAYS)
    @Retryable(maxAttempts = 3, value = {Exception.class})
    public void cleanPasswordCodes() throws Exception {
        retryTemplate.execute(context -> {
            System.out.println("scheduled run");
            this.resetPasswordRepository.deleteAll();

            System.out.println("Cleaning succeeded");
            return null;
        });
    }

}
