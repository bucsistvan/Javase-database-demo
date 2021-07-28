package hu.ulyssys.java.course.database.hibernate;

import hu.ulyssys.java.course.database.hibernate.entity.AbstractPerson;
import hu.ulyssys.java.course.database.hibernate.entity.Student;
import hu.ulyssys.java.course.database.hibernate.entity.Teacher;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.List;

public class SchoolMain {

    public static void main(String[] args) {
        Teacher teacher = new Teacher();
        teacher.setFirstName("Sára");
        teacher.setLastName("Kovács");
        teacher.setCoursesNumber(13L);
        Long id = insertTeacher(teacher);
        Teacher persisTeacher = findByIdT(id);

        Student student = new Student();
        student.setFirstName("Anna");
        student.setLastName("Nagy");
        student.setFriendsNumber(2L);
        Long id2 = insertStudent(student);

        Student persisStudent = findByIdS(id);
        persisTeacher.setFirstName("test");
        persisTeacher.setLastName("Cool name");
        update(persisTeacher);
        findAllT().forEach(teacher1 -> {
            System.out.println(teacher1.getId()+" "+teacher1.getFirstName()+" "+teacher1.getLastName());
        });

        findAllS().forEach(SchoolMain::writeOut);
        findAllT().forEach(teacher1 -> System.out.println(teacher1.getId() + ". Teacher: " + teacher1.getFirstName() + " " + teacher1.getLastName()));
    }

    private static void writeOut(Student student) {
        System.out.println(student.getId() + ". Student: " + student.getFirstName() + " " + student.getLastName());
    }

    private static List<Teacher> findAllT(){
        Session session = DatabaseSessionProvider.getInstance().getSessionObj();
        Query<Teacher> query = session.createQuery("SELECT n from Teacher n", Teacher.class);
        return query.getResultList();
    }

    private static List<Student> findAllS(){
        Session session = DatabaseSessionProvider.getInstance().getSessionObj();
        Query<Student> query = session.createQuery("SELECT n from Student n", Student.class);
        return query.getResultList();
    }

    private static List<AbstractPerson> findAll(Class clazz){
        return DatabaseSessionProvider.getInstance().getSessionObj().createQuery("SELECT n from "+clazz.getSimpleName()+" n", clazz).getResultList();
    }

    private static void update(Teacher teacher) {
        Session session = DatabaseSessionProvider.getInstance().getSessionObj();
        session.beginTransaction();
        session.update(teacher);
        session.getTransaction().commit();
    }

    private static void update(Student student) {
        Session session = DatabaseSessionProvider.getInstance().getSessionObj();
        session.beginTransaction();
        session.update(student);
        session.getTransaction().commit();
    }

    private static Teacher findByIdT(Long id) {
        Session session = DatabaseSessionProvider.getInstance().getSessionObj();
        return session.find(Teacher.class, id);
    }

    private static Student findByIdS(Long id) {
        Session session = DatabaseSessionProvider.getInstance().getSessionObj();
        return session.find(Student.class, id);
    }

    private static Long insertTeacher(Teacher teacher) {
        Session session = DatabaseSessionProvider.getInstance().getSessionObj();
        session.beginTransaction();
        session.save(teacher);
        session.getTransaction().commit();
        return teacher.getId();
    }

    private static Long insertStudent(Student student) {
        Session session = DatabaseSessionProvider.getInstance().getSessionObj();
        session.beginTransaction();
        session.save(student);
        session.getTransaction().commit();
        return student.getId();
    }
}
