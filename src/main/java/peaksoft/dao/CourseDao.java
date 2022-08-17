package peaksoft.dao;

import peaksoft.model.Course;

import java.util.List;

public interface CourseDao {
    List<Course> getAllCourses();
    void addCourse(Course course);
    Course getById(long id);
    void updateCourse(Course course);
    void deleteCourse(Course course);
}
