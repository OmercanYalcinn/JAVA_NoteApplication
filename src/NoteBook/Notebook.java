package NoteBook;

/**
 * Notebook sınıfı, notları yöneten iş mantığı katmanıdır.
 * Not ekleme, silme, güncelleme, arama gibi işlemler burada yapılır.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class Notebook {

	private List<Note> notes;	// List of a Notes type of Note
	
	// Constructor of Notebook and its contains only Array List of notes
	public Notebook() {
		this.notes = new ArrayList<>();
	}
	
	// For getting all notes into the Array List we need to use getAllNotes getter methods so basicly we are looping all the notes
	// We are gicing a copy of notes in this get method, instead of giving it with basic return of notes, we are returning them as an array list
	// Kind of a photocopy machine method
	public List<Note> getAllNotes(){ return new ArrayList<>(notes);	}
	
	// Adding new Note
	public void addNote(Note note) {
		if (note != null) {
            notes.add(note);
        }
	}
	
	// Search the note according to their heading/title
	public List<Note> searchByTitle(String keywordInput){
		List<Note> result = new ArrayList<>();
		
		// Looking to all the notes by their title, no mater what their letter (lower or capital, if there is a result, so if the notes contains it) adding it into the new array list that is created into that method too
		for (Note note : notes) {
            if (note.getTitle().toLowerCase().contains(keywordInput.toLowerCase())) {
                result.add(note);
            }
        }
		// and giving the result after added part is done into the array list
		return result;
	}
	
	// ID'ye göre not sil
    public boolean deleteNoteById(UUID id) {
        return notes.removeIf(note -> note.getId().equals(id));
    }
    // ID'ye göre not bul
    public Optional<Note> findNoteById(UUID id) {
        return notes.stream()
                .filter(note -> note.getId().equals(id))
                .findFirst();
    }
	
	// Not içeriğini güncelle (örnek)
    public boolean updateNote(UUID id, String newTitle, String newContent) {
        Optional<Note> optionalNote = findNoteById(id);
        if (optionalNote.isPresent()) {
            Note note = optionalNote.get();
            if (newTitle != null) note.setTitle(newTitle);
            if (newContent != null) note.setContent(newContent);
            return true;
        }
        return false;
    }

    // Not defterini dosyaya kaydet (NoteManager'la ileride bağlanacak)
    public void saveAllNotes() {
        NoteManager.saveNotes(notes);
    }

    // Dosyadan notları yükle
    public void loadAllNotes() {
        notes = NoteManager.loadNotes();
    }
}
