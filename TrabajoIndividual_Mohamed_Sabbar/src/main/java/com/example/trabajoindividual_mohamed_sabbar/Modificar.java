package com.example.trabajoindividual_mohamed_sabbar;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Optional;

public class Modificar {
    @FXML
    private TextField modificarID;
    @FXML
    private TextField modificarNombre;
    @FXML
    private TextField modificarPuesto;
    @FXML
    private TextField modificarSalario;
    @FXML
    private Button botonCancelar;
    @FXML
    private Button botonEditar;

    private Trabajador trabajadorSeleccionado;
    private Stage stage;

    public void init(Trabajador trabajador) {
        this.trabajadorSeleccionado = trabajador;
        this.stage = stage;

        // Mostrar los datos del trabajador en los campos de texto
        modificarID.setText(String.valueOf(trabajador.getId()));
        modificarNombre.setText(trabajador.getNombre());
        modificarPuesto.setText(trabajador.getPuesto());
        modificarSalario.setText(String.valueOf(trabajador.getSalario()));
    }

    @FXML
    private void cancelar() {
        Stage stage = (Stage) botonCancelar.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void guardarCambios() {
        // Obtener los nuevos valores de los campos de texto
        String nuevoNombre = modificarNombre.getText();
        String nuevoPuesto = modificarPuesto.getText();
        int nuevoSalario = Integer.parseInt(modificarSalario.getText());

        // Actualizar los datos del trabajador seleccionado
        trabajadorSeleccionado.setNombre(nuevoNombre);
        trabajadorSeleccionado.setPuesto(nuevoPuesto);
        trabajadorSeleccionado.setSalario(nuevoSalario);

        // Guardar los cambios en la base de datos
        DBUtils.actualizarTrabajador(trabajadorSeleccionado);

        // Cerrar la ventana de modificación
        stage.close();
    }
    @FXML
    private void editar() {
        // Verificar que hay un trabajador seleccionado
        if (trabajadorSeleccionado != null) {
            String nuevoNombre = modificarNombre.getText();
            String nuevoPuesto = modificarPuesto.getText();
            int nuevoSalario = Integer.parseInt(modificarSalario.getText());
            trabajadorSeleccionado.setNombre(nuevoNombre);
            trabajadorSeleccionado.setPuesto(nuevoPuesto);
            trabajadorSeleccionado.setSalario(nuevoSalario);

            //
            Stage stage = (Stage) botonEditar.getScene().getWindow();
            stage.close();
        } else {
            // Manejar la situación en la que no hay un trabajador seleccionado
            System.out.println("No se ha seleccionado ningún trabajador para editar.");
        }
    }

    @FXML
    private void modificarNombre() {
        String nuevoNombre = obtenerNuevoNombre();
        modificarNombre.setText(nuevoNombre);
    }

    private String obtenerNuevoNombre() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Modificar nombre");
        dialog.setHeaderText("Ingrese el nuevo nombre:");
        dialog.setContentText("Nombre:");
        dialog.initOwner(stage);
        dialog.initModality(Modality.WINDOW_MODAL);
        Optional<String> result = dialog.showAndWait();
        return result.orElse("");
    }

    @FXML
    private void modificarSalario() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Modificar salario");
        dialog.setHeaderText("Ingrese el nuevo salario:");
        dialog.setContentText("Salario:");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(nuevoSalario -> modificarSalario.setText(nuevoSalario));
    }

    @FXML
    private void modificarPuesto() {
        ChoiceDialog<String> dialog = new ChoiceDialog<>("Puesto actual", "Puesto 1", "Puesto 2", "Puesto 3");
        dialog.setTitle("Modificar puesto");
        dialog.setHeaderText("Seleccione el nuevo puesto:");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(nuevoPuesto -> modificarPuesto.setText(nuevoPuesto));
    }
}
