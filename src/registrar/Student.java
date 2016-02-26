package registrar;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by bjackson on 2/21/2016.
 * Manages set of classes a student in enrolled in.
 */
public class Student {

    public String name;
    public Set<Course> enrolledCourses;

    public Student(){
        enrolledCourses = new HashSet<>();
    }

    public void setName(String name){
        this.name = name;
    }

    public Set<Course> getCourses(){
        return enrolledCourses;
    }

    /*
     * Tries to enroll student in course, adds to enrolledCourses and returns true if successful.
     */
    public boolean enrollIn(Course course){
        if(course.enroll(this)) {
            enrolledCourses.add(course);
            return true;
        }
        else return false;
    }

    public void drop(Course course){
        if (enrolledCourses.contains(course)) {
            enrolledCourses.remove(course);
        }
        course.dropStudent(this);
    }
}
