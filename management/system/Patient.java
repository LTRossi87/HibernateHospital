package management.system;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
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
public class Patient extends Person{
    
    @Column(name="date_of_birth")
    @Temporal(TemporalType.DATE)
    private Date patient_date_of_birth;
   
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
