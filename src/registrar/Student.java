package registrar;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by bjackson on 2/21/2016.
 * Refactored by sduong on 2/23/2016.
 * Students should know their registered courses.
 */
public class Student {



    private String studentName; //Name of student
    private Set<Course> courses; //courses the student is enrolled in? rename...there is a function also called enrolledIn

    /**
     * constructor for Student...should probably set the studentName here in addition to remembering the set of courses
     */
    public Student(String student){
        this.courses = new HashSet<>();
        this.studentName = student;
    }

    public void setStudentName(String studentName) {
        //defensive programming
//        if (studentName == null){
//            throw new IllegalAccessException("NAMES CANNOT BE NULL");
//        }
        this.studentName = studentName;
    }

    public String getStudentName() {
        return studentName;
    }

    /**
     * a getter method for getting the set of courses the student is enrolled in
     * @return courses
     */
    public Set<Course> getCourses(){
        return courses;
    }

    /**
     *
     * @param course
     * @return boolean value
     */
    public boolean enrolledIn(Course course){
        if(course.enroll(this)) {
            courses.add(course);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     *
     * @param course
     */
    public void drop(Course course){
        if (courses.contains(course)) {
            courses.remove(course);
        }
        course.dropStudent(this);
    }
}
