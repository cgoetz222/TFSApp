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

import edu.tfse.tfsapp.helper.Messages;
import javafx.scene.image.Image;

import static edu.tfse.tfsapp.TFSApplication.TFSAPPMAIN_VIEW;
import static edu.tfse.tfsapp.TFSApplication.TFSAPPABOUT_VIEW;

public class DrawerManager {

    public static void buildDrawer(MobileApplication app) {
        NavigationDrawer drawer = app.getDrawer();

        NavigationDrawer.Header header = new NavigationDrawer.Header(Messages.getString("DrawerManager.0"), //$NON-NLS-1$
                Messages.getString("DrawerManager.1"), new Avatar(21, new Image(DrawerManager.class.getResourceAsStream("/icon.png")))); //$NON-NLS-1$ //$NON-NLS-2$
        drawer.setHeader(header);
        
        final Item tfsappmainItem = new ViewItem(Messages.getString("DrawerManager.3"), MaterialDesignIcon.HOME.graphic(), TFSAPPMAIN_VIEW, ViewStackPolicy.SKIP); //$NON-NLS-1$
        final Item tfsappaboutItem = new ViewItem(Messages.getString("DrawerManager.4"), MaterialDesignIcon.DASHBOARD.graphic(), TFSAPPABOUT_VIEW); //$NON-NLS-1$
        drawer.getItems().addAll(tfsappmainItem, tfsappaboutItem);
        
        if (Platform.isDesktop()) {
            final Item quitItem = new Item(Messages.getString("DrawerManager.5"), MaterialDesignIcon.EXIT_TO_APP.graphic()); //$NON-NLS-1$
            quitItem.selectedProperty().addListener((obs, ov, nv) -> {
                if (nv) {
                    Services.get(LifecycleService.class).ifPresent(LifecycleService::shutdown);
                }
            });
            drawer.getItems().add(quitItem);
        }
    }
}