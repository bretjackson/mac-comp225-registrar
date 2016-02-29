package registrar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by bjackson on 2/21/2016.
 */
public class Course {

    private Set<Student> roster = new HashSet<>();
    private List<Student> waitlist = new ArrayList<>();
    private String catalogNumber;
    private String title;
    private int enrollmentLimit = 16;
    Integer myInf = Integer.MAX_VALUE;
    Scanner in = new Scanner(System.in);

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
        if(title == null) {
            throw new IllegalArgumentException("course title cannot be null");
        }

        this.title = title;
    }

    public int getEnrollmentLimit() {
        return enrollmentLimit;
    }

    public boolean setEnrollmentLimit(int limit) {
        if (limit < 0) {
            throw new IllegalArgumentException("course cannot have negative enrollment limit: " + limit);
        }

        //If students are enrolled you can't change the limit
        if (!roster.isEmpty()) {
            removeEnrollmentLimit();
            throw new IllegalStateException("enter new enrollment limit");
            // Consider making this IllegalStateException instead of boolean return val
        }

        System.out.println("Enter new limit: ");
        this.enrollmentLimit = in.nextInt();
        return true;
    }

    public Set<Student> getStudents() {
        return Collections.unmodifiableSet(roster);
    }

    public List<Student> getWaitList() {
        return Collections.unmodifiableList(waitlist);
    }

    boolean enroll(Student student) {
        if (roster.contains(student)) {
            return true;
        }
        if (isFull()) {
            addToWaitlist(student);
            return false;
        }
        roster.add(student);
        return true;
    }

    public boolean isFull() {
        return roster.size() >= enrollmentLimit;
    }

    private void addToWaitlist(Student s) {
        if (!waitlist.contains(s)) {
            waitlist.add(s);
        }
    }

    private void enrollNextFromWaitlist() {
        if (!waitlist.isEmpty()) {
            waitlist.remove(0).enrollIn(this);
        }
    }

    void dropStudent(Student student) {
        waitlist.remove(student);
        if (roster.remove(student)) {
            enrollNextFromWaitlist();
        }
    }

    public void removeEnrollmentLimit(){
        if (setEnrollmentLimit(enrollmentLimit)){
            enrollmentLimit = myInf;
        }
    }

    @Override
    public String toString() {
        return getTitle() + " (" + getCatalogNumber() + ")";
    }
}
