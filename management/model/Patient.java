package management.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="PATIENT_INFO")
@NamedQueries({
    @NamedQuery(name="Patient.findByNameAndDOB", 
                query="from Patient where first_name = :fname and last_name = :lname and date_of_birth = :dob")
})
public class Patient extends Person{
    
    @Column(name="date_of_birth")
    //@Temporal(TemporalType.DATE)
    private String patient_date_of_birth;
   
    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="PATIENT_DOCTOR", 
            joinColumns = {@JoinColumn(name="patient_id")},
            inverseJoinColumns = {@JoinColumn(name="doctor_id")})
    private List<Doctor> doctors;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinTable(name = "PATIENT_PRESCRIPTIONS", joinColumns = @JoinColumn(name = "patient_id"), inverseJoinColumns = @JoinColumn(name = "prescription_id"))
    private List<Prescription> prescriptions;
    
    @OneToMany(cascade=CascadeType.ALL)
    @JoinTable(name = "PATIENT_APPOINTMENTS", joinColumns = @JoinColumn(name = "patient_id"), inverseJoinColumns = @JoinColumn(name = "appointment_id"))
    private List<Appointment> appointments;
    
    public Patient()
    {
        doctors = new ArrayList<>();
        prescriptions = new ArrayList<>();
        appointments = new ArrayList<>();
    }
    
    public void cancelAppointment()//Need to have a paramater here... Not sure what though...
    {
        //Implement the deletion of an appointment from the list of appointments
    }
    
    public List<Appointment> getAppointments()
    {
        return this.appointments;
    }
    public void setAppointment(Appointment appointment)
    {
        appointments.add(appointment);
    }
    
    public List<Prescription> getPrescriptions()
    {
        return this.prescriptions;
    }
    public void setPrescription(Prescription prescription)
    {
        this.prescriptions.add(prescription);
    }
    
    public List<Doctor> getDoctors()
    {
        return this.doctors;
    }
    public void setDoctor(Doctor doctor)
    {
        this.doctors.add(doctor);
    }
    
    public String getDOB()
    {
        return this.patient_date_of_birth;
    }
    public void setDOB(String dob)
    {
        this.patient_date_of_birth = dob;
    }
    
    public String toString()
    {
        StringBuilder patient_to_string = new StringBuilder();
        patient_to_string.append(this.id);
        patient_to_string.append("\n");
        patient_to_string.append(this.first_name);
        patient_to_string.append("\n");
        patient_to_string.append(this.patient_date_of_birth);
        patient_to_string.append("\n");
        return patient_to_string.toString();
    }

    
    
}
