package de.juquint.alexa.wurf.Handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import java.util.Optional;
import java.util.Random;

import static com.amazon.ask.request.Predicates.intentName;

import static de.juquint.alexa.wurf.Handlers.ZahlIntentHandler.NUMBER_KEY;

public class WurfIntentHandler implements RequestHandler {
    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("WurfIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String speechText;
        Integer size = (Integer) input.getAttributesManager().getSessionAttributes().get(NUMBER_KEY);

        if (size != null) {
            Random ran = new Random();
            int number = ran.nextInt(size)+1;
            speechText = String.format("Ich habe eine %s gewürfelt.", number);
        } else {
            speechText = "Du hast noch keine Größe für deinen Würfel festgelegt. " +
                         "Du kannst dies tun, indem du zum Beispiel sagst, setze 6!";
        }

        return input.getResponseBuilder()
                .withShouldEndSession(false)
                .withSpeech(speechText)
                .withSimpleCard("Wurf", speechText)
                .build();
    }
}
