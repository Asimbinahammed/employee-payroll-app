package com.bridgelabz.employeepayroll.advice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Purpose : To define AOP
 *
 * @author ASIM AHAMMED
 * @version : 0.0.1-SNAPSHOT
 * @since 2021-12-03
 */
@Component
@Aspect
public class LoggingAdvice {

    Logger logger = LoggerFactory.getLogger(LoggingAdvice.class);

    /**
     * Purpose : To declare pointcuts.
     */
    @Pointcut(value = "execution (* com.bridgelabz.employeepayroll.services.PayrollServices.*(..))")
    public void myPointCut() {
    }

    /**
     * Purpose : Defining log message with class, method names & parameters used in method.
     *
     * @param proceedingJoinPoint : When invoked, the code execution jumps to the next advice or to the target method.
     * @return objectAfterProceeding : It's the response of that method.
     * @throws Throwable :  constructing JSON parser ( JsonParser ) and generator ( JsonGenerator ) instances and also
     *                   exception for proceed.
     */
    @Around("myPointCut()")
    private Object applicationLogger(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        ObjectMapper mapper = new ObjectMapper();
        String methodName = proceedingJoinPoint.getSignature().getName();
        String className = proceedingJoinPoint.getTarget().getClass().toString();
        Object[] array = proceedingJoinPoint.getArgs();
        logger.info("Method invoked: " + className + " : " + methodName + "()" + "arguments : "
                + mapper.writeValueAsString(array));
        Object objectAfterProceeding = proceedingJoinPoint.proceed();
        logger.info(className + " : " + methodName + "()" + "Response : "
                + objectAfterProceeding);
        return objectAfterProceeding;
    }

}
