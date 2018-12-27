import com.eltima.components.ui.DatePicker;
import util.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.*;

/**
 * @ClassName MainWindow
 * @Description 程序主窗体
 * @Author gz
 * @Date 2018/12/26 15:57
 * @Version 1.0
 **/
public class MainWindow extends JFrame {
    //日期输入框
    private JTextField startDateField;
    private JTextField endDateField;
    //标识框
    private JLabel startDateLabel;
    private JLabel endDateLabel;
    private JLabel CM1PIDChangeLable;
    private JLabel CM2PIDChangeLable;
    private JLabel CM3PIDChangeLable;
    //日期控件
    private DatePicker startDatepick;
    private DatePicker endDatepick;
    //按键
    private JButton getStartDateButton;
    private JButton getEndDateButton;
    private JButton getResultButton;
    private JButton dataUpdateButton;
    private JButton getPIDChangeButton;
    //文本输出框
    private JTextArea MPSAreaOutput;
    private JTextArea MessageAreaOutput;
    private JTextArea CM1PIDChangeAreaOutput;
    private JTextArea CM2PIDChangeAreaOutput;
    private JTextArea CM3PIDChangeAreaOutput;
    //滚动条面板
    private JScrollPane CM1jScrollPane;
    private JScrollPane CM2jScrollPane;
    private JScrollPane CM3jScrollPane;

