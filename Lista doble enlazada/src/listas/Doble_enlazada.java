package listas;
/**
 * Lista DOblemente emlazada
 * @author kenneth
 *
 * @param <T>
 */
public class Doble_enlazada<T> {
	Nodos<T> head;
	Nodos<T> tail;
	int largo;
	String id;
	public Doble_enlazada(){
		this.head=null;
		this.tail=null;
	}
	public void append(T objeto){
		Nodos<T> app= new Nodos<T>(objeto);
		if(this.head== null){
			this.head=app;
			this.tail=this.head;
			largo++;
		}
		else{
			Nodos<T> llevar=this.tail;
			 llevar.set_next(app);
			llevar.next.set_previo(llevar);
			this.tail=llevar.next;
			largo++;
			
		}
	}
	public Nodos<T> gethead(){
		return this.head;
	}
	public  Nodos<T> buscar(int indice) {
		Nodos<T> nodo =  this.head;
		while(indice!=0) {
			nodo=nodo.get_next();
			indice--;
		}
		return nodo;
		
	}
	public void editar(int indice, T dato) {
		if (indice>largo) {
			System.out.println("indice muy grande");
		}
		int x = 1;
		Nodos<T> nodo= this.head;
		while(x!= indice) {
			x++;
			nodo=nodo.get_next();
		}
		if (nodo!=null) {
		nodo.set_objeto(dato);
		}
	}
public void eliminar(int indice) {
	Nodos<T> nodo = this.head;
	while(indice!=0) {
		nodo = nodo.next;
		indice--;
	}

	if(nodo==this.head) {
		if(nodo.previo==null && nodo.next == null) {
			this.tail = null;
			this.head=null;
			return;
		}
		this.head = nodo.next;
		this.head.set_previo(null);
		return;
	}
	if(nodo==this.tail) {
		this.tail= nodo.previo;
		this.tail.set_next(null);
		return;
	}
	else{
		nodo.previo.set_next(nodo.next);
		nodo.next.set_previo(nodo.previo);
		return;
	}
}
}
