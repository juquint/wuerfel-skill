package de.juquint.alexa.wurf.Handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.*;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class ZahlIntentHandler implements RequestHandler {
    public static final String NUMBER_KEY = "ZAHL";
    public static final String NUMBER_SLOT = "Zahl";

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("ZahlIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        Request request = input.getRequestEnvelope().getRequest();
        IntentRequest intentRequest = (IntentRequest) request;
        Map<String, Slot> slots = intentRequest.getIntent().getSlots();

        Slot numberSlot = slots.get(NUMBER_SLOT);

        String speechText;
        try{
            int size = Integer.parseInt(numberSlot.getValue());
            input.getAttributesManager().setSessionAttributes(Collections.singletonMap(NUMBER_KEY, size));

            speechText = String.format("Du hast die Würfelgröße %s ausgewählt.", size);
        }catch (NumberFormatException e){
            speechText = "Du kannst als Würfelgröße nur eine Zahl wählen!";
        }

        return input.getResponseBuilder().withSimpleCard("Wurf", speechText)
                .withSpeech(speechText)
                .withShouldEndSession(false).build();
    }
}
