package registrar;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by bjackson on 2/21/2016.
 * Edited by MarcioPorto for COMP 225 Refactoring activity
 */
public class Course {

    private Set<Student> studentsEnrolled;
    private List<Student> waitList;
    private String number;
    private String name;
    private int limit;

    public Course(){
        studentsEnrolled = new HashSet<>();
        waitList = new ArrayList<>();
        limit = 16;
    }

    public Set<Student> getStudents(){
        return studentsEnrolled;
    }

    public List<Student> getWaitList(){
        return waitList;
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

    /*
    * This function sets the enrollment limit for a course, if no one has registered for it yet.
    * @param  limit: the maximum number of students
    * @return true if successful, false otherwise
    * */
    public boolean setEnrollmentLimit(int limit){
        //If students are enrolled you can't change the limit
        if (studentsEnrolled.size() == 0){
            this.limit = limit;
            return true;
        }
        return false;
    }

    /*
    * This function adds a student to the studentsEnrolled list of a course.
    * @param  s the student joining the course
    * @return true if successful, false otherwise
    * */
    public boolean addStudentToCourse(Student s){
        // If the student is already in the list, just returns true
        if (studentsEnrolled.contains(s)){
            return true;
        }
        // If the class is full, add student to waitList
        if (studentsEnrolled.size() >= limit){
            // If the student is already on the waitList, return false
            if (waitList.contains(s)){
                return false;
            }
            waitList.add(s);
            return false;
        }
        // Add student to the studentsEnrolled list
        studentsEnrolled.add(s);
        return true;
    }

    /*
    * If the student is enrolled in a course, this function drops the student from the course's studentsEnrolled list.
    * If the student is on the waitList, this function removes the student from it.
    * @param  s the student to be dropped
    * @return void
    * */
    public void dropStudent(Student s){
        if (studentsEnrolled.contains(s)) {
            studentsEnrolled.remove(s);
            // If there is someone on the waitList, add that person to the studentsEnrolled list
            if (waitList.size() > 0) {
                addFromWaitList();
            }
        }
        else if (waitList.contains(s)){
            waitList.remove(s);
        }
    }

    /*
    * Adds the first student from the waitList to the studentsEnrolled list (removing it from the waitList afterwards).
    * @return void
    * */
    public void addFromWaitList(){
        Student toEnroll = waitList.remove(0);
        studentsEnrolled.add(toEnroll);
        toEnroll.coursesEnrolledIn.add(this);
    }

}
