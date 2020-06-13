package test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyDynamicProxy {
	public static void main(String[] args) {
		Hello hello = new HelloImpl();
		MyInvocationHandler handler = new MyInvocationHandler(hello);
		// 构造代码实例
		Hello proxyHello = (Hello) Proxy.newProxyInstance(
				HelloImpl.class.getClassLoader(),
				HelloImpl.class.getInterfaces(), handler);
		// 调用代理方法
		String returnStr = proxyHello.sayHello();
		System.out.println("returnStr is " + returnStr);
	}
}

interface Hello {
	String sayHello();
}

class HelloImpl implements Hello {
	@Override
	public String sayHello() {
		System.out.println("Hello World");
		return "returnString";
	}
}

class MyInvocationHandler implements InvocationHandler {
	private Object target;

	public MyInvocationHandler(Object target) {
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("Invoking sayHello");
		Object result = method.invoke(target, args);
		System.out.println("Invoking finish");
		return result;
	}
}
