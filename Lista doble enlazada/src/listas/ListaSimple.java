package listas;

public class ListaSimple<T> {
	Nodos<T> head;
	Nodos<T> tail;
	int largo;
	String identificador;
	public ListaSimple(){
		this.head=null;
		this.tail=null;
	}
	public ListaSimple(String id){
		this.identificador= id;
		this.head=null;
		this.tail=null;	
	}
public void append(T objeto) {
	Nodos<T> nodo= new Nodos<>(objeto);
	if(this.head==null) {
		this.head= nodo;
		this.tail=this.head;
	}
	else {
		this.tail.set_next(nodo);
		this.tail= this.tail.get_next();
		
	}
}
public void eliminar(int posicion) {

	if(head == null) {
		return;
	}
	if (posicion==0 && head!=null) {
		System.out.println(this.head.get_objeto());
		this.head=this.head.get_next();
		this.largo--;
		return;
	}
	else {
		Nodos<T> anterior = null;
		Nodos<T> actual = this.head;
		int indice = 0 ;
		while(indice!=posicion) {
			anterior = actual;
			actual = actual.get_next();
			indice++;
			if (actual==null) {
				return;
			}
		}
		if (actual == this.tail) {
			anterior.set_next(null);
			this.tail=anterior;
			this.largo--;
			return;
			
		}
		if(actual!=this.tail) {
			anterior.set_next(actual.get_next());
			this.largo--;
			return;
		}
	}
}
public void print() {
	Nodos<T> actual = this.head;
	while(actual != null) {
		System.out.println(actual.get_objeto());
		actual=actual.get_next();
	}
}
}
