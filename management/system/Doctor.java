package management.system;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="DOCTOR_INFO")
@NamedQueries({
    @NamedQuery(name="Doctor.findByName", query="from Doctor where name = :name"),
    @NamedQuery(name="Patient.findBySpecialty", query="from Doctor where specialty = :specialty"),
})
public class Doctor extends Person{

    @Column(name="doctor_specialty")
    private String specialty;
    
    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="PATIENT_DOCTOR", 
            joinColumns = {@JoinColumn(name="doctor_id")},
            inverseJoinColumns = {@JoinColumn(name="patient_id")})
    private List<Patient> patients;
    
    public Doctor()
    {
        patients = new ArrayList<>();
    }
    
    private enum Specialties {DERMATOLOGY,
                                NEUROLOGY,
                                ORTHOPEDIC_SURGERY,
                                PSYCHIATRY,
                                GENERAL_PRACTICE,
                                FAMILY_PRACTICE}
    
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
}
