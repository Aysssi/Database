package entities.hcf_ex;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table (name = "students")
public class Student extends Person {

    private double grade;
    private Set<Course> coursesSet;

    public Student() {
    }
    @ManyToMany(mappedBy = "studentsSet" ,targetEntity = Course.class)
    public Set<Course> getCoursesSet() {
        return coursesSet;
    }

    public void setCoursesSet(Set<Course> coursesSet) {
        this.coursesSet = coursesSet;
    }

    @Column(name = "grade")
    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }
}
