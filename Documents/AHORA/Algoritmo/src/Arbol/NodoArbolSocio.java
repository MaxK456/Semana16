/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Arbol;

import Modelo.Socio;

public class NodoArbolSocio {

    Socio socio;
    NodoArbolSocio izquierda;
    NodoArbolSocio derecha;

    public NodoArbolSocio(Socio socio) {
        this.socio = socio;
        this.izquierda = null;
        this.derecha = null;
    }
}
