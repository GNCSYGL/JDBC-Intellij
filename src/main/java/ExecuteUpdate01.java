import java.sql.*;

public class ExecuteUpdate01 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");   //ogr yazılmış   :)   tesekkur ederimgöz kararıyo bazen
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed","postgres","060622bg");
        Statement st = con.createStatement();

        //1. Örnek: number_of_employees değeri ortalama çalışan sayısından az olan number_of_employees değerlerini 16000 olarak UPDATE edin.

        String sql1 = "Update companies\n" +
                "Set number_of_employees = 16000\n" +
                "Where number_of_employees<(Select Avg (number_of_employees)\n" +
                "\t\t\t\t\t\t\tFrom companies)";

        int updateEdilenSatirSayisi = st.executeUpdate(sql1);
        System.out.println("updateEdilenSatirSayisi = " + updateEdilenSatirSayisi);

        ResultSet resultSet1 = st.executeQuery("Select * From companies");

        while (resultSet1.next()){
            System.out.println(resultSet1.getInt(1)+"--"+resultSet1.getString(2)+"--"+resultSet1.getInt(3));
        }

        con.close();
        st.close();
        resultSet1.close();

    }
}
