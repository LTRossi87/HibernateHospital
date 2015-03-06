package management.controller;

import java.util.List;
import java.util.Scanner;
import management.model.Appointment;
import management.model.Doctor;
import management.model.Patient;
import management.model.Prescription;

public class PresentationLayer {

    public static void main(String[] args) {

        ServiceLayer serviceLayer = new ServiceLayer();
        try {
            serviceLayer.openCurrentSession();

            setupDatabase(serviceLayer);
            printMainMenu();
            boolean take_user_input = true;
            Scanner user_input = new Scanner(System.in);
            String input_text = "";
            while (take_user_input) {
                input_text = user_input.next().trim().toLowerCase();
                switch (input_text) {
                    case "a":
                        administratorMenu(user_input, serviceLayer);
                        break;
                    case "s":
                        staffMenu(user_input, serviceLayer);
                        break;
                    case "p":
                        patientMenu(user_input, serviceLayer);
                        break;
                    case "d":
                        doctorMenu(user_input, serviceLayer);
                        break;
                    case "q":
                        System.out.println("Saving Data And Exiting Application");
                        take_user_input = false;
                        break;
                    default:
                        System.out.println("Not A Valid Option");
                        printMainMenu();
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        } finally {
            serviceLayer.closeCurrentSession();

            serviceLayer.closeSession();
        }
    }

    public static void administratorMenu(Scanner scanner, ServiceLayer serviceLayer) {
        printAdministratorMenu();
        boolean take_user_input = true;
        Scanner user_input = scanner;
        String input_text = "";
        String firstName;
        String lastName;
        String specialty;
        String patientDOB;
        while (take_user_input) {
            input_text = user_input.next().trim().toLowerCase();
            switch (input_text) {
                case "c":
                    System.out.println("Create A Doctor");
                    System.out.println("Enter The Doctors First Name");
                    firstName = user_input.next().trim().toLowerCase();
                    System.out.println("Enter The Doctors Last Name");
                    lastName = user_input.next().trim().toLowerCase();
                    System.out.println("Enter The Doctors Specialty");
                    specialty = validateDoctorSpecialties(scanner);
                    serviceLayer.createDoctor(firstName, lastName, specialty);
                    printAdministratorMenu();
                    break;
                case "s":
                    System.out.println("Search For Doctor By Name");
                    System.out.println("Enter The Doctors First Name");
                    firstName = user_input.next().trim().toLowerCase();
                    System.out.println("Enter The Doctors Last Name");
                    lastName = user_input.next().trim().toLowerCase();
                    Doctor doctor = serviceLayer.viewDoctorByName(firstName, lastName);
                    if(!isValidDoctor(doctor))
                    {
                        System.out.println("Not A Valid Doctor");
                        take_user_input = false;
                        printMainMenu();
                        break;
                    }
                    System.out.println(doctor.toString());
                    printAdministratorMenu();
                    break;
                case "v":
                    System.out.println("View Doctor By Specialty");
                    System.out.println("Enter The Doctors Specialty");
                    specialty = validateDoctorSpecialties(scanner);
                    specialty = getSpecialty(specialty);
                    List<Doctor> doc = serviceLayer.viewDoctorBySpecialty(specialty);
                    if (doc.isEmpty()) {
                        System.out.println("No Doctors With specialty: " + specialty);
                        printAdministratorMenu();
                        break;
                    }
                    for (Doctor doctor1 : doc) {
                        System.out.println(doctor1.toString());
                    }
                    printAdministratorMenu();
                    break;
                case "d":
                    System.out.println("Delete Not Implemented");
                    printAdministratorMenu();
                    break;
                case "cr":
                    System.out.println("Enter Patients First Name");
                    firstName = user_input.next().trim().toLowerCase();
                    System.out.println("Enter Patients Last Name");
                    lastName = user_input.next().trim().toLowerCase();
                    System.out.println("Enter Patients Date Of Birth");
                    patientDOB = user_input.next().trim().toLowerCase();
                    serviceLayer.createPatient(firstName, lastName, patientDOB);
                    printAdministratorMenu();
                    break;
                case "vi":
                    System.out.println("Enter Patients First Name");
                    firstName = user_input.next().trim().toLowerCase();
                    System.out.println("Enter Patients Last Name");
                    lastName = user_input.next().trim().toLowerCase();
                    System.out.println("Enter Patients Date Of Birth");
                    patientDOB = user_input.next().trim().toLowerCase();
                    Patient patient = serviceLayer.viewPatientAccount(firstName, lastName, patientDOB);
                    if (!isvalidPatient(patient)) {
                        System.out.println("Not A Valid Patient");
                        take_user_input = false;
                        printMainMenu();
                    }
                    System.out.println(patient.toString());
                    printAdministratorMenu();
                    break;
                case "de":
                    System.out.println("Deleting Not Implemented");
                    printAdministratorMenu();
                    break;
                case "b":
                    System.out.println("Returning To Previous Menu");
                    printMainMenu();
                    take_user_input = false;
                    break;
                default:
                    System.out.println("Not A Valid Option");
                    printAdministratorMenu();
                    break;
            }

        }
    }

    public static void staffMenu(Scanner scanner, ServiceLayer serviceLayer) {
        printStaffMenu();
        boolean take_user_input = true;
        Scanner user_input = scanner;
        String input_text = "";
        String doctorsFirstName;
        String doctorsLastName;
        String patientsFirstName;
        String patientsLastName;
        String appointmentDate;
        String patientDOB;
        while (take_user_input) {
            input_text = user_input.next().trim().toLowerCase();
            switch (input_text) {
                case "c":
                    System.out.println("Enter A Doctors First Name");
                    doctorsFirstName = user_input.next().trim().toLowerCase();
                    System.out.println("Enter A Doctors Last Name");
                    doctorsLastName = user_input.next().trim().toLowerCase();
                    System.out.println("Enter A Patients First Name");
                    patientsFirstName = user_input.next().trim().toLowerCase();
                    System.out.println("Enter A Patients Last Name");
                    patientsLastName = user_input.next().trim().toLowerCase();
                    System.out.println("Enter A Patients Date Of Birth");
                    patientDOB = user_input.next().trim().toLowerCase();
                    System.out.println("Enter Date Of Appointment mm/dd/yyy");
                    appointmentDate = user_input.next().trim().toLowerCase();
                    serviceLayer.createAppointmentForPatient(patientsFirstName,
                            patientsLastName,
                            patientDOB,
                            doctorsFirstName,
                            doctorsLastName,
                            appointmentDate);
                    printStaffMenu();
                    break;
                case "d":
                    /*
                     System.out.println("Enter A Patients First Name");
                     patientsFirstName = user_input.next().trim().toLowerCase();
                     System.out.println("Enter A Patients Last Name");
                     patientsLastName = user_input.next().trim().toLowerCase();
                     System.out.println("Enter A Patients Date Of Birth");
                     patientDOB = user_input.next().trim().toLowerCase();
                     System.out.println("Enter Date Of Appointment mm/dd/yyy");
                     appointmentDate = user_input.next().trim().toLowerCase();
                     serviceLayer.cancelAppointment(patientsFirstName, patientsLastName, patientDOB, appointmentDate);
                     */
                    System.out.println("Delete Not Implemented");
                    printStaffMenu();
                    break;
                case "b":
                    System.out.println("Returning To Previous Menu");
                    printMainMenu();
                    take_user_input = false;
                    break;
                default:
                    System.out.println("Not A Valid Option");
                    printStaffMenu();
                    break;
            }

        }
    }

    public static void patientMenu(Scanner scanner, ServiceLayer serviceLayer) {
        boolean take_user_input = true;
        Scanner user_input = scanner;
        String input_text = "";
        String doctorsFirstName;
        String doctorsLastName;
        String patientsFirstName;
        String patientsLastName;
        String patientDOB;
        Patient patient;
        System.out.println("Enter Patients First Name");
        patientsFirstName = user_input.next().trim().toLowerCase();
        System.out.println("Enter Patients Last Name");
        patientsLastName = user_input.next().trim().toLowerCase();
        System.out.println("Enter Patients Date Of Birth");
        patientDOB = user_input.next().trim().toLowerCase();
        patient = serviceLayer.viewPatientAccount(patientsFirstName, patientsLastName, patientDOB);
        if (!isvalidPatient(patient)) {
            System.out.println("Not A Valid Patient");
            take_user_input = false;
            printMainMenu();
        } else {
            printPatientMenu();
        }

        while (take_user_input) {
            input_text = user_input.next().trim().toLowerCase();
            switch (input_text) {
                case "a":
                    List<Appointment> appointments = patient.getAppointments();
                    for (Appointment appointment : appointments) {
                        System.out.println(appointment.toString());
                    }
                    printPatientMenu();
                    break;
                case "p":
                    System.out.println("Enter A Doctors First Name");
                    doctorsFirstName = user_input.next().trim().toLowerCase();
                    System.out.println("Enter A Doctors Last Name");
                    doctorsLastName = user_input.next().trim().toLowerCase();
                    List<Prescription> prescriptions = patient.getPrescriptions();
                    for (Prescription prescription : prescriptions) {
                        String docFirstName = prescription.getDoctors().getFirst_name();
                        String docLastname = prescription.getDoctors().getLast_name();
                        if (docFirstName.equalsIgnoreCase(doctorsFirstName)
                                && docLastname.equalsIgnoreCase(doctorsLastName)) {
                            System.out.println(prescription.toString());
                        }
                    }
                    printPatientMenu();
                    break;
                case "d":
                    List<Doctor> doctors = serviceLayer.viewAllDoctors();
                    for (Doctor doctor : doctors) {
                        System.out.println(doctor.toString());
                    }
                    printPatientMenu();
                    break;
                case "b":
                    System.out.println("Returning To Previous Menu");
                    printMainMenu();
                    take_user_input = false;
                    break;
                default:
                    System.out.println("Not A Valid Option");
                    printPatientMenu();
                    break;
            }

        }
    }

    public static void doctorMenu(Scanner scanner, ServiceLayer serviceLayer) {
        boolean take_user_input = true;
        Scanner user_input = scanner;
        String input_text = "";
        String doctorsFirstName;
        String doctorsLastName;
        String patientsFirstName;
        String patientsLastName;
        String patientDOB;
        String rX;
        System.out.println("Enter Doctors First Name");
        doctorsFirstName = user_input.next().trim().toLowerCase();
        System.out.println("Enter Doctors Last Name");
        doctorsLastName = user_input.next().trim().toLowerCase();
        Doctor doctor = serviceLayer.viewDoctorByName(doctorsFirstName, doctorsLastName);
        if (!isValidDoctor(doctor)) {
            System.out.println("No Such Doctor: " + doctorsFirstName + " " + doctorsLastName);
            take_user_input = false;
            printMainMenu();
        } else {
            printDoctorMenu();
        }

        while (take_user_input) {
            input_text = user_input.next().trim().toLowerCase();
            switch (input_text) {
                case "v":
                    List<Patient> patients = doctor.getPatients();
                    for (Patient patient : patients) {
                        System.out.println(patient.toStringForDoctor());
                    }
                    printDoctorMenu();
                    break;
                case "w":
                    System.out.println("Enter Patients First Name");
                    patientsFirstName = user_input.next().trim().toLowerCase();
                    System.out.println("Enter Patients Last Name");
                    patientsLastName = user_input.next().trim().toLowerCase();
                    System.out.println("Enter Patients Date Of Birth");
                    patientDOB = user_input.next().trim().toLowerCase();
                    Patient patient = serviceLayer.viewPatientAccount(patientsFirstName, patientsLastName, patientDOB);
                    if (!isvalidPatient(patient)) {
                        System.out.println("Not A Valid Patient" + patientsFirstName + " " + patientsLastName);
                        printDoctorMenu();
                        break;
                    }
                    if (serviceLayer.isPatientOfDoctor(doctor, patient)) {
                        System.out.println("Enter Prescription");
                        rX = user_input.next();
                        serviceLayer.createPrescription(patientsFirstName, patientsLastName, patientDOB, doctorsFirstName, doctorsLastName, rX);
                    } else {
                        System.out.println("This Is Not Your Patient."
                                + "\nPatient Must First Schedule An Appointment With Doctor");
                    }
                    printDoctorMenu();
                    break;
                case "b":
                    System.out.println("Returning To Previous Menu");
                    printMainMenu();
                    take_user_input = false;
                    break;
                default:
                    System.out.println("Not A Valid Option");
                    printPatientMenu();
                    break;
            }

        }
    }

    public static boolean isvalidPatient(Patient patient) {
        if (patient == null) {
            return false;
        }
        return true;
    }

    public static boolean isValidDoctor(Doctor doctor) {
        if (doctor == null) {
            return false;
        }
        return true;
    }

    public static String getSpecialty(String Specialties) {

        String returnSpecialty = "";
        switch (Specialties) {
            case "d":
                returnSpecialty = "DERMATOLOGY";
                break;
            case "n":
                returnSpecialty = "NEUROLOGY";
                break;
            case "o":
                returnSpecialty = "ORTHOPEDIC_SURGERY";
                break;
            case "p":
                returnSpecialty = "PSYCHIATRY";
                break;
            case "g":
                returnSpecialty = "GENERAL_PRACTICE";
                break;
            case "f":
                returnSpecialty = "FAMILY_PRACTICE";
                break;

        }
        return returnSpecialty;
    }

    public static String validateDoctorSpecialties(Scanner scanner) {
        printValidateDoctorSpecialtiesMenu();
        boolean take_user_input = true;
        Scanner user_input = scanner;
        String input_text = "";
        String return_text = "";
        while (take_user_input) {
            input_text = user_input.next().trim().toLowerCase();
            switch (input_text) {
                case "d":
                    return_text = "d";
                    take_user_input = false;
                    break;
                case "n":
                    return_text = "n";
                    take_user_input = false;
                    break;
                case "o":
                    return_text = "o";
                    take_user_input = false;
                    break;
                case "p":
                    return_text = "p";
                    take_user_input = false;
                    break;
                case "g":
                    return_text = "g";
                    take_user_input = false;
                    break;
                case "f":
                    return_text = "f";
                    take_user_input = false;
                    break;
                default:
                    System.out.println("Not A Valid Option");

            }

        }
        return return_text;
    }

    public static void printMainMenu() {
        System.out.println("Welcome to Rossi General Hospital");
        System.out.println("Please Make A Selection From The Menu Below");
        System.out.println("[A]dministrator, [S]taff, [P]atient, [D]octor, [Q]uit");
    }

    public static void printAdministratorMenu() {
        System.out.println("Welcome Administrator");
        System.out.println("Please Make A Selection From The Menu Below");
        System.out.println("[C]reate A Doctor, [S]earch For Doctor By Name, [V]iew Doctors By Specialty, [D]elete A Doctor,"
                + "\n[Cr]eate A Patient, [Vi]ew Patient Account, [De]lete Patient [B]ack");
    }

    public static void printValidateDoctorSpecialtiesMenu() {
        System.out.println("Choose The Doctors Specialty");
        System.out.println("Please Make A Selection From The Menu Below");
        System.out.println("[D]ERMATOLOGY, [N]EUROLOGY, [O]RTHOPEDIC_SURGERY,\n"
                + "[P]SYCHIATRY, [G]ENERAL_PRACTICE, [F]AMILY_PRACTICE");
    }

    public static void printStaffMenu() {
        System.out.println("Welcome Staff");
        System.out.println("Please Make A Selection From The Menu Below");
        System.out.println("[C]reate An Appointment, [D]elete An Appointment, [B]ack");
    }

    public static void printPatientMenu() {
        System.out.println("Welcome Patient");
        System.out.println("Please Make A Selection From The Menu Below");
        System.out.println("[A]ppointment List, [P]rescription From Doctor, [D]octor's Information, [B]ack");
    }

    public static void printDoctorMenu() {
        System.out.println("Welcome Doctor");
        System.out.println("Please Make A Selection From The Menu Below");
        System.out.println("[V]iew Patient Information, [W]rite Prescription, [B]ack");
    }

    /**
     * Used to Initialize the Database
     *
     * @param serviceLayer The ServiceLayer deals with business logic
     */
    public static void setupDatabase(ServiceLayer serviceLayer) {
        serviceLayer.createDoctor("ham", "hawk", "o");
        serviceLayer.createDoctor("pork", "rine", "f");
        serviceLayer.createDoctor("sally", "white", "d");
        serviceLayer.createDoctor("will", "smith", "n");
        serviceLayer.createDoctor("dummy", "doctor", "g");

        serviceLayer.createPatient("brandon", "rossi", "10/02/1987");
        serviceLayer.createPatient("anna", "gonzales", "10/17/1988");
        serviceLayer.createPatient("austin", "merritt", "06/26/1981");
        serviceLayer.createPatient("amber", "stephen", "10/14/1978");
        serviceLayer.createPatient("dummy", "patient", "10/10/1910");

        /*One Patient One Doctor One Appointment One Prescription*/
        serviceLayer.createAppointmentForPatient("brandon", "rossi", "10/02/1987", "ham", "hawk", "10/02/1987");

        serviceLayer.createPrescription("brandon", "rossi", "10/02/1987", "ham", "hawk", "Happy Meds");

        /*One Patient One Doctor One Appointment One Prescription*/
        serviceLayer.createAppointmentForPatient("anna", "gonzales", "10/17/1988", "sally", "white", "12/05/2016");

        serviceLayer.createPrescription("anna", "gonzales", "10/17/1988", "sally", "white", "Skin So Nice");

        /*One Patient Two Doctor Two Appointment Two Prescription*/
        serviceLayer.createAppointmentForPatient("austin", "merritt", "06/26/1981", "will", "smith", "01/16/2016");

        serviceLayer.createPrescription("austin", "merritt", "06/26/1981", "Will", "smith", "Headache Be Gone");
        serviceLayer.createAppointmentForPatient("austin", "merritt", "06/26/1981", "pork", "rine", "05/04/2015");

        serviceLayer.createPrescription("austin", "merritt", "06/26/1981", "pork", "rine", "Itch Relief");

        /*One Patient Two Doctor Two Appointment Two Prescription*/
        serviceLayer.createAppointmentForPatient("amber", "stephen", "10/14/1978", "sally", "white", "06/15/2017");

        serviceLayer.createPrescription("amber", "stephen", "10/14/1978", "sally", "white", "Smooth Face Cream");
        serviceLayer.createAppointmentForPatient("amber", "stephen", "10/14/1978", "ham", "hawk", "07/21/2015");

        serviceLayer.createPrescription("amber", "stephen", "10/14/1978", "ham", "hawk", "Joint Lubricant");
    }
}
