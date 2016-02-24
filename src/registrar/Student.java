package registrar;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by bjackson on 2/21/2016.
 */
public class Student {

    public String name;
    public Set<Course> coursesEnrolledIn;

    public Student(){
        coursesEnrolledIn = new HashSet<>();
    }

    //setName sets the name of the student to the string parameter being passed as argument
    public void setName(String name){
        this.name = name;
    }

    public Set<Course> getCourses(){
        return coursesEnrolledIn;
    }

    public boolean enrollIn(Course c){
        if(c.addToClass(this)) {
            //added a condition to check whether the course is already in the student`s course list
            if (!(coursesEnrolledIn.contains(c))) {
                coursesEnrolledIn.add(c);
            }
            return true;
        }
        else return false;
        }


    public void drop(Course c){
        if  (courseStatus(c).equals("enrolled")) {
            coursesEnrolledIn.remove(c);
        }
        c.dropStudent(this);
    }

    /* Created a method for checking the status of a student in a course
       This replaces all the conditions in the two classes, and exposes what I am checking for
     */
    public String courseStatus(Course c){
        if (c.getStudents().contains(this)) {
            return "enrolled";
        }
        else if (c.getWaitList().contains(this)) {
            return "waitList";
        }
        return "notEnrolled";
        }

}
