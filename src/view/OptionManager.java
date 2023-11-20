package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OptionManager implements ActionListener {
    private OptionPanel optionPanel;
    private CardLayoutPanel card;

    public OptionManager(OptionPanel optionPanel, CardLayoutPanel card) {
        this.optionPanel = optionPanel;
        this.card = card;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("speichern")) {
            card.changePanel("menue");
        } else if (e.getActionCommand().equals("rowU")) {
            int row = Integer.valueOf(optionPanel.getLabelRow().getText());
            if (row == 10) {
                optionPanel.getLabelRow().setText("6");
            } else {
                optionPanel.getLabelRow().setText(String.valueOf(row + 1));
            }
        } else if (e.getActionCommand().equals("rowD")) {
            int row = Integer.valueOf(optionPanel.getLabelRow().getText());
            if (row == 6) {
                optionPanel.getLabelRow().setText("10");
            } else {
                optionPanel.getLabelRow().setText(String.valueOf(row - 1));
            }
        } else if (e.getActionCommand().equals("colU")) {
            int row = Integer.valueOf(optionPanel.getLabelCol().getText());
            if (row == 10) {
                optionPanel.getLabelCol().setText("6");
            } else {
                optionPanel.getLabelCol().setText(String.valueOf(row + 1));
            }
        } else if (e.getActionCommand().equals("colD")) {
            int row = Integer.valueOf(optionPanel.getLabelCol().getText());
            if (row == 6) {
                optionPanel.getLabelCol().setText("10");
            } else {
                optionPanel.getLabelCol().setText(String.valueOf(row - 1));
            }
        } else if (e.getActionCommand().equals("winsUp")) {
            int row = Integer.valueOf(optionPanel.getLabelW().getText());
            if (row == 6) {
                optionPanel.getLabelW().setText("4");
            } else {
                optionPanel.getLabelW().setText(String.valueOf(row + 1));
            }
        } else if (e.getActionCommand().equals("winsDown")) {
            int row = Integer.valueOf(optionPanel.getLabelW().getText());
            if (row == 4) {
                optionPanel.getLabelW().setText("6");
            } else {
                optionPanel.getLabelW().setText(String.valueOf(row - 1));
            }
        }

    }
}
