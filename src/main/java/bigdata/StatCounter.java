package bigdata;

import lombok.val;
import org.apache.spark.Partitioner;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import scala.Tuple2;

public class StatCounter {
    /**
     * Функция подсчета средней ЗП
     * Парсит строку лога
     * @param inputRDD - входной DataSet для анализа
     * @return результат подсчета в формате JavaPairRDD
     */
    public static JavaPairRDD<String, Integer> AvgSalary(JavaRDD<String> inputRDD){
        JavaPairRDD<String, Integer> rdd = inputRDD.mapToPair(w -> {
            String[] split = w.split(",");
            return new Tuple2<String, Integer>(split[0] , Integer.parseInt(split[2]));
        });
        JavaPairRDD<String, Integer> rdd2 = inputRDD.mapToPair(w -> {
            String[] split = w.split(",");
            return new Tuple2<String, Integer>(split[0] , 1);
        });

        JavaPairRDD<String, Integer> transformedRDD = rdd.reduceByKey((x,y) -> x+y ).sortByKey();
        JavaPairRDD<String, Integer> transformedRDD2 = rdd2.reduceByKey((x,y) -> x+y ).sortByKey();
        JavaPairRDD<String, Tuple2<Integer,Integer>> RDD3 = transformedRDD.join(transformedRDD2);
        JavaPairRDD<String, Integer> RDD4 = RDD3.mapToPair( x -> new Tuple2<String, Integer>(x._1, x._2._1 / x._2._2));
        return RDD4.sortByKey();
    }
}
