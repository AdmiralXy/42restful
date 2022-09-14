package com.admiralxy.restful.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

@Aspect
@Component
public class ServicePerformanceAspect {

    private final Logger logger = Logger.getLogger(getClass().getName());

    @Pointcut("within(@org.springframework.stereotype.Service *)")
    public void serviceClassMethods() {}

    @Around("serviceClassMethods()")
    public Object measureMethodExecutionTime(ProceedingJoinPoint pjp) throws Throwable {
        Object returnValue = pjp.proceed();
        long start = System.nanoTime();
        long end = System.nanoTime();
        String methodName = pjp.getSignature().getName();
        String className = pjp.getTarget().getClass().getSimpleName();
        String log = String.format("Execution of %s.%s() took %d ms", className, methodName,
                TimeUnit.NANOSECONDS.toMillis(end - start));
        logger.info(log);
        return returnValue;
    }
}
