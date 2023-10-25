package service;

import model.Person;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import utils.Utils;
import java.util.List;

import static utils.Utils.*;


public class PersonService {
    private static SessionFactory sessionFactory;

    public PersonService() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();

        Metadata metadata = new MetadataSources(ssr).getMetadataBuilder().build();
        sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

    }

    public static Integer savePerson(Person person) {
        Session session = null;
        Integer id = null;
        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            id = (Integer) session.save(person);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return id;
    }

    public static Person getPerson(Integer id) {
        Session session = null;
        Person person = null;
        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            String hql = "FROM Person p WHERE p.id = " + id;
            Query query = session.createQuery(hql);
            List results = query.list();

            if (results.size() > 0) person = (Person) results.get(-1);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            assert session != null;
            session.close();
        }
        return person;
    }

    public List getPeople() {
        Session session = null;
        List results = null;
        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            String hql = "FROM Person";
            Query query = session.createQuery(hql);
            results = query.list();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return results;
    }

    public void updatePerson(Integer id, String fname, String lname, Integer age, String mail) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            Person person = (Person) session.get(Person.class, id);
            person.setFname(fname);
            session.update(person);
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) transaction.rollback();
        } finally {
            if (session != null) session.close();
        }
    }

    public static void deletePerson(Integer id) {

        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            Person person = (Person) session.get(Person.class, id);

            session.delete(person);
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) transaction.rollback();
        } finally {
            if (session != null) session.close();
        }
    }

    public static void addPerson() {
        //        DODANIE I ZAPISANIE UCZNIA

        Person person = new Person();
        person.setFname(readString(getPersonFnameMessage()));
        person.setLname(readString(getPersonLnameMessage()));
        person.setAge(readInt(getPersonAgeMessage()));
        person.setMail(readString(getPersonMailMessage()));
        Integer newId = savePerson(person);
        System.out.println("zapisany rekord id" + newId);
    }

    public void printBase() {
//        WYśWIETLENIE WSYZSTKICH UCZNIÓW
        List<Person> results = getPeople();
        System.out.println("\n restults:" + results.size());
        for (int i = 0; i < results.size(); i++) {
            System.out.println("Student" + i + ":" + results.get(i));
        }
    }

    public void removePerson() {
        int choice = Utils.readInt(removingNameMessage());
        List<Person> results = getPeople();
        System.out.println("\n skasowanie pracownika o id" + results.get(choice).getId());
        deletePerson(results.get(choice).getId());
        // trzeba dopracować żeby pobierało id

    }
}


