package registrar;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by bjackson on 2/21/2016.
 * Modified by Hannah Sonsalla on 2/28/16.
 */
public class Course {

    /**
     * Private instance variables including a roster (set of students), 
     * waitlist (list of students), catalog number, course title, enrollment limit, and
     * unlimited enrollment value (true or false).
     */
    private Set<Student> roster;
    private List<Student> waitlist;
    private String catalogNumber;
    private String courseTitle;
    private int enrollmentLimit;
    //Boolean value to account for whether enrollment is unlimited or not.
    private boolean unlimitedEnrollment;

    /* Creates a new Course object with a set of students (roster), a list of students for a
    waitlist (waitlist), unlimited enrollment and no enrollment limit by default.
    */
    public Course(){
        roster = new HashSet<>();
        waitlist = new ArrayList<>();
        //Courses should not impose an enrollment limit by default.
        unlimitedEnrollment = true;
        //Avoid default initialization of int enrollmentLimit to 0 by initializing to maximum value.
        enrollmentLimit = Integer.MAX_VALUE;
    }

    /**
     * Gets the catalog number of a course.
     * @return catalog number of a course
     */
    public String getCatalogNumber() {
        return catalogNumber;
    }

    /**
     * Sets the catalog number of a course.
     * @param catalogNumber catalog number of a course
     */
    public void setCatalogNumber(String catalogNumber){
        this.catalogNumber = catalogNumber;
    }

    /**
     * Gets the title of a course.
     * @return title of a course
     */
    public String getCourseTitle(){
        return courseTitle;
    }

    /**
     * Sets the title of a course.
     * @param courseTitle title of a course
     */
    public void setCourseTitle(String courseTitle){
        //Throws exception if title is null.
        if (courseTitle == null) {
            throw new IllegalArgumentException("course must have title, cannot be null");
        }
        this.courseTitle = courseTitle;
    }

    /**
     * Gets the enrollment limit for a course.
     * @return the enrollment limit for a course (int)
     */
    public int getEnrollmentLimit(){
        return enrollmentLimit;
    }

    /**
     * Sets the enrollment limit of a course.
     * @param limit enrollment limit of a course.
     * @return boolean - true is enrollment limit is set to integer value, false if not possible
     */
    public boolean setEnrollmentLimit(Integer limit){
        // Throws exception if limit is below 0.  Impossible to have negative students in a class.
        if (limit < 0) {
            throw new IllegalArgumentException("Course must have a positive enrollment limit");
        }
        //Cannot set the limit to be less than the class size.
        if (roster.size() > limit) {
            return false;
        }
        unlimitedEnrollment = false;
        this.enrollmentLimit = limit;
        return true;
    }

    /**
     * Gets set of students in a course.
     * @return roster of students.
     */
    public Set<Student> getStudents(){
        return roster;
    }

    /**
     * Gets the list of students in the waitlist.
     * @return list of students in the waitlist
     */
    public List<Student> getWaitList(){
        return waitlist;
    }

    /**
     * Enrolls student in course if possible.
     * @param student student
     * @return boolean - true if student is enrolled, false if not.
     */
    public boolean enroll(Student student){
        //If student already in roster, return true.
        if (roster.contains(student)){
            return true;
        }
        //If roster is full (enrollment limit is met), add student to waitlist and return false.
        if (isFull()){
            addToWaitlist(student);
            return false;
        }
        //Else, add student to roster and return true.
        roster.add(student);
        return true;
    }

    /**
     * Determines whether course is full (if enrollment limit is met)
     * @return boolean - true if full, false if not
     */
    public boolean isFull() {
        if (unlimitedEnrollment) {
            return false;
        }
        return roster.size() >= enrollmentLimit;
    }



    /**
     * Adds student to waitlist.
     * @param student student
     */
    private void addToWaitlist(Student student) {
        //If student is not already in the waitlist, add them.
        if (!waitlist.contains(student)) {
            waitlist.add(student);
        }
    }

    /**
     * Enrolls the next student from the waitlist in the course.
     */
    public void enrollNextStudentFromWaitlist() {
        //Only enroll if waitlist has students.
        if (!waitlist.isEmpty()) {
            Student nextStudentInWaitlist = waitlist.remove(0);
            nextStudentInWaitlist.enrollIn(this);
        }
    }

    /**
     * Drops student from course.
     * @param student student
     */
    public void dropStudent(Student student){
        //Remove student from waitlist.
        waitlist.remove(student);
        //If student is in the roster, remove them and add the next student
        //in the waitlist to the course.
        if(roster.contains(student)) {
            roster.remove(student);
            enrollNextStudentFromWaitlist();
        }
    }

    /**
     * Removes the enrollment limit by setting it to maximum value and assigning
     * true to unlimitedEnrollment.
     */
    public void removeEnrollmentLimit() {
        enrollmentLimit = Integer.MAX_VALUE;
        unlimitedEnrollment = true;
    }

    /**
     * String representation of a course, including catalog number and title.
     * @return  string representation of a course.
     */
   @Override
   public String toString() {
       return getCatalogNumber() + ":" + getCourseTitle();
   }

}
