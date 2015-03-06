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
@Table(name = "PATIENT_INFO")
@NamedQueries({
    @NamedQuery(name = "Patient.findByNameAndDOB",
            query = "from Patient where first_name = :fname and last_name = :lname and date_of_birth = :dob")
})
public class Patient extends Person {

    @Column(name = "date_of_birth")
    private String dob;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "PATIENT_DOCTOR",
            joinColumns = {
                @JoinColumn(name = "patient_id")},
            inverseJoinColumns = {
                @JoinColumn(name = "doctor_id")})
    private List<Doctor> doctors;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "patient", targetEntity = Prescription.class, fetch = FetchType.EAGER)
    private List<Prescription> prescriptions;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "patient", targetEntity = Appointment.class, fetch = FetchType.EAGER)
    private List<Appointment> appointments;

    public Patient() {
        doctors = new ArrayList<>();
        prescriptions = new ArrayList<>();
        appointments = new ArrayList<>();
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }

    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(List<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }

    public void setAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    public void setPrescription(Prescription prescription) {
        this.prescriptions.add(prescription);
    }

    public void setDoctors(Doctor doctor) {
        this.doctors.add(doctor);
    }

    public String toString() {
        StringBuilder patient_to_string = new StringBuilder();
        patient_to_string.append("Patient ID: ");
        patient_to_string.append(this.getId());
        patient_to_string.append(", ");
        patient_to_string.append("Patient Name: ");
        patient_to_string.append(this.getFirst_name().toUpperCase());
        patient_to_string.append(" ");
        patient_to_string.append(this.getLast_name().toUpperCase());
        patient_to_string.append(", ");
        patient_to_string.append("Patient Date Of Birth: ");
        patient_to_string.append(this.dob);
        patient_to_string.append(", ");
        patient_to_string.append("Patients Appointments: \n");
        for (Appointment appointment : appointments) {
            patient_to_string.append("    " + appointment.toString());
        }
        patient_to_string.append("\n");
        return patient_to_string.toString();
    }

    public String toStringForDoctor() {
        StringBuilder patient_to_string = new StringBuilder();
        patient_to_string.append("Patient Name: ");
        patient_to_string.append(this.getFirst_name().toUpperCase());
        patient_to_string.append(" ");
        patient_to_string.append(this.getLast_name().toUpperCase());
        patient_to_string.append(", ");
        patient_to_string.append("Date Of Birth: ");
        patient_to_string.append(this.dob);
        patient_to_string.append("\n");
        for (Appointment appointment : appointments) {
            patient_to_string.append("    " + appointment.toString());
        }
        patient_to_string.append("\n");
        return patient_to_string.toString();
    }

}
