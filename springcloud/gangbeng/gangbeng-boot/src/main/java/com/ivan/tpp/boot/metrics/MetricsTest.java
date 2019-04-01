package com.ivan.tpp.boot.metrics;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.RatioGauge;

public class MetricsTest {

	public static final MetricRegistry REGISTRY = new MetricRegistry(); 
	
	public static void main(String[] args) {
		
	}
	
	public void gauge(){
//		MetricRegistry.name(klass, names)
//		REGISTRY.register(MetricRegistry.name(MetricsTest.class, "123"), new Gauge)
	}
	
}
