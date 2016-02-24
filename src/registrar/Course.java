package registrar;

// Seems pretty minimal..

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
    private String catalog_number;
    private String title;
    private int max_size; 

    public Course(){
        enrolledIn = new HashSet<>();
        waitlist = new ArrayList<>();
        max_size = 0;
        catalog_number = "";
        title = "";
    }

    public void setCatalogNumber(String catalog_number){
        this.catalog_number = catalog_number;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public int getEnrollmentLimit(){
        return this.max_size;
    }

    public boolean setEnrollmentLimit(int max_size){
        //If students are enrolled you can't change the max_size
        if (enrolledIn.size() == 0){
            this.max_size = max_size;
            return true;
        }
        return false;
    }

    public Set<Student> getStudents(){
        return this.enrolledIn;
    }

    public List<Student> getWaitList(){
        return this.waitlist;
    }

    // Adds the student to the course or the waitlist
    public boolean enrollIn(Student student){
        if (this.enrolledIn.contains(student)){
            return true;
        }else if (this.enrolledIn.size() >= this.max_size){
            this.addToWaitlist(student);
            return false;
        } else {
            this.enrolledIn.add(student);
            return true;
        }
    }

    private void addToWaitlist(Student student) {
            if (!this.waitlist.contains(student)){
                    this.waitlist.add(student);
            }
    }


    // Removes the student from the course or the waitlist
    public void dropStudent(Student student){
        if (this.enrolledIn.contains(student)) {
            this.enrolledIn.remove(student);
            this.enrollFromWaitlist();
        }
        else if (waitlist.contains(student)){
            waitlist.remove(student);
        }
    }

    // Add the first member of the waitlist to the class
    private void enrollFromWaitlist() {
            if (this.waitlist.size() > 0 && this.enrolledIn.size() < this.max_size) {
                Student toEnroll = this.waitlist.remove(0);
                this.enrolledIn.add(toEnroll);
                toEnroll.enrolledIn.add(this); // if this returns false there is a large error
            }
    }

}
