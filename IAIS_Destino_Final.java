/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iais_destino_final;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author fer_h
 */
public class IAIS_Destino_Final 
{

    /**
     * @param args the command line arguments
     */
    
    public static IAIS_Destino_Final g=new IAIS_Destino_Final() ;
    public static void main(String[] args) throws FileNotFoundException, IOException 
    {
        String ruta = "C:\\Users\\fer_h\\Desktop\\sql.txt";
        Scanner lector = new Scanner(System.in);
        File archivo = new File(ruta);
        FileReader fr = new FileReader(archivo);
        BufferedReader br = new BufferedReader(fr);
        String linea;
        int lincant = 0;
        int filas = 0;
        int stus = 0;
        int coms = 0;
        int j = 0;
        String texto = "";
        String nomtabla;
        String nomalias;
        String nombre;
        
        
        while ((linea = br.readLine()) != null) 
        {
            lincant++;
            if (linea.contains(".") && linea.contains("com")&&linea.contains("=")) 
            {
                texto = texto +" "+linea.substring(linea.indexOf(".") + 1, linea.indexOf("=")-1)+" "+linea.substring(linea.lastIndexOf(".")+1,linea.length());
            }
            else if (linea.contains(".") && !linea.endsWith(",")) 
            {
                    texto = texto + " " + linea.substring(linea.indexOf('.') + 1, linea.length());
            } 
            else if (linea.contains(".") && linea.endsWith(",")) 
            {
                    texto = texto + " " + linea.substring(linea.indexOf('.') + 1, linea.length() - 1);
            } 
            else if (linea.equals("FROM") || linea.equals("SELECT")) 
            {
                    texto = texto;
            } 
            else if (linea.contains("JOIN")) 
            {
                    texto = texto + linea.replace(linea.substring(linea.lastIndexOf("J"), linea.lastIndexOf("N") + 1), "");
            } 
            else 
            {
                texto = texto + " " + linea;
            }
            if (linea.contains("com.")) 
            {
                coms++;
            }
            if (linea.contains("stu.")) 
            {
                stus++;
            }//name age forum_id comment  students stu comments com forum_username forum_username city = �Ensenada� name 

        }
        
        filas = coms + stus;
        if (texto.contains("ASC")) 
        {
                System.out.println(texto.substring(0, texto.length() - 4));
                texto = texto.substring(0, texto.length() - 4);
        }
        StringTokenizer st = new StringTokenizer(texto);
        int tok = st.countTokens();
        
        if (texto.contains("stu")) 
        {
            
        }

        System.out.println(texto);
        String[][] textoarray = new String[tok][filas];
        textoarray[0][0] = "TableName";
        textoarray[1][0] = "ColumnName";
        System.out.println(tok);
        while(st.hasMoreTokens()) 
        {
            
            textoarray[1][j] = st.nextToken();
            System.out.println(textoarray[1][j]);
        }
        System.out.println("Ingresar nombre de archivo");
        nombre = lector.nextLine();
        ruta = "C:\\Users\\fer_h\\Desktop\\"+nombre+".xls";
        g.generarExcel(textoarray, ruta, nombre);

    }
    
    public void generarExcel(String[][] entrada, String ruta, String nomHoja) 
    {
        try 
        {
            WorkbookSettings conf = new WorkbookSettings();
            conf.setEncoding("ISO-8859-1");
            WritableWorkbook workbook = Workbook.createWorkbook(new File(ruta), conf);

            WritableSheet sheet = workbook.createSheet(nomHoja, 0);

            WritableFont h = new WritableFont(WritableFont.COURIER, 16, WritableFont.NO_BOLD);
            WritableCellFormat hformat = new WritableCellFormat(h);
            for (int i = 0; i < entrada.length; i++) 
            {
                for (int j = 0; j < entrada[i].length; j++) 
                {
                        sheet.addCell(new jxl.write.Label(i, j, entrada[i][j], hformat));
                }
            }

            workbook.write();
            workbook.close();
        }
        catch (IOException e) 
        {
                // TODO Auto-generated catch block
                e.printStackTrace();
        } 
        catch (RowsExceededException e) 
        {
                // TODO Auto-generated catch block
                e.printStackTrace();
        } 
        catch (WriteException e) 
        {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }

    }
    
}
