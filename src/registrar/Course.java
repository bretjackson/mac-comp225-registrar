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

    //looks fine?
    public Course(){
        enrolledIn = new HashSet<>();
        waitlist = new ArrayList<>();
        limit = 16;
    }

    //looks fine?
    public void setCatalogNumber(String coursenumber){
        this.number = coursenumber;
    }

    //looks fine?
    public void setTitle(String courseTitle){
        this.name = courseTitle;
    }

    //looks fine
    public int getEnrollmentLimit(){
        return limit;
    }

    public boolean setEnrollmentLimit(int newLimit){
        //If students are enrolled you can't change the limit
        if (enrolledIn.size() == 0){
            this.limit = newLimit;
            return true;
        }
        // return false if students have already enrolled indicating the limit can't be changed
        else
            return false;
    }

    //these look fine...?
    public Set<Student> getStudents(){
        return enrolledIn;
    }

    public List<Student> getWaitList(){
        return waitlist;
    }

    public boolean enrollIn(Student s){
        if(enrolledIn.contains(s)){
            //catch a student already being enrolled
            return true;
        }
        //if student can be added to class, add them
        if(enrolledIn.size() < limit){
            enrolledIn.add(s);
            return true;
        }
        //else fit them to the waitlist
        else if(enrolledIn.size() >= limit && !waitlist.contains(s)){
            waitlist.add(s);
        }
        return false;
    }

    public void dropStudent(Student studentRemove){
        if (enrolledIn.contains(studentRemove)) {
            enrolledIn.remove(studentRemove);
            if (waitlist.size() > 0) {
                //I feel like Queue would make more sense for waitlist than a list but when I convert queue into a list it tries to override all the queue functions.
                Student studentToEnroll = waitlist.remove(0);
                studentToEnroll.enrollIn(this);
            }
        }
    }

    public void dropStudentFromWaitlist(Student s){
        //quick function for removing a student on the waitlist
        if(waitlist.contains(s)){
            waitlist.remove(s);
        }
    }
}
