package tn.esprit.pidev.entities;

public class Note {
    private int id;
    private String testName;
    private String studentName;
    private int note;
    private String result;

    public Note() {
    }

    public Note(String testName, String studentName, int note, String result) {
        this.testName = testName;
        this.studentName = studentName;
        this.note = note;
        this.result = result;
    }

    public Note(int id, String testName, String studentName, int note, String result) {
        this.id = id;
        this.testName = testName;
        this.studentName = studentName;
        this.note = note;
        this.result = result;
    }

    public Note(String studentName, String result) {
        this.studentName = studentName;
        this.result = result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
