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

    public Course() {
        enrolledStudents = new HashSet<>();
        waitlist = new ArrayList<>();
        enrollLimit = 16;
    }

    public void setCatalogNumber(String number) {
        this.catalogNumber = number;
    }

    public void setCourseTitle(String title) {
        this.courseTitle = title;
    }

    public int getEnrollmentLimit() {
        return enrollLimit;
    }

    public boolean setEnrollmentLimit(int limit) {
        //If students are enrolled you can't change the enrollLimit
        if (enrolledStudents.size() == 0) {
            this.enrollLimit = limit;
            return true;
        }
        return false;
    }

    /**
     * Checks if there is still room available in a course.
     *
     * @return - true if there is still space for someone to enroll in the course
     */
    public boolean spaceAvailable() {
        return enrollLimit > enrolledStudents.size();
    }

    public Set<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public List<Student> getWaitList() {
        return waitlist;
    }

    /**
     * Add a student to the waitlist.
     *
     * @param student - the student to be added to the course waitlist
     */
    public void addToWaitlist(Student student) {
        if (!waitlist.contains(student)) {
            waitlist.add(student);
        }
    }

    /**
     * Enroll a student in a course by adding them to the list of students in the course.
     *
     * @param student - student to be added to the course
     */
    public void enrollStudent(Student student) {
        enrolledStudents.add(student);
    }

    /**
     * Drop the student from the course list of students.
     * If there is a waitlist add the next student to the course.
     *
     * @param student - the student to be dropped from the waitlist
     */
    public void dropStudent(Student student) {
        // Remove the student from the course
        if (enrolledStudents.contains(student)) {
            enrolledStudents.remove(student);
        }

        // If the student was on the waitlist, remove them
        else if (waitlist.contains(student)) {
            waitlist.remove(student);
        }
        // If there is a waitlist, add the first student to the course
        checkWaitlist();
    }

    /**
     * If there is space in the course and someone on the waitlist, add them to the course
     */
    public void checkWaitlist() {
        // Make sure there is space in the course
        if (!spaceAvailable()) {
            return;
        }

        // Add first student on the waitlist to the course
        if (waitlist.size() > 0) {
            Student nextStudent = waitlist.remove(0);
            enrolledStudents.add(nextStudent);
            nextStudent.enrolledCourses.add(this);
        }
    }

}