package peaksoft.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Video;
import peaksoft.repository.VideoRepository;

@Controller
@RequestMapping("/video")
public class VideoController {
    private final VideoRepository videoRepository;

    @Autowired
    public VideoController(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;

    }

    @GetMapping("/{id}/new")
    public String newVideo(@PathVariable Long id, Model model) {
        model.addAttribute("newVideo", new Video());
        model.addAttribute("lessonId", id);
        return "/videos/newVideo";

    }

    @PostMapping("/saveVideo/{id}")
    public String saveLesson(@ModelAttribute("newVideo") Video video,
                             @PathVariable Long id) {
        videoRepository.addVideo(id, video);
        return "redirect:/tasks/allTasks/ " + id;
    }

    @GetMapping("/{id}/edit")
    public String editVideo(@PathVariable("id") Long id, Model model) {
        Video video = videoRepository.getById(id);
        model.addAttribute("videoId", video);
        model.addAttribute("lessonId", video.getLesson().getId());
        return "videos/editVideo";
    }

    @RequestMapping("/{id}/{lessonId}/update")
    public String updateVideo(@ModelAttribute("video") Video video
            , @PathVariable Long id,
                              @PathVariable("lessonId") Long lessonId) {
        videoRepository.updateVideo(id, video);
        return "redirect:/tasks/allTasks/ " + lessonId;

    }

    @RequestMapping("/{id}/{lessonId}/delete")
    public String delete(@PathVariable("id") Long id,
                         @PathVariable("lessonId") Long id2) {
        videoRepository.deleteVideo(id);
        return "redirect:/tasks/allTasks/ " + id2;
    }
}
