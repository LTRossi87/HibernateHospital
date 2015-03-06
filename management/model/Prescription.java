/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package management.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PRESCRIPTION_INFO")
public class Prescription {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    @Column(name = "Rx_INFO")
    private String rx;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    public Doctor getDoctor() {
        return doctor;
    }

    public int getId() {
        return id;
    }

    public Patient getPatient() {
        return patient;
    }

    public String getRx() {
        return rx;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setRx(String rx) {
        this.rx = rx;
    }
    
    

    
    public Patient getPatients()
    {
        return this.patient;
    }
    public void setPatients(Patient patient)
    {
        this.patient = patient;
    }
    
    public int getIds()
    {
        return this.id;
    }
    public void setIds(int id)
    {
        this.id = id;
    }
    
    public String getRxs()
    {
        return this.rx;
    }
    public void setRxs(String rx)
    {
        this.rx = rx;
    }
    
    public Doctor getDoctors()
    {
        return this.doctor;
    }
    public void setDoctors(Doctor doctor)
    {
        this.doctor = doctor;
    }
    
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        
        stringBuilder.append("Prescribing Doctor: ");
        stringBuilder.append(this.getDoctors().toString());
        stringBuilder.append(" :");
        stringBuilder.append("Prescribed Medication: ");
        stringBuilder.append(this.getRxs());
        stringBuilder.append("\n");
        
        return stringBuilder.toString();
    }
    
}
