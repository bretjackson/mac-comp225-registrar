package registrar;

import java.util.*;

/**
 * Created by bjackson on 2/21/2016.
 */
public class Course {

    private String catalogNumber;
    private String title;
    private Set<Student> roster;
    private Queue<Student> waitList;
    public int enrollmentLimit;

    public Course(){
        roster = new HashSet<>();
        waitList = new LinkedList<>();
        enrollmentLimit = Integer.MAX_VALUE;
    }

    public String getCatalogNumber() {
        return catalogNumber;
    }

    public void setCatalogNumber(String catalogNumber) {
        this.catalogNumber = catalogNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null) {
            throw new IllegalArgumentException("Course title cannot be null");
        }
        this.title = title;
    }

    public int getEnrollmentLimit() {
        return enrollmentLimit;
    }

    public boolean setEnrollmentLimit(int enrollmentLimit) {
        if (enrollmentLimit < 0) {
            throw new IllegalArgumentException("Course enrollment limit cannot be less than zero");
        }

        this.enrollmentLimit = enrollmentLimit;
        return true;
    }

    public void removeEnrollmentLimit() {
        this.enrollmentLimit = Integer.MAX_VALUE;
    }

    public Set<Student> getStudents(){
        return roster;
    }

    public Queue<Student> getWaitList(){
        return waitList;
    }

    public boolean enroll(Student student){
        if (roster.contains(student)){
            return true;
        }
        if (isFull()) {
            addToWaitlist(student);
            return false;
        }
        roster.add(student);
        return true;
    }

    //copied from bretjackson's repository at https://github.com/bretjackson/mac-comp225-registrar/blob/master/src/registrar/Course.java
    private void addToWaitlist(Student student) {
        if (!waitList.contains(student)) {
            waitList.add(student);
        }
    }

    //also copied from the above mentioned file
    public boolean isFull() {
        return roster.size() >= enrollmentLimit;
    }

    //also copied from Bret's repo
    private void enrollNextFromWaitlist() {
        if (!waitList.isEmpty()) {
            waitList.poll().enrollIn(this);
        }
    }

    public void dropStudent(Student s) {
        if (roster.contains(s)) {
            roster.remove(s);
            if (waitList.size() > 0) {
                Student nextStudentWaiting = waitList.poll();
                roster.add(nextStudentWaiting);
                nextStudentWaiting.getCourses().add(this);
            }
        }
        else if (waitList.contains(s)) {
            waitList.remove(s);
        }
    }

}
