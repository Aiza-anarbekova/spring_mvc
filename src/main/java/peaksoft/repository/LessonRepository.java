package peaksoft.repository;

import peaksoft.model.Course;
import peaksoft.model.Lesson;

import java.util.List;

public interface LessonRepository {
    List<Lesson> getAllLesson();
    void addLesson(Long id,Lesson lesson);
    Lesson getById(Long id);
    List<Lesson> getLessonByCourseId(Long id);
    void updateLesson(Long id,Lesson lesson);
    void deleteLesson(Long id);
}
