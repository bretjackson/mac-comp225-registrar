package registrar;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by bjackson on 2/21/2016.
 */
public class Student {

    private String name;
    private Set<Course> courses;

    public Student(){
        courses = new HashSet<>();
    }

    public void setName(String name){
        this.name = name;
    }

    public Set<Course> getCourses(){
        return courses;
    }

    public boolean enrollIn(Course course){
        if(course.enrollIn(this)) {
            courses.add(course);
            return true;
        }
        else {
            return false;
        }
    }


    public void drop(Course course){
        if (courses.contains(course)) {
            courses.remove(course);
        }
        course.dropStudent(this);
    }

    public void addToCourses(Course course){
        courses.add(course);
    }

    public void removeFromCourses(Course course){
        courses.remove(course);
    }

}
