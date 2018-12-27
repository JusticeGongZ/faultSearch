package util;

import com.eltima.components.ui.DatePicker;

import java.awt.*;
import java.util.Date;
import java.util.Locale;

/**
 * @ClassName Datepicker
 * @Description 日期控件
 * @Author gz
 * @Date 2018/12/26 15:57
 * @Version 1.0
 **/
public class Datepicker {
    public static void main(String[] args) {

//        final JFrame f = new JFrame("date");
//        f.setSize(400, 300);
//        f.setLocation(200, 200);
//        f.setLayout(null);
//
//        final DatePicker datepick;
////        datepick = Datepicker.getDatePicker();
////        f.add(datepick);
//
//        JButton b = new JButton("获取时间");
//        b.setBounds(137, 183, 100, 30);
//        f.add(b);
//
//
//        b.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
////                JOptionPane.showMessageDialog(f, "获取控件中的日期：" + datepick.getValue());
//                System.out.println(datepick.getValue());//这是一个java.util.Date对象
//            }
//        });
//
//        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        f.setVisible(true);
    }

    public static DatePicker getStartDatePicker() {
        final DatePicker datepick;
        // 格式
        String DefaultFormat = "yyyy-MM-dd HH:mm:ss";
        // 当前时间
        Date date = new Date();
        // 字体
        Font font = new Font("Times New Roman", Font.BOLD, 12);

        Dimension dimension = new Dimension(177, 24);

//        int[] hilightDays = { 1, 3, 5, 7 };

//        int[] disabledDays = { 4, 6, 5, 9 };
        //构造方法（初始时间，时间显示格式，字体，控件大小）
        datepick = new DatePicker(date, DefaultFormat, font, dimension);

        datepick.setLocation(137, 83);
        //设置起始位置
        //也可用setBounds()直接设置大小与位置
//        datepick.setBounds(137, 83, 177, 24);
        // 设置一个月份中需要高亮显示的日子
//        datepick.setHightlightdays(hilightDays, Color.red);
        // 设置一个月份中不需要的日子，呈灰色显示
//        datepick.setDisableddays(disabledDays);
        // 设置国家
        datepick.setLocale(Locale.CHINA);
        // 设置时钟面板可见
        datepick.setTimePanleVisible(true);
        return datepick;
    }

    public static DatePicker getEndDatePicker() {
        final DatePicker datepick;
        // 格式
        String DefaultFormat = "yyyy-MM-dd HH:mm:ss";
        // 当前时间
        Date date = new Date();
        // 字体
        Font font = new Font("Times New Roman", Font.BOLD, 12);

        Dimension dimension = new Dimension(177, 24);

//        int[] hilightDays = { 1, 3, 5, 7 };

//        int[] disabledDays = { 4, 6, 5, 9 };
        //构造方法（初始时间，时间显示格式，字体，控件大小）
        datepick = new DatePicker(date, DefaultFormat, font, dimension);

        datepick.setLocation(137, 160);
        //设置起始位置
        //也可用setBounds()直接设置大小与位置
//        datepick.setBounds(137, 83, 177, 24);
        // 设置一个月份中需要高亮显示的日子
//        datepick.setHightlightdays(hilightDays, Color.red);
        // 设置一个月份中不需要的日子，呈灰色显示
//        datepick.setDisableddays(disabledDays);
        // 设置国家
        datepick.setLocale(Locale.CHINA);
        // 设置时钟面板可见
        datepick.setTimePanleVisible(true);
        return datepick;
    }
}