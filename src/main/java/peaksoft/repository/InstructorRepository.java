package peaksoft.repository;

import peaksoft.model.Instructor;

import java.util.List;

public interface InstructorRepository {
    List<Instructor> getAllInstructor(Long id);
    void addInstructor(Long id,Instructor instructor);
    Instructor getById(Long id);
    List<Instructor> getInstructorByCompanyId(Long id);
    List<Instructor> getInstructorByCourseId(Long id);
    void updateInstructor(Long id,Instructor instructor);
    void deleteInstructor(Long id);
    void assignInstructorToCourse(Long courseId,Long instrId);

}
