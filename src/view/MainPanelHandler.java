package view;

import control.MainController;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Jean-Pierre on 20.10.2016.
 */
public class MainPanelHandler {
    private JButton packButton01;
    private JButton sortButton01;
    private JButton packButton02;
    private JButton sortButton02;
    private JButton packButton03;
    private JButton sortButton03;
    private JButton packButton04;
    private JButton sortButton04;
    private JPanel panel;
    private JTextField artistTextField;
    private JTextField titleTextField;
    private JTextField bNrTextField;
    private JTextField positionTextField;
    private JButton addButton;
    private JButton releaseButton;
    private JList box01;
    private JList box02;
    private JList box03;
    private JList box04;
    private JTextArea outputArea;
    private JList[] allBoxes;
    private MainController controller;

    public MainPanelHandler(MainController controller){
        this.controller = controller;
        outputArea.setText("Welcome to your CD-Administration-Tool.");
        createButtonListener();
        createBoxesAndListeners();
    }

    public JPanel getPanel(){
        return panel;
    }

    private void createButtonListener(){
        packButton01.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pack(1);
            }
        });
        sortButton01.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sort(1);
            }
        });
        packButton02.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pack(2);
            }
        });
        sortButton02.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sort(2);
            }
        });
        packButton03.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pack(3);
            }
        });
        sortButton03.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sort(3);
            }
        });
        packButton04.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pack(4);
            }
        });
        sortButton04.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sort(4);
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNewCD();
            }
        });
        releaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                releaseCD();
            }
        });
    }

    private void createBoxesAndListeners(){
        allBoxes = new JList[4];

        allBoxes[0] = box01;
        allBoxes[1] = box02;
        allBoxes[2] = box03;
        allBoxes[3] = box04;

        for(int i = 0; i < 4; i++){
            update(i);
        }

        for(int i = 0; i < allBoxes.length; i++){
            final int tmp = i;
            allBoxes[i].addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    if(!allBoxes[tmp].isSelectionEmpty()){
                        bNrTextField.setText(String.valueOf(tmp+1));
                        positionTextField.setText(String.valueOf(allBoxes[tmp].getSelectedIndex()+1));
                        artistTextField.setText(controller.getSelectedCD(tmp,allBoxes[tmp].getSelectedIndex())[0]);
                        titleTextField.setText(controller.getSelectedCD(tmp,allBoxes[tmp].getSelectedIndex())[1]);
                    }
                    for(int j = 0; j < allBoxes.length; j++){
                        if(allBoxes[j] != allBoxes[tmp])
                            allBoxes[j].clearSelection();
                    }
                    selectTheRightBoxAndPlace();
                }
            });

        }
    }

    private void selectTheRightBoxAndPlace(){
        allBoxes[Integer.valueOf(bNrTextField.getText())-1].setSelectedIndex(Integer.valueOf(positionTextField.getText())-1);
    }

    private void pack(int index){
        controller.packBox(index-1);
        update(index-1);
    }

    private void sort(int index){
        controller.sortBox(index-1);
        update(index-1);
    }

    private void update(int index){
        DefaultListModel listModel = new DefaultListModel();

        String[] output = controller.showAllCDs(index);
        for(int i = 0; i < output.length; i = i + 2){
            String outputText = output[i] + " - " + output[i+1];
            listModel.addElement(outputText);
        }

        allBoxes[index].setModel(listModel);
    }

    private void addNewCD(){
        int boxNumber = Integer.parseInt(bNrTextField.getText());
        int position = Integer.parseInt(positionTextField.getText());
        String artist = artistTextField.getText();
        String title  = titleTextField.getText();

        if(controller.addNewCD(boxNumber-1,position-1,artist,title)){
            addTextToOutput("Added new CD "+ artist + " - " + title);
        }else{
            addTextToOutput("Adding a new CD failed. You might choose a different empty space.");
        }

        update(boxNumber-1);
    }

    private void releaseCD(){
        int boxNumber = Integer.parseInt(bNrTextField.getText());
        int position = Integer.parseInt(positionTextField.getText());

        if(controller.releaseCD(boxNumber-1,position-1)){
            addTextToOutput("Released a CD.");
        }else{
            addTextToOutput("Didn't find a CD to release. You might choose a different position.");
        }

        update(boxNumber-1);
    }

    private void addTextToOutput(String textToAdd){
        outputArea.setText(outputArea.getText() + "\n" + textToAdd);
    }
}
