import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {


        Homework homework = new Homework();
        homework.setConnection("root","2819");

      //  homework.getVillainsNamesEx2();
      //  homework.getMinionsNamesEx3();
      //  homework.addMinionsEx4();
        homework.changeTownNameCasingEx5();
    }



}