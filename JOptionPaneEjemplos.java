import javax.swing.JOptionPane;

public class JOptionPaneEjemplos {
    public static void main(String[] args) {
        
        // 1. MENSAJE SIMPLE (Information)
        JOptionPane.showMessageDialog(null, "Este es un mensaje");
        JOptionPane.showMessageDialog(null, "Éxito", "Título", JOptionPane.INFORMATION_MESSAGE);
        
        // 2. MENSAJE DE ERROR
        JOptionPane.showMessageDialog(null, "Ha ocurrido un error", "Error", JOptionPane.ERROR_MESSAGE);
        
        // 3. MENSAJE DE ADVERTENCIA
        JOptionPane.showMessageDialog(null, "¡Cuidado!", "Advertencia", JOptionPane.WARNING_MESSAGE);
        
        // 4. MENSAJE PLANO (sin icono)
        JOptionPane.showMessageDialog(null, "Mensaje sin icono", "Info", JOptionPane.PLAIN_MESSAGE);
        
        // 5. PREGUNTA CON SÍ/NO
        int opcion = JOptionPane.showConfirmDialog(null, "¿Deseas continuar?");
        // Retorna: JOptionPane.YES_OPTION, NO_OPTION, CANCEL_OPTION
        
        // 6. PREGUNTA PERSONALIZADA
        Object[] opciones = {"Aceptar", "Cancelar", "Salir"};
        int resultado = JOptionPane.showOptionDialog(null, "Elige una opción",
                "Título", JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
        System.out.println(resultado);
        
        // 7. ENTRADA DE TEXTO
        String nombre = JOptionPane.showInputDialog(null, "¿Cuál es tu nombre?");
        String edad = JOptionPane.showInputDialog(null, "¿Cuál es tu edad?", "20");

        System.out.println("nombre: "+nombre+" edad: "+edad);
        
        // 8. COMBO BOX (lista desplegable)
        Object[] items = {"Java", "Python", "C++"};
        Object seleccion = JOptionPane.showInputDialog(null, "Elige lenguaje",
                "Lenguajes", JOptionPane.QUESTION_MESSAGE, null, items, items[0]);
        System.out.println(seleccion);
        
        // 9. ÁREA DE TEXTO GRANDE
        String[] lineas = {"Línea 1", "Línea 2", "Línea 3"};
        JOptionPane.showMessageDialog(null, String.join("\n", lineas), "Múltiples líneas", JOptionPane.INFORMATION_MESSAGE);
        
        // 10. PROCESAR RESPUESTAS
        if (opcion == JOptionPane.YES_OPTION) {
            System.out.println("Usuario seleccionó SÍ");
        } else if (opcion == JOptionPane.NO_OPTION) {
            System.out.println("Usuario seleccionó NO");
        } else if (opcion == JOptionPane.CANCEL_OPTION) {
            System.out.println("Usuario canceló");
        }
    }
}