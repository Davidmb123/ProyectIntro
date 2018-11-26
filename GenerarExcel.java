import java.io.IOException;
import java.util.Scanner;

public class GenerarExcel {

	public static Generar g = new Generar();

	public static void main(String[] args) throws IOException {
		Scanner leer = new Scanner(System.in);
		String textoarray[][]=g.leertxt();

		System.out.println("Ingrese el nombre de su hoja");
		String nomHoja = leer.next();

		String ruta = "/Users/Davit/Desktop/" + nomHoja + ".xls";
		g.generarExcel(textoarray, ruta, nomHoja);
	}

}
