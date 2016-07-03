package sample;

import java.util.Arrays;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import sample.service.TestService;

@SpringBootApplication
public class HelloWorld implements CommandLineRunner {

	@Autowired
	TestService testService;

    public static void main(String[] args) {
    	SpringApplication.run(HelloWorld.class, args);
    }

	@Override
	public void run(String... args) throws Exception {
    	JavaSparkContext sc = new JavaSparkContext("local[*]", "Hello");
    	JavaRDD<String> rdd = sc.parallelize(Arrays.asList("a","b","c","d","e"));

    	rdd.foreach(data -> {
    		testService.test(data);
    	});
	}
}