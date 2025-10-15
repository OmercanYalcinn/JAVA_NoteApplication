package NoteBook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * NoteManager dosya işlemlerinden sorumludur.
 * Yani notları kaydetme ve yükleme işlemlerini yapar.
 * 
 * Şu anda basit bir metin dosyası (notes.txt) kullanıyoruz.
 * Her satır bir notu temsil eder:
 * id|title|content
 */
public class NoteManager {
	
	private static final String FILE_NAME = "notes.txt";

	// Notları kaydeden metot
	public static void saveNotes(List<Note> notes) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
			for (Note note : notes) {
				// İçerikteki satır sonlarını kaçış karakteriyle değiştiriyoruz
				writer.write(note.getId() + "|" + note.getTitle() + "|" + note.getContent().replace("\n", "\\n"));
				writer.newLine();
			}
		} catch (IOException e) {
			System.err.println("Dosya kaydedilirken hata oluştu: " + e.getMessage());
		}
	}

	// Notları yükleyen metot
	public static List<Note> loadNotes() {
		List<Note> notes = new ArrayList<>();
		File file = new File(FILE_NAME);

		if (!file.exists()) {
			return notes; // Dosya yoksa boş liste dön
		}

		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split("\\|", 3);
				if (parts.length == 3) {
					UUID id = UUID.fromString(parts[0]);
					String title = parts[1];
					String content = parts[2].replace("\\n", "\n");
					Note note = new Note(title, content);
					notes.add(note);
				}
			}
		} catch (IOException e) {
			System.err.println("Dosya okunurken hata oluştu: " + e.getMessage());
		}

		return notes;
	}
}
