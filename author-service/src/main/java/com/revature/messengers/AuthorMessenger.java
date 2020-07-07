package com.revature.messengers;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.entities.Author;

@Component
public class AuthorMessenger implements InitializingBean {

	// IAM authentication details
	@Value("${SNS_ACCESS_KEY}")
	private String accessKey;
	
	@Value("${SNS_SECRET_ACCESS_KEY}")
	private String secretAccessKey;
	
	// SQS location information
	@Value("${SNS_REGION}")
	private String region;
	
	@Value("arn:aws:sns:us-east-1:943074933845:author-delete")
	private String topicArn;
	
	/* Primary interface to Jackson - Allows us to convert Java objects to JSON data */
	@Autowired
	private ObjectMapper objectMapper;
	private AmazonSNS snsClient;
	private Logger logger = Logger.getLogger(AuthorMessenger.class);
	
	/**
	 * 1. This class is instantiated
	 * 2. Spring populates fields with values (@Value or @Autowired)
	 * 2.5 Spring calls afterPropertiesSet method
	 * 3. Bean is ready
	 */
	@Override
	public void afterPropertiesSet() {
		BasicAWSCredentials credentials = new BasicAWSCredentials(accessKey, secretAccessKey);
		snsClient = AmazonSNSClient.builder()
						.withCredentials(new AWSStaticCredentialsProvider(credentials))
						.withRegion(region)
						.build();
	}
	
	/**
	 * Consumes an Author object and sends a message to AWS SNS reporting the deletion
	 * of an Author so that subscribers can update accordingly.
	 * @param author
	 */
	public void sendAuthorDeletionMessage(Author author) {
		String json = null;
		
		try {
			// Converting author object to a JSON string
			json = objectMapper.writeValueAsString(author);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		// Creating an SNS request object
		PublishRequest request = new PublishRequest(topicArn, json);
		// Publishing request to SNS - if successful we're done
		PublishResult result = snsClient.publish(request);
		logger.info("Sending message: " + result.getMessageId());
	}
	
	
}
