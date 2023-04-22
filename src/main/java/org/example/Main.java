package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Main {
    //Este programa recibe como parametro la ubicacion del archivo.csv y el formato en que queremos que nos los transforme "JSON" o "XML"
    //Ej: "ofertas.csv" "JSON"
    public static void main(String[] args) throws IOException, JAXBException {
        ArrayList<Producto> productos=new ArrayList<>();
        ArrayList<Descuento> descuentos=new ArrayList<>();
        ArrayList<String> fechasDesde=new ArrayList<>();
        ArrayList<String> fechasHasta=new ArrayList<>();
        ArrayList<Oferta> ofertas=new ArrayList<>();
        Path archivo = Paths.get(args[0]);
        ArrayList<String> informacion=leerArchivo(archivo);
        String[] infoAux;

        //Creamos los productos con la informacion obtenida
        for (int i=0;i<informacion.size();i++) {
            infoAux=informacion.get(i).split(";");
            productos.add(new Producto(infoAux[0]));
            productos.get(i).setPeso(Float.parseFloat(infoAux[1]),infoAux[2]);
            productos.get(i).setPrecio(Float.parseFloat(infoAux[3]));
        }

        //Creamos los descuentos con la informacion obtenida
        for (String s : informacion) {
            infoAux = s.split(";");
            descuentos.add(new Descuento(infoAux[4], Float.parseFloat(infoAux[5]), Float.parseFloat(infoAux[6])));
        }

        //Creamos las fechas con la informacion obtenida
        for (String s : informacion) {
            infoAux = s.split(";");
            fechasDesde.add(infoAux[7]);
            fechasHasta.add(infoAux[8]);
        }

        //Creamos las ofertas segun la cantidad de productos y descuentos obtenidos
        for (int i=0;i<informacion.size();i++) {
            ofertas.add(new Oferta(productos.get(i),descuentos.get(i),fechasDesde.get(i),fechasHasta.get(i)));
        }

        //Mostramos por pantalla en formato JSON o XML segun le hayamos pedido
        for(int i=0;i< productos.size();i++){
            if (args[1].startsWith("X")||args[1].startsWith("x")){
                obtenerXmlString (ofertas.get(i));
            } else obtenerJsonString(ofertas.get(i));
        }

    }
    public static ArrayList<String> leerArchivo(Path p) throws IOException {
        return new ArrayList<>(Files.readAllLines(p));
    }

    public static void obtenerJsonString (Oferta o) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.INDENT_OUTPUT,true);
        String strJson = mapper.writeValueAsString(o);
        System.out.println(strJson);
    }

    public static void obtenerXmlString (Oferta o) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Oferta.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
        StringWriter stringWriter = new StringWriter();
        marshaller.marshal(o,stringWriter);
        String strXml = stringWriter.toString();
        System.out.println(strXml);    }
}