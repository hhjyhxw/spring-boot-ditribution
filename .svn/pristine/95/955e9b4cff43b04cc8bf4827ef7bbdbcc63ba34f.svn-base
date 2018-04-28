//package com.icloud.config.quartz;
//
//import org.quartz.Scheduler;
//import org.quartz.SchedulerException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.quartz.SchedulerFactoryBean;
///**
// * 
// * 
// *
// */
//@Configuration
//public class SchedulerConfig {
//    @Autowired
//    private ApplicationContext applicationContext;
//
//	@Bean(name = "scheduler")
//	public Scheduler createScheduler(SchedulerFactoryBean SchedulerFty) {
//		Scheduler scheduler = SchedulerFty.getScheduler();
//		return scheduler;
//
//	}
//	
//    @Bean
//    public SchedulerFactoryBean getSchedulerFactoryBean() throws SchedulerException {
//        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
//        // 将Quartz纳入Spring管理范围
//        AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
//        jobFactory.setApplicationContext(applicationContext);
//        schedulerFactoryBean.setJobFactory(jobFactory);
//        return schedulerFactoryBean;
//    }
//    
//
//}