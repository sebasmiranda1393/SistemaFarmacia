package com.example.lab2farmacia;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FarmaciaApp extends JFrame implements ActionListener {
    private JTextField nombreMedicamentoField, cantidadField;
    private JComboBox<String> tipoMedicamentoComboBox;
    private JRadioButton cofarmaRadio, empsepharRadio, cemefarRadio;
    private JCheckBox principalCheckBox, secundariaCheckBox;

    public FarmaciaApp() {
        setTitle("Sistema de Pedidos de Farmacia");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE); // Establecer el fondo en blanco

        JLabel nombreMedicamentoLabel = new JLabel("Nombre del Medicamento:");
        nombreMedicamentoField = new JTextField(20);

        JLabel tipoMedicamentoLabel = new JLabel("Tipo del Medicamento:");
        String[] tipos = {"Analgésico", "Analéptico", "Anestésico", "Antiácido", "Antidepresivo", "Antibiótico"};
        tipoMedicamentoComboBox = new JComboBox<>(tipos);

        JLabel cantidadLabel = new JLabel("Cantidad de Producto:");
        cantidadField = new JTextField(5);

        JLabel distribuidorLabel = new JLabel("Distribuidor Farmacéutico:");
        cofarmaRadio = new JRadioButton("Cofarma");
        empsepharRadio = new JRadioButton("Empsephar");
        cemefarRadio = new JRadioButton("Cemefar");
        ButtonGroup distribuidorGroup = new ButtonGroup();
        distribuidorGroup.add(cofarmaRadio);
        distribuidorGroup.add(empsepharRadio);
        distribuidorGroup.add(cemefarRadio);

        JLabel sucursalLabel = new JLabel("Sucursal de la Farmacia:");
        principalCheckBox = new JCheckBox("Principal");
        secundariaCheckBox = new JCheckBox("Secundaria");

        Color rojo = new Color(255, 51, 51); // Rojo personalizado
        Color verde = new Color(73, 153, 15); // Naranja personalizado

        JButton borrarButton = new JButton("Borrar");
        borrarButton.setBackground(rojo);
        borrarButton.setForeground(Color.WHITE);
        JButton confirmarButton = new JButton("Confirmar");
        confirmarButton.setBackground(verde);
        confirmarButton.setForeground(Color.WHITE);

        borrarButton.addActionListener(this);
        confirmarButton.addActionListener(this);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(borrarButton);
        buttonPanel.add(confirmarButton);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridwidth = 2;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(nombreMedicamentoLabel, gbc);

        gbc.gridy = 1;
        panel.add(tipoMedicamentoLabel, gbc);

        gbc.gridy = 2;
        panel.add(cantidadLabel, gbc);

        gbc.gridy = 3;
        panel.add(distribuidorLabel, gbc);

        gbc.gridy = 4;
        panel.add(sucursalLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 2;
        gbc.gridy = 0;
        panel.add(nombreMedicamentoField, gbc);

        gbc.gridy = 1;
        panel.add(tipoMedicamentoComboBox, gbc);

        gbc.gridy = 2;
        panel.add(cantidadField, gbc);

        gbc.gridy = 3;
        panel.add(cofarmaRadio, gbc);
        gbc.gridx = 3;
        panel.add(empsepharRadio, gbc);
        gbc.gridx = 4;
        panel.add(cemefarRadio, gbc);

        gbc.gridy = 4;
        gbc.gridx = 2;
        panel.add(principalCheckBox, gbc);
        gbc.gridx = 3;
        panel.add(secundariaCheckBox, gbc);


        gbc.gridwidth = 5;
        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(buttonPanel, gbc);

        // Agregar el panel al centro de la ventana principal
        getContentPane().add(panel, BorderLayout.CENTER);

        // Mostrar la ventana
        setVisible(true);
    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FarmaciaApp());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Borrar")) {
            // Borrar todos los campos
            nombreMedicamentoField.setText("");
            cantidadField.setText("");
            tipoMedicamentoComboBox.setSelectedIndex(0);
            cofarmaRadio.setSelected(false);
            empsepharRadio.setSelected(false);
            cemefarRadio.setSelected(false);
            principalCheckBox.setSelected(false);
            secundariaCheckBox.setSelected(false);
        } else if (e.getActionCommand().equals("Confirmar")) {
            // Validar y procesar el pedido
            if (validarPedido()) {
                procesarPedido();
            }
        }
    }

    private boolean validarPedido() {
        // Validar los datos del pedido según las reglas especificadas
        String nombreMedicamento = nombreMedicamentoField.getText();
        String cantidad = cantidadField.getText();

        if (nombreMedicamento.isEmpty() || !nombreMedicamento.matches("^[a-zA-Z0-9\\s]+$")) {
            mostrarMensajeError("Nombre de medicamento inválido.");
            return false;
        }

        if (tipoMedicamentoComboBox.getSelectedIndex() == -1) {
            mostrarMensajeError("Seleccione un tipo de medicamento.");
            return false;
        }

        if (cantidad.isEmpty() || !cantidad.matches("^\\d+$")) {
            mostrarMensajeError("Cantidad debe ser un número entero positivo.");
            return false;
        }

        if (!cofarmaRadio.isSelected() && !empsepharRadio.isSelected() && !cemefarRadio.isSelected()) {
            mostrarMensajeError("Seleccione un distribuidor.");
            return false;
        }

        if (!principalCheckBox.isSelected() && !secundariaCheckBox.isSelected()) {
            mostrarMensajeError("Seleccione una sucursal.");
            return false;
        }

        // Si todas las validaciones son exitosas, retornar true
        return true;
    }

    private void mostrarMensajeError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
    private void procesarPedido() {
        String nombreMedicamento = nombreMedicamentoField.getText();
        String tipoMedicamento = (String) tipoMedicamentoComboBox.getSelectedItem();
        int cantidad = Integer.parseInt(cantidadField.getText());
        String distribuidor = "";

        if (cofarmaRadio.isSelected()) {
            distribuidor = "Cofarma";
        } else if (empsepharRadio.isSelected()) {
            distribuidor = "Empsephar";
        } else if (cemefarRadio.isSelected()) {
            distribuidor = "Cemefar";
        }

        String sucursales = "";
        if (principalCheckBox.isSelected()) {
            sucursales += "Principal";
        }
        if (secundariaCheckBox.isSelected()) {
            if (!sucursales.isEmpty()) {
                sucursales += " y ";
            }
            sucursales += "Secundaria";
        }

        // Crear una nueva ventana de resumen y mostrar el resumen del pedido
        ResumenPedidoFrame resumenFrame = new ResumenPedidoFrame(nombreMedicamento, tipoMedicamento, cantidad, distribuidor, sucursales);

        // Simular el envío del pedido
        System.out.println("Pedido enviado");
    }
}
