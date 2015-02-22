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
        Prescription prescription = new Prescription();
        Prescription prescription1 = new Prescription();
        Appointment appointment = new Appointment();
        Doctor doctor = new Doctor();
        
        patient.setFirstName("Brandon");
        patient.setLastName("Rossi");
        patient.setDOB("10/02/1987");
        prescription.setRx("Happy Meds");
        prescription1.setRx("Really Happy Meds");
        
        appointment.setAppointmentDate("10/2/2015");
        
        
        doctor.setFirstName("Dr. Ham");
        doctor.setLastName("Hawk");
        boolean spreturn1 = doctor.setSpecialty("O");
        
        prescription.setDoctor(doctor);
        prescription.setPatient(patient);
        prescription1.setDoctor(doctor);
        prescription1.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        
        doctor.setPatient(patient);
        
        
        
        patient.setAppointment(appointment);
        patient.setPrescription(prescription);
        patient.setPrescription(prescription1);
        
        
        
        Session session = null;  
        Transaction transaction = null;
      
        try {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            
            session.save(patient);
            
            session.save(doctor);
            
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
            System.exit(0);
        }
    }
}
