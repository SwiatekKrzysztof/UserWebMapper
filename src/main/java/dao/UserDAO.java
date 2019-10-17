package dao;

import hibernate.util.HibernateUtil;
import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.UserService;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class UserDAO{
  private static Logger logger = LogManager.getLogger(UserService.class);
  private final HibernateUtil hibernateUtil = HibernateUtil.getInstance();
  private final EntityManager entityManager = hibernateUtil.getEntityManager();
  public void saveUsers(List<User> users){
    users.forEach(user -> saveUser(user));
  }
  public void saveUser(User user) {
    if(!doesPhoneNumberExist(user.getPhoneNumber())) {
      hibernateUtil.save(user);
    }else{
      logger.warn(user.toString()+"that phone number is already taken");
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

  public List<User> getUsersBySurname(String surname) {
    TypedQuery<User> query = entityManager.createQuery("select u from User u where u.surname = :surname", User.class);
    return query.setParameter("surname", surname).getResultList();
  }

  public List<User> getOldestUserWithPhoneNumber(){
    TypedQuery<User> typedQuery;
    try{
      typedQuery = entityManager
              .createQuery("select u from User u where not(u.phoneNumber is null) order by u.age desc",User.class).setMaxResults(1);
      return typedQuery.getResultList();
    }catch(NoResultException e) {
      return null;
    }
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

  public List<User> getUsersOnPageOrderedById(int recordsPerPage, int currentPage){
    int start = currentPage*recordsPerPage-recordsPerPage;
    TypedQuery<User> typedQuery = entityManager.createQuery("select u from User u order by u.id asc", User.class);
    return typedQuery.getResultList().subList(start,start+recordsPerPage);
  }
  public List<User> getUsersOnPageOrderedByAge(int recordsPerPage, int currentPage){
    int start = currentPage*recordsPerPage-recordsPerPage;
    TypedQuery<User> typedQuery = entityManager.createQuery("select u from User u order by u.id desc ", User.class);
    return typedQuery.getResultList().subList(start,start+recordsPerPage);
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
