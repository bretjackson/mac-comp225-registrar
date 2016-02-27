package registrar;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by bjackson on 2/21/2016.
 */
public class Student {

    private String name;
    private Set<Course> coursesOfStudent;

    //Constructor for student that sets up a hash tag for the students enrollment
    public Student(){
        coursesOfStudent = new HashSet<>();
    }

    /**
     *
     * @return the name of the student
     */
    public String getName(){
        return name;
    }

    /**
     * Sets the name of a student
     * @param name is the desired name of the student
     */
    public void setName(String name){
        if(name==null){
            //throw new IllegalAccessException("Names cannot be null"); has error for some reason
        }
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
     * @param course the course to be enrolled in
     * @return true if the student is successfully enrolled, else false
     */
    public boolean enrollInCourse(Course course){
        if(course.enroll(this)) {
            coursesOfStudent.add(course);
            return true;
        }
        else {
            return false;
        }
    }

    /*
     *Drops a student from a course
     */
    public void drop(Course course){
        if (coursesOfStudent.contains(course)) {
            coursesOfStudent.remove(course);
        }
        course.dropStudent(this);
    }

    @Override
    public String toString(){
        return getName();
    }
}