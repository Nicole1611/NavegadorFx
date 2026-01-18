package com.mycompany.navegadorfx.controller;

import com.mycompany.navegadorfx.model.Pagina;
import com.mycompany.navegadorfx.view.StackCanvas;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;
import java.util.List;

public class StackController {

    @FXML
    private TextField txtValue;
    @FXML
    private StackPane canvasHolder;
    private StackCanvas canvas;

    @FXML
    private Label lblStatus;

    private Pagina navegador;

    @FXML
    private void initialize() {
        navegador = new Pagina("inicio.com"); // página inicial
        canvas = new StackCanvas();
        canvasHolder.getChildren().add(canvas);

        canvas.clearHighlight();
        refreshView();
    }

    @FXML
    private void nuevaPagina() {
        String url = txtValue.getText().trim();
        if (url.isEmpty()) return;

        navegador.nuevaPagina(url);
        lblStatus.setText("Página actual: " + navegador.getActual());
        txtValue.clear();
        txtValue.requestFocus();
        refreshView();
    }

    @FXML
    private void atras() {
        boolean ok = navegador.navegarAtras();
        lblStatus.setText(ok ? "Página actual: " + navegador.getActual() : "No hay páginas atrás");
        refreshView();
    }

    @FXML
    private void adelante() {
        boolean ok = navegador.navegarAdelante();
        lblStatus.setText(ok ? "Página actual: " + navegador.getActual() : "No hay páginas adelante");
        refreshView();
    }

    private void refreshView() {
        List<String> listaParaCanvas = new ArrayList<>();

        // Primero las páginas atrás (de la más antigua a la más reciente)
        listaParaCanvas.addAll(navegador.getAtras());

        // Luego la página actual
        listaParaCanvas.add(navegador.getActual());

        // Finalmente las páginas adelante
        listaParaCanvas.addAll(navegador.getAdelante());

        canvas.setValues(listaParaCanvas);
        canvas.render();
    }
}

