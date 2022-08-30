package peaksoft.repository;

import org.springframework.stereotype.Repository;
import peaksoft.model.Lesson;
import peaksoft.model.Task;

import java.util.List;
@Repository
public interface TaskRepositoryImpl {
    List<Task> getAllTask(Long id);
    void addTask(Long id,Task task);
    Task getById(Long id);
    List<Task> getTaskByLessonId(Long id);
    void updateTask(Long id,Task task);
    void deleteTask(Long id);
}
