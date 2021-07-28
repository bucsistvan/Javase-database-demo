package hu.ulyssys.java.course.database.jpa;

import hu.ulyssys.java.course.database.jpa.dao.CustomerDAO;
import hu.ulyssys.java.course.database.jpa.entity.Customer;
import org.hibernate.usertype.CompositeUserType;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class JPAMain {
    public static void main(String[] args) {
        CustomerDAO customerDAO = new CustomerDAO();
        Customer customer = new Customer();
        customer.setFullName("Nagy Bálint");
        customer.setCash(1);
        customer.setUsername("asdw");
        customerDAO.save(customer);
        customerDAO.findAll().forEach(customer1 -> {
            System.out.println(customer1.getId() + " " + customer1.getFullName() + " " +customer1.getUsername());
        });

        customerDAO.findByName("Szüts Bálint").forEach(customer1 -> {
            customer1.setFullName("Kecske"+System.currentTimeMillis());
            customerDAO.update(customer1);
        });
        customerDAO.findAll().forEach(customer1 -> {
            System.out.println(customer1.getId() + " " + customer1.getFullName() + " " + customer1.getUsername());
        });

    }
}
