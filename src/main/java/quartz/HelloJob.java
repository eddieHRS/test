package quartz;

import org.quartz.Job;
import org.slf4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * @title: HelloJob
 * @Author eddie
 * @Date: 2021/3/10 10:27
 * @Version 1.0
 */
public class HelloJob implements Job {
    private static Logger _log = LoggerFactory.getLogger(HelloJob.class);
    public HelloJob() {
    }
    public void execute(JobExecutionContext context)
            throws JobExecutionException {
        _log.info("Hello World! - " + new Date());
    }
}
