package com.daenis.messaging.core.email;

import com.daenis.messaging.core.shared.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HtmlMapperTest {

    private String body;
    private Message message;

    private String html;

    @Mock
    private SpringTemplateEngine thymeleafTemplateEngine;

    private Context context;
    private HtmlMapper htmlMapper;

    @BeforeEach
    void init() {
        body = "Some Body";
        message = new Message() {
            @Override public String getRecipient() { return null; }
            @Override public String getSubject() { return null; }
            @Override public String getBody() { return body; }
        };

        html = "<h1>Some Body</h1>";

        context = new Context();
        htmlMapper = new HtmlMapper(thymeleafTemplateEngine) {
            @Override Context getNewContext() { return context; }
        };
    }

    // TODO: Why is the thymeleafTemplateEngine null?
    @Test
    void shouldReturnAnHtmlStringForTheProvidedMessage() {
        when(thymeleafTemplateEngine
                .process("email-template.html", context)).thenReturn(html);

        String returnedHtml = htmlMapper.getHtmlBodyFromMessage(message);

        assertThat(returnedHtml).isEqualTo(html);
    }

    @Test
    void shouldSetTheVariablesInTheHtml() {

    }
}