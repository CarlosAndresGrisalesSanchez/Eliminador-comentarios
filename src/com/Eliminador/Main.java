package com.Eliminador;

import java.io.*;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
	// write your code here
        Scanner teclado=new Scanner(System.in);
        System.out.println("Escribe el nombre del fichero tomando como raiz el directorio /Escriptori");
        File leer =new File(/*Ruta del archivo*/"/home/alumne1daw/Escriptori/"+teclado.nextLine());
        try {
            FileReader lecturas=new FileReader(leer);
            Scanner lineasLecturas=new Scanner(lecturas);
            String Escribir="";
            String ruta=leer.getAbsolutePath();
            String rutaNuevo=ruta.substring(0,ruta.indexOf("."))+"-nou"+ruta.substring(ruta.indexOf("."),ruta.length());
            File nuevo=new File(rutaNuevo);
            FileWriter escribeArchivo=new FileWriter(nuevo);

            while ((lineasLecturas.hasNextLine())){
                boolean entrar=true;
                String comprobar=lineasLecturas.nextLine();
                    //Aqui compruebo si hay alguna linea de comentario suelta
                        if (comprobar.contains("//")){comprobar=lineasLecturas.nextLine();}
                    //Aquí busco si en la linea que he leido contiene /*
                        if(comprobar.contains("/*")){
                            //Aquí meto en la variable que voy a escribir todo lo que habia antes del /*
                                    if (comprobar.indexOf("/*")>0) {
                                        Escribir += comprobar.substring(0, comprobar.indexOf("/*")) + "\n";
                                    }
                                    //Aquí voy leyendo líneas hasta que encuentre el final del comentario */
                                    if (!comprobar.contains("*/")){
                                            while (!comprobar.contains("*/")) {
                                                comprobar = lineasLecturas.nextLine();
                                            }
                                    //Aqui estando en la última linea comentada meto todo el contenido despues de que termine
                                            if (comprobar.length()>comprobar.indexOf("*/")+2) {
                                                entrar=true;
                                               comprobar = comprobar.substring(comprobar.indexOf("*/") + 2, comprobar.length());
                                                }else {
                                                entrar=false;}
                                    }else {entrar=false;}
                        }
                    //Aquí voy guardando todo el contenido de la linea en la variable que voy a escribir
                        if (entrar) {
                            for (int i = 0; i < comprobar.length(); i++) {
                                Escribir += comprobar.charAt(i);
                            }
                            //Aquí añado un salto de linea al terminar de escribir cada linea
                            Escribir += "\n";
                        }

            }
            //Aquí visualizo el contenio a escribir
                System.out.println(Escribir);
            //Aquí creo y escribo en el fichero
                nuevo.createNewFile();
                escribeArchivo.write(Escribir);
            //Aquí cierro todo lo que he abierto
                escribeArchivo.close();
                teclado.close();
                lineasLecturas.close();
                lecturas.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
