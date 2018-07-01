package ru.tver.hack.aop;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import ru.tver.hack.models.Project;

import java.util.Arrays;

/**
 * Date 01.07.2018
 *
 * @author Hursanov Sulaymon
 * @version v1.0
 **/

@Aspect
@Component
@Slf4j
public class LoggingAspectProject {


    @Pointcut("within(ru.tver.hack.services.implementations.ProjectServiceImpl)")
    public void serviceLayer(){
    }

    @Before(value = "serviceLayer() && args(project,..)")
    public void beforeAddNewProjectMethodExecution(JoinPoint joinPoint, Project project){
        log.info("argument project: " + project.toString());
    }

    // runs before given pointcut and method which has parameter uuid
    @Before(value = "serviceLayer() && args(uuid)")
    public void beforeGetProjectByUuidMethod(JoinPoint joinPoint, String uuid){
        log.info("argument uuid: " + uuid);
    }

    @Before(value = "execution(* ru.tver.hack.services.implementations.ProjectServiceImpl.updateProject()) && args(project)")
    public void beforeUpdateProjectMethod(Project project){
        log.info("updated project: " + project.toString());
    }

    // logging object which returns method "getProjectByUuid(..)"
    @AfterReturning(pointcut = "execution(* ru.tver.hack.services.implementations.ProjectServiceImpl.getProjectByUuid(..))", returning = "project")
    public void returningGetProjectByUuidMethod(JoinPoint joinPoint, Object project) throws Throwable{
        log.info(joinPoint.getSignature().getName()+" return: " + ((Project)project).toString());
        log.info("--------------------------------------");
    }

    // get time execution of the method
    // this method will start and stop for given pointcut
    @Around(value = "ru.tver.hack.aop.LoggingAspectProject.serviceLayer()")
    public Object logAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object value;
        log.info("--------------------------------------");
        log.info("method: "+ proceedingJoinPoint.getSignature().getName());
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        value = proceedingJoinPoint.proceed();
        stopWatch.stop();
        log.info("execution time: " + stopWatch.getTotalTimeMillis() + "ms");
        log.info("--------------------------------------");
        return value;
    }
    // this method will run for addNewProject(..)
//    @Around("execution(* ru.tver.hack.services.implementations.ProjectServiceImpl.addNewProject(..))")
    public void logAroundAddNewProject(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        log.info("method: "+ proceedingJoinPoint.getSignature().getName());
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        proceedingJoinPoint.proceed();
        stopWatch.stop();
        log.info("execution time: " + stopWatch.getTotalTimeMillis() + "ms");
        log.info("----------------");
    }



}
