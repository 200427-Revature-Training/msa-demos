package com.revature.listeners;

import java.io.IOException;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dtos.AuthorDto;
import com.revature.services.BookService;

@Component
public class AuthorListener implements InitializingBean {
	
	@Value("${SNS_ACCESS_KEY}")
	private String accessKey;
	
	@Value("${SNS_SECRET_ACCESS_KEY}")
	private String secretAccessKey;
	
	@Value("${SNS_REGION}")
	private String region;
	
	@Value("https://sqs.us-east-1.amazonaws.com/943074933845/book-deletion-by-author")
	private String sqsUrl;
	
	Logger logger = Logger.getLogger(AuthorListener.class);
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Autowired
	BookService bookService;
	
	private AmazonSQS sqsClient;

	@Override
	public void afterPropertiesSet() throws Exception {
		BasicAWSCredentials credentials = new BasicAWSCredentials(accessKey, secretAccessKey);
		sqsClient = AmazonSQSClient.builder()
						.withCredentials(new AWSStaticCredentialsProvider(credentials))
						.withRegion(region)
						.build();
	}
	
	
	@Scheduled(fixedRate = 15000)
	public void pollQueue() {
		ReceiveMessageResult pollResult = getMessages();
		List<Message> messages = pollResult.getMessages();
		messages.forEach(message -> {
			String body = message.getBody();
			try {
				AuthorDto author = objectMapper.readValue(body, AuthorDto.class);
				logger.info("Deleting all books with author ID " + author.getId());
				bookService.deleteBooksByAuthorId(author);
				sqsClient.deleteMessage(sqsUrl, message.getReceiptHandle());
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}
	
	/**
	 * Sends request to AWS SQS asking if it has messages available in the queue
	 * SQS may respond with an array of messages if there are messages available
	 * @return
	 */
	public ReceiveMessageResult getMessages() {
		ReceiveMessageRequest messageRequest = new ReceiveMessageRequest(sqsUrl);
		messageRequest.setVisibilityTimeout(15);
		ReceiveMessageResult result = sqsClient.receiveMessage(messageRequest);
		return result;
	}
	
	public void deleteMessage(String receiptHandle) {
		DeleteMessageRequest deleteReqeust = new DeleteMessageRequest(sqsUrl, receiptHandle);
		sqsClient.deleteMessage(deleteReqeust);
	}
}
