package resenkov.work.protrain.aspect;


import lombok.extern.java.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Log
@Component
public class LoggingAOP {
    @Around("execution(* resenkov.work.protrain.controller..*(..))")
    public Object profileControllerMethods(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature signature = (MethodSignature) pjp.getSignature();

        String className = signature.getDeclaringType().getSimpleName();
        String methodName = signature.getName();
        log.info("--------- Выполнение " + className + "." + methodName + "  --------     ");

        final StopWatch stopWatch = new StopWatch();

        stopWatch.start();

        Object result = pjp.proceed();

        stopWatch.stop();

        log.info("------------- Время выполнения " + className + "." + methodName + " составляет " + stopWatch.getTotalTimeMillis() + " мс  ------------");
        return result;
    }
}
