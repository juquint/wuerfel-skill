package de.juquint.alexa.wurf.Handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import java.util.Optional;
import java.util.Random;

import static com.amazon.ask.request.Predicates.intentName;

public class WurfIntentHandler implements RequestHandler {
    public static final String ZAHL_KEY = "ZAHL";
    public static final String ZAHL_SLOT = "Zahl";

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("WurfIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String speechText;
        Integer zahl = (Integer) input.getAttributesManager().getSessionAttributes().get(ZAHL_KEY);

        if (zahl != null) {
            Random ran = new Random();
            int wurf = ran.nextInt(zahl)+1;
            speechText = String.format("Ich habe eine %s gewürfelt.", wurf);
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
