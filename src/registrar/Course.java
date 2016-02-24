package registrar;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by bjackson on 2/21/2016.
 * Refactored by sduong on 2/23/2016.
 * Courses can have a max enrollment enrollmentLimit on the number of students.
 * The enrollment enrollmentLimit cannot be changed once a student registers for the course.
 */
public class Course {

    private Set<Student> studentsEnrolled; //set of students enrolled
    private List<Student> waitlist; //list of students on the waitlist
    private String courseCode;
    private String courseName;
    private int enrollmentLimit;

    /**
     * Constructor for course, and courses should know the list of students enrolled in them.
     */
    public Course(String number, String title){
        studentsEnrolled = new HashSet<>();
        waitlist = new ArrayList<>();
        enrollmentLimit = 16;
        this.courseCode = number;
        this.courseName = title;
    }


    /**
     * getter method to get the enrollment enrollmentLimit
     * @return enrollmentLimit
     */
    public int getEnrollmentLimit(){
        return enrollmentLimit;
    }

    public boolean setEnrollmentLimit(int limit){
        //If students are enrolled you can't change the enrollmentLimit
        if (studentsEnrolled.size() == 0){
            this.enrollmentLimit = limit;
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


    /**
     * checks the enrollment status of the student in the course.
     * @param s
     * @return boolean value
     */
    public boolean enroll(Student s) {

        //check if student is in the set of studentsEnrolled
        return studentsEnrolled.contains(s) || addStudent(s) ;
    }

    public boolean addStudent(Student s){
        //check if the number of students enrolled is equal or greater than the enrollmentLimit
        if (studentsEnrolled.size() >= enrollmentLimit){
            return checkWaitlist(s);
        }
        else{
            studentsEnrolled.add(s); //if the student is not enrolled and there is still spots left, add the student
            return true; //and return true
        }

    }

    public boolean checkWaitlist(Student s){
        if (waitlist.contains(s)){
            return false; //if the student is on the waitlist, return false.
        }
        else{
            waitlist.add(s); //otherwise, add student to the waitlist because enrollmentLimit is reached
            return false;
        }
    }


    public void dropStudent(Student s){
        if (studentsEnrolled.contains(s)) {
            studentsEnrolled.remove(s);
            if (!waitlist.isEmpty()) {
                enrollNextFromWaitList();
            }
        }
        else if (waitlist.contains(s)){
            waitlist.remove(s);
        }
    }

    private void enrollNextFromWaitList(){

            Student studentToAdd = waitlist.remove(0);
            studentsEnrolled.add(studentToAdd);
            studentToAdd.enrolledIn(this);

    }
}
