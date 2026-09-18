package model;

public class Note {

    private int noteId;
    private String content;

    public Note(int noteId, String content) {

        this.noteId = noteId;
        this.content = content;
    }

    public int getNoteId() {

        return noteId;
    }

    public String getContent() {

        return content;
    }

    public void displayNote() {

        System.out.println(
                noteId + ". " + content
        );
    }
}
    
