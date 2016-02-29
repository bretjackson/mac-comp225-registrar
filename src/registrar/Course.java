package registrar;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by bjackson on 2/21/2016.
 */
public class Course {

    private Set<Student> enrolledStudents;//Renaming
    private List<Student> waitlist;// Can change it to a queue
    private String number;
    private String name;
    private int limit;

    public Course(){
        enrolledStudents = new HashSet<>();
        waitlist = new ArrayList<>();
    }


    public void setCatalogNumber(String number){
        this.number = number;
    }

    public void removeEnrollmentLimit(){
        if (limit!=0){
            limit = 1000;
        }
    }

    // Makes the method name and the variable name match
    public void setTitle(String title){
        //Title cannot be null
        if (title==null){
            throw new IllegalArgumentException("Course name cannot be null");
        }
        this.name = title;
    }

    public String getName(){return name;}

    public void setNumber(String number){this.number = number;}

    public String getNumber(){return number;}

    public int getEnrollmentLimit(){
        return limit;
    }

    // Change limit at any time
    // Cannot change limit to a number smaller than roster size.
    public boolean setEnrollmentLimit(int limit){
        if (limit > enrolledStudents.size()){
            this.limit = limit;
            return true;
        } else{
            return false;
        }
    }

    public Set<Student> getStudents(){
        return enrolledStudents;
    }

    public List<Student> getWaitList(){
        return waitlist;
    }

    public boolean enroll(Student s){//                           enroll should not be public
        if (enrolledStudents.contains(s)){
            return true;
        }
        if (enrolledStudents.size() >= limit){
            if (!waitlist.contains(s)){
                waitlist.add(s);
                // Print system message to notify that you are enrolled in, or the class is full already
            }
            return false;
        }
        enrolledStudents.add(s);
        return true;
    }

    public void dropStudent(Student s){
        if (enrolledStudents.remove(s)) {
            if (!waitlist.isEmpty()) {
               enrollNextFromWaitlist();
            }
        }
        waitlist.remove(s); //safe to remove even the student is absent in waitlist

    }

    private void enrollNextFromWaitlist(){
        if (!waitlist.isEmpty()){
            Student studentFromWaitlist = waitlist.remove(0);
            enrolledStudents.add(studentFromWaitlist);
            studentFromWaitlist.enrollIn(this); // Better way to add a new course to the student's list
        }
    }

}
