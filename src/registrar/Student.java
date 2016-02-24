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

    /**
     * Enroll a student in a course if there is room and then update the course
     * If there is not room, add the student to the waitlist for the course
     * @param course - the course that the student wishes to add
     * @return true if the student is successfully enrolled in the course
     */
    public boolean enrollInCourse(Course course){
        if(enrolledCourses.contains(this)) {return false;}
        if(course.enrollStudent(this)) {
            enrolledCourses.add(course);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Drop a course from a student's schedule.
     * @param course - the course the student wishes to drop
     */
    public void dropCourse(Course course){
        if (enrolledCourses.contains(course)) {
            enrolledCourses.remove(course);
        }
        course.dropStudent(this);
    }
}