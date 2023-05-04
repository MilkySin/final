package com.example.hello2.Controller;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;

public class Scene8Controller {

    @FXML
    private ProgressBar progressBar;

    @FXML
    public void startTask() {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                for (int i = 0; i < 100; i++) {
                    updateProgress(i, 100);
                    Thread.sleep(50);
                }
                return null;
            }
        };
        progressBar.progressProperty().bind(task.progressProperty());
        new Thread(task).start();
    }
}
