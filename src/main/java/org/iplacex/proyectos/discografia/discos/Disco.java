package org.iplacex.proyectos.discografia.discos;

import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "discos")
public class Disco {

    @Id 
    public String _id;  //string id

    public String idArtista; //id artista ( de la clase artista)
    public String nombre; //string nombre de la clase artista)
    public int anioLanzamiento; // string año
    public List<String> canciones; // lista que almacena las canciones String
}