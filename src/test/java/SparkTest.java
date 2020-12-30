import bigdata.StatCounter;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import scala.Tuple2;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SparkTest {

    private JavaSparkContext sparkContext;
    private final List<String> input = new ArrayList<>();
    private final List<Tuple2<String, Integer>> expectedOutput = new ArrayList<>();

    @BeforeEach
    public void init() throws ParseException {
        SparkConf conf = new SparkConf();
        conf.setMaster("local");
        conf.setAppName("junit");
        sparkContext = new JavaSparkContext(conf);

        input.add("1,8,1000");
        input.add("1,7,2000");
        input.add("2,12,3000");

        expectedOutput.add(new Tuple2<String, Integer>("1", 1500));
        expectedOutput.add(new Tuple2<String, Integer>("2", 3000));
    }

    @Test
    public void testRDD() {
        JavaRDD<String> inputRDD = sparkContext.parallelize(input);
        JavaPairRDD<String, Integer> resultRDD = StatCounter.AvgSalary(inputRDD);
        List<Tuple2<String, Integer>> resultList = resultRDD.collect();
        assertEquals(expectedOutput.size(), resultList.size());
        assertTrue(resultList.containsAll(expectedOutput));
    }
}
