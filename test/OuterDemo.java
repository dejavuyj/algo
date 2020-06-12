package test;

interface Inter {
	void show();
//	default void show() {
//		System.out.println("default method!");
//	};
}

class InterImpl implements Inter {
	public void show() {
		System.out.println("Impl show!");
	}
}

class Outer {
	// 补齐代码
	public static Inter method() {
		return new Inter() {
			public void show() {
				System.out.println("Hello World!");
			}
		};
	}
}

public class OuterDemo {
	public static void main(String[] args) {
		Outer.method().show();
	}
}
