package com.jts.cache.config;

import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jts.cache.entity.Employee;

@Configuration
public class CacheBeans {

	@Bean
	public CacheStore<Employee> employeeCache() {
		return new CacheStore<>(10, TimeUnit.MINUTES);
	}
}
