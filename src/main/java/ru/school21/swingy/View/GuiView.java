package ru.school21.swingy.View;

import ru.school21.swingy.Model.GameUnit;
import ru.school21.swingy.Model.Hero;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Locale;

import static ru.school21.swingy.Swingy.*;
import static ru.school21.swingy.View.GuiUtils.*;

public class GuiView extends GameView{

    static JButton newButton;
    static JButton loadButton;
    static JButton exitButton;

    static JPanel mainPanel;
    static JPanel textPanel;
    static JScrollPane scroll;
    static JPanel rightPanel;
    static JTextField inputField;
    static JButton enterButton;
    static JLabel infoString;
    static JTextArea textArea;
    static JTextArea heroInfoArea;

    static JButton northButton;
    static JButton southButton;
    static JButton westButton;
    static JButton eastButton;

    static JButton yesButton;
    static JButton noButton;

    static JFrame mainFrame;

    public GuiView()
    {
        initGui();
    }

    public void initGui()
    {
        mainFrame = new JFrame("Swingy");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(800, 600);
        mainFrame.setResizable(false);

        mainPanel = new JPanel(); // master panel to maintain layout
        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        mainPanel.setMaximumSize(new Dimension(800, 600));
        mainPanel.setMinimumSize(new Dimension(800, 600));
        mainPanel.setPreferredSize(new Dimension(800, 600));

        textPanel = new JPanel(); // left panel with text
        textPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        textPanel.setMaximumSize(new Dimension(500, 500));
        textPanel.setMinimumSize(new Dimension(500, 500));
        textPanel.setPreferredSize(new Dimension(500, 500));
        mainPanel.add(textPanel);

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBackground(mainPanel.getBackground());

        scroll = new JScrollPane(textArea);
        scroll.setMaximumSize(new Dimension(490, 490));
        scroll.setMinimumSize(new Dimension(490, 490));
        scroll.setPreferredSize(new Dimension(490, 490));
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setEnabled(true);

        textPanel.add(scroll);

        rightPanel = new JPanel(); // right panel to maintain stats and buttons
        rightPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        rightPanel.setMaximumSize(new Dimension(290, 500));
        rightPanel.setMinimumSize(new Dimension(290, 500));
        rightPanel.setPreferredSize(new Dimension(290, 500));

        mainPanel.add(rightPanel);

        heroInfoArea = new JTextArea();
        heroInfoArea.setEditable(false);
        heroInfoArea.setLineWrap(true);
        heroInfoArea.setWrapStyleWord(true);
        heroInfoArea.setBackground(mainPanel.getBackground());

        heroInfoArea.setMaximumSize(new Dimension(270, 270));
        heroInfoArea.setMinimumSize(new Dimension(270, 270));
        heroInfoArea.setPreferredSize(new Dimension(270, 270));
        rightPanel.add(heroInfoArea);

        final JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        buttonPanel.setMaximumSize(new Dimension(270, 210));
        buttonPanel.setMinimumSize(new Dimension(270, 210));
        buttonPanel.setPreferredSize(new Dimension(270, 210));

        newButton = new JButton("Create new Hero");
        newButton.setPreferredSize(new Dimension(150, 20));
        loadButton = new JButton("Load Hero ");
        loadButton.setPreferredSize(new Dimension(150, 20));
        exitButton = new JButton("Exit game");
        exitButton.setPreferredSize(new Dimension(150, 20));
        northButton = new JButton("North (Up)");
        northButton.setPreferredSize(new Dimension(150, 20));
        southButton = new JButton("South (Down)");
        southButton.setPreferredSize(new Dimension(150, 20));
        westButton = new JButton("West (Left)");
        westButton.setPreferredSize(new Dimension(150, 20));
        eastButton = new JButton("East (Right)");
        eastButton.setPreferredSize(new Dimension(150, 20));
        yesButton = new JButton("Yes");
        yesButton.setPreferredSize(new Dimension(150, 20));
        noButton = new JButton("No");
        noButton.setPreferredSize(new Dimension(150, 20));

        buttonPanel.add(newButton);
        buttonPanel.add(loadButton);
        buttonPanel.add(exitButton);
        buttonPanel.add(northButton);
        buttonPanel.add(southButton);
        buttonPanel.add(westButton);
        buttonPanel.add(eastButton);
        buttonPanel.add(yesButton);
        buttonPanel.add(noButton);

        rightPanel.add(buttonPanel);

        inputField = new JTextField();
        inputField.setMaximumSize(new Dimension(600, 30));
        inputField.setMinimumSize(new Dimension(600, 30));
        inputField.setPreferredSize(new Dimension(600, 30));
        mainPanel.add(inputField);
        enterButton = new JButton();
        enterButton.setPreferredSize(new Dimension(100, 30));
        enterButton.setText("Enter");
        mainPanel.add(enterButton);

        infoString = new JLabel(); // bottom info string
        infoString.setMaximumSize(new Dimension(780, 30));
        infoString.setMinimumSize(new Dimension(780, 30));
        infoString.setPreferredSize(new Dimension(780, 30));
        infoString.setForeground(Color.red);
        infoString.setVerticalAlignment(3);
        mainPanel.add(infoString);

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        GuiUtils.hideDirectButtons();
        GuiUtils.hideChoiceButtons();

        mainFrame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        mainFrame.setVisible(true);
    }

