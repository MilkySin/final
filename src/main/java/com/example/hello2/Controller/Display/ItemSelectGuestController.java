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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ItemSelectGuestController {
    @FXML
    public Label Account;
    private String ID;
    public Text Balance;
    public ScrollPane ownedItemsDisplay;
    public Text Welcome;
    public Button back;

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getUserID() {
        return ID;
    }

    //Read through both files, if selected is empty, add users from user lists
    public void setInitialize() throws IOException {
        UserFileReader userFileReader = new UserFileReader();
        SelectedItemsWriter selectedItemsWriter = new SelectedItemsWriter();

        ArrayList<SelectedItems> selectedItemsArrayList = new SelectedItemsReader().readFileSelectedItems();
        for (UserModel user : userFileReader.readFileUser()) {
            if (Objects.equals(user.getId(), getUserID())) {
                Balance.setText("Balance: $" + user.getBalance());
                Welcome.setText("Welcome: " + user.getUsername());
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
                if (temp.getSelectedItemsList().contains(items.getID()) && Objects.equals(temp.getID(), ID)) {
                    Text owned = new Text(items.toString());
                    owned.setStyle("-fx-fill: white;"); // Set text color of the Text
                    owned.setFont(Font.font(14));
                    HBox itemBox = new HBox();
                    itemBox.getChildren().add(owned);
                    flowPane.getChildren().add(itemBox);
                }
            }
        }

        ownedItemsDisplay.setContent(flowPane);
    }

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

        Path pathCasablanca = Paths.get("src/main/resources/com/example/hello2/Images/Casablanca.png");
        Path pathStarWars = Paths.get("src/main/resources/com/example/hello2/Images/StarWars.png");
        Path pathEasyRider = Paths.get("src/main/resources/com/example/hello2/Images/EasyRider.png");
        Path pathBladeRunner = Paths.get("src/main/resources/com/example/hello2/Images/BladeRunner.png");
        Path pathTheDarkKnight = Paths.get("src/main/resources/com/example/hello2/Images/TheDarkKnight.png");
        Path pathFightClub = Paths.get("src/main/resources/com/example/hello2/Images/FightClub.png");
        Path pathIT = Paths.get("src/main/resources/com/example/hello2/Images/IT.png");
        Path pathOverwatch = Paths.get("src/main/resources/com/example/hello2/Images/overwatch.png");
        Path pathRedDeadRedemption = Paths.get("src/main/resources/com/example/hello2/Images/RDR.png");
        Path pathTheLastOfUs = Paths.get("src/main/resources/com/example/hello2/Images/ThelastOfUS.png");

        Image Casablanca = new Image(String.valueOf(pathCasablanca.toUri()));
        Image starWars = new Image(String.valueOf(pathStarWars.toUri()));
        Image EasyRider = new Image(String.valueOf(pathEasyRider.toUri()));
        Image BladeRunner = new Image(String.valueOf(pathBladeRunner.toUri()));
        Image TheDarkKnight = new Image(String.valueOf(pathTheDarkKnight.toUri()));
        Image FightClub = new Image(String.valueOf(pathFightClub.toUri()));
        Image IT = new Image(String.valueOf(pathIT.toUri()));
        Image Overwatch = new Image(String.valueOf(pathOverwatch.toUri()));
        Image RedDeadRedemption = new Image(String.valueOf(pathRedDeadRedemption.toUri()));
        Image TheLastOfUs = new Image(String.valueOf(pathTheLastOfUs.toUri()));

        FlowPane flowPane = new FlowPane();
        flowPane.setHgap(10);
        flowPane.setVgap(10);
        flowPane.setAlignment(Pos.TOP_LEFT);
        flowPane.setPrefSize(860, 600);
        flowPane.setStyle("-fx-background-color: #e6becd;");
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setStyle("-fx-background-color: #e6becd;");

        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        ArrayList<ItemModel> itemModelArrayList = itemsFileReader.readFileItems();

        int maxSelect = 2;
        for (SelectedItems items : selectedItemsReader.readFileSelectedItems()) {
            if (Objects.equals(items.getID(), getUserID())) {
                maxSelect -= items.getSelectedItemsList().size();
            }
        }

        final int[] selectedCount = {0};
        for (ItemModel items : itemModelArrayList) {
            SelectableCard selectableCard = new SelectableCard();
            ;

            selectableCard.setText(items.toString());


            if (Objects.equals(items.getTitle(), "Casablanca")) {
                selectableCard.setImage(Casablanca);
            } else if (Objects.equals(items.getTitle(), "Star Wars")) {
                selectableCard.setImage(starWars);
            } else if (Objects.equals(items.getTitle(), "Easy Rider")) {
                selectableCard.setImage(EasyRider);
            } else if (Objects.equals(items.getTitle(), "Blade Runner")) {
                selectableCard.setImage(BladeRunner);
            } else if (Objects.equals(items.getTitle(), "The Dark Knight")) {
                selectableCard.setImage(TheDarkKnight);
            } else if (Objects.equals(items.getTitle(), "Fight Club")) {
                selectableCard.setImage(FightClub);
            } else if (Objects.equals(items.getTitle(), "IT")) {
                selectableCard.setImage(IT);
            } else if (Objects.equals(items.getTitle(), "Overwatch")) {
                selectableCard.setImage(Overwatch);
            } else if (Objects.equals(items.getTitle(), "The Last of Us")) {
                selectableCard.setImage(TheLastOfUs);
            } else if (Objects.equals(items.getTitle(), "RDR")) {
                selectableCard.setImage(RedDeadRedemption);
            }
            if (items.getCopies() == 0 || Objects.equals(items.getLoanType(), "2 Days Loan")) {
                selectableCard.cardSetDisable(true);
            } else {
                int finalMaxSelect = maxSelect;
                selectableCard.setOnMouseClicked(event -> {
                    if (!selectableCard.cardIsDisabled()) {
                        if (selectableCard.isSelected() && selectedCount[0] > 0) {
                            selectedCount[0]--;
                            selectableCard.setSelected(false);
                        } else if (!selectableCard.isSelected() && selectedCount[0] < finalMaxSelect) {
                            selectedCount[0]++;
                            selectableCard.setSelected(true);
                        }
                    }
                });
            }
            cardList.add(selectableCard);
            flowPane.getChildren().add(selectableCard);
        }

        dvdButton.setOnAction(event -> {
            // Filter items based on DVD type
            flowPane.getChildren().clear();
            for (ItemModel item : itemModelArrayList) {
                if (Objects.equals(item.getRentalType(), "DVD")) {
                    SelectableCard DVDcard = new SelectableCard();

                    if (Objects.equals(item.getTitle(), "The Dark Knight")) {
                        DVDcard.setImage(TheDarkKnight);
                    } else if (Objects.equals(item.getTitle(), "Fight Club")) {
                        DVDcard.setImage(FightClub);
                    } else if (Objects.equals(item.getTitle(), "IT")) {
                        DVDcard.setImage(IT);
                    }
                    DVDcard.setText(item.toString());
                    if (item.getCopies() == 0 || Objects.equals(item.getLoanType(), "2 Days Loan")) {
                        DVDcard.cardSetDisable(true);
                    }


                    flowPane.getChildren().add(DVDcard);
                }
            }

        });


        recordButton.setOnAction(event -> {
            // Filter items based on Record type
            flowPane.getChildren().clear();
            for (ItemModel item : itemModelArrayList) {
                if (Objects.equals(item.getRentalType(), "Record")) {
                    SelectableCard RecordCard = new SelectableCard();
                    if (Objects.equals(item.getTitle(), "Casablanca")) {
                        RecordCard.setImage(Casablanca);
                    } else if (Objects.equals(item.getTitle(), "Star Wars")) {
                        RecordCard.setImage(starWars);
                    } else if (Objects.equals(item.getTitle(), "Easy Rider")) {
                        RecordCard.setImage(EasyRider);
                    } else if (Objects.equals(item.getTitle(), "Blade Runner")) {
                        RecordCard.setImage(BladeRunner);}
                    RecordCard.setText(item.toString());
                    if (item.getCopies() == 0 || Objects.equals(item.getLoanType(), "2 Days Loan")) {
                        RecordCard.cardSetDisable(true);
                    }
                    flowPane.getChildren().add(RecordCard);
                }
            }


        });

        gameButton.setOnAction(event -> {
            // Filter items based on Game type
            flowPane.getChildren().clear();
            for (ItemModel item : itemModelArrayList) {
                SelectableCard GameCard = new SelectableCard();
                if (Objects.equals(item.getRentalType(), "Game")) {
                    if (Objects.equals(item.getTitle(), "Overwatch")) {
                        GameCard.setImage(Overwatch);
                    } else if (Objects.equals(item.getTitle(), "The Last of Us")) {
                        GameCard.setImage(TheLastOfUs);
                    } else if (Objects.equals(item.getTitle(), "RDR")) {
                        GameCard.setImage(RedDeadRedemption);
                    }

                    GameCard.setText(item.toString());
                    if (item.getCopies() == 0 || Objects.equals(item.getLoanType(), "2 Days Loan")) {
                        GameCard.cardSetDisable(true);
                    }
                    flowPane.getChildren().add(GameCard);
                }

            }

        });


        allButton.setOnAction(event -> {
            // Show all items
            flowPane.getChildren().clear();
            flowPane.getChildren().addAll(cardList);
        });

        for (SelectedItems items : selectedItemsReader.getSelectedItemsList()) {
            for (SelectableCard card : cardList) {
                if (Objects.equals(items.getID(), getUserID())) {
                    for (String sd : items.getSelectedItemsList()) {
                        if (Objects.equals(sd, card.getText())) {
                            card.cardSetDisable(true);
                        }
                    }
                }
            }
        }

        scrollPane.setContent(flowPane);

        // Create an HBox to hold the buttons
        HBox buttonContainer = new HBox(10);
        buttonContainer.getChildren().addAll(dvdButton, recordButton, gameButton, allButton);

        // Create a VBox to hold the button container and flow pane
        VBox contentContainer = new VBox();
        contentContainer.getChildren().addAll(buttonContainer, scrollPane);

        // Create a new Dialog
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setHeaderText("Select an item from the list:");
        ButtonType confirmButton = new ButtonType("Confirm", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().setAll(confirmButton, cancelButton);

        // Set the content of the dialog to the contentContainer VBox
        dialog.getDialogPane().setContent(contentContainer);

        Optional<ButtonType> result = dialog.showAndWait();

        if (result.isPresent() && result.get() == confirmButton) {
            // Confirm button is clicked
            // Perform further actions here
        }
    }


//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setHeaderText("Select an item from the list:");
//        alert.getDialogPane().setContent(scrollPane);
//        alert.showAndWait();
//
//        float total = 0;
//        for (ItemModel items : itemModelArrayList) {
//            for (CheckBox checkBox : checkBoxList) {
//                if (Objects.equals(checkBox.getUserData(), items.getID()) && checkBox.isSelected()) {
//                    total += items.getFee();
//                }
//            }
//        }
//
//        for (UserModel user : userFileReader.readFileUser()) {
//            if (Objects.equals(user.getId(), getUserID())) {
//                if (user.getBalance() >= total) {
//                    user.setBalance(user.getBalance() - total);
//                    usersFileWriter.UserWriteFile(userFileReader.getUserList());
//                    Balance.setText("Balance: $" + user.getBalance());
//                } else {
//                    Alert alerts = new Alert(Alert.AlertType.ERROR);
//                    alerts.setTitle("Insufficient Balance");
//                    alerts.setHeaderText(null);
//                    alerts.setContentText("Not enough money");
//                    alerts.showAndWait();
//                    return;
//                }
//            }
//        }
//
//        // decrement copies value of selected item
//        ArrayList<ItemModel> content = itemsFileReader.getItemList();
//        for (CheckBox checkBox : checkBoxList) {
//            for (ItemModel item : content) {
//                if (checkBox.getText().equals(item.toString()) && checkBox.isSelected()) {
//                    item.setCopies(item.getCopies() - 1); // decrement the copies value
//                    itemsFileWriter.ItemsWriteFile(content); // write the updated items to the file
//                    break;
//                }
//            }
//        }
//
//
//        ArrayList<String> tempArray = new ArrayList<>();
//        for (CheckBox checkBox : checkBoxList) {
//            if (checkBox.isSelected()) {
//                tempArray.add((String) checkBox.getUserData());
//            }
//        }
//
//        for (SelectedItems list : selectedItemsReader.getSelectedItemsList()) {
//            if (list.getSelectedItemsList().isEmpty() && Objects.equals(list.getID(), ID)) {
//                list.setSelectedItemsList(tempArray);
//                selectedItemsWriter.SelectedItemsWriteFIle(selectedItemsReader.getSelectedItemsList());
//                break;
//            }
//            if (!(list.getSelectedItemsList().isEmpty()) && Objects.equals(list.getID(), ID)) {
//                list.getSelectedItemsList().addAll(tempArray);
//                selectedItemsWriter.SelectedItemsWriteFIle(selectedItemsReader.getSelectedItemsList());
//                break;
//            }
//        }

    public void Back() throws IOException {
        Path path = Paths.get("src/main/resources/com/example/hello2/LoginSignup.fxml");
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

        List<CheckBox> checkBoxList = new ArrayList<>();

        FlowPane flowPane = new FlowPane();
        flowPane.setHgap(10); // Set horizontal gap between elements
        flowPane.setVgap(10); // Set vertical gap between elements
        flowPane.setAlignment(Pos.TOP_LEFT);
        flowPane.setPrefSize(530, 400);
        ScrollPane scrollPane = new ScrollPane();

        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        ArrayList<ItemModel> itemModelArrayList = itemsFileReader.readFileItems();

        for (SelectedItems temp : selectedItemsReader.readFileSelectedItems()) {
            for (ItemModel items : itemModelArrayList) {
                if (temp.getSelectedItemsList().contains(items.getID()) && Objects.equals(temp.getID(), ID)) {
                    CheckBox checkBox = new CheckBox(items.toString());
                    checkBox.setUserData(items.getID());

                    HBox itemBox = new HBox();
                    if (items.getCopies() == 0) {
                        checkBox.setDisable(false);
                    }
                    checkBoxList.add(checkBox);
                    itemBox.getChildren().addAll(checkBox);
                    flowPane.getChildren().addAll(itemBox);
                }
            }
        }

        scrollPane.setContent(flowPane);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Select an item from the list:");
        alert.getDialogPane().setContent(scrollPane);
        alert.showAndWait();

        ArrayList<ItemModel> content = itemsFileReader.getItemList();
        for (CheckBox checkBox : checkBoxList) {
            for (ItemModel item : content) {
                if (checkBox.getText().equals(item.toString()) && checkBox.isSelected()) {
                    item.setCopies(item.getCopies() + 1); // decrement the copies value
                    itemsFileWriter.ItemsWriteFile(content); // write the updated items to the file
                    break;
                }
            }
        }
        ArrayList<String> tempArray = new ArrayList<>();
        for (CheckBox checkBox : checkBoxList) {
            if (checkBox.isSelected()) {
                tempArray.add((String) checkBox.getUserData());
            }
        }

        for (UserModel temp : userFileReader.readFileUser()) {
            for (SelectedItems list : selectedItemsReader.getSelectedItemsList()) {
                if (Objects.equals(list.getID(), ID) && Objects.equals(temp.getId(), ID) && !tempArray.isEmpty()) {
                    list.getSelectedItemsList().removeAll(tempArray);
                    temp.setNumReturned(temp.getNumReturned() + tempArray.size());
                    if (temp.getNumReturned() >= 3) {
                        temp.setAccountType("Regular");
                        Path path = Paths.get("src/main/resources/com/example/hello2/RegularUser.fxml");
                        FXMLLoader loader = new FXMLLoader(path.toUri().toURL());
                        Parent root = loader.load();
                        Scene scene = new Scene(root);
                        Stage stage = (Stage) Account.getScene().getWindow();
                        ItemSelectRegularController regularUserController = loader.getController(); // Create an
                        // instance
                        // of
                        // ItemSelectGuestController
                        regularUserController.setID(ID);// Set the ID value
                        regularUserController.setInitialize();
                        stage.setScene(scene);
                        stage.setResizable(false);
                        stage.show();
                        temp.setNumReturned(0);
                    }
                    usersFileWriter.UserWriteFile(userFileReader.getUserList());
                    selectedItemsWriter.SelectedItemsWriteFIle(selectedItemsReader.getSelectedItemsList());
                }
            }
        }
    }
}

