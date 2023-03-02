package PlaySongDemo;

/**
 * This code will play any song assuming that file is in folder songfiles. 
 * 
 * Programmer Rick Mercer and Frederic Geile
 */
import java.io.File;
import java.net.URI;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class PlayAnMP3 extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	private int songsPlayed = 0;

	@Override
	public void start(Stage stage) throws Exception {
		GridPane pane = new GridPane();
		// Song Choice
		String path = "songfiles/DanseMacabreViolinHook.mp3";
		Label pathLabel = new Label(path);
		Label nowPlaying = new Label("Now Playing");

		// Setting Labels to be centered
		GridPane.setHalignment(nowPlaying, javafx.geometry.HPos.CENTER);
		GridPane.setHalignment(pathLabel, javafx.geometry.HPos.CENTER);
		pane.setAlignment(Pos.CENTER);

		// Adding labels to Pane
		pane.add(pathLabel, 1, 2);
		pane.add(nowPlaying, 1, 1);
		playASong(path);

		// Put the pane in a sized Scene and show the GUI
		Scene scene = new Scene(pane, 300, 85); // 255 pixels wide, 85 pixels tall
		stage.setScene(scene);

		// Don't forget to show the running app:
		stage.show();
	}

	private void playASong(String path) {

		// Need a File and URI object so the path works on all OSs
		File file = new File(path);
		URI uri = file.toURI();
		System.out.println(uri);
		// Play one mp3 and and have code run when the song ends
		Media media = new Media(uri.toString());
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		mediaPlayer.play();

		mediaPlayer.setOnEndOfMedia(new Waiter());
		System.out.println("You may need to shut this App down");

	}

	private class Waiter implements Runnable {
		@Override
		// Waits until the song that is being played has ended, will
		// automatically terminate the program
		public void run() {
			songsPlayed++;
			System.out.println("Song ended, play song #" + songsPlayed);
			Platform.exit();
		}
	}
}