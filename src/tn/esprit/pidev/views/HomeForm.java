package tn.esprit.pidev.views;

import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

public class HomeForm extends Form {
    Form current;
    public HomeForm(){
        /* *** *CONFIG SCREEN* *** */
        current = this;
        setTitle("WELCOME");
        setLayout(BoxLayout.y());
        setScrollableY(false);
        add(new Label("Choose an option from side menu"));
        /* *** *YOUR CODE GOES HERE* *** */

        /* *** *SIDE MENU* *** */
        getToolbar().addCommandToLeftSideMenu("", null, (evt) -> {});
        getToolbar().addCommandToLeftSideMenu("Home", null, (evt) -> new HomeForm().show());
        getToolbar().addCommandToLeftSideMenu("Tests", null, (evt) -> new TestForm().show());
    }
}
