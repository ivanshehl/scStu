package com.ivan.tpp.stu.mat;

import java.util.HashSet;
import java.util.Set;

import com.alibaba.fastjson.JSON;

public class TestMap {
	
	public static void main(String[] args) {
		Set<Person> persons = new HashSet<>();
	    Person p1 = new Person("AAAA", 25);
	    Person p2 = new Person("BBBB", 27);
	    Person p3 = new Person("CCCC", 30);
	    persons.add(p1);
	    persons.add(p2);
	    persons.add(p3);
	    System.out.println("size:" + persons.size()); // 3

	    p3.setAge(31); // 修改属性。
//	    persons.remove(p3); // 移除不掉.
//	    persons.add(p3); // 添加成功.

	    System.out.println("size:" + persons.size()); // 4
	    for(Person p : persons){
	    	System.out.println(JSON.toJSON(p));
	    }
	}

}
class Person{
	
	public Person(String name,int age){
		this.name = name;
		this.age = age;
	}
	
	private String name;
	private int age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
}
