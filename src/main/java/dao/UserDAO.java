package dao;

import hibernate.util.HibernateUtil;
import model.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
    if(!doesPhoneNumberExist(user.getPhoneNumber())) {
      hibernateUtil.save(user);
    }
  }

  public void deleteUser(long userId) {
    hibernateUtil.delete(User.class, userId);
  }

  public void deleteAllUsers(){
    long userCount = getUserCount();
    for (long i = 1; i <= userCount; i++) {
      hibernateUtil.delete(User.class, i);
    }

  }

  public List<User> getUserBySurname(String surname) {
    TypedQuery<User> query = entityManager.createQuery("select u from User u where u.surname = :surname", User.class);
    return query.setParameter("surname", surname).getResultList();
  }

  public User getOldestUserWithPhoneNumber(){
    TypedQuery<User> typedQuery = entityManager
            .createQuery("select u from User u where not(u.phoneNumber is null) order by u.age desc",User.class).setMaxResults(1);
    return typedQuery.getSingleResult();
  }

  public long getUserCount(){
    TypedQuery<Long> typedQuery = entityManager.createQuery("select count(u) from User u ",Long.class);
    return typedQuery.getSingleResult();
  }

  public List<User> getUserByName(String name) {
    TypedQuery<User> query = entityManager.createQuery("select u from User u where u.name = :name", User.class);
    return query.setParameter("name", name).getResultList();
  }

  public List<User> getAllUsers(){
    TypedQuery<User> typedQuery = entityManager.createQuery("select u from User u", User.class);
    return typedQuery.getResultList();
  }

  public List<User> getAllUsersOrderedByAge(){
    TypedQuery<User> typedQuery = entityManager.createQuery("select u from User u order by u.age desc", User.class);
    return typedQuery.getResultList();
  }

  public boolean doesPhoneNumberExist(String phoneNumber){
    TypedQuery<User> typedQuery = entityManager
            .createQuery("select u from User u where u.phoneNumber= :phoneNumber",User.class);
    try{
      return null != typedQuery.setParameter("phoneNumber", phoneNumber).getSingleResult();
    }catch(NoResultException e){
      return false;
    }
  }

}
