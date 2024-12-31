package main;

import javax.swing.*;

public class ProjectWindow extends JFrame {
    public ProjectWindow(ProjectPanel projectPanel) {

        JFrame jFrame = new JFrame();

        jFrame.setTitle("Template");

        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.add(projectPanel);
        jFrame.setResizable(false);

        jFrame.setUndecorated(true);

        jFrame.pack();
        jFrame.setLocationRelativeTo(null);

        jFrame.setVisible(true);
    }
}