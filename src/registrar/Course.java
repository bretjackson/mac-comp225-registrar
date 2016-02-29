package registrar;

import java.util.LinkedList;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

/**
 * Created by bjackson on 2/21/2016.
 */
public class Course {

    private Set<Student> studentList;
    private Queue<Student> waitlist;
    private String id;
    private String name;
    private int capacity;
    private boolean enrollmentLimitExists;

    public Course(){
        studentList = new HashSet<>();
        waitlist = new LinkedList<Student>();
        enrollmentLimitExists = false;
    }

    public void setCatalogNumber(String id){
        this.id = id;
    }

    public String getCatalogNumber(){return id; }

    public void setTitle(String name){
        this.name = name;
    }

    public String getTitle(){ return name; }

    public int getEnrollmentLimit(){
        if (getEnrollmentLimitExists()) {
            return capacity;
        }
        else{
            return Integer.MAX_VALUE;
        }
    }


    public void setEnrollmentLimit(int capacity){
            enrollmentLimitExists = true;
            this.capacity = capacity;
    }

    public Set<Student> getStudents(){
        return studentList;
    }

    public Queue<Student> getWaitList(){
        return waitlist;
    }

    public boolean enrollIn(Student student){
        //prevents double enrollment
        if (studentList.contains(student)){
            return true;
        }
        //if the course is full, add student to waitlist
        if (studentList.size() >= capacity && enrollmentLimitExists){
            if (waitlist.contains(student)){
                return false;
            }
            waitlist.add(student);
            return false;
        }
        //if course has open spots, add student to course
        studentList.add(student);
        return true;
    }

    public void dropStudent(Student student){
        //drop student from course
        if (studentList.contains(student)) {
            studentList.remove(student);

            //if there is a waitlist, replace dropped student by waitlist student
            if (waitlist.size() > 0) {
                Student waitlistStudent = waitlist.poll();
                studentList.add(waitlistStudent);
                waitlistStudent.enrolledIn.add(this);
            }
        }
        //if student is on waitlist, remove them from waitlist
        else if (waitlist.contains(student)){
            waitlist.remove(student);
        }
    }

    public void removeEnrollmentLimit(){
        enrollmentLimitExists = false;
        capacity = Integer.MAX_VALUE;
    }

    public boolean getEnrollmentLimitExists(){
        return enrollmentLimitExists;
    }

}

