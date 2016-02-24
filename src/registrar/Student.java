package registrar;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by bjackson on 2/21/2016.
 */
public class Student {

    public String name;
    public Set<Course> coursesOfStudent;

    //Constructor for student that sets up a hash tag for the students enrollment
    public Student(){
        coursesOfStudent = new HashSet<>();
    }

    /**
     * Sets the name of a student
     * @param name is the desired name of the student
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Gets the courses of a student
     * @return the courses a student is enrolled in
     */
    public Set<Course> getCourses(){
        return coursesOfStudent;
    }

    /**
     * Enrolls a student in a course
     * @param c the course to be enrolled in
     * @return true if the student is successfully enrolled, else false
     */
    public boolean enrollIn(Course c){
        if(c.enrollIn(this)) {
            coursesOfStudent.add(c);
            return true;
        }
        else {
            return false;
        }
    }

    /*
     *Drops a student from a course
     */
    public void drop(Course c){
        if (coursesOfStudent.contains(c)) {
            coursesOfStudent.remove(c);
        }
        c.dropStudent(this);
    }
}