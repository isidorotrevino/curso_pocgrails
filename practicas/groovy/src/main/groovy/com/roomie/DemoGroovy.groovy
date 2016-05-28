package com.roomie

public class DemoGroovy implements Comparable<DemoJava> {

	String nombre
	int edad
	
	static void main(String[] args){
		DemoJava demoJava = new DemoJava(nombre:"Juan Perez");
		demoJava.edad = 25
		
		println "${demoJava.nombre} tiene ${demoJava.edad} años"
		
	}
	
	public int compareTo(DemoJava o) {
		return o.nombre <=> this.edad
	}

}
