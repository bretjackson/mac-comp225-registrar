package registrar;

import java.util.ArrayList;
import java.util.Collections;
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
    private int limit;
    public static final int UNLIMITED_ENROLLMENT = -1;

    public Course() {
        enrolledStudents = new HashSet<>();
        waitlist = new ArrayList<>();
        limit = UNLIMITED_ENROLLMENT;
    }

    public void setCatalogNumber(String number){
        this.number = number;
    }

    public String getCatalogNumber(String number){
        return this.number;
    }

    public void setTitle(String title){
        if(title == null) {
            throw new IllegalArgumentException("course title cannot be null");
        }

        this.title = title;
    }
    
    public String getTitle(String title){
        return title;
    }

    public int getEnrollmentLimit(){
        return limit;
    }

    public void removeEnrollmentLimit() {
        this.limit = UNLIMITED_ENROLLMENT;
        enrollEntireWaitList();
    }

    private void enrollEntireWaitList () {
        while (!waitlist.isEmpty()) {
            enrollNextOnWaitList();
        }

    }

    public boolean setEnrollmentLimit(int limit){
        if (limit < enrolledStudents.size()) {
            return false;
        } else {
            this.limit = limit;
            return true;
        }
    }

    public Set<Student> getStudents(){
        return Collections.unmodifiableSet(enrolledStudents);
    }

    public List<Student> getWaitList(){
        return Collections.unmodifiableList(waitlist);
    }

    public boolean enrollStudent(Student student) {
        if (enrolledStudents.contains(student)) {
            return true;
        }
        if (isFull()) {
            addStudentToWaitList(student);
            return false;
        }
        enrolledStudents.add(student);
        return true;
    }

    public boolean isFull() {
        return enrolledStudents.size() >= limit &&
                limit != UNLIMITED_ENROLLMENT;
    }


    public void dropStudent(Student s){
        if (enrolledStudents.remove(s)) {
            enrollNextOnWaitList();
        }
        waitlist.remove(s);
    }

    private void enrollNextOnWaitList() {
        if (!waitlist.isEmpty()) {
            Student toEnroll = waitlist.remove(0);
            enrolledStudents.add(toEnroll);
            toEnroll.getCourses().add(this);
        }
    }

    private void addStudentToWaitList (Student s) {
        if (!waitlist.contains(s)) {
            waitlist.add(s);
        }
    }


}
