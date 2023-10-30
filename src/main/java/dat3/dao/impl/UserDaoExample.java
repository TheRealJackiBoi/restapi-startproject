package dat3.dao.impl;

import dat3.dao.CRUDDao;
import dat3.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class UserDaoExample extends CRUDDao<User, String> {

    private static UserDaoExample instance;

    public static UserDaoExample getInstance(EntityManagerFactory emf) {
        if (instance == null) {
            instance = new UserDaoExample();
            instance.setEntityManagerFactory(emf);
        }
        return instance;
    }

}