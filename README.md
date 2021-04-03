# messaging-service

An implementation of Spring Boot's Messaging module using Thymeleaf.

Because the application requires a Gmail username & password, the `application.properties` file is not included. Simply create this file under the `resources` directory and include the following information:

```
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username={email-address}
spring.mail.password={password}
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```

Additionally, the Email account being used will have to be configured to allow less secure apps (https://support.google.com/accounts/answer/6010255?hl=en)


## Example Request
http://localhost:8080/api/messaging/send

**POST**
```
{
    "recipient": "recipient-address@gmail.com",
    "subject": "Another Test Email",
    "body": "Yet ANOTHER test!"
}
```
