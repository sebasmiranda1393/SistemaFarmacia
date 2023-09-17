package com.example.lab2farmacia;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResumenPedidoFrame extends JFrame {
    public ResumenPedidoFrame(String nombreMedicamento, String tipoMedicamento, int cantidad, String distribuidor, String sucursales) {
        setTitle("Resumen del Pedido");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cierra solo la ventana de resumen

        JLabel tituloLabel = new JLabel("Resumen del Pedido");
        JLabel pedidoLabel = new JLabel(cantidad + " unidades del " + tipoMedicamento + " " + nombreMedicamento);

        JLabel direccionLabel = new JLabel();

        if (sucursales.contains("Principal")) {
            direccionLabel.setText("Para la farmacia situada en Calle de la Rosa n. 28");
        } else if (sucursales.contains("Secundaria")) {
            direccionLabel.setText("Para la farmacia situada en Calle Alcazabilla n. 3");
        }

        JButton cancelarButton = new JButton("Cancelar");
        JButton enviarPedidoButton = new JButton("Enviar Pedido");

        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        //  ActionListener del botón Enviar Pedido
        enviarPedidoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Simulacin del envío del pedido
                System.out.println("Pedido enviado");
                new ConfirmacionPedidoFrame();
                dispose(); // Cierra la ventana de resumen
            }
        });

        JPanel resumenPanel = new JPanel();
        resumenPanel.setLayout(new GridLayout(4, 1));

        resumenPanel.add(tituloLabel);
        resumenPanel.add(pedidoLabel);
        resumenPanel.add(direccionLabel);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(cancelarButton);
        buttonPanel.add(enviarPedidoButton);

        resumenPanel.add(buttonPanel);

        add(resumenPanel);

        setVisible(true);
    }

}
