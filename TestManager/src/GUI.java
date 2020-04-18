
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class GUI {

    private Font titleFont = new Font("Helvetica", Font.BOLD, 36);
    private Font headFont = new Font("Helvetica", Font.PLAIN, 20);
    private JPanel panel = new JPanel();
    private JFrame frame = new JFrame("Test Manager");
    private Color backgroundColor = new Color(217, 217, 217);

    private PostgreManager manage = new PostgreManager();

    public GUI() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        enterMainMenu();
        frame.add(panel, BorderLayout.CENTER);
        frame.setSize(400, 400);
        frame.setVisible(true);
        panel.setBackground(backgroundColor);
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
    
    private void addCancelButtonPanel (JButton cancelButton, JButton optionButton) {
    	JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0,2));
    	buttonPanel.setBackground(backgroundColor);
        buttonPanel.add(cancelButton);
        buttonPanel.add(optionButton);
        panel.add(buttonPanel);
    }
    
    private void addButtonMenu(JButton[] buttons) {
    	JPanel buttonPanel = new JPanel();
    	buttonPanel.setLayout(new GridLayout(buttons.length,1));
    	buttonPanel.setBackground(backgroundColor);
        for (JButton button: buttons) {
        	buttonPanel.add(button);
        }
        panel.add(buttonPanel);
    }

    private void enterMainMenu() {
        panel.removeAll();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));

        addTitle("Test Manager");
        addLabel("By Braden Batman, Matthew Heck, and Micah Withers");
        
        JButton manageButton = createButton("Manage Tests");
        JButton takeButton = createButton("Take a Test");
        JButton viewButton = createButton("View Scores");
        JButton exitButton = createButton("Exit");

        
        addButtonMenu(new JButton[] {manageButton, takeButton, viewButton, exitButton});
        
        manageButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	enterManageTestMenu();
            }
        });
        takeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                enterTakeTestScreen();
            }
        });
        viewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                enterViewScoresScreen();
            }
        });
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	System.exit(0);
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
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        addHeader("Manage Tests");
        
        JButton addButton = createButton("Add Question");
        JButton editButton = createButton("Edit Questions");
        JButton createButton = createButton("Create Test");
        JButton backButton = createButton("< Back");
        
        addButtonMenu(new JButton[] {addButton, editButton, createButton, backButton});
        
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                enterAddQuestionScreen();
            }
        });
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	enterEditQuestionScreen();
            }
        });
        createButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                enterCreateTestScreen();
            }
        });
        backButton.addActionListener(new ActionListener() {
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
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        addHeader("Add Question");

        addLabel("Enter a question:");
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


        JButton cancelButton = createButton("Cancel");
        JButton enterButton = createButton("Enter");
        addCancelButtonPanel(cancelButton, enterButton);

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	enterManageTestMenu();
            }
        });

        enterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if(ques.getText().isEmpty() || corr.getText().isEmpty() || inc1.getText().isEmpty() || inc2.getText().isEmpty() || inc3.getText().isEmpty()) {
            		errorLabel.setText("* A field was left blank *");
            	}
            	else if(ques.getText().contains("'")|| ques.getText().contains("\"") || corr.getText().contains("'")|| corr.getText().contains("\"") || inc1.getText().contains("'")|| inc1.getText().contains("\"") || inc2.getText().contains("'")|| inc2.getText().contains("\"") || inc3.getText().contains("'")|| inc3.getText().contains("\"")) {
            		errorLabel.setText("* Illegal character used *");
            	}
            	else
            	{
            		String query = "insert into question(question,answer,incorrect1,incorrect2,incorrect3) values('" + ques.getText() + "','" + corr.getText() + "','" + inc1.getText() + "','" + inc2.getText() + "','" + inc3.getText() + "')";
            		manage.runUpdateQuery(query);
            		enterManageTestMenu();
            	}

            }});

        panel.revalidate();
        frame.revalidate();
        panel.repaint();
        frame.repaint();
    }

    private void enterEditQuestionScreen() {
    	panel.removeAll();
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        addHeader("Edit Question");

        ArrayList<ArrayList<Object>> questions = manage.runQuery("select * from question");


        addLabel("Select a question:");

        JComboBox<String> questionBox = new JComboBox<String>();

        for(int row = 0;row<questions.size();row++) {
        	questionBox.addItem((String) questions.get(row).get(0).toString() + ": " +(String) questions.get(row).get(1)+ " - " +(String) questions.get(row).get(2));
        }
        panel.add(questionBox);

        JLabel errorLabel = new JLabel("");
        errorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(errorLabel);

        JButton cancelButton = createButton("Cancel");
        JButton editButton = createButton("Edit");
        addCancelButtonPanel(cancelButton, editButton);


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

        panel.revalidate();
        frame.revalidate();
        panel.repaint();
        frame.repaint();
    }

    private void enterQuestionEditor(ArrayList<Object> row) {
        panel.removeAll();
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

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

        JButton cancelButton = createButton("Cancel");
        JButton enterButton = createButton("Enter");
        addCancelButtonPanel(cancelButton, enterButton);


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
            	else if(ques.getText().contains("'")|| ques.getText().contains("\"") || corr.getText().contains("'")|| corr.getText().contains("\"") || inc1.getText().contains("'")|| inc1.getText().contains("\"") || inc2.getText().contains("'")|| inc2.getText().contains("\"") || inc3.getText().contains("'")|| inc3.getText().contains("\"")) {
            		errorLabel.setText("* Illegal character used *");
            	}
            	else
            	{
            		String query = "update question set question='" + ques.getText() + "',answer='" + corr.getText() + "',incorrect1='" + inc1.getText() + "',incorrect2='" + inc2.getText() + "',incorrect3='" + inc3.getText() + "' where qid="+row.get(0);
            		manage.runUpdateQuery(query);
            		enterManageTestMenu();
            	}

            }});

        panel.revalidate();
        frame.revalidate();
        panel.repaint();
        frame.repaint();
    }

    private void enterCreateTestScreen() {
        panel.removeAll();
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        int numQues = manage.count("qid", "question");
        int testQues = 4;
        ArrayList<JTextField> textFields = new ArrayList<>();

        addHeader("Create Test");
        String query = "select * from question";
        JTable table = manage.getResultsTable(query);
        if (table instanceof JTable) {
        	JScrollPane scrollPane = new JScrollPane(table);
        	panel.add(scrollPane, BorderLayout.CENTER);
        }

        JPanel inputPanel = new JPanel(new GridLayout(0,4));
        inputPanel.setBackground(backgroundColor);
        
        addLabel("Enter the qid of "+testQues+" questions:");
        for (int i = 0; i < testQues; ++i) {
        	textFields.add(new JTextField());
        }

        JLabel errorLabel = new JLabel("");
        errorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        for (JTextField textField : textFields) {
			inputPanel.add(textField);
		}
        
        panel.add(inputPanel);
        panel.add(errorLabel);

        JButton cancelButton = createButton("Cancel");
        JButton enterButton = createButton("Enter");
        addCancelButtonPanel(cancelButton, enterButton);

        cancelButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		enterManageTestMenu();
        	}
        });

        enterButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		for (JTextField textField : textFields) {
        			try {
        				if (textField.getText().isEmpty()) {
        					errorLabel.setText("* A field was left blank *");
        					return;
        				}
        				else if (Integer.parseInt(textField.getText()) > numQues || Integer.parseInt(textField.getText()) < 1) {
        					errorLabel.setText("* A field contains an invalid integer *");
        					return;
        				}
	        			int count = 0;
	        			for (JTextField otherField : textFields) {
							if (Integer.parseInt(textField.getText()) == Integer.parseInt(otherField.getText())) {
								++count;
							}
						}
	        			if (count > 1) {
	        				errorLabel.setText("* Two or more fields contain the same number *");
	        				return;
	        			}
        			} catch (NumberFormatException ex) {
        				errorLabel.setText("* A field contains a non-integer character *");
        				return;
        			}
				}
        		errorLabel.setText("");
        		String query = "insert into test(question1,question2,question3,question4) values (";
        		for (int i = 0; i < textFields.size(); ++i) {
        			query += Integer.parseInt(textFields.get(i).getText());
        			if (i < textFields.size()-1) {
        				query += ",";
        			}
        		}
        		query += ")";
        		manage.runUpdateQuery(query);
        		System.out.println("Test created \n");
        		enterMainMenu();

        	}});

        panel.revalidate();
        frame.revalidate();
        panel.repaint();
        frame.repaint();

    }

    private void enterTakeTestScreen() {
        panel.removeAll();
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        addHeader("Select User and Test");
        
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
        
        JLabel errorLabel = new JLabel("");
        errorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(errorLabel);
        
        JButton cancelButton = createButton("Cancel");
        JButton takeButton = createButton("Take");  
        addCancelButtonPanel(cancelButton, takeButton);
        
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
            	else if(((String) studentBox.getSelectedItem()).contains("'") || ((String) studentBox.getSelectedItem()).contains("\"")) {
            		errorLabel.setText("* Illegal character used *");
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
        
        panel.revalidate();
        frame.revalidate();
        panel.repaint();
        frame.repaint();
    }
    
    private void takeTest(int studentid, int testid) {
    	panel.removeAll();
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        addHeader("Take Test");
        
        ArrayList<ArrayList<Object>> questionids = manage.runQuery("select question1, question2, question3, question4 from test where testid='" + testid + "'");
        
        List<String> correctAnswers = new ArrayList<String>();
        List<JComboBox<String>> answerBoxes = new ArrayList<JComboBox<String>>();
        List<String> questionList = new ArrayList<String>();

        
        for(int i=0;i<questionids.get(0).size();i++) {
        	ArrayList<ArrayList<Object>> questions = manage.runQuery("select * from question where qid='" + questionids.get(0).get(i) + "'");
        	questionList.add(questions.get(0).get(1).toString());            
            
            List<String> answers = new ArrayList<String>();
            
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
        
        JLabel errorLabel = new JLabel("");
        errorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(errorLabel);
        
        JButton cancelButton = createButton("Cancel");
        JButton submitButton = createButton("Submit");
        addCancelButtonPanel(cancelButton, submitButton);
        
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
        		enterViewScoresScreen();
            }});
        
        
        panel.revalidate();
        frame.revalidate();
        panel.repaint();
        frame.repaint();
    }
    
    

    private void enterViewScoresScreen() {
        panel.removeAll();
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        addHeader("Scores");

        String query = "select numcorrect, testid, studentid from testlog";
        ArrayList<ArrayList<Object>> scores = manage.runQuery(query);
        
        String[] tableNames = new String[]{"Student Name", "Score (out of 4)", "Test ID"};
        Object[][] tabelCells = new Object[scores.size()][3];
        
        for(int row=0;row<scores.size();row++) {
        	tabelCells[row][0] = manage.runQuery("select name from student where studentid =" + scores.get(row).get(2)).get(0).get(0);
        	for(int col=0;col<scores.get(row).size()-1;col++) {
        		tabelCells[row][col+1] = scores.get(row).get(col);
        	}
        }
        
        JTable table = new JTable(tabelCells, tableNames);
        panel.add(new JScrollPane(table));
        
        JButton backButton = createButton("< Back");
        panel.add(backButton);

        backButton.addActionListener(new ActionListener() {
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
