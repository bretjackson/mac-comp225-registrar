package registrar;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by bjackson on 2/21/2016.
 */
public class Student {

    public String name;
    public Set<Course> coursesEnrolled;
    // Set<Course> changed from 'coursesEnrolled' to 'coursesEnrolled'

    public Student(){
        coursesEnrolled = new HashSet<>();
    }

    public void setName(String name){
        this.name = name;
    }

    public Set<Course> getCourses(){
        return coursesEnrolled;
    }

    /*
    public boolean enrollIn(Course c){
        if(c.enrollIn(this)) {
            coursesEnrolled.add(c);
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

    public void drop(Course c){
        if (coursesEnrolled.contains(c)) {
            coursesEnrolled.remove(c);
            c.dropStudent(this); // this line moved into the if statement.
        }
    }
}
