/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.practica11;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ANGEL ZAMUDIO
 */
public class Main {
  public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException {
        
        System.out.println("----- File -----");
        File archivo = new File("archivo.txt");
        System.out.println(archivo.exists()); //booleano si existe.
        if(!archivo.exists()){
            try {
                boolean seCreo = archivo.createNewFile(); //Crea el archivo.
                System.out.println(seCreo);
                System.out.println(archivo.exists());
            } catch (IOException ex) {
                ex.getMessage();//Informacion del error.
                ex.getStackTrace();//Informacion de error de clases.
            }
        }
        
        System.out.println("----- FileOutputStream -----");
        FileOutputStream fos = null; //Instancia para meter datos.
        byte[] buffer =new byte[81];
        int nBytes; //Apuntador de posicion con informacion valida.
        try {
            System.out.println("Escribir el texto a guardar en archivo: ");
            nBytes = System.in.read(buffer);
            fos = new FileOutputStream("FOS.txt");
            fos.write(buffer,0,nBytes);
        } 
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        finally{//Cerrar.
            try {
            if(fos!=null)
                fos.close();
            } 
            catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
        
        System.out.println("----- FileInputStream -----");
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("FOS.txt");
            nBytes = fis.read(buffer,0,81);
            String texto = new String(buffer,0,nBytes);
            System.out.println(texto);
        } 
        catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        finally{//Cerrar.
            try {
                if(fis!=null){fis.close();}
            }
            catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
        
        System.out.println("----- FileWriter -----");
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Escriba texto para el archivo: ");
            String texto2 = br.readLine();
            
            FileWriter fw = new FileWriter("FW.csv");
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter salida = new PrintWriter(bw);
            
            salida.println(texto2);
            
            //Codigo para proyecto.
            for(int i = 0; i<10 ; i++){
                salida.println(i+" Linea del for.");}
            for(int i = 0; i<10 ; i++){
                salida.println(i+" Uriarte,Ortiz,Yahir,318234757,18,50");}
            
            salida.close();
        }
        catch (IOException ex) {
             System.out.println(ex.getMessage());
        }
    
        System.out.println("----- FileReader -----");
        try {
            BufferedReader br = null;
            FileReader fr = new FileReader("FW.csv");
            br = new BufferedReader(fr);
            System.out.println("El texto del archivo es: ");
            String linea = br.readLine();
            while(linea!=null){
                System.out.println(linea);
                linea = br.readLine();}
            br.close();
        }
        catch (FileNotFoundException ex) {
             System.out.println(ex.getMessage());
        }
        catch (IOException ex) {
             System.out.println(ex.getMessage());
        }
        
        System.out.println("----- StringTokenizer -----");
        String linea = "Ramirez,Agripino,Jaimito,320345626,17,10";
        StringTokenizer tokenizer = new StringTokenizer(linea,",");
        while(tokenizer.hasMoreTokens()){
            System.out.println(tokenizer.nextToken());}
        
        System.out.println("----- Serializacion -----");
        Date date = new Date();
        System.out.println(date);
        try {
            FileOutputStream f = new FileOutputStream("Fecha.ser");
            ObjectOutputStream oos = new ObjectOutputStream(f);
            oos.writeObject(date);
            oos.close();
        }
        catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        System.out.println("----- Deserializacion -----");
        
        try {
            TimeUnit.SECONDS.sleep(10);
            Date date2 = new Date();
            System.out.println("La fecha actuallizada es: "+date2);
            
            FileInputStream f = new FileInputStream("Fecha.ser");
            ObjectInputStream ois = new ObjectInputStream(f);
            Date date3 = (Date)ois.readObject();
            ois.close();
            System.out.println("Objero es señalizado "+date3);
        }
        catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        catch (InterruptedException ex) {
            System.out.println(ex.getMessage());//
        }
    }
}