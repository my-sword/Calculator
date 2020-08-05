package calculator;
//菜单“关于”
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
//背景渐变
class ShadePanel extends JPanel {
    
    private static final long serialVersionUID = -2644424271663261406L;
    //serialVersionUID 用来表明类的不同版本间的兼容性。
	//序列化的时候，被序列化的类要有一个唯一标记。客户端和服务端必须需要同一个对象，serialVersionUID的唯一值判定其为同一个对象。
    
    public ShadePanel() {
        super();
        setLayout(null);
    }
    
    @Override
    protected void paintComponent(Graphics g1) {// 重写绘制组件外观
        Graphics2D g = (Graphics2D) g1;
        super.paintComponent(g);// 执行超类方法
        int width = getWidth();// 获取组件大小
        int height = getHeight();
        // 创建填充模式对象
        GradientPaint paint = new GradientPaint(0, 0, Color.CYAN, 0, height,Color.yellow);//实现颜色渐变
        //GradientPaint paint = new GradientPaint(0, 0, Color.red, 0, height,Color.blue);
        g.setPaint(paint);// 设置填充的绘图对象
        g.fillRect(0, 0, width, height);// 绘制矩形填充控件界面
    }
}
 
public class About extends JDialog {
    private static final long serialVersionUID = 4693799019369193520L;
    private JPanel contentPane;
    private Font f1 = new Font("微软雅黑",Font.PLAIN,15);
	private Font f2 = new Font("微软雅黑",Font.PLAIN,20);
	private ImageIcon icon;
	private JLabel label;
    public About() {
        setTitle("关于");//设置窗体标题
		ImageIcon imageIcon = new ImageIcon(getClass().getResource("./res/mi64.png"));//窗口图标
		setIconImage(imageIcon.getImage());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setModal(true);//设置为模态窗口（是否是模式对话框）
        setSize(430,400);
        setResizable(false);
        setLocationRelativeTo(null);	//居中
        contentPane = new JPanel();		// 创建内容面板
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        ShadePanel shadePanel = new ShadePanel();			// 创建渐变背景面板
        contentPane.add(shadePanel, BorderLayout.CENTER);	// 添加面板到窗体内容面板
        shadePanel.setLayout(null);
        
        JTextArea J1 = new JTextArea("开发者：my_sword\n开发语言：Java\n"
				+ "Email:760410154@qq.com\n版本：2019-12-25_1.0.0");
        J1.setFocusable(false);
    	J1.setFont(f2);
    	J1.setEditable(false);
    	J1.setOpaque(false);//背景透明
    	shadePanel.add(J1);
    	J1.setBounds(10, 10, 400, 180);
		//图片标签
    	icon = new ImageIcon("mi6.png");
		ImageIcon imageIcon2 = new ImageIcon(getClass().getResource("./res/mi6.png"));//窗口图标
    	icon.setImage(imageIcon2.getImage().getScaledInstance(80,80,Image.SCALE_SMOOTH));//保持图片的清晰
    	label = new JLabel(icon);
    	shadePanel.add(label);
    	label.setBounds(270, 20, 80, 80);
    	
    	JPanel p = new JPanel();
    	p.setBounds(5, 130, 395, 1);
	    p.setBorder(BorderFactory.createLineBorder(Color.black));
	    shadePanel.add(p);
	    
	    JLabel J2 = new JLabel("欢迎访问我的主页:");
	    J2.setBounds(10, 145, 200, 20);
	    J2.setFont(f2);
	    shadePanel.add(J2);

	    //网址标签
    	JLabel MyGithub_Label = new JLabel("Github:");
    	MyGithub_Label.setFont(f2);
    	final JLabel MyGithub = new JLabel("https://github.com/my-sword");
    	MyGithub.setFont(f2);
    	MyGithub.setBackground(Color.white);
    	MyGithub.addMouseListener(new InternetMonitor());//添加鼠标事件（继承重写鼠标事件）

    	JLabel MyCnBlog_Label = new JLabel("CSDN:");
    	MyCnBlog_Label.setFont(f2);
    	final JLabel MyCnBlog = new JLabel("https://blog.csdn.net/weixin_47649446");
    	MyCnBlog.setFont(f2);
    	MyCnBlog.addMouseListener(new InternetMonitor());
    	JTextArea Copyright = new JTextArea("     	Copyright @XZB2019.\n   	  All rights reserved.");
    	Copyright.setFocusable(false);
    	Copyright.setOpaque(false);
    	Copyright.setFont(f1);
    	Copyright.setEditable(false);
    	
    	shadePanel.add(MyGithub_Label);
    	MyGithub_Label.setBounds(10, 180, 400, 20);
    	shadePanel.add(MyGithub);
    	MyGithub.setBounds(10, 200, 400, 30);
    	shadePanel.add(MyCnBlog_Label);
    	MyCnBlog_Label.setBounds(10, 240, 400, 20);
    	shadePanel.add(MyCnBlog);
    	MyCnBlog.setBounds(10, 260, 400, 30);
    	shadePanel.add(Copyright);
    	Copyright.setBounds(10, 300, 400, 50);
       
    	setVisible(true);
    }
    
    public static void main(String[] args) {
		new About();
	}
}

class InternetMonitor extends MouseAdapter{
	public void mouseClicked(MouseEvent e){
		JLabel JLabel_temp = (JLabel)e.getSource();
		String J_temp = JLabel_temp.getText();
		System.out.println(J_temp);
		URI uri ;
			try {
				uri = new URI(J_temp);
				Desktop desk=Desktop.getDesktop();
				if(Desktop.isDesktopSupported() && desk.isSupported(Desktop.Action.BROWSE)){
					try {
						desk.browse(uri);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			} catch (URISyntaxException e1) {
				e1.printStackTrace();
			}
	}
	public void mouseEntered(MouseEvent e){
		JLabel JLabel_temp = (JLabel)e.getSource();
		JLabel_temp.setForeground(Color.red);
	}
	public void mouseExited(MouseEvent e){
		JLabel JLabel_temp = (JLabel)e.getSource();
		JLabel_temp.setForeground(Color.blue);
	}
}
