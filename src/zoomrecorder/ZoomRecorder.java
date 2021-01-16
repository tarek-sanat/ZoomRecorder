/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zoomrecorder;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class ZoomRecorder extends GetWindowRect  {
    
   

    public static void main(String[] args) throws SchedulerException, InterruptedException {
        // get the cron string 
        ClassDb db = new ClassDb();
        String cron = db.getCron();
        //define job and tie to job class
        JobDetail job = JobBuilder.newJob(startRecord.class).build();
        //CronScheduleBuilder.cronSchedule("0 0 17 ? * WED *")
        // define a trigger with a specific interval 
        //Trigger t1 = TriggerBuilder.newTrigger().withIdentity("StartTime").withSchedule(CronScheduleBuilder.cronSchedule(cron)).build();
        Trigger t1 = TriggerBuilder.newTrigger().withIdentity("s").startNow().build();
        Scheduler sc = StdSchedulerFactory.getDefaultScheduler();
        sc.start();
        // repeat the job every time the trigger t1 is activated 
        sc.scheduleJob(job, t1);
        
        
              
    }
    
}