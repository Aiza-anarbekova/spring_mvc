package peaksoft.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Course;
import peaksoft.model.Instructor;
import peaksoft.repository.CompanyRepository;
import peaksoft.repository.CourseRepository;
import peaksoft.repository.InstructorRepository;

import java.util.List;

@Controller
@RequestMapping("/instructors")
public class InstructorController {
    private final InstructorRepository instructorRepository;
    private final CourseRepository courseRepository;


    @Autowired
    public InstructorController(InstructorRepository instructorRepository, CourseRepository courseRepository) {
        this.instructorRepository = instructorRepository;
        this.courseRepository = courseRepository;

    }
    @ModelAttribute("allCourses")
    public List<Course> courses(Long id){
        return courseRepository.getAllCourses(id);
    }

    @GetMapping("/{id}/new")
    public String newIns(@PathVariable Long id, Model model){
        model.addAttribute("newInstructor",new Instructor());
        model.addAttribute("comId",id);
        return "/instructors/newInstructor";

    }

    @PostMapping("/{id}/save")
    public String saveIns(@ModelAttribute("newInstructor") Instructor instructor,
                          @PathVariable Long id ){
        instructorRepository.addInstructor(id,instructor);
        return "redirect:/courses/allCourses/"+id;
    }
    @GetMapping("/{id}/edit")
    public String editIns(@PathVariable("id") Long id, Model model) {
        Instructor instructor = instructorRepository.getById(id);
        model.addAttribute("ins",instructor);
        model.addAttribute("comId", instructor.getCompany().getId());
        return "instructors/editInst";
    }

    @RequestMapping("/{id}/{comId}/update")
    public String updateIns(@ModelAttribute("ins") Instructor instructor
                           ,@PathVariable Long id,
                            @PathVariable("comId") Long comId) {
        instructorRepository.updateInstructor(id,instructor);
        return "redirect:/courses/allCourses/ " + comId;

    }

    @RequestMapping("/{id}/{companyId}/delete")
    public String delete(@PathVariable("id") Long id,
                         @PathVariable("companyId") Long id2) {
        instructorRepository.deleteInstructor(id);
        return "redirect:/courses/allCourses/ " + id2;
    }



}
