package fr.univ_lyon1.info.m1.cv_search.view;

import java.io.File;

import fr.univ_lyon1.info.m1.cv_search.model.Applicant;
import fr.univ_lyon1.info.m1.cv_search.model.ApplicantList;
import fr.univ_lyon1.info.m1.cv_search.model.ApplicantListBuilder;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class JfxView {
    private HBox searchSkillsBox;
    private VBox resultBox;
    private ComboBox<String> comboBox;
    /**
     * Create the main view of the application.
     */
    public JfxView(Stage stage, int width, int height) {
        // Name of window
        stage.setTitle("Search for CV");

        VBox root = new VBox();

        Node newSkillBox = createNewSkillWidget();
        root.getChildren().add(newSkillBox);

        Node searchSkillsBox = createCurrentSearchSkillsWidget();
        root.getChildren().add(searchSkillsBox);

        Node strategyBox = createStrategySelection();
        root.getChildren().add(strategyBox);

        Node search = createSearchWidget();
        root.getChildren().add(search);

        Node resultBox = createResultsWidget();
        root.getChildren().add(resultBox);

        // Everything's ready: add it to the scene and display it
        Scene scene = new Scene(root, width, height);
        stage.setScene(scene);
        stage.show();
    }


    /**
     * Create the text field to enter a new skill.
     */
    private Node createNewSkillWidget() {
        HBox newSkillBox = new HBox();
        Label labelSkill = new Label("Skill:");
        TextField textField = new TextField();
        Button submitButton = new Button("Add skill");
        newSkillBox.getChildren().addAll(labelSkill, textField, submitButton);
        newSkillBox.setSpacing(10);

        EventHandler<ActionEvent> skillHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String text = textField.getText().strip();
                if (text.equals("")) {
                    return; // Do nothing
                }

                Button skillBtn = new Button(text);
                searchSkillsBox.getChildren().add(skillBtn);
                skillBtn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        searchSkillsBox.getChildren().remove(skillBtn);
                    }
                });

                textField.setText("");
                textField.requestFocus();
            }
        };
        submitButton.setOnAction(skillHandler);
        textField.setOnAction(skillHandler);
        return newSkillBox;
    }

    /**
     * Create the widget showing the list of applicants.
     */
    private Node createResultsWidget() {
        resultBox = new VBox();
        return resultBox;
    }

    /**
     * Create the widget used to trigger the search.
     */
    private Node createSearchWidget() {
        Button search = new Button("Search");
        search.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // TODO
                ApplicantList listApplicants = new ApplicantListBuilder(new File(".")).build();
                String strategy = comboBox.getValue();
                String[] s = strategy.split(" ");
                boolean all = (s[0].equals("All"));
                int n = Integer.parseInt(s[2]);
                resultBox.getChildren().clear();
                for (Applicant a : listApplicants) {
                    double moyenne = 0;
                    boolean selected = true;
                    double nom = 0;
                    double denom = 0;
                    for (Node skill : searchSkillsBox.getChildren()) {
                        String skillName = ((Button) skill).getText();
                        nom += a.getSkill(skillName);
                        denom += 1;
                        if (a.getSkill(skillName) < n) {
                            selected = false;
                        }
                        moyenne = nom / denom;

                    }
                    if (!all) {
                        if (moyenne < 50) {
                            selected = false;
                        } else {
                            selected = true;
                        }
                    }
                    if (selected) {
                        String m = Double.toString(moyenne);
                        resultBox.getChildren().addAll(new Label(a.getName()), new Label(m));
                    }
                }
            }
        });
        return search;
    }

    /**
     * Create the widget showing the list of skills currently searched
     * for.
     */
    private Node createCurrentSearchSkillsWidget() {
        searchSkillsBox = new HBox();
        return searchSkillsBox;
    }

    private Node createStrategySelection() {
        HBox strategyBox = new HBox();
        Label strategy = new Label("Strategy:");
        comboBox = new ComboBox<>();
        comboBox.getItems().addAll(
                "All >= 50",
                "All >= 60",
                "Average >= 50"
        );
        strategyBox.getChildren().addAll(strategy, comboBox);
        strategyBox.setSpacing(5);
        return strategyBox;
    }



}
