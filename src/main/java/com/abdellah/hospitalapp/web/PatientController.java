package com.abdellah.hospitalapp.web;


import com.abdellah.hospitalapp.entities.Patient;
import com.abdellah.hospitalapp.repository.PatientRepository;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
            ,@RequestParam(name = "size",defaultValue = "5")int size
            ,@RequestParam(name = "keyword", defaultValue = "") String keyword
    )
    {

        // Use PageRequest when you need to create a pageable object manually

        Page<Patient> patients = patientRepository.findByFirstNameContainsIgnoreCaseOrLastNameContainsIgnoreCase(keyword,keyword,PageRequest.of(page,size));
        model.addAttribute("patients", patients.getContent());
        model.addAttribute("totalPages", new int[patients.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "allPatients";
    }

    @GetMapping("/deletePatient")
    public String deletePatient(@RequestParam("id") Long id,String keyword, int page) {
        patientRepository.deleteById(id);
        // We use here the redirect because we want after the deletion we redirect to the main page (AllPatients)
        return "redirect:/AllPatients?page="+page+"&keyword="+keyword;

    }

    @GetMapping("/EditPatient")
    public String editPatient(@RequestParam("id") Long id, Model model) {
        Patient patient = patientRepository.findById(id).get();
        model.addAttribute("patient", patient);
        return "editPatient";
    }




    // ON ajoute un objet vide Patient au modèle,
    // ce qui permet à Thymeleaf de le remplir dans le formulaire (name="")
    @GetMapping("/AddPatient")
    public String addPatient(Model model) {
        model.addAttribute("patient", new Patient());
        return "formPatient";
    }

    @PostMapping("/savePatient")
    public String savePatient(@Valid Patient patient, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "formPatient";
        }
        patientRepository.save(patient);
        return "redirect:/AllPatients";
    }
}
