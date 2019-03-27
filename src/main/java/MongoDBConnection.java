import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.mongodb.client.model.Filters.eq;

public class MongoDBConnection {

    public static void main(String[] args){

        //Oculta el error del Logger
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);


        //1. Conexion a la instancia MongoDB ejeecutandose en localhost
        MongoClient mongoClient = new MongoClient("localhost");

        //acceso a base de datos llamada "SesameStreet"
        MongoDatabase database = mongoClient.getDatabase("SesameStreet");

        //Acceso a la coleccion llamada "Populations"
        MongoCollection collection = database.getCollection("Populations");


        //2. Crear documentos
        Document document = new Document().append("Nombre","Elmo").append("Edad","-").append("Color","Rojo");
        Document document1 = new Document().append("Nombre","Caponata").append("Edad","-").append("Color","Azul");

        //3.Insertar los documentos
        collection.insertOne(document);
        collection.insertOne(document1);

        //4. Modificar un documento
        collection.updateOne(eq("Nombre","Caponata"), new Document("$set", new Document("Nombre","Coco")));

        //5.Eliminar un documento
        collection.deleteOne(eq("Nombre","Coco"));

        //6. Mostrar los documentos
        List results = (List) collection.find().into(new ArrayList());
        //System.out.println(results);



    }





}

