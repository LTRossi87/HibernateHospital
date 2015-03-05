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
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
    
    @Column(name = "Rx_INFO")
    private String prescription_details;
    
    public int getID()
    {
        return this.id;
    }
    public void setID(int id)
    {
        this.id = id;
    }
    
    public String getRx()
    {
        return this.prescription_details;
    }
    public void setRx(String rx)
    {
        this.prescription_details = rx;
    }
    
    public Doctor getDoctor()
    {
        return this.doctor;
    }
    public void setDoctor(Doctor doctor)
    {
        this.doctor = doctor;
    }
    
}
