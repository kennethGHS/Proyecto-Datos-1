package listas;

public class Nodos<T> {
public T objeto;
public Nodos<T> next;
public Nodos<T> previo;
public static int cantidad_nodos;
public Nodos(T nombre){
	this.objeto=nombre;
	cantidad_nodos++;
}
public T get_objeto(){
	return this.objeto;
}
public void set_objeto(T objeto){
	this.objeto =objeto;
}
public Nodos<T> get_next(){
	return this.next;
}
public void set_next(Nodos<T> next){
	this.next= next;
}
public Nodos<T> get_previo(){
	return previo;
}
public void set_previo(Nodos<T> previo){
	this.previo=previo;
}

}
