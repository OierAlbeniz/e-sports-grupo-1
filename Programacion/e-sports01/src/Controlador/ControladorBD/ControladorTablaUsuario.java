    package Controlador.ControladorBD;

    import Modelo.Usuario;
    import Vista.VentanaInicioSesion;
    import Vista.VentanaPrincipal;

    import javax.swing.*;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    import java.sql.Connection;
    import java.sql.PreparedStatement;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.time.LocalDate;

    public class ControladorTablaUsuario {
        private Connection con;
        private VentanaInicioSesion vsesion;
        private String nombreUsuarioActual;
        private VentanaPrincipal vp;

        public ControladorTablaUsuario(Connection con) {
            this.con = con;
            this.vp = vp;
            this.nombreUsuarioActual = nombreUsuarioActual;
        }

        public Usuario buscarUsuario(String nombre,String password) throws Exception {
            Usuario user = null;

            String plantilla = "SELECT nombre, contrasena, tipo FROM usuario WHERE nombre=? ";

            PreparedStatement buscarUsuario = con.prepareStatement(plantilla);
            buscarUsuario.setString(1, nombre);

            ResultSet rs = buscarUsuario.executeQuery();

            if (rs.next()) {
                user = new Usuario();
                user.setNombre(rs.getString("nombre"));
                user.setContrasena(rs.getString("contrasena"));
                user.setTipo(rs.getString("tipo"));



            } else {
                System.out.println("Usuario no encontrado.");
            }

            rs.close();
            buscarUsuario.close();

            return user;
        }

        class CerrarSesionListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String updateStatus = "UPDATE usuario SET estado = ? WHERE nombre = ?";
                    PreparedStatement ps = con.prepareStatement(updateStatus);
                    ps.setString(1, "offline");
                    ps.setString(2, nombreUsuarioActual);
                    ps.executeUpdate();
                    ps.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                vp.dispose();  // Cierra la ventana principal
                vsesion = new VentanaInicioSesion();  // Crea una nueva instancia de la ventana de inicio de sesión
                vsesion.setVisible(true);  // Muestra la ventana de inicio de sesión
            }
        }

        //creacion de ususario

        public Usuario crearUsuario(String nombre, String contrasena, String tipoUsuario) throws Exception {
            Usuario user = null;

            String plantilla = "SELECT nombre, contrasena, tipo FROM usuario WHERE nombre=? ";

            PreparedStatement buscarUsuario = con.prepareStatement(plantilla);
            buscarUsuario.setString(1, nombre);

            ResultSet rs = buscarUsuario.executeQuery();

            if (rs.next()) {
                user = new Usuario();
                user.setNombre(rs.getString("nombre"));
                user.setContrasena(rs.getString("contrasena"));

                // Mostrar el usuario encontrado por pantalla
                JOptionPane.showMessageDialog(null, "El usuario ya existe");

            } else {
                String plantillaCreateUsuario = "INSERT INTO USUARIO (NOMBRE,CONTRASENA,TIPO) VALUES (?,?,?)";
                PreparedStatement crearUsuario = con.prepareStatement(plantillaCreateUsuario);
                crearUsuario.setString(1, nombre);
                crearUsuario.setString(2, contrasena);
                crearUsuario.setString(3, tipoUsuario);
                crearUsuario.executeUpdate();

                JOptionPane.showMessageDialog(null, "el usuario " + nombre + " se ha insertado correctamente");
            }

            rs.close();
            buscarUsuario.close();

            return user;
        }




    }