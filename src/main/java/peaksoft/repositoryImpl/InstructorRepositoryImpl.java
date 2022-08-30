package peaksoft.repositoryImpl;

import org.springframework.stereotype.Repository;
import peaksoft.model.Company;
import peaksoft.model.Course;
import peaksoft.model.Instructor;
import peaksoft.repository.InstructorRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class InstructorRepositoryImpl implements InstructorRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Instructor> getAllInstructor(Long id) {
        List<Instructor> instructors =
                entityManager.
                        createQuery("select c from Instructor c where c.id=:id", Instructor.class).
                        setParameter("id", id).getResultList();
        return instructors;
    }

    @Override
    public void addInstructor(Long id, Instructor instructor) {
        Company company = entityManager.find(Company.class, id);
        company.addInstructor(instructor);
        instructor.setCompany(company);
        entityManager.merge(instructor);

    }

    @Override
    public Instructor getById(Long id) {
        return entityManager.find(Instructor.class, id);
    }

    @Override
    public List<Instructor> getInstructorByCompanyId(Long id) {

        return entityManager.createQuery("select  c  from Instructor c where c.company.id=: id", Instructor.class)
                .setParameter("id", id).getResultList();
    }

    @Override
    public List<Instructor> getInstructorByCourseId(Long id) {
        return entityManager.find(Course.class, id).getInstructors();
    }

    @Override
    public void updateInstructor(Long id, Instructor instructor) {
        Instructor instructor1 = entityManager.find(Instructor.class, id);
        instructor1.setFirstName(instructor.getFirstName());
        instructor1.setLastName(instructor.getLastName());
        instructor1.setEmail(instructor.getEmail());
        instructor1.setPhoneNumber(instructor.getPhoneNumber());
        instructor1.setSpecialization(instructor.getSpecialization());
        entityManager.merge(instructor1);

    }

    @Override
    public void deleteInstructor(Long id) {
        Instructor instructor = entityManager.find(Instructor.class, id);
        for (Course c : instructor.getCourses()) {
            c.setInstructors(null);
        }
        entityManager.remove(instructor);

    }

    @Override
    public void assignInstructorToCourse(Long instrId, Long courseId) {
        Instructor instructor = entityManager.find(Instructor.class, instrId);
        Course course = entityManager.find(Course.class, courseId);
        instructor.addCourse(course);
        course.addInstructor(instructor);
        entityManager.merge(course);
    }
}
