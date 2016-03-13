package registrar;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by bjackson on 2/21/2016.
 * Edited by Tyler J. Sklzuacek on 3/12/2016
 *
 * The student class allows the user to create a student, complete with the name
 * and the courses he or she is currently enrolled.
 */
public class Student {

    private String name;
    private Set<Course> courses;

    public Student(){
        courses = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Set<Course> getCourses(){
        return Collections.unmodifiableSet(courses);
    }

    /** Let a student enroll in a given course**/
    public boolean enrollIn(Course course) {
        if (course.enroll(this)) {
            courses.add(course);
            return true;
        }
        else {
            return false;
        }
    }

    /**Allows the student to drop a given course **/
    public void drop(Course course) {
        courses.remove(course);
        course.dropStudent(this);
    }

    /** to-string method to Turn the student's name into a string **/
    @Override
    public String toString() {
        return getName();
    }
}

