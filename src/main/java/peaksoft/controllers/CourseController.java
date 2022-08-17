package peaksoft.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CourseController {
    @GetMapping("/hello")
    public String allCourses(){
        return "courses/getAllCourses";
    }
}
