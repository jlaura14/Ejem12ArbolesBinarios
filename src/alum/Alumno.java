package alum;

import java.util.Comparator;

public class Alumno implements Comparable<Alumno>,Comparator<Alumno>{

	String nombre = null;
	double promedio=0.0;
	
	
	
	
	public Alumno(String nombre, double promedio) {
		super();
		this.nombre = nombre;
		this.promedio = promedio;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public double getPromedio() {
		return promedio;
	}
	public void setPromedio(double promedio) {
		this.promedio = promedio;
	}
	
	@Override
	public int compareTo(Alumno o) {
		if(this.promedio == o.promedio) return 0;
		else if(this.promedio > o.promedio) return 1;
		else
			return -1;
	}
	@Override
	public int compare(Alumno o1, Alumno o2) {
		
		return o1.compareTo(o2);
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ("Alumno: "+nombre+" Promedio: "+promedio);
	}
	
	
}
