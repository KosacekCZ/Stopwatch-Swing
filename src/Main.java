public class Main {
    public static void main(String[] args) throws InterruptedException {
        GUI gui = new GUI();
        gui.init();
        gui.renderComponents();
        gui.repaint();
    }
}
