/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zoomrecorder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author tarek
 */
public class ClassDb {
    String url = "jdbc:mysql://td5l74lo6615qq42.cbetxkdyhwsb.us-east-1.rds.amazonaws.com:3306/j0z2rlzcsmdpb77f";
    String user = "xb4okm73tltld7mb";
    String password = "ra7q2yok5dh3zsrl";
    ResultSet resultSet = null;
    String returnString;
    public String dbConnect(String elementToSelect){
        // connect to database
        try (Connection connection = DriverManager.getConnection(url, user, password)) {

            Statement statement = connection.createStatement();
            // Create and execute a SELECT SQL statement.
            String selectSql = "SELECT "+ elementToSelect+" from ClassDB";
            resultSet = statement.executeQuery(selectSql);

            
            // assign the element to a string
            while (resultSet.next()) {
                returnString = resultSet.getString(1);
            }
            return returnString;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    //get Cron format 
    public String getCron(){
        return dbConnect("CronFormat");
    }
    
    //get Duration of class
    public int getDuration(){
        return Integer.parseInt(dbConnect("ClassDuration"));
    }
    
    // get the id of the class
    public String getId(){
        return dbConnect("ClassId");
    }
}

