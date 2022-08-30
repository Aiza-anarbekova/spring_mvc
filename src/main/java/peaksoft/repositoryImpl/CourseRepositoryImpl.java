package peaksoft.repositoryImpl;

import org.springframework.stereotype.Repository;
import peaksoft.model.Company;
import peaksoft.model.Course;
import peaksoft.model.Instructor;
import peaksoft.repository.CourseRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CourseRepositoryImpl implements CourseRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Course> getAllCourses(Long id) {
        List<Course> courses =
                entityManager.
                        createQuery("select c from Course c where c.company.id=:id", Course.class).
                        setParameter("id", id).getResultList();
        return courses;
    }

    @Override
    public void addCourse(Long id, Course course) {
        Company company = entityManager.find(Company.class, id);
//        entityManager.merge(course);
        company.addCourse(course);
        course.setCompany(company);
        entityManager.merge(course);
    }

    @Override
    public List<Course> getCourseByCompanyId(Long id) {

        return entityManager.createQuery("select  c from Course c where c.company.id=:id", Course.class)
                .setParameter("id", id).getResultList();
    }

    @Override
    public Course getById(Long id) {
        return entityManager.find(Course.class, id);
    }

    @Override
    public void updateCourse(Long id, Course course) {
        Course course1 = getById(id);
        course1.setName(course.getName());
        course1.setDescription(course.getDescription());
        course1.setDuration(course.getDuration());
        course1.setDateOfStart(course.getDateOfStart());
        course1.setImage(course.getImage());
        entityManager.merge(course1);

    }

    @Override
    public void deleteCourse(Long id) {
        Course course = entityManager.find(Course.class, id);
        for (Instructor i : course.getInstructors()) {
            i.setCourses(null);
        }
        course.setCompany(null);
        entityManager.remove(course);
    }

    @Override
    public void assignInstructorToCourse(Long courseId, Long instrId) {
        Instructor instructor = entityManager.find(Instructor.class, instrId);
        Course course = entityManager.find(Course.class, courseId);
        instructor.addCourse(course);
        course.addInstructor(instructor);
        entityManager.persist(instructor);
        entityManager.persist(course);

    }
}
