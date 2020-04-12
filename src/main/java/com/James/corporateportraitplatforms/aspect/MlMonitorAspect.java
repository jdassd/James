package com.James.corporateportraitplatforms.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Aspect
@Component
public class MlMonitorAspect {

    @Pointcut("execution(* sweeneyhe.Ml.*(..)) || execution(* com.James.corporateportraitplatforms.service.CsvService.saveCompanyFlag2File(..))")
    public void useTimePointCut(){}

    @Around(value = "useTimePointCut()")
    public Object methodAround(ProceedingJoinPoint joinPoint) throws Throwable {
        final String methodName = joinPoint.getSignature().getName();

        log.info("Start running {}", methodName);

        long startTime = new Date().getTime();
        final Object proceedReturn = joinPoint.proceed();
        long endTime = new Date().getTime();

        log.info("{} used time {}s", methodName, ((double)endTime - startTime) / 1000);

        return proceedReturn;
    }
}
