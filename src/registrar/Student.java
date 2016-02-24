package registrar;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by bjackson on 2/21/2016.
 */
public class Student {

    private String name;

    private Set<Course> enrolledCourses;

    public Student(){
        enrolledCourses = new HashSet<>();
    }

    public void setName(String name){
        this.name = name;
    }

    public void setEnrolledCourses(Set<Course> enrolledCourses) {
        this.enrolledCourses = enrolledCourses;
    }

    public Set<Course> getCourses(){
        return enrolledCourses;
    }

    public boolean enrollIn(Course course){
        if(course.enrollIn(this)) {
            enrolledCourses.add(course);
            return true;
        }
        else {
            return false;
        }
    }

    public void drop(Course course){
        enrolledCourses.remove(course);
        course.dropStudent(this);
    }
}
