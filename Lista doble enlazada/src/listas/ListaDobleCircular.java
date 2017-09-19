package listas;

public class ListaDobleCircular<T> {
	Nodos<T> head;
	Nodos<T> tail;
	int largo;
	public String id;
	public ListaDobleCircular(){
		this.head=null;
		this.tail=null;
	}
	public ListaDobleCircular(String id){
		this.id= id;
		this.head=null;
		this.tail=null;	
	}
	public void append(T objeto){
		Nodos<T> app= new Nodos<T>(objeto);
		if(this.head== null){
			this.head=app;
			this.tail=this.head;
			this.head.next= this.head;
			largo++;
		}
		else{
			Nodos<T> llevar=this.tail;
			 llevar.set_next(app);
			llevar.next.set_previo(llevar);
			this.tail=llevar.next;
			
			tail.set_next(this.head);
			this.head.set_previo(this.tail);
			largo++;
			
		}
	}
	public Nodos<T> gethead(){
		return this.head;
	}
	public  Nodos<T> buscar(int indice) {
		if(indice>largo) {
			return null;
		}
		Nodos<T> variable= this.head;
		while(indice!=0) {
			variable = variable.next;
			indice--;
		}
		return variable;
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
	public void print() {
		Nodos<T> cosas = this.head;
		while(true) {
		System.out.println(cosas.get_objeto());
		cosas= cosas.get_next();
		}
		
	}
	public void borrar(int indice ) {
		if(indice>= this.largo) {
			return;
		}
		Nodos<T> nodo = this.head;
		if(this.head== null) {
			return;
		}
		while(indice!=0) {
		 nodo = nodo.next;
			indice--;
		}
		if(nodo.next==nodo && nodo == this.head) {
			this.largo--;
			this.head = null;
			this.tail = null;
			return;
		}
		if( nodo == this.head && this.head.get_next()!=null) {
			this.largo--;
			nodo.get_next().set_previo(nodo.get_previo());
			nodo.get_previo().set_next(nodo.next);
			this.head= nodo.next;
			return;
		}
		if( nodo == this.tail ) {
			this.largo--;
			nodo.get_next().set_previo(nodo.get_previo());
			nodo.get_previo().set_next(nodo.next);
			this.tail= nodo.get_previo();
			return;
		
		}
		else {
			System.out.println("entre");
			this.largo--;
			nodo.get_next().set_previo(nodo.get_previo());
			nodo.get_previo().set_next(nodo.next);
			return;
		}
		}
	public static void pruebas() {
		ListaDobleCircular<String> lista = new ListaDobleCircular<>();
		lista.append("cosas");
		lista.append("cosasss");
		lista.append("Nuevo");
		lista.append("cosas");
		lista.append("231");
		lista.append("cosasmas");
		lista.borrar(5);
		lista.borrar(0);
		lista.borrar(2);
		
	
		System.out.println(lista.tail.objeto + "this is tail");
		System.out.println(lista.head.objeto+"this is head");
		//lista.print();
		

	}
}
