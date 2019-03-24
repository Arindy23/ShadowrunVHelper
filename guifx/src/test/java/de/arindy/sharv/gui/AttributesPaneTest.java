package de.arindy.sharv.gui;

import de.arindy.sharv.api.gui.AttributesListener;
import de.arindy.sharv.api.gui.AttributesView;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.control.LabeledMatchers;

import static org.hamcrest.CoreMatchers.is;
import static org.testfx.api.FxAssert.verifyThat;

class AttributesPaneTest extends HeadlessGUITest {

    private AttributesView attributes;
    private TestAttributesListener attributesListener;

    @Start
    private void start(Stage stage) {
        super.start(stage, new Scene(createAttributesPane()));
    }

    private AttributesPane createAttributesPane() {
        final AttributesPane attributes = new AttributesPane();
        this.attributes = attributes;
        attributes.registerListener(createAttributesListener());
        return attributes;
    }

    private AttributesListener createAttributesListener() {
        attributesListener = new TestAttributesListener();
        return attributesListener;
    }

    @Test
    void changingBodyShouldTriggerListener(final FxRobot r) {
        robot(r).clickOn("#body").write("23");
        verifyThat(attributesListener.body, is(23));
    }

    @Test
    void bodyShouldOnlyAcceptIntegers(final FxRobot r) {
        robot(r).clickOn("#body").write("noInt");
        verifyThat(attributesListener.body, is(0));
    }

    @Test
    void changingReactionShouldTriggerListener(final FxRobot r) {
        robot(r).clickOn("#reaction").write("23");
        verifyThat(attributesListener.reaction, is(23));
    }

    @Test
    void agilityShouldOnlyAcceptIntegers(final FxRobot r) {
        robot(r).clickOn("#agility").write("noInt");
        verifyThat(attributesListener.agility, is(0));
    }

    @Test
    void changingAgilityShouldTriggerListener(final FxRobot r) {
        robot(r).clickOn("#agility").write("23");
        verifyThat(attributesListener.agility, is(23));
    }

    @Test
    void reactionShouldOnlyAcceptIntegers(final FxRobot r) {
        robot(r).clickOn("#reaction").write("noInt");
        verifyThat(attributesListener.reaction, is(0));
    }

    @Test
    void changingStrengthShouldTriggerListener(final FxRobot r) {
        robot(r).clickOn("#strength").write("23");
        verifyThat(attributesListener.strength, is(23));
    }

    @Test
    void strengthShouldOnlyAcceptIntegers(final FxRobot r) {
        robot(r).clickOn("#strength").write("noInt");
        verifyThat(attributesListener.strength, is(0));
    }

    @Test
    void changingWillpowerShouldTriggerListener(final FxRobot r) {
        robot(r).clickOn("#willpower").write("23");
        verifyThat(attributesListener.willpower, is(23));
    }

    @Test
    void willpowerShouldOnlyAcceptIntegers(final FxRobot r) {
        robot(r).clickOn("#willpower").write("noInt");
        verifyThat(attributesListener.willpower, is(0));
    }

    @Test
    void changingLogicShouldTriggerListener(final FxRobot r) {
        robot(r).clickOn("#logic").write("23");
        verifyThat(attributesListener.logic, is(23));
    }

    @Test
    void logicShouldOnlyAcceptIntegers(final FxRobot r) {
        robot(r).clickOn("#logic").write("noInt");
        verifyThat(attributesListener.logic, is(0));
    }

    @Test
    void changingIntuitionShouldTriggerListener(final FxRobot r) {
        robot(r).clickOn("#intuition").write("23");
        verifyThat(attributesListener.intuition, is(23));
    }

    @Test
    void intuitionShouldOnlyAcceptIntegers(final FxRobot r) {
        robot(r).clickOn("#intuition").write("noInt");
        verifyThat(attributesListener.intuition, is(0));
    }

    @Test
    void changingCharismaShouldTriggerListener(final FxRobot r) {
        robot(r).clickOn("#charisma").write("23");
        verifyThat(attributesListener.charisma, is(23));
    }

    @Test
    void charismaShouldOnlyAcceptIntegers(final FxRobot r) {
        robot(r).clickOn("#charisma").write("noInt");
        verifyThat(attributesListener.charisma, is(0));
    }

    @Test
    void changingSpecialValueShouldTriggerListener(final FxRobot r) {
        robot(r).clickOn("#specialValue").write("23");
        verifyThat(attributesListener.specialValue, is(23));
    }

