package registrar;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by bjackson on 2/21/2016.
 */
public class Course {

    private Set<Student> enrolledIn;
    private List<Student> waitlist;
    private String number;
    private String name;
    private int limit;

    public Course() {
        enrolledIn = new HashSet<>();
        waitlist = new ArrayList<>();
        limit = 16;
    }

    public void setCatalogNumber(String number) {
        this.number = number;
    }

    public void setTitle(String title) {
        this.name = title;
    }

    public int getEnrollmentLimit() {
        return limit;
    }

    public boolean setEnrollmentLimit(int limit) {
        if (enrolledIn.isEmpty()) {
            this.limit = limit;
            return true;
        }
        return false;
    }

    public Set<Student> getStudents() {
        return enrolledIn;
    }

    public List<Student> getWaitList() {
        return waitlist;
    }

    public boolean enrollIn(Student s) {
        if (enrollStatus(s)=="enrolled") {
            return true;
        }
        if (enrollStatus(s)=="waitlisted"){
            return false;
        }
        if (enrolledIn.size() >= limit) {
            addStudent(s);
            return false;
        }
        addStudent(s);
        return true;
    }

    public String enrollStatus(Student s) {
        if (enrolledIn.contains(s)) {
            return "enrolled";
        } else if (waitlist.contains(s)) {
            return "waitlisted";
        } else return "notenrolled";
    }

    public void addStudent(Student s) {
        if (enrolledIn.size() >= limit) {
            waitlist.add(s);
        }
        else enrolledIn.add(s);
    }

    public void removeStudentfromWaitlist() {
        Student toEnroll = waitlist.remove(0);
        enrolledIn.add(toEnroll);
        toEnroll.enrollIn(this);
    }

    public void dropStudent(Student s) {
        if(enrollStatus(s) == "enrolled"){
            enrolledIn.remove(s);
            if (waitlist.size() > 0) {
                removeStudentfromWaitlist();
            }
        }
        else waitlist.remove(s);
        }
}


