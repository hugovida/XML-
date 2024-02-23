/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;



/**
 *
 * @author Usuario
 */
public class AD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        // TODO code application logic here
        Scanner e=new Scanner(System.in);
        int opciones=0;
        String texto="";
        //Introducimos por teclado la opcion deseada
        //Se crea un bucle para que se repita hasta que se quiera salir del programa
        do{
            System.out.println("SELECCIONE UNA OPCIÓN:\n1. PROCESAR EL DOCUMENTO XML CREADO \n2. GUARDAR LOS VALORES DEL DOCUMENTO XML EN OTRO FICHERO \n"
                + "3. MOSTRAR LOS VALORES DEL FICHERO DE TEXTO ANTERIOR \n4. SALIR");
        opciones=e.nextInt();
        //Contempla las distintas opciones
        switch(opciones){
            case 1: texto=procesarDocumento();
                break;
            case 2: guardarValores(texto);
                break;
            case 3: mostrarValores();
                break;
            case 4: System.out.println("SALIENDO DEL PROGRAMA");
                break;
            default: System.out.println("ERROR");
                break;
        }
        }while(opciones!=4);
    }
    public static String procesarDocumento(){
        //Creamos la variable texto, que sera la que saquemos mas adelante
        String texto="";
        try {
            //Abrimos el documento
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse("formula1.xml");
            //Creamos una cadena de texto con los datos
            //Elemento raiz:
            String texto2;
            texto="Elemento raiz:" + document.getDocumentElement().getNodeName();
            System.out.println(texto);
            //Pilotos:
            NodeList pilotos = document.getElementsByTagName("piloto");
            for (int i = 0; i < pilotos.getLength(); i++) {
                Element piloto = (Element) pilotos.item(i);
                String id = piloto.getAttribute("id");
                String nombre = piloto.getElementsByTagName("nombre").item(0).getTextContent();
                String apellido = piloto.getElementsByTagName("apellido").item(0).getTextContent();
                String edad = piloto.getElementsByTagName("edad").item(0).getTextContent();
                texto2="Piloto " + id + ": " + nombre + " " + apellido + ", " + edad + " años";
                System.out.println(texto2);
                texto=texto+"\n"+texto2;
            }
            //Escuderias:
            NodeList escuderias = document.getElementsByTagName("escuderia");
            for (int i = 0; i < escuderias.getLength(); i++) {
                Element escuderia = (Element) escuderias.item(i);
                String id = escuderia.getAttribute("id");
                String nombre = escuderia.getElementsByTagName("nombre").item(0).getTextContent();
                String trabajadores = escuderia.getElementsByTagName("trabajadores").item(0).getTextContent();
                texto2="Escudería " + id + ": " + nombre + ", " + trabajadores + " trabajadores";
                System.out.println(texto2);
                texto=texto+"\n"+texto2;
            }
        } catch (IOException | ParserConfigurationException | DOMException | SAXException e) {
            e.printStackTrace();
        }
        //Devolvemos el texto para tenerlo almacenado
        return texto;
    }
    public static void mostrarValores() throws FileNotFoundException, IOException{
    try{
        //Creamos un lector para el archivo
        BufferedReader lector = new BufferedReader(new FileReader("archivo.txt"));
        //Creamos una variable inicializada
        String linea ="";
        //Leemos el documento linea a linea
        while ((linea= lector.readLine())!=null){
            System.out.println(linea);
        }  
    }
   catch (IOException e) {
            System.out.println(e.toString());
        }
}
    
public static void guardarValores(String texto) throws ParserConfigurationException, SAXException, IOException{
    try{
            //Creamos un escritor para escribir en el archivo la información
            BufferedWriter bw = new BufferedWriter(new FileWriter("archivo.txt"));
            //Escribimos la variable texto en el archivo
            bw.write(texto);
            // Cerramos el archivo de texto
            bw.close();
            System.out.println("Se ha copiado la información del archivo XML al archivo de texto.");
    } 
        catch (Exception e) {
            System.out.println(e.toString());
        }
}
    }
    