    @Test
    void specialValueShouldOnlyAcceptIntegers(final FxRobot r) {
        robot(r).clickOn("#specialValue").write("noInt");
        verifyThat(attributesListener.specialValue, is(0));
    }

    @Test
    void changingEdgeShouldTriggerListener(final FxRobot r) {
        robot(r).clickOn("#edge").write("23");
        verifyThat(attributesListener.edge, is(23));
    }

    @Test
    void edgeShouldOnlyAcceptIntegers(final FxRobot r) {
        robot(r).clickOn("#edge").write("noInt");
        verifyThat(attributesListener.edge, is(0));
    }

    @Test
    void changingSpecialShouldTriggerListener(final FxRobot r) {
        Platform.runLater(() -> {
            attributes.removeSpecials();
            attributes.addSpecials("Magic", "poop");
        });
        robot(r).selectComboBoxItem("#special", "Magic");
        verifyThat(attributesListener.special, is("Magic"));
    }

    @Test
    void setSpecialShouldChangeSpecial(final FxRobot r) throws Exception {
        Platform.runLater(() -> {
            attributes.removeSpecials();
            attributes.addSpecials("Magic", "poop");
        });
        Platform.runLater(() -> attributes.setSpecial("poop"));
        awaitSharV();
        verifyThat(robot(r).lookupComboBox("#special").getSelectionModel().getSelectedItem(), is("poop"));
    }

    @Test
    void setBodyShouldUpdateBodyTextField(final FxRobot r) throws Exception {
        Platform.runLater(() -> attributes.setBody(23));
        awaitSharV();
        verifyThat(robot(r).lookupTextField("#body"), TextFieldMatchers.hasText("23"));
    }

    @Test
    void setBodyMaxShouldUpdateBodyMaxLabel(final FxRobot r) throws Exception {
        Platform.runLater(() -> attributes.setBodyMax(23));
        awaitSharV();
        verifyThat(robot(r).lookupLabel("#bodyMax"), LabeledMatchers.hasText("23"));
    }

    @Test
    void setBodyEffectiveShouldUpdateBodyEffectiveLabel(final FxRobot r) throws Exception {
        Platform.runLater(() -> attributes.setBodyEffective(23));
        awaitSharV();
        verifyThat(robot(r).lookupLabel("#bodyEffective"), LabeledMatchers.hasText("23"));
    }

    @Test
    void setAgilityShouldUpdateAgilityTextField(final FxRobot r) throws Exception {
        Platform.runLater(() -> attributes.setAgility(23));
        awaitSharV();
        verifyThat(robot(r).lookupTextField("#agility"), TextFieldMatchers.hasText("23"));
    }

    @Test
    void setAgilityMaxShouldUpdateAgilityMaxLabel(final FxRobot r) throws Exception {
        Platform.runLater(() -> attributes.setAgilityMax(23));
        awaitSharV();
        verifyThat(robot(r).lookupLabel("#agilityMax"), LabeledMatchers.hasText("23"));
    }

    @Test
    void setAgilityEffectiveShouldUpdateAgilityEffectiveLabel(final FxRobot r) throws Exception {
        Platform.runLater(() -> attributes.setAgilityEffective(23));
        awaitSharV();
        verifyThat(robot(r).lookupLabel("#agilityEffective"), LabeledMatchers.hasText("23"));
    }

    @Test
    void setReactionShouldUpdateReactionTextField(final FxRobot r) throws Exception {
        Platform.runLater(() -> attributes.setReaction(23));
        awaitSharV();
        verifyThat(robot(r).lookupTextField("#reaction"), TextFieldMatchers.hasText("23"));
    }

    @Test
    void setReactionMaxShouldUpdateReactionMaxLabel(final FxRobot r) throws Exception {
        Platform.runLater(() -> attributes.setReactionMax(23));
        awaitSharV();
        verifyThat(robot(r).lookupLabel("#reactionMax"), LabeledMatchers.hasText("23"));
    }

    @Test
    void setReactionEffectiveShouldUpdateReactionEffectiveLabel(final FxRobot r) throws Exception {
        Platform.runLater(() -> attributes.setReactionEffective(23));
        awaitSharV();
        verifyThat(robot(r).lookupLabel("#reactionEffective"), LabeledMatchers.hasText("23"));
    }

    @Test
    void setStrengthShouldUpdateReactionTextField(final FxRobot r) throws Exception {
        Platform.runLater(() -> attributes.setStrength(23));
        awaitSharV();
        verifyThat(robot(r).lookupTextField("#strength"), TextFieldMatchers.hasText("23"));
    }

