package btree;

import Node.node;

public class btree<T extends Comparable> implements Comparable {
	private node<T> root = null;
	private int height = 0;
	private int cant = 0;

	public btree() {
		this.root = new node<>();
	}

	public btree(T value) {
		this.root = new node<>(value);
	}

	public void add(T value) {
		if (root.getValue() == null)
			root.setValue(value);
		else
			add(value, root);
	}

	private void add(T value, node<T> root) {
		if (root.getValue().compareTo(value) >= 1) {
			if (root.getLeft() == null) {
				root.setLeft(new node<>(value));
			} else
				add(value, root.getLeft());
		} else if (root.getValue().compareTo(value) <= -1 || root.getValue().compareTo(value) == 0) {
			if (root.getRight() == null) {
				root.setRight(new node<>(value));
				return;
			} else
				add(value, root.getRight());
		}
	}

	public boolean remove(T value) {
		node<T> tmp = deepSearch(value);// si lo encontro
		/*
		 * System.out.println(tmp.getValue().toString());
		 * System.out.println(tmp.getLeft().getValue().toString());
		 * System.out.println(tmp.getRight().getValue().toString());
		 */
		// Si el que queremos borrar es la raiz
		if (isChild(value) == null) {
			node<T> miNode = minSearch(tmp.getRight());
			miNode.setLeft(tmp.getLeft());
			this.root = tmp.getRight();
			return true;
		}
		if (tmp != null)
			return remove(value, tmp, isChild(value));
		return false;
	}

	private boolean remove(T value, node<T> root, node<T> imyourfather) {
		// caso 1 no tiene hijos
		if (root.getLeft() == null && root.getRight() == null) {
			if (imyourfather.getLeft() != null && imyourfather.getLeft().equals(root))
				imyourfather.setLeft(null);
			else if (imyourfather.getRight() != null)
				imyourfather.setRight(null);
			return true;
			// caso dos tiene 1 hijo
		} else if (root.getLeft() != null && root.getRight() == null) {
			if (imyourfather.getLeft().equals(root))
				imyourfather.setLeft(root.getLeft());
			else
				imyourfather.setRight(root.getLeft());
			return true;
		} else if (root.getLeft() == null && root.getRight() != null) {
			if (imyourfather.getLeft().equals(root))
				imyourfather.setLeft(root.getRight());
			else
				imyourfather.setRight(root.getRight());
			return true;
			// caso 3 tiene dos hijos
		} else {
			if (imyourfather.getLeft().equals(root)) {
				node<T> left = minSearch(root.getRight());
				left.setLeft(root.getLeft());
				imyourfather.setLeft(root.getRight());
			} else {
				node<T> left = minSearch(root.getRight());
				left.setLeft(root.getLeft());
				imyourfather.setRight(root.getRight());
			}
			// return true;
		}
		return false;
	}

	public void printInorder() {
		printInorder(root);
	}

	private void printInorder(node<T> root) {// izquierdo, raiz, derecho
		if (root.getLeft() != null)
			printInorder(root.getLeft());
		System.out.println(root.getValue().toString());

		if (root.getRight() != null)
			printInorder(root.getRight());
	}

	public void printPreorder() {// raiz, izquierdo, derecho
		printPreorder(root);
	}

	private void printPreorder(node<T> root) {
		if (root != null)
			System.out.println(root.getValue().toString());
		if (root.getLeft() != null)
			printInorder(root.getLeft());
		if (root.getRight() != null)
			printInorder(root.getRight());
	}

	public void printPostorder() {// izquierdo, derecho, raiz
		printPostorder(root);
	}

	private void printPostorder(node<T> root) {
		if (root.getLeft() != null)
			printInorder(root.getLeft());
		if (root.getRight() != null)
			printInorder(root.getRight());
		System.out.println(root.getValue().toString());
	}

	public node<T> isChild(T value) {
		if (deepSearch(value) == null)
			return null;
		return isChild(value, root, null);
	}

