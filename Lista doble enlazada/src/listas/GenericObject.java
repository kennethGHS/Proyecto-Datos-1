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
private SimpleStringProperty atributo11;
private SimpleStringProperty atributo12;
private SimpleStringProperty atributo13;
private SimpleStringProperty atributo14;
private SimpleStringProperty atributo15;
private SimpleStringProperty atributo16;
private SimpleStringProperty atributo17;
private SimpleStringProperty atributo18;
private SimpleStringProperty atributo19;
private SimpleStringProperty atributo20;
private SimpleStringProperty atributo21;

private int id;
public GenericObject(int id) {
	this.id = id;
}
public SimpleStringProperty getatribut() {
	SimpleStringProperty x = this.atributo;
	return x;
}
//atributo id
public int getId() {
	return this.id;
}
//me define atributos segun el indice
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
if (indice == 11) {
	this.atributo11 = new SimpleStringProperty(nombre);
}
if (indice == 12) {
	this.atributo12 = new SimpleStringProperty(nombre);
}
if (indice == 13) {
	this.atributo13 = new SimpleStringProperty(nombre);
}
if (indice == 14) {
	this.atributo14 = new SimpleStringProperty(nombre);
}
if (indice == 15) {
	this.atributo15 = new SimpleStringProperty(nombre);
}
if (indice == 16) {
	this.atributo16 = new SimpleStringProperty(nombre);
}
if (indice == 17) {
	this.atributo17 = new SimpleStringProperty(nombre);
}
if (indice == 18) {
	this.atributo18 = new SimpleStringProperty(nombre);
}
if (indice == 19) {
	this.atributo19 = new SimpleStringProperty(nombre);
}
if (indice == 20) {
	this.atributo20 = new SimpleStringProperty(nombre);
}
if (indice == 21) {
	this.atributo21 = new SimpleStringProperty(nombre);
}
}
//esta es el metodo que me permite meter los datos en tablas
public static String get(int indice) {
	if(indice==0) {
		return "atributo";
	}
	else {
		return "atributo"+indice;
	}
	//setters y getters de los atributos
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
public SimpleStringProperty getAtributo21() {
	return atributo21;
}
public void setAtributo21(SimpleStringProperty atributo21) {
	this.atributo21 = atributo21;
}
public SimpleStringProperty getAtributo20() {
	return atributo20;
}
public void setAtributo20(SimpleStringProperty atributo20) {
	this.atributo20 = atributo20;
}
public SimpleStringProperty getAtributo19() {
	return atributo19;
}
public void setAtributo19(SimpleStringProperty atributo19) {
	this.atributo19 = atributo19;
}
public SimpleStringProperty getAtributo18() {
	return atributo18;
}
public void setAtributo18(SimpleStringProperty atributo18) {
	this.atributo18 = atributo18;
}
public SimpleStringProperty getAtributo17() {
	return atributo17;
}
public void setAtributo17(SimpleStringProperty atributo17) {
	this.atributo17 = atributo17;
}
public SimpleStringProperty getAtributo16() {
	return atributo16;
}
public void setAtributo16(SimpleStringProperty atributo16) {
	this.atributo16 = atributo16;
}
public SimpleStringProperty getAtributo15() {
	return atributo15;
}
public void setAtributo15(SimpleStringProperty atributo15) {
	this.atributo15 = atributo15;
}
public SimpleStringProperty getAtributo14() {
	return atributo14;
}
public void setAtributo14(SimpleStringProperty atributo14) {
	this.atributo14 = atributo14;
}
public SimpleStringProperty getAtributo13() {
	return atributo13;
}
public void setAtributo13(SimpleStringProperty atributo13) {
	this.atributo13 = atributo13;
}
public SimpleStringProperty getAtributo12() {
	return atributo12;
}
public void setAtributo12(SimpleStringProperty atributo12) {
	this.atributo12 = atributo12;
}
public SimpleStringProperty getAtributo11() {
	return atributo11;
}
public void setAtributo11(SimpleStringProperty atributo11) {
	this.atributo11 = atributo11;
}
}