    @Test
    void setStrengthMaxShouldUpdateStrengthMaxLabel(final FxRobot r) throws Exception {
        Platform.runLater(() -> attributes.setStrengthMax(23));
        awaitSharV();
        verifyThat(robot(r).lookupLabel("#strengthMax"), LabeledMatchers.hasText("23"));
    }

    @Test
    void setStrengthEffectiveShouldUpdateStrengthEffectiveLabel(final FxRobot r) throws Exception {
        Platform.runLater(() -> attributes.setStrengthEffective(23));
        awaitSharV();
        verifyThat(robot(r).lookupLabel("#strengthEffective"), LabeledMatchers.hasText("23"));
    }

    @Test
    void setWillpowerShouldUpdateReactionTextField(final FxRobot r) throws Exception {
        Platform.runLater(() -> attributes.setWillpower(23));
        awaitSharV();
        verifyThat(robot(r).lookupTextField("#willpower"), TextFieldMatchers.hasText("23"));
    }

    @Test
    void setWillpowerMaxShouldUpdateWillpowerMaxLabel(final FxRobot r) throws Exception {
        Platform.runLater(() -> attributes.setWillpowerMax(23));
        awaitSharV();
        verifyThat(robot(r).lookupLabel("#willpowerMax"), LabeledMatchers.hasText("23"));
    }

    @Test
    void setWillpowerEffectiveShouldUpdateWillpowerEffectiveLabel(final FxRobot r) throws Exception {
        Platform.runLater(() -> attributes.setWillpowerEffective(23));
        awaitSharV();
        verifyThat(robot(r).lookupLabel("#willpowerEffective"), LabeledMatchers.hasText("23"));
    }

    @Test
    void setLogicShouldUpdateReactionTextField(final FxRobot r) throws Exception {
        Platform.runLater(() -> attributes.setLogic(23));
        awaitSharV();
        verifyThat(robot(r).lookupTextField("#logic"), TextFieldMatchers.hasText("23"));
    }

    @Test
    void setLogicMaxShouldUpdateLogicMaxLabel(final FxRobot r) throws Exception {
        Platform.runLater(() -> attributes.setLogicMax(23));
        awaitSharV();
        verifyThat(robot(r).lookupLabel("#logicMax"), LabeledMatchers.hasText("23"));
    }

    @Test
    void setLogicEffectiveShouldUpdateLogicEffectiveLabel(final FxRobot r) throws Exception {
        Platform.runLater(() -> attributes.setLogicEffective(23));
        awaitSharV();
        verifyThat(robot(r).lookupLabel("#logicEffective"), LabeledMatchers.hasText("23"));
    }

    @Test
    void setIntuitionShouldUpdateReactionTextField(final FxRobot r) throws Exception {
        Platform.runLater(() -> attributes.setIntuition(23));
        awaitSharV();
        verifyThat(robot(r).lookupTextField("#intuition"), TextFieldMatchers.hasText("23"));
    }

    @Test
    void setIntuitionMaxShouldUpdateIntuitionMaxLabel(final FxRobot r) throws Exception {
        Platform.runLater(() -> attributes.setIntuitionMax(23));
        awaitSharV();
        verifyThat(robot(r).lookupLabel("#intuitionMax"), LabeledMatchers.hasText("23"));
    }

    @Test
    void setIntuitionEffectiveShouldUpdateIntuitionEffectiveLabel(final FxRobot r) throws Exception {
        Platform.runLater(() -> attributes.setIntuitionEffective(23));
        awaitSharV();
        verifyThat(robot(r).lookupLabel("#intuitionEffective"), LabeledMatchers.hasText("23"));
    }

    @Test
    void setCharismaShouldUpdateReactionTextField(final FxRobot r) throws Exception {
        Platform.runLater(() -> attributes.setCharisma(23));
        awaitSharV();
        verifyThat(robot(r).lookupTextField("#charisma"), TextFieldMatchers.hasText("23"));
    }

    @Test
    void setCharismaMaxShouldUpdateCharismaMaxLabel(final FxRobot r) throws Exception {
        Platform.runLater(() -> attributes.setCharismaMax(23));
        awaitSharV();
        verifyThat(robot(r).lookupLabel("#charismaMax"), LabeledMatchers.hasText("23"));
    }

