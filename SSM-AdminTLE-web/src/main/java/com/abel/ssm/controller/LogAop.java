package com.abel.ssm.controller;

import com.abel.domain.SysLog;
import com.abel.service.ISysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * Bean 比较综合的总结了关于Spring AOP 和 DI 的知识
 */
@Component //不是Controller 只是一个Bean 注入到Spring
@Aspect //表示一个切面 需要在spring-mvc.xml 里面添加配置 <aop:aspectj-autoproxy proxy-target-class="true"/>
public class LogAop {

    @Autowired
    private ISysLogService sysLogService;

    @Autowired
    private HttpServletRequest request; // 在web.xml文件里面配置  <listener-class>org.springframework.web.context.request.RequestContextListener</listener-class>
    private Date visitTime; // 开始时间
    private Class clazz; // 访问的类
    private Method method; // 访问的方法

    /**
     * JoinPoint 对象
     * JoinPoint对象封装了SpringAop中切面方法的信息,在切面方法中添加JoinPoint参数,就可以获取到封装了该方法信息的JoinPoint对象. 
     * @param jp
     * @throws NoSuchMethodException
     */
     
    /**
     * 前置通知 主要是获取方法的开始时间 和执行的类是哪一个 执行的是哪一个方法
     * @param jp
     * @throws NoSuchMethodException
     */
    @Before("execution(* com.abel.ssm.controller.*.*(..))") // 拦截controller 下的所有类的所有方法
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        visitTime = new Date(); // 当前时间就是开始访问的时间
        clazz = jp.getTarget().getClass();  // 具体要访问的类 getTarget() 表示获取被代理的堆对象  
        String methodName = jp.getSignature().getName(); // 获取访问的方法的名称 getSignature() 获取封装了署名信息的对象,在该对象中可以获取到目标方法名,所属类的Class等信息
//        method = clazz.getMethod(methodName); //只能获取无参数的
        Object[] args = jp.getArgs();// 获取访问方法的参数
        //获取到具体执行的Method对象
        if (args == null || args.length == 0) { //说明方法没有参数
            method = clazz.getMethod(methodName);
        } else {
            Class[] classArgs = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                classArgs[i] = args[i].getClass();
            }
            clazz.getMethod(methodName, classArgs);
        }
    }

    /**
     * 后置通知 方法执行后调用的方法
     * @param jp
     * @throws Exception
     */
    @After("execution(* com.abel.ssm.controller.*.*(..))") //拦截controller 下的所有类的所有方法
    public void doAfter(JoinPoint jp) throws Exception {
        long time = new Date().getTime() - visitTime.getTime(); //获取了访问的时长
        //需要通过 ==反射== 去获取url 类上的RequestMapping 和 方法上的 RequestMapping 的组合
        //****************************************************************//
        //获取url
        String url = "";
        if (clazz != null && method != null && clazz != LogAop.class) { //clazz != LogAop.class 拦截自己没有用
            //获取类上的 url
            RequestMapping classAnnotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if (classAnnotation != null) {
                String[] classValue = classAnnotation.value();
                //获取方法上的 注解 value值
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                if (methodAnnotation != null) {
                    String[] methodValue = methodAnnotation.value();
                    url = classValue[0] + methodValue[0]; // "/orders" "/orders.do"
                    //获取ip地址 需要request -> 在web.xml里卖配置listener
                    String ip = request.getRemoteAddr();
                    //获取当前操作的用户
                    SecurityContext context = SecurityContextHolder.getContext(); // 从上下文中获取当前登陆的用户
                    User user = (User) context.getAuthentication().getPrincipal(); // security 里的user
                    String username = user.getUsername();
                    //将日志相关信息封装到Syslog里面
                    SysLog sysLog = new SysLog();
                    sysLog.setExecutionTime(time);
                    sysLog.setIp(ip);
                    sysLog.setMethod("[类名] " + clazz.getName() + "[方法名] " + method.getName());
                    sysLog.setUrl(url);
                    sysLog.setUsername(username);
                    sysLog.setVisitTime(visitTime);
                    //条用service完成数据库的日志记录操作
                    sysLogService.save(sysLog);
                }
            }
        }
    }
}
