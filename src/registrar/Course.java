package registrar;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by bjackson on 2/21/2016.
 */
public class Course {

    private Set<Student> studentsEnrolled;
    private List<Student> waitlist;
    private String catalogNumber;
    private String title;
    private int enrollmentLimit;

    public Course(){
        studentsEnrolled = new HashSet<>();
        waitlist = new ArrayList<>();
        //-1 identifies unlimited enrollment and constructor sets it as default
        enrollmentLimit = -1;
    }

    public String getCatalogNumber() {
        return catalogNumber;
    }

    public void setCatalogNumber(String number){ this.catalogNumber = number; }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title){
        if(title == null) {
            throw new IllegalArgumentException("title cannot be null");
        }
        this.title = title;
    }

    public int getEnrollmentLimit(){
        return enrollmentLimit;
    }

    public boolean setEnrollmentLimit(int limit){
        this.enrollmentLimit = limit;
        return true;
    }

    public Set<Student> getEnrolledStudents(){
        return studentsEnrolled;
    }

    public List<Student> getWaitList(){
        return waitlist;
    }

    public boolean enrolled(Student s){
        if (studentsEnrolled.contains(s)){
            return true;
        }
        //checks that class is not at full enrollment AND class does not have unlimited enrollment
        if (studentsEnrolled.size() >= enrollmentLimit && enrollmentLimit != -1){
            if (waitlist.contains(s)){
                return false;
            }
            waitlist.add(s);
            return false;
        }
        studentsEnrolled.add(s);
        return true;
    }

    public void dropStudent(Student s){
        if (studentsEnrolled.remove(s)) {
            if (waitlist.size() > 0) {
                Student toEnroll = waitlist.remove(0);
                studentsEnrolled.add(toEnroll);
                toEnroll.enrollIn(this);
            }
        }
        waitlist.remove(s);
    }

    public void removeEnrollmentLimit(){
        //identifies unlimited enrollment by -1
        this.enrollmentLimit = -1;
    }

}
