package com.example.Playlist.Generator.App.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.logging.Logger;

@Aspect
@Component
@Slf4j
public class MethodLoggerAspect {

    //Logger log = Logger.getLogger(this.getClass().getName());
    @Around("@annotation(com.example.Playlist.Generator.App.customTags.Log)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        log.info("Method: " + joinPoint.getSignature().getName() + " is about to execute" +
                " with parameters: " + Arrays.toString(joinPoint.getArgs()) + ".");
        Object returnedValue = joinPoint.proceed();
        log.info("Method: " + joinPoint.getSignature().getName() + " executed successfully.\n");
        return returnedValue;

    }

}
