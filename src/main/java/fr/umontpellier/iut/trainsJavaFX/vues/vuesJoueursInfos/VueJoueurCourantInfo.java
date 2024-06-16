package fr.umontpellier.iut.trainsJavaFX.vues.vuesJoueursInfos;

import fr.umontpellier.iut.trainsJavaFX.IJoueur;
import fr.umontpellier.iut.trainsJavaFX.vues.CouleursJoueurs;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.ObjectProperty;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class VueJoueurCourantInfo extends VueJoueursInfos {
    private ObjectProperty<IJoueur> joueur;
    private ImageView cube;

    public VueJoueurCourantInfo(ObjectProperty<IJoueur> joueur){
        super (new Label(), new Label(), new Label(), new Label(), new Label(), new Label(), new Label(), new Label(), new CouleursJoueurs(), new BorderPane());
        this.joueur = joueur;
        this.cube = new ImageView(new Image("images/icons/cube_" + couleursJoueurs.getCouleurAnglais(joueur.getValue().getCouleur()) + ".png"));
        creerBindings();
        fillVue(cube);
        this.getChildren().add(vue);
        this.setPadding(new Insets(10));
    }

    public void creerBindings(){

        cube.imageProperty().unbind();
        argentLabel.textProperty().unbind();
        cubes.textProperty().unbind();
        railsLabel.textProperty().unbind();
        pointsVictoire.textProperty().unbind();
        cartesEnMain.textProperty().unbind();
        pioche.textProperty().unbind();
        defausse.textProperty().unbind();
        nomJoueur.textProperty().unbind();



        cube.imageProperty().bind(new ObjectBinding<>(){
            {
                super.bind(joueur);
            }

            @Override
            protected Image computeValue() {
                return new Image("images/icons/cube_" + couleursJoueurs.getCouleurAnglais(joueur.getValue().getCouleur()) + ".png");
            }
        });

        StringBinding cubesBinding = new StringBinding() {
            {
                super.bind(joueur.getValue().nbJetonsRailsProperty());
            }
            @Override
            protected String computeValue() {
                return Integer.toString(joueur.getValue().nbJetonsRailsProperty().getValue());
            }
        };

        StringBinding nomJoueurBinding = new StringBinding() {
            {
                super.bind(joueur);
            }
            @Override
            protected String computeValue() {
                return joueur.getValue().getNom();
            }
        };

        StringBinding argentBinding = new StringBinding() {
            {
                super.bind(joueur.getValue().argentProperty());
            }
            @Override
            protected String computeValue() {
                return Integer.toString(joueur.getValue().argentProperty().getValue());
            }
        };


        StringBinding railsBinding = new StringBinding() {
            {
                super.bind(joueur.getValue().pointsRailsProperty());
            }
            @Override
            protected String computeValue() {
                return Integer.toString(joueur.getValue().pointsRailsProperty().getValue());
            }
        };

        StringBinding nbPointVictoireBinding = new StringBinding() {
            {
                super.bind(joueur.getValue().scoreProperty());
            }
            @Override
            protected String computeValue() {
                return Integer.toString(joueur.getValue().scoreProperty().getValue());
            }
        };

        StringBinding cartesEnMainBinding = new StringBinding() {
            {
                super.bind(joueur.getValue().mainProperty());
            }
            @Override
            protected String computeValue() {
                int size = joueur.getValue().mainProperty().size();
                return Integer.toString(size);
            }
        };

        StringBinding piocheBinding = new StringBinding() {
            {
                super.bind(joueur.getValue().piocheProperty());
            }
            @Override
            protected String computeValue() {
                int size = joueur.getValue().piocheProperty().size();
                return Integer.toString(size);
            }
        };

        StringBinding defausseBinding = new StringBinding() {
            {
                super.bind(joueur.getValue().defausseProperty());
            }
            @Override
            protected String computeValue() {
                int size = joueur.getValue().defausseProperty().size();
                return Integer.toString(size);
            }
        };

        this.styleProperty().bind(new StringBinding() {
            {
                super.bind(joueur);
            }
            @Override
            protected String computeValue() {
                return "-fx-background-color: " + couleursJoueurs.getCouleur(joueur.getValue().getCouleur());
            }
        });

        argentLabel.textProperty().bind(argentBinding);
        cubes.textProperty().bind(cubesBinding);
        railsLabel.textProperty().bind(railsBinding);
        pointsVictoire.textProperty().bind(nbPointVictoireBinding);
        cartesEnMain.textProperty().bind(cartesEnMainBinding);
        pioche.textProperty().bind(piocheBinding);
        defausse.textProperty().bind(defausseBinding);

        nomJoueur.textProperty().bind(nomJoueurBinding);

    }
}