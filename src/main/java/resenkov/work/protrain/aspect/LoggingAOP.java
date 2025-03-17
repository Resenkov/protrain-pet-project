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
    // Аспект будет выполняться для всех методов из пакета контроллеров
    @Around("execution(* resenkov.work.protrain.controller..*(..))")
    public Object profileControllerMethods(ProceedingJoinPoint pjp) throws Throwable {
        // Получаем сигнатуру метода, который был вызван
        MethodSignature signature = (MethodSignature) pjp.getSignature();

        // Информация о том, какой класс и метод выполняется
        String className = signature.getDeclaringType().getSimpleName(); // Имя класса
        String methodName = signature.getName(); // Имя метода

        // Логируем информацию о выполнении метода
        log.info("--------- Выполнение " + className + "." + methodName + "  --------     ");

        final StopWatch stopWatch = new StopWatch();

        stopWatch.start();

        // Вызываем метод, обернутый в аспект
        Object result = pjp.proceed(); // Выполнение метода и получение результата

        stopWatch.stop();

        log.info("------------- Время выполнения " + className + "." + methodName + " составляет " + stopWatch.getTotalTimeMillis() + " мс  ------------");
        return result; // Возвращаем результат выполнения метода
    }
}
