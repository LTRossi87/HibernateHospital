import management.system.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class HospitalTester {
    private static SessionFactory sessionFactory;
    
    public static void main(String[] args)
    {
        Patient patient = new Patient();
        patient.setFirstName("Brandon");
        patient.setLastName("Rossi");
        patient.setDOB("10/02/1987");
        
        Patient anotherPatient = new Patient();
        anotherPatient.setFirstName("Anna");
        anotherPatient.setLastName("Gonzales");
        anotherPatient.setDOB("10/17/1988");
        
        Doctor doctor = new Doctor();
        doctor.setFirstName("Dr. Ham");
        doctor.setLastName("Hawk");
        boolean spreturn1 = doctor.setSpecialty("O");
        
        Doctor anotherDoctor = new Doctor();
        anotherDoctor.setFirstName("Dr. Pork");
        anotherDoctor.setLastName("Rine");
        boolean spreturn2 = anotherDoctor.setSpecialty("D");
        System.out.println(spreturn1 + " " + spreturn2);
        Session session = null;
        
        Transaction transaction = null;
        
        try {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            
            session.save(patient);
            session.save(anotherPatient);
            session.save(doctor);
            session.save(anotherDoctor);
            
            transaction.commit();
        } 
        catch (HibernateException hibernateException)
        {
            transaction.rollback();
            System.out.println("Transaction is rolled back.");
            hibernateException.printStackTrace();
            
        }
        finally
        {
            session.close();
            sessionFactory.close();
            System.exit(0);
        }
    }
}
