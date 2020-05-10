package test;

public class TestPrivateConstructor {

	class Parent {
		private Parent() {
			throw new AssertionError();
		}
	}

	class Child extends Parent {

	}

	public static void main(String[] args) {
		TestPrivateConstructor t = new TestPrivateConstructor();
		t.new Child();
	}
}