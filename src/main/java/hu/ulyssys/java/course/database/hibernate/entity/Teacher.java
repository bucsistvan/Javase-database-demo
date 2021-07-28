package hu.ulyssys.java.course.database.hibernate.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "teacher")
public class Teacher extends AbstractPerson implements Serializable {
    @Column(name = "courses_number")
    private Long coursesNumber;

    public Long getCoursesNumber() {
        return coursesNumber;
    }

    public void setCoursesNumber(Long coursesNumber) {
        this.coursesNumber = coursesNumber;
    }
}
