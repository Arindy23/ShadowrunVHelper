package de.arindy.sharv.gui;

import de.arindy.sharv.api.gui.PersonalDataListener;
import de.arindy.sharv.api.gui.PersonalDataView;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.control.LabeledMatchers;
import org.testfx.matcher.control.TextFlowMatchers;

import static org.hamcrest.CoreMatchers.is;
import static org.testfx.api.FxAssert.verifyThat;

class PersonalDataPaneTest extends HeadlessGUITest {

    private PersonalDataView personalData;
    private TestPersonalDataListener personalDataListener;

    @Start
    private void start(Stage stage) {
        super.start(stage, new Scene(createPersonalDataPane()));
    }

    private PersonalDataPane createPersonalDataPane() {
        final PersonalDataPane personalDataPane = new PersonalDataPane();
        this.personalData = personalDataPane;
        personalDataPane.registerListener(createPersonalDataListener());
        return personalDataPane;
    }

    private TestPersonalDataListener createPersonalDataListener() {
        personalDataListener = new TestPersonalDataListener();
        return personalDataListener;
    }

    @Test
    void setKarmaShouldUpdateKarmaLabel(final FxRobot r) throws Exception {
        SharV.performAction(() -> personalData.setKarma(23));
        awaitSharV();
        verifyThat(robot(r).lookupLabel("#karma"), LabeledMatchers.hasText("23"));
    }

    @Test
    void setKarmaMaxShouldUpdateKarmaMaxLabel(final FxRobot r) throws Exception {
        SharV.performAction(() -> personalData.setKarmaMax(23));
        awaitSharV();
        verifyThat(robot(r).lookupLabel("#karmaMax"), LabeledMatchers.hasText("23"));
    }

    @Test
    void setNuyenMaxShouldUpdatenuyenLabel(final FxRobot r) throws Exception {
        SharV.performAction(() -> personalData.setNuyen(666));
        awaitSharV();
        verifyThat(robot(r).lookupLabel("#nuyen"), LabeledMatchers.hasText("666"));
    }

    @Test
    void changingNameShouldTriggerListener(final FxRobot r) {
        robot(r).clickOn("#name").write("Cthulhu");
        verifyThat(personalDataListener.name, is("Cthulhu"));
    }

    @Test
    void changingStreetnameShouldTriggerListener(final FxRobot r) {
        robot(r).clickOn("#streetname").write("Cthulhu");
        verifyThat(personalDataListener.streetname, is("Cthulhu"));
    }

    @Test
    void changingAgeShouldTriggerListener(final FxRobot r) {
        robot(r).clickOn("#age").write("23");
        verifyThat(personalDataListener.age, is(23));
    }

    @Test
    void ageShouldOnlyAcceptIntegers(final FxRobot r) {
        robot(r).clickOn("#age").write("noInt");
        verifyThat(personalDataListener.age, is(0));
    }

    @Test
    void changingHeightShouldTriggerListener(final FxRobot r) {
        robot(r).clickOn("#height").write("180");
        verifyThat(personalDataListener.height, is(180));
    }

    @Test
    void heightShouldOnlyAcceptIntegers(final FxRobot r) {
        robot(r).clickOn("#height").write("noInt");
        verifyThat(personalDataListener.height, is(0));
    }

    @Test
    void changingWeightShouldTriggerListener(final FxRobot r) {
        robot(r).clickOn("#weight").write("70");
        verifyThat(personalDataListener.weight, is(70));
    }

    @Test
    void weightShouldOnlyAcceptIntegers(final FxRobot r) {
        robot(r).clickOn("#weight").write("noInt");
        verifyThat(personalDataListener.weight, is(0));
    }

    @Test
    void changingEthnicityShouldTriggerListener(final FxRobot r) {
        robot(r).clickOn("#ethnicity").write("english");
        verifyThat(personalDataListener.ethnicity, is("english"));
    }

    @Test
    void changingConceptShouldTriggerListener(final FxRobot r) {
        robot(r).clickOn("#concept").write("Chaosmage");
        verifyThat(personalDataListener.concept, is("Chaosmage"));
    }

    @Test
    void changingStreetCredShouldTriggerListener(final FxRobot r) {
        robot(r).clickOn("#streetCred").write("23");
        verifyThat(personalDataListener.streetCred, is(23));
    }

