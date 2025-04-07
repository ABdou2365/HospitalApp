package com.abdellah.hospitalapp;

import com.abdellah.hospitalapp.entities.Patient;
import com.abdellah.hospitalapp.repository.PatientRepository;
import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class HospitalAppApplication {

//    private final PatientRepository patientRepository;

//    public HospitalAppApplication(PatientRepository patientRepository) {
//
//        this.patientRepository = patientRepository;
//    }


    public static void main(String[] args) {
        SpringApplication.run(HospitalAppApplication.class, args);
    }

    //The bean annotation let us when we run the application all the methods
    // that has the bean annotation will be instantiated automatically  at first

//    @Bean
//    public CommandLineRunner start(){
//            return args -> {
//
//                // The first way how to add a Patient using the @NoArgsConstructor
//
//                Patient p1 = new Patient();
//                p1.setFirstName("Abdellah");
//                p1.setLastName("Moutawakkil");
//                p1.setDateOfBirth(new Date(2002,11,30));
//                p1.setScore(7);
//                p1.setSick(false);
//
//                patientRepository.save(p1);
//
//                // The second way how to add a Patient using the @AllArgsConstructor
//
//                Patient p2 = new Patient(null,"reda","kriate",new Date(2002,10,16),8,true);
//                patientRepository.save(p2);
//
//                // The third way using the @Builder lombok annotation
//
//                Patient p3 = Patient.builder().firstName("hamoushi").lastName("nessab").dateOfBirth(new Date(2002,11,17)).score(5).build();
//
//                patientRepository.save(p3);
//
//                List<Patient> patients = patientRepository.findAll();
//                // Displaying all the patients
//                patients.forEach(p -> System.out.println(p.toString()));
//            };
//    }
    @Bean
    public LayoutDialect layoutDialect() {
        return new LayoutDialect();
    }
}
