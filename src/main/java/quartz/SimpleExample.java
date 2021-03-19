package quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.quartz.DateBuilder.evenMinuteDate;

import java.util.Date;

/**
 * @title: SimpleExample
 * @Author eddie
 * @Date: 2021/3/10 10:31
 * @Version 1.0
 */

public class SimpleExample {
    private static Logger log = LoggerFactory.getLogger(SimpleExample.class);

    public void run() throws Exception {

        log.info("------- 初始化----------------------");

        // 首先，我们得到一个scheduler实例
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();

        log.info("------- 初始化完成 -----------");

        // computer a time that is on the next round minute
        Date runTime = evenMinuteDate(new Date());

        log.info("------- 调度任务 -------------------");

        // define the job and tie it to our HelloJob class
        JobDetail job = JobBuilder.newJob(HelloJob.class).withIdentity("job1", "group1").build();

        // Trigger the job to run on the next round minute
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1").startAt(runTime).build();

        // 告诉quartz利用trigger触发器来调度job
        sched.scheduleJob(job, trigger);
        log.info(job.getKey() + " will run at: " + runTime);

        // Start up the scheduler (nothing can actually run until the
        // scheduler has been started)
        sched.start();

        log.info("------- 任务已经已经启动了 -----------------");

        // wait long enough so that the scheduler as an opportunity to
        // run the job!
        log.info("------- Waiting 65 seconds... -------------");
        try {
            // wait 65 seconds to show job
            Thread.sleep(65L * 1000L);
            // executing...
        } catch (Exception e) {
            //
        }

        // shut down the scheduler
        log.info("------- 调度关闭 ---------------------");
        sched.shutdown(true);
        log.info("------- 关闭完成 -----------------");
    }
    public static void main(String[] args) throws Exception {

        SimpleExample example = new SimpleExample();
        example.run();

    }
}

