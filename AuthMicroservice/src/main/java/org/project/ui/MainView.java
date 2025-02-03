package org.project.ui;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import jakarta.annotation.security.RolesAllowed;
import org.project.entity.Auth;
import org.project.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Route("")
@RolesAllowed("USER")
@PreserveOnRefresh
@PageTitle("Main View")
@UIScope
@SpringComponent
@AnonymousAllowed
public class MainView extends VerticalLayout {
    private final AuthService authService;

    Grid<Auth> grid = new Grid<>(Auth.class);

    @Autowired
    public MainView(AuthService authService) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() ||
                authentication instanceof AnonymousAuthenticationToken) {
            UI.getCurrent().getPage().setLocation("login"); // Redirect if not authenticated
        }

        this.authService = authService;
        addClassName("list-view");
        setSizeFull();
        configuredGrid();

        add(grid);
        setupGrid();
    }

    private void configuredGrid() {
        grid.addClassName("auth-view");
        grid.setSizeFull();
        grid.setColumns("id", "userName", "password", "email");
    }

    private void setupGrid() {
        grid.setItems(authService.getAll());
    }
}