    @Test
    void setCharismaEffectiveShouldUpdateCharismaEffectiveLabel(final FxRobot r) throws Exception {
        Platform.runLater(() -> attributes.setCharismaEffective(23));
        awaitSharV();
        verifyThat(robot(r).lookupLabel("#charismaEffective"), LabeledMatchers.hasText("23"));
    }

    @Test
    void setComposureShouldUpdateComposureLabel(final FxRobot r) throws Exception {
        Platform.runLater(() -> attributes.setComposure(23));
        awaitSharV();
        verifyThat(robot(r).lookupLabel("#composure"), LabeledMatchers.hasText("23"));
    }

    @Test
    void setJudgeIntentionsShouldUpdateJudgeIntentionsLabel(final FxRobot r) throws Exception {
        Platform.runLater(() -> attributes.setJudgeIntentions(23));
        awaitSharV();
        verifyThat(robot(r).lookupLabel("#judgeIntentions"), LabeledMatchers.hasText("23"));
    }

    @Test
    void setMemoryShouldUpdateMemoryLabel(final FxRobot r) throws Exception {
        Platform.runLater(() -> attributes.setMemory(23));
        awaitSharV();
        verifyThat(robot(r).lookupLabel("#memory"), LabeledMatchers.hasText("23"));
    }

    @Test
    void setLiftCarryShouldUpdateLiftCarryLabel(final FxRobot r) throws Exception {
        Platform.runLater(() -> attributes.setLiftCarry(23));
        awaitSharV();
        verifyThat(robot(r).lookupLabel("#liftCarry"), LabeledMatchers.hasText("23"));
    }

    @Test
    void setWalkShouldUpdateWalkLabel(final FxRobot r) throws Exception {
        Platform.runLater(() -> attributes.setWalk(23));
        awaitSharV();
        verifyThat(robot(r).lookupLabel("#walk"), LabeledMatchers.hasText("23"));
    }

    @Test
    void setRunShouldUpdateRunLabel(final FxRobot r) throws Exception {
        Platform.runLater(() -> attributes.setRun(23));
        awaitSharV();
        verifyThat(robot(r).lookupLabel("#run"), LabeledMatchers.hasText("23"));
    }

    @Test
    void setSprintShouldUpdateSprintLabel(final FxRobot r) throws Exception {
        Platform.runLater(() -> attributes.setSprint(23));
        awaitSharV();
        verifyThat(robot(r).lookupLabel("#sprint"), LabeledMatchers.hasText("23"));
    }

    @Test
    void setPhysicalLimitShouldUpdatePhysicalLimitLabel(final FxRobot r) throws Exception {
        Platform.runLater(() -> attributes.setPhysicalLimit(23));
        awaitSharV();
        verifyThat(robot(r).lookupLabel("#physicalLimit"), LabeledMatchers.hasText("23"));
    }

    @Test
    void setMentalLimitShouldUpdateMentalLimitLabel(final FxRobot r) throws Exception {
        Platform.runLater(() -> attributes.setMentalLimit(23));
        awaitSharV();
        verifyThat(robot(r).lookupLabel("#mentalLimit"), LabeledMatchers.hasText("23"));
    }

    @Test
    void setSocialLimitShouldUpdateSocialLimitLabel(final FxRobot r) throws Exception {
        Platform.runLater(() -> attributes.setSocialLimit(23));
        awaitSharV();
        verifyThat(robot(r).lookupLabel("#socialLimit"), LabeledMatchers.hasText("23"));
    }

    @Test
    void setEssenceShouldUpdateEssenceLabel(final FxRobot r) throws Exception {
        Platform.runLater(() -> attributes.setEssence(23));
        awaitSharV();
        verifyThat(robot(r).lookupLabel("#essence"), LabeledMatchers.hasText("23"));
    }

    @Test
    void setEdgeShouldUpdateEdgeLabel(final FxRobot r) throws Exception {
        Platform.runLater(() -> attributes.setEdge(23));
        awaitSharV();
        verifyThat(robot(r).lookupTextField("#edge"), TextFieldMatchers.hasText("23"));
    }

    @Test
    void setSpecialValueShouldUpdateSpecialValueLabel(final FxRobot r) throws Exception {
        Platform.runLater(() -> attributes.setSpecialValue(23));
        awaitSharV();
        verifyThat(robot(r).lookupTextField("#specialValue"), TextFieldMatchers.hasText("23"));
    }

