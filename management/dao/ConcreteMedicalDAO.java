package management.dao;

import java.util.List;
import management.model.Appointment;
import management.utils.HibernateUtil;
import management.model.Doctor;
import management.model.Patient;
import management.model.Prescription;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.Query;

/*
    Brandon Rossi
    CS 157B
    Spring 2015
    Assignment 1
*/

public class ConcreteMedicalDAO implements MedicalDAO {

    private static SessionFactory sessionFactory;
    private static Session session;
    private static Transaction transaction;

    public ConcreteMedicalDAO() {
        try {
            this.sessionFactory = HibernateUtil.getSessionFactory();
        } catch (HibernateException hibernateException) {
            System.out.println("Problem getting Hibernate Session Factory");
            System.exit(1);
        }
        this.session = null;
        this.transaction = null;
    }

    @Override
    public void persistDoctor(Doctor doctor) {
        transaction = session.beginTransaction();
        session.save(doctor);
        transaction.commit();
    }

    @Override
    public Doctor findDoctorByName(String firstName, String lastName) {
        Query query;
        query = session.getNamedQuery("Doctor.findByName");
        query.setString("fname", firstName);
        query.setString("lname", lastName);
        Doctor doctor = (Doctor) query.uniqueResult();
        return doctor;
    }

    @Override
    public List<Doctor> findDoctorBySpecialty(String specialty) {
        Query query;
        query = session.getNamedQuery("Doctor.findBySpecialty");
        query.setString("specialty", specialty);
        List<Doctor> doctor = query.list();
        return doctor;
    }

    @Override
    public void deleteDoctor(Doctor doctor) {
        transaction = session.beginTransaction();
        session.delete(doctor);
        transaction.commit();
    }

    @Override
    public void persistPatient(Patient patient) {
        transaction = session.beginTransaction();
        session.save(patient);
        transaction.commit();
    }

    @Override
    public Patient findPatient(String firstName, String lastName, String dateOfBirth) {
        Query query;
        query = session.getNamedQuery("Patient.findByNameAndDOB");
        query.setString("fname", firstName);
        query.setString("lname", lastName);
        query.setString("dob", dateOfBirth);
        Patient patient = (Patient) query.uniqueResult();
        return patient;
    }

    @Override
    public void deletePatient(Patient patient) {
        transaction = session.beginTransaction();
        session.delete(patient);
        transaction.commit();
    }

    @Override
    public void persistAppointment(Appointment appointment) {
        transaction = session.beginTransaction();
        session.save(appointment);
        transaction.commit();
    }

    @Override
    public void persistPrescription(Prescription prescription) {
        transaction = session.beginTransaction();
        session.save(prescription);
        transaction.commit();
    }

    @Override
    public void deleteAppointment(int id)//Appointment appointment)
    {
        try {
            transaction = session.beginTransaction();
            this.session.clear();
            Appointment apt = (Appointment) session.get(Appointment.class, id);
            this.session.delete(apt);
            transaction.commit();
        } catch (HibernateException hibernateException) {
            System.out.println("Problem Deleting Appointment");
            hibernateException.printStackTrace();
            System.exit(1);
        }
    }

    @Override
    public void openCurrentSessionWithTransaction() {
        try {
            this.session = sessionFactory.openSession();
        } catch (HibernateException hibernateException) {
            System.out.println("Problem Opening Session or Begining Transaction");
            System.exit(1);
        }
    }

    @Override
    public void closeCurrentSessionWithTransaction() {
        try {
            this.session.close();
        } catch (HibernateException hibernateException) {
            System.out.println("Problem Commiting Transaction or Closing Session");
            transaction.rollback();
            hibernateException.printStackTrace();
            System.exit(1);
        }

    }

    @Override
    public void closeSessionFactory() {
        try {
            this.sessionFactory.close();
        } catch (HibernateException hibernateException) {
            System.out.println("Problem Closing Session Factory");
            System.exit(1);
        } finally {
            System.exit(0);
        }
    }

    @Override
    public List<Doctor> viewAllDoctors() {
        Query query;
        query = session.getNamedQuery("Doctor.getAllDoctors");
        List<Doctor> doctors = query.list();
        return doctors;
    }
}
