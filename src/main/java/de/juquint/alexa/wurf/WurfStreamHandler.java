package de.juquint.alexa.wurf;

import com.amazon.ask.Skill;
import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.Skills;
import de.juquint.alexa.wurf.Handlers.*;

public class WurfStreamHandler extends SkillStreamHandler{
    private static final String SKILL_ID="";

    public WurfStreamHandler(){
        super(buildSkill());
    }

    private static Skill buildSkill() {
        return Skills.standard()
            .addRequestHandlers(
                    new CancelStopIntentHandler(),
                    new WurfIntentHandler(),
                    new ZahlIntentHandler(),
                    new HelpIntentHandler(),
                    new LaunchRequestHandler(),
                    new SessionEndedRequestHandler())
            .withSkillId(SKILL_ID)
            .build();
    }
}
