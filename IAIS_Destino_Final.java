/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iais_destino_final;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.io.*;

/**
 *
 * @author fer_h
 */
public class IAIS_Destino_Final 
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException
    {
        File archivo = new File("C:\\Users\\fer_h\\Desktop\\sql.txt");
        FileReader fr = new FileReader(archivo);
        BufferedReader br = new BufferedReader(fr);
        String linea = br.readLine();
        StringTokenizer st = new StringTokenizer(linea);
        int n = 0;
        int m = 0;
        int j = 0;
        int k = 0;
        while((linea = br.readLine()) != null)
        {
            System.out.println(linea);
            if (linea.contains("stu") == true) 
            {
                n++;
            }
            
            if (linea.contains(",")) 
            {
                m++;
            }
            if (linea.contains(" ")) 
            {
                j++;
            }
            if (linea.contains("")) 
            {
                k++;
            }
        }
        System.out.println(n);
        System.out.println(m);
        System.out.println(j);
        System.out.println(k);
        System.out.println("");
        ArrayList<String> textoarray = new ArrayList<String>();
        while(st.hasMoreTokens())
        {
            textoarray.add(st.nextToken());
        }
        System.out.println();
        
        
    }
    
}
