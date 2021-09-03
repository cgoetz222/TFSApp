package edu.tfse.tfsapp;

import com.gluonhq.attach.lifecycle.LifecycleService;
import com.gluonhq.attach.util.Platform;
import com.gluonhq.attach.util.Services;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.application.ViewStackPolicy;
import com.gluonhq.charm.glisten.control.Avatar;
import com.gluonhq.charm.glisten.control.NavigationDrawer;
import com.gluonhq.charm.glisten.control.NavigationDrawer.Item;
import com.gluonhq.charm.glisten.control.NavigationDrawer.ViewItem;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import javafx.scene.image.Image;

import static edu.tfse.tfsapp.TFSApplication.TFSAPPMAIN_VIEW;
import static edu.tfse.tfsapp.TFSApplication.TFSAPPABOUT_VIEW;

public class DrawerManager {

    public static void buildDrawer(MobileApplication app) {
        NavigationDrawer drawer = app.getDrawer();

        NavigationDrawer.Header header = new NavigationDrawer.Header("TFSE",
                "Fitness App", new Avatar(21, new Image(DrawerManager.class.getResourceAsStream("/icon.png"))));
        drawer.setHeader(header);
        
        final Item tfsappmainItem = new ViewItem("Theodor-Frey-Schule", MaterialDesignIcon.HOME.graphic(), TFSAPPMAIN_VIEW, ViewStackPolicy.SKIP);
        final Item tfsappaboutItem = new ViewItem("TFS About", MaterialDesignIcon.DASHBOARD.graphic(), TFSAPPABOUT_VIEW);
        drawer.getItems().addAll(tfsappmainItem, tfsappaboutItem);
        
        if (Platform.isDesktop()) {
            final Item quitItem = new Item("Quit", MaterialDesignIcon.EXIT_TO_APP.graphic());
            quitItem.selectedProperty().addListener((obs, ov, nv) -> {
                if (nv) {
                    Services.get(LifecycleService.class).ifPresent(LifecycleService::shutdown);
                }
            });
            drawer.getItems().add(quitItem);
        }
    }
}