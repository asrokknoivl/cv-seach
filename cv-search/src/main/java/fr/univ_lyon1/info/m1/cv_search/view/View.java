package fr.univ_lyon1.info.m1.cv_search.view;

import fr.univ_lyon1.info.m1.cv_search.controller.Controller;


import fr.univ_lyon1.info.m1.cv_search.model.Applicant;
import fr.univ_lyon1.info.m1.cv_search.model.ApplicantList;
import fr.univ_lyon1.info.m1.cv_search.model.Experience;
import fr.univ_lyon1.info.m1.cv_search.model.ExperienceList;
import fr.univ_lyon1.info.m1.cv_search.model.Skill;
import fr.univ_lyon1.info.m1.cv_search.model.SkillList;
import fr.univ_lyon1.info.m1.cv_search.model.Strategy;
import fr.univ_lyon1.info.m1.cv_search.model.StrategyList;
import fr.univ_lyon1.info.m1.cv_search.model.ViewObserver;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class View extends ViewObserver {
    private Stage stage;
    private int width;
    private int height;
    private ComboBox strategyBox; //Combobox used to display the filtering strategies
    private HBox searchSkillsBox = new HBox(); //
    private VBox resultBox = new VBox();
    private VBox expBox = new VBox();
    private HBox professionalExpBox = new HBox();
    private ScrollPane sp;
    private Controller ctrl;

    //constructor
    public View(Controller ctrl, Stage stage, int width, int height) {
        this.ctrl = ctrl;
        this.ctrl.attach(this);
        this.stage = stage;
        this.width = width;
        this.height = height;
        strategyBox = createStrategySelectionWidget();
    }

    //main display function
    public void show() {
        stage.setTitle("Search for CV");
        VBox skills = new VBox();
        VBox exps = new VBox();
        VBox root = new VBox();
        VBox exp = new VBox();
        VBox res = new VBox();

        Node newSkillBox = createNewSkillWidget();
        Node titleSkills = new Text("Added Skills");
        Node searchSkillsBox = getSearchSkillsBox();
        skills.setSpacing(10);
        skills.getChildren().addAll(newSkillBox, titleSkills, searchSkillsBox);

        Node strategySet = getStrategySet();

        Node experienceWidget = createExperienceWidget();
        Node titleExps = new Text("Added Experiences");
        Node experienceBox = getExpBox();
        exps.setSpacing(10);
        exps.getChildren().addAll(experienceWidget, titleExps, experienceBox);

        root.getChildren().addAll(skills, strategySet, exps);

        Node profExpBox = createProfExpBox();
        Node professionalExperience = getProfessionalExpBox();
        exp.getChildren().addAll(profExpBox, professionalExperience);
        exp.setSpacing(10);

        root.getChildren().add(exp);

        Node titleRes = new Text("Matching Results");
        Node resultBox = getResultBox();
        Node searchButton = createSearchWidget();
        res.setSpacing(10);
        res.getChildren().addAll(titleRes, searchButton, resultBox);

        root.getChildren().add(res);
        root.setSpacing(40);
        root.setPadding(new Insets(30));

        Scene scene = new Scene(new ScrollPane(root), width, height);
        stage.setScene(scene);
        stage.show();
    }

    private Node createNewSkillWidget() {
        HBox box = new HBox();
        HBox newSkillBox = new HBox();
        Label labelSkill = new Label("Skill:");
        TextField textField = new TextField();
        Button submitButton = new Button("Add skill");
        box.getChildren().addAll(labelSkill, textField, submitButton);
        box.setAlignment(Pos.BASELINE_CENTER);
        box.setSpacing(10);
        newSkillBox.getChildren().add(box);
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

    public Node getSearchSkillsBox() {
        return searchSkillsBox;
    }

    @Override
    public void updateSkills() {
        searchSkillsBox.getChildren().clear();
        for (Skill skill : (SkillList) ctrl.get("s")) {
            HBox newSkill = new HBox();
            Label skillLabel = new Label(skill.getSkill());
            Button button = new Button("x");
            button.setPadding(new Insets(8));
            newSkill.setStyle("-fx-padding: 2;" + "-fx-border-style: solid inside;"
                    + "-fx-border-width: 1;" + "-fx-border-insets: 5;"
                    + "-fx-border-radius: 5;" + "-fx-border-color: black;");
            newSkill.setAlignment(Pos.BASELINE_CENTER);
            newSkill.getChildren().addAll(skillLabel, button);
            searchSkillsBox.getChildren().addAll(newSkill);
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    ctrl.remove("s", skill);
                }
            });
        }
        show();
    }


    private ComboBox createStrategySelectionWidget() {
        ComboBox strategyBox = new ComboBox();
        for (Strategy s : (StrategyList) ctrl.get("st")) {
            strategyBox.getItems().add(s.getStrategy());
        }
        strategyBox.setOnHidden((event) -> {
            ctrl.set("cs", new Strategy((String) strategyBox.getValue()));
        });
        strategyBox.setValue(ctrl.get("css"));

        return strategyBox;
    }

    private Node getStrategySet() {
        HBox strategySet = new HBox();
        HBox box = new HBox();
        Label strategy = new Label("Strategy:");
        strategySet.setSpacing(10);
        box.setAlignment(Pos.BASELINE_CENTER);
        box.getChildren().addAll(strategy, strategyBox);
        strategySet.getChildren().add(box);
        return strategySet;
    }

    @Override
    public void updateStrategy() {
        strategyBox.setValue(ctrl.get("css"));
        show();
    }

    private Node createSearchWidget() {
        Button search = new Button("Search");
        search.setAlignment(Pos.BASELINE_CENTER);
        search.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String cs = (String) ctrl.get("css");
                int val = 0;
                val = Integer.parseInt(cs.split(" ")[cs.split(" ").length - 1]);
                String type = cs.split(" ")[0];
                if (type.equals("All")) {
                    ctrl.filterAll(val);
                } else if (type.equals("Average")) {
                    ctrl.filterAvg(val);
                } else if (type.equals("Harmonic")) {
                    ctrl.filterHrmnc(val);
                } else {
                    ctrl.filterAll(val);
                }
            }
        });
        return search;
    }


    private Node getResultBox() {
        return resultBox;
    }


    @Override
    public void updateApplicants() {
        resultBox.getChildren().clear();
        resultBox.setSpacing(10);
        resultBox.getChildren().add(
                new Text(""
                + "****************************"
                + "****************************"
                + "****************************"
                + "*****************************"
                + "******************************"
                + "*************************"
                + "*************"));
        for (Applicant a : (ApplicantList) ctrl.get("r")) {
            HBox name = new HBox();
            HBox avg = new HBox();
            HBox skills = new HBox();
            HBox expBox = new HBox();
            VBox exps = new VBox();
            exps.setSpacing(10);
            name.getChildren().addAll(new Label("Name: "), new Text(a.getName()));
            Text t = new Text(Double.toString(a.getMoyenne()));
            avg.getChildren().addAll(new Label("Average Skills Score (entered skills): "), t);
            skills.getChildren().add(new Label("Skills: "));
            for (String s : a.getSkills().keySet()) {
                skills.getChildren().add(new Text(" " + s + ", "));
            }
            expBox.getChildren().add(new Label("Professional experiences: "));
            for (Experience s : a.getExperiences().getList()) {
                String ts = s.getCompany() + ", " +  s.getStartDate() + ", " + s.getEndDate();
                exps.getChildren().add(new Text(ts));
            }
            expBox.getChildren().add(exps);
            resultBox.getChildren().addAll(name, avg, skills, expBox,
                    new Text(""
                    + "*************************"
                    + "*******************************"
                    + "****************************"
                    + "*****************************"
                    + "******************************"
                    + "*************************"
                    + "*************"));
        }
        show();
    }

    private Node createExperienceWidget() {
        HBox expBox = new HBox();
        HBox box = new HBox();
        Label labelExp = new Label("Experience at: ");
        TextField textFieldE = new TextField();
        Label labelDuration = new Label("Duration (years): ");
        TextField textFieldD = new TextField();
        Button submitButton = new Button("Add Experience");
        box.getChildren().addAll(labelExp, textFieldE, labelDuration, textFieldD, submitButton);
        box.setAlignment(Pos.BASELINE_CENTER);
        box.setSpacing(10);
        expBox.getChildren().add(box);
        EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String company = textFieldE.getText().strip();
                String duration = textFieldD.getText().strip();
                if (company.equals("")) {
                    return;
                }
                if (duration.equals("")) {
                    duration = "undefined";
                }
                ctrl.add("exp", new Experience(company, duration));
                updateExperiences();
                textFieldE.setText("");
                textFieldE.requestFocus();
                textFieldD.setText("");
                textFieldD.requestFocus();

            }
        };
        submitButton.setOnAction(handler);
        textFieldD.setOnAction(handler);
        textFieldE.setOnAction(handler);
        return expBox;
    }

    @Override
    public void updateExperiences() {
        expBox.getChildren().clear();
        for (Experience e : (ExperienceList) ctrl.get("exp")) {
            HBox newExperience = new HBox();
            Text labelCompany = new Text("Experience at: ");
            Text company = new Text(e.getCompany());
            company.setFill(Color.DARKMAGENTA);
            Text labelDuration = new Text("Number of years: ");
            Text duration = new Text(e.getDurationS());
            duration.setFill(Color.DARKMAGENTA);
            Button buttonRemove = new Button("x");
            newExperience.setSpacing(15);
            buttonRemove.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    ctrl.remove("exp", e);
                }
            });
            newExperience.setStyle("-fx-padding: 2;" + "-fx-border-style: solid inside;"
                    + "-fx-border-width: 1;" + "-fx-border-insets: 5;"
                    + "-fx-border-radius: 5;" + "-fx-border-color: black;");
            newExperience.setAlignment(Pos.BASELINE_CENTER);
            newExperience.getChildren().addAll(labelCompany, company);
            newExperience.getChildren().addAll(labelDuration, duration, buttonRemove);
            expBox.getChildren().add(newExperience);
        }
        show();
    }

    private Node getExpBox() {
        return expBox;
    }

    private Node getProfessionalExpBox() {
        professionalExpBox.getChildren().clear();
        int profExp = (int) ctrl.get("pexp");
        Text y = new Text(" years");
        professionalExpBox.getChildren().addAll(new Text(Integer.toString(profExp)), y);
        return professionalExpBox;
    }

    private Node createProfExpBox() {
        HBox box = new HBox();
        Label profExpLabel = new Label("Total professional experience (years):");
        TextField profExpText = new TextField();
        Button button = new Button("change");
        box.setSpacing(10);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int pexp = Integer.parseInt(profExpText.getText());
                ctrl.setProfExp(pexp);
                updateProfExp();
            }
        });
        box.getChildren().addAll(profExpLabel, profExpText, button);
        box.setAlignment(Pos.CENTER_LEFT);
        return box;
    }
    @Override
    public void updateProfExp() {
        show();
    }
}
