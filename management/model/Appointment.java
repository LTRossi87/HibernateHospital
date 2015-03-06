/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package management.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "APPOINTMENT_INFO")
public class Appointment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    //@Temporal(TemporalType.DATE)
    private String appointmentDate;
    
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
    
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
   
    
    public Patient getPatient()
    {
        return this.patient;
    }
    public void setPatient(Patient patient)
    {
        this.patient = patient;
    }
    
    public int getId()
    {
        return this.id;
    }
    public void setId(int id)
    {
        this.id = id;
    }
    
    public Doctor getDoctor()
    {
        return this.doctor;
    }
    public void setDoctor(Doctor doctor)
    {
        this.doctor = doctor;
    }
    
    
    public String getAppointmentDate()
    {
        return this.appointmentDate;
    }
    public void setAppointmentDate(String appointment_date)
    {
        this.appointmentDate = appointment_date;
    }
    
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(":Appointment Scheduled With: ");
        stringBuilder.append(this.getDoctor().toString());
        stringBuilder.append("        :On: ");
        stringBuilder.append(this.getAppointmentDate());        
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }
}