	private node<T> isChild(T value, node<T> root, node<T> imyourfather) {
		if (root != null) {
			if (root.getValue().equals(value))
				return imyourfather;
			if (root.getValue().compareTo(value) <= -1)
				return isChild(value, root.getRight(), root);
			else
				return isChild(value, root.getLeft(), root);
		} else
			return null;
	}

	public boolean isEmpty() {
		if (root == null) {
			return true;
		} else {
			return false;
		}
	}

	public node<T> deepSearch(T value) {
		return deepSearch(value, root);
	}

	private node<T> deepSearch(T value, node<T> root) {
		if (root != null) {
			if (root.getValue().equals(value))
				return root;
			if (root.getValue().compareTo(value) <= -1)
				return deepSearch(value, root.getRight());
			else
				return deepSearch(value, root.getLeft());
		} else
			return null;

	}

	public int nivel() {
		return nivel(root);
	}

	private int nivel(node<T> actual) {
		if (actual == null)
			return -1;
		else
			return 1 + Math.max(nivel(actual.getLeft()), nivel(actual.getRight()));

	}

	public void breadthSearch() {
		breadthSearch(root);
	}

	private void breadthSearch(node<T> nodo) {
		int max = 0;
		int nivel = nivel();

		for (; nivel >= 0; nivel--)
			max += Math.pow(2, nivel);
		max++;

		node cola[] = new node[max];

		cola[1] = nodo;
		int x = 1;

		for (int i = 2; i < max; i += 2, x++) {
			if (cola[x] == null) {
				cola[i] = null;
				cola[i + 1] = null;
			} else {
				cola[i] = cola[x].getLeft();
				cola[i + 1] = cola[x].getRight();
			}
		}
		nivel = 0;
		int cont = 0;
		int cantidad = 1;
		int ultimaPosicion = 1;

		for (int i = 1; i < max; i++) {
			if (i == Math.pow(2, nivel)) {
				System.out.print("\n Nivel " + (nivel) + ": ");
				nivel++;
			}
			if (cola[i] != null) {
				System.out.print("[" + cola[i].getValue() + "]");
				cont++;
			}
			if (ultimaPosicion == i && cantidad == Math.pow(2, --nivel)) {
				if (cantidad == 1)
					System.out.print(" Cantidad de nodos: " + cont + " (raiz)");
				else
					System.out.print(" Cantidad de nodos: " + cont);
				cont = 0;
				cantidad *= 2;
				ultimaPosicion += (int) Math.pow(2, ++nivel);
			}
		}
	}

	private node<T> minSearch(node<T> root) {
		while (root.getLeft() != null)
			root = root.getLeft();
		return root;
	}

	private node<T> maxSearch() {
		while (root.getRight() != null)
			root = root.getRight();
		return root;
	}

	public int maxDepth() {
		return maxDepth(root);
	}

	private int maxDepth(node<T> root) {
		if (root == null)
			return 0;
		else {
			int lDepth = maxDepth(root.getLeft());
			int rDepth = maxDepth(root.getRight());

			if (lDepth > rDepth)
				return (lDepth + 1);
			else
				return (rDepth + 1);
		}
	}

	public void Clear() {

		if (root != null) {
			root.setLeft(null);
			root.setRight(null);
			root.setValue(null);
			System.out.println("limpio");
		} else {
			System.out.println("lleno");
		}
	}

	public boolean exists(T value) {
		node<T> tmp = root;
		while (tmp != null) {
			if (value == tmp.getValue()) {
				System.out.println("Es la raíz");
				return true;
			} else if (tmp.getValue().compareTo(value) <= -1) {
				tmp = tmp.getRight();
				System.out.println("Está a la derecha");
			} else {
				tmp = tmp.getLeft();
				System.out.println("Esta a la izquierda");
			}
		}
		return false;
	}

	public int cantidad() {
		cant = 0;
		cantidad(root);
		return cant;
	}

	private void cantidad(node<T> tmp) {
		if (tmp != null) {
			cant++;
			cantidad(tmp.getLeft());
			cantidad(tmp.getRight());
		}
	}

	@Override
	public int compareTo(Object o) {

		return 0;
	}

}
