package peaksoft.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Course;
import peaksoft.model.Instructor;
import peaksoft.repository.*;

@Controller
@RequestMapping("/courses")
public class CourseController {
    private final CourseRepository courseRepository;
    private final InstructorRepository instructorRepository;

    private final StudentRepository studentRepository;

    @Autowired
    public CourseController(
            CourseRepository courseRepository,
            InstructorRepository instructorRepository,
            StudentRepository studentRepository) {
        this.courseRepository = courseRepository;
        this.instructorRepository = instructorRepository;
        this.studentRepository = studentRepository;
    }

    @GetMapping("/allCourses/{id}")
    public String allCourses(@PathVariable Long id, Model model, @ModelAttribute("inst") Instructor instructor) {
        model.addAttribute("allCourses", courseRepository.getAllCourses(id));
        model.addAttribute("companyId", id);
        model.addAttribute("instructors", instructorRepository.getInstructorByCompanyId(id));
        model.addAttribute("instructor", instructorRepository.getById(id));
        model.addAttribute("students", studentRepository.getStudentByCompanyId(id));
        return "courses/CompanyInner";
    }

    @GetMapping("{id}/new")
    public String addCourse(@PathVariable Long id, Model model) {
        model.addAttribute("newCourse", new Course());
        model.addAttribute("companyId", id);
        System.out.println("method is working with company id" + id);
        return "courses/newCourse";
    }

    @PostMapping("{id}/saveCourse")
    public String save(@ModelAttribute("newCourse") Course course,
                       @PathVariable Long id) {
        courseRepository.addCourse(id, course);
        return "redirect:/courses/allCourses/" + id;
    }

    @GetMapping("/{id}/edit")
    public String editCourse(@PathVariable("id") Long id, Model model) {
        Course course = courseRepository.getById(id);
        model.addAttribute("course", course);
        model.addAttribute("comId", course.getCompany().getId());
        return "courses/edit";
    }

    @RequestMapping("/{id}/{comId}/update")
    public String updateCourse(@ModelAttribute("course") Course course
                             , @PathVariable Long id,
                               @PathVariable("comId") Long comId) {
        courseRepository.updateCourse(id, course);
        return "redirect:/courses/allCourses/ " + comId;

    }

    @RequestMapping("/{id}/{companyId}/delete")
    public String delete(@PathVariable("id") Long id,
                         @PathVariable("companyId") Long id2) {
        courseRepository.deleteCourse(id);
        return "redirect:/courses/allCourses/ " + id2;
    }

    @PostMapping("/{companyId}/{courseId}/saveAssign")
    private String saveAssign(@PathVariable("courseId") Long courseId,
                              @ModelAttribute("inst") Instructor instructor,
                              @PathVariable("companyId") Long compId) {
        instructorRepository.assignInstructorToCourse(instructor.getId(), courseId);
        return "redirect:/courses/allCourses/" + compId;
    }
}
