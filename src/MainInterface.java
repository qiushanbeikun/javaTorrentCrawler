import Model.AnimeCrawler;
import Model.PornCrawler;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainInterface extends JFrame implements ActionListener {

    public JButton go;
    public JComboBox<String> selectType;
    public JLabel errorPane;
    public JTextField searchContent;

    public MainInterface(){
        super("Main Interface");
    }

    public void showFrame(){

        JPanel contentPane = new JPanel();
        this.setContentPane(contentPane);

        JLabel selection = new JLabel("Select the type of genre you want to search");
        contentPane.add(selection);

        selectType = new JComboBox<>();
        selectType.addItem(null);
        selectType.addItem("Games");
        selectType.addItem("Anime");
        selectType.addItem("Vehicle License");
        selectType.addItem("Other resources that I can offer");
        contentPane.add(selectType);

        JLabel content = new JLabel("To search:");
        contentPane.add(content);

        searchContent = new JTextField();
        searchContent.setColumns(20);
        contentPane.add(searchContent);

        go = new JButton("Search");
        contentPane.add(go);
        go.addActionListener(this);

        errorPane = new JLabel("No errors so far");
        contentPane.add(errorPane);

        SpringLayout layout = new SpringLayout();
        contentPane.setLayout(layout);

        layout.putConstraint(SpringLayout.WEST, selection, 5, SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.NORTH, selection, 5, SpringLayout.NORTH, contentPane);

        layout.putConstraint(SpringLayout.WEST, selectType, 40, SpringLayout.EAST, selection);
        layout.putConstraint(SpringLayout.NORTH, selectType, 5, SpringLayout.NORTH, contentPane);

        layout.putConstraint(SpringLayout.WEST, content, 5, SpringLayout.WEST, selection);
        layout.putConstraint(SpringLayout.NORTH, content, 35, SpringLayout.NORTH, contentPane);

        layout.putConstraint(SpringLayout.WEST, searchContent, 5, SpringLayout.EAST, content);
        layout.putConstraint(SpringLayout.NORTH, searchContent, 35, SpringLayout.NORTH, contentPane);

        layout.putConstraint(SpringLayout.WEST, go, 130, SpringLayout.WEST, selection);
        layout.putConstraint(SpringLayout.NORTH, go, 65, SpringLayout.NORTH, contentPane);

        layout.putConstraint(SpringLayout.WEST, errorPane, 230, SpringLayout.WEST, selection);
        layout.putConstraint(SpringLayout.NORTH, errorPane, 65, SpringLayout.NORTH, contentPane);


        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        this.setSize(550,200);
        Dimension d = this.getToolkit().getScreenSize();
        Rectangle r = this.getBounds();
        this.setLocation( (d.width - r.width)/2, (d.height - r.height)/2 );

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String type = (String) selectType.getSelectedItem();
        String content = searchContent.getText();

        try {
            if (e.getSource() == go) {

                if (type == null || content == null) {
                    errorPane.setText("please check type and content");
                } else {
                    switch (type) {
                        case "Anime":
                            // todo
                            errorPane.setText("No errors so far");
                            AnimeCrawler animeCrawler = new AnimeCrawler(content);
                            animeCrawler.startSearch();
                            break;
                        case "Games":
                            // todo
                            errorPane.setText("No errors so far");
                            break;
                        case "Vehicle License":
                            // todo
                            PornCrawler pornCrawler = new PornCrawler(content);
                            pornCrawler.startSearch();
                            errorPane.setText("No errors so far");
                            break;
                        case "Other resources that I can offer":
                            // todo
                            errorPane.setText("No errors so far");
                            break;
                        default:
                            errorPane.setText("the error should not happen at switch");
                    }
                }

            } else {
                errorPane.setText("this error should not happen");
            }
        }catch (Error error) {
            errorPane.setText("there is error caught here" + error);
        }

    }
}
