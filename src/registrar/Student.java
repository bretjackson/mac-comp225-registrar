package registrar;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Collections;

/**
 * Created by bjackson on 2/21/2016.
 */
public class Student {

    public String name;
    public Set<Course> courses;

    public Student(){
        courses = new HashSet<>();
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Set<Course> getCourses(){
        return Collections.unmodifiableSet(courses);
    }

    public boolean enrollIn(Course course){
        if (course.enroll(this)){
            courses.add(course);
            return true;
        }
        else {
            return false;
        }
    }

    public void drop(Course course){
        courses.remove(course);
        course.dropStudent(this);
    }

    @Override
    public String toString(){
        return getName();
    }
}
