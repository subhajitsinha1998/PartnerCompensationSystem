package com.pcs.user.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;

@Configuration
public class AwsConfig {
    
    @Value(value = "${aws.cognito.region}")
    private String region;
    @Value(value = "${aws.access-key}")
    private String accessKey;
    @Value(value = "${aws.access-secret}")
    private String secretKey;
    
    @Bean
    public AWSCognitoIdentityProvider cognitoClient() {
        BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, secretKey);
        System.out.println("bean");
        AWSCognitoIdentityProvider cognitoClient = AWSCognitoIdentityProviderClientBuilder
                .standard().withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .withRegion(region).build();
        return cognitoClient;
    }
}
