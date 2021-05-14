package tn.esprit.pidev.views;

import com.codename1.components.MultiButton;
import com.codename1.ui.Component;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import tn.esprit.pidev.entities.Test;
import tn.esprit.pidev.services.TestService;

import java.util.ArrayList;
import java.util.Collections;

public class TestForm extends Form {
    Form current;
    TestService testService = new TestService();
    ArrayList<Test> testArrayList = new ArrayList<>();

    public TestForm() {
        /* *** *CONFIG SCREEN* *** */
        current = this;
        setTitle("TESTS");
        setLayout(BoxLayout.y());
       // getStyle().setBgColor(0x000000);

        /* *** *YOUR CODE GOES HERE* *** */
        testArrayList = testService.showAll();
        showTests();
        /* *** *OVERFLOW MENU* *** */
        getToolbar().addCommandToOverflowMenu("Sort by Name", null, (evt)->{
            removeAll();
            Collections.sort(testArrayList, Test.nameComparator);
            showTests();
        });
        getToolbar().addCommandToOverflowMenu("Sort by Category", null, (evt)->{
            removeAll();
            Collections.sort(testArrayList, Test.categoryComparator);
            showTests();
        });
        /* *** *SEARCHBAR* *** */
        getToolbar().addSearchCommand(e -> {
            String text = (String) e.getSource();
            if (text == null || text.length() == 0) {
                // clear search
                for (Component cmp : getContentPane()) {
                    cmp.setHidden(false);
                    cmp.setVisible(true);
                }
                getContentPane().animateLayout(150);
            } else {
                text = text.toLowerCase();
                for (Component cmp : getContentPane()) {
                    MultiButton mb = (MultiButton) cmp;
                    String line1 = mb.getTextLine1();
                    String line2 = mb.getTextLine2();
                    boolean show = line1 != null && line1.toLowerCase().indexOf(text) > -1 ||
                            line2 != null && line2.toLowerCase().indexOf(text) > -1;
                    mb.setHidden(!show);
                    mb.setVisible(show);

                }
                getContentPane().animateLayout(150);
            }
        }, 4);
        /* *** *SIDE MENU* *** */
        getToolbar().addCommandToLeftSideMenu("", null, (evt) -> {});
        getToolbar().addCommandToLeftSideMenu("Home", null, (evt) -> new HomeForm().show());
        getToolbar().addCommandToLeftSideMenu("Tests", null, (evt) -> new TestForm().show());
    }

    private void showTests() {
        for (Test test : testArrayList) {
            MultiButton multiButton = new MultiButton();
            multiButton.setTextLine1(test.getName());
            multiButton.setTextLine2(test.getDate().toString());
            //multiButton.setHorizontalLayout(true);
            multiButton.setTextLine3(test.getCategory());
            multiButton.setUIID(test.getId() + "");
            multiButton.setEmblem(FontImage.createMaterial(FontImage.MATERIAL_KEYBOARD_ARROW_RIGHT, "", 10.0f));
            multiButton.addActionListener(l -> new ShowTest(current, test).show());
            add(multiButton);
        }
    }
}
