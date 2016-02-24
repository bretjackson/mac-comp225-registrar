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
    private String name;
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
        this.name = title;
    }

    public int getEnrollmentLimit(){
        return limit;
    }

    public boolean setEnrollmentLimit(int limit){
        //If students are enrolled you can't change the limit
        if (studentsEnrolled.isEmpty()){
            this.limit = limit;
            return true;
        }
        return false;
    }

    public Set<Student> getStudents(){
        return studentsEnrolled;
    }

    public List<Student> getWaitList(){
        return waitlist;
    }


    public boolean enrollIn(Student s){
        if (studentsEnrolled.contains(s)){
            return true;
        }
        if (studentsEnrolled.size() >= limit){
            addToWaitList(s);
            return false;
        }
        studentsEnrolled.add(s);
        s.addCourse(this); //calling this method here guarantees that whenever a student is enrolled in a course, the students courseSet is also updated
        return true;
    }
    public void addToWaitList(Student s){
        if (!waitlist.contains(s)){
            waitlist.add(s);
        }
    }

    public void dropStudent(Student s){
        if (studentsEnrolled.contains(s)) {
            studentsEnrolled.remove(s);
            s.removeCourse(this); //calling this method here guarantees that whenever a student drops a course, the students courseSet is also updated
            if (!waitlist.isEmpty()) {
                Student toEnroll = waitlist.remove(0);
                studentsEnrolled.add(toEnroll);
                toEnroll.addCourse(this);
            }
        }
        else if (waitlist.contains(s)){
            waitlist.remove(s);
        }
    }

}
