package main;

public class Project implements Runnable{
    private ProjectWindow projectWindow;
    private ProjectPanel projectPanel;
    private Thread projectThread;

    private final int FPS_SET = 120;
    private final int UPS_SET = 120;

    int frames = 0;
    long lastCheck = 0;

    int updates = 0;

    public Project() {
        projectPanel = new ProjectPanel();
        projectWindow = new ProjectWindow(projectPanel);
        projectPanel.setFocusable(true);
        projectPanel.requestFocus();

        startProjectLoop();
    }

    private void startProjectLoop() {
        projectThread = new Thread(this);
        projectThread.start();
    }

    public void updateProject() {
        projectPanel.updateProject();
    }

    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / UPS_SET;

        long previousTime = System.nanoTime();

        double deltaU = 0;
        double deltaF = 0;

        while (true) {
            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;

            previousTime = currentTime;

            if (deltaU >= 1) {
                updateProject();
                updates++;
                deltaU--;
            }

            if (deltaF >= 1) {
                projectPanel.repaint();
                frames++;
                deltaF--;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }
}
