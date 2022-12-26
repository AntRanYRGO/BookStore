package se.yrgo.spring.advice;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class SimpleLoggingAdvice implements AfterReturningAdvice {

    @Override
    public void afterReturning(Object returnValue, Method method,
                               Object[] arguments, Object target) throws Throwable {
        System.out.println("Now finished calling the " + method.getName()
                + " method");
        System.out.println("The target method returned the value " +
                returnValue);
    }
}
