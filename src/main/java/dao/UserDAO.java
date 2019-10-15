package dao;

import hibernate.util.HibernateUtil;
import model.User;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class UserDAO{
  private final HibernateUtil hibernateUtil = HibernateUtil.getInstance();
  private final EntityManager entityManager = hibernateUtil.getEntityManager();
  public void saveUsers(List<User> users){
    users.forEach(user -> saveUser(user));
  }
  public void saveUser(User user) {
    hibernateUtil.save(user);
  }

  public void deleteUser(long userId) {
    hibernateUtil.delete(User.class, userId);
  }

  public List<User> getUserBySurname(String surname) {
    TypedQuery<User> query = entityManager.createQuery("select u from User u where surname = :surname", User.class);
    return query.setParameter("surname", surname).getResultList();
  }

  public List<User> getUserByName(String name) {
    TypedQuery<User> query = entityManager.createQuery("select u from User u where name = :name", User.class);
    return query.setParameter("name", name).getResultList();
  }

  public List<User> getAllUsers(){
    TypedQuery<User> typedQuery = entityManager.createQuery("select u from User u", User.class);
    return typedQuery.getResultList();
  }

}
