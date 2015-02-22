/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package management.system;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name="PERSON_INFO")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Person {
    
    @Id
    @GeneratedValue (strategy = GenerationType.TABLE)
    int id;
    
    @Column(name="first_name")
    String first_name;
    
    @Column(name="last_name")
    String last_name;
    
    public String getFirstName()
    {
        return this.first_name;
    }
    public void setFirstName(String firstName)
    {  
        this.first_name = firstName;
    }
    public String getLastName()
    {
        return this.last_name;
    }
    public void setLastName(String lastName)
    {
        this.last_name = lastName;
    }
    
    public int getID()
    {
        return this.id;
    }
    public void setID(int id)
    {
        this.id = id;
    }
    
    public String toString()
    {
        StringBuilder patient_to_string = new StringBuilder();
        patient_to_string.append(this.id);
        patient_to_string.append("\n");
        patient_to_string.append(this.first_name);
        patient_to_string.append(" ");
        patient_to_string.append(this.last_name);
        patient_to_string.append("\n");
        return patient_to_string.toString();
    }
}
