
import javax.swing.Box;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class GUI {

    private Font titleFont = new Font("Helvetica", Font.BOLD, 36);
    private Font headFont = new Font("Helvetica", Font.PLAIN, 20);
    private JPanel panel = new JPanel();
    private JFrame frame = new JFrame("Test Manager");
    
    private PostgreManager manage = new PostgreManager();

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

        addButton("Manage Tests").addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	enterManageTestMenu();
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
    
    private void enterManageTestMenu() {
        panel.removeAll();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        addHeader("Manage Tests");

        addButton("Add Question").addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                enterAddQuestionScreen();
            }
        });
        addButton("Edit Questions").addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	enterEditQuestionScreen();
            }
        });
        addButton("Create Test").addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createTestClick();
            }
        });
        addButton("< Back").addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	enterMainMenu();
            }
        });
        panel.revalidate();
        frame.revalidate();
        panel.repaint();
        frame.repaint();
    }
    
    private void enterAddQuestionScreen() {
        panel.removeAll();
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        
        addHeader("Add Quesion");

        
        addLabel("Enter a question below:");
        
        JTextField ques = new JTextField();
        panel.add(ques);
                
        addLabel("Enter the correct answer:");
        JTextField corr = new JTextField();
        panel.add(corr);
        
        addLabel("Enter an incorrect answer:");
        JTextField inc1 = new JTextField();
        panel.add(inc1);
        
        addLabel("Enter another incorrect answer:");
        JTextField inc2 = new JTextField();
        panel.add(inc2);
        
        addLabel("Enter one more incorrect answer:");
        JTextField inc3 = new JTextField();
        panel.add(inc3);   
        
        JLabel errorLabel = new JLabel("");
        errorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(errorLabel);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0,2));
        
        JButton cancelButton = createButton("Cancel");
        JButton enterButton = createButton("Enter");  
        
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	enterManageTestMenu();
            }
        });
        
        enterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if(ques.getText().isBlank() || corr.getText().isBlank() || inc1.getText().isBlank() || inc2.getText().isBlank() || inc3.getText().isBlank()) {
            		errorLabel.setText("* A field was left blank *");
            	}
            	else
            	{
            		String query = "insert into question(question,answer,incorrect1,incorrect2,incorrect3) values('" + ques.getText() + "','" + corr.getText() + "','" + inc1.getText() + "','" + inc2.getText() + "','" + inc3.getText() + "')";
            		manage.runUpdateQuery(query);
            	}
            
            }});
        

        buttonPanel.add(cancelButton);
        buttonPanel.add(enterButton);
        
        panel.add(buttonPanel);
        
        panel.revalidate();
        frame.revalidate();
        panel.repaint();
        frame.repaint();
    }
    
    private void enterEditQuestionScreen() {
        panel.removeAll();
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        addHeader("Edit Question");

        ArrayList<ArrayList<Object>> questions = manage.runQuery("select * from question");
        
        
        addLabel("Select a question:");
        
        JComboBox<String> questionBox = new JComboBox<String>();
        
        for(int row = 0;row<questions.size();row++) {
        	questionBox.addItem((String) questions.get(row).get(0).toString() + ": " +(String) questions.get(row).get(1)+ " - " +(String) questions.get(row).get(2));
        }
        panel.add(questionBox);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0,2));
        
        JButton cancelButton = createButton("Cancel");
        JButton editButton = createButton("Edit");  
        
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	enterManageTestMenu();
            }
        });
        
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	enterQuestionEditor(questionBox.getSelectedItem().toString().charAt(0));
            	//System.out.println(questionBox.getSelectedItem().toString().charAt(0));
            
            }});
        

        buttonPanel.add(cancelButton);
        buttonPanel.add(editButton);
        
        panel.add(buttonPanel);
        
        panel.revalidate();
        frame.revalidate();
        panel.repaint();
        frame.repaint();
    }
    
    private void enterQuestionEditor(int id) {
        panel.removeAll();
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        
        addHeader("Edit Quesion");

        
        addLabel("Question:");
        
        JTextField ques = new JTextField();
        panel.add(ques);
                
        addLabel("Correct answer:");
        JTextField corr = new JTextField();
        panel.add(corr);
        
        addLabel("An incorrect answer:");
        JTextField inc1 = new JTextField();
        panel.add(inc1);
        
        addLabel("Another incorrect answer:");
        JTextField inc2 = new JTextField();
        panel.add(inc2);
        
        addLabel("One more incorrect answer:");
        JTextField inc3 = new JTextField();
        panel.add(inc3);   
        
        JLabel errorLabel = new JLabel("");
        errorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(errorLabel);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0,2));
        
        JButton cancelButton = createButton("Cancel");
        JButton enterButton = createButton("Enter");  
        
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	enterManageTestMenu();
            }
        });
        
        enterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if(ques.getText().isBlank() || corr.getText().isBlank() || inc1.getText().isBlank() || inc2.getText().isBlank() || inc3.getText().isBlank()) {
            		errorLabel.setText("* A field was left blank *");
            	}
            	else
            	{
            		//todo
            	}
            
            }});
        

        buttonPanel.add(cancelButton);
        buttonPanel.add(enterButton);
        
        panel.add(buttonPanel);
        
        panel.revalidate();
        frame.revalidate();
        panel.repaint();
        frame.repaint();
    }

    private JButton addButton (String text) {
    	JButton button = createButton(text);
    	panel.add(button);
        panel.add(Box.createRigidArea(new Dimension(0,10)));
        return button;
    }
    
    private JButton createButton (String text) {
    	JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        return button;
    }

    private void addTitle (String text) {
        JLabel title = new JLabel(text);
        title.setFont(titleFont);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(title);
        panel.add(Box.createRigidArea(new Dimension(0,10)));
    }
    
    private void addHeader (String text) {
        JLabel head = new JLabel(text);
        head.setFont(headFont);
        head.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(head);
        panel.add(Box.createRigidArea(new Dimension(0,10)));
    }
    
    private void addLabel (String text) {
    	JLabel label = new JLabel(text);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(label);
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