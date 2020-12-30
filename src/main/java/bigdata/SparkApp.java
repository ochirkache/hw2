package bigdata;

import lombok.extern.log4j.Log4j;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;
/**
 * @param args - args[0]: входной файл, args[1] - выходная папка
 */
@Log4j
public class SparkApp {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("spark.lab").setMaster("local");
        try(final JavaSparkContext context = new JavaSparkContext(conf)) {
            JavaRDD<String> textFile = context.textFile(args[0]);
            JavaPairRDD<String, Integer> resultRDD = StatCounter.AvgSalary(textFile);
            resultRDD.saveAsTextFile(args[1]);
        }
    }
}
