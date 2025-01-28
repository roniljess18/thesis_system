package dev.rjm;

import dev.rjm.app.RootLoader;
import dev.rjm.data.DegreeDAO;
import dev.rjm.data.TBLStudentDAO;
import dev.rjm.data.TBLThesisDAO;
import dev.rjm.data.TBLThesisResearcherDAO;
//import dev.rjm.models.TBLStudent;
import dev.sol.core.application.FXApplication;
import dev.sol.core.application.loader.FXLoaderFactory;
import dev.sol.core.registry.FXCollectionsRegister;
import dev.sol.core.registry.FXControllerRegister;
import dev.sol.core.registry.FXNodeRegister;
import dev.sol.core.scene.FXSkin;
//import dev.sol.core.scene.FXSkin;
import dev.sol.db.DBService;
import javafx.collections.FXCollections;


/**
 * JavaFX App
 */
public class App extends FXApplication {

    public static final FXControllerRegister CONTROLLER_REGISTER = FXControllerRegister.INSTANCE;
    public static final FXCollectionsRegister COLLECTIONS_REGISTER = FXCollectionsRegister.INSTANCE;
    public static final FXNodeRegister NODE_REGISTER = FXNodeRegister.INSTANCE;

    public static final DBService DB_THESIS = DBService.INSTANCE
            .initialize("jdbc:mysql://localhost:3300/thesis_database?user=root&passwords=");

    @Override
    public void initialize() throws Exception {
        setTitle("Thesis Management");
         setSkin(FXSkin.PRIMER_LIGHT);

        _initialize_dataset();
        _initialize_application();
        
    }
    

    private void _initialize_dataset() {
       
        COLLECTIONS_REGISTER.register("STUDENT",
                FXCollections.observableArrayList(TBLStudentDAO.getTBLStudentList()));
        COLLECTIONS_REGISTER.register("THESIS",
                FXCollections.observableArrayList(TBLThesisDAO.getTBLThesisList()));
        COLLECTIONS_REGISTER.register("TBLTHESISRESEARCHER",
                FXCollections.observableArrayList(TBLThesisResearcherDAO.getTBLThesisResearchers()));
        COLLECTIONS_REGISTER.register("DEGREE",
                FXCollections.observableArrayList(DegreeDAO.getDegreeList()));
        
       
    }

    private void _initialize_application() {
        RootLoader rootLoader = (RootLoader) FXLoaderFactory.createInstance(
                RootLoader.class,
                App.class.getResource("/dev/rjm/app/ROOT.fxml"))
                .addParameter("scene", applicationScene)
                .initialize();
        rootLoader.load();

    }

}