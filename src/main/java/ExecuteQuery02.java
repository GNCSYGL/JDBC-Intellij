import java.sql.*;

public class ExecuteQuery02 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");   //ogr yazılmış   :)   tesekkur ederimgöz kararıyo bazen
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed","postgres","060622bg");
        Statement st = con.createStatement();

        //1. Örnek: companies tablosundan en yüksek ikinci number_of_employees değeri olan company ve number_of_employees değerlerini çağırın.

        String sql1 = "Select company,number_of_employees\n" +
                "From companies \n" +
                "Order By number_of_employees Desc\n" +
                "Offset 1 Row\n" +
                "Fetch Next 1 Row Only";

        ResultSet resultSet1 = st.executeQuery(sql1);

        while (resultSet1.next()){
            System.out.println(resultSet1.getString("company")+ "--"+resultSet1.getInt("number_of_employees"));
        }


        //2. Yol: Subquery Kullanarak.
        String sql2 = "SELECT company,number_of_employees\n" +
                "FROM companies\n" +
                "WHERE number_of_employees=(SELECT MAX(number_of_employees) FROM companies\n" +
                "WHERE number_of_employees<(SELECT MAX(number_of_employees) FROM companies));";

        ResultSet resultSet2 = st.executeQuery(sql2);
        while (resultSet2.next()) {

            System.out.println(resultSet2.getString(1) + "--" + resultSet2.getInt(2));

        }
        con.close();
        st.close();
        resultSet1.close();
        resultSet2.close();
    }
}
