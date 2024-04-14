package pegGame;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Scanner;

/**
 * This class represents a graphical user interface (GUI) for a peg game.
 * The GUI allows users to interact with the peg game, make moves, save the game, and exit.
 */
public class PegGameGUI1 extends Application {
    private SquarePegGame squarePegGame;
    private GridPane gridPane;
    private int buttonSize = 50;
    private Button selectedButton = null;
    private Button saveButton;
    private Button exitButton;
    private Label errorLabel;
    private Label gameStateLabel;
    private Label possibleMovesLabel;
    private Label moveCounterLabel;
    private int moveCounter = 0;


/**
 * Initializes the graphical user interface.
 * This method sets up the layout, buttons, labels, and event handlers.
 *
 * @param primaryStage The primary stage of the JavaFX application.
 * @throws IOException If an I/O error occurs while initializing the GUI.
 */
@Override

    public void start(Stage primaryStage) throws IOException{
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the filename: ");
        String filename = scanner.nextLine().trim();
        BoardReader reader = new BoardReader(filename);
        squarePegGame = reader.readFromFile();

        gridPane = new GridPane();
        initializeGUI();

//Create the error label
    errorLabel = new Label("");
    errorLabel.setStyle("-fx-text-fill: black;" +
                        "-fx-border-color: black;" +
                        "-fx-background-color: red;" +
                        "-fx-font-size:25;");
                    
//Iterate over the collection of possible moves and append two moves per line

Collection<Move> possibleMoves = squarePegGame.getPossibleMoves();       
StringBuilder movesStringBuilder = new StringBuilder("Possible Moves:\n");
int moveCount = 0;
for (Move move : possibleMoves) {
    if (moveCount % 2 == 0 && moveCount != 0) {
        movesStringBuilder.append("\n");
    }
    movesStringBuilder.append(move.toString()).append("   ");
    moveCount++;
}

    //Create a Label with the constructed string
    possibleMovesLabel = new Label(movesStringBuilder.toString());
        possibleMovesLabel.setStyle(
            "-fx-border-color: black; " +  // Set solid black border
            "-fx-border-width: 1px; " +     // Set border width
            "-fx-background-color: lightblue;" +
            "-fx-font-size: 25;"); // Set light blue background

        // Create the move counter label
        moveCounterLabel = new Label("Move Counter: 0");
        moveCounterLabel.setStyle("-fx-font-size: 30;"+
                                    "-fx-border-color: black;"+
                                    "-fx-background-color: lightblue;");

        VBox.setMargin(moveCounterLabel, new Insets(10));
        VBox mainWindow = new VBox();

        mainWindow.getChildren().addAll(gridPane, createControlPane(), errorLabel, possibleMovesLabel, moveCounterLabel);

        Scene scene = new Scene(mainWindow);
            primaryStage.setScene(scene);
            primaryStage.setFullScreen(true);
            primaryStage.setTitle("PegGame GUI");
            primaryStage.show();
            scanner.close();
    }

    /**
     * Initializes the GUI layout and grid.
     */    
private void initializeGUI() {
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(5);
        gridPane.setVgap(5);
    
        for (int row = 0; row < squarePegGame.getRow(); row++) {
            for (int col = 0; col < squarePegGame.getcol(); col++) {
                final int finalRow = row;
                final int finalCol = col;
                Button button = new Button();
                button.setMinSize(buttonSize, buttonSize);
                button.setMaxSize(buttonSize, buttonSize);
                button.setShape(new Circle(buttonSize * 2)); // Set button shape to circle
                
                // Set smaller size for grey buttons (holes)
                if (squarePegGame.getBoard()[row][col] == 0) {
                    button.setMinSize(buttonSize * 0.6, buttonSize * 0.6);
                    button.setMaxSize(buttonSize * 0.6, buttonSize * 0.6);
                    button.setAlignment(null);
                }
                
                gridPane.add(button, col, row); 
                gridPane.setStyle("-fx-background-color: black;"); // Sets background color to red
                GridPane.setHalignment(button, HPos.CENTER);
                GridPane.setValignment(button, VPos.CENTER);      
    
                // Add event handler for button click
                button.setOnAction(event -> buttonClick(button, finalRow, finalCol));
    
                // Set button appearance based on peg or hole
                if (squarePegGame.getBoard()[row][col] == 0) {
                    button.setStyle("-fx-background-color: grey; -fx-border-color: black;");
                } else {
                    button.setStyle("-fx-background-color: lightblue; -fx-border-color: black;");
                }
            }
        }
    }

