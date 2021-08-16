package GameStore.Controllers;

import GameStore.Classes.Game;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
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
    public Label AvailableCopiesLabel;
    public ImageView ImageViewCurrentGame;
    public TitledPane DescriptionTitledPane;

    private ObservableList<Game> games_data;
    private Game game_data;
    private HomeController homeController;

    public void setData(ObservableList<Game> games_data){
        this.games_data = games_data;
    }
    public void setGame(Game game) {this.game_data = game;}


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        DescriptionTitledPane.setExpanded(false);
        GenresLabel.setWrapText(true);
        DescriptionLabel.setWrapText(true);
        ModesLabel.setWrapText(true);
        DevelopersLabel.setWrapText(true);
        PublishersLabel.setWrapText(true);
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
            AvailableCopiesLabel.setText(game_data.getAvailableCopies().toString());

            System.out.println(game_data.getAvailableCopies());


            soldGameInfo.showAndWait();
        }

        if(result.get() == ButtonType.CANCEL)
        {
            sellingGameInfo.close();
        }
    }

    @FXML
    public void deleteGame()
    {
        Alert deleteInfo = new Alert(Alert.AlertType.CONFIRMATION);
        deleteInfo.setHeaderText("You are about to delete " + game_data.getName() + "\n from store!\n" +
                "Are you sure?");
        Optional<ButtonType> choice = deleteInfo.showAndWait();

        if(choice.get() == ButtonType.OK)
        {
            Alert deleteSuccesful = new Alert(Alert.AlertType.INFORMATION);

            Integer id = game_data.getId();
            games_data.remove(id);

            ObjectMapper mapper = new ObjectMapper();
            try {
                JsonNode jsonNode = mapper.readTree(new File("src/GameStore/Resources/games.json"));
                Iterator<JsonNode> nodes = jsonNode.elements();

                for (Iterator<JsonNode> it = nodes; it.hasNext(); ) {
                    JsonNode gameNode = it.next();
                    if(gameNode instanceof ObjectNode) {
                        ObjectNode object = (ObjectNode) gameNode;
                        object.removeAll();
                    }
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            deleteSuccesful.setHeaderText("Deleting successful.");
            deleteSuccesful.showAndWait();

        }

        if(choice.get() == ButtonType.CANCEL)
        {
            deleteInfo.close();
        }
    }

    @FXML
    public void buyGameButtonAction()
    {

        Alert boughtGameInfo = new Alert(Alert.AlertType.INFORMATION);

        if(game_data.getAvailableCopies() == 0 || game_data.getAvailableCopies() < 0)
        {
            Alert buyingGameInfo = new Alert(Alert.AlertType.INFORMATION);
            buyingGameInfo.setTitle("Sorry!");
            buyingGameInfo.setHeaderText("You can't buy " + game_data.getName() + ", no copies available.");
            buyingGameInfo.showAndWait();
        }
        else
            {
                Alert buyingGameInfo = new Alert(Alert.AlertType.CONFIRMATION);
                buyingGameInfo.setTitle("Buying Game");
                buyingGameInfo.setHeaderText("You are buying " + game_data.getName() + "\nfor "
                    + game_data.getSellPrice() + ".");

            Optional<ButtonType> result = buyingGameInfo.showAndWait();

            if (result.get() == ButtonType.OK) {
                boughtGameInfo.setTitle("Buying successful!");
                boughtGameInfo.setHeaderText("You successfully bought " + game_data.getName() + "!");
                game_data.setAvailableCopies(game_data.getAvailableCopies() - 1);
                AvailableCopiesLabel.setText(game_data.getAvailableCopies().toString());
                boughtGameInfo.showAndWait();
            }

            if (result.get() == ButtonType.CANCEL) {
                boughtGameInfo.close();
            }
        }
    }
}
