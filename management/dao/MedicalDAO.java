
package management.dao;

import java.util.List;
import management.model.Doctor;
import management.model.Patient;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public interface MedicalDAO 
{
    public void persistDoctor(Doctor doctor);
    
    public Doctor findDoctorByName(String firstName, String lastName);
    public List<Doctor> findDoctorBySpecialty(String specialty);
    public void deleteDoctor(Doctor doctor);
    
    public void persistPatient(Patient patient);
    
    public Patient findPatient(String firstName, String lastName, String dateOfBirth);
    public void deletePatient(Patient patient);
    
    public void openCurrentSessionWithTransaction();
    public void closeCurrentSessionWithTransaction();
    public void closeSessionFactory();
}
