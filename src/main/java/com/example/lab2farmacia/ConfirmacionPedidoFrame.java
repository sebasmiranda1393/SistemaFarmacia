package com.example.lab2farmacia;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfirmacionPedidoFrame extends JFrame {
    public ConfirmacionPedidoFrame() {

        setTitle("Confirmación de Pedido");
        setSize(1000, 600);
        Color amarillo = new Color(255, 201, 51);


        JLabel confirmacionLabel = new JLabel("Pedido confirmado y enviado.");
        JButton cerrarButton = new JButton("Cerrar");
        cerrarButton.setBackground(amarillo);
        cerrarButton.setForeground(Color.black);

        cerrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        JPanel confirmacionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        confirmacionPanel.add(confirmacionLabel);
        confirmacionPanel.add(cerrarButton);

        add(confirmacionPanel);

        setVisible(true);
    }
}
