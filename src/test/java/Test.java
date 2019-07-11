import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.QueryTest$;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.streaming.StreamTest;
import org.scalatest.Assertions;

import java.util.List;

public class Test {

    private static void checkAnswer(Dataset<Row> actual, List<Row> expected) {
        String errorMessage = QueryTest$.MODULE$.checkAnswer(actual, expected);
        if (errorMessage != null) {
            System.out.println(errorMessage);
        }
    }
}
