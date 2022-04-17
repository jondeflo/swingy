package ru.school21.swingy.View;

import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import static ru.school21.swingy.View.GuiView.*;

public class GuiUtils {

    private static String removeColors(String in) {

        String text = in;

        if (text.contains("\u001B[0m"))
            text = text.replace("\u001B[0m", "");
        if (text.contains("\033[0;91m"))
            text = text.replace("\033[0;91m", "");
        if (text.contains("\033[0;92m"))
            text = text.replace("\033[0;92m", "");
        if (text.contains("\033[0;93m"))
            text = text.replace("\033[0;93m", "");
        if (text.contains("\033[0;95m"))
            text = text.replace("\033[0;95m", "");
        if (text.contains("\033[0;96m"))
            text = text.replace("\033[0;96m", "");
        if (text.contains("\033[0;97m"))
            text = text.replace("\033[0;97m", "");

        return text;
    }

    static void setInfoStringText(String text) {
        infoString.setText(removeColors(text));
    }

    static void setTextAreaText(String text) {
        if (text.length() > 0) {
            final int[] verticalScrollBarMaximumValue = new int[1];
            verticalScrollBarMaximumValue[0] = scroll.getVerticalScrollBar().getMaximum();
            scroll.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {

                public void adjustmentValueChanged(AdjustmentEvent e) {
                    if ((verticalScrollBarMaximumValue[0] - e.getAdjustable().getMaximum()) == 0)
                        return;
                    e.getAdjustable().setValue(e.getAdjustable().getMaximum());
                    verticalScrollBarMaximumValue[0] = scroll.getVerticalScrollBar().getMaximum();
                }
            });

            textArea.setText(textArea.getText() + "\n" + removeColors(text));
        }
    }

    static void hideInputButtons() {
        inputField.setEnabled(false);
        enterButton.setEnabled(false);
    }

    static void showInputButtons() {
        inputField.setEnabled(true);
        enterButton.setEnabled(true);
    }


    static void hideChoiceButtons() {
        yesButton.setVisible(false);
        noButton.setVisible(false);
    }

    static void showChoiceButtons() {
        yesButton.setVisible(true);
        noButton.setVisible(true);
    }

    static void showDirectButtons() {
        northButton.setVisible(true);
        southButton.setVisible(true);
        westButton.setVisible(true);
        eastButton.setVisible(true);
    }

    static void hideDirectButtons() {
        northButton.setVisible(false);
        southButton.setVisible(false);
        westButton.setVisible(false);
        eastButton.setVisible(false);
    }

}
