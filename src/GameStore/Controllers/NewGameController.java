package GameStore.Controllers;

import GameStore.Classes.Game;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

public class NewGameController implements Initializable {
    private ObservableList<Game> games_data;
    private Game game_data;

    public TextField GameNameTextField, PublishersTextField, ModesTextField, GenresTextField, DevelopersTextField;
    public TextField BuyPriceTextField, SellPriceTextField;
    public TextField AvailableCopiesTextField;
    public TextArea DescriptionTextArea;
    public DatePicker ReleaseDateDatePicker;
    public ComboBox<String> PlatformComboBox = new ComboBox<String>();

    public void setData(ObservableList<Game> games_data) {this.games_data = games_data;}
    public void setGame(Game game) {this.game_data = game;}

    public ObservableList<String> platforms;

    @FXML
    public void setGameValues()
    {
        Game game = new Game();

        game.setName(GameNameTextField.getText());
        game.setPlatform(PlatformComboBox.getSelectionModel().getSelectedItem());

        game.setGenres(GenresTextField.getStyleClass().toArray(new String[0]));
        game.setModes(ModesTextField.getStyleClass().toArray(new String[0]));
        game.setDevelopers(DevelopersTextField.getStyleClass().toArray(new String[0]));
        game.setPublishers(PublishersTextField.getStyleClass().toArray(new String[0]));

        // Buy Price set
        String tmp_buy = BuyPriceTextField.getText();
        Float tmp_buy_float = Float.parseFloat(tmp_buy);
        game.setBuyPrice(tmp_buy_float);
        // Sell Price set
        String tmp_sell = SellPriceTextField.getText();
        Float tmp_sell_float = Float.parseFloat(tmp_sell);
        game.setSellPrice(tmp_sell_float);

        // Available Copies set
        String tmp_copies = AvailableCopiesTextField.getText();
        Integer tmp_copies_int = Integer.parseInt(tmp_copies);
        game.setAvailableCopies(tmp_copies_int);

        // Release Date set
        LocalDate date = ReleaseDateDatePicker.getValue();

        game.setDescription(DescriptionTextArea.getText());
     ReleaseDateDatePicker.getChronology();

        System.out.println(game.toString());


    }

    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {

      //  ReleaseDateDatePicker.set
        PlatformComboBox.setItems(FXCollections.observableArrayList("PC", "PS3", "PS4", "PS5", "XBOX360", "XBOX ONE", "NINTENDO SWITCH"));
    }

    public Button ChooseImageButton;
    private Desktop desktop = Desktop.getDesktop();

    @FXML
    ImageView PreviewImageView;

    @FXML
    public void open_img(Event event){
        final Stage stage = new Stage();
        stage.setTitle("Choose image");
        final FileChooser fileChooser = new FileChooser();

        fileChooser.setInitialDirectory(new File("src/GameStore/Resources/img/GameCovers"));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
                );
        File file = fileChooser.showOpenDialog(stage);
            if(file != null){

                    System.out.println(file.getName());
                    Image tmp_img = new Image("file:src/GameStore/Resources/img/GameCovers/" + file.getName());
                    PreviewImageView.setImage(tmp_img);

               // PreviewImageView.setImage();
            }


    }

}
