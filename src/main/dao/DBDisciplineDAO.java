package main.dao;

import main.models.Discipline;
import main.models.Role;
import main.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class DBDisciplineDAO implements DisciplineDAO{

    @Override
    public Discipline findById ( int id ) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Discipline.class, id);
    }

    @Override
    public void save ( Discipline arg ) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(arg);
        transaction.commit();
        session.close();
    }

    @Override
    public void remove ( Discipline arg ) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.remove(arg);
        transaction.commit();
        session.close();
    }

    @Override
    public void update ( Discipline arg ) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(arg);
        transaction.commit();
        session.close();
    }

    @Override
    public List<Discipline> findAll ( ) {
        List<Discipline> disciplines = (List<Discipline>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Discipline").list();
        return disciplines;
    }

    public Discipline findByCode(String code){
        var query = HibernateSessionFactoryUtil.getSessionFactory()
                .openSession().createQuery("FROM Discipline as d WHERE d.disciplineCode=:param");
        query.setParameter("param", code);
        if (query.list().isEmpty()) {
            return null;
        }
        return (Discipline) query.list().get(0);
    }

    public Discipline findByName(String name){
        var query = HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Discipline WHERE name=:param");
        query.setParameter("param", name);
        if (query.list().isEmpty()) {
            return null;
        }
        return (Discipline) query.list().get(0);
    }
}
