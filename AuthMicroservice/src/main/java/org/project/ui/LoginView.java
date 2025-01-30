package org.project.ui;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("login")
@PageTitle("Login | MyApp")
public class LoginView extends VerticalLayout {

    public LoginView() {
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        H1 title = new H1("MyApp Login");

        LoginForm loginForm = new LoginForm();
        loginForm.setAction("login");

        loginForm.addLoginListener(event -> {
            loginForm.setError(true);
        });

        add(title, loginForm);
    }
}
