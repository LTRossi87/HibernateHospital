package management.system;

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
    @NamedQuery(name="Patient.findByName", query="from Patient where name = :name"),
    @NamedQuery(name="Patient.findByDOB", query="from Patient where dob = :dob"),
})
public class Patient extends Person{
    
    @Column(name="date_of_birth")
    @Temporal(TemporalType.DATE)
    private Date patient_date_of_birth;
   
    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="PATIENT_DOCTOR", 
            joinColumns = {@JoinColumn(name="patient_id")},
            inverseJoinColumns = {@JoinColumn(name="doctor_id")})
    private List<Doctor> doctors;

    
    @OneToMany(mappedBy = "patient",targetEntity = Prescription.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Prescription> prescriptions;
    
    @OneToMany(mappedBy = "patient", targetEntity = Appointment.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Appointment> appointments;
    
    public Patient()
    {
        doctors = new ArrayList<>();
        prescriptions = new ArrayList<>();
        appointments = new ArrayList<>();
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
    
    public Date getDOB()
    {
        return this.patient_date_of_birth;
    }
    public void setDOB(String dob)
    {
        this.patient_date_of_birth = new Date(dob);
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
