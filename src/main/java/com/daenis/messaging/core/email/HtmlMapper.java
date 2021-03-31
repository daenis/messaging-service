package com.daenis.messaging.core.email;

import com.daenis.messaging.core.shared.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.util.HashMap;
import java.util.Map;

// TODO: test
@Component
@RequiredArgsConstructor
public class HtmlMapper {

    private final SpringTemplateEngine thymeleafTemplateEngine;

    String getHtmlBodyFromMessage(Message message) {
        Map<String, Object> variables = new HashMap<>();
        variables.put("text", message.getBody());

        Context context = getNewContext();
        context.setVariables(variables);
        return thymeleafTemplateEngine.process("email-template.html", context);
    }

    Context getNewContext() {
        return new Context();
    }
}
