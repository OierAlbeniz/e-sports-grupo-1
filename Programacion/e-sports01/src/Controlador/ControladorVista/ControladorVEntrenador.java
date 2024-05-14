package Controlador.ControladorVista;

import Vista.Staff;

public class ControladorVEntrenador {
        private Staff st;

        public ControladorVEntrenador(){
          try{
              if (st.getAltaStaff().isEnabled()){
                  st.getPanelModificacion().setVisible(false);
              }
          }catch (Exception e){

          }
        }

}
