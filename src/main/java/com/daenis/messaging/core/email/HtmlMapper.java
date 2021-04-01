package com.daenis.messaging.core.email;

import com.daenis.messaging.core.shared.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.Context;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class HtmlMapper {

    private final ITemplateEngine iTemplateEngine;

    String getHtmlBodyFromMessage(Message message) {
        Map<String, Object> variables = new HashMap<>();
        variables.put("text", message.getBody());

        Context context = getNewContext();
        context.setVariables(variables);
        return iTemplateEngine.process("email-template.html", context);
    }

    Context getNewContext() {
        return new Context();
    }
}
