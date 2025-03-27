package com.abdellah.hospitalapp.web;


import com.abdellah.hospitalapp.entities.Patient;
import com.abdellah.hospitalapp.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PatientController {

    private static PatientRepository patientRepository;

    public PatientController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }



    @GetMapping("/AllPatients")
    public String allPatients(Model model) {
        List<Patient> patients = patientRepository.findAll();
        model.addAttribute("patients", patients);
        return "allPatients";
    }

    @GetMapping("/deletePatient")
    public String deletePatient(@RequestParam("id") Long id) {
        patientRepository.deleteById(id);
        // We use here the redirect because we want after the deletion we redirect to the main page (AllPatients)
        return "redirect:/AllPatients";

    }


}
