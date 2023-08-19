import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import javafx.scene.control.MenuItem;
import javafx.util.Duration;

public class JavaFXTemplate extends Application {

	HashMap<String,Scene> sceneMap = new HashMap<>();
    ArrayList<Button> buttonList = new ArrayList<>();
	HashSet<Integer> selectedNumbers = new HashSet<>();
	HashSet<Integer> autopickedNumbers = new HashSet<>();
    HashSet<Integer> winningNumbers = new HashSet<>();
    ArrayList<Button> resButtons = new ArrayList<>();
    GridPane resGrid = new GridPane();
	HashSet<Integer> selected = new HashSet<>();

    int numSpots = 0;
	int numDraws = 0;
	int totalWon = 0;
    int correctSelections = 0;
	int curRound = 1;
    boolean AutoPick = false;
	int colorScheme = 0;



	public static void main(String[] args) {
		launch(args);
	}


	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Keno");

		// Create Scenes and add to map
		sceneMap.put("welcome", createWelcomeScene(primaryStage));
		sceneMap.put("rules", createRulesScene(primaryStage));
		sceneMap.put("odds", createOddsScene(primaryStage));
		sceneMap.put("play", createPlayScene(primaryStage));
        sceneMap.put("results", createResultsScene(primaryStage));


		// Start on Welcome Screen
		primaryStage.setScene(sceneMap.get("welcome"));
		primaryStage.show();
	}

	public Scene createWelcomeScene(Stage primaryStage) {
		BorderPane pane = new BorderPane();

		// MENU
		MenuBar menuBar = new MenuBar();
		Menu menu = new Menu("Menu");

		MenuItem rules = new MenuItem("Rules");
		MenuItem odds = new MenuItem("Odds");
		MenuItem exit = new MenuItem("Exit");

		rules.setOnAction(e->primaryStage.setScene(sceneMap.get("rules")));
		odds.setOnAction(e->primaryStage.setScene(sceneMap.get("odds")));
		exit.setOnAction(e->Platform.exit());
		menu.getItems().addAll(rules, odds, exit);
		menuBar.getMenus().add(menu);

		// Page Items
		Text t = new Text("Welcome to Keno!");
		Button start = new Button("Start Game!");
		t.setFont(Font.font ("Comic Sans MS", 50));

		start.setOnAction(e-> primaryStage.setScene(sceneMap.get("play")));

		VBox bar = new VBox(menuBar);
        VBox view = new VBox(50, t, start);
        VBox page = new VBox(250, bar, view);
        view.setAlignment(Pos.CENTER);
		pane.setCenter(page);
		pane.setStyle("-fx-background-color: darkcyan");

		return new Scene(pane, 700, 700);
	}


	public Scene createRulesScene(Stage primaryStage) {
		BorderPane pane = new BorderPane();

		// MENU
		MenuBar menuBar = new MenuBar();
		Menu menu = new Menu("Menu");

		MenuItem home = new MenuItem("Home");
		MenuItem odds = new MenuItem("Odds");
		MenuItem exit = new MenuItem("Exit");

		home.setOnAction(e->primaryStage.setScene(sceneMap.get("welcome")));
		odds.setOnAction(e->primaryStage.setScene(sceneMap.get("odds")));
		exit.setOnAction(e->Platform.exit());
		menu.getItems().addAll(home, odds, exit);
		menuBar.getMenus().add(menu);

		// Page Items
		Text title = new Text("Rules of Keno");
		title.setFont(Font.font ("Comic Sans MS", 50));

		Text rule1 = new Text("1) Select how many spots to play, how many you match determines how much you win");
		Text rule2 = new Text("2) Select number of draws, consecutive draws extend the fun");
		Text rule3 = new Text("3) Pick as many numbers as you did spots. 20 numbers will be drawn out of 80 numbers each drawing");
		Text rule4 = new Text("4) Submit your card to view the winning numbers!");
		Text rule5 = new Text("5) Continue to next drawing or start a new game!");

		rule1.setFont(Font.font ("Times New Roman", 14));
		rule2.setFont(Font.font ("Times New Roman", 14));
		rule3.setFont(Font.font ("Times New Roman", 14));
		rule4.setFont(Font.font ("Times New Roman", 14));
		rule5.setFont(Font.font ("Times New Roman", 14));

		VBox view = new VBox(40, menuBar, title, rule1, rule2, rule3, rule4, rule5);
		view.setAlignment(Pos.TOP_CENTER);
		pane.setCenter(view);
		pane.setStyle("-fx-background-color: darkcyan");

		return new Scene(pane, 700, 700);
	}


	public Scene createOddsScene(Stage primaryStage) {
		BorderPane pane = new BorderPane();

		// MENU
		MenuBar menuBar = new MenuBar();
		Menu menu = new Menu("Menu");

		MenuItem home = new MenuItem("Home");
		MenuItem rules = new MenuItem("Rules");
		MenuItem exit = new MenuItem("Exit");

		home.setOnAction(e->primaryStage.setScene(sceneMap.get("welcome")));
		rules.setOnAction(e->primaryStage.setScene(sceneMap.get("rules")));
		exit.setOnAction(e->Platform.exit());
		menu.getItems().addAll(home, rules, exit);
		menuBar.getMenus().add(menu);

		// Page Items
		Text title = new Text("Odds of Winning");
		title.setFont(Font.font ("Comic Sans MS", 50));

		Text mp1 = new Text("Match   Prize");
		Text mp2 = new Text("Match   Prize");
		Text mp3 = new Text("Match   Prize");
		Text mp4 = new Text("Match   Prize");


		Text headerTen = new Text("10 Spot Game");
		Text ten = new Text("10 -> $100,000");
		Text nine = new Text("9 -> $4,250 ");
		Text eight = new Text("8 -> $450");
		Text seven = new Text("7 -> $40");
		Text six = new Text("6 -> $15");
		Text five =  new Text("5 -> $2");
		Text zero = new Text("0 -> $5");
		Text oddTen = new Text("Overall Odds: 1 in 9.05");

		VBox tenOdds = new VBox(headerTen, mp1,ten, nine, eight,seven, six, five, zero, oddTen);
		tenOdds.setAlignment(Pos.CENTER);

		Text headerEight = new Text("8 Spot Game");
		Text ei = new Text("8 -> $10,000");
		Text sev = new Text("7 -> $750");
		Text si = new Text("6 -> $50");
		Text fiv =  new Text("5 -> $12");
		Text four = new Text("0 -> $2");
		Text oddEight = new Text("Overall Odds: 1 in 9.77");

		VBox eightOdds = new VBox(headerEight, mp2, ei, sev, si, fiv, four, oddEight);
		eightOdds.setAlignment(Pos.CENTER);

		Text headerFour = new Text("4 Spot Game");
		Text f = new Text("4 -> $75");
		Text three = new Text("3 -> $5");
		Text two  = new Text("2 -> $1");
		Text oddFour = new Text("Overall Odds: 1 in 3.86");

		VBox fourOdds = new VBox(headerFour, mp3, f, three, two, oddFour);
		fourOdds.setAlignment(Pos.CENTER);

		Text headerOne = new Text("1 Spot Game");
		Text one = new Text("1 -> $2");
		Text oddOne = new Text("Overall Odds: 1 in 4.00");

		VBox oneOdds = new VBox(headerOne, mp4, one, oddOne);
		oneOdds.setAlignment(Pos.CENTER);

		headerTen.setFont(Font.font ("Comic Sans MS", 20));
		headerEight.setFont(Font.font ("Comic Sans MS", 20));
		headerFour.setFont(Font.font ("Comic Sans MS", 20));
		headerOne.setFont(Font.font ("Comic Sans MS", 20));


		VBox odds = new VBox(20, tenOdds, eightOdds, fourOdds, oneOdds);
		VBox view = new VBox(menuBar, title, odds);
		view.setAlignment(Pos.TOP_CENTER);
		pane.setCenter(view);
		pane.setStyle("-fx-background-color: darkcyan");

		return new Scene(pane, 700, 700);
	}


	public Scene createPlayScene(Stage primaryStage) {
		BorderPane pane = new BorderPane();

		// MENU
		MenuBar menuBar = new MenuBar();
		Menu menu = new Menu("Menu");

		MenuItem home = new MenuItem("Home");
		MenuItem rules = new MenuItem("Rules");
		MenuItem newLook = new MenuItem("New Look");
		MenuItem exit = new MenuItem("Exit");

		home.setOnAction(e->primaryStage.setScene(sceneMap.get("welcome")));
		rules.setOnAction(e->primaryStage.setScene(sceneMap.get("rules")));
		//TODO: new look event handler
		newLook.setOnAction(e->{
			if (colorScheme == 0) {
				sceneMap.get("welcome").getRoot().setStyle("-fx-background-color: lightblue");
				sceneMap.get("rules").getRoot().setStyle("-fx-background-color: lightblue");
				sceneMap.get("odds").getRoot().setStyle("-fx-background-color: lightblue");
				sceneMap.get("play").getRoot().setStyle("-fx-background-color: lightblue");
				sceneMap.get("results").getRoot().setStyle("-fx-background-color: lightblue");
				colorScheme = 1;
			}
			else {
				sceneMap.get("welcome").getRoot().setStyle("-fx-background-color: darkcyan");
				sceneMap.get("rules").getRoot().setStyle("-fx-background-color: darkcyan");
				sceneMap.get("odds").getRoot().setStyle("-fx-background-color: darkcyan");
				sceneMap.get("play").getRoot().setStyle("-fx-background-color: darkcyan");
				sceneMap.get("results").getRoot().setStyle("-fx-background-color: darkcyan");
				colorScheme = 0;
			}


		});

		exit.setOnAction(e->Platform.exit());
		menu.getItems().addAll(home, rules, newLook, exit);
		menuBar.getMenus().add(menu);

		// Page Items
		Text title = new Text("Create Your Card!");
		title.setFont(Font.font ("Comic Sans MS", 25));

		Text spotTxt = new Text("Select the number of spots:");
		Button one = new Button("1");
		Button four = new Button("4");
		Button eight = new Button("8");
		Button ten = new Button("10");
		HBox spots = new HBox(10, spotTxt, one, four, eight, ten);
		spots.setAlignment(Pos.CENTER);

		// EventHandlers for spot buttons
		one.setOnAction(e->{
			numSpots = 1;
			one.setStyle("-fx-background-color: mediumspringgreen");
			four.setStyle("");
			eight.setStyle("");
			ten.setStyle("");
		});

		four.setOnAction(e->{
			numSpots = 4;
			four.setStyle("-fx-background-color: mediumspringgreen");
			one.setStyle("");
			eight.setStyle("");
			ten.setStyle("");

		});

		eight.setOnAction(e->{
			numSpots = 8;
			eight.setStyle("-fx-background-color: mediumspringgreen");
			one.setStyle("");
			four.setStyle("");
			ten.setStyle("");
		});

		ten.setOnAction(e->{
			numSpots = 10;
			ten.setStyle("-fx-background-color: mediumspringgreen");
			one.setStyle("");
			four.setStyle("");
			eight.setStyle("");
		});

		Text drawTxt = new Text("Select the number of consecutive draws:");
		Button a = new Button("1");
		Button b = new Button("2");
		Button c = new Button("3");
		Button d = new Button("4");
		HBox draws = new HBox(10, drawTxt, a, b, c, d);
		draws.setAlignment(Pos.CENTER);

		// EventHandlers for draw buttons
		a.setOnAction(e->{
			numDraws = 1;
			a.setStyle("-fx-background-color: mediumspringgreen");
			b.setStyle("");
			c.setStyle("");
			d.setStyle("");
		});

		b.setOnAction(e->{
			numDraws = 2;
			b.setStyle("-fx-background-color: mediumspringgreen");
			a.setStyle("");
			c.setStyle("");
			d.setStyle("");
		});

		c.setOnAction(e->{
			numDraws = 3;
			c.setStyle("-fx-background-color: mediumspringgreen");
			a.setStyle("");
			b.setStyle("");
			d.setStyle("");
		});

		d.setOnAction(e->{
			numDraws = 4;
			d.setStyle("-fx-background-color: mediumspringgreen");
			a.setStyle("");
			b.setStyle("");
			c.setStyle("");
		});

        Button confirmSelections = new Button("Confirm Selections");
        confirmSelections.setVisible(false);
        confirmSelections.setDisable(true);


        // AutoPick
        Text autoTxt = new Text("Auto-Pick Numbers?");
        Button yesAuto = new Button("Yes");
        Button noAuto = new Button("No");
        autoTxt.setVisible(false);
        yesAuto.setVisible(false);
		yesAuto.setDisable(true);
        noAuto.setVisible(false);
		noAuto.setDisable(true);

        yesAuto.setOnAction(e->{
            AutoPick = true;
            yesAuto.setStyle("-fx-background-color: mediumspringgreen");
            noAuto.setStyle("");
            confirmSelections.setVisible(true);
            confirmSelections.setDisable(false);
        });

        noAuto.setOnAction(e->{
            AutoPick = false;
            noAuto.setStyle("-fx-background-color: mediumspringgreen");
            yesAuto.setStyle("");
            confirmSelections.setVisible(true);
            confirmSelections.setDisable(false);
        });


        HBox autopick = new HBox(10, autoTxt, yesAuto, noAuto);
		autopick.setAlignment(Pos.CENTER);

        Button toAuto = new Button("Continue");
        toAuto.setOnAction(e-> {
            autoTxt.setVisible(true);
            yesAuto.setVisible(true);
			yesAuto.setDisable(false);
            noAuto.setVisible(true);
			noAuto.setDisable(false);
        });

		// Submit, Reset, and Redo Buttons
		Button submit = new Button("Submit");
		submit.setVisible(false);
		submit.setDisable(true);

		Button reset = new Button("Reset Card");
		reset.setVisible(false);
		reset.setDisable(true);

		Button redo = new Button("Redo AutoPick");
		redo.setVisible(false);
		redo.setDisable(true);


		reset.setOnAction(e->{
			if (numSpots == 1) {
				one.setStyle("");
			} else if (numSpots == 4) {
				four.setStyle("");
			} else if (numSpots == 8) {
				eight.setStyle("");
			} else if (numSpots == 10) {
				ten.setStyle("");
			}

			if (numDraws == 1) {
				a.setStyle("");
			} else if (numDraws == 2) {
				b.setStyle("");
			} else if (numDraws == 3) {
				c.setStyle("");
			} else if (numDraws == 4) {
				d.setStyle("");
			}

			one.setDisable(false);
			four.setDisable(false);
			eight.setDisable(false);
			ten.setDisable(false);
			a.setDisable(false);
			b.setDisable(false);
			c.setDisable(false);
			d.setDisable(false);
			toAuto.setDisable(false);
			autoTxt.setVisible(false);
			if (AutoPick) {
				yesAuto.setStyle("");
			} else {
				noAuto.setStyle("");
			}
			yesAuto.setVisible(false);
			noAuto.setVisible(false);
			confirmSelections.setDisable(true);
			confirmSelections.setVisible(false);

			for (Button button : buttonList) {
				button.setStyle("");
				button.setDisable(true);
				button.setVisible(false);
			}

			reset.setVisible(false);
			reset.setDisable(true);

			redo.setVisible(false);
			redo.setDisable(true);

			submit.setVisible(false);
			submit.setDisable(true);

			numDraws = 0;
			numSpots = 0;
			selectedNumbers.clear();
			autopickedNumbers.clear();
		});

		redo.setOnAction(e-> {
			autopickedNumbers.clear();
			for (Button z : buttonList) {
				z.setStyle("");
			}
			generateNums();
			for (int i = 0; i < buttonList.size(); i++) {
				if (autopickedNumbers.contains(i + 1)) {
					buttonList.get(i).setStyle("-fx-background-color: mediumspringgreen");
				}
				else {
					buttonList.get(i).setStyle("");
				}
			}
		});

		HBox options = new HBox(10, reset, redo, submit);
		options.setAlignment(Pos.CENTER);

		// GridPane
		int row = 0;
		int col = 0;
        GridPane grid = new GridPane();
		for (int i = 1 ; i < 81; i++) {
			String name = "" + i;
			Button x = new Button(name);
            x.setDisable(true);
            x.setVisible(false);
            buttonList.add(x);

			int I = i;
			x.setOnAction(e-> {
				if (selectedNumbers.contains(I)) {
					selectedNumbers.remove(I);
					x.setStyle("");
				}
				else {
					selectedNumbers.add(I);
					x.setStyle("-fx-background-color: mediumspringgreen");
				}

				submit.setDisable(selectedNumbers.size() != numSpots);

			});

			if (col == 10) {
				row++;
				col = 0;
			}
			grid.add(x, col, row);
			col++;
		}
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);


        confirmSelections.setOnAction(e-> {
            if (AutoPick) {
				buttonList.forEach(button -> button.setVisible(true));
				redo.setDisable(false);

				//autopick and display results
				generateNums();
				for (int i = 0; i < buttonList.size(); i++) {
					if (autopickedNumbers.contains(i + 1)) {
						buttonList.get(i).setStyle("-fx-background-color: mediumspringgreen");
					}
					else {
						buttonList.get(i).setStyle("");
					}
				}
				submit.setDisable(false);
			}
			else {
				buttonList.forEach(button -> {
					button.setDisable(false);
					button.setVisible(true);
				});
				redo.setDisable(true);
			}

            one.setDisable(true);
            four.setDisable(true);
            eight.setDisable(true);
            ten.setDisable(true);
            a.setDisable(true);
            b.setDisable(true);
            c.setDisable(true);
            d.setDisable(true);
            toAuto.setDisable(true);
            yesAuto.setDisable(true);
            noAuto.setDisable(true);
			submit.setVisible(true);
			reset.setVisible(true);
			reset.setDisable(false);
			redo.setVisible(true);

        });

        submit.setOnAction(e->{
            primaryStage.setScene(sceneMap.get("results"));

            int rowNum = 0;
            int colNum = 0;
            for (int i = 1 ; i < 81; i++) {
                Button x = new Button(buttonList.get(i - 1).getText());
                x.setStyle(buttonList.get(i-1).getStyle());
                resButtons.add(x);

                if (colNum == 10) {
                    rowNum++;
                    colNum = 0;
                }
                resGrid.add(x, colNum, rowNum);
                colNum++;
            }

			one.setDisable(false);
			one.setStyle("");
			four.setDisable(false);
			four.setStyle("");
			eight.setDisable(false);
			eight.setStyle("");
			ten.setDisable(false);
			ten.setStyle("");

			a.setDisable(false);
			a.setStyle("");
			b.setDisable(false);
			b.setStyle("");
			c.setDisable(false);
			c.setStyle("");
			d.setDisable(false);
			d.setStyle("");

			toAuto.setDisable(false);
			autoTxt.setVisible(false);
			yesAuto.setVisible(false);
			yesAuto.setStyle("");
			noAuto.setVisible(false);
			noAuto.setStyle("");

			confirmSelections.setDisable(true);
			confirmSelections.setVisible(false);

			for(Button button: buttonList) {
				button.setStyle("");
				button.setDisable(true);
				button.setVisible(false);
			}

			reset.setDisable(true);
			reset.setVisible(false);
			redo.setDisable(true);
			redo.setVisible(false);
			submit.setDisable(true);
			submit.setVisible(false);

        });


		VBox bar = new VBox(menuBar);
		VBox view = new VBox(20, title, spots, draws, toAuto, autopick, confirmSelections, grid, options);
		view.setAlignment(Pos.CENTER);

		VBox page = new VBox(bar, view);
		pane.setCenter(page);
		pane.setStyle("-fx-background-color: darkcyan");

		return new Scene(pane, 700, 700);
	}

	public void generateNums() {
		int min = 1;
		int max = 80;

		while (autopickedNumbers.size() < numSpots) {
			int rand_int = (int)Math.floor(Math.random()*(max-min+1)+min);
			autopickedNumbers.add(rand_int);
		}
	}

    public void generateWinningNums() {
        int min = 1;
        int max = 80;

        while (winningNumbers.size() < 20) {
            int rand_int = (int)Math.floor(Math.random()*(max-min+1)+min);
            winningNumbers.add(rand_int);
        }
    }


    public Scene createResultsScene(Stage primaryStage) {
        BorderPane pane = new BorderPane();
        ArrayList<Integer> winVals = new ArrayList<>();


        // Page Items
        Text title = new Text("Results");
        Button show = new Button("Show Results");
		title.setFont(Font.font ("Comic Sans MS", 50));

		Text matchTxt = new Text("Spots Matched: ");
		Text numMatched = new Text();
		HBox matched = new HBox(20, matchTxt, numMatched);
		matched.setAlignment(Pos.CENTER);
		matchTxt.setVisible(false);
		numMatched.setVisible(false);

		Text wonTxt = new Text("Current Winnings: ");
		Text wonVal = new Text();
		HBox won = new HBox(20, wonTxt, wonVal);
		won.setAlignment(Pos.CENTER);
		wonTxt.setVisible(false);
		wonVal.setVisible(false);

		Button next = new Button("Next Draw");
		Button newGame = new Button("New Game");
		next.setDisable(true);
		next.setVisible(false);
		newGame.setVisible(false);
		newGame.setDisable(true);
		HBox after = new HBox(20, next, newGame);
		after.setAlignment(Pos.CENTER);

        generateWinningNums();


        // Pauses and Actions
        PauseTransition p1 = new PauseTransition(Duration.seconds(2));
        PauseTransition p2 = new PauseTransition(Duration.seconds(2));
        PauseTransition p3 = new PauseTransition(Duration.seconds(2));
        PauseTransition p4 = new PauseTransition(Duration.seconds(2));
        PauseTransition p5 = new PauseTransition(Duration.seconds(2));
        PauseTransition p6 = new PauseTransition(Duration.seconds(2));
        PauseTransition p7 = new PauseTransition(Duration.seconds(2));
        PauseTransition p8 = new PauseTransition(Duration.seconds(2));
        PauseTransition p9 = new PauseTransition(Duration.seconds(2));
        PauseTransition p10 = new PauseTransition(Duration.seconds(2));
        PauseTransition p11 = new PauseTransition(Duration.seconds(2));
        PauseTransition p12 = new PauseTransition(Duration.seconds(2));
        PauseTransition p13 = new PauseTransition(Duration.seconds(2));
        PauseTransition p14 = new PauseTransition(Duration.seconds(2));
        PauseTransition p15 = new PauseTransition(Duration.seconds(2));
        PauseTransition p16 = new PauseTransition(Duration.seconds(2));
        PauseTransition p17 = new PauseTransition(Duration.seconds(2));
        PauseTransition p18 = new PauseTransition(Duration.seconds(2));
        PauseTransition p19 = new PauseTransition(Duration.seconds(2));
        PauseTransition p20 = new PauseTransition(Duration.seconds(2));



        p1.setOnFinished(e-> {
            int num = winVals.get(0);
            if (selected.contains(num)) {
                resButtons.get(num-1).setStyle("-fx-background-color: yellow");
                correctSelections++;
            }
            else {
                resButtons.get(num-1).setStyle("-fx-background-color: red");
            }

			String val = "" + correctSelections;
			numMatched.setText(val);
            p2.play();
        });

        p2.setOnFinished(e-> {
            int num = winVals.get(1);
            if (selected.contains(num)) {
                resButtons.get(num-1).setStyle("-fx-background-color: yellow");
                correctSelections++;
            }
            else {
                resButtons.get(num-1).setStyle("-fx-background-color: red");
            }

			String val = "" + correctSelections;
			numMatched.setText(val);
            p3.play();
        });

        p3.setOnFinished(e-> {
            int num = winVals.get(2);
            if (selected.contains(num)) {
                resButtons.get(num-1).setStyle("-fx-background-color: yellow");
                correctSelections++;
            }
            else {
                resButtons.get(num-1).setStyle("-fx-background-color: red");
            }

			String val = "" + correctSelections;
			numMatched.setText(val);
            p4.play();
        });

        p4.setOnFinished(e-> {
            int num = winVals.get(3);
            if (selected.contains(num)) {
                resButtons.get(num-1).setStyle("-fx-background-color: yellow");
                correctSelections++;
            }
            else {
                resButtons.get(num-1).setStyle("-fx-background-color: red");
            }

			String val = "" + correctSelections;
			numMatched.setText(val);
            p5.play();
        });

        p5.setOnFinished(e-> {
            int num = winVals.get(4);
            if (selected.contains(num)) {
                resButtons.get(num-1).setStyle("-fx-background-color: yellow");
                correctSelections++;
            }
            else {
                resButtons.get(num-1).setStyle("-fx-background-color: red");
            }

			String val = "" + correctSelections;
			numMatched.setText(val);
            p6.play();
        });

        p6.setOnFinished(e-> {
            int num = winVals.get(5);
            if (selected.contains(num)) {
                resButtons.get(num-1).setStyle("-fx-background-color: yellow");
                correctSelections++;
            }
            else {
                resButtons.get(num-1).setStyle("-fx-background-color: red");
            }

			String val = "" + correctSelections;
			numMatched.setText(val);
            p7.play();
        });

        p7.setOnFinished(e-> {
            int num = winVals.get(6);
            if (selected.contains(num)) {
                resButtons.get(num-1).setStyle("-fx-background-color: yellow");
                correctSelections++;
            }
            else {
                resButtons.get(num-1).setStyle("-fx-background-color: red");
            }

			String val = "" + correctSelections;
			numMatched.setText(val);
            p8.play();
        });

        p8.setOnFinished(e-> {
            int num = winVals.get(7);
            if (selected.contains(num)) {
                resButtons.get(num-1).setStyle("-fx-background-color: yellow");
                correctSelections++;
            }
            else {
                resButtons.get(num-1).setStyle("-fx-background-color: red");
            }

			String val = "" + correctSelections;
			numMatched.setText(val);
            p9.play();
        });

        p9.setOnFinished(e-> {
            int num = winVals.get(8);
            if (selected.contains(num)) {
                resButtons.get(num-1).setStyle("-fx-background-color: yellow");
                correctSelections++;
            }
            else {
                resButtons.get(num-1).setStyle("-fx-background-color: red");
            }

			String val = "" + correctSelections;
			numMatched.setText(val);
            p10.play();
        });

        p10.setOnFinished(e-> {
            int num = winVals.get(9);
            if (selected.contains(num)) {
                resButtons.get(num-1).setStyle("-fx-background-color: yellow");
                correctSelections++;
            }
            else {
                resButtons.get(num-1).setStyle("-fx-background-color: red");
            }

			String val = "" + correctSelections;
			numMatched.setText(val);
            p11.play();
        });

        p11.setOnFinished(e-> {
            int num = winVals.get(10);
            if (selected.contains(num)) {
                resButtons.get(num-1).setStyle("-fx-background-color: yellow");
                correctSelections++;
            }
            else {
                resButtons.get(num-1).setStyle("-fx-background-color: red");
            }

			String val = "" + correctSelections;
			numMatched.setText(val);
            p12.play();
        });

        p12.setOnFinished(e-> {
            int num = winVals.get(11);
            if (selected.contains(num)) {
                resButtons.get(num-1).setStyle("-fx-background-color: yellow");
                correctSelections++;
            }
            else {
                resButtons.get(num-1).setStyle("-fx-background-color: red");
            }

			String val = "" + correctSelections;
			numMatched.setText(val);
            p13.play();
        });

        p13.setOnFinished(e-> {
            int num = winVals.get(12);
            if (selected.contains(num)) {
                resButtons.get(num-1).setStyle("-fx-background-color: yellow");
                correctSelections++;
            }
            else {
                resButtons.get(num-1).setStyle("-fx-background-color: red");
            }

			String val = "" + correctSelections;
			numMatched.setText(val);
            p14.play();
        });

        p14.setOnFinished(e-> {
            int num = winVals.get(13);
            if (selected.contains(num)) {
                resButtons.get(num-1).setStyle("-fx-background-color: yellow");
                correctSelections++;
            }
            else {
                resButtons.get(num-1).setStyle("-fx-background-color: red");
            }

			String val = "" + correctSelections;
			numMatched.setText(val);
            p15.play();
        });

        p15.setOnFinished(e-> {
            int num = winVals.get(14);
            if (selected.contains(num)) {
                resButtons.get(num-1).setStyle("-fx-background-color: yellow");
                correctSelections++;
            }
            else {
                resButtons.get(num-1).setStyle("-fx-background-color: red");
            }

			String val = "" + correctSelections;
			numMatched.setText(val);
            p16.play();
        });

        p16.setOnFinished(e-> {
            int num = winVals.get(15);
            if (selected.contains(num)) {
                resButtons.get(num-1).setStyle("-fx-background-color: yellow");
                correctSelections++;
            }
            else {
                resButtons.get(num-1).setStyle("-fx-background-color: red");
            }

			String val = "" + correctSelections;
			numMatched.setText(val);
            p17.play();
        });

        p17.setOnFinished(e-> {
            int num = winVals.get(16);
            if (selected.contains(num)) {
                resButtons.get(num-1).setStyle("-fx-background-color: yellow");
                correctSelections++;
            }
            else {
                resButtons.get(num-1).setStyle("-fx-background-color: red");
            }

			String val = "" + correctSelections;
			numMatched.setText(val);
            p18.play();
        });

        p18.setOnFinished(e-> {
            int num = winVals.get(17);
            if (selected.contains(num)) {
                resButtons.get(num-1).setStyle("-fx-background-color: yellow");
                correctSelections++;
            }
            else {
                resButtons.get(num-1).setStyle("-fx-background-color: red");
            }

			String val = "" + correctSelections;
			numMatched.setText(val);
            p19.play();
        });

        p19.setOnFinished(e-> {
            int num = winVals.get(18);
            if (selected.contains(num)) {
                resButtons.get(num-1).setStyle("-fx-background-color: yellow");
                correctSelections++;
            }
            else {
                resButtons.get(num-1).setStyle("-fx-background-color: red");
            }

			String val = "" + correctSelections;
			numMatched.setText(val);
            p20.play();
        });

        p20.setOnFinished(e-> {
            int num = winVals.get(19);
            if (selected.contains(num)) {
                resButtons.get(num-1).setStyle("-fx-background-color: yellow");
                correctSelections++;
            }
            else {
                resButtons.get(num-1).setStyle("-fx-background-color: red");
            }

			String val = "" + correctSelections;
			numMatched.setText(val);

			calculateWinnings(numSpots, correctSelections);


			wonTxt.setVisible(true);
			String total = "$" + totalWon;
			wonVal.setText(total);
			wonVal.setVisible(true);

			next.setVisible(true);
			newGame.setVisible(true);

			if (curRound == numDraws) {
				newGame.setDisable(false);
			}
			else {
				next.setDisable(false);
			}

        });

        show.setOnAction(e-> {
			winVals.addAll(winningNumbers);

            if (AutoPick) {
				selected.addAll(autopickedNumbers);
            }
            else {
                selected.addAll(selectedNumbers);
            }

			numMatched.setVisible(true);
			matchTxt.setVisible(true);
			show.setDisable(true);

            p1.play();
        });

		next.setOnAction(e->{
			for (int i = 0; i < resButtons.size(); i++) {
				if(selected.contains(i + 1)) {
					resButtons.get(i).setStyle("-fx-background-color: mediumspringgreen");
				}
				else {
					resButtons.get(i).setStyle("");
				}
			}

			winVals.clear();
			winningNumbers.clear();
			generateWinningNums();
			curRound++;
			correctSelections = 0;

			matchTxt.setVisible(false);
			numMatched.setVisible(false);
			numMatched.setText("");
			wonTxt.setVisible(false);
			wonVal.setVisible(false);
			next.setDisable(true);
			next.setVisible(false);
			newGame.setDisable(true);
			newGame.setVisible(false);
			show.setDisable(false);
		});

		newGame.setOnAction(e->{
			for (Button resButton : resButtons) {
				resButton.setStyle("");
			}
			winVals.clear();
			winningNumbers.clear();
			selectedNumbers.clear();
			selected.clear();
			autopickedNumbers.clear();
			resButtons.clear();

			numSpots = 0;
			numDraws = 0;
			totalWon = 0;
			correctSelections = 0;
			curRound = 1;

			generateWinningNums();
			matchTxt.setVisible(false);
			numMatched.setVisible(false);
			numMatched.setText("");
			wonTxt.setVisible(false);
			wonVal.setVisible(false);
			next.setDisable(true);
			next.setVisible(false);
			newGame.setDisable(true);
			newGame.setVisible(false);
			show.setDisable(false);

			primaryStage.setScene(sceneMap.get("welcome"));
		});


        resGrid.setAlignment(Pos.CENTER);
        resGrid.setHgap(10);
        resGrid.setVgap(10);

        VBox view = new VBox(20, title, resGrid, show, matched, won, after);
        view.setAlignment(Pos.CENTER);
        pane.setCenter(view);
		pane.setStyle("-fx-background-color: darkcyan");

        return new Scene(pane, 700, 700);
    }

	public void calculateWinnings(int numSpots, int matched) {
		int roundWinnings = 0;

		if (numSpots == 10) {
			if (matched == 10) {
				roundWinnings = 100000;
			}
			else if (matched == 9) {
				roundWinnings = 4250;
			}
			else if (matched == 8) {
				roundWinnings = 450;
			}
			else if (matched == 7) {
				roundWinnings = 40;
			}
			else if (matched == 6) {
				roundWinnings = 15;
			}
			else if (matched == 5) {
				roundWinnings = 2;
			}
			else if (matched == 0) {
				roundWinnings = 5;
			}
		}
		else if (numSpots == 8) {
			if (matched == 8) {
				roundWinnings = 10000;
			}
			else if (matched == 7) {
				roundWinnings = 750;
			}
			else if (matched == 6) {
				roundWinnings = 50;
			}
			else if (matched == 5) {
				roundWinnings = 12;
			}
			else if (matched == 4) {
				roundWinnings = 2;
			}
		}
		else if (numSpots == 4) {
			if (matched == 4) {
				roundWinnings = 75;
			}
			else if (matched == 3) {
				roundWinnings = 5;
			}
			else if (matched == 2) {
				roundWinnings = 1;
			}
		}
		else if (numSpots == 1) {
			if (matched == 1) {
				roundWinnings = 2;
			}
		}

		totalWon += roundWinnings;
	}


}
