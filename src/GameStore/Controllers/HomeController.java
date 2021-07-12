package GameStore.Controllers;

import GameStore.Classes.Client;
import GameStore.Classes.Game;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Platform;
import javafx.fxml.Initializable;

import java.net.URL;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

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


            games.forEach(System.out::println);
            clients.forEach(System.out::println);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
