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



object RoamingApp{
  

   def main(args: Array[String]): Unit = {
     val spark = SparkSession.builder().master("local[*]").appName("Roaming Drop").getOrCreate();
     import spark.implicits._

    val roamingSchema = StructType(Array(
        StructField("visitorLoc", StringType, true),
        StructField("callDuration", DoubleType, true),
        StructField("phoneNumber", StringType, true),
        StructField("errorCode", StringType, true)))
     val dataLoc = "hdfs:///tmp/cdr.csv"
     val df = spark.read.format("csv").schema(roamingSchema).load(dataLoc)
     mostPersonDroped(df,10).foreach(println);

  }
 def mostPersonDroped(df:DataFrame,number:Int):Array[Row]={
     df.groupBy("phoneNumber").count().orderBy(desc("count")).select("phoneNumber").take(number)
     
   }
  
}