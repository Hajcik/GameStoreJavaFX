package GameStore.Controllers;

import GameStore.Classes.Client;
import GameStore.Classes.Game;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    public Button check;
    public ListView ListViewGames;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        loadDataJSON();

        Thread thread = new Thread( () -> {
           while(true)
           {
               try{
                   Thread.sleep(500);
                   Platform.runLater(() -> loadDataJSON());
               }
               catch(InterruptedException e){
                   e.printStackTrace();
               }
           }
        });

        // make if statement to reload data after inserting new data
     //   thread.start();
    }

    public void loadDataJSON() {

        try {
            ObjectMapper mapper = new ObjectMapper();

            List<Game> games = Arrays.asList(mapper.readValue(Paths.get("src/GameStore/Resources/games.json").toFile(), Game[].class));
            List<Client> clients = Arrays.asList(mapper.readValue(Paths.get("src/GameStore/Resources/clients.json").toFile(), Client[].class));

            ObservableList<Game> games_obs = FXCollections.observableArrayList(games);
            ObservableList<Client> clients_obs = FXCollections.observableArrayList(clients);

            ListViewGames.setItems(games_obs);
            ListViewGames.setCellFactory(param -> new ListCell<Game>(){
                @Override
                protected void updateItem(Game game, boolean empty) {

                    super.updateItem(game, empty);

                    if(empty || game == null || game.getName() == null || game.getImageLink() == null) {
                        setText(null);
                        setGraphic(null);
                    } else {

                        String image_url = game.getImageLink();
                        Image image = new Image(image_url);
                        ImageView imageView = new ImageView(image);
                        imageView.setFitWidth(150);
                        imageView.setFitHeight(200);

                        setGraphic(imageView);
                        setText(game.listViewDisplay_Game());
                    }
                }
            });

            games.forEach(System.out::println);
            clients.forEach(System.out::println);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // FXML functions

    // Display game information

    @FXML
    protected void onClickListViewCell_Games(Event event)
    {
        Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);

        infoAlert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        infoAlert.setTitle("Game Information");
        infoAlert.setHeaderText(null);

        GridPane grid = new GridPane();
        ColumnConstraints graphicColumn = new ColumnConstraints();
        graphicColumn.setFillWidth(false);
        graphicColumn.setHgrow(Priority.NEVER);
        grid.getColumnConstraints().setAll(graphicColumn);
        grid.setPadding(new Insets(5));

        Game game = (Game) ListViewGames.getSelectionModel().getSelectedItem();

        Image image = new Image(game.getImageLink());
        ImageView imageView = new ImageView(image);

        imageView.setFitHeight(128);
        imageView.setFitWidth(128);

        StackPane stackPane = new StackPane(imageView);
        stackPane.setAlignment(Pos.CENTER_RIGHT);
        grid.add(stackPane, 0, 0);


        infoAlert.setContentText(game.listViewDisplay_Game());
        infoAlert.showAndWait();
    }
}
