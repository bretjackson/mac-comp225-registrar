package registrar;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by bjackson on 2/21/2016.
 */
public class Course {

    private Set<Student> enrolledStudents;
    private List<Student> waitlist;
    private String catalogNumber;
    private String courseTitle;
    private int enrollLimit;

    public Course(){
        enrolledStudents = new HashSet<>();
        waitlist = new ArrayList<>();
        enrollLimit = 16;
    }

    public void setCatalogNumber(String number){
        this.catalogNumber = number;
    }

    public void setCourseTitle(String title){
        this.courseTitle = title;
    }

    public int getEnrollmentLimit(){
        return enrollLimit;
    }

    public boolean setEnrollmentLimit(int limit){
        //If students are enrolled you can't change the enrollLimit
        if (enrolledStudents.size() == 0){
            this.enrollLimit = limit;
            return true;
        }
        return false;
    }

    /**
     * Checks if there is still room available in a course.
     * @return - true if there is still space for someone to enroll in the course
     */
    public boolean spaceAvailable() {
        return enrollLimit > enrolledStudents.size();
    }

    public Set<Student> getEnrolledStudents(){
        return enrolledStudents;
    }

    public List<Student> getWaitList(){
        return waitlist;
    }

    /**
     * Enroll a student in a course by adding them to the list of students in the course.
     * If there is not room in the course, add student to the waitlist.
     * @param student - student to be added to the course
     * @return true if the student is successfully enrolled in the course
     */
    public boolean enrollStudent(Student student){
        // Check if the student is already enrolled in the course
        if (enrolledStudents.contains(student)){
            return true;
        }

        // If the course is full, add the student to the waitlist
        if (!spaceAvailable()){
            if (waitlist.contains(student)){
                return false;
            }
            waitlist.add(student);
            return false;
        }

        // Otherwise, enroll the student
        enrolledStudents.add(student);
        return true;
    }

    /**
     * Drop the student from the course list of students.
     * If there is a waitlist add the next student to the course.
     * @param student
     */
    public void dropStudent(Student student){
        // Remove the student from the course
        if (enrolledStudents.contains(student)) {
            enrolledStudents.remove(student);

            // If there is a waitlist, add the first student to the course
            if (waitlist.size() > 0) {
                Student toEnroll = waitlist.remove(0);
                enrolledStudents.add(toEnroll);
                toEnroll.enrolledCourses.add(this);
            }
        }

        // If the student was on the waitlist, remove them
        else if (waitlist.contains(student)){
            waitlist.remove(student);
        }
    }

}