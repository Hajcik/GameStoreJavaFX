package GameStore.Controllers;

import GameStore.Classes.Game;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewGameController implements Initializable {

    public Button BuyGameButton;
    public Button SellGameButton;
    public Label BuyPriceLabel;
    public Label SellPriceLabel;
    public Label GameNameLabel;
    public Label PublishersLabel;
    public Label DevelopersLabel;
    public Label DescriptionLabel;
    public Label ReleaseDateLabel;
    public Label GenresLabel;
    public Label ModesLabel;
    public Label PlatformLabel;
    public ImageView ImageViewCurrentGame;
    public TitledPane DescriptionTitledPane;

    private ObservableList<Game> games_data;

    public void setData(ObservableList<Game> games_data){
        this.games_data = games_data;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("GameStore/FXMLs/GameStoreMain.fxml"));
        HomeController homeController = (HomeController) loader.getController();

        DescriptionTitledPane.setExpanded(false);

    //    String game_cover = homeController.game_data.getImageLink();
    //    Image game_image = new Image(game_cover);

    //    ImageViewCurrentGame.setImage(game_image);

    }
}
