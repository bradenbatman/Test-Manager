
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableColumn;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


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
            		enterManageTestMenu();
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
        
        JLabel errorLabel = new JLabel("");
        errorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(errorLabel);
        
        JButton cancelButton = createButton("Cancel");
        JButton editButton = createButton("Edit");  
        
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	enterManageTestMenu();
            }
        });
        
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if(questionBox.getSelectedIndex() == -1) {
            		errorLabel.setText("* No question is selected. *");
            	}
            	else
            	{
            	enterQuestionEditor(questions.get(questionBox.getSelectedIndex()));
            	//System.out.println(questionBox.getSelectedItem().toString().charAt(0));
            	}
            }});
        

        buttonPanel.add(cancelButton);
        buttonPanel.add(editButton);
        
        panel.add(buttonPanel);
        
        panel.revalidate();
        frame.revalidate();
        panel.repaint();
        frame.repaint();
    }
    
    private void enterQuestionEditor(ArrayList<Object> row) {
        panel.removeAll();
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        
        addHeader("Edit Quesion");
        addLabel("Question:");
        JTextField ques = new JTextField(row.get(1).toString());
        panel.add(ques);
                
        addLabel("Correct answer:");
        JTextField corr = new JTextField(row.get(2).toString());
        panel.add(corr);
        
        addLabel("An incorrect answer:");
        JTextField inc1 = new JTextField(row.get(3).toString());
        panel.add(inc1);
        
        addLabel("Another incorrect answer:");
        JTextField inc2 = new JTextField(row.get(4).toString());
        panel.add(inc2);
        
        addLabel("One more incorrect answer:");
        JTextField inc3 = new JTextField(row.get(5).toString());
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
            		String query = "update question set question='" + ques.getText() + "',answer='" + corr.getText() + "',incorrect1='" + inc1.getText() + "',incorrect2='" + inc2.getText() + "',incorrect3='" + inc3.getText() + "' where qid="+row.get(0);
            		manage.runUpdateQuery(query);
            		enterManageTestMenu();
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
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        addHeader("Take Test");
        
        ArrayList<ArrayList<Object>> students = manage.runQuery("select * from student"); 
        
        addLabel("Select an existing student or put in your name:");
        JComboBox<String> studentBox = new JComboBox<String>();
        studentBox.setEditable(true);
        
        for(int row = 0;row<students.size();row++) {
        	studentBox.addItem(students.get(row).get(0).toString());
        }
        panel.add(studentBox);
        
        
        ArrayList<ArrayList<Object>> tests = manage.runQuery("select * from test");        
        
        addLabel("Select a test:");
        
        JComboBox<String> testBox = new JComboBox<String>();
        
        for(int row = 0;row<tests.size();row++) {
        	testBox.addItem("Test: "+ (String) tests.get(row).get(0).toString());
        }
        panel.add(testBox);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0,2));
        
        JLabel errorLabel = new JLabel("");
        errorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(errorLabel);
        
        JButton cancelButton = createButton("Cancel");
        JButton takeButton = createButton("Take");  
        
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	enterMainMenu();
            }
        });
        
        takeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if(testBox.getSelectedIndex() == -1) {
            		errorLabel.setText("* No test is selected. *");
            	}
            	else if(((String) studentBox.getSelectedItem()).isBlank()) {
            		errorLabel.setText("* A name must be input. *");

            	}
            	else
            	{
            		boolean newName = true;
            		for(int row = 0;row<students.size();row++) {
            			if(students.get(row).get(0) == studentBox.getSelectedItem()) {
            				newName = false;
            			}
            		}
            		
            		//Create new student if they input a new name
            		if(newName) {
            			String query = "insert into student(name) values('"+ studentBox.getSelectedItem() + "')";
                		manage.runUpdateQuery(query);
            		}
            		
                    ArrayList<ArrayList<Object>> studentids = manage.runQuery("select studentid from student where name='" + studentBox.getSelectedItem() + "'");
                    int studentid = (int) studentids.get(0).get(0);
                    
                    
                    takeTest(studentid, (int) (tests.get(testBox.getSelectedIndex()).get(0)));
            	}
            }});
        

        buttonPanel.add(cancelButton);
        buttonPanel.add(takeButton);
        
        panel.add(buttonPanel);
        
        panel.revalidate();
        frame.revalidate();
        panel.repaint();
        frame.repaint();
    }
    
    private void takeTest(int studentid, int testid) {
    	panel.removeAll();
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        addHeader("Take Test");
        
        ArrayList<ArrayList<Object>> questionids = manage.runQuery("select question1, question2, question3, question4 from test where testid='" + testid + "'");
        
        List<String> correctAnswers = new ArrayList();
        List<JComboBox<String>> answerBoxes = new ArrayList();
        List<String> questionList = new ArrayList();

        
        for(int i=0;i<questionids.get(0).size();i++) {
        	ArrayList<ArrayList<Object>> questions = manage.runQuery("select * from question where qid='" + questionids.get(0).get(i) + "'");
        	questionList.add(questions.get(0).get(1).toString());            
            
            List<String> answers = new ArrayList();
            
            correctAnswers.add(questions.get(0).get(2).toString());
            answers.add(questions.get(0).get(2).toString());
            answers.add(questions.get(0).get(3).toString());
            answers.add(questions.get(0).get(4).toString());
            answers.add(questions.get(0).get(5).toString());
            
            Collections.shuffle(answers);
            JComboBox<String> answerOptions = new JComboBox<String>();
            
            for(int x=0;x<answers.size();x++) {
            	answerOptions.addItem(answers.get(x));
            }
            
            answerBoxes.add(answerOptions);
            
        }
        
        for(int i=0;i<questionList.size();i++) {
        	addLabel("Question:");
        	addLabel(questionList.get(i));
            addLabel("Select answer:");
            panel.add(answerBoxes.get(i));
        }
        
                
        
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0,2));
        
        JLabel errorLabel = new JLabel("");
        errorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(errorLabel);
        
        JButton cancelButton = createButton("Cancel");
        JButton submitButton = createButton("Submit");  
        
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	enterMainMenu();
            }
        });
        
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	int numCorrect = 0;
            	for(int x=0;x<correctAnswers.size();x++) {
            		if(answerBoxes.get(x).getSelectedItem().equals(correctAnswers.get(x))) {
            			numCorrect++;
            		}
            	}
            	System.out.println(numCorrect);
            	String query = "insert into testlog(numcorrect,studentid,testid) values('" + numCorrect + "','" + studentid + "','" + testid + "')";
        		manage.runUpdateQuery(query);
        		viewScoresClick();
            }});

        buttonPanel.add(cancelButton);
        buttonPanel.add(submitButton);
        
        panel.add(buttonPanel);
        
        panel.revalidate();
        frame.revalidate();
        panel.repaint();
        frame.repaint();
    }
    
    

    private void viewScoresClick() {
        panel.removeAll();
        panel.repaint();
        
        addHeader("Scores");
        
        String query = "select * from testlog";
        ArrayList<ArrayList<Object>> scores = manage.runQuery(query);
        
        String[] names = new String[]{"logid", "numcorrect", "studentid", "testid"};
        Object[][] cells = new Object[scores.size()][4];
        
        for(int row=0;row<scores.size();row++) {
        	for(int col=0;col<scores.get(row).size();col++) {
        		cells[row][col] = scores.get(row).get(col);
        	}
        }
        
        
        
        JTable table = new JTable(cells, names);
        panel.add(new JScrollPane(table));
        
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


}