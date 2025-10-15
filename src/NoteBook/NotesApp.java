package NoteBook;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class NotesApp extends Application {

	private Notebook notebook;
	private ListView<Note> noteListView;
	private TextField titleField;
	private TextArea contentArea;

	@Override
	public void start(Stage stage) {
		// Program açıldığında önce diskteki kayıtlı notları yüklüyoruz
		notebook = new Notebook();
		notebook.loadAllNotes();

		// UI bileşenleri - Notları Liste şeklinde gösteriyoruz - getAllNotes ile tüm notları getirip listeye ekliyor
		noteListView = new ListView<>();
		noteListView.getItems().addAll(notebook.getAllNotes());
		
		// Yazı alanları oluşturuluyor ve title ve content yazmak için iki kutu gösteriliyor
		titleField = new TextField();
		titleField.setPromptText("Note title...");

		contentArea = new TextArea();
		contentArea.setPromptText("Note content...");

		// Butonlarımız oluşturuluyor
		Button addButton = new Button("Add");
		Button deleteButton = new Button("Delete");
		Button saveButton = new Button("Save");
		
		// HBox temelde butonlarımızı yatay düzleme dizer ve yan yana gösterir
		HBox buttonBox = new HBox(10, addButton, deleteButton, saveButton);
		buttonBox.setPadding(new Insets(10));
		
		// VBox diğer her şeyi dikey yerleştirir, alt alta dizer ve gösterir
		VBox layout = new VBox(10, noteListView, titleField, contentArea, buttonBox);
		layout.setPadding(new Insets(10));

		// Liste bir şey seçildiğinde ne olur?
		// O notun başlığı ve içeriği sağdaki kutulara dolduruluyor, böylece seçilen notu düzenleyebilirsin demek oluyor
		noteListView.getSelectionModel().selectedItemProperty().addListener((obs, oldNote, newNote) -> {
			if (newNote != null) {
				titleField.setText(newNote.getTitle());
				contentArea.setText(newNote.getContent());
			}
		});

		// Add butonu
		addButton.setOnAction(e -> {
			if (!titleField.getText().isEmpty()) {
				Note newNote = new Note(titleField.getText(), contentArea.getText());
				notebook.addNote(newNote);
				noteListView.getItems().add(newNote);
				notebook.saveAllNotes();
				titleField.clear();
				contentArea.clear();
			}
		});

		// Delete butonu
		deleteButton.setOnAction(e -> {
			Note selected = noteListView.getSelectionModel().getSelectedItem();
			if (selected != null) {
				noteListView.getItems().remove(selected);
				notebook.saveAllNotes();
				titleField.clear();
				contentArea.clear();
			}
		});

		// Save butonu (tüm notları kaydeder)
		saveButton.setOnAction(e -> {
			notebook.saveAllNotes();
			new Alert(Alert.AlertType.INFORMATION, "Notes saved successfully!").showAndWait();
		});

		// Pencere ayarları
		Scene scene = new Scene(layout, 600, 400);
		stage.setScene(scene);
		stage.setTitle("Notebook App");
		stage.show();
	}

}
