package peaksoft.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Company;
import peaksoft.repository.CompanyRepository;
import peaksoft.repository.CourseRepository;

@Controller
public class CompanyController {
    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyController(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @GetMapping("/allCompany")
    public String getCompaniesPage(Model model){
        model.addAttribute("allCompany",companyRepository.getAllCompanies());
        return "company/companyMainPage";
    }


      @GetMapping("/new")
      public String newCompany(Model model){
        model.addAttribute("newCompany",new Company());
        return "company/newCompany";
      }

      @PostMapping("/save")
      public String saveCompany(@ModelAttribute("newCompany")Company company){
        companyRepository.addCompany(company);
        return "redirect:/allCompany";
      }

    @GetMapping("/{id}/edit")
      public String editCompany(@PathVariable("id") Long id,Model model){
        model.addAttribute("company",companyRepository.getById(id));
        return "company/editCompany";

      }
      @PostMapping("/{id}/update")
      public String updateCompany(@ModelAttribute("company") Company company){
        companyRepository.updateCompany(company);
        return "redirect:/allCompany";

      }

      @PostMapping("{id}/delete")
      public String deleteCompany(@PathVariable("id") Long id){
        companyRepository.deleteCompany(companyRepository.getById(id));
        return "redirect:/allCompany";

      }

}
