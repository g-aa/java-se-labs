package task11_studentslecturers;;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private enum DialogType {
        STUDENT("Student Data", "Name", "Age"),
        LECTURER("Faculty Data", "Name", "Degree");

        private String m_valueText;
        private String m_nameText;
        private String m_windowsTitle;

        private DialogType(String windowsTitle, String nameText, String valueText) {
            m_windowsTitle = windowsTitle;
            m_nameText = nameText;
            m_valueText = valueText;
        }

        public String getTitle() {
            return m_windowsTitle;
        }

        public String getNameText() {
            return  m_nameText;
        }

        public String getValueText() {
            return m_valueText;
        }
    };
    private FacultyData m_Data;
    private JTextArea m_textArea;
    private JLabel m_status;

    public MainFrame() {
        super("DS&A. Assignment");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        m_Data = new FacultyData();

        // menu bar:
        JMenuBar menuBar = new JMenuBar();
        JButton btn_student = new JButton(DialogType.STUDENT.getTitle());
        btn_student.addActionListener(actionEvent -> {
            new MyDialogFrame(DialogType.STUDENT);
            RefreshStatus();
        });
        menuBar.add(btn_student);

        JButton btn_faculty = new JButton(DialogType.LECTURER.getTitle());
        btn_faculty.addActionListener(actionEvent -> {
            new MyDialogFrame(DialogType.LECTURER);
            RefreshStatus();
        });
        menuBar.add(btn_faculty);

        JButton btn_print = new JButton("Print Data");
        btn_print.addActionListener(actionEvent -> m_textArea.setText(m_Data.toString()));
        menuBar.add(btn_print);
        this.setJMenuBar(menuBar);

        Container container = this.getContentPane();
        m_textArea = new JTextArea();
        m_textArea.setLineWrap(true);
        m_textArea.setWrapStyleWord(true);
        m_textArea.setEditable(false);

        JScrollPane textScrollPanel = new JScrollPane(m_textArea);
        textScrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        container.add(textScrollPanel, BorderLayout.CENTER);

        // status bar:
        m_status = new JLabel("About collection: "+ m_Data.getStudentCount() + " students; " + m_Data.getLecturerCount() + " lecturers");
        m_status.setBorder(BorderFactory.createEtchedBorder());
        container.add(m_status, BorderLayout.SOUTH);

        // this.pack();
        this.setMinimumSize(new Dimension(400,400));
        this.setVisible(true);
    }

    private void RefreshStatus() {
        m_status.setText(("About collection: "+ m_Data.getStudentCount() + " students; " + m_Data.getLecturerCount() + " lecturers"));
    }

    private class MyDialogFrame extends JDialog {
        private JTextField m_name;
        private JTextField m_value;
        private JLabel m_accept;

        private MyDialogFrame(DialogType dialogType) {
            this.setTitle(dialogType.getTitle());
            this.setModal(true);
            this.setResizable(false);
            Container container = this.getContentPane();

            // fields:
            JPanel nameValuePanel = new JPanel(new GridLayout(1, 4, 10, 10));
            nameValuePanel.add(new JLabel(dialogType.getNameText() + ":"));
            m_name = new JTextField("", 10);
            nameValuePanel.add(m_name);

            nameValuePanel.add(new JLabel(dialogType.getValueText() + ":"));
            m_value = new JTextField("", 10);
            nameValuePanel.add(m_value);

            // buttons:
            JPanel btnPanel = new JPanel(new GridLayout(1, 3, 10, 10));
            JButton btn_accept = new JButton("Accept");
            btnPanel.add(btn_accept);
            btn_accept.addActionListener(actionEvent -> {
                try {
                    boolean flag = (dialogType.equals(DialogType.LECTURER)) ?
                            m_Data.setLecturer(m_name.getText(), m_value.getText()) :
                            m_Data.setStudent(m_name.getText(), m_value.getText());
                    m_accept.setVisible(flag);
                    if (!flag) {
                        JOptionPane.showMessageDialog(this,dialogType.getNameText() + ": \"" + m_name.getText() + "\" существует в колекции!");
                    }
                } catch (IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(this, e.getMessage());
                }
            });

            JButton btn_clear = new JButton("Clear");
            btn_clear.addActionListener(actionEvent -> {
                m_accept.setVisible(false);
                m_name.setText("");
                m_value.setText("");
            });
            btnPanel.add(btn_clear);

            JButton btn_close = new JButton("Close");
            btn_close.addActionListener(action -> dispose());
            btnPanel.add(btn_close);

            // status:
            JPanel btnLblPanel = new JPanel(new GridLayout(2, 1, 10, 10));
            btnLblPanel.add(btnPanel);
            m_accept = new JLabel("ACCEPT!");
            m_accept.setVisible(false);
            btnLblPanel.add(m_accept);

            container.add(nameValuePanel, BorderLayout.NORTH);
            container.add(btnLblPanel, BorderLayout.SOUTH);
            this.pack();
            this.setVisible(true);
        }
    }
}