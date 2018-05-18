package de.juquint.alexa.wurf.Handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.*;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import static de.juquint.alexa.wurf.Handlers.WurfIntentHandler.ZAHL_KEY;
import static de.juquint.alexa.wurf.Handlers.WurfIntentHandler.ZAHL_SLOT;
import static com.amazon.ask.request.Predicates.intentName;

public class ZahlIntentHandler implements RequestHandler {
    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("ZahlIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        Request request = input.getRequestEnvelope().getRequest();
        IntentRequest intentRequest = (IntentRequest) request;
        Map<String, Slot> slots = intentRequest.getIntent().getSlots();

        Slot zahlSlot = slots.get(ZAHL_SLOT);

        String speechText;
        try{
            int zahl = Integer.parseInt(zahlSlot.getValue());
            input.getAttributesManager().setSessionAttributes(Collections.singletonMap(ZAHL_KEY, zahl));

            speechText = String.format("Du hast die Würfelgröße %s ausgewählt.", zahl);
        }catch (NumberFormatException e){
            speechText = "Du kannst als Würfelgröße nur eine Zahl wählen!";
        }

        return input.getResponseBuilder().withSimpleCard("Wurf", speechText)
                .withSpeech(speechText)
                .withShouldEndSession(false).build();
    }
}
