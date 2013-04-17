/**
 * 
 * Description: The userProfile class retrieves information from the 
 * databae relevant to the user's profile or keep everything empty if the 
 * user chooses not to edit their profile. Mainly holds more details about 
 * the user. 
 * 
 * 
 * Authors: Daniela Cardona, Morgan Brown, Sonia Obeng
 * Language: Java
 * Date: April 3, 2013
 * 
 **/

public class userProfile {

DBConnect connect = new DBConnect( );


userProfile( ){
//default constructor
}

/**
 *This method gets user's name 
 *@param user holds the string of user's username
 * @return name returns user's first and last name.
 * */
public String getName(String user){
String name = connect.getName(user);// string to hold user's name
return name;
}


/**
 *This method gets user's birthday
 *@param user holds the string of user's username.
 * @return birthday returns user's birthday
 * */
public String getBirthday(String user){
String birthday = connect.getAge(user);
return birthday;
}

/**
 *This method holds user's "about me"
 *@param user holds the string of user's username.
 * @return aboutme  returns user's input
 * */
public String aboutMe(String user){
String aboutme = connect.getAboutMe(user);
return aboutme;
}

/**
 *This method holds user's choice of color
 *@param user holds the string of user's username.
 * @return color returns user's color
 **/
public String color(String user){
    String color = connect.getColor(user);
    return color;
}

/**
 *This method holds user's details and inserts into user's profile
 *@param user holds the string of user's username.
 * @param firstholds the string of user's first name
 * @param last holds the string of user's last name
 * @param bday holds the string of user's birthday
 * @param me holds the string of user's "about me"
 * @param color holds the string of user's color
 * @return true returns true when all user info is inserted into user's profile
 **/
public boolean profile(String user, String first,String last,String bday,String me,String color){

        connect.insertProfile(user,first,last,bday,me,color);
    return true;
}

}
