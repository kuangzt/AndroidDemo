package com.davis.aspect;

import android.animation.ObjectAnimator;
import android.util.Log;
import android.view.View;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

@Aspect
public class SingleClickAspect {

    private static final String POINTCUT_METHOD =
            "execution(@com.davis.aspect.annotation.SingleClick * *(..))";

    private static final String POINTCUT_CONSTRUCTOR =
            "execution(@com.davis.aspect.annotation.SingleClick *.new(..))";

    @Pointcut(POINTCUT_METHOD)
    public void methodAnnotatedWithDebugTrace() {}

    @Pointcut(POINTCUT_CONSTRUCTOR)
    public void constructorAnnotatedDebugTrace() {}

    @Around("methodAnnotatedWithDebugTrace() || constructorAnnotatedDebugTrace()")
    public void weaveJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();

        View view = null;
        Object[] args = joinPoint.getArgs();
        if(args!=null){
            for(Object obj:args){
                if(obj instanceof View){
                    view = (View)obj;
                    break;
                }
            }
        }
        if(view==null){
            return;
        }
        long lastTime = 0;
        Object object = (Object) view.getTag(R.id.click_throttle);
        if(object instanceof Long){
            lastTime = (Long)object;
        }
        long currentTime = System.currentTimeMillis();
        if(currentTime-lastTime>1000){
            joinPoint.proceed();
            view.setTag(R.id.click_throttle,currentTime);
        }else{
            if(lastTime==0){
                view.setTag(R.id.click_throttle,currentTime);
                joinPoint.proceed();
            }
        }

        return;
    }
}
