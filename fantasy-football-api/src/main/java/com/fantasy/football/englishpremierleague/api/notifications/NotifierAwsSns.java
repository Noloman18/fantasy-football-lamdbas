package com.fantasy.football.englishpremierleague.api.notifications;

import com.amazonaws.services.sns.AmazonSNSAsync;
import com.amazonaws.services.sns.model.PublishRequest;
import com.fantasy.football.englishpremierleague.api.clients.ClientFactory;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;

public class NotifierAwsSns implements Notifier {
    private AmazonSNSAsync amazonSNSAsync;

    public NotifierAwsSns() {
        ClientFactory clientFactory = new ClientFactory();
        amazonSNSAsync = clientFactory.createSnsClient();
    }

    @Override
    public void sendNotification(Throwable throwable) {
        if (throwable == null)
            return;

        sendNotification(
                String.format("Error time %s", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())),
                Arrays.stream(throwable.getStackTrace())
                        .map(error -> error.toString()).collect(Collectors.joining("\r\n")));
    }

    @Override
    public void sendNotification(String subject, String message) {
        try {
            PublishRequest publishReq = new PublishRequest()
                    .withTopicArn("arn:aws:sns:us-east-2:073655690990:fantasy-football-events")
                    .withMessage(message)
                    .withSubject(subject);
            amazonSNSAsync.publish(publishReq);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
