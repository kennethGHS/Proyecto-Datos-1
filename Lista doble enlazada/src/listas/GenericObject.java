package listas;

import javafx.beans.property.SimpleStringProperty;

public class GenericObject {
private SimpleStringProperty atributo;
private SimpleStringProperty atributo1;
private SimpleStringProperty atributo2;
private SimpleStringProperty atributo3;
private SimpleStringProperty atributo4;
private SimpleStringProperty atributo5;
private SimpleStringProperty atributo6;
private SimpleStringProperty atributo7;
private SimpleStringProperty atributo8;
private SimpleStringProperty atributo9;
private SimpleStringProperty atributo10;
public GenericObject() {}
public SimpleStringProperty getatribut() {
	SimpleStringProperty x = this.atributo;
	return x;
}
public void setatributo(String nombre, int indice) {
	if (indice == 0) {
		this.atributo = new SimpleStringProperty(nombre);
	}
if (indice == 1) {
	this.atributo1 = new SimpleStringProperty(nombre);
	}
if (indice == 2) {
	this.atributo2 = new SimpleStringProperty(nombre);
}
if (indice == 3) {
	this.atributo3 = new SimpleStringProperty(nombre);
}
if (indice == 4) {
	this.atributo4 = new SimpleStringProperty(nombre);
}
if (indice == 5) {
	this.atributo5 = new SimpleStringProperty(nombre);
}
if (indice == 6) {
	this.atributo6 = new SimpleStringProperty(nombre);
}
if (indice == 7) {
	this.atributo7 = new SimpleStringProperty(nombre);
}
if (indice == 8) {
	this.atributo8 = new SimpleStringProperty(nombre);
}
if (indice == 9) {
	this.atributo9 = new SimpleStringProperty(nombre);
}
if (indice == 10) {
	this.atributo10 = new SimpleStringProperty(nombre);
}
}
public static String get(int indice) {
	if(indice==0) {
		return "atributo";
	}
	else {
		return "atributo"+indice;
	}
}
public String getAtributo() {
    return atributo.get();
    }
public String getAtributo1() {
    return atributo1.get();
    }
public String getAtributo2() {
    return atributo2.get();
    }
public String getAtributo3() {
    return atributo3.get();
    }
public String getAtributo4() {
    return atributo4.get();
    }
public String getAtributo5() {
    return atributo5.get();
    }
public String getAtributo6() {
    return atributo6.get();
    }
public String getAtributo7() {
    return atributo7.get();
    }
public String getAtributo8() {
    return atributo8.get();
    }
public String getAtributo9() {
    return atributo9.get();
    }
public String getAtributo10() {
    return atributo10.get();
    }
public String setatributo() {
    return atributo.get();
    }
public String setatributo1() {
    return atributo1.get();
    }
public String setatributo2() {
    return atributo2.get();
    }
public String setatributo3() {
    return atributo3.get();
    }
public String setatributo4() {
    return atributo4.get();
    }
public String setatributo5() {
    return atributo5.get();
    }
public String setatributo6() {
    return atributo6.get();
    }
public String setatributo7() {
    return atributo7.get();
    }
public String setatributo8() {
    return atributo8.get();
    }
public String setatributo9() {
    return atributo9.get();
    }
public String setatributo10() {
    return atributo10.get();
    }
}
