package registrar;

import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableRangeSet;
import com.google.common.collect.ImmutableSet;

import javax.management.ImmutableDescriptor;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by bjackson on 2/21/2016.
 * enrollIn() and drop() both assume that theu will be called to enter Course.java, and never the other way around. Otherwise, end up with inequalities in student and course listings.
 */
public class Student {

    public String name;
    private Set<Course> enrolledIn;

    public Student(){
        enrolledIn = new HashSet<>();
    }

    public void setName(String name){
        this.name = name;
    }

    public Set<Course> getCourses(){
        return ImmutableSet.copyOf(enrolledIn);
    }

    /**
     * Moved majority of code from Course addStudent() here so that all additions to courses
     * and studentLists happen in the same method. More readable and trackable.
     **/
    public boolean enrollIn(Course c){
        Set<Student>studentsEnrolled = c.getStudents();
        List<Student> waitlist = c.getWaitList();
        int courseLimit = c.getEnrollmentLimit();

        if(studentsEnrolled.contains(this)){
            System.out.println(this.name + " is already enrolled.");
            addCourse(c);
            return true;
        }

        if(c.hasLimit() && studentsEnrolled.size() >= courseLimit){
            if(waitlist.contains(this)){
                System.out.println("Class is full. " + this.name + " is already waitlisted.");
                return false;
            }
            System.out.println("Class is full. " + this.name + " has been waitlisted.");
            waitlist.add(this);
            return false;
        }

        boolean success = c.addStudent(this);
        if(success){
            System.out.println(this.name + " has been enrolled.");
            addCourse(c);
            return true;
        }

        System.out.println("Error: Student not added");
        return false;
    }

    public void addCourse(Course c){
        enrolledIn.add(c);
    }

    /**
     *Moved some code from Course.dropStudent() here because the student drops the class,
     *The class doesn't drop the student. But the course still adds the next waitlisted
     *student to the course.
     **/
    public void drop(Course c){
            c.dropStudent(this);
            enrolledIn.remove(c);
            System.out.println(this.name + " has dropped " + c.getTitle() + ".");
    }
}
