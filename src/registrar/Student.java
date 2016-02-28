package registrar;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by bjackson on 2/21/2016.
 * Edited by MarcioPorto for COMP 225 Refactoring activity
 */
public class Student {

    private String name;
    private Set<Course> coursesEnrolledIn;

    public Student(){
        coursesEnrolledIn = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Set<Course> getCourses(){
        return Collections.unmodifiableSet(coursesEnrolledIn);
    }

    /*
    * This function enrolls a student in a course if the student is not enrolled in it yet.
    * @param  course the course to be added
    * @return true if successful, false otherwise
    * */
    public boolean joinCourse(Course course) {
        if (course.addStudent(this)) {
            coursesEnrolledIn.add(course);
            return true;
        }
        return false;
    }

    /*
    * This function drops a student from a course if the student is enrolled in it.
    * @param  course the course to be dropped
    * @return void
    * */
    public void drop(Course course){
        if (coursesEnrolledIn.contains(course)) {
            coursesEnrolledIn.remove(course);
        }
        // Drops student from the list of students in the course
        course.dropStudent(this);
    }

    @Override
    public String toString() {
        return getName();
    }
}
