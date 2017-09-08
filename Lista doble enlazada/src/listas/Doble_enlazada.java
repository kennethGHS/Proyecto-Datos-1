package listas;

public class Doble_enlazada<T> {
	Nodos<T> head;
	Nodos<T> tail;
	int largo;
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
		if (indice>largo) {
			System.out.println("indice muy grande");
			return null;
		}
		if(this.head==null) {
			return null;
		}
		int x = 1;
		Nodos<T> nodo= this.head;
		while(x!= largo) {
			x++;
			nodo=nodo.get_next();
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

}
