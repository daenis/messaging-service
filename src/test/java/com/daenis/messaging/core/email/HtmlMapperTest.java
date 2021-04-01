package com.daenis.messaging.core.email;

import com.daenis.messaging.core.shared.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.Context;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HtmlMapperTest {

    private String body;
    private Message message;

    private String html;

    @Mock
    private ITemplateEngine iTemplateEngine;

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
        htmlMapper = new HtmlMapper(iTemplateEngine) {
            @Override Context getNewContext() { return context; }
        };
    }

    @Test
    void shouldReturnAnHtmlStringForTheProvidedMessage() {
        when(iTemplateEngine.process("email-template.html", context)).thenReturn(html);

        String returnedHtml = htmlMapper.getHtmlBodyFromMessage(message);

        assertThat(returnedHtml).isEqualTo(html);
    }

    @Test
    void shouldSetTheVariablesInTheHtml() {
        htmlMapper.getHtmlBodyFromMessage(message);

        assertThat(context.getVariable("text")).isEqualTo(body);
    }
}