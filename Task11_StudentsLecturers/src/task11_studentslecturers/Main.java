package task11_studentslecturers;;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
	// write your code here
        MainFrame mf = new MainFrame();
        //new Task10();
    }
}




class Task10 extends javax.swing.JFrame {

    private abstract static class Unit {
        private String name;

        Unit(String name) {
            this.name = name;
        }

        String getName() {
            return this.name;
        }
    }

    private static class Student extends Unit {
        private Integer age;

        Student(String name, int age) {
            super(name);
            this.age = (age <= 0) ? null : age;
        }

        @Override
        public String toString() {
            return "Student: " + getName() + ", " + age + " yo";
        }
    }

    private static class Teacher extends Unit {
        private String position;

        Teacher(String name, String position) {
            super(name);
            this.position = position;
        }

        @Override
        public String toString() {
            return "Teacher: " + getName() + ", works as " + position;
        }
    }

    static private java.util.ArrayList<Unit> facultyMembers = new java.util.ArrayList<>();

    private String facToString() {
        if(facultyMembers.size() == 0)
            return "List of students is empty!";
        else
        {
            StringBuilder str = new StringBuilder();
            for(Unit i: facultyMembers) {
                str.append(i.toString()).append("\n");
            }

            return str.toString();
        }
    }

    private static class InternalDialog extends JDialog {
        InternalDialog(boolean unitType) {
            setTitle(unitType ? "Student Data" : "Faculty Data");
            setSize(300, 120);
            setResizable(false);
            setModal(true);

            javax.swing.JLabel statusBar = new javax.swing.JLabel(" ");
            statusBar.setBorder(javax.swing.BorderFactory.createEtchedBorder());

            JPanel panel = new JPanel();
            panel.add(new JLabel("Name:"));
            JTextField name = new JTextField("", 10);
            panel.add(name);

            panel.add(new JLabel(unitType ? "Age:" : "Degree:"));
            JTextField ageDegree = new JTextField("", 5);
            panel.add(ageDegree);

            JPanel buttons = new JPanel();
            JButton acceptBtn = new JButton("Accept");
            acceptBtn.addActionListener(action -> {
                if (unitType) {
                    facultyMembers.add(new Student(name.getText(), Integer.parseInt(ageDegree.getText())));
                } else {
                    facultyMembers.add(new Teacher(name.getText(), ageDegree.getText()));
                }
                statusBar.setText("Accepted");
            });
            buttons.add(acceptBtn);
            JButton clearBtn = new JButton("Clear");
            clearBtn.addActionListener(acton -> {
                name.setText("");
                ageDegree.setText("");
            });
            buttons.add(clearBtn);
            JButton closeBtn = new JButton("Close");
            closeBtn.addActionListener(action -> dispose());
            buttons.add(closeBtn);

            add(buttons, BorderLayout.CENTER);
            add(panel, BorderLayout.NORTH);
            add(statusBar, java.awt.BorderLayout.SOUTH);
            setVisible(true);
        }
    }

    Task10() {
        super("DS&A. Assignment");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 100);
        setResizable(false);

        javax.swing.JMenuBar menuBar = new javax.swing.JMenuBar();

        javax.swing.JMenuItem studentData = new javax.swing.JMenuItem("Student Data");
        studentData.addActionListener(actionEvent -> new InternalDialog(true));

        javax.swing.JMenuItem facultyData = new javax.swing.JMenuItem("Faculty Data");
        facultyData.addActionListener(actionEvent -> new InternalDialog(false));

        javax.swing.JMenuItem printTheList = new javax.swing.JMenuItem("Print the list");
        printTheList.addActionListener(actionEvent -> javax.swing.JOptionPane.showMessageDialog(this,
                facToString()
                , "Faggots", JOptionPane.PLAIN_MESSAGE)
        );

        menuBar.add(studentData);
        menuBar.add(facultyData);
        menuBar.add(printTheList);

        add(menuBar, java.awt.BorderLayout.NORTH);
        setVisible(true);
    }
}


