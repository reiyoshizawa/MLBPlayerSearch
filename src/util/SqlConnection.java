package util;

import java.sql.*;

public class SqlConnection {

    /**
     * connects to the database
     * @return
     */

    public  Connection Connect() {
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:BaseballData.db";
            conn = DriverManager.getConnection(url); // connect to the db.
            System.out.println("Connection to SQLite BaseballData has been established.");

        } catch (SQLException e) {
            e.printStackTrace(); // prints Exception message.
        }
        return conn;
    }


    // CRUD Operation
    // C : Create (CREATE TABLE students ...)
    // R : Read   (SELECT * from students_info ...)
    // U : Update (UPDATE ...)
    // D : Delete (DELETE ..., DROP students_info...)
//    public static Scanner scan = new Scanner(System.in);
//    public static ArrayList<Person> studentList = new ArrayList<>();
//
//    public static void main(String[] args) {
//        // 1. Connect to the database. (students.db)
//        Connection conn = connect();
//        boolean isOn = true;
//        while (isOn){
//
//            System.out.println("===== Student Database =====");
//            System.out.println("| 1. Read all students (r) |");
//            System.out.println("| 2. Create a student  (c) |");
//            System.out.println("| 3. Update a student  (u) |");
//            System.out.println("| 4. Delete a student  (d) |");
//            System.out.println("| 5. Quit              (q) |");
//            System.out.println("| 6. Java Objects      (j) |");
//            System.out.println("============================");
//
//            System.out.println("Choose your option: ");
//            String command = scan.nextLine().toLowerCase();
//            switch (command) {
//                case "6":
//                case "j":
////                    usingJavaObjects();
//                    break;
//                case "5":
//                case "q":
//                    isOn = false;
//                    break;
//                case "1":
//                case "r":
////                    readAllStudents(conn);
//                    break;
//                case "3":
//                case "u":
//                    updateStudent(conn);
////                    readAllStudents(conn);
//                    break;
//                case "2":
//                case "c":
//                    insertStudent(conn);
////                    readAllStudents(conn);
//                    break;
//                case "4":
//                case "d":
//                    deleteStudent(conn);
////                    readAllStudents(conn);
//                    break;
//                default:
//                    System.out.println("Wrong Command!");
//                    break;
//            }
//
//        }
//
//        // 3. Close the database.
//        closeConnect(conn);
//    }

//    private static void usingJavaObjects() {
//
//        for (Student std: studentList) {
//            System.out.println(std);
//        }
//
//    }

//    private static void closeConnect(Connection conn) {
//        try {
//            if (conn != null) {
//                conn.close();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//
//
//    /**
//     *
//     * Takes user input and inserts into the database.
//     * @param conn connection
//     *
//     */
//    public static void insertStudent(Connection conn) {
//        System.out.print("firstname: ");
//        String firstname = scan.nextLine();
//        System.out.print("Email: ");
//        String email = scan.nextLine();
//        System.out.print("Country: ");
//        String country = scan.nextLine();
//        System.out.print("Hobby: ");
//        String hobby = scan.nextLine();
//
//        String query = "INSERT INTO students_info(firstname, email, country, hobby) VALUES (?, ?, ?, ?)";
//        try {
//            PreparedStatement pstmt = conn.prepareStatement(query); // Creates a prepared statement based on your query str.
//            // set the data for each placeholder
//            pstmt.setString(1, firstname);
//            pstmt.setString(2, email);
//            pstmt.setString(3, country);
//            pstmt.setString(4, hobby);
//
//            pstmt.executeUpdate();
//            System.out.println("Successfully added a new student " + firstname);
//        } catch(SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//
    /**
     * Read all entries from the database.
     * @param conn connection
//     */
//    private static void readAllStudents(Connection conn) {
//        String query = "SELECT * FROM students_info";
//        try {
//            // a. Create a statement
//            Statement stmt = conn.createStatement();
//            // b. execute the query -> returns a 'ResultSet'
//            ResultSet rs = stmt.executeQuery(query); // Iterator
//
//            System.out.println("----------------------------------------------------------------------------------------------------");
//            while (rs.next()) {
//                System.out.printf("%2d %-10s %-40s %-15s %-20s%n",
//                        rs.getString("id"),
//                        rs.getString("firstname"),
//                        rs.getString("email"),
//                        rs.getString("country"),
//                        rs.getString("hobby"));
//
//                Student std = new Student(rs.getString("id"), rs.getString("firstname"),
//                        rs.getString("email"), rs.getString("country"),
//                        rs.getString("hobby"));
//                        std.getId();
//                        std.getFirstname();
//                        std.getEmail();
//                        std.getCountry();
//                        std.getHobby();
//
//                studentList.add(std);
//
////                System.out.printf("%2d %-10s %-40s %-15s %-20s%n",
////                        std.getId(),
////                        std.getFirstname(),
////                        std.getEmail(),
////                        std.getCountry(),
////                        std.getHobby());
//
//
//            }
//            System.out.println("----------------------------------------------------------------------------------------------------");
//
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * Take user input and upadates a student with the given Id
//     * @param conn connection
//     */
//    public static void updateStudent(Connection conn) {
//        // Get user input data
//
//        System.out.print("Update student at id: ");
//        int id = Integer.parseInt(scan.nextLine());
//        System.out.print("firstname: ");
//        String firstname = scan.nextLine();
//        System.out.print("Email: ");
//        String email = scan.nextLine();
//        System.out.print("Country: ");
//        String country = scan.nextLine();
//        System.out.print("Hobby: ");
//        String hobby = scan.nextLine();
//
//        String query = "UPDATE students_info SET firstname = ?, email = ?, country = ?, hobby = ? WHERE id = ?";
//        try {
//            PreparedStatement pstat = conn.prepareStatement(query);
//            pstat.setString(1, firstname);
//            pstat.setString(1, email);
//            pstat.setString(1, country);
//            pstat.setString(1, hobby);
//            pstat.setInt(5, 1);
//
//            pstat.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//        public static void deleteStudent(Connection conn) {
//
//        System.out.println("Delete student at id: ");
//        int id = Integer.parseInt(scan.nextLine());
//
//        String query = "DELETE FROM students_info WHERE id = ?";
//
//        try {
//            PreparedStatement pstat = conn.prepareStatement(query);
//            pstat.setInt(1,id);
//
//            pstat.execute();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//    }


}
