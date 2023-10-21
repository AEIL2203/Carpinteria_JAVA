package Inicio;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import static java.lang.System.exit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Inicio {

    public static void main(String[] args) {
        int Op;

        Scanner Entrada = new Scanner(System.in);
        // Menu principal
        System.out.println("Bienvenido que eres???");
        System.out.println("1) Usuario");
        System.out.println("2) Administrador ");
        System.out.println("3) Salir");

        Op = Entrada.nextInt();

        switch (Op) {

            case 1:
                // Mostrar opciones de usuario
                Usuario();
                break;
            case 2:
                // Mostrar opciones de Administrador
                Admin();
                break;
            case 3:
                // Salir del programa
                exit(0);
                break;

        }
    }

    private static void Usuario() {
        
        String url = "jdbc:mariadb://127.0.0.1:3306/fabrica_silla";
        String usuario = "root";
        String contraseña = "1234";
        Connection conexion = null;

        Scanner Entrada = new Scanner(System.in);
        Random rand = new Random();

        String Nom;
        int NIT, Sil, Mad = 10, Cla = 5, Gom = 5;
        int i = 0;
        int Mad2, Cla2, Gom2, Total;
        int Rand, Rand2, Rand3, Rand4;

        System.out.println("Ingrese su Nombre");
        Nom = Entrada.nextLine();
        System.out.println("Ingrese su NIT");
        NIT = Entrada.nextInt();
        System.out.println("Bienvenido " + Nom + " a la fabrica de sillas");
        System.out.println("Cuantas sillas va a comprar??");
        Sil = Entrada.nextInt();

        
        int maderaActual = 0, clavosActual = 0, gomaActual = 0;

        try {
            Connection connection = DriverManager.getConnection(url, usuario, contraseña);
            Statement statement = connection.createStatement();
            String consulta = "SELECT * FROM materiales";
            ResultSet resultado = statement.executeQuery(consulta);

            while (resultado.next()) {
                maderaActual = resultado.getInt("Madera");
                clavosActual = resultado.getInt("Clavos");
                gomaActual = resultado.getInt("Goma");
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Ciclo para ensamblar las sillas
        while (i < Sil) {
          
            if (Mad <= maderaActual && Cla <= clavosActual && Gom <= gomaActual) {
                // Resta de materiales 
                maderaActual -= Mad;
                clavosActual -= Cla;
                gomaActual -= Gom;
            } else {
                System.out.println("No hay suficientes materiales para hacer la silla.");
            }

            i++;
        }

        // Cálculo de costos
        Mad2 = Sil * Mad;
        Cla2 = Sil * Cla;
        Gom2 = Sil * Gom;
        Total = Sil * 100; 

        // Generación de números aleatorios para el número de factura
        Rand = rand.nextInt(10);
        Rand2 = rand.nextInt(10);
        Rand3 = rand.nextInt(10);
        Rand4 = rand.nextInt(10);

        // Impresión de la factura
        System.out.println("------FACTURA No." + Rand + Rand2 + Rand3 + Rand4 + "------");
        System.out.println(" ");
        System.out.println("------" + Nom + "------");
        System.out.println("------Materiales usados------");
        System.out.println("Tipo------Cantidad------Precio  ");
        System.out.println("Madera:      " + Mad2 + "         Q50.00"); 
        System.out.println("Clavos:      " + Cla2 + "         Q25.00"); 
        System.out.println("Goma:        " + Gom2 + "         Q25.00"); 
        System.out.println("Total-------------------Q" + Total + ".00");

     

        try {
            conexion = DriverManager.getConnection(url, usuario, contraseña);

            if (conexion != null) {
                System.out.println("Conexion exitosa a la base de datos.");

                String consultaSQL = "INSERT INTO factura (NIT, Nom_Client, Madera, Clavos, Goma) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement preparedStatement = conexion.prepareStatement(consultaSQL);

                preparedStatement.setInt(1, NIT);
                preparedStatement.setString(2, Nom);
                preparedStatement.setInt(3, Mad2);
                preparedStatement.setInt(4, Cla2);
                preparedStatement.setInt(5, Gom2);

                int filasAfectadas = preparedStatement.executeUpdate();
                if (filasAfectadas > 0) {
                    System.out.println("Datos insertados exitosamente.");
                } else {
                    System.out.println("No se insertaron datos.");
                }

                preparedStatement.close();

                // Actualización de los datos de materiales en la tabla "materiales"
                String updateQuery = "UPDATE materiales SET Madera = ?, Clavos = ?, Goma = ?";
                PreparedStatement updateStatement = conexion.prepareStatement(updateQuery);
                updateStatement.setInt(1, maderaActual);
                updateStatement.setInt(2, clavosActual);
                updateStatement.setInt(3, gomaActual);

                int filasActualizadas = updateStatement.executeUpdate();
                if (filasActualizadas > 0) {
                    System.out.println("Actualizacion exitosa. Filas actualizadas: " + filasActualizadas);
                } else {
                    System.out.println("No se encontraron registros para actualizar.");
                }

                updateStatement.close();
            }
        } catch (SQLException e) {
            System.err.println("Error de conexión a la base de datos: " + e.getMessage());
        } finally {
            try {
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexion: " + e.getMessage());
            }
        }
    }

    private static void Admin() {
        String Usua;
        int Contra, i = 0;
        Scanner Entrada2 = new Scanner(System.in);

        System.out.println("Bienvenido Admin");

        while (i == 0) {

            System.out.println("Ingrese el Usuario");
            Usua = Entrada2.next();
            System.out.println("Ingrese el Contrasena");
            Contra = Entrada2.nextInt();

            if (Usua.equals("Admin234") && Contra == 102003) {
                System.out.println("Bienvenido Admin");
                i = i + 1;
                Admin234();

            } else if (Usua.equals("Admin234") && Contra != 102003) {
                System.out.println("Contrasena Incorrecta");
                limpiarConsola();

            } else if (!Usua.equals("Admin234") && Contra == 102003) {
                System.out.println("Usuario Incorecto");
                limpiarConsola();

            }
        }
    }

    public static void limpiarConsola() {
        for (int i = 0; i < 20; i++) {
            System.out.println();
        }
    }

    public static void Admin234() {
        String archivoEntrada = "Materiales.txt";
        String url = "jdbc:mariadb://127.0.0.1:3306/fabrica_silla";
        String usuario = "root";
        String contraseña = "1234";
        Connection conexion = null;

        int Op2, Mad, Cla, Gom;
        String TxT;
        Scanner Entrada = new Scanner(System.in);

        System.out.println("Que desea hacer?");
        System.out.println("1) Ver cantidad de materiales");
        System.out.println("2) Comprar materiales");
        System.out.println("3) Salir");

        Op2 = Entrada.nextInt();

        switch (Op2) {
            case 1:
			
        try {

                conexion = DriverManager.getConnection(url, usuario, contraseña);

                if (conexion != null) {
                    System.out.println("Conexion exitosa a la base de datos.");

                    String consultaSQL = "SELECT * FROM materiales";
                    Statement statement = conexion.createStatement();
                    ResultSet resultado = statement.executeQuery(consultaSQL);

                    while (resultado.next()) {
                        String columna1 = resultado.getString("Madera");
                        String columna2 = resultado.getString("Clavos");
                        String columna3 = resultado.getString("Goma");

                        System.out.println("Madera: " + columna1);
                        System.out.println("Clavos: " + columna2);
                        System.out.println("Goma: " + columna2);
                    }
                    resultado.close();
                    statement.close();
                }
            } catch (SQLException e) {
                System.err.println("Error de conexion a la base de datos: " + e.getMessage());
            } finally {
                try {
                    if (conexion != null) {
                        conexion.close();
                    }
                } catch (SQLException e) {
                    System.err.println("Error al cerrar la conexion: " + e.getMessage());
                }
            }
            break;
//
            case 2:
           
        System.out.println("Cuantas tablas de madera desea comprar?");
        Mad = Entrada.nextInt();

        System.out.println("Cuantos clavos desea comprar?");
        Cla = Entrada.nextInt();

        System.out.println("Cuantos litros de goma desea comprar?");
        Gom = Entrada.nextInt();

        try {
                    conexion = DriverManager.getConnection(url, usuario, contraseña);

                    String updateQuery = "UPDATE materiales SET Madera = ?, Clavos = ? , Goma = ?";
                    PreparedStatement preparedStatement = conexion.prepareStatement(updateQuery);
                    preparedStatement.setInt(1, Mad);
                    preparedStatement.setInt(2, Cla);
                    preparedStatement.setInt(3, Gom);

                    int filasActualizadas = preparedStatement.executeUpdate();

                    if (filasActualizadas > 0) {
                        System.out.println("Actualizacion exitosa. Filas actualizadas: " + filasActualizadas);
                    } else {
                        System.out.println("No se encontraron registros para actualizar.");
                    }

                    conexion.close();
                } catch (SQLException e) {
                    e.printStackTrace();

                }

                break;
            case 3:
                System.out.println("Saliendo del programa.");
                System.exit(0);
                break;
            default:
                System.out.println("Opción no válida.");
        }

    }
}
