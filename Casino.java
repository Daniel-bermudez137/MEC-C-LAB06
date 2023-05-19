import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Casino extends JFrame implements ActionListener {

    private JButton btnDados;
    private JButton btnTragamonedas;
    private JButton btnSalir;
    private Timer timerTragamonedas;
    private Timer timerDados;
    private ImageIcon[] simbolosTragamonedas;
    private ImageIcon[] simbolosDados;
    private JLabel lblCarrete1;
    private JLabel lblCarrete2;
    private JLabel lblCarrete3;
    private JLabel lblDado1;
    private JLabel lblDado2;
    private JLabel lblDado3;
    private boolean tragamonedasJugado;

    public Casino() {
        // Configuración de la ventana
        setTitle("Casino Lab6");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Crear botones
        btnDados = new JButton("Dados");
        btnTragamonedas = new JButton("Tragamonedas");
        btnSalir = new JButton("Salir");

        // Agregar oyentes de eventos
        btnDados.addActionListener(this);
        btnTragamonedas.addActionListener(this);
        btnSalir.addActionListener(this);

        // Agregar botones al panel superior
        JPanel panelBotones = new JPanel();
        panelBotones.add(btnDados);
        panelBotones.add(btnTragamonedas);
        panelBotones.add(btnSalir);
        add(panelBotones, BorderLayout.NORTH);

        // Cargar las imágenes de los símbolos para el juego de tragamonedas
        simbolosTragamonedas = new ImageIcon[]{
                new ImageIcon("Cereza.png"),
                new ImageIcon("naranja.png"),
                new ImageIcon("uva.png"),
                new ImageIcon("7.png")
        };

        // Cargar las imágenes de los símbolos para el juego de dados
        simbolosDados = new ImageIcon[]{
                new ImageIcon("1.png"),
                new ImageIcon("2.png"),
                new ImageIcon("3.png"),
                new ImageIcon("4.png"),
                new ImageIcon("5.png"),
                new ImageIcon("6.png")
        };

        // Inicializar los labels de los carretes y dados
        lblCarrete1 = new JLabel();
        lblCarrete2 = new JLabel();
        lblCarrete3 = new JLabel();
        lblDado1 = new JLabel();
        lblDado2 = new JLabel();
        lblDado3 = new JLabel();

        // Agregar los labels al panel central
        JPanel panelCarretes = new JPanel();
        panelCarretes.add(lblCarrete1);
        panelCarretes.add(lblCarrete2);
        panelCarretes.add(lblCarrete3);
        add(panelCarretes, BorderLayout.CENTER);

        JPanel panelDados = new JPanel();
        panelDados.add(lblDado1);
        panelDados.add(lblDado2);
        panelDados.add(lblDado3);
        add(panelDados, BorderLayout.SOUTH);

        // Configurar el temporizador para el juego de tragamonedas
        timerTragamonedas = new Timer(100, new ActionListener() {
            private int tiempoTranscurrido = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                tiempoTranscurrido += 100;

                if (tiempoTranscurrido >= 5000) {
                    timerTragamonedas.stop();
                    mostrarResultadoTragamonedas();
                    reiniciarCarretes();
                    reiniciarTemporizador();
                } else {
                    cambiarImagenesCarretes(simbolosTragamonedas);
                }
            }
        });

        timerDados = new Timer(200, new ActionListener() {
            private int tiempoTranscurrido = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                tiempoTranscurrido += 200;

                if (tiempoTranscurrido >= 5000) {
                    timerDados.stop();
                    mostrarResultadoDados();
                    reiniciarDados();
                } else {
                    cambiarImagenesDados(simbolosDados);
                }
            }
        });

        tragamonedasJugado = false;
    }

    // Método que se ejecuta cuando se hace clic en un botón
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnDados) {
            if (!timerDados.isRunning()) {
                timerDados.start();
            }
        } else if (e.getSource() == btnTragamonedas) {
            if (!timerTragamonedas.isRunning() && !tragamonedasJugado) {
                timerTragamonedas.start();
                tragamonedasJugado = true;
            }
        } else if (e.getSource() == btnSalir) {
            // Salir de la aplicación
            dispose();
        }
    }

    // Método que cambia las imágenes en los carretes
    private void cambiarImagenesCarretes(ImageIcon[] simbolos) {
        Random random = new Random();

        // Generar índices aleatorios para las imágenes de los carretes
        int indice1 = random.nextInt(simbolos.length);
        int indice2 = random.nextInt(simbolos.length);
        int indice3 = random.nextInt(simbolos.length);

        // Establecer las imágenes en los labels de los carretes
        lblCarrete1.setIcon(simbolos[indice1]);
        lblCarrete2.setIcon(simbolos[indice2]);
        lblCarrete3.setIcon(simbolos[indice3]);
    }

    // Método que cambia las imágenes en los dados
    private void cambiarImagenesDados(ImageIcon[] simbolos) {
        Random random = new Random();

        // Generar índices aleatorios para las imágenes de los dados
        int indice1 = random.nextInt(simbolos.length);
        int indice2 = random.nextInt(simbolos.length);
        int indice3 = random.nextInt(simbolos.length);

        // Establecer las imágenes en los labels de los dados
        lblDado1.setIcon(simbolos[indice1]);
        lblDado2.setIcon(simbolos[indice2]);
        lblDado3.setIcon(simbolos[indice3]);
    }

    // Método que muestra el resultado de la suma de los dados en una ventana emergente
    private void mostrarResultadoDados() {
        Random random = new Random();
        int resultado1 = random.nextInt(6) + 1;
        int resultado2 = random.nextInt(6) + 1;
        int resultado3 = random.nextInt(6) + 1;

        int suma = resultado1 + resultado2 + resultado3;

        JOptionPane.showMessageDialog(this, "Resultado de los dados: " + suma);
    }

    // Método para reiniciar los carretes a su estado inicial
    private void reiniciarCarretes() {
        lblCarrete1.setIcon(null);
        lblCarrete2.setIcon(null);
        lblCarrete3.setIcon(null);
    }

    // Método para reiniciar los dados a su estado inicial
    private void reiniciarDados() {
        lblDado1.setIcon(null);
        lblDado2.setIcon(null);
        lblDado3.setIcon(null);
    }

    // Método para reiniciar el temporizador del juego de tragamonedas a su estado inicial
    private void reiniciarTemporizador() {
        timerTragamonedas = new Timer(100, new ActionListener() {
            private int tiempoTranscurrido = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                tiempoTranscurrido += 100;

                if (tiempoTranscurrido >= 5000) {
                    timerTragamonedas.stop();
                    mostrarResultadoTragamonedas();
                    reiniciarCarretes();
                    reiniciarTemporizador();
                } else {
                    cambiarImagenesCarretes(simbolosTragamonedas);
                }
            }
        });
    }

    // Método para determinar el ganador en el juego de tragamonedas
    private void mostrarResultadoTragamonedas() {
        ImageIcon imagen1 = (ImageIcon) lblCarrete1.getIcon();
        ImageIcon imagen2 = (ImageIcon) lblCarrete2.getIcon();
        ImageIcon imagen3 = (ImageIcon) lblCarrete3.getIcon();

        if (imagen1 == imagen2 && imagen2 == imagen3) {
            JOptionPane.showMessageDialog(this, "¡Felicidades! ¡Eres un ganador en el juego de tragamonedas!");
        } else {
            JOptionPane.showMessageDialog(this, "Lo siento, no has ganado en el juego de tragamonedas. ¡Inténtalo de nuevo!");
        }

        tragamonedasJugado = false;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Casino juegosDeAzarGUI = new Casino();
                juegosDeAzarGUI.pack();
                juegosDeAzarGUI.setVisible(true);
            }
        });
    }
}
