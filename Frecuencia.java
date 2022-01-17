
import java.util.Arrays;
import javax.swing.JOptionPane;

// Como ejemplo debe usar el ejercicio de los salarios quincenales, en dólares recopilados de una muestra de 45 empleados

public class Frecuencia {

    private int[] salarios;
    private int cantidad;
    private int valor;
    private int Maximo;
    private int Minimo;
    private int Rango;
    private int Intervalo;
    private int Amplitud;
    private double[][] tabla;

    public void cargarCantidad(){
        cantidad = Integer.parseInt(JOptionPane.showInputDialog("Cantidad de datos: "));
        salarios = new int[cantidad];
    }

    public void cargarDatos(){
        for (int i = 1; i <= salarios.length; i++) {
            valor = (int) (Math.random() * (5000-1000+1)+1000); 
            //valor = Integer.parseInt(JOptionPane.showInputDialog("Ingrese dato "+i+": "));
            salarios[i-1] = valor;
        }
    }

    // 1. Determinar la cantidad de datos (N)

    // 2. Determinar los valores máximo y mínimo
    public static int OP_Maximo(int[] salarios){
        int maxNum = salarios[0];
        for (int j : salarios) {
            if (j > maxNum)
                maxNum = j;
        }

        return maxNum;
    }

    public static int OP_Minimo(int[] salarios){
        int minNum = salarios[0];
        for (int j : salarios) {
            if (j < minNum)
                minNum = j;
        }

        return minNum;
    }

    // 3. Determinar el rango (R) de valores.
    public static int OP_Rango(int Max, int Min){
        int rango = Max - Min;

        return rango;
    }

    // 4. Determinar el número de intervalos (K) según la regla de Sturges.
    public static int OP_Intervalo(int N){
        int redondeo;
        double interTemp = 1+ 3.3*Math.log10(N);

        if(interTemp % 1 == 0){
            redondeo = (int) interTemp;
        }else{
            redondeo = (int) interTemp + 1;
        }
        
        return  redondeo;
    }

    // 5. Determinar la amplitud (A). 
    public static int OP_Amplitud(int rango, int intervalo){
        float amplitudTemp = (float) rango/intervalo;

        int amplitud = (int) Math.round(amplitudTemp);
        return amplitud;
    }

    public void realizarOperaciones(){
        Maximo = OP_Maximo(salarios);
        Minimo = OP_Minimo(salarios);
        Rango = OP_Rango(Maximo, Minimo);
        Intervalo = OP_Intervalo(cantidad);
        Amplitud = OP_Amplitud(Rango, Intervalo);

    }

    public void imprimirResultados(){
        System.out.println("\nDATOS: \n" + Arrays.toString(salarios));
        System.out.println("\nCantidad de datos: " + cantidad);
        System.out.println("Valor Maximo: " + Maximo);
        System.out.println("Valor Minimo: " + Minimo);
        System.out.println("Rango de valores: " + Rango);
        System.out.println("Numero de Intervalos: " + Intervalo);
        System.out.println("Amplitud: " + Amplitud);
    }

    // Tabla de frecuencias
    public void ejecutarTabla(){
        tabla = new double[Intervalo][8];
        int min=Minimo, max=Minimo + Amplitud, Fi=0;
        double Hi = 0;

        for (int i = 0; i < tabla.length; i++) {
            int cant = 0;
            for (int j = 0; j < salarios.length; j++){
                if (salarios[j] >= min && salarios[j] < max)
                    cant++;
            }

            Fi += cant;
            Hi += (double)cant/cantidad;

            tabla[i][0] = i+1;
            tabla[i][1] = min;
            tabla[i][2] = max;
            tabla[i][3] = cant;
            tabla[i][4] = Fi;
            tabla[i][5] = Math.round(((double)cant/cantidad)*100.0)/100.0;
            tabla[i][6] = Math.round(((double)Hi)*100.0)/100.0;
            tabla[i][7] = Math.round((float)(min + max)/2);

            min += Amplitud;
            max += Amplitud;
        }

        System.out.println("\nTABLA DE FRECUENCIAS:\n N      Minimo  Maximo   fi      Fi       hi      Hi      xi");
        for (int i = 0; i < tabla.length; i++) {
            for (int j = 0; j < tabla[i].length; j++) {

                if (j != 5 && j != 6) {
                    System.out.print(" " + (int)tabla[i][j] + "\t");
                }else{
                    System.out.print(" " + tabla[i][j] + "\t");
                }
                
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Frecuencia frecuencia = new Frecuencia();

        frecuencia.cargarCantidad();
        frecuencia.cargarDatos();
        frecuencia.realizarOperaciones();
        frecuencia.imprimirResultados();
        frecuencia.ejecutarTabla();
    }


}