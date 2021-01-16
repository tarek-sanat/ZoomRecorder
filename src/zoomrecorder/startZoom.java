/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zoomrecorder;

import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.IOException;

/**
 *
 * @author tarek
 */
public class startZoom {  
    
    public void launchZoom(Robot robot) throws InterruptedException{
        Runtime runtime = Runtime.getRuntime();     //getting Runtime object
 
        try{
            runtime.exec("C:\\Users\\tarek\\AppData\\Roaming\\Zoom\\bin\\zoom"); //opens new zoom instance
            Thread.sleep(4000);
            int dimensions[] = windowRect();
            System.out.println(dimensions[0]+" "+dimensions[1]+" "+dimensions[2]+" "+dimensions[3]);
            maximizeWindow(robot,dimensions[0],dimensions[1] );
 
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    
    
    // will return the size of the zoom window
    public int[] windowRect(){
        
        int[] rect;
        try {
            rect = GetWindowRect.getRect("Zoom");
            return rect;
        } 
      
        catch (GetWindowRect.WindowNotFoundException | GetWindowRect.GetWindowRectException e) {
            return null;
        }
    }
    
    // will maximize the zoom window
    public void maximizeWindow(Robot robot, int x, int y){
        // move mouse over window bar
        robot.mouseMove(x + 100, y+10);
        // double click on it
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
    }
    
    //clickOnJoin
    public void clickJoin(Robot robot){
        // move mouse over join button
        robot.mouseMove(805, 445);
        //click on join button
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
    }
    
    //joinMeeting
    //clickOnJoin
    public void joinMeeting(Robot robot){
        // move mouse over join meeting
        robot.mouseMove(983, 654);
        // join meeting
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
    }
}