    @Test
    void setInitiativePhysicalShouldUpdateInitiativePhysicalLabel(final FxRobot r) throws Exception {
        Platform.runLater(() -> attributes.setInitiativePhysical(23));
        awaitSharV();
        verifyThat(robot(r).lookupLabel("#initiativePhysical"), LabeledMatchers.hasText("23"));
    }

    @Test
    void setInitiativePhysicalDiceShouldUpdateInitiativePhysicalDiceLabel(final FxRobot r) throws Exception {
        Platform.runLater(() -> attributes.setInitiativePhysicalDice(23));
        awaitSharV();
        verifyThat(robot(r).lookupLabel("#initiativePhysicalDice"), LabeledMatchers.hasText("23"));
    }

    @Test
    void setInitiativeAstralShouldUpdateInitiativeAstralLabel(final FxRobot r) throws Exception {
        Platform.runLater(() -> attributes.setInitiativeAstral(23));
        awaitSharV();
        verifyThat(robot(r).lookupLabel("#initiativeAstral"), LabeledMatchers.hasText("23"));
    }

    @Test
    void setInitiativeAstralDiceShouldUpdateInitiativeAstralDiceLabel(final FxRobot r) throws Exception {
        Platform.runLater(() -> attributes.setInitiativeAstralDice(23));
        awaitSharV();
        verifyThat(robot(r).lookupLabel("#initiativeAstralDice"), LabeledMatchers.hasText("23"));
    }

    @Test
    void setInitiativeMatrixARShouldUpdateInitiativeMatrixARLabel(final FxRobot r) throws Exception {
        Platform.runLater(() -> attributes.setInitiativeMatrixAR(23));
        awaitSharV();
        verifyThat(robot(r).lookupLabel("#initiativeMatrixAR"), LabeledMatchers.hasText("23"));
    }

    @Test
    void setInitiativeMatrixARDiceShouldUpdateInitiativeMatrixARDiceLabel(final FxRobot r) throws Exception {
        Platform.runLater(() -> attributes.setInitiativeMatrixARDice(23));
        awaitSharV();
        verifyThat(robot(r).lookupLabel("#initiativeMatrixARDice"), LabeledMatchers.hasText("23"));
    }

    @Test
    void setInitiativeMatrixVRcoldShouldUpdateInitiativeMatrixVRcoldLabel(final FxRobot r) throws Exception {
        Platform.runLater(() -> attributes.setInitiativeMatrixVRcold(23));
        awaitSharV();
        verifyThat(robot(r).lookupLabel("#initiativeMatrixVRcold"), LabeledMatchers.hasText("23"));
    }

    @Test
    void setInitiativeMatrixVRcoldDiceShouldUpdateInitiativeMatrixVRcoldDiceLabel(final FxRobot r) throws Exception {
        Platform.runLater(() -> attributes.setInitiativeMatrixVRcoldDice(23));
        awaitSharV();
        verifyThat(robot(r).lookupLabel("#initiativeMatrixVRcoldDice"), LabeledMatchers.hasText("23"));
    }

    @Test
    void setInitiativeMatrixVRhotShouldUpdateInitiativeMatrixVRhotLabel(final FxRobot r) throws Exception {
        Platform.runLater(() -> attributes.setInitiativeMatrixVRhot(23));
        awaitSharV();
        verifyThat(robot(r).lookupLabel("#initiativeMatrixVRhot"), LabeledMatchers.hasText("23"));
    }

    @Test
    void setInitiativeMatrixVRhotDiceShouldUpdateInitiativeMatrixVRhotDiceLabel(final FxRobot r) throws Exception {
        Platform.runLater(() -> attributes.setInitiativeMatrixVRhotDice(23));
        awaitSharV();
        verifyThat(robot(r).lookupLabel("#initiativeMatrixVRhotDice"), LabeledMatchers.hasText("23"));
    }

    @Test
    void setInitiativeRiggingARShouldUpdateInitiativeRiggingARLabel(final FxRobot r) throws Exception {
        Platform.runLater(() -> attributes.setInitiativeRiggingAR(23));
        awaitSharV();
        verifyThat(robot(r).lookupLabel("#initiativeRiggingAR"), LabeledMatchers.hasText("23"));
    }

    @Test
    void setInitiativeRiggingARDiceShouldUpdateInitiativeRiggingARDiceLabel(final FxRobot r) throws Exception {
        Platform.runLater(() -> attributes.setInitiativeRiggingARDice(23));
        awaitSharV();
        verifyThat(robot(r).lookupLabel("#initiativeRiggingARDice"), LabeledMatchers.hasText("23"));
    }

