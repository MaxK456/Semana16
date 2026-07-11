/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Arbol;
import Modelo.Socio;

public class ArbolSocio {

    private NodoArbolSocio raiz;

    public ArbolSocio() {
        raiz = null;
    }

    public void insertar(Socio socio) {
        raiz = insertarRec(raiz, socio);
    }

    private NodoArbolSocio insertarRec(NodoArbolSocio nodo, Socio socio) {

        if (nodo == null) {
            return new NodoArbolSocio(socio);
        }

        if (socio.getId() < nodo.socio.getId()) {
            nodo.izquierda = insertarRec(nodo.izquierda, socio);
        } else if (socio.getId() > nodo.socio.getId()) {
            nodo.derecha = insertarRec(nodo.derecha, socio);
        }

        return nodo;
    }

    public Socio buscar(int id) {
        return buscarRec(raiz, id);
    }

    private Socio buscarRec(NodoArbolSocio nodo, int id) {

        if (nodo == null) {
            return null;
        }

        if (id == nodo.socio.getId()) {
            return nodo.socio;
        }

        if (id < nodo.socio.getId()) {
            return buscarRec(nodo.izquierda, id);
        }

        return buscarRec(nodo.derecha, id);
    }

    public void inOrden() {
        inOrdenRec(raiz);
    }

    private void inOrdenRec(NodoArbolSocio nodo) {

        if (nodo != null) {

            inOrdenRec(nodo.izquierda);

            System.out.println(nodo.socio);

            inOrdenRec(nodo.derecha);
        }
    }
}
