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
	public void agregar(int dato){
		NodoArbol nuevoNodo = new NodoArbol(dato);
		if(nodoRaiz==null) {
			nodoRaiz = nuevoNodo;
		}else {
			
			NodoArbol aux = nodoRaiz;
			NodoArbol nodoAnterior;
			
			while(aux!=null) {
				nodoAnterior = aux;
				
				if(dato>=aux.getDato()) {
					aux = aux.getNodoIzq();
					if(aux==null)
						nodoAnterior.setNodoIzq(nuevoNodo);
				}else {
					aux = aux.getNodoDer();
					if(aux==null)
						nodoAnterior.setNodoDer(nuevoNodo);
				}
			}
		}
	}
	public NodoArbol desplazar(NodoArbol nodo){
		NodoArbol anterior = nodo;
		NodoArbol ret = nodo;
		NodoArbol aux = nodo.getNodoDer();
		
		while(aux!=null){
			anterior = ret;
			ret = aux;
			aux= aux.getNodoIzq();
		}
		if(ret!=nodo.getNodoDer()){
			anterior.setNodoIzq(ret.getNodoDer());
			ret.setNodoIzq(nodo.getNodoDer());
		}
		return ret;		
	}
	public boolean eliminarDato(int dato) {
		if (nodoRaiz!=null) {
			boolean esIzq=false;
			NodoArbol nodoAnterior = nodoRaiz;
			NodoArbol aux = nodoRaiz;
			
			while(aux!=null&&aux.getDato()!=dato) {
					nodoAnterior = aux;
					
					if(aux.getDato()>=dato) {
						esIzq=true;
						aux = aux.getNodoIzq();
					}else {
						esIzq=false;
						aux = aux.getNodoDer();
					}
			}
			if (aux==null) {
				System.out.println("no se encontro el dato");
				return false;
			}else {
				System.out.println("se encontro el dato");
			}
			
			if(aux.getNodoIzq()==null && aux.getNodoDer()==null) {
				if(aux==nodoRaiz)
					nodoRaiz = null;
				else if(esIzq) {
					nodoAnterior.setNodoIzq(null);
				}else {
					nodoAnterior.setNodoDer(null);
				}
			}else if(aux.getNodoDer()==null) {
				if(aux==nodoRaiz) {
					nodoRaiz = aux.getNodoDer();
				}else if(esIzq) {
					nodoAnterior.setNodoIzq(aux.getNodoDer());
				}else {
					nodoAnterior.setNodoDer(aux.getNodoDer());
				}
			}else if(aux.getNodoIzq()==null) {
				if(aux==nodoRaiz) {
					nodoRaiz = aux.getNodoIzq();
				}else if(esIzq) {
					nodoAnterior.setNodoIzq(aux.getNodoIzq());
				}else {
					nodoAnterior.setNodoDer(aux.getNodoIzq());
				}
			}else {
				NodoArbol desp = desplazar(aux);
				if(aux==nodoRaiz) {
					nodoRaiz = desp;
				}else if(esIzq) {
					nodoAnterior.setNodoIzq(desp);
				}else
					nodoAnterior.setNodoDer(desp);
				
				desp.setNodoIzq(aux.getNodoIzq());
			}
			return true;
		}else {
			System.out.println("el arbol esta vacio");
			return false;
		}
		
		
	}
	public void recorridoPreorden(NodoArbol nodoRaiz) {
		if(nodoRaiz!=null) {
			System.out.print(nodoRaiz.getDato() + " => ");
			recorridoPreorden(nodoRaiz.getNodoIzq());
			recorridoPreorden(nodoRaiz.getNodoDer());
		}
	}
	public void recorridoEnorden(NodoArbol nodoRaiz) {
		if(nodoRaiz!=null) {
			recorridoEnorden(nodoRaiz.getNodoIzq());
			System.out.print(nodoRaiz.getDato() + " => ");
			recorridoEnorden(nodoRaiz.getNodoDer());
		}
	}
	public void recorridoPostorden(NodoArbol nodoRaiz) {
		if(nodoRaiz!=null) {
			recorridoPostorden(nodoRaiz.getNodoIzq());
			recorridoPostorden(nodoRaiz.getNodoDer());
			System.out.print(nodoRaiz.getDato() + " => ");
		}
	}
	public void mostrarDatoMayor() {
		if(nodoRaiz==null) {
			System.out.println("el arbol esta vacio");
		}else {
			NodoArbol aux = nodoRaiz;
			NodoArbol nodoAnterior;
			
			while(aux!=null) {
				nodoAnterior = aux;
				aux = aux.getNodoIzq();
				if(aux==null) {
					System.out.println("el numero mayor es: "+nodoAnterior.getDato());
				}
			}
		}
	}
	public void mostrarDatoMenor() {
		if(nodoRaiz==null) {
			System.out.println("el arbol esta vacio");
		}else {
			NodoArbol aux = nodoRaiz;
			NodoArbol nodoAnterior;
			
			while(aux!=null) {
				nodoAnterior = aux;
				aux = aux.getNodoDer();
				if(aux==null) {
					System.out.println("el numero menor es: "+nodoAnterior.getDato());
				}
			}
		}
	}
	public int buscarDato(NodoArbol nodo, int dato,int encontrado) {
		if(nodo!=null) {
			if(dato==nodo.getDato()) {
				encontrado+=1;
			}
			encontrado+=buscarDato(nodo.getNodoIzq(),dato,encontrado);
			encontrado+=buscarDato(nodo.getNodoDer(),dato,encontrado);
		}
		return encontrado;
	}
	
	
}



public class PruebaArbolBinarioBusqueda {

	public static void main(String[] args) {
		
		ArbolBinarioBusqueda abb = new ArbolBinarioBusqueda();	
		
		abb.agregar(7);
		abb.agregar(14);
		abb.agregar(3);
		abb.agregar(9);
		abb.agregar(37);
		abb.agregar(1);
		abb.eliminarDato(7);
		
		abb.recorridoPreorden(abb.nodoRaiz);
		System.out.println();
		abb.recorridoEnorden(abb.nodoRaiz);
		System.out.println();
		abb.recorridoPostorden(abb.nodoRaiz);
		System.out.println();
		abb.mostrarDatoMayor();
		abb.mostrarDatoMenor();
		System.out.println(abb.buscarDato(abb.nodoRaiz, 3, 0));
		

	}

}
