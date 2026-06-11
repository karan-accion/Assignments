class Pen{
	String color;
	String type;

	public void write(){
		System.out.println("writing something with my "+this.color+" "+this.type+" Pen");
	}
	public void printColor(){
		System.out.println("this.color");
	}
	public void printType(){
		System.out.println("this.type");
	}
}

class Student{
	String name;
	int age;

	public void printInfo(String name){
		System.out.println(name);
	}
	public void printInfo(int age){
		System.out.println(name);
	}
	public void printInfo(String name, int age){
		System.out.println(name+" "+age);
	}
	Student(Student s2){
		System.out.println("Constructor Called");
		this.name = s2.name;
		this.age = s2.age;
	}
	Student(){

	}
}

public class OOP{
	public static void main(String[] args) {
		Pen pen1 = new Pen();
		pen1.color = "red";
		pen1.type = "gel";

		Pen pen2 = new Pen();
		pen2.color = "blue";
		pen2.type = "ballpoint";

		pen2.printColor();
		pen1.printType();
		pen1.write();

		Student s1 = new Student();
		s1.name = "Aman";
		s1.age = 26;
		
		Student s2 = new Student(s1);
		s2.printInfo(s2.age);
		s1.printInfo(s1.name,s1.age);
	}
}
class Shape{
	public void area(){
		System.out.println("Display Area");
	}
}

class Triangle extends Shape{
	public void area(int l, int h){
		System.out.println(1/2*l*h);
	}
}

class EquilateralTriangle extends Triangle{
	public void area(int l, int h){
		System.out.println(1/2*l*h);
	}
}

class Circle extends Shape{
	public void area(int r){
		System.out.println((3.14)*r*r);
	}
}

public class OOP{
	public static void main(String[] args) {
		Triangle t1 = new Triangle();

	}
}