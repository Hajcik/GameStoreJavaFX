package GameStore.Controllers;

import GameStore.Classes.Client;
import GameStore.Classes.Game;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class HomeController implements Initializable {

    public ListView ListViewGames;

    public void Styling_ListViewGames()
    {
        ListViewGames.setStyle(".list-cell{-fx-font-size:20.0;");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Styling_ListViewGames();
        loadDataJSON();

        Thread thread = new Thread( () -> {
           while(true)
           {
               try{
                   Thread.sleep(15000);
                   Platform.runLater(() -> loadDataJSON());
               }
               catch(InterruptedException e){
                   e.printStackTrace();
               }
           }
        });

        // make if statement to reload data after inserting new data
      //  thread.start();
    }


    public Button SearchButton;
    public TextField SearchBar;

    public ObservableList<Game> games_obs;
    public ObservableList<Client> clients_obs;

    public void loadDataJSON() {

        try {
            ObjectMapper mapper = new ObjectMapper();

            List<Game> games = Arrays.asList(mapper.readValue(Paths.get("src/GameStore/Resources/games.json").toFile(), Game[].class));
            List<Client> clients = Arrays.asList(mapper.readValue(Paths.get("src/GameStore/Resources/clients.json").toFile(), Client[].class));

            games_obs = FXCollections.observableArrayList(games);
            clients_obs = FXCollections.observableArrayList(clients);


            ListViewGames.setItems(games_obs.sorted());
            ListViewGames.setCellFactory(param -> new ListCell<Game>(){
                @Override
                protected void updateItem(Game game, boolean empty) {

                    super.updateItem(game, empty);

                    if(empty || game == null || game.getName() == null || game.getImageLink() == null) {
                        setText(null);
                        setGraphic(null);

                    } else if (SearchBar.getText().isEmpty() ||
                            game.getPlatform().toLowerCase(Locale.ROOT).contains(SearchBar.getText().toLowerCase(Locale.ROOT)) ||
                            game.getName().toLowerCase(Locale.ROOT).contains(SearchBar.getText().toLowerCase(Locale.ROOT))) {
                        String image_url = game.getImageLink();
                        Image image = new Image("file:" + image_url);
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

    public Game game_data;
    // Add New Game
    @FXML
    public void onOpenDialog_AddNewGame(Event event)
    {
        Parent root;
        FXMLLoader loader;
        try {
            loader = new FXMLLoader(getClass().getClassLoader().getResource("GameStore/FXMLs/GameStoreNewGame.fxml"));
            root = (Parent) loader.load();

            NewGameController newGameController = (NewGameController) loader.getController();
            newGameController.setGame(game_data);
            newGameController.setData(games_obs);


            Stage stage = new Stage();
            stage.setTitle("New Game Panel");
            stage.setScene(new Scene(root, 700, 500));
            stage.setResizable(false);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Open stage code - View Game Info
    @FXML
    public void openGameInfo(Event event){
        Parent root;
        FXMLLoader loader;
        try {

            loader = new FXMLLoader(getClass().getClassLoader().getResource("GameStore/FXMLs/GameStoreViewGame.fxml"));
            root = (Parent) loader.load();

            ViewGameController viewGameController = (ViewGameController) loader.getController();
            viewGameController.setData(games_obs);

            // set HomeController for ViewGameController.java ??

            game_data = (Game) ListViewGames.getSelectionModel().getSelectedItem();
            viewGameController.setGame(game_data);

            String game_cover = game_data.getImageLink();
            Image image = new Image("file:"+game_cover);

            viewGameController.ImageViewCurrentGame.setImage(image);
            viewGameController.SellPriceLabel.setText(game_data.getSellPrice().toString() + " PLN");
            viewGameController.BuyPriceLabel.setText(game_data.getBuyPrice().toString() + " PLN");
            viewGameController.GameNameLabel.setText(game_data.getName());
            viewGameController.DescriptionLabel.setText(game_data.getDescription());
            viewGameController.AvailableCopiesLabel.setText(game_data.getAvailableCopies().toString());
            viewGameController.PlatformLabel.setText(game_data.getPlatform());

            String tmp_devs = Arrays.toString(game_data.getDevelopers()).substring(1);
            String tmp_pubs = Arrays.toString(game_data.getPublishers()).substring(1);
            String tmp_modes = Arrays.toString(game_data.getModes()).substring(1);
            String tmp_genres = Arrays.toString(game_data.getGenres()).substring(1);

            viewGameController.ModesLabel.setText(tmp_modes.substring(0, tmp_modes.length()-1));
            viewGameController.GenresLabel.setText(tmp_genres.substring(0, tmp_genres.length()-1));
            viewGameController.DevelopersLabel.setText(tmp_devs.substring(0, tmp_devs.length()-1));
            viewGameController.PublishersLabel.setText(tmp_pubs.substring(0, tmp_pubs.length()-1));

            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            viewGameController.ReleaseDateLabel.setText(formatter.format(game_data.getReleaseDate()));




            Stage stage = new Stage();
            stage.setTitle("Game Information");
            stage.setScene(new Scene(root, 800, 450));
            stage.setResizable(false);
            stage.show();



          //  ((Node)(event.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Button exitButton;
    public Button aboutButton;

    public void aboutInfo()
    {
        Alert aboutAlert = new Alert(Alert.AlertType.INFORMATION);
        aboutAlert.setTitle("About the app");
        aboutAlert.setHeaderText("Easy Peasy Games App");
        aboutAlert.setContentText("Author: Mariusz Jędrzejewski\n" +
                "About: Application provides ability to buy or sell games.\n" +
                "It's constructed to make it easy. Select / Search for the game\n" +
                "you are interested in and choose if you want to buy it, or you have it for sale!");
        aboutAlert.showAndWait();
    }


    public void exitApp()
    {
        System.exit(0);
    }

}
