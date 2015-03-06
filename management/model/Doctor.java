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

/*
    Brandon Rossi
    CS 157B
    Spring 2015
    Assignment 1
*/

@Entity
@Table(name="DOCTOR_INFO")
@NamedQueries({
    @NamedQuery(name="Doctor.findByName", query="from Doctor where first_name = :fname and last_name = :lname"),
    @NamedQuery(name="Doctor.findBySpecialty", query="from Doctor where specialty = :specialty"),
    @NamedQuery(name="Doctor.getAllDoctors", query="from Doctor")
})
public class Doctor extends Person{

    @Column(name="doctor_specialty")
    private String specialty;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="PATIENT_DOCTOR",
            joinColumns = {@JoinColumn(name="doctor_id")},
            inverseJoinColumns = {@JoinColumn(name="patient_id")})
    private List<Patient> patients;
    
    @OneToMany(cascade=CascadeType.ALL, mappedBy = "doctor", targetEntity = Appointment.class, fetch = FetchType.EAGER)
    private List<Appointment> appointments;
    
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

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    public void setPrescriptions(List<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public String getSpecialty() {
       return specialty;
    }
    
    public void setAppointment(Appointment appointment)
    {
        this.appointments.add(appointment);
    }
    public void setPrescription(Prescription prescription)
    {
        this.prescriptions.add(prescription);
    }
    public void setPatient(Patient patient)
    {
        patients.add(patient);
    }
    public boolean setSpecialties(String input_specialty)
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
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Dr. ");
        stringBuilder.append(this.getFirst_name().toUpperCase());
        stringBuilder.append(" ");
        stringBuilder.append(this.getLast_name().toUpperCase());
        stringBuilder.append(", ");
        stringBuilder.append("Specialty: ");
        stringBuilder.append(this.getSpecialty());
        stringBuilder.append("\n");
        
        return stringBuilder.toString();
    }
}
