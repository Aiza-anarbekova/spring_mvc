package peaksoft.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Lesson;
import peaksoft.repository.LessonRepository;

@Controller
@RequestMapping("lesson")
public class LessonController {
    private final LessonRepository lessonRepository;

    public LessonController(LessonRepository lessonRepository) {

        this.lessonRepository = lessonRepository;
    }

    @GetMapping("/{id}/new")
    public String newLesson(@PathVariable Long id, Model model) {
        model.addAttribute("newLesson", new Lesson());
        model.addAttribute("courseId", id);
        return "/lessons/newLesson";

    }

    @PostMapping("/saveLesson/{id}")
    public String saveLesson(@ModelAttribute("newLesson") Lesson lesson,
                             @PathVariable Long id) {
        lessonRepository.addLesson(id, lesson);
        return "redirect:/students/allStudents/ " + id;
    }

    @GetMapping("/{id}/edit")
    public String editLesson(@PathVariable("id") Long id, Model model) {
        Lesson lesson = lessonRepository.getById(id);
        model.addAttribute("lesson", lesson);
        model.addAttribute("courseId", lesson.getCourse().getId());
        return "lessons/saveToUpdate";
    }

    @RequestMapping("/{id}/{courseId}/update")
    public String updateLesson(@ModelAttribute("lesson") Lesson lesson
            , @PathVariable Long id,
                               @PathVariable("courseId") Long courseId) {
        lessonRepository.updateLesson(id, lesson);
        return "redirect:/students/allStudents/ " + courseId;

    }

    @RequestMapping("/{id}/{courseId}/delete")
    public String delete(@PathVariable("id") Long id,
                         @PathVariable("courseId") Long id2) {
        lessonRepository.deleteLesson(id);
        return "redirect:/students/allStudents/ " + id2;
    }
}
