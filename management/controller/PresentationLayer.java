package management.controller;

import com.sun.xml.internal.bind.v2.WellKnownNamespace;
import java.util.Scanner;
import management.model.Doctor;


public class PresentationLayer 
{
    
    public static void main(String[] args)
    {

        /*
        Here will be the code that deals with the user input. The user will go through a set of menus.
        When they enter information, for example creating a doctor, They will be asked for the name specitialy when they hit return
            the info will be passed to the serviceLayer that will construct a Doctor 
            object and then pass that to the DAO for insertion into the database
        */
        ServiceLayer serviceLayer = new ServiceLayer();
        try 
        {
            setupDatabase(serviceLayer);
            printMainMenu();
            boolean take_user_input = true;
            Scanner user_input = new Scanner(System.in);
            String input_text = "";
            while (take_user_input) 
            {
                input_text = user_input.next().trim().toLowerCase();
                switch (input_text)
                {
                    case "a": administratorMenu(user_input, serviceLayer);
                        break;
                    case "s": staffMenu(user_input, serviceLayer);
                        break;
                    case "p": patientMenu(user_input, serviceLayer);
                        break;
                    case "d": doctorMenu(user_input, serviceLayer);
                        break;
                    case "q": System.out.println("Saving Data And Exiting Application");
                              take_user_input = false;
                        break;
                    default: System.out.println("Not A Valid Option");
                }   
            }
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            System.exit(1);
        }
        finally
        {
            serviceLayer.closeSession();
        }
    }
    
    public static void administratorMenu(Scanner scanner, ServiceLayer serviceLayer)
    {
        printAdministratorMenu();
        boolean take_user_input = true;
        Scanner user_input = scanner;
        String input_text = "";
        while (take_user_input) 
        {
            input_text = user_input.next().trim().toLowerCase();
            switch (input_text)
            {
                case "c": System.out.println("Create A Doctor");
                        System.out.println("Enter The Doctors First Name");
                        String firstName = user_input.next().trim().toLowerCase();
                        System.out.println("Enter The Doctors Last Name");
                        String lastName = user_input.next().trim().toLowerCase();
                        System.out.println("Enter The Doctors Specialty");
                        String specialty = validateDoctorSpecialties(scanner);
                        serviceLayer.createDoctor(firstName, lastName, specialty);
                        printAdministratorMenu();
                    break;
                case "s": System.out.println("Search For Doctor By Name");
                    break;
                case "v": System.out.println("View Doctor By Specialty");
                    break;
                case "d": System.out.println("Delete");
                    break;
                case "b": System.out.println("Returning To Previous Menu");
                          printMainMenu();
                          take_user_input = false;
                    break;
                default: System.out.println("Not A Valid Option");
            }

        }
    }
    
    public static void staffMenu(Scanner scanner, ServiceLayer serviceLayer)
    {
        System.out.println("Staff Menu");
    }
    
    public static void patientMenu(Scanner scanner, ServiceLayer serviceLayer)
    {
        System.out.println("Patient");
    }
    
    public static void doctorMenu(Scanner scanner, ServiceLayer serviceLayer)
    {
        System.out.println("Doctor");
    }
    
    public static String validateDoctorSpecialties(Scanner scanner)
    {
        printValidateDoctorSpecialtiesMenu();
        boolean take_user_input = true;
        Scanner user_input = scanner;
        String input_text = "";
        String return_text = "";
        while (take_user_input) 
        {
            input_text = user_input.next().trim().toLowerCase();
            switch(input_text)
            {
                case "d": return_text = "d";
                          take_user_input = false;
                    break;
                case "n": return_text = "n";
                          take_user_input = false;
                    break;
                case "o": return_text = "o";
                          take_user_input = false;
                    break;
                case "p": return_text = "p";
                          take_user_input = false;
                    break;
                case "g": return_text = "g";
                          take_user_input = false;
                    break;
                case "f": return_text = "f";
                          take_user_input = false;
                    break;
                default: System.out.println("Not A Valid Option");
                            
            }
            
        }
        return return_text;
    }
    
