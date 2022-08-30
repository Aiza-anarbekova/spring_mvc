package peaksoft.repository;

import peaksoft.model.Lesson;
import peaksoft.model.Task;
import peaksoft.model.Video;

import java.util.List;

public interface VideoRepository {
    List<Video> getAllVideo();
    void addVideo(Long id, Video video);
    Video getById(Long id);
    List<Video> getVideoByLessonId(Long id);
    void updateVideo(Long id,Video video);
    void deleteVideo(Long id);
}
