package com.example.hello2.Model;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class SelectableCard extends StackPane {
    private CheckBox checkBox;
    private Text text;
    private ImageView imageView;
    private Rectangle background = new Rectangle(160, 280);


    public SelectableCard() {
        checkBox = new CheckBox();
        checkBox.setManaged(false);
        checkBox.setVisible(false);

        text = new Text();
        text.setFill(Color.BLACK);
        text.setFont(Font.font("Arial", FontWeight.BOLD, 12));

        background.setFill(Color.DARKGRAY);
        background.setStroke(Color.BLACK);

        imageView = new ImageView();
        imageView.setFitWidth(130);
        imageView.setFitHeight(130);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageView.setCache(true);

        VBox content = new VBox(20, imageView, text);
        content.setPadding(new Insets(10, 10, -10, 10));
        content.setAlignment(Pos.BOTTOM_CENTER);

        setAlignment(Pos.CENTER);
        getChildren().addAll(background, checkBox, content);

        setOnMouseEntered(event -> {
            if (!cardIsDisabled() && !isSelected()) {
                background.setFill(Color.web("#c3c3d4"));
            }

        });
        setOnMouseExited(event -> {
            if (!cardIsDisabled() && !isSelected()) {
                background.setFill(Color.DARKGRAY);
            }
        });
    }

    public boolean isSelected() {
        return checkBox.isSelected();
    }

    public void setSelected(boolean selected) {
        checkBox.setSelected(selected);
        updateStyle();
    }


    public boolean isEmpty() {
        return text.getText().isEmpty();
    }

    public void setText(String itemText) {
        text.setText(itemText);
    }

    public String getText() {
        return text.getText();
    }

    public void setImage(Image image) {
        imageView.setImage(image);
    }

    public void cardSetDisable(boolean disable) {
        checkBox.setDisable(disable);
        updateStyle();
    }

    public boolean cardIsDisabled() {
        return checkBox.isDisable();
    }

    private void updateStyle() {
        if (isSelected()) {
            text.setFill(Color.BLACK);
        } else if (cardIsDisabled()) {
            text.setFill(Color.DARKGRAY);
            background.setFill(Color.GRAY);
        } else {
            text.setFill(Color.BLACK);
        }
    }
}
