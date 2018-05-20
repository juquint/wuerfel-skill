package de.juquint.alexa.wurf.Handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class HelpIntentHandler implements RequestHandler {
    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("AMAZON.HelpIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String speechText = "Du kannst mit mir Würfeln, indem du sagst würfel!";

        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("Wurf", speechText)
                .withReprompt(speechText)
                .build();
    }
}
