package com.care.root.config;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Component
@Aspect
@EnableAspectJAutoProxy
public class AOPConfig {
	//around 안에 있는 메소드가 실행되기 전에 commonAop가 실행
	//jp.proceed()를 만나면 일시중지 execution 메소드 실행
	// execution 메소드 끝나면 다시 commonAop 실행
	@Around("execution(* com.care.root.board.service.BoardServiceImpl.getBoardList(..))")
	public void commonAop(ProceedingJoinPoint jp) {
		System.out.println("메소드 실행 전...");
		try {
			jp.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		System.out.println("메소드 실행 후...");
	}
	
	//인터셉터 이후에 aop 실행
	@Before("execution(* com.care.root.board.controller.BoardController.writeForm(..))")
	public void test() {
		System.out.println("aaaaaaa");
	}
	
	Logger LOG = Logger.getGlobal();
	@Around("execution(* com.care.root.board.controller.*.*(..))")
	public Object aop02(ProceedingJoinPoint jp) {
		Object[] params = jp.getArgs();
		for(Object o : params) {
			LOG.log(Level.INFO, "들어온 파라미터 값: " + o);
		}
		Object obj = null;
		try {
			obj = jp.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		LOG.log(Level.INFO, "실행된 메소드: " + obj);
		return obj;
	}
}
