package com.channelapi.web.aop;

import com.channelapi.web.annotation.DynamicDataSourceAnnotation;
import com.channelapi.web.constant.Constant;
import com.channelapi.web.util.DataSourceContext;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
@Order(1)
public class DynamicDataSourceAspect {

    private static Logger logger = Logger.getLogger(DynamicDataSourceAspect.class);

    //前置通知
    @Before("@annotation(com.channelapi.web.annotation.DynamicDataSourceAnnotation)")
    public void beforeHandle(JoinPoint point){
        //获得当前访问的class
        Class<?> className = point.getTarget().getClass();
        DynamicDataSourceAnnotation dataSourceAnnotation = className.getAnnotation(DynamicDataSourceAnnotation.class);
        if (dataSourceAnnotation != null ) {
            //获得访问的方法名
            String methodName = point.getSignature().getName();
            //得到方法的参数的类型
            Class[] argClass = ((MethodSignature)point.getSignature()).getParameterTypes();
            String dataSource = Constant.DATA_SOURCE_LOCAL;
            try {
                Method method = className.getMethod(methodName, argClass);
                if (method.isAnnotationPresent(DynamicDataSourceAnnotation.class)) {
                    DynamicDataSourceAnnotation annotation = method.getAnnotation(DynamicDataSourceAnnotation.class);
                    dataSource = annotation.dataSource();
                    System.out.println("DataSource Aop ====> "+dataSource);
                }
            } catch (Exception e) {
                logger.error("DataSource Aop Before Error", e);
            }
            DataSourceContext.setDataSourceType(dataSource);
        }

    }

    //后置通知
    @After("@annotation(com.channelapi.web.annotation.DynamicDataSourceAnnotation)")
    public void afterHandle(JoinPoint point){
        //获得当前访问的class
        Class<?> className = point.getTarget().getClass();
        DynamicDataSourceAnnotation dataSourceAnnotation = className.getAnnotation(DynamicDataSourceAnnotation.class);
        if (dataSourceAnnotation != null ) {
            //获得访问的方法名
            String methodName = point.getSignature().getName();
            //得到方法的参数的类型
            Class[] argClass = ((MethodSignature)point.getSignature()).getParameterTypes();
            String dataSource = Constant.DATA_SOURCE_LOCAL;
            try {
                Method method = className.getMethod(methodName, argClass);
                if (method.isAnnotationPresent(DynamicDataSourceAnnotation.class)) {
                    DynamicDataSourceAnnotation annotation = method.getAnnotation(DynamicDataSourceAnnotation.class);
                    dataSource = annotation.dataSource();
                }
            } catch (Exception e) {
                logger.error("DataSource Aop After Error", e);
            }
            if(!Constant.DATA_SOURCE_LOCAL.equals(dataSource)){
                DataSourceContext.clearDataSourceType();
            }
        }
    }
}
