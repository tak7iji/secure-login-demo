package org.terasoluna.securelogin.domain.common.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceCallLoggingAspect {

	private static final Logger Logger = LoggerFactory.getLogger(ServiceCallLoggingAspect.class);
	
	@Before("@within(org.springframework.stereotype.Service)")
	public void before(JoinPoint jp){
		Logger.trace("[START SERVICE]"+jp.getSignature().toShortString());
	}
	
	@AfterReturning("@within(org.springframework.stereotype.Service)")
	public void afterReturning(JoinPoint jp){
		Logger.trace("[COMPLETE SERVICE]"+jp.getSignature().toShortString());
	}
	
	@AfterThrowing(value="@within(org.springframework.stereotype.Service)", throwing="ex")
	public void afterThrowing(JoinPoint jp, Throwable ex){
		Logger.trace("[SERVICE THROWS EXCEPTION]"+jp.getSignature().toShortString());
		Logger.trace(ex.getMessage());
	}
	
}
