package com.James.corporateportraitplatforms.aspect;

import com.James.corporateportraitplatforms.model.Company;
import com.James.corporateportraitplatforms.model.KnowledgeReport;
import com.James.corporateportraitplatforms.model.MoneyReport;
import com.James.corporateportraitplatforms.model.YearReport;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

@Slf4j
@Aspect
@Component
@ConfigurationProperties(prefix = "my-aspect.sql-split")
class MonitorAspect {
    
    private boolean enableSqlSplit = false;
    private int blockSize;

    public boolean isEnableSqlSplit() {
        return enableSqlSplit;
    }

    public void setEnableSqlSplit(boolean enableSqlSplit) {
        this.enableSqlSplit = enableSqlSplit;
    }

    public int getBlockSize() {
        return blockSize;
    }

    public void setBlockSize(int blockSize) {
        this.blockSize = blockSize;
    }

    @Pointcut("execution(* com.James.corporateportraitplatforms.mapper.CompanyMapper.insertBatch(..)) && args(companyList)")
    public void SqlBatchPointCutByCompany(Object companyList) {}
    @Pointcut("execution(* com.James.corporateportraitplatforms.mapper.KnowledgeReportMapper.insertBatch(..)) && args(companyList)")
    public void SqlBatchPointCutByKnowledge(Object companyList) {}
    @Pointcut("execution(* com.James.corporateportraitplatforms.mapper.MoneyReportMapper.insertBatch(..)) && args(companyList)")
    public void SqlBatchPointCutByMoneyReport(Object companyList) {}
    @Pointcut("execution(* com.James.corporateportraitplatforms.mapper.YearReportMapper.insertBatch(..)) && args(companyList)")
    public void SqlBatchPointCutByYearReport(Object companyList) {}

    @Around(value = "SqlBatchPointCutByCompany(paramData)", argNames = "joinPoint,paramData")
    public Object takeUpTimeByCompany(ProceedingJoinPoint joinPoint, Object paramData) throws Throwable {
        return handleJoinPoint(joinPoint, paramData, "CompanyMapper");
    }

    @Around(value = "SqlBatchPointCutByKnowledge(paramData)", argNames = "joinPoint,paramData")
    public Object takeUpTimeByKnowledge(ProceedingJoinPoint joinPoint, Object paramData) throws Throwable {
        return handleJoinPoint(joinPoint, paramData, "KnowledgeMapper");
    }
    @Around(value = "SqlBatchPointCutByMoneyReport(paramData)", argNames = "joinPoint,paramData")
    public Object takeUpTimeByMoneyReport(ProceedingJoinPoint joinPoint, Object paramData) throws Throwable {
        return handleJoinPoint(joinPoint, paramData, "MoneyReportMapper");
    }
    @Around(value = "SqlBatchPointCutByYearReport(paramData)", argNames = "joinPoint,paramData")
    public Object takeUpTimeByYearReport(ProceedingJoinPoint joinPoint, Object paramData) throws Throwable {
        return handleJoinPoint(joinPoint, paramData, "YearReportMapper");
    }

    private Object handleJoinPoint(ProceedingJoinPoint joinPoint, Object paramData, String label) throws Throwable {
        final String methodName = joinPoint.getSignature().getName(); // 获取方法名
        List<Object> companyList = (List<Object>) paramData;
        int dataSize = companyList.size();
        int packetSize = blockSize;
//
        long startTime = new Date().getTime();

        if (enableSqlSplit) {
            log.info("sql split model");
            for (int i = 0; i < dataSize;) {
                int endIndex = Math.min(i + packetSize, dataSize);
                final List<Object> subCompanyList = companyList.subList(i, endIndex);
                joinPoint.proceed(new Object[]{subCompanyList,});
                i = endIndex;
            }
        } else joinPoint.proceed();
        long endTime = new Date().getTime();
        log.info("{} {}({}) use time {}", label, methodName, dataSize, endTime - startTime);
        return null;
    }
}
