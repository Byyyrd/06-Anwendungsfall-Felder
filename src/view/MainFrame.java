package view;

import control.MainController;

import javax.swing.*;

/**
 * Created by Jean-Pierre on 20.10.2016.
 */
public class MainFrame extends JFrame {

    public MainFrame(MainController controller){
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setContentPane(new MainPanelHandler(controller).getPanel());
        this.setBounds(100,100,1100,700);
        this.setVisible(true);
    }

}
