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

    @Pointcut("execution(* sweeneyhe.Ml.*(..))")
    public void mlPointCut(){}
    @Pointcut("execution(* com.James.corporateportraitplatforms.service.CsvService.saveCompanyFlag2File(..))")
    public void saveCsv2FilePointCut(){}

    @Around(value = "mlPointCut()")
    public Object methodAround(ProceedingJoinPoint joinPoint) throws Throwable {
        final String methodName = joinPoint.getSignature().getName();

        log.info("Start running {}", methodName);

        long startTime = new Date().getTime();
        final Object proceedReturn = joinPoint.proceed();
        long endTime = new Date().getTime();

        log.info("{} used time {}s", methodName, ((double)endTime - startTime) / 1000);

        return proceedReturn;
    }

    @Around("saveCsv2FilePointCut()")
    public Object saveFileAround(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Start running saveCsvFile");

        long startTime = new Date().getTime();
        final Object proceedReturn = joinPoint.proceed();
        long endTime = new Date().getTime();

        log.info("saveCsvFile used time {}s", ((double)endTime - startTime) / 1000);

        return proceedReturn;
    }
}
