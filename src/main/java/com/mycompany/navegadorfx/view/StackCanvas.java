package com.mycompany.navegadorfx.view;

import java.util.Collections;
import java.util.List;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class StackCanvas extends Canvas {

    /** Datos a dibujar: snapshot de los valores (Strings). */
    private List<String> values = Collections.emptyList();

    /** Índice del nodo a resaltar (-1 = ninguno). */
    private int highlightIndex = -1;

    private final double margin = 24;
    private final double nodeW = 120;
    private final double nodeH = 48;
    private final double spacing = 36;
    private final double baseY = 110;

    public StackCanvas() {
        setWidth(1200);
        setHeight(260);
    }

    public void setValues(List<String> values) {
        this.values = (values == null) ? Collections.emptyList() : values;
    }

    public void setHighlightIndex(int idx) {
        this.highlightIndex = idx;
    }

    public void clearHighlight() {
        this.highlightIndex = -1;
    }

    public void render() {
        int n = values.size();
        double neededW = (n == 0) ? 900 : margin * 2 + n * nodeW + (n - 1) * spacing + 180;
        setWidth(Math.max(900, neededW));
        setHeight(260);

        GraphicsContext g = getGraphicsContext2D();

        g.setFill(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setFill(Color.rgb(20, 20, 20));
        g.setFont(Font.font(16));
        g.fillText("Pila de Páginas", margin, 28);

        g.setFill(Color.rgb(90, 90, 90));
        g.setFont(Font.font(12));
        g.fillText("Nodo: [URL | next] → último apunta a null", margin, 48);

        if (n == 0) {
            g.setFill(Color.rgb(120, 120, 120));
            g.setFont(Font.font(14));
            g.fillText("La pila está vacía. Inserta páginas para ver los nodos.", margin, baseY);
            return;
        }

        drawHead(g);

        double x = margin;
        for (int i = 0; i < n; i++) {
            boolean hl = (i == highlightIndex);
            drawNode(g, x, baseY, values.get(i), hl);

            double midY = baseY + nodeH / 2;
            double x2 = x + nodeW + spacing;

            g.setStroke(Color.rgb(60, 60, 60));
            g.setLineWidth(2);
            g.strokeLine(x + nodeW, midY, x2, midY);
            arrowHead(g, x + nodeW, midY, x2, midY);

            if (i == n - 1) {
                g.setFill(Color.rgb(120, 120, 120));
                g.setFont(Font.font(12));
                g.fillText("null", x2 + 10, midY + 4);
            }

            x += nodeW + spacing;
        }
    }

    private void drawHead(GraphicsContext g) {
        double headX = margin;
        double headY = baseY - 45;

        g.setFill(Color.rgb(50, 50, 50));
        g.setFont(Font.font(12));
        g.fillText("head", headX, headY);

        g.setStroke(Color.rgb(60, 60, 60));
        g.setLineWidth(2);
        g.strokeLine(headX + 14, headY + 6, headX + 14, baseY - 8);
        arrowHead(g, headX + 14, baseY - 8, headX + 14, baseY);
    }

    private void drawNode(GraphicsContext g, double x, double y, String text, boolean highlighted) {
        double splitX = x + nodeW * 0.62;

        g.setLineWidth(highlighted ? 3 : 2);
        g.setStroke(highlighted ? Color.rgb(25, 90, 200) : Color.rgb(40, 40, 40));
        g.strokeRoundRect(x, y, nodeW, nodeH, 16, 16);
        g.strokeLine(splitX, y, splitX, y + nodeH);

        g.setFill(Color.rgb(20, 20, 20));
        g.setFont(Font.font(12));
        g.fillText(text, x + 6, y + 30);

        g.setFill(Color.rgb(90, 90, 90));
        g.setFont(Font.font(11));
        g.fillText("next", splitX + 6, y + 28);

        if (highlighted) {
            g.setFill(Color.rgb(25, 90, 200));
            g.setFont(Font.font(11));
            g.fillText("FOUND", x + 6, y - 6);
        }
    }

    private void arrowHead(GraphicsContext g, double x1, double y1, double x2, double y2) {
        double angle = Math.atan2(y2 - y1, x2 - x1);
        double len = 10;
        double a1 = angle - Math.PI / 8;
        double a2 = angle + Math.PI / 8;
        double ax1 = x2 - len * Math.cos(a1);
        double ay1 = y2 - len * Math.sin(a1);
        double ax2 = x2 - len * Math.cos(a2);
        double ay2 = y2 - len * Math.sin(a2);
        g.strokeLine(x2, y2, ax1, ay1);
        g.strokeLine(x2, y2, ax2, ay2);
    }
}
