import org.apache.spark.sql.functions.count
import org.apache.spark.sql.test.SharedSQLContext
import org.apache.spark.sql.{QueryTest, Row}

class SparkTestSuite extends QueryTest with SharedSQLContext {

  import testImplicits._

  test("vikas ") {

    Seq(1, 2, 3)
      .map(i => (i, i.toString))
      .toDF("int", "str")
      .groupBy("str")
      .agg($"str", count("str").as("strCount"))
      .createOrReplaceTempView("df")

    sql(
      """
SELECT x.str, SUM(x.strCount)
FROM df x JOIN df y ON x.str = y.str
GROUP BY x.str
        """).show()

    checkAnswer(
      sql(
        """
          |SELECT x.str, SUM(x.strCount)
          |FROM df x JOIN df y ON x.str = y.str
          |GROUP BY x.str
        """.stripMargin),
      Row("1", 1) :: Row("2", 1) :: Row("3", 1) :: Nil)
  }

}
