package registrar;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by bjackson on 2/21/2016.
 */
public class Student {

    private String studentName;
    public Set<Course> coursesEnrolledIn;
    public Student(){
        coursesEnrolledIn = new HashSet<>();
    }//Student Object

    /**
     * Enrolles a student into a course
     * @param course a course to enrole a student in
     * @return whether or not the student is in this particular course
     */
    public boolean enrollIn(Course course){
        if(course.enrollIn(this)) {//If the student can get into this course
            coursesEnrolledIn.add(course);//add course into this student's list
            return true;
        }
        else {//If the student can't get into this course
            return false;
        }
    }

    /**
     * Used to let a student drop a course
     * @param course a course the student wants to drop
     */
    public void drop(Course course){
        if (coursesEnrolledIn.contains(course)) {
            coursesEnrolledIn.remove(course);//takes this course off the student's list
        }
        course.dropStudent(this);//takes student off that course's list
    }

    //Getters and Setters
    public Set<Course> getCourses(){
        return coursesEnrolledIn;
    }

    public void setStudentName(String studentName){
        this.studentName = studentName;
    }

}
