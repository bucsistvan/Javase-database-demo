package hu.ulyssys.java.course.database.hibernate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "student")
public class Student extends AbstractPerson implements Serializable {
    @Column(name = "friends_number")
    private Long friendsNumber;

    public Long getFriendsNumber() {
        return friendsNumber;
    }

    public void setFriendsNumber(Long friendsNumber) {
        this.friendsNumber = friendsNumber;
    }
}
