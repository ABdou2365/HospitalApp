package com.abdellah.hospitalapp.web;


import com.abdellah.hospitalapp.entities.Patient;
import com.abdellah.hospitalapp.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public String allPatients(Model model
            ,@RequestParam(name = "page",defaultValue = "0") int page
            ,@RequestParam(name = "size",defaultValue = "5")int size)
    {
        Page<Patient> patients = patientRepository.findAll(PageRequest.of(page,size));
        model.addAttribute("patients", patients.getContent());
        model.addAttribute("totalPages", new int[patients.getTotalPages()]);
        model.addAttribute("currentPage", page);
        return "allPatients";
    }

    @GetMapping("/deletePatient")
    public String deletePatient(@RequestParam("id") Long id) {
        patientRepository.deleteById(id);
        // We use here the redirect because we want after the deletion we redirect to the main page (AllPatients)
        return "redirect:/AllPatients";

    }


}
