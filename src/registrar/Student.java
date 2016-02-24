package registrar;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by bjackson on 2/21/2016.
 */
public class Student {

    public String studentName;
    public Set<Course> enrolledCourses;

    public Student(){
        enrolledCourses = new HashSet<>();
    }

    public void setStudentName(String studentName){
        this.studentName = studentName;
    }

    public Set<Course> getCourses(){
        return enrolledCourses;
    }

    public boolean enrollInCourse(Course course){
        if(course.enrollStudent(this)) {
            enrolledCourses.add(course);
            return true;
        }
        else {
            return false;
        }
    }

    public void dropCourse(Course course){
        if (enrolledCourses.contains(course)) {
            enrolledCourses.remove(course);
        }
        course.dropStudent(this);
    }
}