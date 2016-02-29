package registrar;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by bjackson on 2/21/2016.
 */
public class Course {

    private Set<Student> roster;
    private List<Student> waitlist;
    private String number;
    private String name;
    private int limit;
    public static final int UNLIMITED = 100000;

    public Course(){
        roster = new HashSet<>();
        waitlist = new ArrayList<>();
        limit = UNLIMITED;
    }

    public void setCatalogNumber(String number){
        this.number = number;
    }

    public void setTitle(String title){
        this.name = title;
    }

    public int getEnrollmentLimit(){
        return limit;
    }

    public void setEnrollmentLimit(int newLimit) {
        this.limit = newLimit;
    }

    public void removeEnrollmentLimit() {
        this.limit = UNLIMITED;
    }

    public Set<Student> getStudents(){
        return roster;
    }

    public List<Student> getWaitList(){
        return waitlist;
    }

    public boolean enrollInClass (Student s){
        if (roster.contains(s)){
            return true;
        }
        if (roster.size() >= limit){
            addToWaitList(s);
            return false;
        }
        roster.add(s);
        return true;
    }

    public void dropStudentFromWaitList(Student s) {
        if (roster.remove(s)) {
            enrollNextOnWaitList();
        }
        waitlist.remove(s);
    }

    private void enrollNextOnWaitList() {
        if(!waitlist.isEmpty()) {
            Student nextEnroll = waitlist.remove(0);
            roster.add(nextEnroll);
            nextEnroll.getCourses().add(this);
        }
    }

    private void addToWaitList(Student s) {
        if (!waitlist.contains(s)) {
            waitlist.add(s);
        }
    }

}
