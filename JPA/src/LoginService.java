import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class LoginService {

    public boolean authenticateUser(String email, String pass) {
        User user = getUserByEmail(email);
        if(user!=null && user.getEmail().equals(email) && user.getPass().equals(pass)){
            return true;
        }else{
            return false;
        }
    }

    public User getUserByEmail(String email) {
        Session session = HibernateUtil.openSession();
        Transaction tx = null;
        User user = null;
        try {
            tx = session.getTransaction();
            tx.begin();
            Query query = session.createQuery("from User where email='"+email+"'");
            user = (User)query.uniqueResult();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return user;
    }
}

