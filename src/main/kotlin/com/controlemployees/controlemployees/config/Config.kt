package com.controlemployees.controlemployees.config

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class Config {

    companion object{
        @JvmStatic var SERVICE_ENDPOINT: String = ""
        @JvmStatic var REGION: String = ""
        @JvmStatic var ACCESS_KEY: String = ""
        @JvmStatic var SECRET_KEY: String = ""
    }

    @Bean
    fun mapper(): DynamoDBMapper{
        return DynamoDBMapper(amazonDBConfig())
    }

    private fun amazonDBConfig(): AmazonDynamoDB? {
        return AmazonDynamoDBClientBuilder.standard()
            .withEndpointConfiguration(AwsClientBuilder.EndpointConfiguration(SERVICE_ENDPOINT, REGION))
            .withCredentials(AWSStaticCredentialsProvider(BasicAWSCredentials(ACCESS_KEY, SECRET_KEY))).build()
    }

}