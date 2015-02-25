package management.controller;
import java.util.List;
import management.dao.ConcreteMedicalDAO;
import management.model.Appointment;
import management.model.Doctor;
import management.model.Patient;
import management.model.Prescription;

public class ServiceLayer 
{
    ConcreteMedicalDAO medicalDAO;
    
    public ServiceLayer()
    {
        medicalDAO = new ConcreteMedicalDAO();
    }
    
    public void createDoctor(String firstName, String lastName, String specialty)
    {
        medicalDAO.openCurrentSessionWithTransaction();
        
        Doctor doctor = new Doctor();
        doctor.setFirstName(firstName);
        doctor.setLastName(lastName);
        doctor.setSpecialty(specialty);
        medicalDAO.persistDoctor(doctor);
        
        medicalDAO.closeCurrentSessionWithTransaction();
    }
  
    public Doctor viewDoctorByName(String firstName, String lastName)
    {
        medicalDAO.openCurrentSessionWithTransaction();
        
        Doctor doctor = medicalDAO.findDoctorByName(firstName, lastName);
        
        medicalDAO.closeCurrentSessionWithTransaction();
        return doctor;
    }
    
    public List<Doctor> viewDoctorBySpecialty(String specialty)
    {
        medicalDAO.openCurrentSessionWithTransaction();
        
        List<Doctor> listOfDoctors = medicalDAO.findDoctorBySpecialty(specialty);        
        
        medicalDAO.closeCurrentSessionWithTransaction();
        return listOfDoctors;
    }
    
    public void deleteDoctor(String firstName, String lastName)
    {
        medicalDAO.openCurrentSessionWithTransaction();
        
        Doctor doctor = medicalDAO.findDoctorByName(firstName, lastName);
        medicalDAO.deleteDoctor(doctor);
        
        medicalDAO.closeCurrentSessionWithTransaction();
    }
    
    public void createPatient(String firstName, String lastName, String dateOfBirth)
    {
        medicalDAO.openCurrentSessionWithTransaction();
        
        Patient patient = new Patient();
        patient.setFirstName(firstName);
        patient.setLastName(lastName);
        patient.setDOB(dateOfBirth);
        medicalDAO.persistPatient(patient);
        
        medicalDAO.closeCurrentSessionWithTransaction();
    }
    
    public Patient viewPatientAccount(String firstName, String lastName, String dateOfBirth)
    {
        medicalDAO.openCurrentSessionWithTransaction();
        
        Patient patient = medicalDAO.findPatient(firstName, lastName, dateOfBirth);
        
        medicalDAO.closeCurrentSessionWithTransaction();
        return patient;
    }
    
    public void deletePatientAccount(String firstName, String lastName, String dateOfBirth)
    {
        medicalDAO.openCurrentSessionWithTransaction();
        
        Patient patient = medicalDAO.findPatient(firstName, lastName, dateOfBirth);
        medicalDAO.deletePatient(patient);
        
        medicalDAO.closeCurrentSessionWithTransaction();
    }
    
    public void createAppointmentForPatient(String patientFirstName, 
                                            String patientLastName, 
                                            String patientDateOfBirth,
                                            String doctorFirstName,
                                            String doctorLastName,
                                            String appointmentDate)
    {
        medicalDAO.openCurrentSessionWithTransaction();
        
        Doctor doctor = medicalDAO.findDoctorByName(doctorFirstName, doctorLastName);
        Patient patient = medicalDAO.findPatient(patientFirstName, patientLastName, patientDateOfBirth);
        Appointment appointment = new Appointment();
        
        /*This may need to be moved out of here... Check with Dr. Kim */
        doctor.setPatient(patient);
        patient.setDoctor(doctor);
        
        appointment.setAppointmentDate(appointmentDate);
        appointment.setDoctor(doctor);
        patient.setAppointment(appointment);
        
        medicalDAO.persistDoctor(doctor);
        medicalDAO.persistPatient(patient);
        
        medicalDAO.closeCurrentSessionWithTransaction();
    }
    
    /*May need to impelement a method fo find the appointments of a patient*/
    
    public void cancelAppointment(String firstName, String lastName, String dateOfBirth)
    {
        medicalDAO.openCurrentSessionWithTransaction();
        //logic to delete an appointment
        medicalDAO.closeCurrentSessionWithTransaction();
    }
    
    public void createPrescription(String patientFirstName, 
                                   String patientLastName, 
                                   String patientDateOfBirth,
                                   Doctor doc,
                                   String rX)
    {
        medicalDAO.openCurrentSessionWithTransaction();
        
        Doctor doctor = doc;
        Patient patient = medicalDAO.findPatient(patientFirstName, patientLastName, patientDateOfBirth);
        Prescription prescription = new Prescription();
        prescription.setRx(rX);
        prescription.setDoctor(doctor);
        patient.setPrescription(prescription);
        
        medicalDAO.persistPatient(patient);
        
        medicalDAO.closeCurrentSessionWithTransaction();
    }
    
    public void closeSession()
    {
        
        medicalDAO.closeSessionFactory();
    }
}
