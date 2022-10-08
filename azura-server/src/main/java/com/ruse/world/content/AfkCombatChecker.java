package com.ruse.world.content;

import com.ruse.GameSettings;
import com.ruse.model.Animation;
import com.ruse.model.Locations;
import com.ruse.model.Position;
import com.ruse.util.Misc;
import com.ruse.util.Stopwatch;
import com.ruse.webhooks.discord.DiscordMessager;
import com.ruse.world.World;
import com.ruse.world.entity.impl.player.Player;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AfkCombatChecker {

    private Player player;

    @Getter
    @Setter
    private int afkCaught = 0;

    @Getter
    private final Stopwatch afkCombatTimer = new Stopwatch();

    @Getter
    @Setter
    private boolean answeringQuestions = false;

    @Getter
    private final Stopwatch answeringTimer = new Stopwatch();

    @Getter
    @Setter
    private int answerID;

    @Getter
    @Setter
    private String[] answers;

    @Getter
    @Setter
    private String question;


    public AfkCombatChecker(Player player) {
        this.player = player;
    }

    public void check() {
        if (afkCombatTimer.elapsed(780_000)) {//13
            openQuestions();
            if (answeringQuestions && answeringTimer.elapsed(120_000)) {
                moveHome(false);
            }
        }
    }


    public void openQuestions() {
        if (player.getLocation() == Locations.Location.HOME_BANK){
            answeringQuestions = false;
            afkCombatTimer.reset();
            answeringTimer.reset();
            return;
        }

        if (!answeringQuestions) {
            if (Misc.getRandom(1) == 0) {
                Questions questions = Questions.values()[Misc.getRandom(Questions.values().length - 1)];
                String answer = questions.getAnswers()[0];

                List<String> strList = Arrays.asList(questions.getAnswers().clone());
                Collections.shuffle(strList);
                answers = strList.toArray(new String[strList.size()]);
                for (int i = 0; i < 3; i++) {
                    if (answer == answers[i])
                        answerID = i;
                }

                question = questions.getQuestion();
            } else {
                int number1 = 1 + Misc.getRandom(8);
                int number2 = 1 + Misc.getRandom(8);

                answerID = Misc.getRandom(2);

                answers = new String[3];
                for (int i = 0; i < 3; i++) {
                    if (i == answerID) {
                        answers[i] = "" + (number1 + number2);
                    } else {
                        if (Misc.getRandom(1) == 0)
                            answers[i] = "" + Misc.getRandom(1);
                        else
                            answers[i] = "" + (20 + Misc.getRandom(10));

                    }
                }
                question = "What is " + number1 + "+" + number2 + "?";
            }

            answeringQuestions = true;
            answeringTimer.reset();

            World.sendStaffMessage("<col=FF0066><img=2> [AFK]<col=6600FF> " + player.getUsername() + " has been AFK checked. ");

        }

        player.getPacketSender().sendString(116004, question);
        for (int i = 0; i < 3; i++) {
            player.getPacketSender().sendString(116005 + i, answers[i]);
        }
        player.getPacketSender().sendInterface(116000);
    }


    public boolean handleButton(int buttonID) {

        if (buttonID >= 116005 && buttonID <= 116007) {
            int index = buttonID - 116005;
            answerQuestion(index);

            return true;
        }
        return false;

    }


    public void answerQuestion(int index) {
        player.getPacketSender().sendInterfaceRemoval();
        answeringQuestions = false;
        afkCombatTimer.reset();
        answeringTimer.reset();
        if (answerID == index) {
            World.sendStaffMessage("<col=FF0066><img=2> [AFK]<col=6600FF> " + player.getUsername() + " has passed the AFK checked. ");
            player.sendMessage("@blu@You answered the AFK Question correctly.");
        } else {
            moveHome(true);
        }
    }


    public void moveHome(boolean answered) {
        afkCaught += 1;
        answeringQuestions = false;
        answeringTimer.reset();
        afkCombatTimer.reset();
        player.getCombatBuilder().reset(true);

        player.moveTo(GameSettings.DEFAULT_POSITION);

        World.sendStaffMessage("<col=FF0066><img=2> [AFK]<col=6600FF> " + player.getUsername() + " has FAILED the AFK checked. @red@Offenses: " + afkCaught);

        if (answered)
            player.sendMessage("@red@You answered the AFK Question incorrectly.");
        else
            player.sendMessage("@red@You failed to answer the AFK Question in time.");

        player.getPacketSender().sendString(117005, answered ? "@red@Answered incorrectly" : "@red@Didn't answer AFK Question");
        player.getPacketSender().sendString(117006, "Total offenses:\\n@red@" + afkCaught);
        player.getPacketSender().sendInterface(117000);

        if (afkCaught >= 3) {
            ZonedDateTime  zonedDateTime = ZonedDateTime.now().plusHours(6);
            int hour = zonedDateTime.getHour();
            String hourPrefix = hour < 10 ? "0" + hour + "" : "" + hour + "";
            int minute = zonedDateTime.getMinute();
            String minutePrefix = minute < 10 ? "0" + minute + "" : "" + minute + "";

            DiscordMessager.sendAFKJail(player.getUsername() +
                    "==" +
                    "Offenses: " + afkCaught +
                    "==" +
                    "Jailed: " + Misc.getCurrentServerTime() +
                    "==" +
                    "Free: " + hourPrefix + ":" + minutePrefix + ""

            );
            PlayerPunishment.Jail.jailPlayer(player.getUsername());

            player.getSkillManager().stopSkilling();
            player.getPacketSender().sendMessage("You have been jailed by " + player.getUsername() + ".");
            World.sendStaffMessage("<col=FF0066><img=2> [PUNISHMENTS]<col=6600FF> " + player.getUsername()
                    + " has just been jailed by AFK checker ");

            player.performAnimation(new Animation(1994));
            player.moveTo(new Position(2510, 9326));
            player.setLocation(Locations.Location.JAIL);

            player.getPacketSender().sendString(117006, "Total offenses:\\n@red@" + afkCaught);

        }
    }


    @AllArgsConstructor
    @Getter
    public enum Questions {
        NAME("What is the name of this server?", new String[]{"Lunite", "OSRS", "Runescape"}),
        YEAR("What is year is it?", new String[]{"2022", "2011", "2030"}),
        ;
        private String question;
        private String[] answers;
    }

}
