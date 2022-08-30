package peaksoft.repository;

import peaksoft.model.Course;

import java.util.List;

public interface CourseRepository {
    List<Course> getAllCourses(Long id);
    void addCourse(Long id,Course course);
    List<Course>  getCourseByCompanyId(Long id);
    Course getById(Long id);
    void updateCourse(Long id,Course course);
    void deleteCourse(Long id);

    void assignInstructorToCourse(Long courseId,Long instId);
}