    @Override
    public void displayWinMessage(String name) {
        setTextAreaText("Battle outcome with " + name + ":\nCongrats! You have overcome " + name);
    }

    @Override
    public void displayLoseMessage(String name) {
        setTextAreaText("Battle outcome with " + name + ":\nOh no! " + name + " had completely bite you!");
        heroInfoArea.setText("Press Exit to finish game");
    }

    @Override
    public void displayEnemyStat(GameUnit unit) {
        setTextAreaText("Your enemy: " + unit.toString());
    }

    @Override
    public void drawMessage(String msg) {
        if (cnt.getModel().isAlive())
            setTextAreaText(msg);
    }

    @Override
    public void displayHeroStat() {
        heroInfoArea.setText(getHeroStat());
    }

    @Override
    public void redrawMap(GameUnit[] map) {
        displayHeroStat();
        mainFrame.repaint();
    }

    @Override
    public void writeOut(String msg) {
        setTextAreaText(msg);
    }

    @Override
    public void fiasco() {
        setInfoStringText("- - - GAME OVER - - -");
        exitButton.setVisible(true);
        while (true) {}
    }

    @Override
    public String getUserInput() {
        return getMovement();
    }

    @Override
    public Hero loadHero(ArrayList<Hero> heroList) {

        if (heroList != null && heroList.size() > 0) {
            String text = "";
            text += "Available heroes:\n\n";
            int i = 0;
            for (Hero h : heroList) {
                text += (i + 1) + ": " + heroList.get(i) + "\n\n";
                i++;
            }

            setInfoStringText("Enter hero number from 1 to " + (heroList.size()));

            while (true) {
                setTextAreaText(text);
                int num = -1;
                String tmp = getSaveNumber();
                try {
                    num = Integer.parseInt(tmp);
                } catch (NumberFormatException e) { }
                if (num < 1 || num > heroList.size())
                    setInfoStringText("Wrong input. Enter correct save number (from 1 to " + heroList.size() + ")");
                else {
                    return heroList.get(num - 1);
                }
            }
        }
        exitButton.setVisible(true);
        return null;
    }

