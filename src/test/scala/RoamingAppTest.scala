import org.scalatest.FunSuite
import org.scalatest.BeforeAndAfter
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types._; 
import org.apache.spark.storage.StorageLevel;
import scala.io.Source;
import scala.collection.mutable.HashMap; 
import java.io.File;
import org.apache.spark.ml.Pipeline
import org.apache.spark.sql.Row; 
import org.apache.spark.sql.types._; 
import org.apache.spark.sql._;
import org.apache.spark.sql.functions._;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.catalyst.encoders.ExpressionEncoder;
import org.apache.spark.sql.Encoder;
import scala.collection.mutable.ListBuffer;
import org.apache.spark.util.IntParam; 
import org.apache.spark.util.StatCounter; 
import org.apache.spark.rdd.RDD; 
import org.apache.spark.SparkContext; 
import org.apache.spark.SparkContext._;
import org.apache.spark.SparkConf;
import org.apache.spark.sql.SQLContext;
import org.apache.spark._;
import org.apache.spark.rdd._; 


import com.holdenkarau.spark.testing.{RDDComparisons, SharedSparkContext,DataFrameSuiteBase}

class RoamingAppTest extends FunSuite with DataFrameSuiteBase with BeforeAndAfter {
  var roamingSchema:StructType = _;
  var df:DataFrame = _; 
  before {
    val sqlCtx = sqlContext
    import sqlCtx.implicits._
    roamingSchema = StructType(Array(
    StructField("visitorLoc", StringType, true),
    StructField("callDuration", DoubleType, true),
    StructField("phoneNumber", StringType, true),
    StructField("errorCode", StringType, true)))
    df = spark.createDataFrame(sc.emptyRDD[Row], roamingSchema)
    
    for(i <- 1 to 5){ df = df.union(Seq(("a",2.0,"01022223333","abc")).toDF())}
    for(i <- 1 to 3){ df = df.union(Seq(("b",2.0,"01022224444","cde")).toDF())}
    for(i <- 1 to 2){ df = df.union(Seq(("b",2.0,"01022227777","fgh")).toDF())}
  }
  
   test("test most error") {
     assert(RoamingApp.mostPersonDroped(df,1),Array(Row("01022223333")))
     assert(RoamingApp.mostPersonDroped(df,2),Array(Row("01022223333"),Row("01022224444")))
  }

}