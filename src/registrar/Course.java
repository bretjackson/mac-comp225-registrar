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
    private String number;
    private String title;
    private int limit;

    public Course(){
        studentsEnrolled = new HashSet<>();
        waitlist = new ArrayList<>();
        limit = 16;
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
        //If students are enrolled you can't change the limit
        if (studentsEnrolled.size() == 0){
            this.limit = limit;
            return true;
        }

        System.out.println("Students enrolled. Unable to set limit.");
        return false;
    }

    public Set<Student> getStudents(){
        return studentsEnrolled;
    }

    public List<Student> getWaitList(){
        return waitlist;
    }

    public boolean addStudent(Student s){
        studentsEnrolled.add(s);
        return true;
    }

    public void dropStudent(Student s){
        if (studentsEnrolled.contains(s)) {
            studentsEnrolled.remove(s);
            //add next waitlisted student
            if (waitlist.size() > 0) {
                Student toEnroll = waitlist.remove(0);
                toEnroll.enrollIn(this);
                System.out.println(toEnroll.name + " has enrolled in " + this.title + " from the waitlist.");
            }
        }
        else if (waitlist.contains(s)){
            waitlist.remove(s);
        }
    }

}
