package management.system;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="DOCTOR_INFO")
@NamedQueries({
    @NamedQuery(name="Doctor.findByName", query="from Doctor where name = :name"),
    @NamedQuery(name="Patient.findBySpecialty", query="from Doctor where specialty = :specialty"),
})
public class Doctor implements Person{

    private int doctor_id;
    private String doctor_first_name;
    private String doctor_last_name;
    public String specialty;
    
    private enum Specialties {DERMATOLOGY,
                            NEUROLOGY,
                            ORTHOPEDIC_SURGERY,
                            PSYCHIATRY,
                            GENERAL_PRACTICE,
                            FAMILY_PRACTICE}
    
    @Override
    @Id
    @GeneratedValue
    public int getID() {
        return this.doctor_id;
    }
    @Override
    public void setID(int id) {
        this.doctor_id = id;
    }
    
    @Override
    @Column(name="doctor_first_name")
    public String getFirstName() {
        return this.doctor_first_name;
    }
    @Override
    public void setFirstName(String firstName) {
        this.doctor_first_name = firstName;
    }
    
    @Override
    @Column(name="doctor_last_name")
    public String getLastName() {
        return this.doctor_last_name;
    }
    @Override
    public void setLastName(String lastName) {
        this.doctor_last_name = lastName;
    }
    
    @Column(name="doctor_specialty")
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
