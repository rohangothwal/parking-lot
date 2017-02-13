package com.parkinglot.config;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource({"app.properties"})
@ComponentScan(basePackages = {"com.parkinglot"})
public class AppConfig {
	
	@Autowired
	private Environment env;
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigIn() {
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	@Bean
	public ExecutorService processingThreadPool(){
		ThreadPoolExecutor processingThreadPool = new ThreadPoolExecutor(env.getProperty("processing.thread.pool.corepoolsize", Integer.class, 5), 
				env.getProperty("processing.thread.pool.maxpoolsize", Integer.class, 10), 
				env.getProperty("processing.thread.pool.keepalivetime", Integer.class, 120), 
				TimeUnit.SECONDS, new SynchronousQueue<>(),new ThreadPoolExecutor.CallerRunsPolicy());
		
		return processingThreadPool;
	}
	
	@Bean
	public ExecutorService dataFetchThreadPool(){
		ThreadPoolExecutor processingThreadPool = new ThreadPoolExecutor(env.getProperty("datafetch.thread.pool.corepoolsize", Integer.class, 5), 
				env.getProperty("datafetch.thread.pool.maxpoolsize", Integer.class, 10), 
				env.getProperty("datafetch.thread.pool.keepalivetime", Integer.class, 120), 
				TimeUnit.SECONDS, new SynchronousQueue<>(),new ThreadPoolExecutor.CallerRunsPolicy());
		
		return processingThreadPool;
	}
	
	@Bean
	public ExecutorService networkIOThreadPool(){
		ThreadPoolExecutor processingThreadPool = new ThreadPoolExecutor(env.getProperty("networkio.thread.pool.corepoolsize", Integer.class, 5), 
				env.getProperty("networkio.thread.pool.maxpoolsize", Integer.class, 10), 
				env.getProperty("networkio.thread.pool.keepalivetime", Integer.class, 120), 
				TimeUnit.SECONDS, new SynchronousQueue<>(),new ThreadPoolExecutor.CallerRunsPolicy());
		
		return processingThreadPool;
	}
}