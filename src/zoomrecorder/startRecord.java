/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zoomrecorder;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 *
 * @author tarek
 */
public class startRecord extends ClassDb implements Job {

    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
        
        try {
            Robot robot = new Robot();
            startZoom launch = new startZoom();
            launch.launchZoom(robot);

            //connect to class
            joinClass(robot, launch);
            // start the recording by pressing alt + f9
            StartStopRecording();
            
            //get duration of class and sleep for that duration
            Thread.sleep(getDuration()*1000);
            
            //stop the recording of the class
            StartStopRecording();
            

        } catch (AWTException e) {
            e.printStackTrace();
        } catch (InterruptedException ex) {
            Logger.getLogger(startRecord.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }
    
    // press alt+f9 and start or stop recording the class
    public void StartStopRecording() throws AWTException{
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ALT);
        robot.keyPress(KeyEvent.VK_F9);
        robot.keyRelease(KeyEvent.VK_ALT);
        robot.keyRelease(KeyEvent.VK_F9);
    }
    
    // join class and launch the meeting 
    public void joinClass(Robot robot, startZoom launch) throws InterruptedException{
        launch.clickJoin(robot);
        Thread.sleep(1000);
        enterClassId(robot, getId());
        launch.joinMeeting(robot);
        
        
    }
    
    
    // enter the id of the class in the zoom box
    public static void enterClassId(Robot robot, String id) throws InterruptedException{
        
        StringSelection stringSelection = new StringSelection(id);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, stringSelection);
        
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        
    }
}
