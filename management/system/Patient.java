package management.system;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="PATIENT_INFO")
@NamedQueries({
    @NamedQuery(name="Patient.findByName", query="from Patient where name = :name"),
    @NamedQuery(name="Patient.findByDOB", query="from Patient where dob = :dob"),
})
public class Patient implements Person{

    private int patient_id;
    private String patient_first_name;
    private String patient_last_name;
    private Date patient_date_of_birth;
    
    @Override
    @Id
    @GeneratedValue
    public int getID()
    {
        return patient_id;
    }
    @Override
    public void setID(int patientId)
    {
        this.patient_id = patientId;
    }
    
    @Override
    @Column(name="patient_first_name")
    public String getFirstName() {
        return this.patient_first_name;
    }
    @Override
    public void setFirstName(String firstName) {
        this.patient_first_name = firstName;
    }
    
    @Override
    @Column(name="patient_last_name")
    public String getLastName() {
        return this.patient_last_name;
    }

    @Override
    public void setLastName(String lastName) {
        this.patient_last_name = lastName;
    }
    
    @Column(name="patient_dob")
    @Temporal(TemporalType.DATE)
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
        patient_to_string.append(patient_id);
        patient_to_string.append("\n");
        patient_to_string.append(patient_first_name);
        patient_to_string.append("\n");
        patient_to_string.append(patient_date_of_birth);
        patient_to_string.append("\n");
        return patient_to_string.toString();
    }

    
    
}
