/**
 * 
 * Description: The honkReader class has all methods related to a posts- when 
 * needed to be posted, searched, and displayed.  
 * 
 * Authors: Daniela Cardona, Morgan Brown,  Sonia Obeng
 * Language: Java
 * Date: April 3, 2013
 * 
 **/



import java.util.*;

public class honkReader {

DBConnect connect = new DBConnect( );

honkReader( ){
    //default constructor
}


/**
 *This method checks if user chooses to make post private or public
*@param user holds the string of user's username
*@param honk holds the string of user's honk post
* @return false if user is not in database
* @return false returns false if user does not choose public or private
* */
public boolean post(String user, String honk, String setting){

if(!connect.getUser(user)){
return false;
}else{
if(setting == "Public"){
connect.insertPublicPost(user,honk);
}else if(setting == "Private"){
connect.insertPrivatePost(user,honk);
}else{
return false;
}
return true;
}
}

/**
 *This method gets all posts posted by a user
*@param username holds the string of user's username
* @return honk returns all "honks"/ posts posted by a user. 
* */
public LinkedList<String> getAllPosts(String username){
LinkedList<String> honkList = connect.getPublicPost( );
if(username == "null"){

}else{
LinkedList<String> subList = connect.getSubscribe(username);
Iterator<String> subListIt = subList.iterator( );
while(subListIt.hasNext( )){
String subscribe = subListIt.next( );
LinkedList<String> subPrivPosts = connect.getPrivatePost(subscribe);
Iterator<String> subPrivPostsIt = subPrivPosts.iterator( );
while(subPrivPostsIt.hasNext( )){
honkList.add(subPrivPostsIt.next( ));
}
}
LinkedList<String> yourPrivPosts = connect.getPrivatePost(username);
Iterator<String> privPostsIterator = yourPrivPosts.iterator( );
while(privPostsIterator.hasNext( )){
    honkList.add(privPostsIterator.next( ));
}
}
return honkList;
}


/**
 *This method gets all users current user is subscribed to 
*@param username holds the string of user's username
* @return honk returns all "honks"/ posts posted by a subscriber.
* */
public LinkedList<String> getSubscribers(String username){    
LinkedList<String> honkList = connect.getSubscribe(username);
if(username == "null"){

}else{
LinkedList<String> subList = connect.getSubscribe(username);
Iterator<String> subListIt = subList.iterator( );
while(subListIt.hasNext( )){
String subscribe = subListIt.next( );
LinkedList<String> subPrivPosts = connect.getPrivatePost(subscribe);
Iterator<String> subPrivPostsIt = subPrivPosts.iterator( );
while(subPrivPostsIt.hasNext( )){
honkList.add(subPrivPostsIt.next( ));
}
}
}
return honkList;
}

/**
 *This method...
*@param username 
* @param keyword 
* @return error 
* @return finalReturn 
* */

//Guys, I cannot understand what this methos is doing.
public LinkedList<String> search(String username, String keyword){
LinkedList<String> error = new LinkedList<>( );
error.add("The search term was not found.");
LinkedList<String> honkList = getAllPosts(username);
String honk;
LinkedList<String> finalReturn = new LinkedList<>( );
boolean found = false;
keyword = "#"+keyword;
Iterator<String> iterator = honkList.iterator( );
while(iterator.hasNext( )){
honk = iterator.next( );
iterator.remove( );
if(honk.indexOf(keyword)==-1){

}else{
finalReturn.add(honk);
found = true;
}
}
if(found == false){
return error;
}else{
return finalReturn;
}
}
}
