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
    private String courseNumber;
    private String courseName;
    private int limit;

    public Course(){
        enrolledIn = new HashSet<>();
        waitlist = new ArrayList<>();
        limit = 16;
    }

    public void setCatalogNumber(String courseNumber){
        this.courseNumber = courseNumber;
    }

    public void setTitle(String courseName){
        this.courseName = courseName;
    }

    public int getEnrollmentLimit(){
        return limit;
    }

    public boolean setEnrollmentLimit(int limit){
        //If students are enrolled you can't change the limit
        if (enrolledIn.size() == 0){
            this.limit = limit;
            return true;
        }
        System.out.println("Could not change the course size limit. Students are already enrolled in this course");
        return false;
    }

    public Set<Student> getStudents(){
        return enrolledIn;
    }

    public List<Student> getWaitList(){
        return waitlist;
    }

    /* addToClass is called by Student, when one tries to enroll in a class,
     checks if that is ok, and adds them to the list of enrolled students
      */

    public boolean addToClass(Student s){
        if (enrolledIn.contains(s)){
            return true;
        }
        if (enrolledIn.size() >= limit){
            if (waitlist.contains(s)){
                return false;
            }
            waitlist.add(s);
            return false;
        }
        enrolledIn.add(s);
        return true;
    }

    //enrolls next available student in the waiting list
    public void enroll_nextWaiting(){
        if (waitlist.size() > 0) {
            enrolledIn.add(waitlist.get(0));
            waitlist.get(0).coursesEnrolledIn.add(this);
            waitlist.remove(0);
        }
    }

    //called by student.drop
    public void dropStudent(Student s){
        if (s.courseStatus(this)=="enrolled") {
            enrolledIn.remove(s);
            enroll_nextWaiting();
        }
        else if (s.courseStatus(this)=="waitList"){
            waitlist.remove(s);
        }
    }

}
