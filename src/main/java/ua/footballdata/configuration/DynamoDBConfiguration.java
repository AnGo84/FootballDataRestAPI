package ua.footballdata.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;

@Profile("dev")
@Configuration
@EnableDynamoDBRepositories(basePackages = "ua.footballdata.repositories")

@PropertySource(ignoreResourceNotFound = true, value = "classpath:application.properties")
public class DynamoDBConfiguration {

	public static final Logger logger = LoggerFactory.getLogger(DynamoDBConfiguration.class);

	@Value("${amazon.dynamodb.endpoint}")
	private String amazonDynamoDBEndpoint;

	@Value("${amazon.dynamodb.accesskey}")
	private String amazonDynamoDBAccessKey;

	@Value("${amazon.dynamodb.secretkey}")
	private String amazonDynamoDBSecretKey;

	/*
	 * @Bean public AmazonDynamoDB amazonDynamoDB() {
	 * logger.info("Get AmazonDynamoDB"); AmazonDynamoDB amazonDynamoDB = new
	 * AmazonDynamoDBClient(amazonAWSCredentials());
	 * 
	 * if (!StringUtils.isEmpty(amazonDynamoDBEndpoint)) {
	 * amazonDynamoDB.setEndpoint(amazonDynamoDBEndpoint); }
	 * 
	 * return amazonDynamoDB; }
	 * 
	 * @Bean public AWSCredentials amazonAWSCredentials() {
	 * logger.info("Get AWSCredentials"); return new
	 * BasicAWSCredentials(amazonDynamoDBAccessKey, amazonDynamoDBAccessKey); }
	 */

	@Bean
	public AWSCredentials amazonAWSCredentials() {
		return new BasicAWSCredentials(amazonDynamoDBAccessKey, amazonDynamoDBSecretKey);
	}

	@Bean
	@Primary
	public DynamoDBMapperConfig dynamoDBMapperConfig() {
		// DynamoDBMapperConfig config =
		// DynamoDBMapperConfig.builder().withConversionSchema(ConversionSchemas.V2).build();
		// return config;
		/*logger.info("dynamoDBMapperConfig: ");
		logger.info("amazonDynamoDBEndpoint: " + amazonDynamoDBEndpoint);
		logger.info("amazonDynamoDBAccessKey: " + amazonDynamoDBAccessKey);*/

		return DynamoDBMapperConfig.DEFAULT;
	}

	@Bean
	@Primary
	public DynamoDBMapper dynamoDBMapper(AmazonDynamoDB amazonDynamoDB, DynamoDBMapperConfig config) {
		return new DynamoDBMapper(amazonDynamoDB, config);
	}

	@Bean
	public AmazonDynamoDB amazonDynamoDB() {
		AmazonDynamoDB amazonDynamoDB = AmazonDynamoDBClientBuilder.standard()
				.withCredentials(amazonAWSCredentialsProvider()).withRegion(Regions.EU_WEST_3).build();
		amazonDynamoDB.listTables().getTableNames().forEach(table -> logger.info("Table: " + table));

		return amazonDynamoDB;
	}

	@Bean
	public DynamoDB dynamoDB() {
		return new DynamoDB(amazonDynamoDB());
	}

	public AWSCredentialsProvider amazonAWSCredentialsProvider() {
		return new AWSStaticCredentialsProvider(amazonAWSCredentials());
	}

}
