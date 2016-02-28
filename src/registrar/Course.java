package registrar;

// Seems pretty minimal..

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * Created by bjackson on 2/21/2016.
 */
public class Course {

    private Set<Student> roster = new HashSet<>();
    private List<Student> waitlist = new ArrayList<>();
    private String catalogNumber;
    private String title;
    private int enrollmentLimit = null; //null means unlimited enrollment

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
            if(!isEnrollmentLimit || limit > roster.size()) {
                this.enrollmentLimit = limit;
                return true;
            }
            return false;
    }

    public boolean isEnrollmentLimit() {
            return this.enrollmentLimit != null;
    }

    public void removeEnrollmentLimit() {
        this.enrollmentLimit = null;
        enrollAllFromWaitlist();
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
        } else {
            this.enrolledIn.add(student);
            return true;
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

    private void enrollAllFromWaitlist() {
            for(Student student : waitlist) {
                    enrollNextFromWaitlist;
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

    @Override
    public String toString() {
        return getTitle() + " (" + getCatalogNumber() + ")";
    }
}
