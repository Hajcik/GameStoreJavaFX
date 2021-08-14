package GameStore.Controllers;

import GameStore.Classes.Game;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
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
    private Game game_data;
    private HomeController homeController;

    public void setData(ObservableList<Game> games_data){
        this.games_data = games_data;
    }
    public void setGame(Game game) {this.game_data = game;}

    public void setHomeController(HomeController homeController) {this.homeController = homeController;}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        DescriptionTitledPane.setExpanded(false);
        GenresLabel.setWrapText(true);
        DescriptionLabel.setWrapText(true);
        ModesLabel.setWrapText(true);
        DevelopersLabel.setWrapText(true);
        PublishersLabel.setWrapText(true);

    //    String game_cover = homeController.game_data.getImageLink();
    //    Image game_image = new Image(game_cover);

    //    ImageViewCurrentGame.setImage(game_image);

    }

    public Optional<ButtonType> result;

    @FXML
    public void sellGameButtonAction()
    {
        Alert sellingGameInfo = new Alert(Alert.AlertType.CONFIRMATION);
        Alert soldGameInfo = new Alert(Alert.AlertType.INFORMATION);
        sellingGameInfo.setHeaderText("You are selling " + game_data.getName() + ",\nyou'll receive "
        + game_data.getBuyPrice() + " PLN. Are you sure?");
        sellingGameInfo.setTitle("Selling Game");

        result = sellingGameInfo.showAndWait();

        if(result.get() == ButtonType.OK)
        {
            soldGameInfo.setTitle("Selling successful!");
            soldGameInfo.setHeaderText("You successfully sold " + game_data.getName() + "!");
            game_data.setAvailableCopies(game_data.getAvailableCopies()+1);

            System.out.println(game_data.getAvailableCopies());


            soldGameInfo.showAndWait();
        }

        if(result.get() == ButtonType.CANCEL)
        {
            sellingGameInfo.close();
        }
    }

    @FXML
    public void buyGameButtonAction()
    {
        Alert buyingGameInfo = new Alert(Alert.AlertType.CONFIRMATION);
        Alert boughtGameInfo = new Alert(Alert.AlertType.INFORMATION);
        buyingGameInfo.setTitle("Buying Game");
        buyingGameInfo.setHeaderText("You are buying " + "gameController" + "\nfor "
                + "priceController" + ".");

        Optional<ButtonType> result = buyingGameInfo.showAndWait();

        if(result.get() == ButtonType.OK)
        {
            boughtGameInfo.setTitle("Buying successful!");
            boughtGameInfo.setHeaderText("You successfully bought blabal...");
            boughtGameInfo.showAndWait();
        }

        if(result.get() == ButtonType.CANCEL)
        {
            boughtGameInfo.close();
        }
    }
}