    @Test
    void setInitiativeRiggingVRcoldShouldUpdateInitiativeRiggingVRcoldLabel(final FxRobot r) throws Exception {
        Platform.runLater(() -> attributes.setInitiativeRiggingVRcold(23));
        awaitSharV();
        verifyThat(robot(r).lookupLabel("#initiativeRiggingVRcold"), LabeledMatchers.hasText("23"));
    }

    @Test
    void setInitiativeRiggingVRcoldDiceShouldUpdateInitiativeRiggingVRcoldDiceLabel(final FxRobot r) throws Exception {
        Platform.runLater(() -> attributes.setInitiativeRiggingVRcoldDice(23));
        awaitSharV();
        verifyThat(robot(r).lookupLabel("#initiativeRiggingVRcoldDice"), LabeledMatchers.hasText("23"));
    }

    @Test
    void setInitiativeRiggingVRhotShouldUpdateInitiativeRiggingVRhotLabel(final FxRobot r) throws Exception {
        Platform.runLater(() -> attributes.setInitiativeRiggingVRhot(23));
        awaitSharV();
        verifyThat(robot(r).lookupLabel("#initiativeRiggingVRhot"), LabeledMatchers.hasText("23"));
    }

    @Test
    void setInitiativeRiggingVRhotDiceShouldUpdateInitiativeRiggingVRhotDiceLabel(final FxRobot r) throws Exception {
        Platform.runLater(() -> attributes.setInitiativeRiggingVRhotDice(23));
        awaitSharV();
        verifyThat(robot(r).lookupLabel("#initiativeRiggingVRhotDice"), LabeledMatchers.hasText("23"));
    }

    @Test
    void setInitiativeRiggingDirectShouldUpdateInitiativeRiggingDirectLabel(final FxRobot r) throws Exception {
        Platform.runLater(() -> attributes.setInitiativeRiggingDirect(23));
        awaitSharV();
        verifyThat(robot(r).lookupLabel("#initiativeRiggingDirect"), LabeledMatchers.hasText("23"));
    }

    @Test
    void setInitiativeRiggingDirectDiceShouldUpdateInitiativeRiggingDirectDiceLabel(final FxRobot r) throws Exception {
        Platform.runLater(() -> attributes.setInitiativeRiggingDirectDice(23));
        awaitSharV();
        verifyThat(robot(r).lookupLabel("#initiativeRiggingDirectDice"), LabeledMatchers.hasText("23"));
    }

    @Test
    void burnEdgeShouldTriggerListener(final FxRobot r) throws Exception {
        Platform.runLater(() -> attributes.setEdge(9));
        awaitSharV();
        robot(r).clickOn("#edge#5");
        verifyThat(attributesListener.burnedEdge, is(5));
    }

    @Test
    void unBurnEdgeShouldTriggerListener(final FxRobot r) throws Exception {
        Platform.runLater(() -> {
            attributes.setEdge(9);
            attributes.setBurnedEdge(6);
        });
        awaitSharV();
        robot(r).clickOn("#edge#5");
        verifyThat(attributesListener.burnedEdge, is(4));
    }

    private static class TestAttributesListener implements AttributesListener {

        private int body;
        private int agility;
        private int reaction;
        private int strength;
        private int willpower;
        private int logic;
        private int intuition;
        private int charisma;
        private String special;
        private int specialValue;
        private int edge;
        private int burnedEdge;

        @Override
        public void changeBody(int body) {
            this.body = body;
        }

        @Override
        public void changeAgility(int agility) {
            this.agility = agility;
        }

        @Override
        public void changeReaction(int reaction) {
            this.reaction = reaction;
        }

        @Override
        public void changeStrength(int strength) {
            this.strength = strength;
        }

        @Override
        public void changeWillpower(int willpower) {
            this.willpower = willpower;
        }

        @Override
        public void changeLogic(int logic) {
            this.logic = logic;
        }

        @Override
        public void changeIntuition(int intuition) {
            this.intuition = intuition;
        }

        @Override
        public void changeCharisma(int charisma) {
            this.charisma = charisma;
        }

        @Override
        public void changeSpecial(String special) {
            this.special = special;
        }

        @Override
        public void changeSpecialValue(int specialValue) {
            this.specialValue = specialValue;
        }

        @Override
        public void changeEdge(int edge) {
            this.edge = edge;
        }

        @Override
        public void changeBurnedEdge(int burnedEdge) {
            this.burnedEdge = burnedEdge;
        }
    }

}
