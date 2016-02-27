package registrar;

import java.util.*;

/**
 * Created by bjackson on 2/21/2016.
 */
public class Course {

    private Set<Student> studentsEnrolled;
    private List<Student> waitlist;
    private String catalogNumber;
    private String title;
    private int enrollmentLimit=16;

    /*
    Constructor for a course
     */
    public Course(){
        studentsEnrolled = new HashSet<>();
        waitlist = new ArrayList<>();
        setEnrollmentLimit(enrollmentLimit);
    }

    /**
     * @return returns the catalogNumber of a course
     */
    public String getCatalogNumber(){
        return catalogNumber;
    }

    /*
    Sets the catalog Number of a course
     */
    public void setCatalogNumber(String number){
        this.catalogNumber = number;
    }

    /**
     *
     * @return the title of a course
     */
    public String getTitle(){
        return title;
    }

    /**
     * sets the title of a course
     * @param title
     */
    public void setTitle(String title){
        this.title= title;
    }

    /**
     * Gets the enrollment limit for a course
     * @return the enrollment limit for a course
     */
    public int getEnrollmentLimit(){
        return enrollmentLimit;
    }

    /**
     * Sets the enrollment limit of a course
     * @param limit is the number of openings
     * @return false if the enrollment limit has not been set
     */
    public boolean setEnrollmentLimit(int limit){
        //If no students are enrolled, set enrollmentLimit to limit
        if (studentsEnrolled.size() == 0){
            this.enrollmentLimit = limit;
            return true;
        }
        //If the new limit is higher than the old limit, emmit more students into the course
        else if(studentsEnrolled.size()<limit){
            int additionalStudents=limit-studentsEnrolled.size();
            this.enrollmentLimit=limit;
            for (int i=0;i<=additionalStudents;i++){
                editWaitList();
            }
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Removes the enrollment limit of a course
     */
    public boolean removeEnrollmentLimit(){
        setEnrollmentLimit((int) Float.POSITIVE_INFINITY);
        if(enrollmentLimit==(int) Float.POSITIVE_INFINITY){
            return true;
        }
        return false;
    }

    /**
     * gets the students in enrolledIn
     * @return a set of students that are enrolled in the course
     */
    public Set<Student> getStudents(){
        return Collections.unmodifiableSet(studentsEnrolled);
    }

    /**
     *
     * @return the waitlist of students
     */
    public List<Student> getWaitList(){
        return Collections.unmodifiableList(waitlist);
    }

    /**
     * Enrolls a student in a course
     * @param student the student to be enrolled in a course
     * @return true if the student is successfully enrolled, false if they are added to the waitlist
     */
    public boolean enroll(Student student){
        //Checks to see if student is already enrolled
        if (studentsEnrolled.contains(student)){
            return true;
        }
        if (studentsEnrolled.size() >= enrollmentLimit){
            //Checks to see if student is already on the waitlist
            if (waitlist.contains(student)){
                return false;
            }
            waitlist.add(student);
            return false;
        }
        studentsEnrolled.add(student);
        return true;
    }

    /**
     * Drops a student from a class or from a class's wait list
     * @param student is a student to be removed
     */
    public void dropStudent(Student student){
        //If in a class, remove them
        if (studentsEnrolled.contains(student)) {
            studentsEnrolled.remove(student);
            editWaitList();
        }
        //If on the waitlist, remove them
        else if (waitlist.contains(student)){
            waitlist.remove(student);
        }
    }


    /**
     * Removes a student from the wait list and adds them to enrolled
     */
    public void editWaitList(){
        if (!waitlist.isEmpty()) {
            Student nextUp = waitlist.remove(0);
            studentsEnrolled.add(nextUp);
            nextUp.enrollInCourse(this);
        }
    }




}