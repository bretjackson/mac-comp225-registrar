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

    public Course(){
        enrolledIn = new HashSet<>();
        waitlist = new ArrayList<>();
        limit = 16;
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

    public boolean enrollIn(Student s){
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

    public void dropStudent(Student s){
        if (enrolledIn.contains(s)) {
            enrolledIn.remove(s);
            if (waitlist.size() > 0) {

                /*Eliminated additional variable "toEnroll" and exposed waitlist.get(0) for each reference to the new student
                This clarifies that we are referring to the first student in the waiting list*/

                enrolledIn.add(waitlist.get(0));
                waitlist.get(0).enrolledIn.add(this);
                waitlist.remove(0);
            }
        }
        else if (waitlist.contains(s)){
            waitlist.remove(s);
        }
    }

}
