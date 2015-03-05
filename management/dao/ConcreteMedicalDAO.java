package management.dao;
import java.util.List;

import management.utils.HibernateUtil;
import management.model.Doctor;
import management.model.Patient;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.Query;

public class ConcreteMedicalDAO implements MedicalDAO
{

    private static SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;
    
    public ConcreteMedicalDAO()
    {
        try
        {
            this.sessionFactory  = HibernateUtil.getSessionFactory();
        }
        catch(HibernateException hibernateException)
        {
            System.out.println("Problem getting Hibernate Session Factory");
            System.exit(1);
        } 
        this.session = null;
        this.transaction = null;
    }
    
    @Override
    public void persistDoctor(Doctor doctor) 
    {
        session.save(doctor);
    }

    @Override
    public Doctor findDoctorByName(String firstName, String lastName) 
    {
        Query query;
        query = session.getNamedQuery("Doctor.findByName");
        query.setString("fname", firstName);
        query.setString("lname", lastName);
        Doctor doctor = (Doctor)query.uniqueResult();
        System.out.println(doctor.getFirstName());
        return doctor;
    }

    @Override
    public List<Doctor> findDoctorBySpecialty(String specialty) 
    {
        Query query;
        query = session.getNamedQuery("Doctor.findBySpecialty");
        query.setString("specialty", specialty);
        List<Doctor> doctor = query.list();
        return doctor;
    }

    @Override
    public void deleteDoctor(Doctor doctor) 
    {
        session.delete(doctor);
    }

    @Override
    public void persistPatient(Patient patient) 
    {
        session.save(patient);
    }

    @Override
    public Patient findPatient(String firstName, String lastName, String dateOfBirth) 
    {
        Query query;
        query = session.getNamedQuery("Patient.findByNameAndDOB");
        query.setString("fname", firstName);
        query.setString("lname", lastName);
        query.setString("dob", dateOfBirth);
        Patient patient = (Patient)query.uniqueResult();
        System.out.println(patient.getFirstName());
        return patient;
    }

    @Override
    public void deletePatient(Patient patient) 
    {
        session.delete(patient);
    }

    @Override
    public void openCurrentSessionWithTransaction() 
    {
        try 
        {
            this.session = sessionFactory.openSession();
            this.transaction = session.beginTransaction();
        } 
        catch (HibernateException hibernateException) 
        {
            System.out.println("Problem Opening Session or Begining Transaction");
            System.exit(1);
        }
    }

    @Override
    public void closeCurrentSessionWithTransaction() 
    {
        try 
        {
            this.transaction.commit();
            this.session.close();
        } 
        catch (HibernateException hibernateException) 
        {
            System.out.println("Problem Commiting Transaction or Closing Session");
            transaction.rollback();
            hibernateException.printStackTrace();
            System.exit(1);
        }
        
    }
    
    @Override
    public void closeSessionFactory()
    {
        try 
        {
            this.sessionFactory.close();
        } 
        catch (HibernateException hibernateException)
        {
            System.out.println("Problem Closing Session Factory");
            System.exit(1);
        }
        finally
        {
            System.exit(0);
        }
        
    }
}