    /**
     * Creates the control pane containing save, exit, and game state buttons.
     *
     * @return The HBox containing the control buttons.
     */
    private HBox createControlPane() {
        HBox controlPane = new HBox(10); // Spacing between buttons
        controlPane.setPadding(new Insets(10));

        // Create and add the save button
        saveButton = new Button("Save");
        saveButton.setStyle("-fx-font-size: 20;");
        saveButton.setOnAction(event -> saveGame());
        controlPane.getChildren().add(saveButton);

        // Create and add the exit button
        exitButton = new Button("Exit");
        exitButton.setStyle("-fx-font-size: 20;");
        exitButton.setOnAction(event -> exitGame());
        controlPane.getChildren().add(exitButton);

        // Create and add GameState label
        gameStateLabel = new Label("Game State: Game has not started");
        gameStateLabel.setStyle("-fx-border-color: black;"+
                                "-fx-background-color: lightgreen;" +
                                "-fx-font-size: 30;");
        controlPane.getChildren().add(gameStateLabel);

        return controlPane;
    }
      
    /**
     * Updates the GUI board based on the current state of the peg game.
     */
    private void updateBoard() {
        int[][] board = squarePegGame.getBoard();
    
        for (int row = 0; row < squarePegGame.getRow(); row++) {
            for (int col = 0; col < squarePegGame.getcol(); col++) {
                Button button = (Button) gridPane.getChildren().get(row * squarePegGame.getcol() + col);
                if (board[row][col] == 1) {
                    button.setStyle("-fx-background-color: lightblue; -fx-border-color: black;");
                    button.setMinSize(buttonSize, buttonSize);
                    button.setMaxSize(buttonSize, buttonSize);
                }  else {
                    button.setStyle("-fx-background-color: grey; -fx-border-color: black;");
                    button.setMinSize(buttonSize * 0.6, buttonSize * 0.6);
                    button.setMaxSize(buttonSize * 0.6, buttonSize * 0.6);
                }
            }
           
        }
    }
    
    /**
     * Handles button click events.
     *
     * @param button The button that was clicked.
     * @param row    The row index of the button in the grid.
     * @param col    The column index of the button in the grid.
     */
private void buttonClick(Button button, int row, int col) {
        if (selectedButton == null) {
            // First click: select peg
            if (squarePegGame.getBoard()[row][col] == 1) { // Check if the clicked button represents a peg
                selectedButton = button;
                button.setStyle("-fx-background-color: blue; -fx-border-color: black;");
            } else {
                errorLabel.setText("Please select a peg.");
            }
        } else {
            // Second click: select destination hole
            try {
                int fromRow = GridPane.getRowIndex(selectedButton);
                int fromCol = GridPane.getColumnIndex(selectedButton);
                int toRow = row;
                int toCol = col;

                Location fromLocation = new Location(fromRow, fromCol);
                Location toLocation = new Location(toRow, toCol);
                Move move = new Move(fromLocation, toLocation);
                
                squarePegGame.makeMove(move); // Attempt to make the move
                moveCounter++;
                moveCounterLabel.setText("Move Counter: " + moveCounter);
                updatePossibleMovesLabel();
                gameStateLabel.setText("Game State: " + squarePegGame.getGameState());
                errorLabel.setText("");
            }   catch (pegGameException e) {
                // Handle invalid move
                errorLabel.setText(e.getMessage());
            } 
            finally {
                if (selectedButton != null) {
                    if (squarePegGame.getBoard()[row][col] == 0) {
                        selectedButton.setStyle("-fx-background-color: grey; -fx-border-color: black;");
                    } else {
                        selectedButton.setStyle("-fx-background-color: lightblue; -fx-border-color: black;");
                    }
                }
                updateBoard(); // Update the GUI board
                selectedButton = null; // Reset selected button
            }
            
        }
    }

    /**
     * Updates the possible moves label based on the current state of the peg game.
     */
    private void updatePossibleMovesLabel() {
        Collection<Move> possibleMoves = squarePegGame.getPossibleMoves();
        StringBuilder movesStringBuilder = new StringBuilder("Possible Moves:\n");
    
        int moveCount = 0;
        for (Move move : possibleMoves) {
            if (moveCount % 2 == 0 && moveCount != 0) {
                movesStringBuilder.append("\n");
            }
            movesStringBuilder.append(move.toString()).append("   ");
            moveCount++;
        }
    
        possibleMovesLabel.setText(movesStringBuilder.toString());
    }

    /**
     * Saves the current state of the peg game to a file.
     */
    private void saveGame() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Game");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text files", "*.txt"));
        File selectedFile = fileChooser.showSaveDialog(new Stage());
    
        if (selectedFile != null) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(selectedFile))) {
                writer.write(squarePegGame.getRow() + "\n");
    
                int[][] board = squarePegGame.getBoard();
                for (int row = 0; row < squarePegGame.getRow(); row++) {
                    StringBuilder line = new StringBuilder();
                    for (int col = 0; col < squarePegGame.getcol(); col++) {
                        if (board[row][col] == 1) {
                            line.append("o");
                        } else {
                            line.append(".");
                        }
                        
                    }
                    writer.write(line.toString() + "\n");
                }
            } catch (IOException e) {
                System.out.println("Error saving file: " + e.getMessage());
            }
        }
    }
    

    /**
     * Exits the peg game application.
     */
    private void exitGame(){
        Stage stage = (Stage) gridPane.getScene().getWindow();
            stage.close();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
