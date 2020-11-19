import java.util.Scanner;

interface Validacion{
	Scanner input = new Scanner(System.in);
	
	public static int validacionNatural() {
		int ret = 0;
		boolean err = false;
		do {
			try {
				ret = input.nextInt();
			} catch (java.util.InputMismatchException e) {
				System.out.println("entrada no valida, intente de nuevo:");
				input.nextLine();
				err=true;
			}
			if (ret>0) {
				err=false;
			}else {
				System.out.println("solo números naturales");
				err=true;
			}
		}while(err);
		return ret;
	}
}


class NodoArbol{
	private NodoArbol nodoIzq;
	private int dato;
	private NodoArbol nodoDer;
	
	public NodoArbol(int dato) {
		this.dato = dato;
	}
	public NodoArbol(){
	}
	
	public NodoArbol getNodoIzq() {
		return nodoIzq;
	}
	public void setNodoIzq(NodoArbol nodoIzq) {
		this.nodoIzq = nodoIzq;
	}
	public int getDato() {
		return dato;
	}
	public void setDato(int dato) {
		this.dato = dato;
	}
	public NodoArbol getNodoDer() {
		return nodoDer;
	}
	public void setNodoDer(NodoArbol nodoDer) {
		this.nodoDer = nodoDer;
	}
	
	@Override
	public String toString() {
		return "NodoArbol [nodoIzq=" + nodoIzq + ", dato=" + dato + ", nodoDer=" + nodoDer + "]";
	}
	
}

class ArbolBinarioBusqueda{
	NodoArbol nodoRaiz;
	
	public ArbolBinarioBusqueda(){
		nodoRaiz=null;
	}
	public void agergar(int dato) {
		NodoArbol nuevoNodo = new NodoArbol(dato);
		if(nodoRaiz==null) {
			nodoRaiz = nuevoNodo;
		}else {
			
			NodoArbol aux = nodoRaiz;
			NodoArbol nodoAnterior;
			
			while(aux!=null) {
				nodoAnterior = aux;
				
				if(dato>=aux.getDato()) {  //derecha
					aux = aux.getNodoDer();
					if(aux==null)
						nodoAnterior.setNodoIzq(nuevoNodo);
				}else { //izquierda
					aux = aux.getNodoIzq();
					if(aux==null)
						nodoAnterior.setNodoDer(nuevoNodo);
				}
			}
		}
	}
	
	
}



public class PruebaArbolBinarioBusqueda {

	public static void main(String[] args) {
		
		

	}

}
