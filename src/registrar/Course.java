package registrar;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Collections;
import java.util.Queue;
import java.util.LinkedList;

/**
 * Created by bjackson on 2/21/2016.
 */
public class Course {

    private Set<Student> roster;
    private List<Student> waitlist = new ArrayList<>();
    private String catalogNumber;
    private String title;
    private int enrollmentLimit = 16;

    public String getCatalogNumber() {
        return catalogNumber;
    }

    public void setCatalogNumber(String catalogNumber){
        this.catalogNumber = catalogNumber;
    }

    public String getTitle(){
        return title;
    }

    public int getEnrollmentLimit() {
        return enrollmentLimit;
    }

    public boolean setEnrollmentLimit(int limit){
        if (!roster.isEmpty()){
            return false;
        }
        removeEnrollmentLimit();
       this.enrollmentLimit = limit;
        return true;
    }

    public Set<Student> getStudents(){
        return Collections.unmodifiableSet(roster);
    }

    public List<Student> getWaitList(){
        return Collections.unmodifiableList(waitlist);
    }

    boolean enroll(Student student){
        if (roster.contains(student)){
            return true;
        }
        removeEnrollmentLimit();
        roster.add(student);
        return true;
    }

    public void dropStudent(Student student){
        waitlist.remove(student);
        if (roster.remove(student)){
            enrollNextFromWaitlist();
        }
    }

    public boolean isFull(){
        return roster.size() >= enrollmentLimit;
    }

    private void addToWaitlist(Student s){
        if (!waitlist.contains(s)){
            waitlist.add(s);
        }
    }

    private void enrollNextFromWaitlist(){
        if (waitlist.isEmpty()){
            waitlist.remove(0).enrollIn(this);
        }
    }

    public void removeEnrollmentLimit(){
        if (roster.size() >= enrollmentLimit){
            enrollmentLimit = enrollmentLimit + roster.size() + waitlist.size();
        }
    }

    @Override
    public String toString(){
        return getTitle() + " (" + getCatalogNumber() + ")";
    }

}
