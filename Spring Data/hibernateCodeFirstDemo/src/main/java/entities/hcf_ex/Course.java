package entities.hcf_ex;

import entities.BaseEntity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "courses")
public class Course extends BaseEntity {
    private String name;

    private Set<Student> studentsSet;
    private Teacher teacher;

    @ManyToOne
    @JoinColumn (name= "teacher_id", referencedColumnName = "id")
    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Course() {
    }

    @Column(name = "name",nullable = false, unique = true)
    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @ManyToMany
    @JoinTable(name = "courses_students_set", joinColumns = @JoinColumn(name ="course_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn (name= "student_id",referencedColumnName = "id"))

    public Set<Student> getStudentsSet() {
        return studentsSet;
    }

    public void setStudentsSet(Set<Student> studentsSet) {
        this.studentsSet = studentsSet;
    }
}
