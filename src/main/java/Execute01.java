import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Execute01 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1. Adım: Driver'a kaydol
       Class.forName("org.postgresql.Driver");   //ogr yazılmış   :)   tesekkur ederimgöz kararıyo bazen

        //2. Adım: Datbase'e bağlan
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed","postgres","060622bg");

        //3. Adım: Statement oluştur.
        Statement st = con.createStatement();
        System.out.println("Connection Success");

        //4. Adim: query calistir.

        /*
            execute() methodu DDL(create, drop, alter table) ve DQL (select) icin kullanilabilir.
            1-Eger execute() methodu DDL icin kullanilirsa 'false' return yapar.
            2-Eger execute() methodu DQL icin kullanilirsa ResultSet alindiginda 'true', aksi halde 'false' return eder.
         */

        //1.Örnek: "workers" adında bir table oluşturup "worker_id,worker_name, worker_salary" sütunlarını ekleyin.
        boolean sql1 = st.execute("CREATE TABLE workers(worker_id VARCHAR(20),worker_name VARCHAR(20), worker_salary INT)");
        System.out.println("sql1 = " + sql1);//false return eder. Cunku data cagirmiyoruz

        //2.Örnek: Table'a worker_address sütunu ekleyerek alter yapın.
        String sql2 = "ALTER TABLE workers ADD worker_address VARCHAR(80)";
        boolean sql2b = st.execute(sql2);
        System.out.println("sql2b = " + sql2b);

        //3.Örnek: workers table'ini silin.
        String sql3 = "Drop table workers";
        st.execute(sql3);

        //5. Adim: Baglanti ve Statement'i kapat.
        con.close();
        st.close();


    }
}
