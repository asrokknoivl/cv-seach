package fr.univ_lyon1.info.m1.cv_search.view;
import java.io.File;

import fr.univ_lyon1.info.m1.cv_search.controller.Controller;
import fr.univ_lyon1.info.m1.cv_search.model.*;
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

public class View extends ViewObserver {
    private Stage stage;
    private int width;
    private int height;
    private ComboBox strategyBox; //Combobox used to display the filtering strategies
    private HBox searchSkillsBox = new HBox(); //
    private VBox resultBox = new VBox();

    //constructor
    public View(Controller ctrl, Stage stage, int width, int height){
        this.ctrl = ctrl;
        this.ctrl.attach(this);
        this.stage = stage;
        this.width = width;
        this.height = height;
        strategyBox = createStrategySelectionWidget();
    }
    //main display function
    public void show(){
        stage.setTitle("Search for CV");

        VBox root = new VBox();

        Node newSkillBox = createNewSkillWidget();
        root.getChildren().add(newSkillBox);

        updateSkills();
        Node searchSkillsBox = getSearchSkillsBox();
        root.getChildren().add(searchSkillsBox);

        Node strategySet = getStrategySet();
        root.getChildren().add(strategySet);

        Node searchButton = createSearchWidget();
        root.getChildren().add(searchButton);

        Node resultBox = getResultBox();
        root.getChildren().add(resultBox);

        Scene scene = new Scene(root, width, height);
        stage.setScene(scene);
        stage.show();
    }

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
                ctrl.add("s", new Skill(text));
                updateSkills();
                textField.setText("");
                textField.requestFocus();

            }
        };
        submitButton.setOnAction(skillHandler);
        textField.setOnAction(skillHandler);
        return newSkillBox;
    }

    public Node getSearchSkillsBox(){
        return searchSkillsBox;
    }

    @Override
    public void updateSkills(){
        searchSkillsBox.getChildren().clear();
        for (Skill skill : (SkillList) ctrl.get("s")){
            Button button = new Button(skill.getSkill());
            searchSkillsBox.getChildren().add(button);
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    ctrl.remove("s", skill);
                }
            });
        }
    }


    private ComboBox createStrategySelectionWidget() {
        ComboBox strategyBox = new ComboBox();
        for (Strategy s : (StrategyList) ctrl.get("st")){
            strategyBox.getItems().add(s.getStrategy());
        }
        strategyBox.setOnHidden((event) -> {
            ctrl.set("cs", new Strategy((String) strategyBox.getValue()));
        });

        return strategyBox;
    }
    private Node getStrategySet(){
        HBox strategySet = new HBox();
        Label strategy = new Label("Strategy:");
        strategySet.getChildren().addAll(strategy, strategyBox);
        return strategySet;
    }
    @Override
    public void updateStrategy(){
        strategyBox.setValue(ctrl.get("css"));
        show();
    }

    private Node createSearchWidget() {
        Button search = new Button("Search");
        search.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ctrl.execute("filter", (Strategy) ctrl.get("cs"));
                System.out.println("filterd");
            }
        });
        return search;
    }


    private Node getResultBox(){
        return resultBox;
    }


    @Override
    public void updateApplicants() {
        resultBox.getChildren().clear();
        for (Applicant a : (ApplicantList)ctrl.get("r")){
            resultBox.getChildren().addAll(new Label(a.getName()), new Label(Double.toString(a.getMoyenne())));
            resultBox.setSpacing(5);
        }
        show();
    }

}
