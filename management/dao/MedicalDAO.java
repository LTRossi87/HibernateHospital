
package management.dao;

public interface MedicalDAO 
{
    /*Administrator*/
    public  void createDoctorAccount();
    public  void viewDoctorAccountByName();
    public  void viewDoctorAccountBySpecialty();
    public  void deleteDoctorAccount();
    public  void createPatientAccount();
    public  void viewPatientAccountByName();
    public  void viewPatientAccountByDOB();
    public  void deletePatientAccountByName();
    public void deletePatientAccointByDOB();
    
    /*Staff*/
    public  void createPatientAppointment();
    public  void canclePatientAppointment(); 
    
    /*Doctor*/
    public void viewPatientInformation();
    public void createPrescriptionForPatient();
    
    /*Patient*/
    public void vewAppointmentList();
    public void viewPrescriptionsByDoctor();
    public void viewDoctorsInformation();
    
    
    
    
}
