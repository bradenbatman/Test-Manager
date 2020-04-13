
import javax.swing.Box;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUI {

    private Font titleFont = new Font("Helvetica", Font.BOLD, 36);
    private JPanel panel = new JPanel();
    private JFrame frame = new JFrame("Test Manager");
    private final String[] answerChoices = {"a", "b", "c", "d"};

    public GUI() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        enterMainMenu();
        frame.add(panel, BorderLayout.CENTER);
        frame.setSize(400, 400);
        frame.setVisible(true);
    }

    private void enterMainMenu() {
        panel.removeAll();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        addTitle("Test Manager");

        addButton("Add Question").addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addQuestionClick();
            }
        });
        addButton("Edit Question").addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editQuestionClick();
            }
        });
        addButton("Create Test").addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createTestClick();
            }
        });
        addButton("Take Test").addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                takeTestClick();
            }
        });
        addButton("View Scores").addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewScoresClick();
            }
        });
        panel.revalidate();
        frame.revalidate();
        panel.repaint();
        frame.repaint();
    }

    private JButton addButton (String text) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(button);
        panel.add(Box.createRigidArea(new Dimension(0,10)));
        return button;
    }

    private void addTitle (String text) {
        JLabel title = new JLabel(text);
        title.setFont(titleFont);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(title);
        panel.add(Box.createRigidArea(new Dimension(0,10)));
    }

    private void addQuestionClick() {
        panel.removeAll();
        JLabel questionLabel = new JLabel("Enter question below");
        panel.add(questionLabel);
        JTextField questionField = new JTextField();
        panel.add(questionField);
        JLabel answerLabel = new JLabel("Enter answer below");
        panel.add(answerLabel);
        JTextField a = new JTextField();
        panel.add(a);
        JTextField b = new JTextField();
        panel.add(b);
        JTextField c = new JTextField();
        panel.add(c);
        JTextField d = new JTextField();
        panel.add(d);
        JComboBox answerDropDown = new JComboBox(answerChoices);
        panel.add(answerDropDown);
        addButton("Enter").addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                enterMainMenu();
            }
        });
        panel.revalidate();
        frame.revalidate();
        panel.repaint();
        frame.repaint();
    }

    private void editQuestionClick() {
        panel.removeAll();
        panel.repaint();
    }

    private void createTestClick() {
        panel.removeAll();
        panel.repaint();
    }
    private void takeTestClick() {
        panel.removeAll();
        panel.repaint();
    }

    private void viewScoresClick() {
        panel.removeAll();
        panel.repaint();
    }


}