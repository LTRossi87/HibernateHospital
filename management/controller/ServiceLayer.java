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
        
        
        Doctor doctor = new Doctor();
        doctor.setFirst_name(firstName);
        doctor.setLast_name(lastName);
        doctor.setSpecialties(specialty);
        medicalDAO.persistDoctor(doctor);
        
        
    }
  
    public Doctor viewDoctorByName(String firstName, String lastName)
    {
        
        
        Doctor doctor = medicalDAO.findDoctorByName(firstName, lastName);
        
        
        return doctor;
    }
    
    public List<Doctor> viewDoctorBySpecialty(String specialty)
    {
        
        
        List<Doctor> listOfDoctors = medicalDAO.findDoctorBySpecialty(specialty);        
        
        
        return listOfDoctors;
    }
    
    public void deleteDoctor(String firstName, String lastName)
    {
        
        
        Doctor doctor = medicalDAO.findDoctorByName(firstName, lastName);
        medicalDAO.deleteDoctor(doctor);
        
    }
    
    public void createPatient(String firstName, String lastName, String dateOfBirth)
    {
        
        
        Patient patient = new Patient();
        patient.setFirst_name(firstName);
        patient.setLast_name(lastName);
        patient.setDob(dateOfBirth);
        medicalDAO.persistPatient(patient);

        
    }
    
    public Patient viewPatientAccount(String firstName, String lastName, String dateOfBirth)
    {
        
        
        Patient patient = medicalDAO.findPatient(firstName, lastName, dateOfBirth);
        
        
        return patient;
    }
    
    public void deletePatientAccount(String firstName, String lastName, String dateOfBirth)
    {
        
        
        Patient patient = medicalDAO.findPatient(firstName, lastName, dateOfBirth);
        medicalDAO.deletePatient(patient);
        
    }
    
    public void createAppointmentForPatient(String patientFirstName, 
                                            String patientLastName, 
                                            String patientDateOfBirth,
                                            String doctorFirstName,
                                            String doctorLastName,
                                            String appointmentDate)
    {
        
        
        Doctor doctor = medicalDAO.findDoctorByName(doctorFirstName, doctorLastName);
        Patient patient = medicalDAO.findPatient(patientFirstName, patientLastName, patientDateOfBirth);
        Appointment appointment = new Appointment();
        
        /*This may need to be moved out of here... Check with Dr. Kim */
        doctor.setPatient(patient);
        patient.setDoctors(doctor);
        
        appointment.setAppointmentDate(appointmentDate);
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        patient.setAppointment(appointment);
        doctor.setAppointment(appointment);
        
        //medicalDAO.persistDoctor(doctor);
        //medicalDAO.persistPatient(patient);
        medicalDAO.persistAppointment(appointment);
        
    }
    
    /*May need to impelement a method fo find the appointments of a patient*/
    
    public void cancelAppointment(String patientFirstName, 
                                  String patientLastName, 
                                  String patientDateOfBirth, 
                                  String appointment_date)
    {
        
        
        Patient patient = medicalDAO.findPatient(patientFirstName, patientLastName, patientDateOfBirth);
        List<Appointment> appointments = patient.getAppointments();
        
        for(Appointment appointment : appointments)
        {   
            System.out.println(appointment.getAppointmentDate() + " : " + appointment_date);
            if(appointment.getAppointmentDate().equals(appointment_date))
            { 
                
                medicalDAO.deleteAppointment(appointment.getId());
                
            }
        }
        
    }
    
    public void createPrescription(String patientFirstName, 
                                   String patientLastName, 
                                   String patientDateOfBirth,
                                   String doctorFirstName,
                                   String doctorLastName,
                                   String rX)
    {
        
        
        Doctor doctor = medicalDAO.findDoctorByName(doctorFirstName, doctorLastName);
        Patient patient = medicalDAO.findPatient(patientFirstName, patientLastName, patientDateOfBirth);

        Prescription prescription = new Prescription();
        prescription.setRxs(rX);
        prescription.setDoctors(doctor);
        prescription.setPatients(patient);
        //patient.setPrescription(prescription);
        //doctor.setPrescriptions(prescription);
        
        //medicalDAO.persistPatient(patient);
        //medicalDAO.persistDoctor(doctor);
        medicalDAO.persistPrescription(prescription);

        
    }
    
    public boolean isPatientOfDoctor(Doctor doctor, Patient patient)
    {
        List<Patient> patients = doctor.getPatients();
        for(Patient patient1: patients)
        {
            if(patient.getId()== patient1.getId())
            {
                System.out.println(patient.getId()+ ":" + patient1.getId());
                return true;
            }
        }
        return false;
    }
    
    public List<Doctor> viewAllDoctors()
    {
        
        List<Doctor> doctors =  medicalDAO.viewAllDoctors();
        
        return doctors;
        
    }
    
    public void closeSession()
    {
        
        medicalDAO.closeSessionFactory();
    }
    
    public void openCurrentSession()
    {
        medicalDAO.openCurrentSessionWithTransaction();
    }
    public void closeCurrentSession()
    {
        medicalDAO.closeCurrentSessionWithTransaction();
    }
}
