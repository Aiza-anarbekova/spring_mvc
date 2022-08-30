package peaksoft.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Lesson;
import peaksoft.model.Task;
import peaksoft.repository.*;

@Controller
@RequestMapping("/tasks")
public class TaskController {
    private final TaskRepositoryImpl taskRepository;
    private final VideoRepository videoRepository;
@Autowired
    public TaskController(TaskRepositoryImpl taskRepository,
                          VideoRepository videoRepository) {

        this.taskRepository = taskRepository;
        this.videoRepository = videoRepository;
    }
    @GetMapping("/allTasks/{id}")
    public String allTasks(@PathVariable Long id,
                           Model model,
                           @ModelAttribute("lesson") Lesson lesson){
        model.addAttribute("tasks",taskRepository.getById(id));
        model.addAttribute("task",taskRepository.getTaskByLessonId(id));
        model.addAttribute("videos",videoRepository.getVideoByLessonId(id));
        model.addAttribute("lesson",id) ;
        return "task/mainTask";
    }

    @GetMapping("/{id}/newTask")
    public String newTask(@PathVariable Long id, Model model){
        model.addAttribute("newTask",new Task());
        model.addAttribute("lessonId",id);
        return "task/newTask";

    }

    @PostMapping("/{id}/saveTask")
    public String saveTask(@ModelAttribute("newTask") Task task,
                             @PathVariable Long id ){
        taskRepository.addTask(id,task);
        return "redirect:/tasks/allTasks/ "+id;
    }
    @GetMapping("/{id}/edit")
    public String editTask(@PathVariable Long id, Model model) {
        Task task= taskRepository.getById(id);
        model.addAttribute("task",task);
        model.addAttribute("lessonId", task.getLesson().getId());
        return "task/editTask";
    }

    @RequestMapping("/{id}/{lessonId}/update")
    public String updateTask(@ModelAttribute("task")Task task
                            ,@PathVariable Long id,
                             @PathVariable ("lessonId") Long lessonId) {
        taskRepository.updateTask(id,task);
        return "redirect:/tasks/allTasks/ " + lessonId;

    }

    @RequestMapping("/{id}/{lessonId}/delete")
    public String delete(@PathVariable("id") Long id,
                         @PathVariable("lessonId") Long id2) {
        taskRepository.deleteTask(id);
        return "redirect:/tasks/allTasks/ " + id2;
    }
}
