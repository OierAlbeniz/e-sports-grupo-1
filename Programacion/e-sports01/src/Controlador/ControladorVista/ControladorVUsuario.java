package Controlador.ControladorVista;

import Modelo.Usuario;
import Vista.VistaPerfil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ControladorVUsuario {
    private VistaPerfil vpr;
    private ControladorVista cv;
    public ControladorVUsuario(ControladorVista cv) {
        this.cv = cv;
    }



    public void crearMostrarUsuario() {
        vpr = new VistaPerfil();
        vpr.setVisible(true);
        vpr.addBCancelar(new botonCancelar());
        vpr.addCrearUsuario(new crearUsuario());
    }

    public class botonCancelar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            cv.crearMostrarVP();
            vpr.dispose();
        }
    }

    public class crearUsuario implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            try {
                String nombre = vpr.getTxtNombre().getText();
                String contrasena = vpr.getTxtContrasena().getText();


                if (nombre.isEmpty() || contrasena.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Los datos de entrada son obligatorios");
                    return; // Agrega un return para salir del método en caso de datos faltantes
                }

                String expresionRegular = "^[A-Za-z]{3,}$";
                Pattern patron = Pattern.compile(expresionRegular);
                Matcher matcher = patron.matcher(nombre);
                if (!matcher.matches()) {
                    JOptionPane.showMessageDialog(null, "El nombre no tiene el formato adecuado");
                    return; // Agrega un return para salir del método si el nombre no coincide con la expresión regular
                }

                expresionRegular = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[&+=!@#$%^])(?=\\S+$).{7,}$";
                patron = Pattern.compile(expresionRegular);
                matcher = patron.matcher(contrasena);
                if (!matcher.matches()) {
                    JOptionPane.showMessageDialog(null, "La contraseña no tiene el formato adecuado" +
                            " \n recuerda que tiene que tener al menos 7 caracteres" +
                            " \n una mayuscula" +
                            "\n un numero " +
                            "\n y un caracter especial");
                    return; // Agrega un return para salir del método si la contraseña no coincide con la expresión regular
                    }else {
                        String tipoUsuario = null;

                        if (vpr.getRdbUsuario().isSelected()) {
                             tipoUsuario = "usuario";
                         } else if (vpr.getRdbAdmin().isSelected()) {
                            tipoUsuario = "administrador";
                        }
                        Usuario user = cv.crearUsuario(nombre, contrasena, tipoUsuario);
                    }
                } catch (Exception ex) {
               }
        }

        }
    }






