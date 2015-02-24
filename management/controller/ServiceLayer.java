package management.controller;
import management.dao.ConcreteMedicalDAO;

public class ServiceLayer {
    /*
    
        In this class there will be calles to the DAO that will insert update and delete data...
    
        For example. To delete a Doctor. This class will get the request to delete Doctor x from the database
            To do this the Service Layer will call find doctor from the DAO and that will return a Doctor,
            Next the Service Layer will call Delete Doctor from the DAO passing it the Doctor object that was retrieved from the previous call.
    
    */
}
