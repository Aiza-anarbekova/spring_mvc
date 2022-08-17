package peaksoft.dao;

import org.springframework.stereotype.Repository;
import peaksoft.model.Course;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
@Repository
@Transactional
public class CourseDaoImpl implements CourseDao{
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Course> getAllCourses() {
        List<Course> courses =
                entityManager.createQuery("select c from Course c",Course.class).getResultList();
        return courses;
    }

    @Override
    public void addCourse(Course course) {

    }

    @Override
    public Course getById(long id) {
        return null;
    }

    @Override
    public void updateCourse(Course course) {

    }

    @Override
    public void deleteCourse(Course course) {

    }
}
