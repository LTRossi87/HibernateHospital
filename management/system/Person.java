/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package management.system;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public interface Person {
    String first_name = null;
    String last_name = null;//Not sure how to use these?? :S
    
    public String getFirstName();
    public void setFirstName(String firstName);
    public String getLastName();
    public void setLastName(String lastName);
    
    public int getID();
    public void setID(int id);
}
