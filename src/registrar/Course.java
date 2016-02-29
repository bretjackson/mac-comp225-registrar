package registrar;

import java.util.Collections;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.LinkedList;

/**
 * Created by bjackson on 2/21/2016.
 * Keeps track of students enrolled in course and manages the course wait list.
 */
public class Course {
    private String catalogNumber;
    private String title;
    private Integer enrollmentLimit;

    private Set<Student> enrolled;
    private List<Student> waitList;

    public Course(){
        this(null, null, Integer.MAX_VALUE);
    }

    public Course(String catalogNumber, String title, Integer enrollmentLimit) {
        this.catalogNumber = catalogNumber;
        this.title = title;
        this.enrollmentLimit = enrollmentLimit;
        enrolled = new HashSet<Student>();
        waitList = new LinkedList<Student>();
    }

    public void setCatalogNumber(String number){
        this.catalogNumber = number;
    }

    public String getCatalogNumber() {
        return catalogNumber;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public Integer getEnrollmentLimit(){
        return this.enrollmentLimit;
    }

    /* Changes enrollment limit and puts waitlisted student in any extra spots.
     * @param New enrollment limit, a null value removes the limit
     * @return Successful if there aren't more enrolled students than the new limit
     */
    public boolean setEnrollmentLimit(Integer limit){
        //You can't set the limit below the number of currently enrolled students
        if (limit >= this.enrolled.size()){
            this.enrollmentLimit = limit;
            while (!this.waitList.isEmpty() && enrolled.size() < this.enrollmentLimit) {
                this.enrollNextWaitListed();
            }
            return true;
        }
        else return false;
    }

    public boolean hasEnrollmentLimit() {
        if (this.enrollmentLimit == Integer.MAX_VALUE) return false;
        else return true;
    }

    /*
     * Removes enrollment limit and enrolls any waitlisted students.
     */
    public void removeEnrollmentLimit() {
        this.enrollmentLimit = Integer.MAX_VALUE;
        while (!this.waitList.isEmpty()) {
            this.enrollNextWaitListed();
        }
    }

    public Set<Student> getStudents(){
        return Collections.unmodifiableSet(enrolled);
    }

    public List<Student> getWaitList(){
        return Collections.unmodifiableList((List) waitList);
    }

    private void addToWaitList(Student student) {
        if (!waitList.contains(student)){
            waitList.add(student);
        }
    }

    /*
     * Enrolls student in course or adds student to wait list if enrollment limit reached.
     * @return true if student already enrolled or successfully enrolled, false if student added to wait list
     */
    public boolean enroll(Student student){
        // the enrollment limit isn't reached or the student is already enrolled,enroll student
        // if not already enrolled and return true
        if (enrolled.size() < enrollmentLimit || enrolled.contains(student)) {
            enrolled.add(student);
            return true;
        } else {
            addToWaitList(student);
            return false;
        }
    }

    private void enrollNextWaitListed() {
        if (waitList.size() > 0) {
            Student nextStudent = waitList.remove(0);
            enrolled.add(nextStudent);
            nextStudent.enrolledCourses.add(this);
        }
    }

    /*
     * Removes student from set of enrolled students or wait list if in either
     */
    public void dropStudent(Student student){
        if (enrolled.contains(student)) {
            enrolled.remove(student);
            student.drop(this);
            enrollNextWaitListed();
        }
        else if (waitList.contains(student)){
            waitList.remove(student);
        }
    }

}
