package org.iplacex.proyectos.discografia.discos;

import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "discos")
public class Disco {

    @Id 
    public String _id;  //string id

    @Field("idArtista")
    public String idArtista; //id artista ( de la clase artista)
    @Field("nombre")
    public String nombre; //string nombre de la clase artista)
    @Field("anioLanzamiento")
    public int anioLanzamiento; // string año
    @Field("canciones")
    public List<String> canciones; // lista que almacena las canciones String
}