package se.yrgo.spring.advice;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public class PerformanceTimingAdvice {

    public Object  performTimingMeasurement (ProceedingJoinPoint method) throws Throwable{
        //before
        long startTime = System.nanoTime();

        try {
            //proceed to target
            return method.proceed();
        } finally {
            //after
            long endTime = System.nanoTime();
            long timeTaken = endTime - startTime;
            System.out.println("The method " + method.getSignature().getName() + " took " + timeTaken/1000000);
        }
    }
    //before advice
    public void beforeAdviceTesting(JoinPoint jp) {
        System.out.println("Now entering method " +
                jp.getSignature().getName());
    }
}
