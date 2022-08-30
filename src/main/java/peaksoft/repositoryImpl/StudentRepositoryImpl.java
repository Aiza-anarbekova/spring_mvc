package peaksoft.repositoryImpl;

import org.springframework.stereotype.Repository;
import peaksoft.model.Company;
import peaksoft.model.Course;
import peaksoft.model.Student;
import peaksoft.repository.StudentRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class StudentRepositoryImpl implements StudentRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Student> getAllStudents() {
        List<Student> students =
                entityManager.createQuery("select c from Student c", Student.class).getResultList();
        return students;
    }

    @Override
    public void addStudents(Long id, Student student) {
        Company company = entityManager.find(Company.class, id);
        company.addStudent(student);
        student.setCompany(company);
        entityManager.merge(student);

    }

    @Override
    public Student getById(Long id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> getStudentByCourseId(Long id) {
        return entityManager.createQuery("select  c from Student  c where  c.course.id=:id", Student.class)
                .setParameter("id", id).getResultList();
    }

    @Override
    public List<Student> getStudentByCompanyId(Long id) {
        return entityManager.createQuery("select  c from  Student  c where c.company.id=:id", Student.class)
                .setParameter("id", id).getResultList();
    }

    @Override
    public void updateStudent(Long id, Student student) {
        Student student1 = entityManager.find(Student.class, id);
        student1.setId(student.getId());
        student1.setFirstName(student.getFirstName());
        student1.setLastName(student.getLastName());
        student1.setPhoneNumber(student.getPhoneNumber());
        student1.setEmail(student.getEmail());
        student1.setStudyFormat(student.getStudyFormat());
        entityManager.merge(student1);

    }

    @Override
    public void deleteStudent(Long id) {
        Student student = entityManager.find(Student.class, id);
        student.setCourse(null);
        entityManager.remove(student);
    }

    @Override
    public void assignStudentToCourseId(Long studId, Long courseId) {
        Student student = entityManager.find(Student.class, studId);
        Course course = entityManager.find(Course.class, courseId);
        student.setCourse(course);
        course.addStudent(student);
        entityManager.persist(course);
        entityManager.persist(student);
    }
}
