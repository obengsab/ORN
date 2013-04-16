
import java.sql.*;
import java.util.*;


public class DBConnect {
    Scanner in = new Scanner(System.in);   
    private Connection con;
    private Statement stmt;
    private ResultSet rs;    
  
public DBConnect(){
    try{   
        Class.forName("com.mysql.jdbc.Driver");
        
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/orn","root","");
        stmt = con.createStatement();


                
    }
    catch(Exception exp){
    System.out.println("YOU SUCK AT CONNECTING"+exp);
    }
}   
public void insertUser(String username,String password){
    try{
              
            String insert = "INSERT INTO user (username, password) VALUES ('"+username+"','"+password+"')";
            stmt.executeUpdate(insert);
            System.out.println("  ");
    }
        catch(Exception e)
        {
            System.out.println("YOU SUCK AT INSERTING A USER"+e);
        }
}   
public void insertSubscribe(String currentuser,String subname){
    try{
            String id = getCurrentUser(currentuser);
            String insert = "INSERT INTO subscribe (user_id,subname) VALUES ('"+id+"','"+subname+"')";
            stmt.executeUpdate(insert);
    }
        catch(Exception e)
        {
            System.out.println("YOU SUCK AT SUBSCRIBING"+e);
        }
}   
public void insertPrivatePost(String currentuser,String pripost){
   try{
            String id = getCurrentUser(currentuser);
            String insert = "INSERT INTO private_post (user_id,pri_post) VALUES ('"+id+"','"+pripost+"')";  
            stmt.executeUpdate(insert);
  }
        catch(Exception e)
        {
            System.out.println("YOU SUCK AT INSERTING PRIVATE POST"+e);
        }
}
public void insertPublicPost(String currentuser, String pubpost){
   try{
            String id = getCurrentUser(currentuser);
            String insert = "INSERT INTO public_post (user_id,post) VALUES ('"+id+"','"+pubpost+"')";
            stmt.executeUpdate(insert);
  }
        catch(Exception e)
        {
            System.out.println("YOU SUCK AT INSERTING PUBLIC POST"+e);
        }
}
public void insertProfile(){    //MAY HAVE PROBLEMS WITH WRITTING MORE THEN ONE PROFILE FOR A PERSON
   try{
        System.out.println("ENTER Profile Info");
        System.out.println("ENTER USER_ID");
        String user_id = in.nextLine();
        System.out.println("ENTER FIRST NAME");
        String firstname = in.nextLine();
        System.out.println("ENTER LAST NAME");
        String lastname = in.nextLine();
        System.out.println("ENTER ABOUT ME SECTION");
        String aboutme = in.nextLine();
        System.out.println("ENTER AGE");
        String age = in.nextLine();
              
        String insert = "INSERT INTO profile (user_id,firstname,lastname,aboutme,age) VALUES ('"+user_id+"','"+firstname+"','"+lastname+"','"+aboutme+"','"+age+"')";
            System.out.println(insert);
            stmt.executeUpdate(insert);
            System.out.println("");
   } 
        catch(Exception e)
        {
            System.out.println("YOU SUCK AT INSERTING PROFILE");
        }
}
public boolean getUser(String testuser){
        try{   
            String query = "SELECT username FROM user";
            rs = stmt.executeQuery(query);      
            System.out.println("Getting USERNAME");
            
            while(rs.next()){     
                String username =rs.getString("username");
                if(testuser.equals(username)){
                    return true;
                }  
                
            } 
            return false;
        }
        catch(Exception ex){
            System.out.println("YOU SUCK AT GETTING USER"+ex);
            return false;
        }  
}
public String getCurrentUser(String currentuser){
    String user_id = "-1";    
    try{      
            String query = "SELECT user_id FROM user WHERE username = '"+currentuser+"'";
            rs = stmt.executeQuery(query);  
    
            while(rs.next()){
                user_id =rs.getString("user_id");           

            }          
        }
        catch(Exception ex){
            System.out.println("YOU SUCK AT GETTING CURRENT USER NUM"+ex);
        }    
        return user_id;
}
public LinkedList<String> getPrivatePost(String currentuser){
        LinkedList<String> list = new LinkedList<>();
        
    try{  
            String id = getCurrentUser(currentuser);
            String query = "SELECT * FROM private_post WHERE user_id = '"+id+"'";
            rs = stmt.executeQuery(query);
            System.out.println("Getting private posts");
    
            while(rs.next()){
                String privatePost =rs.getString("pri_post");
                list.add(privatePost);
            }          
        }catch(Exception ex){
            System.out.println("YOU SUCK AT GETTING PRIVATE POSTS"+ex);
        }
        return list;
} 
public LinkedList<String> getPublicPost(){
    LinkedList<String> list = new LinkedList<>();
        try{            
            String query = "SELECT * FROM public_post";
            rs = stmt.executeQuery(query);
            System.out.println("Getting public posts");
    
            while(rs.next()){
                String post =rs.getString("post");
                list.add(post);
            }          
        }catch(Exception ex){
            System.out.println("YOU SUCK AT GETTING PUBLIC POSTS"+ex);
        }
        return list;
} 
public LinkedList<String> getSubscribe(String currentuser){
    LinkedList<String> list = new LinkedList<>();
        try{            
            String id = getCurrentUser(currentuser);
            String query = "SELECT subname FROM subscribe WHERE user_id ='"+id+"'";
            rs = stmt.executeQuery(query);
    
            while(rs.next()){
                String subname =rs.getString("subname");
                list.add(subname);
            }          
        }catch(Exception ex){
            System.out.println("YOU SUCK AT GETTING PUBLIC POSTS"+ex);
        }
        return list;
} 
public String getName(String currentuser){   //LOOKS WEIRD WITH NO ABOUT ME INFO *** EDIT FOR ONLY A SPECIFIC USER
    String name = "DB ERROR";   
    try{            
            String id = getCurrentUser(currentuser);
            if(id.equals("-1")){
                System.out.println("Cant do nothing");
            }else{
            String query = "SELECT firstname,lastname FROM profile WHERE user_id = '"+id+"'";
            
            rs = stmt.executeQuery(query);
            
            while(rs.next()){
                String firstname =rs.getString("firstname");
                String lastname =rs.getString("lastname");
 
                name = firstname+" "+lastname;
            }          
            }
        }catch(Exception ex){
            System.out.println("YOU SUCK AT GETTING A NAME"+ex);
        }
      return name;  
}  
public String getAge(String currentuser){   
    String age = "DB ERROR";   
    try{            
            String id = getCurrentUser(currentuser);
            if(id.equals("-1")){
                System.out.println("Cant do nothing");
            }else{
            String query = "SELECT age FROM profile WHERE user_id = '"+id+"'";
            
            rs = stmt.executeQuery(query);
            
            while(rs.next()){
                age =rs.getString("age");
                
            }          
            }
        }catch(Exception ex){
            System.out.println("YOU SUCK AT GETTING AGE"+ex);
        }
      return age;  
}  
public String getAboutMe(String currentuser){   
    String AboutMe = "DB ERROR";   
    try{            
            String id = getCurrentUser(currentuser);
            if(id.equals("-1")){
                System.out.println("Cant do nothing");
            }else{
            String query = "SELECT aboutme FROM profile WHERE user_id = '"+id+"'";
            
            rs = stmt.executeQuery(query);
            
            while(rs.next()){
                AboutMe =rs.getString("aboutme");
                
            }          
            }
        }catch(Exception ex){
            System.out.println("YOU SUCK AT GETTING ABOUT ME"+ex);
        }
      return AboutMe;  
}  
public boolean checkPassword(String testuser,String testpass){
        try{      
            String query = "SELECT password FROM user WHERE username = '"+testuser+"'";
            rs = stmt.executeQuery(query);  
            System.out.println("CHECKING PASSWORD");
    
            while(rs.next()){
    
                String password =rs.getString("password");
                
                if(password.equals(testpass)){
           
                return true;
            }          
                
        } return false;
        }
        catch(Exception ex){
            System.out.println("YOU SUCK AT CHECKING FOR PASSWORD"+ex);
            return false;
        }    
}
public void checkUsername(){
        try{      
            String query = "SELECT username FROM user WHERE username = 'HAPPYfeet'";
            rs = stmt.executeQuery(query);  
            System.out.println("CHECKING USERNAME");
    
            while(rs.next()){
                String username =rs.getString("username");
           
                
                System.out.println(""+username+"");
                System.out.println("  ");
         
            }          
        }
        catch(Exception ex){
            System.out.println("YOU SUCK AT CHECKING FOR USERNAME"+ex);
        }    
}

}   
