package com.jts.cache.config;

import java.util.concurrent.TimeUnit;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

public class CacheStore<T> {
	
	private Cache<String, T> cache;
	
	public CacheStore(int expiryDuration, TimeUnit timeUnit) {
		cache = CacheBuilder.newBuilder().expireAfterWrite(expiryDuration, timeUnit)
		.concurrencyLevel(Runtime.getRuntime().availableProcessors()).build();
	}
	
	public T get(String key) {
		return cache.getIfPresent(key);
	}
	
	public void add(String key, T value) {
		cache.put(key, value);
		System.out.println("Data inserted into cache with key :: " + key);
	}

}
