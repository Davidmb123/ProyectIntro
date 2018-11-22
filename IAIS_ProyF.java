/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iais_proyf;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.util.StringTokenizer;

/**
 *
 * @author fer_h
 */
public class IAIS_ProyF {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException 
    {
		File archivo = new File("C:\\Users\\fer_h\\Desktop\\Texto.txt");
		FileReader fr = new FileReader(archivo);
		BufferedReader br = new BufferedReader(fr);
		String linea = br.readLine();
		String texto = "";
		while ((linea = br.readLine()) != null) 
                {
                    texto=texto+linea;
                    if(linea.contains("stu")) 
                    {
                        System.out.println(linea.subSequence(linea.indexOf(".")+1, linea.length()));
                    }
                    else 
                    {
                        System.out.println(linea);
                    }
		}
                System.out.println("");
		StringTokenizer st = new StringTokenizer(texto);
                int i = 0;
                ArrayList<String> textoarray = new ArrayList<>();
		while(st.hasMoreTokens()) 
                {
                    textoarray.add(0, st.nextToken());
		}
		
		System.out.println(textoarray.get(2));
		System.out.println("");
		
    }
    
}
