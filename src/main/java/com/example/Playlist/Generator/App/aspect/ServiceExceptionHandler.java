package com.example.Playlist.Generator.App.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
@Slf4j
public class ServiceExceptionHandler {

    //private Logger log = Logger.getLogger(ServiceExceptionHandler.class.getName());

    @AfterThrowing("@annotation(customTags.com.example.Playlist.Generator.App.CatchSpotifyApiExceptions)")
    public void handleError(JoinPoint joinPoint, Throwable error) throws Throwable {

        log.info("Method: " + joinPoint.getSignature() + " has encountered an error.\n");
        log.info("***Error: " + error.getMessage() + "***");
    }
}
