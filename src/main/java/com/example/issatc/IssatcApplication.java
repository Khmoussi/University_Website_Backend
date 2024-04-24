package com.example.issatc;

import com.example.issatc.Entities.Responses.TeacherWithDepResponse;
import com.example.issatc.Infrastructure.EntityMappers.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class IssatcApplication {

private final JpaTeacherRepository jpaTeacherRepository;
	private final JpaGroupRepository groupRepository;
	private final JpaSubjectRepository subjectRepository;

	public static void main(String[] args) {
		SpringApplication.run(IssatcApplication.class, args);
	}
@Bean
	public CommandLineRunner commandLineRunner(
			PasswordEncoder passwordEncoder
	) {
	/*List<TeacherWithDepResponse> teacherWithDepResponse= this.jpaTeacherRepository.getTeacherWithDep();
	for(int i=0 ;i<teacherWithDepResponse.size();i++)
	{
		System.out.println("aaaaa");

		System.out.println(teacherWithDepResponse.get(i));
	}
*/
	List<TeacherWithDepResponse> chefDepWithDepNameResponse=this.jpaTeacherRepository.getAllTeachers();

	//this.groupRepository.save(new GroupMapper("Group 1"));
	return args -> {

	};

	}
}
