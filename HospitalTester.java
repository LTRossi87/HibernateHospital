import management.model.Doctor;
import management.model.Prescription;
import management.model.Appointment;
import management.model.Patient;
import management.utils.HibernateUtil;
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
        
        prescription1.setDoctor(doctor);
        
        appointment.setDoctor(doctor);
        
        doctor.setPatient(patient);
        patient.setAppointment(appointment);
        patient.setPrescription(prescription);
        patient.setPrescription(prescription1);
        
        
        Patient anotherPatient = new Patient();
        Prescription anotherPrescription = new Prescription();
        Prescription anotherPrescription1 = new Prescription();
        Appointment anotherAppointment = new Appointment();
        Doctor anotherDoctor = new Doctor();
        
        anotherPatient.setFirstName("Anna");
        anotherPatient.setLastName("Gonzales");
        anotherPatient.setDOB("10/17/1987");
        anotherPrescription.setRx("Supper Meds");
        anotherPrescription1.setRx("Really Supper Meds");
        anotherAppointment.setAppointmentDate("10/17/2015");
        anotherDoctor.setFirstName("Dr. Prok");
        anotherDoctor.setLastName("Rine");
        boolean spreturn2 = anotherDoctor.setSpecialty("F");
        anotherPrescription.setDoctor(anotherDoctor);
        anotherPrescription1.setDoctor(doctor);
        doctor.setPatient(anotherPatient);
        anotherDoctor.setPatient(anotherPatient);
        anotherAppointment.setDoctor(anotherDoctor);
        anotherPatient.setAppointment(anotherAppointment);
        anotherPatient.setPrescription(anotherPrescription);
        anotherPatient.setPrescription(anotherPrescription1);
        
        Session session = null;  
        Transaction transaction = null;
      
        try {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            
            session.save(patient);            
            
            session.save(doctor);
            session.save(anotherDoctor);
            session.save(anotherPatient);
            
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
