package registrar;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by bjackson on 2/21/2016.
 */
public class Student {

    public String name;
    public Set<Course> enrolledCourses;

    public Student(){
        enrolledCourses = new HashSet<>();
    }

    public void setName(String studentName){
        this.name = studentName;
    }

    public Set<Course> getCourses(){
        return enrolledCourses;
    }

    /**
     * Enroll a student in a course.
     * @param course - the course that the student wishes to enroll in
     * @return boolean - if the course registration is successful
     */
    public boolean enrollIn(Course course){
        // If student is already enrolled in course then stop
        if (this.enrolledCourses.contains(course)) { return false; }
        if (course.getEnrolledStudents().contains(this)) { return false;}

        // If course is full, add the student to the waitlist
        if (course.getWaitList().size() >= course.getEnrollmentLimit()) {
            // If student is already on waitlist, don't add them again
            if (!course.getWaitList().contains(this)) {
                course.addToWaitlist(this);
            }
            return false;
        }

        // Add student to course
        this.enrolledCourses.add(course);
        course.addStudent(this);
        return true;
    }

    /**
     * Drop a course for a student
     * @param course - the course that the student wishes to drop
     */
    public void drop(Course course){
        if (enrolledCourses.contains(course)) {
            enrolledCourses.remove(course);
        }
        course.dropStudent(this);
    }
}
