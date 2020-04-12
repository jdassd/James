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
public class SqlInsertMonitorAspect {
    @Pointcut("execution(* com.James.corporateportraitplatforms.mapper.CompanyMapper.insertBatch*(..))")
    public void SqlBatchPointCutByCompany() {}
    @Pointcut("execution(* com.James.corporateportraitplatforms.mapper.KnowledgeReportMapper.insertBatch*(..))")
    public void SqlBatchPointCutByKnowledge() {}
    @Pointcut("execution(* com.James.corporateportraitplatforms.mapper.MoneyReportMapper.insertBatch*(..))")
    public void SqlBatchPointCutByMoneyReport() {}
    @Pointcut("execution(* com.James.corporateportraitplatforms.mapper.YearReportMapper.insertBatch*(..))")
    public void SqlBatchPointCutByYearReport() {}
    @Pointcut("execution(* com.James.corporateportraitplatforms.mapper.CompanyScoreMapper.insertBatch*(..))")
    public void SqlBatchPointCutByScore() {}
    @Pointcut("execution(* com.James.corporateportraitplatforms.mapper.CompanyShowDataMapper.insertBatch*(..))")
    public void SqlBatchPointCutByShowData() {}

    @Around(value = "SqlBatchPointCutByCompany()")
    public Object takeUpTimeByCompany(ProceedingJoinPoint joinPoint) throws Throwable {
        return handleJoinPoint(joinPoint, "CompanyMapper");
    }

    @Around(value = "SqlBatchPointCutByKnowledge()")
    public Object takeUpTimeByKnowledge(ProceedingJoinPoint joinPoint) throws Throwable {
        return handleJoinPoint(joinPoint, "KnowledgeMapper");
    }
    @Around(value = "SqlBatchPointCutByMoneyReport()")
    public Object takeUpTimeByMoneyReport(ProceedingJoinPoint joinPoint) throws Throwable {
        return handleJoinPoint(joinPoint, "MoneyReportMapper");
    }
    @Around(value = "SqlBatchPointCutByYearReport()")
    public Object takeUpTimeByYearReport(ProceedingJoinPoint joinPoint) throws Throwable {
        return handleJoinPoint(joinPoint, "YearReportMapper");
    }

    @Around(value = "SqlBatchPointCutByScore()")
    public Object takeUpTimeByScore(ProceedingJoinPoint joinPoint) throws Throwable {
        return handleJoinPoint(joinPoint, "scoreMapper");
    }
    @Around(value = "SqlBatchPointCutByShowData()")
    public Object takeUpTimeByShowData(ProceedingJoinPoint joinPoint) throws Throwable {
        return handleJoinPoint(joinPoint, "showDataMapper");
    }

    private Object handleJoinPoint(ProceedingJoinPoint joinPoint, String label) throws Throwable {
        final String methodName = joinPoint.getSignature().getName(); // 获取方法名

        log.info("Start running {} {}", label, methodName);

        long startTime = new Date().getTime();
        final Object proceedReturn = joinPoint.proceed();
        long endTime = new Date().getTime();

        log.info("{} {} use time {}s", label, methodName, ((double)endTime - startTime) / 1000);
        return proceedReturn;
    }

}
