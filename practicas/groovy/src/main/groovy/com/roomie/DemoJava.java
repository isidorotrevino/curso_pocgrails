package com.roomie;

public class DemoJava implements Comparable<DemoJava> {

	private String nombre;
	private int edad;
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public static void main(String[] args){
		DemoJava demoJava = new DemoJava();
		demoJava.setNombre("Juan Perez");
		demoJava.setEdad(25);
		
		System.out.println(demoJava.getNombre()+" tiene "+demoJava.getEdad()+" años ");
	}
	
	public int compareTo(DemoJava o) {		
		return o.getNombre().compareTo(this.getNombre());
	}

}
