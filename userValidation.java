

/**
 * 
 * Description: The userValidation class checks
 * if input for login is valid, if username entered is registered and 
 * adds a new subcriber to user's list of subscribers. 
 * 
 * Authors: Daniela Cardona, Morgan Brown, Sonia Obeng
 * Language: Java
 * Date: April 3, 2013
 * 
 **/

public class userValidation {
  
DBConnect connect = new DBConnect( );


userValidation( ){
// default constructor
}

/**
 *This method checks if the username and password entered is correct or incorrect.
 *@param user holds the string of user's username
 *@param pass holds the string of user's password
 * @return true returns true if username and password is correct
 * @return false returns false if username or password is incorrect
 * */

public boolean login(String user, String pass){
if(connect.getUser(user) && connect.checkPassword(user,pass)){
return true;
}else{
return false;
}
}

/**
 *This method checks if the usernameentered is registered.
 *@param user holds the string of user's username
 * @return true returns true if username is registered
 * @return false returns false if username is not registered
 * */

public boolean isUser(String user){
if(connect.getUser(user)){
return true;
}else{
return false;
}
}
/**
 *This method checks if username to be subscribed to is registered and if current user
 * is not null. It adds a new subscriber to current user's list of subscribers. 
 *@param user holds the string of current user's username
 * @param subscribe holds the string of user to be subscribed to.
 *@return true returns true if condition is met. 
 * @return false returns false if did not meet condition. 
 * */

public boolean newSubscribe(String user, String subscribe){
if(isUser(subscribe) && user != null){
connect.insertSubscribe(user, subscribe);
return true;
}else{
return false;
}
}
}
