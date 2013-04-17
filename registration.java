
/**
 * 
 * Description: The registration class creates a new registration class object
 * that adds a new user to the database. 
 * 
 * Authors: Daniela Cardona, Morgan Brown,  Sonia Obeng
 * Language: Java
 * Date: April 3, 2013
 * 
 **/


public class registration {

 
DBConnect connect = new DBConnect( ); // new DBconnect object


registration( ){
  //default constructor
}


/**
 * This method inserts new user's username and password in the database.
 * @param user holds the string of user's created username
 * @param pass holds the string of users's created password
 * @return true user is now registered
 * */

public boolean register(String user, String pass){
  
connect.insertUser(user,pass);

return true;

}


}
