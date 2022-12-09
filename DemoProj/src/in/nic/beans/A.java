package in.nic.beans;

public class A {
 private String a;
 private int b;

 public A(String a, int b) {
	super();
	this.a = a;
	this.b = b;
}

public String getA() {
	return a;
}

public void setA(String a) {
	this.a = a;
}

public int getB() {
	return b;
}

public void setB(int b) {
	this.b = b;
}

@Override
public String toString() {
	return "A [a=" + a + ", b=" + b + "]";
}
 
 
 
 
}
