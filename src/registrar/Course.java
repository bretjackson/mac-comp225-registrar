package registrar;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableRangeSet;
import com.google.common.collect.ImmutableSet;


/**
 * Created by bjackson on 2/21/2016.
 */
public class Course {

    private Set<Student> studentsEnrolled;
    private List<Student> waitlist;
    private String number;
    private String title;
    private int limit;
    private boolean hasLimit;

    public Course(){
        studentsEnrolled = new HashSet<>();
        waitlist = new ArrayList<>();
        limit = 16;
        hasLimit = false;
    }

    public boolean hasLimit(){
        return hasLimit;
    }

    public void setCatalogNumber(String number){
        this.number = number;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getTitle(){
        return this.title;
    }

    public int getEnrollmentLimit(){
        return limit;
    }

    public boolean setEnrollmentLimit(int limit){
        hasLimit = true;
        this.limit = limit;
        addStudentsFromWaitlistUntilFull();
        return true;
    }

    public Set<Student> getStudents(){
        return ImmutableSet.copyOf(studentsEnrolled);
    }

    public List<Student> getWaitList(){
        return waitlist;
    }

    public void removeEnrollmentLimit(){
        hasLimit = false;
        //add all students from waitlist
        while(waitlist.size() != 0){
            addStudentFromWaitlist();
        }
    }

    public boolean addStudent(Student s){
        studentsEnrolled.add(s);
        return true;
    }

    public void dropStudent(Student s){
            studentsEnrolled.remove(s);
            if(waitlist.contains(s)) {
                waitlist.remove(s);
            }

            //add next waitlisted student
            if ((waitlist.size() > 0 && studentsEnrolled.size() < limit)){
                addStudentFromWaitlist();
            }
    }

    private void addStudentsFromWaitlistUntilFull(){
        while(studentsEnrolled.size() < limit && waitlist.size() > 0){
            addStudentFromWaitlist();
        }
    }

    private void addStudentFromWaitlist(){
        Student toEnroll = waitlist.remove(0);
        toEnroll.enrollIn(this);
        System.out.println(toEnroll.name + " has enrolled in " + this.title + " from the waitlist.");
    }

}
