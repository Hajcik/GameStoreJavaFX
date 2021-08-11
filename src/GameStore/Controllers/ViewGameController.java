package GameStore.Controllers;

import GameStore.Classes.Game;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewGameController implements Initializable {

    public Button BuyGameButton;
    public Button SellGameButton;
    public Label BuyPriceLabel;
    public Label SellPriceLabel;
    public ImageView ImageViewCurrentGame;

    private ObservableList<Game> games_data;

    public void setData(ObservableList<Game> games_data){
        this.games_data = games_data;
    }


    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("GameStore/FXMLs/GameStoreMain.fxml"));
    HomeController controller = (HomeController) loader.getController();

    Game game = (Game) controller.ListViewGames.getSelectionModel().getSelectedItem();

    // Load photo
      String image_url = game.getImageLink();
      Image image = new Image(image_url);

  //    ImageViewCurrentGame.setImage(image);




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }




}
