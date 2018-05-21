package de.juquint.alexa.wurf.Handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

import static de.juquint.alexa.wurf.Handlers.ZahlIntentHandler.NUMBER_KEY;

public class HelpIntentHandler implements RequestHandler {
    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("AMAZON.HelpIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String speechText;
        Integer size = (Integer) input.getAttributesManager().getSessionAttributes().get(NUMBER_KEY);

        if(size != null){
            speechText = "Du kannst mit mir Würfeln, indem du sagst würfel!";
        }else {
            speechText = "Du kannst eine Würfelgröße festlegen, indem du zum Beispiel sagst setze 12. " +
                         "Danach kannst du eine Zahl würfeln, indem du sagst würfel";
        }

        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("Wurf", speechText)
                .withReprompt(speechText)
                .build();
    }
}
