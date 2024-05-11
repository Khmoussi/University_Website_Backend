package com.example.issatc;

import com.example.issatc.Entities.Responses.TeacherWithDepResponse;
import com.example.issatc.Infrastructure.EntityMappers.*;
import com.example.issatc.utilities.DataCleaning;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
@EnableScheduling // Enable Spring's scheduling capabilities

public class IssatcApplication {
private final DataCleaning dataCleaning;
private final JpaTeacherRepository jpaTeacherRepository;
	private final JpaGroupRepository groupRepository;
	private final JpaSubjectRepository subjectRepository;
	private final JpaSeanceRepository seanceRepository;


	public static void main(String[] args) {
		SpringApplication.run(IssatcApplication.class, args);
	}
@Bean
	public CommandLineRunner commandLineRunner(
			PasswordEncoder passwordEncoder
	) {

	return args -> {

	};

	}

}
