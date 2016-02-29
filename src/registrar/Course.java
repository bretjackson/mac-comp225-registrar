package registrar;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by bjackson on 2/21/2016.
 */
public class Course {

    private Set<Student> studentsEnrolledInClass;//Students actually in the class
    private List<Student> studentsInWaitlist;//Students on a waitlist for the class
    private String courseIdNumber;
    private String courseIdName;
    private boolean hasEnrollementLimit;//If a course has an enrollment limit or not
    private int enrollmentLimit;//Max amount of students a class can hold

    public Course(){
        studentsEnrolledInClass = new HashSet<>();
        studentsInWaitlist = new ArrayList<>();
        hasEnrollementLimit = false;
        enrollmentLimit = 1000000;//1000000 is a place holder number. Be default, classes have no enrollment limit. The placeholder is then replaced when an enrollment limit is set.
    }//Course object

    /**
     * Enrolls a given student into this course
     * @param student a student to be enrolled into the course
     * @return whether or not the student is in the class, or can get in the class
     */
    public boolean enrollIn(Student student){
        if (studentsEnrolledInClass.contains(student)){
            return true;
        }else if(hasEnrollementLimit&&(studentsEnrolledInClass.size() >= enrollmentLimit)) {//If there is a enrollment limit and the class is at or over capacity
            if (studentsInWaitlist.contains(student)) {//If the student is already in the waitlist
                return false;
            } else {
                studentsInWaitlist.add(student);//add student to waitlist
                return false;
            }
        }
        studentsEnrolledInClass.add(student);
        return true;
    }

    /**
     * This method drops a chosen student
     * @param student the selected student to be dropped
     */
    public void dropStudent(Student student){
        if (studentsEnrolledInClass.contains(student)) {//If the student is in the class
            studentsEnrolledInClass.remove(student);//remove the student
            if (studentsInWaitlist.size() > 0) {//If there is a waitlist
                Student nextToEnroll = studentsInWaitlist.remove(0);//Enrolle the next student in the class
                studentsEnrolledInClass.add(nextToEnroll);
                nextToEnroll.coursesEnrolledIn.add(this);
            }
        }
        else{//removes a student from a class if they are in the waitlist
            studentsInWaitlist.remove(student);
        }
    }

    /**
     * Sets the enrollment limit for a class
     * @param limit an int which signifies how many students the class can hold
     * @return returns true if it can expand the class, false if it cannot
     */
    public boolean setEnrollmentLimit(int limit){
        //If students are enrolled you can't change the enrollmentLimit
        if(studentsEnrolledInClass.size()<limit){
            this.enrollmentLimit = limit;
            this.hasEnrollementLimit = true;
            return true;
        }
        return false;

    }

    /**
     * This function removes the enrollment limit for a class and then takes all students in the waitlist and moves them into the class
     */
    public void removeEnrollmentLimit(){
        this.hasEnrollementLimit = false;
//        for (Student student:studentsInWaitlist){
//            studentsInWaitlist.remove(student);apparently the code already does these?
//            student.enrollIn(this);
//            this.enrollIn(student);
//        }
    }

    //Getters and Setters
    public boolean getHasEnrollementLimit() {
        return hasEnrollementLimit;
    }

    public int getEnrollmentLimit(){
        return enrollmentLimit;
    }

    public Set<Student> getStudents(){
        return studentsEnrolledInClass;
    }

    public List<Student> getWaitList(){
        return studentsInWaitlist;
    }

    public void setCatalogNumber(String number){
        this.courseIdNumber = number;
    }

    public void setTitle(String title){
        this.courseIdName = title;
    }

}
