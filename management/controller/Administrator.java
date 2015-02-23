package management.controller;
import management.model.Doctor;
import management.model.Patient;
import management.utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


public class Administrator 
{
    private static SessionFactory sessionFactory;
    
    
    
    public void createDoctor()
    {
        
    }
    
    public void viewDoctorAccountByName()
    {
        
    }
    
    public void viewDoctorAccountBySpecialty()
    {
        
    }
    
    public void deleteDoctorAccount()
    {
        
    }
    
    public void createPatientAccount()
    {
        
    }
    
    public void viewPatientAccountByName()
    {
        
    }
    
    public void viewPatientAccountByDOB()
    {
        
    }
    
    public void deletePatientAccount()
    {
        
    }
    
    public void commitToDatabase(Object object)
    {
        Session session = null;  
        Transaction transaction = null;
      
        try {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            
            if(object instanceof Doctor)
            {
                Doctor tempDoc = (Doctor)object;
                session.save(object);
            }
            else if(object instanceof Patient)
            {
                Patient patient = (Patient)object;
                session.save(patient);
            }

            transaction.commit();
        } 
        catch(HibernateException hibernateException)
        {
            transaction.rollback();
            System.out.println("Transaction is rolled back.");
            hibernateException.printStackTrace(); 
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        finally
        {
            session.close();
            sessionFactory.close(); 
        }
    }
}
