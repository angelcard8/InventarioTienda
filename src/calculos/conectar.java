package calculos;
import java.sql.Connection;
import java.sql.DriverManager;
public class conectar 
{
    String host="localhost";
    String bd="veshop";
    String usuario="root";
    String contra="root";
    //String contra="Password@123";
    
      Connection conecta = null;
       public Connection conexion () {
          try
 {        
                 Class.forName("com.mysql.jdbc.Driver");
                 //Creamos un enlace hacia la base de datos
                 conecta = DriverManager.getConnection("jdbc:mysql://"+host+":3306/"+bd+"?" +  "user="+usuario+"&password="+contra+"&characterEncoding=utf8");

          }
          catch (Exception e) {
              System.out.print (e.getMessage ()+"err");   
          }
          return conecta;
      }

  }
