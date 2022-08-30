package peaksoft.repositoryImpl;

import org.springframework.stereotype.Repository;
import peaksoft.model.Course;
import peaksoft.model.Lesson;
import peaksoft.repository.LessonRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class LessonRepositoryImpl implements LessonRepository {
    @PersistenceContext
    private EntityManager entityManager ;
    @Override
    public List<Lesson> getAllLesson() {
        List<Lesson> lessons = entityManager.createQuery("select c from Lesson c").getResultList();
        return lessons;
    }

    @Override
    public void addLesson(Long id, Lesson lesson) {
        Course course = entityManager.find(Course.class,id);
        course.addLesson(lesson);
        lesson.setCourse(course);
        entityManager.merge(lesson);

    }

    @Override
    public Lesson getById(Long id) {
        return entityManager.find(Lesson.class,id);
    }

    @Override
    public List<Lesson> getLessonByCourseId(Long id) {
        return entityManager.createQuery("select c from  Lesson c where c.course.id=:id", Lesson.class).
                setParameter("id", id).getResultList();

    }
    @Override
    public void updateLesson(Long id,Lesson lesson) {
        Lesson lesson1=entityManager.find(Lesson.class,id);
        lesson1.setLessonName(lesson.getLessonName());
        lesson1.setId(lesson.getId());
        entityManager.merge(lesson1);

    }

    @Override
    public void deleteLesson(Long id) {
        Lesson lesson = entityManager.find(Lesson.class,id);
      lesson.setCourse(null);
      entityManager.remove(lesson);
    }
}
