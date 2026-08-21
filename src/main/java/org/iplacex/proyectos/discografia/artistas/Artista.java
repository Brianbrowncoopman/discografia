package org.iplacex.proyectos.discografia.artistas;

import java.util.List;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;



@Document(collection = "artistas")
public class Artista {
    @Id 
    public String _id; //id string

    public String nombre; //nombre en string
    public List<String> estilos;  // estilos musicales, es una lista almacena varios  en string
   
    public int anioFundacion; // año de fundacion  en entero
    public boolean estaActivo; // estaactivo booleano si o no
}