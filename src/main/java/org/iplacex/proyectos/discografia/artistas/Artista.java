package org.iplacex.proyectos.discografia.artistas;

import java.util.List;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.annotation.Id;



@Document(collection = "artistas")
public class Artista {
    @Id 
    public String _id; //id string

    @Field("nombre")
    public String nombre; //nombre en string
    @Field("estilos")
    public List<String> estilos;  // estilos musicales, es una lista almacena varios  en string
   
    @Field("anioFundacion")
    public int anioFundacion; // año de fundacion  en entero
    @Field("estaActivo")
    public boolean estaActivo; // estaactivo booleano si o no
}