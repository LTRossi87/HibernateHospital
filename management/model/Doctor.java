package management.model;

import java.util.ArrayList;
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

@Entity
@Table(name="DOCTOR_INFO")
@NamedQueries({
    @NamedQuery(name="Doctor.findByName", query="from Doctor where first_name = :fname and last_name = :lname"),
    @NamedQuery(name="Doctor.findBySpecialty", query="from Doctor where specialty = :specialty"),
})
public class Doctor extends Person{

    @Column(name="doctor_specialty")
    private String specialty;
    
    @ManyToMany
    @JoinTable(name="PATIENT_DOCTOR", 
            joinColumns = {@JoinColumn(name="doctor_id")},
            inverseJoinColumns = {@JoinColumn(name="patient_id")})
    private List<Patient> patients;
    
//    @OneToMany(cascade=CascadeType.ALL)
//    @JoinTable(name="DOCTOR_APPOINTMENTS", 
//            joinColumns = {@JoinColumn(name="doctor_id")},
//            inverseJoinColumns = {@JoinColumn(name="appointment_id")})
    @OneToMany(cascade=CascadeType.ALL, mappedBy = "doctor", targetEntity = Appointment.class, fetch = FetchType.EAGER)
    private List<Appointment> appointments;
    
//    @OneToMany(cascade=CascadeType.ALL)
//    @JoinTable(name="DOCTOR_PRESCRIPTIONS", 
//            joinColumns = {@JoinColumn(name="doctor_id")},
//            inverseJoinColumns = {@JoinColumn(name="appointment_id")})
    @OneToMany(cascade=CascadeType.ALL, mappedBy = "doctor", targetEntity = Prescription.class, fetch = FetchType.EAGER)
    private List<Prescription> prescriptions;
    
    public Doctor()
    {
        patients = new ArrayList<>();
        appointments = new ArrayList<>();
        prescriptions = new ArrayList<>();
    }
    
    private enum Specialties {DERMATOLOGY,
                                NEUROLOGY,
                                ORTHOPEDIC_SURGERY,
                                PSYCHIATRY,
                                GENERAL_PRACTICE,
                                FAMILY_PRACTICE}
    
    public List<Appointment> getAppointments()
    {
        return this.appointments;
    }
    public void setAppointments(Appointment appointment)
    {
        this.appointments.add(appointment);
    }
    
    public List<Prescription> getPrescriptions()
    {
        return this.prescriptions;
    }
    public void setPrescriptions(Prescription prescription)
    {
        this.prescriptions.add(prescription);
    }
    
    public List<Patient> getDoctors()
    {
        return this.patients;
    }
    public void setPatient(Patient patient)
    {
        patients.add(patient);
    }
    
    public String getSpecialty()
    {
        return this.specialty;
    }
    public boolean setSpecialty(String input_specialty)
    {
        input_specialty = input_specialty.toLowerCase();
        
        if(!input_specialty.equalsIgnoreCase("d") &&
                !input_specialty.equalsIgnoreCase("n") &&
                !input_specialty.equalsIgnoreCase("o") &&
                !input_specialty.equalsIgnoreCase("p") &&
                !input_specialty.equalsIgnoreCase("g") &&
                !input_specialty.equalsIgnoreCase("f")){return false;}
        
        switch (input_specialty)
        {
            case "d": this.specialty = Specialties.DERMATOLOGY.toString();
                break;
            case "n": this.specialty = Specialties.NEUROLOGY.toString();
                break;
            case "o": this.specialty = Specialties.ORTHOPEDIC_SURGERY.toString();
                break;
            case "p": this.specialty = Specialties.PSYCHIATRY.toString();
                break;
            case "g": this.specialty = Specialties.GENERAL_PRACTICE.toString();
                break;
            case "f": this.specialty = Specialties.FAMILY_PRACTICE.toString();
        }
        return true;
    }
    public String toString()
    {
        return "" + this.first_name + " : " +  this.last_name + " : " + this.specialty;
    }
}
