import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class Generar {
	public static String[][] leertxt() throws IOException {
		File archivo = new File("C:\\Users\\Davit\\Desktop\\Texto.txt");
		FileReader fr = new FileReader(archivo);
		BufferedReader br = new BufferedReader(fr);
		String linea;
		int lincant = 0;
		int stus = 1;
		int coms = 0;
		String texto = "";
		String texto2 = "";
		String nomtab1 = "";
		String nomtab2 = "";

		while ((linea = br.readLine()) != null) {
			lincant++;
			if (linea.contains(".") && linea.contains("com") && linea.contains("=") && linea.contains("ON")) {
				texto = texto + " " + linea.substring(linea.indexOf(".") + 1, linea.indexOf("=") - 1) + " "
						+ linea.substring(linea.lastIndexOf(".") + 1, linea.length());
				texto2 = texto2 + " " + linea.substring(3, linea.indexOf(".")) + " "
						+ linea.substring(linea.indexOf("=") + 1, linea.lastIndexOf("."));

			} else if (linea.contains(".") && !linea.endsWith(",")) {
				if (linea.contains("WHERE")) {
					texto = texto + " " + linea.substring(linea.indexOf(".") + 1, linea.indexOf("=") - 1);
					texto2 = texto2 + " " + linea.substring(linea.indexOf(" "), linea.indexOf("."));
				} else if (linea.contains("ORDER")) {
					texto = texto + " " + linea.substring(linea.indexOf(".") + 1, linea.lastIndexOf(" "));

					texto2 = texto2 + " " + linea.substring(linea.indexOf("Y ") + 1, linea.indexOf("."));

				} else {
					texto2 = texto2 + " " + linea.substring(0, linea.indexOf("."));
					texto = texto + " " + linea.substring(linea.indexOf('.') + 1, linea.length());

				}

			} else if (linea.contains(".") && linea.endsWith(",")) {
				texto = texto + " " + linea.substring(linea.indexOf('.') + 1, linea.length() - 1);
				texto2 = texto2 + " " + linea.substring(0, linea.indexOf("."));

			} else if (linea.equals("FROM") || linea.equals("SELECT")) {
				texto = texto;

			} else if (linea.contains("JOIN")) {
				texto = texto + linea.replace(linea.substring(linea.lastIndexOf("J"), linea.lastIndexOf("N") + 1), "");
				texto2 = texto2 + linea.substring(linea.indexOf(" "), linea.length());
			} else {
				texto = texto + " " + linea;
				texto2 = texto2 + " " + linea;
			}

		}
		System.out.println(lincant);
		if (texto.contains("ASC")) {
			System.out.println(texto.substring(0, texto.length() - 4));
			texto = texto.substring(0, texto.length() - 4);
		}
		System.out.println(texto);
		StringTokenizer st = new StringTokenizer(texto);
		StringTokenizer st2 = new StringTokenizer(texto2);
		int tok = st.countTokens();
		int i = 0;
		String aux = null;
		int tok2 = st2.countTokens();
		String linea2 = br.readLine();

		System.out.println(texto2);
		String[][] textoarray = new String[2][tok2 + 1];

		textoarray[0][0] = "TableName";
		textoarray[1][0] = "ColumnName";
		System.out.println(tok);
		System.out.println(tok2);
		int c = 0;
		for (int j = 1; j < tok; j++) {

			textoarray[1][j] = st.nextToken();
			textoarray[0][j] = st2.nextToken();
			System.out.println(textoarray[0][j] + "   " + textoarray[1][j]);
			if (textoarray[0][j].equals(textoarray[1][j])) {
				aux = textoarray[0][j];
				c++;
			}
			if (c == 1 && textoarray[0][j].length() > 3) {
				nomtab1 = aux;
			} else if (c > 1 && textoarray[0][j].length() > 3) {
				nomtab2 = aux;
			}

		}
		for (int j = 1; j < tok; j++) {
			if (textoarray[0][j].contains(nomtab1.substring(0, 2)) && !textoarray[1][j].contains(".")) {
				textoarray[0][j] = nomtab1;

			} else if (textoarray[0][j].contains(nomtab2.substring(0, 2)) && !textoarray[1][j].contains(".")) {
				textoarray[0][j] = nomtab2;

			}

		}
		for (int j = 0; j < tok; j++) {
			if (textoarray[0][j].equals(textoarray[1][j])) {
				textoarray[0][j] = "";
				textoarray[1][j] = "";

			} else if (textoarray[1][j].length() <= 3 && textoarray[0][j].contains(textoarray[1][j])) {
				textoarray[0][j] = "";
				textoarray[1][j] = "";
			}
		}
		int cont = 0;
		int cont2 = 0;
		for (int j = 0; j < tok; j++) {
			if (textoarray[0][j] != "") {
				cont++;
			}
		}
		System.out.println(cont);
		String[][] textoarrayexcel = new String[cont][cont];
		for(int j=0; j<tok; j++) {
			if(textoarray[0][j]!="") {
			textoarrayexcel[0][cont2]=textoarray[0][j];
			textoarrayexcel[1][cont2]=textoarray[1][j];

			cont2++;
			}
		}
		System.out.println(cont2);
		return textoarrayexcel;
	}

	public void generarExcel(String[][] entrada, String ruta, String nomHoja) {
		try {
			WorkbookSettings conf = new WorkbookSettings();
			conf.setEncoding("ISO-8859-1");
			WritableWorkbook workbook = Workbook.createWorkbook(new File(ruta), conf);

			WritableSheet sheet = workbook.createSheet(nomHoja, 0);

			WritableFont h = new WritableFont(WritableFont.COURIER, 16, WritableFont.NO_BOLD);
			WritableCellFormat hformat = new WritableCellFormat(h);
			for (int i = 0; i < entrada.length; i++) {
				for (int j = 0; j < entrada[i].length; j++) {

					sheet.addCell(new jxl.write.Label(i, j, entrada[i][j], hformat));

				}
			}

			workbook.write();

			workbook.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}