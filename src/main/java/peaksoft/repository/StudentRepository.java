package peaksoft.repository;

import peaksoft.model.Lesson;
import peaksoft.model.Student;

import java.util.List;

public interface StudentRepository {
    List<Student> getAllStudents();
    void addStudents(Long id,Student student);
    Student getById(Long id);

    List<Student> getStudentByCourseId(Long id);
    List<Student> getStudentByCompanyId(Long id);
    void updateStudent(Long id,Student student);
    void deleteStudent(Long id);
    void assignStudentToCourseId(Long studId,Long courseId);
}