    public static void printMainMenu()
    {
        System.out.println("Welcome to Rossi General Hospital");
        System.out.println("Please Make A Selection From The Menu Below");
        System.out.println("[A]dministrator, [S]taff, [P]atient, [D]octor, [Q]uit");
    }
    public static void printAdministratorMenu()
    {
        System.out.println("Welcome Administrator");
        System.out.println("Please Make A Selection From The Menu Below");
        System.out.println("[C]reate A Doctor, [S]earch For Doctor By Name, [V]iew Doctors By Specialty, [D]elete A Doctor, [B]ack");
    }
     public static void printValidateDoctorSpecialtiesMenu()
    {
        System.out.println("Choose The Doctors Specialty");
        System.out.println("Please Make A Selection From The Menu Below");
        System.out.println("[D]ERMATOLOGY, [N]EUROLOGY, [O]RTHOPEDIC_SURGERY,\n"
                         + "[P]SYCHIATRY, [G]ENERAL_PRACTICE, [F]AMILY_PRACTICE");
    }
     
    /**
     * Used to Initialize the Database
     * @param serviceLayer The ServiceLayer deals with business logic
     */
    public static void setupDatabase(ServiceLayer serviceLayer)
    {
        serviceLayer.createDoctor("Ham", "Hawk", "o");
        serviceLayer.createDoctor("Pork", "Rine", "f");
        serviceLayer.createDoctor("Sally", "White", "d");
        serviceLayer.createDoctor("Will", "Smith", "n");
        serviceLayer.createDoctor("Dummy", "Doctor", "g");
        
        serviceLayer.createPatient("Brandon", "Rossi", "10/02/1987");
        serviceLayer.createPatient("Anna", "Gonzales", "10/17/1988");
        serviceLayer.createPatient("Austin", "Merritt", "06/26/1981");
        serviceLayer.createPatient("Amber", "Stephen", "10/14/1978");
        serviceLayer.createPatient("Dummy", "Patient", "10/10/1910");
        
        Doctor doctor;
        /*One Patient One Doctor One Appointment One Prescription*/
        serviceLayer.createAppointmentForPatient("Brandon", "Rossi", "10/02/1987", "Ham", "Hawk", "10/02/1987");        
        doctor = serviceLayer.viewDoctorByName("Ham", "Hawk");
        serviceLayer.createPrescription("Brandon", "Rossi", "10/02/1987", doctor, "Happy Meds");
        
        /*One Patient One Doctor One Appointment One Prescription*/
        serviceLayer.createAppointmentForPatient("Anna", "Gonzales", "10/17/1988", "Sally", "White", "12/05/2016");
        doctor = serviceLayer.viewDoctorByName("Sally", "White");
        serviceLayer.createPrescription("Anna", "Gonzales", "10/17/1988", doctor, "Skin So Nice");
        
        /*One Patient Two Doctor Two Appointment Two Prescription*/
        serviceLayer.createAppointmentForPatient("Austin", "Merritt", "06/26/1981", "Will", "Smith", "01/16/2016");
        doctor = serviceLayer.viewDoctorByName("Will", "Smith");
        serviceLayer.createPrescription("Austin", "Merritt", "06/26/1981", doctor, "Headache Be Gone");
        serviceLayer.createAppointmentForPatient("Austin", "Merritt", "06/26/1981", "Pork", "Rine", "05/04/2015");
        doctor = serviceLayer.viewDoctorByName("Pork", "Rine");
        serviceLayer.createPrescription("Austin", "Merritt", "06/26/1981", doctor, "Itch Relief");
        
        /*One Patient Two Doctor Two Appointment Two Prescription*/
        serviceLayer.createAppointmentForPatient("Amber", "Stephen", "10/14/1978", "Sally", "White", "06/15/2017");
        doctor = serviceLayer.viewDoctorByName("Sally", "White");
        serviceLayer.createPrescription("Amber", "Stephen", "10/14/1978", doctor, "Smooth Face Cream");
        serviceLayer.createAppointmentForPatient("Amber", "Stephen", "10/14/1978", "Ham", "Hawk", "07/21/2015");
        doctor = serviceLayer.viewDoctorByName("Ham", "Hawk");
        serviceLayer.createPrescription("Amber", "Stephen", "10/14/1978", doctor, "Joint Lubricant");  
    }
}
