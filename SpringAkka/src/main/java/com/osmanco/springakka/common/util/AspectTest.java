package com.osmanco.springakka.common.util;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectTest {

    private static Logger logger = LoggerFactory.getLogger(AspectTest.class);

    @Pointcut("execution(@com.osmanco.springakka.common.util.PerfLog * *.*(..))")
    public void performanceTargets() {
    }

    @Around("performanceTargets()")
    public Object logPerformanceStats(ProceedingJoinPoint joinpoint) {
        try {
            long start = System.nanoTime();
            Object result = joinpoint.proceed();
            long end = System.nanoTime();
            logger.info(String.format("%s took %d ns", joinpoint.getSignature(), (end - start)));
            return result;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
    
   @Before("performanceTargets()")
    public void beforeMethod() {
        logger.info("before method");
    }
    
    @After("performanceTargets()")
    public void afterMethod() {
        logger.info("after method");
    }    
}
