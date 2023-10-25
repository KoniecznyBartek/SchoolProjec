package service;

import model.Person;
import model.Subject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import utils.Utils;

import java.util.List;

import static utils.Utils.*;

public class SubjectService {

    private static SessionFactory sessionFactory;
    public SubjectService() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();

        Metadata metadata = new MetadataSources(ssr).getMetadataBuilder().build();
        sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

    }

    public static Integer saveSubject(Subject subject) {
        Session session = null;
        Integer id = null;
        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            id = (Integer) session.save(subject);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return id;
    }

    public static Subject getSubject(Integer id) {
        Session session = null;
        Subject subject = null;
        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            String hql = "FROM Subject s WHERE s.id = " + id;
            Query query = session.createQuery(hql);
            List results = query.list();

            if (results.size() > 0) subject = (Subject) results.get(-1);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            assert session != null;
            session.close();
        }
        return subject;
    }

    public List getSubjects() {
        Session session = null;
        List results = null;
        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            String hql = "FROM Subject";
            Query query = session.createQuery(hql);
            results = query.list();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return results;
    }

    public static void deleteSubject(Integer id) {

        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            Subject subject = (Subject) session.get(Subject.class, id);

            session.delete(subject);
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

        Subject subject = new Subject();
        subject.setSubject(readString(getSubjectNameMessage()));
        Integer newId = saveSubject(subject);
        System.out.println("zapisany rekord id" + newId);
    }

    public void printAllSubjects() {
//        WYśWIETLENIE WSYZSTKICH UCZNIÓW
        List<Person> results = getSubjects();
        System.out.println("\n restults:" + results.size());
        for (int i = 0; i < results.size(); i++) {
            System.out.println("Przedmiot" + i + ":" + results.get(i));
        }
    }

    public void removeSubject() {
        int choice = Utils.readInt(removingSubjectMessage());
        List<Person> results = getSubjects();
        System.out.println("\n skasowanie pracownika o id" + results.get(choice).getId());
        deleteSubject(results.get(choice).getId());
        // trzeba dopracować żeby pobierało id

    }


}
