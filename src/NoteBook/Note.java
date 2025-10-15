package NoteBook;
/*
 * A note object actually contains two information; title and content.
 * This Note Class basicly shows how a note is going to be, like a template.
 * That's why we cerated this class.
 * 
 * We use Constructor Method for deciding how the future notes are going to be that contain a title and a content.
 * "Bu notun başlığı bana verilen başlık olsun." olarak da açıklanabilir.
 * 
 * Getter methods are used for the accessbility of private variables but cannot be changed. We could reach title with
 * String t = note.getTitile(); We cannot reach it directly and thanks to that the data is secure.
 * 
 * While we are trying to print the note, thanks to toString() method, we are able to see titles of notes. We only return it.
 * */

import java.util.UUID;

public class Note {
	private UUID _id;
	private String _title;
	private String _content;
	
	// Constructor of Note
	public Note(String title, String content) {
		this._id = UUID.randomUUID();	// Unique ID created automaticlly
		this._title = title;
		this._content = content;
	}
	
	// Getter and Setters
	public UUID getId() { return _id; }
	public String getTitle() { return _title; }
	public String getContent() { return _content; }
	
	public String setTitle(String newTitle) {
		_title = newTitle;
		return newTitle;
	}
	public String setContent(String newContent) {
		_content = newContent;
		return newContent;
	}
	
	@Override
    public String toString() {
        return _title;
    }
}
