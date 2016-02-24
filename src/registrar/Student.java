package registrar;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by bjackson on 2/21/2016.
 * Edited by MarcioPorto for COMP 225 Refactoring activity
 */
public class Student {

    public String name;
    public Set<Course> coursesEnrolledIn;

    public Student(){
        coursesEnrolledIn = new HashSet<>();
    }

    public void setName(String name){
        this.name = name;
    }

    public Set<Course> getCourses(){
        return coursesEnrolledIn;
    }

    /*
    * This function enrolls a student in a course if the student is not enrolled in it yet.
    * @param  c the course to be added
    * @return true if successful, false otherwise
    * */
    public boolean joinCourse(Course c){
        if(c.addStudentToCourse(this)) {
            coursesEnrolledIn.add(c);
            return true;
        }
        return false;
    }

    /*
    * This function drops a student from a course if the student is enrolled in it.
    * @param  c the course to be dropped
    * @return void
    * */
    public void drop(Course c){
        if (coursesEnrolledIn.contains(c)) {
            coursesEnrolledIn.remove(c);
        }
        // Drops student from the list of students in the course
        c.dropStudent(this);
    }
}
