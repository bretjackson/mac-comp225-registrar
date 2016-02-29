package registrar;

import java.util.*;

/**
 * Created by bjackson on 2/21/2016.
 */
public class Course {

    private Set<Student> roster = new HashSet<>();
    private List<Student> waitlist = new ArrayList<>();
    private String catalogNumber;
    private String title;
    private int enrollLimit = 16;

    public void setCatalogNumber(String number){
        this.catalogNumber = number;
    }

    public void setTitle(String title){
        if(title == null) {
            throw new IllegalArgumentException("course title cannot be null");
        }
        this.title = title;
    }

    public int getEnrollmentLimit(){
        return enrollLimit;
    }

    public void setEnrollmentLimit(int limit){
        if (limit < 0) {
            throw new IllegalArgumentException("course cannot have negative enrollment limit: " + limit);
        }
        this.enrollLimit = limit;
    }

    public void removeEnrollmentLimit(){
        //Add a big number that is out of any possible class size to roster size
        int inf = 1000000000;
        this.enrollLimit = roster.size() + inf;
    }

    public void fixOverEnrollmentFromRemoveEnrollmentLimit(){
        if (enrollLimit < roster.size()){
            throw new IllegalArgumentException("Fix enrollment limit or remove students: roster size is larger than enroll limit");
        }
    }

    public Set<Student> getStudents(){
        return Collections.unmodifiableSet(roster);
    }

    public List<Student> getWaitList(){
        return Collections.unmodifiableList(waitlist);
    }

    public boolean enrollStudent(Student s){
        if (roster.contains(s)){
            return true;
        }
        if (roster.size() >= enrollLimit){
            putOnWaitlist(s);
            return false;
        }
        roster.add(s);
        return true;
    }

    public boolean putOnWaitlist(Student s) {
        if (waitlist.contains(s)){
            return true;
        }
        waitlist.add(s);
        return true;
    }

    public void dropStudent(Student s){
        removeStudentEnrollWaitListed(s);
        removeStudentWaitList(s);
    }

    public void removeStudentEnrollWaitListed(Student s){
        if (roster.contains(s)) {
            roster.remove(s);
            if(waitlist.size()>0){
                Student toEnroll = waitlist.remove(0);
                enrollWaitListed(toEnroll);
            }
        }
    }

    public void enrollWaitListed(Student s){
        s.enrollInCourse(this);
    }

    public void removeStudentWaitList(Student s){
        if (waitlist.contains(s)){
            waitlist.remove(s);
        }
    }

}
