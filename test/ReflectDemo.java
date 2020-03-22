package test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ReflectDemo {
	/**
	 * Ϊ�˿����Java���䲿�ִ��룬�����쳣�Ҷ�����׳��������������
	 * 
	 * @param args
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 */
	public static void main(String[] args) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException,
			SecurityException, NoSuchFieldException, NoSuchMethodException {
		// TODO Auto-generated method stub

		// Demo1. ͨ��Java������Ƶõ���İ���������
		Demo1();
		System.out.println("===============================================");

		// Demo2. ��֤���е��඼��Class���ʵ������
		Demo2();
		System.out.println("===============================================");

		// Demo3. ͨ��Java������ƣ���Class ���������[��Ҳ���Ƿ�����ڵ���������]���޲ι���
		Demo3();
		System.out.println("===============================================");

		// Demo4: ͨ��Java������Ƶõ�һ����Ĺ��캯������ʵ�ֹ������ʵ������
		Demo4();
		System.out.println("===============================================");

		// Demo5: ͨ��Java������Ʋ�����Ա����, set �� get
		Demo5();
		System.out.println("===============================================");

		// Demo6: ͨ��Java������Ƶõ����һЩ���ԣ� �̳еĽӿڣ����࣬������Ϣ����Ա��Ϣ�����͵�
		Demo6();
		System.out.println("===============================================");

		// Demo7: ͨ��Java������Ƶ������з���
		Demo7();
		System.out.println("===============================================");

		// Demo8: ͨ��Java������ƻ���������
		Demo8();
		System.out.println("===============================================");

	}

	/**
	 * Demo1: ͨ��Java������Ƶõ���İ���������
	 */
	public static void Demo1() {
		Person person = new Person();
		System.out.println("Demo1: ����: "
				+ person.getClass().getPackage().getName() + "��" + "��������: "
				+ person.getClass().getName());
	}

	/**
	 * Demo2: ��֤���е��඼��Class���ʵ������
	 * 
	 * @throws ClassNotFoundException
	 */
	public static void Demo2() throws ClassNotFoundException {
		// �����������Ͷ�δ֪��Class , ���ó�ֵΪnull, ������θ����Ǹ�ֵ��Person��
		Class<?> class1 = null;
		Class<?> class2 = null;

		// д��1, �����׳� ClassNotFoundException [�������д��]
		class1 = Class.forName("test.Person");
		System.out.println("Demo2:(д��1) ����: " + class1.getPackage().getName()
				+ "��" + "��������: " + class1.getName());

		// д��2
		class2 = Person.class;
		System.out.println("Demo2:(д��2) ����: " + class2.getPackage().getName()
				+ "��" + "��������: " + class2.getName());
	}

	/**
	 * Demo3: ͨ��Java������ƣ���Class ���������[��Ҳ���Ƿ�����ڵ���������]
	 * 
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public static void Demo3() throws ClassNotFoundException,
			InstantiationException, IllegalAccessException {
		Class<?> class1 = null;
		class1 = Class.forName("test.Person");
		// �������ﲻ�ܴ�������������Ҫʵ�����������Person��һ��Ҫ���޲ι��캯������
		Person person = (Person) class1.newInstance();
		person.setAge(20);
		person.setName("LeeFeng");
		System.out.println("Demo3: " + person.getName() + " : "
				+ person.getAge());
	}

	/**
	 * Demo4: ͨ��Java������Ƶõ�һ����Ĺ��캯������ʵ�ִ�������ʵ������
	 * 
	 * @throws ClassNotFoundException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws IllegalArgumentException
	 */
	public static void Demo4() throws ClassNotFoundException,
			IllegalArgumentException, InstantiationException,
			IllegalAccessException, InvocationTargetException {
		Class<?> class1 = null;
		Person person1 = null;
		Person person2 = null;

		class1 = Class.forName("test.Person");
		// �õ�һϵ�й��캯������
		Constructor<?>[] constructors = class1.getConstructors();

		person1 = (Person) constructors[0].newInstance();
		person1.setAge(30);
		person1.setName("leeFeng");

		person2 = (Person) constructors[1].newInstance(20, "leeFeng");

		System.out.println("Demo4: " + person1.getName() + " : "
				+ person1.getAge() + "  ,   " + person2.getName() + " : "
				+ person2.getAge());

	}

	/**
	 * Demo5: ͨ��Java������Ʋ�����Ա����, set �� get
	 * 
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws InstantiationException
	 * @throws ClassNotFoundException
	 */
	public static void Demo5() throws IllegalArgumentException,
			IllegalAccessException, SecurityException, NoSuchFieldException,
			InstantiationException, ClassNotFoundException {
		Class<?> class1 = null;
		class1 = Class.forName("test.Person");
		Object obj = class1.newInstance();

		Field personNameField = class1.getDeclaredField("name");
		personNameField.setAccessible(true);
		personNameField.set(obj, "�ֻ���ɭ");

		System.out.println("Demo5: �޸�����֮��õ����Ա�����ֵ��" + personNameField.get(obj));

	}

	/**
	 * Demo6: ͨ��Java������Ƶõ����һЩ���ԣ� �̳еĽӿڣ����࣬������Ϣ����Ա��Ϣ�����͵�
	 * 
	 * @throws ClassNotFoundException
	 */
	public static void Demo6() throws ClassNotFoundException {
		Class<?> class1 = null;
		class1 = Class.forName("test.SuperMan");

		// ȡ�ø�������
		Class<?> superClass = class1.getSuperclass();
		System.out.println("Demo6:  SuperMan��ĸ�����: " + superClass.getName());

		System.out.println("===============================================");

		Field[] fields = class1.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			System.out.println("���еĳ�Ա: " + fields[i]);
		}
		System.out.println("===============================================");

		// ȡ���෽��
		Method[] methods = class1.getDeclaredMethods();
		for (int i = 0; i < methods.length; i++) {
			System.out.println("Demo6,ȡ��SuperMan��ķ�����");
			System.out.println("��������" + methods[i].getName());
			System.out.println("�����������ͣ�" + methods[i].getReturnType());
			System.out.println("�����������η���"
					+ Modifier.toString(methods[i].getModifiers()));
			System.out.println("��������д���� " + methods[i]);
		}

		System.out.println("===============================================");

		// ȡ����ʵ�ֵĽӿ�,��Ϊ�ӿ���Ҳ����Class,���Եõ��ӿ��еķ���Ҳ��һ���ķ����õ���
		Class<?> interfaces[] = class1.getInterfaces();
		for (int i = 0; i < interfaces.length; i++) {
			System.out.println("ʵ�ֵĽӿ�����: " + interfaces[i].getName());
		}

	}

	/**
	 * Demo7: ͨ��Java������Ƶ����෽��
	 * 
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InstantiationException
	 */
	public static void Demo7() throws ClassNotFoundException,
			SecurityException, NoSuchMethodException, IllegalArgumentException,
			IllegalAccessException, InvocationTargetException,
			InstantiationException {
		Class<?> class1 = null;
		class1 = Class.forName("test.SuperMan");

		System.out.println("Demo7: \n�����޲η���fly()��");
		Method method = class1.getMethod("fly");
		method.invoke(class1.newInstance());

		System.out.println("�����вη���walk(int m)��");
		method = class1.getMethod("walk", int.class);
		method.invoke(class1.newInstance(), 100);
	}

	/**
	 * Demo8: ͨ��Java������Ƶõ����������Ϣ
	 * 
	 * ��java�������������������[����������Ͻ�ȡ] 1��Bootstrap ClassLoader �˼���������c++��д��һ�㿪���к��ټ���
	 * 2��Extension ClassLoader ����������չ��ļ��أ�һ���Ӧ����jre\lib\extĿ¼�е���
	 * 3��AppClassLoader ����classpathָ�����࣬����õļ�������ͬʱҲ��java��Ĭ�ϵļ�������
	 * 
	 * @throws ClassNotFoundException
	 */
	public static void Demo8() throws ClassNotFoundException {
		Class<?> class1 = null;
		class1 = Class.forName("test.SuperMan");
		String nameString = class1.getClassLoader().getClass().getName();

		System.out.println("Demo8: �����������: " + nameString);
	}

}

/**
 * 
 * @author xiaoyaomeng
 *
 */
class Person {
	private int age;
	private String name;

	public Person() {

	}

	public Person(int age, String name) {
		this.age = age;
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

class SuperMan extends Person implements ActionInterface {
	private boolean BlueBriefs;

	public void fly() {
		System.out.println("���˻��Ү����");
	}

	public boolean isBlueBriefs() {
		return BlueBriefs;
	}

	public void setBlueBriefs(boolean blueBriefs) {
		BlueBriefs = blueBriefs;
	}

	@Override
	public void walk(int m) {
		// TODO Auto-generated method stub
		System.out.println("���˻���Ү��������" + m + "�׾��߲����ˣ�");
	}
}

interface ActionInterface {
	public void walk(int m);
}