    @Test
    void streetCredShouldOnlyAcceptIntegers(final FxRobot r) {
        robot(r).clickOn("#streetCred").write("noInt");
        verifyThat(personalDataListener.streetCred, is(0));
    }

    @Test
    void changingNotorietyShouldTriggerListener(final FxRobot r) {
        robot(r).clickOn("#notoriety").write("23");
        verifyThat(personalDataListener.notoriety, is(23));
    }

    @Test
    void notorietyShouldOnlyAcceptIntegers(final FxRobot r) {
        robot(r).clickOn("#notoriety").write("noInt");
        verifyThat(personalDataListener.notoriety, is(0));
    }

    @Test
    void changingPublicAwarenessShouldTriggerListener(final FxRobot r) {
        robot(r).clickOn("#publicAwareness").write("23");
        verifyThat(personalDataListener.publicAwareness, is(23));
    }

    @Test
    void publicAwarenessShouldOnlyAcceptIntegers(final FxRobot r) {
        robot(r).clickOn("#publicAwareness").write("noInt");
        verifyThat(personalDataListener.publicAwareness, is(0));
    }

    @Test
    void changingMetatypeShouldTriggerListener(final FxRobot r) throws Exception {
        SharV.performAction(() -> {
            personalData.removeMetatypes();
            personalData.addMetatypes("Human", "Elf");
        });
        robot(r).selectComboBoxItem("#metatype", "Human");
        verifyThat(personalDataListener.metatype, is("Human"));
    }

    @Test
    void changingSexShouldTriggerListener(final FxRobot r) throws Exception {
        SharV.performAction(() -> {
            personalData.removeAllSexes();
            personalData.addSexes("male", "female");
        });
        robot(r).selectComboBoxItem("#sex", "female");
        verifyThat(personalDataListener.sex, is("female"));
    }

    @Test
    void addingRacialBonusesShouldUpdateRacialBonusesTextArea(final FxRobot r) throws Exception {
        SharV.performAction(() -> {
            personalData.removeAllRacialBonuses();
            personalData.addRacialBonuses("firstBonus", "secondBonus");
        });
        awaitSharV();
        verifyThat(robot(r).lookupTextFlow("#racialBonuses"), TextFlowMatchers.hasText(String.format("%s%n%s", "firstBonus", "secondBonus")));
    }

    @Test
    void removingRacialBonusesShouldUpdateRacialBonusesTextArea(final FxRobot r) throws Exception {
        SharV.performAction(() -> robot(r).lookupTextFlow("#racialBonuses").getChildren().addAll(
                new Text("firstBonus"),
                new Text("\n"),
                new Text("secondBonus")
        ));
        SharV.performAction(() -> personalData.removeAllRacialBonuses());
        awaitSharV();
        verifyThat(robot(r).lookupTextFlow("#racialBonuses"), TextFlowMatchers.hasText(""));
    }

    private static class TestPersonalDataListener implements PersonalDataListener {

        private String name;
        private String streetname;
        private String metatype;
        private String sex;
        private int age;
        private int height;
        private int weight;
        private String ethnicity;
        private String concept;
        private int streetCred;
        private int notoriety;
        private int publicAwareness;

        @Override
        public void changeName(String name) {
            this.name = name;
        }

        @Override
        public void changeStreetname(String streetname) {
            this.streetname = streetname;
        }

        @Override
        public void changeMetatype(String metatype) {
            this.metatype = metatype;
        }

        @Override
        public void changeSex(String sex) {
            this.sex = sex;
        }

        @Override
        public void changeAge(int age) {
            this.age = age;
        }

        @Override
        public void changeHeight(int height) {
            this.height = height;
        }

        @Override
        public void changeWeight(int weight) {
            this.weight = weight;
        }

        @Override
        public void changeEthnicity(String ethnicity) {
            this.ethnicity = ethnicity;
        }

        @Override
        public void changeConcept(String concept) {
            this.concept = concept;
        }

        @Override
        public void changeStreetCred(int streetCred) {
            this.streetCred = streetCred;
        }

        @Override
        public void changeNotoriety(int notoriety) {
            this.notoriety = notoriety;
        }

        @Override
        public void changePublicAwareness(int publicAwareness) {
            this.publicAwareness = publicAwareness;
        }
    }

}
