import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TxtSwing extends JFrame {

    private JTextArea textAreaOutput;

    public TxtSwing() throws IOException {
        super("txt");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.FlowLayout());
        textAreaOutput = new JTextArea("111", 10, 10);
        textAreaOutput.setSelectedTextColor(Color.RED);
        textAreaOutput.setLineWrap(true); // 激活自动换行功能
        textAreaOutput.setWrapStyleWord(true); // 激活断行不断字功能
//        BufferedReader reader = new BufferedReader(new FileReader(
////                "D:\\wc.txt"));
////        String str = reader.readLine();
////        String end = null;
////        while ((str != null)) {
////            end = end + str + "\n";
////            str = reader.readLine();
////        }
        textAreaOutput.setText("asd");

        getContentPane().add(textAreaOutput);
        setSize(1024, 768);

    }

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        TxtSwing tm = new TxtSwing();
        tm.setVisible(true);
    }

}
