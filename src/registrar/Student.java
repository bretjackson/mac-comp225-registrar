package registrar;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by bjackson on 2/21/2016.
 */
public class Student {

<<<<<<< HEAD
    private String name;
    private Set<Course> courses;

    public Student(){
        courses = new HashSet<>();
    }

    public String getName() {
        return name;
=======
    public String name;
    public Set<Course> coursesEnrolled;
    // Set<Course> changed from 'coursesEnrolled' to 'coursesEnrolled'

    public Student(){
        coursesEnrolled = new HashSet<>();
>>>>>>> 0e3b9fbefe50c96d1c8f121f2237cf47445b5122
    }

    public void setName(String name){
        this.name = name;
    }

    public Set<Course> getCourses(){
<<<<<<< HEAD
        return Collections.unmodifiableSet(courses);
    }

    public boolean enrollIn(Course course) {
        if (course.enroll(this)) {
            courses.add(course);
=======
        return coursesEnrolled;
    }

    /*
    public boolean enrollIn(Course c){
        if(c.enrollIn(this)) {
            coursesEnrolled.add(c);
>>>>>>> 0e3b9fbefe50c96d1c8f121f2237cf47445b5122
            return true;
        }
        else {
            return false;
        }
    }
    */
    
    // The enrollIn method above should be replaced by the one below.
    
    public boolean enrollIn(Course c){
    	if(c.enrollStudent(this)) {
    		coursesEnrolled.add(c);
    		System.out.println("You've successfully enrolled in the course");
    		return true;
    	}
    	else {
    		System.out.println( c.enrollStudent(this)); //this prints out why enrollment failed.
    		return false;
    	}
    }

<<<<<<< HEAD
    public void drop(Course course) {
        courses.remove(course);
        course.dropStudent(this);
    }

    @Override
    public String toString() {
        return getName();
=======
    public void drop(Course c){
        if (coursesEnrolled.contains(c)) {
            coursesEnrolled.remove(c);
            c.dropStudent(this); // this line moved into the if statement.
        }
>>>>>>> 0e3b9fbefe50c96d1c8f121f2237cf47445b5122
    }
}










