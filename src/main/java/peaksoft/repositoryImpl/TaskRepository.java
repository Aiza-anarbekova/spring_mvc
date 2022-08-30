package peaksoft.repositoryImpl;

import org.springframework.stereotype.Repository;
import peaksoft.model.Lesson;
import peaksoft.model.Task;
import peaksoft.repository.TaskRepositoryImpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class TaskRepository implements TaskRepositoryImpl {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Task> getAllTask(Long id) {

        return entityManager.createQuery("select c from Task c where c.id=:id").setParameter("id",id).getResultList();
    }

    @Override
    public void addTask(Long id, Task task) {
        Lesson lesson = entityManager.find(Lesson.class, id);
        lesson.addTask(task);
        task.setLesson(lesson);
        entityManager.merge(task);
    }

    @Override
    public Task getById(Long id) {
        return entityManager.find(Task.class, id);

    }

    @Override
    public List<Task> getTaskByLessonId(Long id) {

        return entityManager.createQuery("select  c from  Task c where c.lesson.id=:id", Task.class)
                .setParameter("id", id).getResultList();
    }

    @Override
    public void updateTask(Long id, Task task) {
        Task task1 = entityManager.find(Task.class, id);
        task1.setId(task.getId());
        task1.setName(task.getName());
        task1.setDeadline(task.getDeadline());
        task1.setTask(task.getTask());
        entityManager.merge(task1);

    }

    @Override
    public void deleteTask(Long id) {
        Task task = entityManager.find(Task.class, id);
        task.setLesson(null);
        entityManager.remove(task);

    }
}
