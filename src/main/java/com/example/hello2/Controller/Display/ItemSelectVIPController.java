package com.example.hello2.Controller.Display;

import com.example.hello2.Model.ItemModel;
import com.example.hello2.Model.SelectableCard;
import com.example.hello2.Model.SelectedItems;
import com.example.hello2.Model.UserModel;
import com.example.hello2.Reader.ItemsFileReader;
import com.example.hello2.Reader.SelectedItemsReader;
import com.example.hello2.Reader.UserFileReader;
import com.example.hello2.Writer.ItemsFileWriter;
import com.example.hello2.Writer.SelectedItemsWriter;
import com.example.hello2.Writer.UsersFileWriter;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class ItemSelectVIPController {

    @FXML
    private String ID;

    public Button back;
    public Text Welcome;
    public Text Balance;
    public Text Points;
    public ScrollPane ownedItemsDisplay;
    public Button Deposit;
    public Label Account;

    @FXML
    private Button freebutton;

    static HashMap<String, String> itemImageFile = new HashMap<>();

    static {
        itemImageFile.put("Casablanca", "Casablanca.png");
        itemImageFile.put("Star Wars", "StarWars.png");
        itemImageFile.put("Easy Rider", "EasyRider.png");
        itemImageFile.put("Blade Runner", "BladeRunner.png");
        itemImageFile.put("The Dark Knight", "TheDarkKnight.png");
        itemImageFile.put("Fight Club", "FightClub.png");
        itemImageFile.put("IT", "IT.png");
        itemImageFile.put("Overwatch", "overwatch.png");
        itemImageFile.put("RDR", "RDR.png");
        itemImageFile.put("The Last of Us", "ThelastOfUS.png");
        itemImageFile.put("Schindler's List", "schindler's list.jpg");
        itemImageFile.put("The Notebook", "Notebook.jpg");
        itemImageFile.put("Inception", "Inception.jpg");
        itemImageFile.put("The Sixth Sense", "Thesixthsense.jpg");
        itemImageFile.put("Gone with the Wind", "GoneWithTheWind.png");
        itemImageFile.put("Sonic", "Sonic.jpg");
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getUserID() {
        return ID;
    }

    public ItemSelectVIPController() {
    }

    public void setInitialize() throws IOException {
        UserFileReader userFileReader = new UserFileReader();
        SelectedItemsWriter selectedItemsWriter = new SelectedItemsWriter();

        ArrayList<SelectedItems> selectedItemsArrayList = new SelectedItemsReader().readFileSelectedItems();

        for (UserModel user : userFileReader.readFileUser()) {
            if (Objects.equals(user.getId(), getUserID())) {
                Account.setText("Account Status: " + user.getAccountType());
                Balance.setText("Balance: $" + String.format("%.2f", user.getBalance()));
                Welcome.setText("Welcome: " + user.getUsername());
                Points.setText("Current Points: " + (user.getNumReturned() * 10));
                freebutton.setVisible(true);
            }
        }

        if (!selectedItemsArrayList.isEmpty()) {
            ArrayList<String> temp = new ArrayList<>();
            for (SelectedItems items : selectedItemsArrayList) {
                temp.add(items.getID());
            }

            for (UserModel user : userFileReader.getUserList()) {
                if (!temp.contains(user.getId())) {
                    SelectedItems selectedItems = new SelectedItems(user.getId());
                    selectedItemsArrayList.add(selectedItems);
                    selectedItemsWriter.SelectedItemsWriteFIle(selectedItemsArrayList);
                }
            }
        }

        if (selectedItemsArrayList.isEmpty()) {
            for (UserModel user : userFileReader.getUserList()) {
                SelectedItems selectedItems = new SelectedItems(user.getId());
                selectedItemsArrayList.add(selectedItems);
                selectedItemsWriter.SelectedItemsWriteFIle(selectedItemsArrayList);
            }
        }
    }

    public void ownedItems() throws IOException {
        owned(ownedItemsDisplay, ID);
    }

    static void owned(ScrollPane ownedItemsDisplay, String id) throws IOException {
        ArrayList<ItemModel> itemModelArrayList = new ItemsFileReader().readFileItems();
        ArrayList<SelectedItems> selectedItemsArrayList = new SelectedItemsReader().readFileSelectedItems();

        FlowPane flowPane = new FlowPane();
        flowPane.setHgap(30); // Set horizontal gap between elements
        flowPane.setVgap(10); // Set vertical gap between elements
        flowPane.setAlignment(Pos.TOP_LEFT);
        flowPane.setPrefSize(600, 400);
        flowPane.setPadding(new Insets(10));

        ownedItemsDisplay.setFitToWidth(true);
        ownedItemsDisplay.setFitToHeight(true);
        flowPane.setStyle("-fx-background-color: #515151;"); // Set background color of ScrollPane

        for (SelectedItems temp : selectedItemsArrayList) {
            for (ItemModel items : itemModelArrayList) {
                if (temp.getSelectedItemsList().contains(items.getID()) && Objects.equals(temp.getID(), id)) {
                    SelectableCard selectableCard = new SelectableCard();

                    selectableCard.setText(items.toString());
                    selectableCard.setUserData(items.getRentalType());
                    selectableCard.setId(items.getID());


                    String fileName = itemImageFile.get(items.getTitle());
                    URL resourceUrl = ItemSelectVIPController.class.getResource("/com/example/hello2/Images/" + fileName);
                    if (resourceUrl != null) {
                        Image image = new Image(resourceUrl.toExternalForm());
                        selectableCard.setImage(image);
                    }
                    flowPane.getChildren().add(selectableCard);

                }
            }
        }

        ownedItemsDisplay.setContent(flowPane);
    }

    private List<SelectableCard> getRandomItems(List<SelectableCard> items, int count) {
        List<SelectableCard> randomItems = new ArrayList<>(items);
        java.util.Collections.shuffle(randomItems);
        return randomItems.subList(0, Math.min(count, randomItems.size()));
    }

    public void freeItem() throws IOException {
        ItemsFileReader itemsFileReader = new ItemsFileReader();
        ItemsFileWriter itemsFileWriter = new ItemsFileWriter();
        SelectedItemsReader selectedItemsReader = new SelectedItemsReader();
        SelectedItemsWriter selectedItemsWriter = new SelectedItemsWriter();
        UsersFileWriter usersFileWriter = new UsersFileWriter();
        UserFileReader userFileReader = new UserFileReader();
        List<SelectableCard> cardList = new ArrayList<>();


        Button dvdButton = new Button("DVD");
        Button recordButton = new Button("Record");
        Button gameButton = new Button("Game");
        Button allButton = new Button("All");

        FlowPane flowPane = new FlowPane();
        flowPane.setHgap(10); // Set horizontal gap between elements
        flowPane.setVgap(10); // Set vertical gap between elements
        flowPane.setAlignment(Pos.TOP_LEFT);
        flowPane.setPrefSize(860, 600);
        flowPane.setStyle("-fx-background-color: #e6becd;");
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setStyle("-fx-background-color: #e6becd;");

        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        ArrayList<ItemModel> itemModelArrayList = itemsFileReader.readFileItems();
        int points = 0;
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        for (UserModel user : userFileReader.readFileUser()) {
            if (Objects.equals(user.getId(), getUserID())) {
                points = user.getNumReturned();
                if (user.getNumReturned() < 10) {
                    alert.setContentText(100 - (user.getNumReturned() * 10) + " Points until you get a free item");
                    alert.showAndWait();
                    return;
                }
            }
        }

        final int[] selectedCount = {0};
        for (ItemModel items : itemModelArrayList) {
            SelectableCard selectableCard = new SelectableCard();

            selectableCard.setText(items.toString());
            selectableCard.setUserData(items.getRentalType());
            selectableCard.setId(items.getID());


            String fileName = itemImageFile.get(items.getTitle());
            URL resourceUrl = getClass().getResource("/com/example/hello2/Images/" + fileName);
            if (resourceUrl != null) {
                Image image = new Image(resourceUrl.toExternalForm());
                selectableCard.setImage(image);
            }
            if (items.getCopies() == 0) {
                selectableCard.cardSetDisable(true);
            } else {
                int finalPoints = points;
                selectableCard.setOnMouseClicked((event) -> {
                    if (!selectableCard.cardIsDisabled()) {
                        if (selectedCount[0] > 0 && selectableCard.isSelected()) {
                            selectedCount[0]--;
                            selectableCard.setSelected(false);
                        } else if (!selectableCard.isSelected() && selectedCount[0] < finalPoints / 10) {
                            selectedCount[0]++;
                            selectableCard.setSelected(true);
                        }
                    }
                });
            }
            setupHoverEffect(selectableCard, items);
            cardList.add(selectableCard);
            flowPane.getChildren().add(selectableCard);
        }

        dvdButton.setOnAction(event -> {
            // Filter items based on DVD type
            flowPane.getChildren().clear();
            for (SelectableCard card : cardList) {
                if (Objects.equals(card.getUserData(), "DVD")) {
                    flowPane.getChildren().add(card);
                }
            }
        });


        recordButton.setOnAction(event -> {
            // Filter items based on Record type
            flowPane.getChildren().clear();
            for (SelectableCard card : cardList) {
                if (Objects.equals(card.getUserData(), "Record")) {
                    flowPane.getChildren().add(card);
                }
            }
        });

        gameButton.setOnAction(event -> {
            // Filter items based on Game type
            flowPane.getChildren().clear();
            for (SelectableCard card : cardList) {
                if (Objects.equals(card.getUserData(), "Game")) {
                    flowPane.getChildren().add(card);
                }
            }
        });


        allButton.setOnAction(event -> {
            // Show all items
            flowPane.getChildren().clear();
            flowPane.getChildren().addAll(cardList);

        });


        for (SelectedItems items : selectedItemsReader.readFileSelectedItems()) {
            for (SelectableCard card : cardList) {
                if (Objects.equals(items.getID(), getUserID())) {
                    for (String sd : items.getSelectedItemsList()) {
                        if (Objects.equals(sd, card.getUserData())) {
                            card.setDisable(true);
                        }
                    }
                }
            }
        }

        scrollPane.setContent(flowPane);

        HBox buttonContainer = new HBox(10);
        buttonContainer.getChildren().addAll(dvdButton, recordButton, gameButton, allButton);

        // Create a VBox to hold the button container and flow pane
        VBox contentContainer = new VBox();
        contentContainer.getChildren().addAll(buttonContainer, scrollPane);
        contentContainer.setStyle("-fx-background-color: #e6becd;");

        // Create a new Dialog
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setHeaderText("Select an item from the list:");
        ButtonType confirmButton = new ButtonType("Confirm", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().setAll(confirmButton, cancelButton);

        // Set the content of the dialog to the contentContainer VBox
        dialog.getDialogPane().setContent(contentContainer);

        Optional<ButtonType> result = dialog.showAndWait();

        ArrayList<String> tempArray = new ArrayList<>();
        for (SelectableCard card : cardList) {
            if (card.isSelected()) {
                tempArray.add(card.getId());
            }
        }

        if (result.isPresent() && result.get() == confirmButton) {
            for (UserModel user : userFileReader.getUserList()) {
                if (Objects.equals(user.getId(), getUserID())) {
                    user.setNumReturned(user.getNumReturned() - tempArray.size() * 10);
                    Points.setText("Current Points: " + (user.getNumReturned() * 10));
                    usersFileWriter.UserWriteFile(userFileReader.getUserList());
                }
            }
            // Confirm button is clicked
            // decrement copies value of selected item
            ArrayList<ItemModel> content = itemsFileReader.getItemList();
            for (SelectableCard card : cardList) {
                for (ItemModel item : content) {
                    if (card.getText().equals(item.toString()) && card.isSelected()) {
                        item.setCopies(item.getCopies() - 1); // decrement the copies value
                        itemsFileWriter.ItemsWriteFile(content); // write the updated items to the file
                        break;
                    }
                }
            }

            for (SelectedItems list : selectedItemsReader.getSelectedItemsList()) {
                if (list.getSelectedItemsList().isEmpty() && Objects.equals(list.getID(), ID)) {
                    list.setSelectedItemsList(tempArray);
                    selectedItemsWriter.SelectedItemsWriteFIle(selectedItemsReader.getSelectedItemsList());
                    break;
                }
                if (!(list.getSelectedItemsList().isEmpty()) && Objects.equals(list.getID(), ID)) {
                    list.getSelectedItemsList().addAll(tempArray);
                    selectedItemsWriter.SelectedItemsWriteFIle(selectedItemsReader.getSelectedItemsList());
                    break;
                }
            }
        }
    }

    @FXML
    public void rentItems() throws IOException {
        ItemsFileReader itemsFileReader = new ItemsFileReader();
        ItemsFileWriter itemsFileWriter = new ItemsFileWriter();
        SelectedItemsReader selectedItemsReader = new SelectedItemsReader();
        SelectedItemsWriter selectedItemsWriter = new SelectedItemsWriter();
        UsersFileWriter usersFileWriter = new UsersFileWriter();
        UserFileReader userFileReader = new UserFileReader();
        List<SelectableCard> cardList = new ArrayList<>();

        Button dvdButton = new Button("DVD");
        Button recordButton = new Button("Record");
        Button gameButton = new Button("Game");
        Button allButton = new Button("All");
        Button recommended = new Button("Recommended");


        FlowPane flowPane = new FlowPane();
        flowPane.setHgap(10); // Set horizontal gap between elements
        flowPane.setVgap(10); // Set vertical gap between elements
        flowPane.setAlignment(Pos.TOP_LEFT);
        flowPane.setPrefSize(860, 600);
        flowPane.setStyle("-fx-background-color: #e6becd;");
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setStyle("-fx-background-color: #e6becd;");

        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        ArrayList<ItemModel> itemModelArrayList = itemsFileReader.readFileItems();

        for (ItemModel items : itemModelArrayList) {
            SelectableCard selectableCard = new SelectableCard();

            selectableCard.setText(items.toString());
            selectableCard.setUserData(items.getRentalType());
            selectableCard.setId(items.getID());


            String fileName = itemImageFile.get(items.getTitle());
            URL resourceUrl = getClass().getResource("/com/example/hello2/Images/" + fileName);
            if (resourceUrl != null) {
                Image image = new Image(resourceUrl.toExternalForm());
                selectableCard.setImage(image);
            }


            if (items.getCopies() == 0) {
                selectableCard.cardSetDisable(true);
            } else {
                selectableCard.setOnMouseClicked(event -> {
                    if (!selectableCard.cardIsDisabled()) {
                        if (selectableCard.isSelected()) {
                            selectableCard.setSelected(false);
                        } else if (!selectableCard.isSelected()) {
                            selectableCard.setSelected(true);
                        }
                    }
                });
            }
            setupHoverEffect(selectableCard, items);
            cardList.add(selectableCard);
            flowPane.getChildren().add(selectableCard);
        }


        dvdButton.setOnAction(event -> {
            // Filter items based on DVD type
            flowPane.getChildren().clear();
            for (SelectableCard card : cardList) {
                if (Objects.equals(card.getUserData(), "DVD")) {
                    flowPane.getChildren().add(card);
                }
            }
        });


        recordButton.setOnAction(event -> {
            // Filter items based on Record type
            flowPane.getChildren().clear();
            for (SelectableCard card : cardList) {
                if (Objects.equals(card.getUserData(), "Record")) {
                    flowPane.getChildren().add(card);
                }
            }
        });

        gameButton.setOnAction(event -> {
            // Filter items based on Game type
            flowPane.getChildren().clear();
            for (SelectableCard card : cardList) {
                if (Objects.equals(card.getUserData(), "Game")) {
                    flowPane.getChildren().add(card);
                }
            }
        });

        recommended.setOnAction(event -> {
            int itemCount = 5; // Number of random items to select
            List<SelectableCard> randomItems = getRandomItems(cardList, itemCount);

            flowPane.getChildren().clear();
            flowPane.getChildren().addAll(randomItems);
        });

        allButton.setOnAction(event -> {
            // Show all items
            flowPane.getChildren().clear();
            flowPane.getChildren().addAll(cardList);

        });


        for (SelectedItems items : selectedItemsReader.readFileSelectedItems()) {
            for (SelectableCard card : cardList) {
                if (Objects.equals(items.getID(), getUserID())) {
                    for (String sd : items.getSelectedItemsList()) {
                        if (Objects.equals(sd, card.getId())) {
                            card.cardSetDisable(true);
                        }
                    }
                }
            }
        }
        scrollPane.setContent(flowPane);

        // decrement copies value of selected item
        HBox buttonContainer = new HBox(10);
        buttonContainer.getChildren().addAll(dvdButton, recordButton, gameButton, allButton, recommended);

        // Create a VBox to hold the button container and flow pane
        VBox contentContainer = new VBox();
        contentContainer.getChildren().addAll(buttonContainer, scrollPane);
        contentContainer.setStyle("-fx-background-color: #e6becd;");

        // Create a new Dialog
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setHeaderText("Select an item from the list:");
        ButtonType confirmButton = new ButtonType("Confirm", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().setAll(confirmButton, cancelButton);

        // Set the content of the dialog to the contentContainer VBox
        dialog.getDialogPane().setContent(contentContainer);


        Optional<ButtonType> result = dialog.showAndWait();

        ArrayList<String> tempArray = new ArrayList<>();
        for (SelectableCard card : cardList) {
            if (card.isSelected()) {
                tempArray.add(card.getId());
            }
        }
        float total = 0;
        for (ItemModel items : itemModelArrayList) {
            for (SelectableCard card : cardList) {
                if (Objects.equals(card.getId(), items.getID()) && card.isSelected()) {
                    total += items.getFee();
                }
            }
        }

        if (result.isPresent() && result.get() == confirmButton) {
            // Confirm button is clicked
            for (UserModel user : userFileReader.readFileUser()) {
                if (Objects.equals(user.getId(), getUserID())) {
                    if (user.getBalance() >= total) {
                        user.setBalance(user.getBalance() - total);
                        usersFileWriter.UserWriteFile(userFileReader.getUserList());
                        Balance.setText("Balance: $" + String.format("%.2f", user.getBalance()));
                    } else {
                        Alert alerts = new Alert(Alert.AlertType.ERROR);
                        alerts.setTitle("Insufficient Balance");
                        alerts.setHeaderText(null);
                        alerts.setContentText("Not enough money");
                        alerts.showAndWait();
                        return;
                    }
                }
            }
            // decrement copies value of selected item
            ArrayList<ItemModel> content = itemsFileReader.getItemList();
            for (SelectableCard card : cardList) {
                for (ItemModel item : content) {
                    if (card.getText().equals(item.toString()) && card.isSelected()) {
                        item.setCopies(item.getCopies() - 1); // decrement the copies value
                        itemsFileWriter.ItemsWriteFile(content); // write the updated items to the file
                        break;
                    }
                }
            }

            for (SelectedItems list : selectedItemsReader.getSelectedItemsList()) {
                if (list.getSelectedItemsList().isEmpty() && Objects.equals(list.getID(), ID)) {
                    list.setSelectedItemsList(tempArray);
                    selectedItemsWriter.SelectedItemsWriteFIle(selectedItemsReader.getSelectedItemsList());
                    break;
                }
                if (!(list.getSelectedItemsList().isEmpty()) && Objects.equals(list.getID(), ID)) {
                    list.getSelectedItemsList().addAll(tempArray);
                    selectedItemsWriter.SelectedItemsWriteFIle(selectedItemsReader.getSelectedItemsList());
                    break;
                }
            }
        }
    }

    private void setupHoverEffect(SelectableCard selectableCard, ItemModel item) {
        Tooltip tooltip = new Tooltip(item.getDescription());
        tooltip.setWrapText(true);
        tooltip.setMaxWidth(400);

        // Add the new hover effect without overwriting the existing one
        EventHandler<MouseEvent> enterHandler = event -> {
            Point2D location = selectableCard.localToScreen(selectableCard.getBoundsInLocal().getMaxX(), selectableCard.getBoundsInLocal().getMaxY());
            tooltip.show(selectableCard, location.getX(), location.getY());
        };

        EventHandler<MouseEvent> exitHandler = event -> {
            tooltip.hide();
        };

        // Combine the new hover effect with the existing one
        selectableCard.addEventHandler(MouseEvent.MOUSE_ENTERED, enterHandler);
        selectableCard.addEventHandler(MouseEvent.MOUSE_EXITED, exitHandler);
    }


    public void Back() throws IOException {
        Path path = Paths.get("src/main/resources/com/example/hello2/FXML/LoginSignup.fxml");
        FXMLLoader loader = new FXMLLoader(path.toUri().toURL());
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) back.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void Return() throws IOException {
        SelectedItemsReader selectedItemsReader = new SelectedItemsReader();
        SelectedItemsWriter selectedItemsWriter = new SelectedItemsWriter();

        UserFileReader userFileReader = new UserFileReader();
        UsersFileWriter usersFileWriter = new UsersFileWriter();

        ItemsFileReader itemsFileReader = new ItemsFileReader();
        ItemsFileWriter itemsFileWriter = new ItemsFileWriter();

        List<SelectableCard> cardList = new ArrayList<>();


        FlowPane flowPane = new FlowPane();
        flowPane.setHgap(10); // Set horizontal gap between elements
        flowPane.setVgap(10); // Set vertical gap between elements
        flowPane.setAlignment(Pos.TOP_LEFT);
        flowPane.setPrefSize(860, 600);
        flowPane.setStyle("-fx-background-color: #e6becd;");
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setStyle("-fx-background-color: #e6becd;");

        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        ArrayList<ItemModel> itemModelArrayList = itemsFileReader.readFileItems();

        for (SelectedItems temp : selectedItemsReader.readFileSelectedItems()) {
            for (ItemModel items : itemModelArrayList) {
                if (temp.getSelectedItemsList().contains(items.getID()) && Objects.equals(temp.getID(), ID)) {
                    SelectableCard selectableCard = new SelectableCard();
                    selectableCard.setUserData(items.getID());
                    selectableCard.setText(items.toString());

                    String fileName = itemImageFile.get(items.getTitle());
                    URL resourceUrl = getClass().getResource("/com/example/hello2/Images/" + fileName);
                    if (resourceUrl != null) {
                        Image image = new Image(resourceUrl.toExternalForm());
                        selectableCard.setImage(image);
                    }

                    if (items.getCopies() == 0) {
                        selectableCard.setDisable(false);
                    }
                    selectableCard.setOnMouseClicked(event -> {
                        if (!selectableCard.cardIsDisabled()) {
                            if (selectableCard.isSelected()) {
                                selectableCard.setSelected(false);
                            } else if (!selectableCard.isSelected()) {
                                selectableCard.setSelected(true);
                            }
                        }
                    });

                    cardList.add(selectableCard);
                    flowPane.getChildren().addAll(selectableCard);
                }
            }
        }

        scrollPane.setContent(flowPane);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Select an item from the list:");
        alert.getDialogPane().setContent(scrollPane);
        alert.showAndWait();

        ArrayList<ItemModel> content = itemsFileReader.getItemList();
        for (SelectableCard selectableCard : cardList) {
            for (ItemModel item : content) {
                if (selectableCard.getText().equals(item.toString()) && selectableCard.isSelected()) {
                    item.setCopies(item.getCopies() + 1); // decrement the copies value
                    itemsFileWriter.ItemsWriteFile(content); // write the updated items to the file
                    break;
                }
            }
        }
        ArrayList<String> tempArray = new ArrayList<>();
        for (SelectableCard selectablecard : cardList) {
            if (selectablecard.isSelected()) {
                tempArray.add((String) selectablecard.getUserData());
            }
        }

        for (UserModel temp : userFileReader.readFileUser()) {
            for (SelectedItems list : selectedItemsReader.getSelectedItemsList()) {
                if (Objects.equals(list.getID(), ID) && Objects.equals(temp.getId(), ID) && !tempArray.isEmpty()) {
                    list.getSelectedItemsList().removeAll(tempArray);
                    temp.setNumReturned(temp.getNumReturned() + tempArray.size());
                    Points.setText("Current Points: " + (temp.getNumReturned() * 10));
                    freebutton.setVisible(temp.getNumReturned() >= 10);
                    usersFileWriter.UserWriteFile(userFileReader.getUserList());
                    selectedItemsWriter.SelectedItemsWriteFIle(selectedItemsReader.getSelectedItemsList());
                }
            }
        }
    }

    @FXML
    public void Deposit() throws IOException {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Deposit");
        dialog.setHeaderText("Enter the amount to deposit:");
        dialog.setContentText("Amount:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            String input = result.get();
            try {
                double amount = Double.parseDouble(input);
                if (amount <= 0) {
                    showErrorAlert("Invalid amount. Please enter a positive number.");
                    return;
                } else if (input.length() > 10) {
                    showErrorAlert("Invalid amount");
                    return;
                }

                // Update the user's balance
                UserFileReader userFileReader = new UserFileReader();
                UsersFileWriter usersFileWriter = new UsersFileWriter();
                ArrayList<UserModel> userList = userFileReader.readFileUser();

                for (UserModel user : userList) {
                    if (user.getId().equals(getUserID())) {
                        user.setBalance((float) (user.getBalance() + amount));
                        usersFileWriter.UserWriteFile(userList);
                        Balance.setText("Balance: $" + String.format("%.2f", user.getBalance()));
                        showInfoAlert("Deposit successful. New balance: $" + String.format("%.2f", user.getBalance()));
                        return;
                    }
                }

                showErrorAlert("User not found.");
            } catch (NumberFormatException e) {
                showErrorAlert("Invalid input. Please enter a valid number.");
            }
        }
    }

    private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showInfoAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
