package GameStore.Controllers;

import GameStore.Classes.Game;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;
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
import javafx.util.StringConverter;

import java.awt.Desktop;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
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

    public String image_url;

    @FXML
    public void setGameValues()
    {
        Game game = new Game();

        // Game name
        game.setName(GameNameTextField.getText());

        // Game platform
        game.setPlatform(PlatformComboBox.getSelectionModel().getSelectedItem());

        String genres, modes, devs, publishers;
        String[] genresInput, modesInput, devsInput, publishersInput;

        // Game genres
        genres = GenresTextField.getText();
        genresInput = genres.split(",");
        game.setGenres(genresInput);

        // Game modes
        modes = ModesTextField.getText();
        modesInput = modes.split(",");
        game.setModes(modesInput);

        // Developers

        devs = DevelopersTextField.getText();
        devsInput = devs.split(",");
        game.setDevelopers(devsInput);

        // Publishers
        publishers = PublishersTextField.getText();
        publishersInput = publishers.split(",");
        game.setPublishers(publishersInput);

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
        ZoneId defaultZoneId = ZoneId.systemDefault();

        LocalDate localDate = ReleaseDateDatePicker.getValue();
        Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
        game.setReleaseDate(date);

        // Description
        game.setDescription(DescriptionTextArea.getText());

        // Game image link
        game.setImageLink(image_url);

        System.out.println(game.toString());

        try {
            // object to json
            ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            ObjectNode game_node = mapper.createObjectNode();

            String tmp_genres = Arrays.toString(game.getGenres()).substring(1);
            String tmp_modes = Arrays.toString(game.getModes()).substring(1);
            String tmp_devs = Arrays.toString(game.getDevelopers()).substring(1);
            String tmp_pubs = Arrays.toString(game.getPublishers()).substring(1);


            game_node.put("Name", game.getName());
            game_node.put("Platform", game.getPlatform());
            game_node.put("BuyPrice", game.getBuyPrice());
            game_node.put("SellPrice", game.getSellPrice());
            game_node.putArray("Genres").add(tmp_genres.substring(0, tmp_genres.length()-1));
            game_node.putArray("Modes").add(tmp_modes.substring(0, tmp_modes.length()-1));
            game_node.put("ReleaseDate", formatter.format(game.getReleaseDate()));
            game_node.putArray("Developers").add(tmp_devs.substring(0, tmp_devs.length()-1));
            game_node.putArray("Publishers").add(tmp_pubs.substring(0, tmp_pubs.length()-1));
            game_node.put("AvailableCopies", game.getAvailableCopies());
            game_node.put("ImageLink", game.getImageLink());
            game_node.put("Description", game.getDescription());

            String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(game_node);

            try {
                mapper.writeValue(new File("src/GameStore/Resources/games.json"), game_node);
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println(json);

        } catch (Exception e)
        {
            e.printStackTrace();
        }

        // Add alert info to button ADD "Are you sure?"
        // Important to do!

    }

    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        String datePattern = "dd/MM/yyyy";
        ReleaseDateDatePicker.setConverter(new StringConverter<LocalDate>(){
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(datePattern);

            @Override
            public String toString(LocalDate date)
            {
                if(date != null) {
                    return dateTimeFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if(string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateTimeFormatter);
                } else {
                    return null;
                }
            }
        });

        LocalDate example = LocalDate.of(2000, 01, 01);
        ReleaseDateDatePicker.setValue(example);
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
                    image_url = "src/GameStore/Resources/img/GameCovers/" + file.getName();
                    Image tmp_img = new Image("file:" + image_url);
                    PreviewImageView.setImage(tmp_img);
            }


    }

}
