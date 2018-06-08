package Controller;
import Model.Player;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import util.SqlConnection;

import javax.xml.crypto.Data;
import java.net.URL;
import java.sql.*;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class mlbPlayerController {

    @FXML private TableView<Player> playerTable;
    @FXML private TableColumn<Player, String> playerId;
    @FXML private TableColumn<Player, Integer> birthYear;
    @FXML private TableColumn<Player, String> birthCountry;
    @FXML private TableColumn<Player, String> nameFirst;
    @FXML private TableColumn<Player, String> nameLast;
    @FXML private TableColumn<Player, String> nameGiven;
    @FXML private TableColumn<Player, Integer> weight;
    @FXML private TableColumn<Player, Integer> height;
    @FXML private TableColumn<Player, String> bats;
    @FXML private TableColumn<Player, String> throwing;
    @FXML private TableColumn<Player, Integer> debut;
    @FXML private TableColumn<Player, Integer> finalGame;
    @FXML private Button loadButton;

    // Advanced Search
    @FXML private TextField exactNameTextField;
    @FXML private TextField anyNameTextField;
    @FXML private TextField birthYearTextField;
    @FXML private TextField birthCountryTextField;
    @FXML private TextField debutTextField;
    @FXML private TextField finalGameTextField;
    @FXML private Button exactNameSeacrhButton;
    @FXML private Button anyNameSeacrhButton;
    @FXML private Button weightTop10Button;
    @FXML private Button heightTop10Button;
    @FXML private Button averageWeightButton;
    @FXML private Button averageHeightButton;

    // PieChart
    @FXML private PieChart birthCountryChart;
    @FXML private PieChart batsChart;
    @FXML private PieChart throwsChart;
    @FXML private ObservableList<PieChart.Data> piechardata;
    ArrayList<String> x = new ArrayList<>();
    ArrayList<Number> y = new ArrayList<>();

    private ObservableList<Player> data;
    private SqlConnection dc;


    @FXML
    private void displayBirthCountryChart() {
        piechardata = FXCollections.observableArrayList();
        try {
            SqlConnection sqlConnection = new SqlConnection();
            Connection conn = sqlConnection.Connect();
            String query = "SELECT birthCountry, count(*) FROM People GROUP BY birthCountry";
            ResultSet rs = conn.createStatement().executeQuery(query);
            while (rs.next()) {
                piechardata.add(new PieChart.Data(rs.getString(1), rs.getInt(2)));
                x.add(rs.getString(1));
                System.out.println(rs.getString(2));
                y.add(rs.getInt(2));
                birthCountryChart.setData(piechardata);
            }

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }


    }

    @FXML
    private void displayBatsChart() {
        piechardata = FXCollections.observableArrayList();
        try {
            SqlConnection sqlConnection = new SqlConnection();
            Connection conn = sqlConnection.Connect();
            String query = "SELECT bats, count(*) FROM People GROUP BY bats";
            ResultSet rs = conn.createStatement().executeQuery(query);
            while (rs.next()) {
                piechardata.add(new PieChart.Data(rs.getString(1), rs.getInt(2)));
                x.add(rs.getString(1));
                System.out.println(rs.getString(2));
                y.add(rs.getInt(2));
                batsChart.setData(piechardata);
            }

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }


    }

    @FXML
    private void displayThrowsChart() {
        piechardata = FXCollections.observableArrayList();
        try {
            SqlConnection sqlConnection = new SqlConnection();
            Connection conn = sqlConnection.Connect();
            String query = "SELECT throws, count(*) FROM People GROUP BY throws";
            ResultSet rs = conn.createStatement().executeQuery(query);
            while (rs.next()) {
                piechardata.add(new PieChart.Data(rs.getString(1), rs.getInt(2)));
                x.add(rs.getString(1));
                System.out.println(rs.getString(2));
                y.add(rs.getInt(2));
                throwsChart.setData(piechardata);
            }

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }


    }


    @FXML
    private void loadDataFromDatabase(ActionEvent event) {
        try {
            setting();
            SqlConnection sqlConnection = new SqlConnection();
            Connection conn = sqlConnection.Connect();
            data = FXCollections.observableArrayList();
            // Execute query and store result
            String query = "SELECT * FROM People";
            ResultSet rs = conn.createStatement().executeQuery(query);
            while (rs.next()) {
//                 get string from db
                data.add(new Player(
                        rs.getString("playerId"),
                        rs.getString("birthYear"),
                        rs.getString("birthCountry"),
                        rs.getString("nameFirst"),
                        rs.getString("nameLast"),
                        rs.getString("nameGiven"),
                        rs.getString("weight"),
                        rs.getString("height"),
                        rs.getString("bats"),
                        rs.getString("throws"),
                        rs.getString("debut"),
                        rs.getString("finalGame")
                        ));

            }
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

    }

    @FXML
    private void exactNameSeacrh(ActionEvent event) {
        try {
            SqlConnection sqlConnection = new SqlConnection();
            Connection conn = sqlConnection.Connect();
            data = FXCollections.observableArrayList();

            String exactName = exactNameTextField.getText();

//          Execute query and store result
            String query = "SELECT * FROM People WHERE nameFirst= '" + exactName
                        + "' OR nameLast= '" + exactName + "'";

            System.out.println(query);

            ResultSet rs = conn.createStatement().executeQuery(query);

            while (rs.next()) {
//                 get string from db
                data.add(new Player(
                        rs.getString("playerId"),
                        rs.getString("birthYear"),
                        rs.getString("birthCountry"),
                        rs.getString("nameFirst"),
                        rs.getString("nameLast"),
                        rs.getString("nameGiven"),
                        rs.getString("weight"),
                        rs.getString("height"),
                        rs.getString("bats"),
                        rs.getString("throws"),
                        rs.getString("debut"),
                        rs.getString("finalGame")
                ));
            }
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        setting();


    }

    @FXML
    private void anyNameSeacrh(ActionEvent event) {
        try {
            SqlConnection sqlConnection = new SqlConnection();
            Connection conn = sqlConnection.Connect();
            data = FXCollections.observableArrayList();

            String anyName = anyNameTextField.getText();

//          Execute query and store result
            String query = "SELECT * FROM People WHERE nameFirst like '" + anyName + "%"
                        + "' OR nameLast like '" + anyName + "%"
                        + "' OR nameFirst like '%" + anyName
                        + "' OR nameLast like '%" + anyName
                        + "' OR nameFirst like '%" + anyName + "%"
                        + "' OR nameLast like '%" + anyName + "%'";

            System.out.println(query);


            ResultSet rs = conn.createStatement().executeQuery(query);

            while (rs.next()) {
//                 get string from db
                data.add(new Player(
                        rs.getString("playerId"),
                        rs.getString("birthYear"),
                        rs.getString("birthCountry"),
                        rs.getString("nameFirst"),
                        rs.getString("nameLast"),
                        rs.getString("nameGiven"),
                        rs.getString("weight"),
                        rs.getString("height"),
                        rs.getString("bats"),
                        rs.getString("throws"),
                        rs.getString("debut"),
                        rs.getString("finalGame")
                ));
            }
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        setting();


    }

    @FXML
    private void birthYearSearch(ActionEvent event) {
        try {
            SqlConnection sqlConnection = new SqlConnection();
            Connection conn = sqlConnection.Connect();
            data = FXCollections.observableArrayList();

            String birthYear = birthYearTextField.getText();
//          Execute query and store result
            String query = "SELECT * FROM People WHERE birthYear= '" + birthYear + "'";

            System.out.println(query);


            ResultSet rs = conn.createStatement().executeQuery(query);

            while (rs.next()) {
//                 get string from db
                data.add(new Player(
                        rs.getString("playerId"),
                        rs.getString("birthYear"),
                        rs.getString("birthCountry"),
                        rs.getString("nameFirst"),
                        rs.getString("nameLast"),
                        rs.getString("nameGiven"),
                        rs.getString("weight"),
                        rs.getString("height"),
                        rs.getString("bats"),
                        rs.getString("throws"),
                        rs.getString("debut"),
                        rs.getString("finalGame")
                ));
            }
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        setting();


    }


    @FXML
    private void birthCountrySearch(ActionEvent event) {
        try {
            SqlConnection sqlConnection = new SqlConnection();
            Connection conn = sqlConnection.Connect();
            data = FXCollections.observableArrayList();

            String birthCountry = birthCountryTextField.getText();
//          Execute query and store result
            String query = "SELECT * FROM People WHERE birthCountry= '" + birthCountry + "'";

            System.out.println(query);


            ResultSet rs = conn.createStatement().executeQuery(query);

            while (rs.next()) {
//                 get string from db
                data.add(new Player(
                        rs.getString("playerId"),
                        rs.getString("birthYear"),
                        rs.getString("birthCountry"),
                        rs.getString("nameFirst"),
                        rs.getString("nameLast"),
                        rs.getString("nameGiven"),
                        rs.getString("weight"),
                        rs.getString("height"),
                        rs.getString("bats"),
                        rs.getString("throws"),
                        rs.getString("debut"),
                        rs.getString("finalGame")
                ));
            }
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        setting();


    }

    @FXML
    private void debutSearch(ActionEvent event) {
        try {
            SqlConnection sqlConnection = new SqlConnection();
            Connection conn = sqlConnection.Connect();
            data = FXCollections.observableArrayList();

            String debut = debutTextField.getText();
//          Execute query and store result
            String query = "SELECT * FROM People WHERE debut like '" + debut + "%'";

            System.out.println(query);


            ResultSet rs = conn.createStatement().executeQuery(query);

            while (rs.next()) {
//                 get string from db
                data.add(new Player(
                        rs.getString("playerId"),
                        rs.getString("birthYear"),
                        rs.getString("birthCountry"),
                        rs.getString("nameFirst"),
                        rs.getString("nameLast"),
                        rs.getString("nameGiven"),
                        rs.getString("weight"),
                        rs.getString("height"),
                        rs.getString("bats"),
                        rs.getString("throws"),
                        rs.getString("debut"),
                        rs.getString("finalGame")
                ));
            }
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        setting();


    }

    @FXML
    private void finalGameSearch(ActionEvent event) {
        try {
            SqlConnection sqlConnection = new SqlConnection();
            Connection conn = sqlConnection.Connect();
            data = FXCollections.observableArrayList();

            String finalGame = finalGameTextField.getText();
//          Execute query and store result
            String query = "SELECT * FROM People WHERE finalGame like '" + finalGame + "%'";

            System.out.println(query);


            ResultSet rs = conn.createStatement().executeQuery(query);

            while (rs.next()) {
//                 get string from db
                data.add(new Player(
                        rs.getString("playerId"),
                        rs.getString("birthYear"),
                        rs.getString("birthCountry"),
                        rs.getString("nameFirst"),
                        rs.getString("nameLast"),
                        rs.getString("nameGiven"),
                        rs.getString("weight"),
                        rs.getString("height"),
                        rs.getString("bats"),
                        rs.getString("throws"),
                        rs.getString("debut"),
                        rs.getString("finalGame")
                ));
            }
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        setting();


    }


    @FXML
    private void weightTop10(ActionEvent event) {
        try {
            SqlConnection sqlConnection = new SqlConnection();
            Connection conn = sqlConnection.Connect();
            data = FXCollections.observableArrayList();

//          Execute query and store result
            String query = "SELECT * FROM People WHERE weight is not '' ORDER BY weight DESC LIMIT 10";
            ResultSet rs = conn.createStatement().executeQuery(query);

            while (rs.next()) {
//                 get string from db
                data.add(new Player(
                        rs.getString("playerId"),
                        rs.getString("birthYear"),
                        rs.getString("birthCountry"),
                        rs.getString("nameFirst"),
                        rs.getString("nameLast"),
                        rs.getString("nameGiven"),
                        rs.getString("weight"),
                        rs.getString("height"),
                        rs.getString("bats"),
                        rs.getString("throws"),
                        rs.getString("debut"),
                        rs.getString("finalGame")
                ));
            }
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        setting();


    }

    @FXML
    private void heightTop10(ActionEvent event) {
        try {
            SqlConnection sqlConnection = new SqlConnection();
            Connection conn = sqlConnection.Connect();
            data = FXCollections.observableArrayList();

//          Execute query and store result
            String query = "SELECT * FROM People WHERE height is not '' ORDER BY height DESC LIMIT 10";

            ResultSet rs = conn.createStatement().executeQuery(query);

            while (rs.next()) {
//                 get string from db
                data.add(new Player(
                        rs.getString("playerId"),
                        rs.getString("birthYear"),
                        rs.getString("birthCountry"),
                        rs.getString("nameFirst"),
                        rs.getString("nameLast"),
                        rs.getString("nameGiven"),
                        rs.getString("weight"),
                        rs.getString("height"),
                        rs.getString("bats"),
                        rs.getString("throws"),
                        rs.getString("debut"),
                        rs.getString("finalGame")
                ));
            }
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        setting();


    }

//    @FXML
//    private void averageWeight(ActionEvent event) {
//        try {
//            SqlConnection sqlConnection = new SqlConnection();
//            Connection conn = sqlConnection.Connect();
//            data = FXCollections.observableArrayList();
////          Execute query and store result
//            String query = "SELECT *, birthCountry, AVG(weight) FROM People GROUP BY birthCountry;";
//
//            ResultSet rs = conn.createStatement().executeQuery(query);
//
//            while (rs.next()) {
////                 get string from db
////                System.out.println(rs.getString("birthCountry") + " : " + rs.getDouble(2));
//
//                data.add(new Player(
//                        rs.getString("playerId"),
//                        rs.getString("birthYear"),
//                        rs.getString("birthCountry"),
//                        rs.getString("nameFirst"),
//                        rs.getString("nameLast"),
//                        rs.getString("nameGiven"),
//                        rs.getString("weight"),
//                        rs.getString("height"),
//                        rs.getString("bats"),
//                        rs.getString("throws"),
//                        rs.getString("debut"),
//                        rs.getString("finalGame")
//                ));
//            }
//        } catch (SQLException ex) {
//            System.err.println("Error" + ex);
//        }
//
//        setting();
//
//
//    }
//
//    @FXML
//    private void averageHeight(ActionEvent event) {
//        try {
//            SqlConnection sqlConnection = new SqlConnection();
//            Connection conn = sqlConnection.Connect();
//            data = FXCollections.observableArrayList();
//
////          Execute query and store result
//            String query = "SELECT *, birthCountry,AVG(height) FROM People GROUP BY birthCountry;";
//
//            ResultSet rs = conn.createStatement().executeQuery(query);
//
//            while (rs.next()) {
////                 get string from db
//                data.add(new Player(
//                        rs.getString("playerId"),
//                        rs.getString("birthYear"),
//                        rs.getString("birthCountry"),
//                        rs.getString("nameFirst"),
//                        rs.getString("nameLast"),
//                        rs.getString("nameGiven"),
//                        rs.getString("weight"),
//                        rs.getString("height"),
//                        rs.getString("bats"),
//                        rs.getString("throws"),
//                        rs.getString("debut"),
//                        rs.getString("finalGame")
//                ));
//            }
//        } catch (SQLException ex) {
//            System.err.println("Error" + ex);
//        }
//
//        setting();
//
//
//    }


    public void setting() {
        // Set cell value factory to tableView
        // PropertyValue Factory must be the same with the one set in model class.
        playerId.setCellValueFactory(new PropertyValueFactory<>("playerId"));
        birthYear.setCellValueFactory(new PropertyValueFactory<>("birthYear"));
        birthCountry.setCellValueFactory(new PropertyValueFactory<>("birthCountry"));
        nameFirst.setCellValueFactory(new PropertyValueFactory<>("nameFirst"));
        nameLast.setCellValueFactory(new PropertyValueFactory<>("nameLast"));
        nameGiven.setCellValueFactory(new PropertyValueFactory<>("nameGiven"));
        weight.setCellValueFactory(new PropertyValueFactory<>("weight"));
        height.setCellValueFactory(new PropertyValueFactory<>("height"));
        bats.setCellValueFactory(new PropertyValueFactory<>("bats"));
        throwing.setCellValueFactory(new PropertyValueFactory<>("throwing"));
        debut.setCellValueFactory(new PropertyValueFactory<>("debut"));
        finalGame.setCellValueFactory(new PropertyValueFactory<>("finalGame"));

        playerTable.setItems(data);
    }



//    private void load(ActionEvent event) {
//        Connection conn = null;
//        final ObservableList<Player> data = FXCollections.observableArrayList();
//        String query = "SELECT * FROM People";
//        try {
//
//            // a. Create a statement
//            Statement stmt = conn.createStatement();
//            // b. execute the query -> returns a 'ResultSet'
//            ResultSet rs = stmt.executeQuery(query); // Iterator
//
//            while (rs.next()) {
//                data.add(new Player(
//                        rs.getString("playerId"),
//                        rs.getInt("birthYear"),
//                        rs.getInt("birthMonth"),
//                        rs.getInt("birthDay"),
//                        rs.getString("birthCountry"),
//                        rs.getString("birthState"),
//                        rs.getString("birthCity"),
//                        rs.getInt("deathYear"),
//                        rs.getInt("deathMonth"),
//                        rs.getInt("deathDay"),
//                        rs.getString("deathCountry"),
//                        rs.getString("deathState"),
//                        rs.getString("deathCity"),
//                        rs.getString("nameFirst"),
//                        rs.getString("nameLast"),
//                        rs.getString("nameGiven"),
//                        rs.getInt("weight"),
//                        rs.getInt("height"),
//                        rs.getString("bats"),
//                        rs.getString("throwing"),
//                        rs.getInt("debut"),
//                        rs.getInt("finalGame"),
//                        rs.getString("retroId"),
//                        rs.getString("bbrefId")
//                ));
//                playerTable.setItems(data);
//            }
//            stmt.close();
//            rs.close();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//
//    }

//    public void initialize(URL url, ResourceBundle rb) {
//        dc = new SqlConnection();
//
//        playerId.setCellValueFactory(new PropertyValueFactory<>("playerID"));
//        birthYear.setCellValueFactory(new PropertyValueFactory<>("birthYear"));
//        birthMonth.setCellValueFactory(new PropertyValueFactory<>("birthMonth"));
//        birthDay.setCellValueFactory(new PropertyValueFactory<>("birthDay"));
//        birthCountry.setCellValueFactory(new PropertyValueFactory<>("birthCountry"));
//        birthState.setCellValueFactory(new PropertyValueFactory<>("birthState"));
//        birthCity.setCellValueFactory(new PropertyValueFactory<>("birthCity"));
//        deathYear.setCellValueFactory(new PropertyValueFactory<>("deathYear"));
//        deathMonth.setCellValueFactory(new PropertyValueFactory<>("deathMonth"));
//        deathDay.setCellValueFactory(new PropertyValueFactory<>("deathDay"));
//        deathCountry.setCellValueFactory(new PropertyValueFactory<>("deathCountry"));
//        deathState.setCellValueFactory(new PropertyValueFactory<>("deathState"));
//        deathCity.setCellValueFactory(new PropertyValueFactory<>("deathCity"));
//        nameFirst.setCellValueFactory(new PropertyValueFactory<>("nameFirst"));
//        nameLast.setCellValueFactory(new PropertyValueFactory<>("nameLast"));
//        nameGiven.setCellValueFactory(new PropertyValueFactory<>("nameGiven"));
//        weight.setCellValueFactory(new PropertyValueFactory<>("weight"));
//        height.setCellValueFactory(new PropertyValueFactory<>("height"));
//        bats.setCellValueFactory(new PropertyValueFactory<>("bats"));
//        throwing.setCellValueFactory(new PropertyValueFactory<>("throwing"));
//        debut.setCellValueFactory(new PropertyValueFactory<>("debut"));
//        finalGame.setCellValueFactory(new PropertyValueFactory<>("finalGame"));
//        retroId.setCellValueFactory(new PropertyValueFactory<>("retroId"));
//        bbrefId.setCellValueFactory(new PropertyValueFactory<>("bbrefId"));
//    }


//    public void editMidterm(TableColumn.CellEditEvent cellEditEvent) {
//        Score scoreEdit = studentScoreTable.getSelectionModel().getSelectedItem();
//        scoreEdit.setMidterm(cellEditEvent.getNewValue().toString());
//    }
//
//    public void editFinalTest(TableColumn.CellEditEvent cellEditEvent) {
//        Score scoreEdit = studentScoreTable.getSelectionModel().getSelectedItem();
//        scoreEdit.setFinalTest(cellEditEvent.getNewValue().toString());
//    }
//
//    public void editFinalProject(TableColumn.CellEditEvent cellEditEvent) {
//        Score scoreEdit = studentScoreTable.getSelectionModel().getSelectedItem();
//        scoreEdit.setFinalProject(cellEditEvent.getNewValue().toString());
//    }
//
//    public void changeToStudentList(ActionEvent event) throws IOException {
//
//        Parent tableViewParent = FXMLLoader.load(getClass().getResource("../View/StudentList.fxml"));
//        Scene tableViewScene = new Scene(tableViewParent);
//
//        // This line gets the Stage information
//        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
//
//        window.setScene(tableViewScene);
//        window.show();
//    }
//
//    public void displayStudentScore() {
//
//    }




}











