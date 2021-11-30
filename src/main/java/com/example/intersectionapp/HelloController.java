package com.example.intersectionapp;

import eu.hansolo.tilesfx.addons.Switch;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.util.*;
import java.util.stream.Collectors;

public class HelloController {

    @FXML
    private TextField sizeListA;

    @FXML
    private TextField sizeListB;

    @FXML
    private Switch SwitchAorB;

    @FXML
    private TextArea ListAContent;

    @FXML
    private TextArea ListBContent;

    @FXML
    private TextArea ListIntersectionContent;

    @FXML
    private Label ExecutionTimeLabel;

    @FXML
    protected void onHelloButtonClick() {

        int min = 0;
        int max = 100;

        Random r = new Random();

        List<Integer> listA = new ArrayList<>();
        List<Integer> listB = new ArrayList<>();

        try {
            int sizeA = Integer.parseInt(sizeListA.getText());
            int sizeB = Integer.parseInt(sizeListB.getText());

            for (int i = 0; i < sizeA; i++) {
                listA.add(r.nextInt((max - min)));
            }

            for (int i = 0; i < sizeB; i++) {
                listB.add(r.nextInt((max - min)));
            }
        }
         catch (NumberFormatException exception) {
             Alert alert = new Alert(Alert.AlertType.WARNING);
             alert.setTitle("Warnung!");
             alert.setHeaderText("Formatfehler");
             alert.setContentText("Bitte Zahlen eingeben!");
             alert.showAndWait();
        }

        ListAContent.setText(Arrays.toString(listA.toArray()));
        ListBContent.setText(Arrays.toString(listB.toArray()));

        if (SwitchAorB.isActive() == false) {
            long startTime = System.nanoTime();
            HashSet<Integer> listAset = new HashSet<>(listA);

            Set<Integer> IntersectionSet = listAset.stream()
                    .distinct()
                    .filter(listB::contains)
                    .collect(Collectors.toSet());

            ListIntersectionContent.setText(Arrays.toString(IntersectionSet.toArray()));
            System.out.println("A ist aktiv");
            long endTime = System.nanoTime();
            long duration = (endTime - startTime);
            ExecutionTimeLabel.setText("Execution time (List A): " + duration);
        }

        else {
            long startTime = System.nanoTime();
            HashSet<Integer> listBset = new HashSet<>(listB);

            Set<Integer> IntersectionSet = listBset.stream()
                    .distinct()
                    .filter(listA::contains)
                    .collect(Collectors.toSet());

            ListIntersectionContent.setText(Arrays.toString(IntersectionSet.toArray()));
            System.out.println("B ist aktiv");
            long endTime = System.nanoTime();
            long duration = (endTime - startTime);
            ExecutionTimeLabel.setText("Execution time (List B): " + duration);
        }

    }
}