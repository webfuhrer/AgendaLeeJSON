/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leejson;

import java.util.ArrayList;

/**
 *
 * @author luis
 */
public class CrearHTML {
    public static String crearTabla(ArrayList<Contacto> lista)
    {
        String aux="<table><tr><th>Nombre</th><th>Teléfono</th></tr>";
        for(int i=0; i<lista.size(); i++)
        {
            Contacto c=lista.get(i);
            aux+="<tr><td>"+c.getNombre()+"</td><td>"+c.getTelefono()+"</td></tr>";
        }
        aux+="<table>";
        return aux;
    }
}
