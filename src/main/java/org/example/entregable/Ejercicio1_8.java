package org.example.entregable;
import java.io.*;
import java.util.*;

public class Ejercicio1_8 {
        private static boolean señalTerminar;
        private static int contadorLineas;

        public static void main(String[] args) {
            señalTerminar = false;
            contadorLineas = 0;
            manejarSeñal();
            programa(args);
        }

        private static void manejarSeñal() {
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                señalTerminar = true;
                System.out.println("Terminando programa por señal, la cantidad de lineas leidas fue:" + contadorLineas);
            }));
        }

        private static void ejecutarComandoEnHilo(String[] partesComando, Map<String, String> entorno, String separador) {
            Thread hiloComando = new Thread(() -> {
                String comando = partesComando[0];
                String[] argsComandos = new String[partesComando.length - 1];
                System.arraycopy(partesComando, 1, argsComandos, 0, argsComandos.length);
                try {
                    ProcessBuilder constructorProceso = new ProcessBuilder();
                    List<String> listaComando = new ArrayList<>();
                    listaComando.add(comando);
                    listaComando.addAll(Arrays.asList(argsComandos));
                    constructorProceso.command(listaComando);
                    constructorProceso.environment().putAll(entorno);

                    Process proceso = constructorProceso.start();

                    int codigoSalida = proceso.waitFor();
                    System.out.println("Comando: "+comando + separador + " Retorno: "+codigoSalida);

                    if (codigoSalida != 0)
                        System.err.println("El comando " + comando + " falló con valor de retorno " + codigoSalida);
                } catch (IOException | InterruptedException e) {
                    System.err.println("Error al ejecutar el comando: " + e.getMessage());
                }
            });
            hiloComando.start();
        }

        private static Map<String, String> obtenerEntorno() {
            Map<String, String> variablesEntorno = new HashMap<>(System.getenv());
            variablesEntorno.put("HOSTNAME", "PRUEBA");
            return variablesEntorno;
        }

        private static void programa(String[] args) {
            String separador;
            separador = (args.length > 0 && args[0].equals("-s") && args.length > 1) ? args[1] : "\t";
            BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
            String linea;
            try {
                while (!señalTerminar && (linea = lector.readLine()) != null) {
                    String[] partesComando = linea.split(" ");
                    if (partesComando.length != 0 || !partesComando[0].isEmpty()) {
                        contadorLineas++;
                        ejecutarComandoEnHilo(partesComando, obtenerEntorno(), separador);
                    } else {
                        señalTerminar = true;
                    }
                }
                lector.close();
                System.exit(0);
            } catch (IOException e) {
                System.err.println("Error al momento de leer la entrada estándar: " + e.getMessage());
                System.exit(1);
            }
        }

}
