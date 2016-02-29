package registrar;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by bjackson on 2/21/2016.
 * Modified by Hannah Sonsalla on 2/28/16.
 */
public class Student {

    /**
     * Instance variables (made private) including student name and a set of courses.
     */
    private String name;
    private Set<Course> courses;

    /* Creates a new Student object with a set of courses.
    */
    public Student(){
        courses = new HashSet<>();
    }

    /**
     * Sets the name of a student.
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Gets the name of a student.
     * @return name of student
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the set of courses for a student.
     * @return set of courses
     */
    public Set<Course> getCourses(){
        return courses;
    }

    /**
     * Allows student to enroll in class if possible.
     * @param course course
     * @return boolean - true if student enrolls, false if not possible
     */
    public boolean enrollIn(Course course){
        if(course.enroll(this)) {
            courses.add(course);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Drop a course method.  If student drops a course, it will be removed
     * from the student's set of courses and the student will be dropped
     * from the course's set of students.
     * @param course
     */
    public void drop(Course course){
        courses.remove(course);
        course.dropStudent(this);
    }

    /**
     * String representation of a student (includes string name).
     * @return String of student's name
     */
    @Override
    public String toString(){
        return getName();
    }
}