    @Override
    public String getCreateOrLoadDecision()
    {
        hideInputButtons();

        setInfoStringText("Press button to Create/Load Hero or Exit game");
        if (cnt.getHeroList() == null || cnt.getHeroList().size() == 0)
            loadButton.setVisible(false);

        final String[] ret = {""};
        while(ret[0].length() == 0)
        {
            newButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) { ret[0] = "1"; }
            });

            loadButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ret[0] = "2";
                }
            });
        }
        newButton.setVisible(false);
        loadButton.setVisible(false);
        exitButton.setVisible(false);
        String res = new String(ret[0]);
        showInputButtons();
        return res;
    }

    @Override
    public String getHeroName()
    {
        setTextAreaText(HERO_NAME);
        setInfoStringText("Type Hero name (3-30 symbols)");
        showInputButtons();

        final String[] ret = {""};

        boolean flag = false;
        while (!flag)
        {
            enterButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ret[0] = inputField.getText();
                }
            });

            if (ret[0].length() > 0) {
                String input = ret[0].toLowerCase(Locale.ROOT);
                if (cnt.validateInput(input, "2", "s", "y"))
                    flag = true;
                else
                {
                    ret[0] = "";
                    inputField.setText("");
                }
            }

        }
        hideInputButtons();
        setTextAreaText("");
        inputField.setText("");
        return ret[0];
    }

    @Override
    public String getHeroType()
    {
        setTextAreaText(HERO_TYPES);
        setInfoStringText("Choose Hero type (1-3)");
        showInputButtons();

        final String[] ret = {""};

        boolean flag = false;
        while (!flag && ret[0].length() == 0)
        {
            enterButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ret[0] = inputField.getText();
                }
            });

            if (ret[0].length() > 0) {
                String input = ret[0].toLowerCase(Locale.ROOT);
                if (cnt.validateInput("name", input, "s", "y"))
                    flag = true;
                else {
                    ret[0] = "";
                    inputField.setText("");
                }
            }
        }
        hideInputButtons();
        setTextAreaText("");
        setInfoStringText("");
        inputField.setText("");

        return ret[0];
    }

    public String getSaveNumber()
    {
        showInputButtons();
        inputField.setText("");

        String res = "";
        final String[] ret = {""};

        boolean flag = false;
        while (!flag && ret[0].length() == 0)
        {
            enterButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ret[0] = inputField.getText();
                }
            });

            if (ret[0].length() > 0) {
                res = ret[0].toLowerCase(Locale.ROOT);
                flag = true;
            }
        }
        inputField.setText("");
        hideInputButtons();
        textArea.setText("");


        return res;
    }

    @Override
    public String getArtifactAcception(String artInfo, String artName)
    {
        hideDirectButtons();
        yesButton.setText("Pick up artifact");
        noButton.setText("Leave artifact");
        showChoiceButtons();

        setInfoStringText("Choose to pick up or leave Artifact");

        setTextAreaText("Artifact '" + artName + "' found.\n" +
                artInfo +
                "Pick up new Artifact or keep yours?");

        final String[] ret = {""};
        while(ret[0].length() == 0)
        {
            yesButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) { ret[0] = "y"; }
            });

            noButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ret[0] = "n";
                }
            });
        }
        hideChoiceButtons();
        setInfoStringText("");
        yesButton.setText("Yes");
        noButton.setText("No");
        return ret[0];
    }

    @Override
    public void displayWinGame()
    {
        setTextAreaText("Hooray! You reach 8th level and win the game!");
        setInfoStringText("- - YOU BEAT ALL THE ENEMIES!! - -");
        hideDirectButtons();
        hideChoiceButtons();
        exitButton.setVisible(true);
        while(true) {} // infinite loop with only exit button active
    }

    public String getContinueOrExit()
    {
        hideDirectButtons();
        yesButton.setText("Continue ");
        noButton.setText("Exit");

        showChoiceButtons();
        setInfoStringText("Choose to continue or exit the game");
        setTextAreaText(CONTINUE_OR_EXIT_GUI);

        final String[] ret = {""};
        while(ret[0].length() == 0)
        {
            yesButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) { ret[0] = "y"; }
            });

            noButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ret[0] = "n";
                }
            });
        }
        hideChoiceButtons();
        yesButton.setText("Yes");
        noButton.setText("No");
        setTextAreaText("");
        setInfoStringText("");

        return ret[0];
    }

    public String getEscapeOrFight()
    {
        hideDirectButtons();
        yesButton.setText("Fight!");
        noButton.setText("Try to escape");
        showChoiceButtons();


        setInfoStringText("Choose to fight or try you luck to escape");
        setTextAreaText(CELL_IS_OCCUPIED_GUI);

        final String[] ret = {""};
        while(ret[0].length() == 0)
        {
            yesButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) { ret[0] = "y"; }
            });

            noButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ret[0] = "n";
                }
            });
        }
        hideChoiceButtons();
        yesButton.setText("Yes");
        noButton.setText("No");
        setInfoStringText("");
        return ret[0];
    }

    public String getMovement()
    {
        showDirectButtons();
        setInfoStringText(IN_GAME_INFO_GUI);
        final String[] ret = {""};

        boolean flag = false;
        while (!flag)
        {
            northButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ret[0] = "w";
                }
            });
            southButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ret[0] = "s";
                }
            });
            westButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) { ret[0] = "a"; }
            });
            eastButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) { ret[0] = "d"; }
            });

            if (ret[0].length() > 0) {
                String input = ret[0].toLowerCase(Locale.ROOT);
                if (cnt.validateInput("name", "2", input, "y"))
                    flag = true;
                else
                    ret[0] = "";
            }

        }
        setTextAreaText("");
        hideDirectButtons();
        setInfoStringText("");
        return ret[0];
    }
}
