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
    private String number;
    private String title;
    private int enrollmentLimit;

    public Course() {
        enrolledStudents = new HashSet<>();
        waitlist = new ArrayList<>();
        enrollmentLimit = 16;
    }

    public void setCatalogNumber(String courseNum) {
        this.number = courseNum;
    }

    public void setTitle(String courseTitle) {
        this.title = courseTitle;
    }

    public int getEnrollmentLimit() {
        return enrollmentLimit;
    }

    public boolean setEnrollmentLimit(int limit) {
        //If students are enrolled you can't change the enrollmentLimit
        if (enrolledStudents.size() == 0) {
            this.enrollmentLimit = limit;
            return true;
        }
        return false;
    }

    public Set<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public List<Student> getWaitList() {
        return waitlist;
    }

    public boolean addStudent(Student student) {
        enrolledStudents.add(student);
        return true;
    }

    public boolean addToWaitlist(Student student) {
        waitlist.add(student);
        return true;
    }

    /**
     * Drop a student from the current course.
     * If there is a waitlist, move the first student into the course.
     * @param student - the student that wishes to drop the course
     */
    public void dropStudent(Student student){
        if (enrolledStudents.contains(student)) {
            enrolledStudents.remove(student);

            // If there are students on the waitlist, enroll the first one
            if (waitlist.size() > 0) {
                Student toEnroll = waitlist.remove(0);
                toEnroll.enrollIn(this);
            }
        }

        // If the student is on the waitlist for the course, drop the student
        else if (waitlist.contains(student)){
            waitlist.remove(student);
        }
    }

}
