
package management.dao;

import java.util.List;
import management.model.Appointment;
import management.model.Doctor;
import management.model.Patient;
import management.model.Prescription;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public interface MedicalDAO 
{
    public void persistDoctor(Doctor doctor);
    
    public Doctor findDoctorByName(String firstName, String lastName);
    public List<Doctor> findDoctorBySpecialty(String specialty);
    public void deleteDoctor(Doctor doctor);
    
    public void persistPatient(Patient patient);
    public void persistAppointment(Appointment appointment);
    public void persistPrescription(Prescription prescription);
    public List<Doctor> viewAllDoctors();
    public void deleteAppointment(int id);//Appointment appointment);
    
    public Patient findPatient(String firstName, String lastName, String dateOfBirth);
    public void deletePatient(Patient patient);
    
    public void openCurrentSessionWithTransaction();
    public void closeCurrentSessionWithTransaction();
    public void closeSessionFactory();        
}
