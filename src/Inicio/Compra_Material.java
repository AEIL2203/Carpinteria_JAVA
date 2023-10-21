package Inicio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Compra_Material {

	public static void Compra_Mat(POO materiales) {

		try {

			FileReader fileReader = new FileReader("Materiales.txt");
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			String linea;
			StringBuilder contenido = new StringBuilder();
			int numeroDeLinea = 0;

			while ((linea = bufferedReader.readLine()) != null) {
				numeroDeLinea++;
				if (numeroDeLinea == 1) {

					contenido.append(materiales.getNom() + "," + materiales.getCant()).append("\n");
				} else {

					contenido.append(linea).append("\n");
				}
			}

			bufferedReader.close();

			FileWriter fileWriter = new FileWriter("Materiales.txt");
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			bufferedWriter.write(contenido.toString());

			bufferedWriter.close();

			System.out.println("Datos ingresados en la primera línea correctamente.");

		} catch (IOException e) {
			System.err.println("Error al manipular el archivo: " + e.getMessage());
		}
	}

	public static void Compra_Mat2(POO materiales2) {

		try {

			FileReader fileReader = new FileReader("Materiales.txt");
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			String linea;
			StringBuilder contenido = new StringBuilder();
			int numeroDeLinea = 0;

			while ((linea = bufferedReader.readLine()) != null) {
				numeroDeLinea++;
				if (numeroDeLinea == 2) {

					contenido.append(materiales2.getNom() + "," + materiales2.getCant()).append("\n");
				} else {

					contenido.append(linea).append("\n");
				}
			}

			bufferedReader.close();

			FileWriter fileWriter = new FileWriter("Materiales.txt");
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			bufferedWriter.write(contenido.toString());

			bufferedWriter.close();

			System.out.println("Datos ingresados en la segunda línea correctamente.");

		} catch (IOException e) {
			System.err.println("Error al manipular el archivo: " + e.getMessage());
		}
	}

	public static void Compra_Mat3(POO materiales3) {

		try {

			FileReader fileReader = new FileReader("Materiales.txt");
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			String linea;
			StringBuilder contenido = new StringBuilder();
			int numeroDeLinea = 0;

			while ((linea = bufferedReader.readLine()) != null) {
				numeroDeLinea++;
				if (numeroDeLinea == 3) {

					contenido.append(materiales3.getNom() + "," + materiales3.getCant()).append("\n");
				} else {

					contenido.append(linea).append("\n");
				}
			}

			bufferedReader.close();

			FileWriter fileWriter = new FileWriter("Materiales.txt");
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			bufferedWriter.write(contenido.toString());

			bufferedWriter.close();

			System.out.println("Datos ingresados en la segunda línea correctamente.");

		} catch (IOException e) {
			System.err.println("Error al manipular el archivo: " + e.getMessage());
		}
	}
}
