import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AppTest extends Application{

    public static void main(String[] args) {

        // On main thread
        Application.launch(args);

        // To get list of parameters later:
        // App.getParameters()
    }

    @Override
    public void start(Stage primaryStage) {

        // on JavaFX application thread
        primaryStage.setTitle("mPad");
        Group root = new Group();
        Scene scene = new Scene(root, 300, 250);

        Button button = new Button("Press me!");
        button.setLayoutX(100);
        button.setLayoutY(80);
        button.setOnAction(event -> {

            System.out.println("Button pressed");
        });

        Line line = new Line(12,15,117,118);
        line.setStroke(Color.DARKGREEN);
        line.setStrokeWidth(10);
        line.setStrokeLineCap(StrokeLineCap.BUTT);
        line.getStrokeDashArray().addAll(10d, 5d, 15d, 20d);
        line.setStrokeDashOffset(0);
        line.setOnMouseClicked(event -> {
            System.out.println("Detected click on line");
        });

        Slider slider = new Slider(0, 100, 0);
        slider.setLayoutX(10);
        slider.setLayoutY(170);

        Text offsetText = new Text("Stroke dash offset: " + slider.getValue());
        offsetText.setX(10);
        offsetText.setY(80);
        offsetText.setStroke(Color.CADETBLUE);
        slider.valueProperty().addListener((ov, curVal, newVal) -> {
            offsetText.setText("Stroke dash offset: " + newVal);
            offsetText.setFont(Font.font((Double) curVal));
        });

        line.strokeDashOffsetProperty().bind(slider.valueProperty());

        root.getChildren().addAll(button, line, slider, offsetText);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}