    private static long startDate = 0;
    private static long endDate = 0;

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MainWindow inst = new MainWindow();
                inst.setLocationRelativeTo(null);
                inst.setVisible(true);
                inst.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            }
        });

    }

    public MainWindow() {
        super();
        initGUI();
    }

    //初始化窗口
    private void initGUI() {
        try {
            setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            getContentPane().setLayout(null);
            this.setFocusable(false);
            this.setTitle("CM段故障统计");
            this.setFont(new java.awt.Font("Arial", 1, 10));
            {

                startDateLabel = new JLabel();
                startDateLabel.setText("起始时间");
                startDateLabel.setBounds(40, 100, 200, 20);
                getContentPane().add(startDateLabel);

                endDateLabel = new JLabel();
                endDateLabel.setText("截止时间");
                endDateLabel.setBounds(40, 180, 200, 20);
                getContentPane().add(endDateLabel);

                CM1PIDChangeLable = new JLabel();
                CM1PIDChangeLable.setBounds(80, 660, 200, 20);
                CM1PIDChangeLable.setText("CM1 IQPIDChange统计");
                getContentPane().add(CM1PIDChangeLable);

                CM2PIDChangeLable = new JLabel();
                CM2PIDChangeLable.setBounds(340, 660, 200, 20);
                CM2PIDChangeLable.setText("CM2 IQPIDChange统计");
                getContentPane().add(CM2PIDChangeLable);

                CM3PIDChangeLable = new JLabel();
                CM3PIDChangeLable.setBounds(580, 660, 200, 20);
                CM3PIDChangeLable.setText("CM3 IQPIDChange统计");
                getContentPane().add(CM3PIDChangeLable);

                startDatepick = Datepicker.getStartDatePicker();
                startDatepick.setBounds(170, 100, 150, 25);
                getContentPane().add(startDatepick);

                endDatepick = Datepicker.getEndDatePicker();
                endDatepick.setBounds(170, 180, 150, 25);
                getContentPane().add(endDatepick);

                MPSAreaOutput = new JTextArea(45, 80);
                MPSAreaOutput.setBounds(40, 300, 480, 150);
                getContentPane().add(MPSAreaOutput);

                MessageAreaOutput = new JTextArea(45, 80);
                MessageAreaOutput.setBounds(200, 20, 200, 30);
                getContentPane().add(MessageAreaOutput);

                CM1PIDChangeAreaOutput = new JTextArea(45, 80);
                CM1PIDChangeAreaOutput.setBounds(40, 500, 480, 150);

                CM2PIDChangeAreaOutput = new JTextArea(45, 80);
                CM2PIDChangeAreaOutput.setBounds(40, 500, 480, 150);

                CM3PIDChangeAreaOutput = new JTextArea(45, 80);
                CM3PIDChangeAreaOutput.setBounds(40, 500, 480, 150);

                CM1jScrollPane = new JScrollPane(CM1PIDChangeAreaOutput);
                CM2jScrollPane = new JScrollPane(CM2PIDChangeAreaOutput);
                CM3jScrollPane = new JScrollPane(CM3PIDChangeAreaOutput);

                CM1jScrollPane.setBounds(40, 500, 240, 150);
                CM1jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                getContentPane().add(CM1jScrollPane);

                CM2jScrollPane.setBounds(290, 500, 240, 150);
                CM2jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                getContentPane().add(CM2jScrollPane);

                CM3jScrollPane.setBounds(540, 500, 240, 150);
                CM3jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                getContentPane().add(CM3jScrollPane);
            }
            getContentPane().add(getStartDateButton());
            getContentPane().add(getEndDateButton());
            getContentPane().add(getResultButton());
            getContentPane().add(dataUpdateButton());
            getContentPane().add(getPIDChangeButton());

            pack();
            setSize(850, 750);
        } catch (Exception e) {
            // add your error handling code here
            e.printStackTrace();
        }
    }

    private JTextField getStartDateField() {
        if (startDateField == null) {
            startDateField = new JTextField();
            startDateField.setBounds(150, 77, 100, 30);
            startDateField.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
//                    startDate = startDateField.getText();
                    System.out.println(startDate);
                }
            });
        }
        return startDateField;
    }

    //Phase limlit num enter
    private JTextField getEndDateField() {
        if (endDateField == null) {
            endDateField = new JTextField();
            endDateField.setBounds(150, 160, 100, 30);
            endDateField.addActionListener(new ActionListener() { //鐩戝惉鍣?
                public void actionPerformed(ActionEvent e) {
//                    endDate = endDateField.getText();
                    System.out.println(endDate);
                }
            });
        }
        return endDateField;
    }

    //获取起始时间
    private JButton getStartDateButton() {
        if (getStartDateButton == null) {
            getStartDateButton = new JButton("获取时间");
            getStartDateButton.setBounds(400, 100, 100, 30);
            getStartDateButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {

                    String s = startDatepick.getValue().toString();
                    SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.US);
                    Date d = null;
                    try {
                        d = sdf.parse(s);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    startDate = d.getTime();
                }
            });
        }
        return getStartDateButton;
    }

    //获取截止时间
    private JButton getEndDateButton() {
        if (getEndDateButton == null) {
            getEndDateButton = new JButton("获取时间");
            getEndDateButton.setBounds(400, 180, 100, 30);
            getEndDateButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    String s = endDatepick.getValue().toString();
                    SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.US);
                    Date d = null;
                    try {
                        d = sdf.parse(s);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    endDate = d.getTime();
                }
            });
        }
        return getEndDateButton;
    }

    //获取MPS故障统计
    private JButton getResultButton() {
        if (getResultButton == null) {
            getResultButton = new JButton("MPS故障统计");
            getResultButton.setBounds(40, 260, 120, 30);
            getResultButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    int CM1_1MPSTimes = 0;
                    int CM1_2MPSTimes = 0;
                    int CM1_3MPSTimes = 0;
                    int CM1_4MPSTimes = 0;
                    int CM1_5MPSTimes = 0;
                    int CM1_6MPSTimes = 0;

                    int CM2_1MPSTimes = 0;
                    int CM2_2MPSTimes = 0;
                    int CM2_3MPSTimes = 0;
                    int CM2_4MPSTimes = 0;
                    int CM2_5MPSTimes = 0;
                    int CM2_6MPSTimes = 0;

                    int CM3_1MPSTimes = 0;
                    int CM3_2MPSTimes = 0;
                    int CM3_3MPSTimes = 0;
                    int CM3_4MPSTimes = 0;
                    int CM3_5MPSTimes = 0;
                    if (startDate != 0 && endDate != 0) {
                        try {
                            //CM1故障统计
                            CM1_1MPSTimes = JcifsUtil.readAndStatic(Constant.LOCALTEMPPATH + File.separator + "CM1-1visa.log", startDate, endDate);
                            CM1_2MPSTimes = JcifsUtil.readAndStatic(Constant.LOCALTEMPPATH + File.separator + "CM1-2visa.log", startDate, endDate);
                            CM1_3MPSTimes = JcifsUtil.readAndStatic(Constant.LOCALTEMPPATH + File.separator + "CM1-3visa.log", startDate, endDate);
                            CM1_4MPSTimes = JcifsUtil.readAndStatic(Constant.LOCALTEMPPATH + File.separator + "CM1-4visa.log", startDate, endDate);
                            CM1_5MPSTimes = JcifsUtil.readAndStatic(Constant.LOCALTEMPPATH + File.separator + "CM1-5visa.log", startDate, endDate);
                            CM1_6MPSTimes = JcifsUtil.readAndStatic(Constant.LOCALTEMPPATH + File.separator + "CM1-6visa.log", startDate, endDate);
                            //CM2故障统计
                            CM2_1MPSTimes = JcifsUtil.readAndStatic(Constant.LOCALTEMPPATH + File.separator + "CM2-1visa.log", startDate, endDate);
                            CM2_2MPSTimes = JcifsUtil.readAndStatic(Constant.LOCALTEMPPATH + File.separator + "CM2-2visa.log", startDate, endDate);
                            CM2_3MPSTimes = JcifsUtil.readAndStatic(Constant.LOCALTEMPPATH + File.separator + "CM2-3visa.log", startDate, endDate);
                            CM2_4MPSTimes = JcifsUtil.readAndStatic(Constant.LOCALTEMPPATH + File.separator + "CM2-4visa.log", startDate, endDate);
                            CM2_5MPSTimes = JcifsUtil.readAndStatic(Constant.LOCALTEMPPATH + File.separator + "CM2-5visa.log", startDate, endDate);
                            CM2_6MPSTimes = JcifsUtil.readAndStatic(Constant.LOCALTEMPPATH + File.separator + "CM2-6visa.log", startDate, endDate);
                            //CM3故障统计
                            CM3_1MPSTimes = JcifsUtil.readAndStatic(Constant.LOCALTEMPPATH + File.separator + "CM3-1visa.log", startDate, endDate);
                            CM3_2MPSTimes = JcifsUtil.readAndStatic(Constant.LOCALTEMPPATH + File.separator + "CM3-2visa.log", startDate, endDate);
                            CM3_3MPSTimes = JcifsUtil.readAndStatic(Constant.LOCALTEMPPATH + File.separator + "CM3-3visa.log", startDate, endDate);
                            CM3_4MPSTimes = JcifsUtil.readAndStatic(Constant.LOCALTEMPPATH + File.separator + "CM3-4visa.log", startDate, endDate);
                            CM3_5MPSTimes = JcifsUtil.readAndStatic(Constant.LOCALTEMPPATH + File.separator + "CM3-5visa.log", startDate, endDate);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    MPSAreaOutput.setText("CM1-1保护次数：" + CM1_1MPSTimes + "      " + "CM2-1保护次数：" + CM2_1MPSTimes + "      " + "CM3-1保护次数：" + CM3_1MPSTimes + "\r\n");
                    MPSAreaOutput.append("CM1-2保护次数：" + CM1_2MPSTimes + "      " + "CM2-2保护次数：" + CM2_2MPSTimes + "      " + "CM3-2保护次数：" + CM3_2MPSTimes + "\r\n");
                    MPSAreaOutput.append("CM1-3保护次数：" + CM1_3MPSTimes + "      " + "CM2-3保护次数：" + CM2_3MPSTimes + "      " + "CM3-3保护次数：" + CM3_3MPSTimes + "\r\n");
                    MPSAreaOutput.append("CM1-4保护次数：" + CM1_4MPSTimes + "      " + "CM2-4保护次数：" + CM2_4MPSTimes + "      " + "CM3-4保护次数：" + CM3_4MPSTimes + "\r\n");
                    MPSAreaOutput.append("CM1-5保护次数：" + CM1_5MPSTimes + "      " + "CM2-5保护次数：" + CM2_5MPSTimes + "      " + "CM3-5保护次数：" + CM3_5MPSTimes + "\r\n");
                    MPSAreaOutput.append("CM1-6保护次数：" + CM1_6MPSTimes + "      " + "CM2-6保护次数：" + CM2_6MPSTimes + "\r\n");
                }
            });
        }
        return getResultButton;
    }

    //获取IQPIDChange统计
    private JButton getPIDChangeButton() {
        if (getPIDChangeButton == null) {
            getPIDChangeButton = new JButton("IQPIDChange统计");
            getPIDChangeButton.setBounds(40, 460, 140, 30);
            getPIDChangeButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {

                    List CM1_1PIDTimes = null;
                    List CM1_2PIDTimes = null;
                    List CM1_3PIDTimes = null;
                    List CM1_4PIDTimes = null;
                    List CM1_5PIDTimes = null;
                    List CM1_6PIDTimes = null;

                    List CM2_1PIDTimes = null;
                    List CM2_2PIDTimes = null;
                    List CM2_3PIDTimes = null;
                    List CM2_4PIDTimes = null;
                    List CM2_5PIDTimes = null;
                    List CM2_6PIDTimes = null;

                    List CM3_1PIDTimes = null;
                    List CM3_2PIDTimes = null;
                    List CM3_3PIDTimes = null;
                    List CM3_4PIDTimes = null;
                    List CM3_5PIDTimes = null;
                    if (startDate != 0 && endDate != 0) {
                        try {
                            //CM1PID变化统计
                            CM1_1PIDTimes = JcifsUtil.PIDChangeStatic(Constant.LOCALTEMPPATH + File.separator + "CM1-1visa.log", startDate, endDate);
                            CM1_2PIDTimes = JcifsUtil.PIDChangeStatic(Constant.LOCALTEMPPATH + File.separator + "CM1-2visa.log", startDate, endDate);
                            CM1_3PIDTimes = JcifsUtil.PIDChangeStatic(Constant.LOCALTEMPPATH + File.separator + "CM1-3visa.log", startDate, endDate);
                            CM1_4PIDTimes = JcifsUtil.PIDChangeStatic(Constant.LOCALTEMPPATH + File.separator + "CM1-4visa.log", startDate, endDate);
                            CM1_5PIDTimes = JcifsUtil.PIDChangeStatic(Constant.LOCALTEMPPATH + File.separator + "CM1-5visa.log", startDate, endDate);
                            CM1_6PIDTimes = JcifsUtil.PIDChangeStatic(Constant.LOCALTEMPPATH + File.separator + "CM1-6visa.log", startDate, endDate);
                            //CM2PID变化统计
                            CM2_1PIDTimes = JcifsUtil.PIDChangeStatic(Constant.LOCALTEMPPATH + File.separator + "CM2-1visa.log", startDate, endDate);
                            CM2_2PIDTimes = JcifsUtil.PIDChangeStatic(Constant.LOCALTEMPPATH + File.separator + "CM2-2visa.log", startDate, endDate);
                            CM2_3PIDTimes = JcifsUtil.PIDChangeStatic(Constant.LOCALTEMPPATH + File.separator + "CM2-3visa.log", startDate, endDate);
                            CM2_4PIDTimes = JcifsUtil.PIDChangeStatic(Constant.LOCALTEMPPATH + File.separator + "CM2-4visa.log", startDate, endDate);
                            CM2_5PIDTimes = JcifsUtil.PIDChangeStatic(Constant.LOCALTEMPPATH + File.separator + "CM2-5visa.log", startDate, endDate);
                            CM2_6PIDTimes = JcifsUtil.PIDChangeStatic(Constant.LOCALTEMPPATH + File.separator + "CM2-6visa.log", startDate, endDate);
                            //CM3PID变化统计
                            CM3_1PIDTimes = JcifsUtil.PIDChangeStatic(Constant.LOCALTEMPPATH + File.separator + "CM3-1visa.log", startDate, endDate);
                            CM3_2PIDTimes = JcifsUtil.PIDChangeStatic(Constant.LOCALTEMPPATH + File.separator + "CM3-2visa.log", startDate, endDate);
                            CM3_3PIDTimes = JcifsUtil.PIDChangeStatic(Constant.LOCALTEMPPATH + File.separator + "CM3-3visa.log", startDate, endDate);
                            CM3_4PIDTimes = JcifsUtil.PIDChangeStatic(Constant.LOCALTEMPPATH + File.separator + "CM3-4visa.log", startDate, endDate);
                            CM3_5PIDTimes = JcifsUtil.PIDChangeStatic(Constant.LOCALTEMPPATH + File.separator + "CM3-5visa.log", startDate, endDate);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        //CM1PID统计输出
                        CM1PIDChangeAreaOutput.setText("CM1-1 PIDChange时间：" + "\r\n");
                        for (Object str : CM1_1PIDTimes) {
                            CM1PIDChangeAreaOutput.append(str + "\r\n");
                        }
                        CM1PIDChangeAreaOutput.append("\r\n" + "CM1-2 PIDChange时间：" + "\r\n");
                        for (Object str : CM1_2PIDTimes) {
                            CM1PIDChangeAreaOutput.append(str + "\r\n");
                        }
                        CM1PIDChangeAreaOutput.append("\r\n" + "CM1-3 PIDChange时间：" + "\r\n");
                        for (Object str : CM1_3PIDTimes) {
                            CM1PIDChangeAreaOutput.append(str + "\r\n");
                        }
                        CM1PIDChangeAreaOutput.append("\r\n" + "CM1-4 PIDChange时间：" + "\r\n");
                        for (Object str : CM1_4PIDTimes) {
                            CM1PIDChangeAreaOutput.append(str + "\r\n");
                        }
                        CM1PIDChangeAreaOutput.append("\r\n" + "CM1-5 PIDChange时间：" + "\r\n");
                        for (Object str : CM1_5PIDTimes) {
                            CM1PIDChangeAreaOutput.append(str + "\r\n");
                        }
                        CM1PIDChangeAreaOutput.append("\r\n" + "CM1-6 PIDChange时间：" + "\r\n");
                        for (Object str : CM1_6PIDTimes) {
                            CM1PIDChangeAreaOutput.append(str + "\r\n");
                        }
                        //CM2PID统计输出
                        CM2PIDChangeAreaOutput.setText("CM2-1 PIDChange时间：" + "\r\n");
                        for (Object str : CM2_1PIDTimes) {
                            CM2PIDChangeAreaOutput.append(str + "\r\n");
                        }
                        CM2PIDChangeAreaOutput.append("\r\n" + "CM2-2 PIDChange时间：" + "\r\n");
                        for (Object str : CM2_2PIDTimes) {
                            CM2PIDChangeAreaOutput.append(str + "\r\n");
                        }
                        CM2PIDChangeAreaOutput.append("\r\n" + "CM2-3 PIDChange时间：" + "\r\n");
                        for (Object str : CM2_3PIDTimes) {
                            CM2PIDChangeAreaOutput.append(str + "\r\n");
                        }
                        CM2PIDChangeAreaOutput.append("\r\n" + "CM2-4 PIDChange时间：" + "\r\n");
                        for (Object str : CM2_4PIDTimes) {
                            CM2PIDChangeAreaOutput.append(str + "\r\n");
                        }
                        CM2PIDChangeAreaOutput.append("\r\n" + "CM2-5 PIDChange时间：" + "\r\n");
                        for (Object str : CM2_5PIDTimes) {
                            CM2PIDChangeAreaOutput.append(str + "\r\n");
                        }
                        CM2PIDChangeAreaOutput.append("\r\n" + "CM2-6 PIDChange时间：" + "\r\n");
                        for (Object str : CM2_6PIDTimes) {
                            CM2PIDChangeAreaOutput.append(str + "\r\n");
                        }
                        //CM3PID统计输出
                        CM3PIDChangeAreaOutput.setText("CM3-1 PIDChange时间：" + "\r\n");
                        for (Object str : CM3_1PIDTimes) {
                            CM3PIDChangeAreaOutput.append(str + "\r\n");
                        }
                        CM3PIDChangeAreaOutput.append("\r\n" + "CM3-2 PIDChange时间：" + "\r\n");
                        for (Object str : CM3_2PIDTimes) {
                            CM3PIDChangeAreaOutput.append(str + "\r\n");
                        }
                        CM3PIDChangeAreaOutput.append("\r\n" + "CM3-3 PIDChange时间：" + "\r\n");
                        for (Object str : CM3_3PIDTimes) {
                            CM3PIDChangeAreaOutput.append(str + "\r\n");
                        }
                        CM3PIDChangeAreaOutput.append("\r\n" + "CM3-4 PIDChange时间：" + "\r\n");
                        for (Object str : CM3_4PIDTimes) {
                            CM3PIDChangeAreaOutput.append(str + "\r\n");
                        }
                        CM3PIDChangeAreaOutput.append("\r\n" + "CM3-5 PIDChange时间：" + "\r\n");
                        for (Object str : CM3_5PIDTimes) {
                            CM2PIDChangeAreaOutput.append(str + "\r\n");
                        }
                    }

                }
            });
        }
        return getPIDChangeButton;
    }

    //数据获取
    private JButton dataUpdateButton() {
        if (dataUpdateButton == null) {
            dataUpdateButton = new JButton("数据读取");
            dataUpdateButton.setBounds(20, 20, 100, 30);
            dataUpdateButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    Boolean sign1_1 = false;
                    Boolean sign1_2 = false;
                    Boolean sign1_3 = false;
                    Boolean sign1_4 = false;
                    Boolean sign1_5 = false;
                    Boolean sign1_6 = false;

                    Boolean sign2_1 = false;
                    Boolean sign2_2 = false;
                    Boolean sign2_3 = false;
                    Boolean sign2_4 = false;
                    Boolean sign2_5 = false;
                    Boolean sign2_6 = false;

                    Boolean sign3_1 = false;
                    Boolean sign3_2 = false;
                    Boolean sign3_3 = false;
                    Boolean sign3_4 = false;
                    Boolean sign3_5 = false;


                    ExecutorService exec = Executors.newCachedThreadPool();
                    Callable callable1_1 = new MutiDataUpdate(Constant.CM1_1REMOTEPATH, Constant.LOCALTEMPPATH);
                    Future future1_1 = exec.submit(callable1_1);

                    Callable callable1_2 = new MutiDataUpdate(Constant.CM1_2REMOTEPATH, Constant.LOCALTEMPPATH);
                    Future future1_2 = exec.submit(callable1_2);

                    Callable callable1_3 = new MutiDataUpdate(Constant.CM1_3REMOTEPATH, Constant.LOCALTEMPPATH);
                    Future future1_3 = exec.submit(callable1_3);

                    Callable callable1_4 = new MutiDataUpdate(Constant.CM1_4REMOTEPATH, Constant.LOCALTEMPPATH);
                    Future future1_4 = exec.submit(callable1_4);

                    Callable callable1_5 = new MutiDataUpdate(Constant.CM1_5REMOTEPATH, Constant.LOCALTEMPPATH);
                    Future future1_5 = exec.submit(callable1_5);

                    Callable callable1_6 = new MutiDataUpdate(Constant.CM1_6REMOTEPATH, Constant.LOCALTEMPPATH);
                    Future future1_6 = exec.submit(callable1_6);


                    Callable callable2_1 = new MutiDataUpdate(Constant.CM2_1REMOTEPATH, Constant.LOCALTEMPPATH);
                    Future future2_1 = exec.submit(callable2_1);

                    Callable callable2_2 = new MutiDataUpdate(Constant.CM2_2REMOTEPATH, Constant.LOCALTEMPPATH);
                    Future future2_2 = exec.submit(callable2_2);

                    Callable callable2_3 = new MutiDataUpdate(Constant.CM2_3REMOTEPATH, Constant.LOCALTEMPPATH);
                    Future future2_3 = exec.submit(callable2_3);

                    Callable callable2_4 = new MutiDataUpdate(Constant.CM2_4REMOTEPATH, Constant.LOCALTEMPPATH);
                    Future future2_4 = exec.submit(callable2_4);

                    Callable callable2_5 = new MutiDataUpdate(Constant.CM2_5REMOTEPATH, Constant.LOCALTEMPPATH);
                    Future future2_5 = exec.submit(callable2_5);

                    Callable callable2_6 = new MutiDataUpdate(Constant.CM2_6REMOTEPATH, Constant.LOCALTEMPPATH);
                    Future future2_6 = exec.submit(callable2_6);


                    Callable callable3_1 = new MutiDataUpdate(Constant.CM3_1REMOTEPATH, Constant.LOCALTEMPPATH);
                    Future future3_1 = exec.submit(callable3_1);

                    Callable callable3_2 = new MutiDataUpdate(Constant.CM3_2REMOTEPATH, Constant.LOCALTEMPPATH);
                    Future future3_2 = exec.submit(callable3_2);

                    Callable callable3_3 = new MutiDataUpdate(Constant.CM3_3REMOTEPATH, Constant.LOCALTEMPPATH);
                    Future future3_3 = exec.submit(callable3_3);

                    Callable callable3_4 = new MutiDataUpdate(Constant.CM3_4REMOTEPATH, Constant.LOCALTEMPPATH);
                    Future future3_4 = exec.submit(callable3_4);

                    Callable callable3_5 = new MutiDataUpdate(Constant.CM3_5REMOTEPATH, Constant.LOCALTEMPPATH);
                    Future future3_5 = exec.submit(callable3_5);


                    try {
                        sign1_1 = (Boolean) future1_1.get();
                        sign1_2 = (Boolean) future1_2.get();
                        sign1_3 = (Boolean) future1_3.get();
                        sign1_4 = (Boolean) future1_4.get();
                        sign1_5 = (Boolean) future1_5.get();
                        sign1_6 = (Boolean) future1_6.get();

                        sign2_1 = (Boolean) future2_1.get();
                        sign2_2 = (Boolean) future2_2.get();
                        sign2_3 = (Boolean) future2_3.get();
                        sign2_4 = (Boolean) future2_4.get();
                        sign2_5 = (Boolean) future2_5.get();
                        sign2_6 = (Boolean) future2_6.get();

                        sign3_1 = (Boolean) future3_1.get();
                        sign3_2 = (Boolean) future3_2.get();
                        sign3_3 = (Boolean) future3_3.get();
                        sign3_4 = (Boolean) future3_4.get();
                        sign3_5 = (Boolean) future3_5.get();

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                    if (sign1_1 == true && sign1_2 == true && sign1_3 == true && sign1_4 == true && sign1_5 == true && sign1_6 == true && sign2_1 == true && sign2_2 == true && sign2_3 == true && sign2_4 == true && sign2_5 == true && sign2_6 == true && sign3_1 == true && sign3_2 == true && sign3_3 == true && sign3_4 == true && sign3_5 == true) {
                        MessageAreaOutput.setText("数据读取完成");
                    }
                }
            });
        }
        return dataUpdateButton;
    }
}
