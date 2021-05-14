package tn.esprit.pidev.views;

import com.codename1.components.SpanLabel;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import tn.esprit.pidev.entities.Note;
import tn.esprit.pidev.entities.Test;
import tn.esprit.pidev.services.NoteService;


public class ShowTest extends Form {
    Form current;
    NoteService noteService = new NoteService();

    public ShowTest(Form previous, Test test) {
        /* *** *CONFIG SCREEN* *** */
        current = this;
        setTitle("TESTS");
        setLayout(BoxLayout.y());
        setScrollableY(false);
        /* *** *YOUR CODE GOES HERE* *** */
        Label nameLabel = new Label(test.getName());
        Label categoryLabel = new Label("Category: "+test.getCategory());
        SpanLabel contenuLabel = new SpanLabel("Q: "+test.getContenu());
        Label dateLabel = new Label("Date: "+test.getDate().toString());
        Label teacherNameLabel = new Label("Teacher: "+test.getTeacherName());
        Button takeTestButton = new Button("TAKE THE TEST");
        TextField studentNameTextField = new TextField();
        TextField answerTextField = new TextField(50);
        Button submitButton = new Button("Send The Answer");
        submitButton.addActionListener(evt -> {
            if (!studentNameTextField.getText().equals("") && !answerTextField.getText().equals("")) {
                noteService.addNote(new Note(studentNameTextField.getText(), answerTextField.getText()), test.getId());
                previous.showBack();
            } else {
                Dialog.show("INPUT ERROR", "Please fill in the blanks", "OK", null);
            }
        });
        takeTestButton.addActionListener(e -> {
            addAll(new Label("Student Name:"), studentNameTextField, new Label("Your answer"), answerTextField, submitButton);
            repaint();
            revalidate();
            contenuLabel.animate();
        });
        categoryLabel.getStyle().setPaddingLeft(6);
        teacherNameLabel.getStyle().setPaddingLeft(6);
        contenuLabel.getStyle().setPaddingLeft(6);
        dateLabel.getStyle().setPaddingLeft(6);

        addAll(nameLabel, categoryLabel ,teacherNameLabel, dateLabel, contenuLabel, takeTestButton);
        /* *** *BACK BUTTON* *** */
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }
}
