package com.ivan.tpp.stu.aop.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ToolInvocationHandler implements InvocationHandler {
	
	public ToolInvocationHandler(Object target){
		this.target = target;
	}

	// 需要被代理的那个对象
    private Object target;

    public void setTarget(Object target){
        this.target = target;
    }
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		EnhanceTool enhanceTool = new EnhanceTool();
		enhanceTool.before();
		Object rlt = method.invoke(target, args);
		enhanceTool.after();
		System.out.println("++++++++++++++rlt="+rlt);
		return rlt;
	}

}
