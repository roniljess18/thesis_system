package dev.rjm.app;

import dev.rjm.App;
import dev.sol.core.application.loader.FXLoader;
import javafx.scene.Scene;

public class RootLoader extends FXLoader {

    @Override
    public void load() {
        Scene scene = (Scene) params.get("scene");
        scene.setRoot(root);

        RootController controller = loader.getController();
        App.CONTROLLER_REGISTER.register("ROOT", controller);
        controller
            .addParameter("SCENE", scene)
            .load();
    }
    
}
