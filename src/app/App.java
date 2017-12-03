package app;

import Node.node;
import alum.Alumno;
import btree.btree;

public class App {

	public static void main(String[] args) {
		btree<Integer> numeritos = new btree<Integer>();
		btree<String> nombres = new btree<String>();
		btree<Double> dob = new btree<Double>();
		btree<Alumno> prom= new btree<Alumno>();
		
		
		numeritos.add(5);
		numeritos.add(-1);
		numeritos.add(0);
		numeritos.add(24);
		numeritos.add(-6);
		numeritos.add(5);
		numeritos.add(9);
		numeritos.add(30);
		numeritos.add(35);
		numeritos.add(27);
		/*int i=0, value = (int)Math.random()*100;
		while(i<1000){
			numeritos.add((int)Math.random()*100);
			i++;
		}*/
		System.out.println("-------------printInorder--------------");
		numeritos.printInorder();
		System.out.println("-------------printPostorder--------------");
		numeritos.printPostorder();
		System.out.println("-------------printPreorder--------------");
		numeritos.printPreorder();
		
		System.out.println("-------------Is Empty--------------");
		boolean resp=numeritos.isEmpty();
		if(resp)
			System.out.println("Lista vacia");
		else
			System.out.println("Lista llena");
		
		System.out.println("-------------Exists----------------");
		numeritos.exists(-1);
		
		System.out.println("-------------- Max Depth------------");
		System.out.println(numeritos.maxDepth());
		
		System.out.println("-----------breadthSearch------------");
		numeritos.breadthSearch();
		System.out.println("                                  ");

		/*System.out.println("------------InOrder Cadenas---------");
		nombres.add("Fernando");
		nombres.add("Rotzana");
		nombres.add("Jalil");
		nombres.add("Milton");
		nombres.add("Chardo");
		
		nombres.printInorder();
		nombres.printPostorder();
		nombres.printPreorder();
		
		System.out.println("-----------InOrder Dobles-----------");
		dob.add(6.3);
		dob.add(3.2);
		dob.add(0.2);
		dob.add(4.8);
		dob.add(1.5);
		
		dob.printInorder();
		dob.printPostorder();
		dob.printPreorder();*/
		
		System.out.println("-----------DeepSearch--------------");
		System.out.println(numeritos.deepSearch(24).getValue());
		/*System.out.println(nombres.deepSearch("Milton").getValue());
		System.out.println(dob.deepSearch(4.8).getValue());
		System.out.println("----------isChild------------------");
		//node<Integer> tmp = numeritos.isChild(5);*/
		/*if(tmp!=null)*/
		System.out.println("------------isChild----------------");
		System.out.println("El padre del 9 es "+numeritos.isChild(9).getValue());
		
		System.out.println("-------------Remove----------------");
		while(!numeritos.remove(0));
		numeritos.printInorder();
		
		System.out.println("----------Cantidad-----------------");
		System.out.println("La cantidad de nodos es: "+numeritos.cantidad());
		//numeritos.printInorder();
		
		System.out.println("-------------Clear-----------------");
		numeritos.Clear();
		
		
		
	}

}
