package task10_texteditor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor extends JFrame {

    private JTextArea m_textArea;
    private StringBuilder m_buffer;

    private JButton m_btnChange;
    private JMenuItem m_changeMenuItem;
    private boolean m_canChange;

    private JButton m_btnRw;
    private JMenuItem m_rwMenuItem;
    private boolean m_canWrite;
    private File m_file;

    private JLabel m_status;

    public TextEditor() {
        super("Text editor");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.m_canChange = false;
        this.m_canWrite = false;
        this.m_file = null;

        Dimension baseSize = new Dimension(500,500);
        this.setMinimumSize(baseSize);

        // menu bar:
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(this.fileMenu());
        menuBar.add(this.OperationMenu());
        setJMenuBar(menuBar);

        Container container = this.getContentPane();
        // tool bar:
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);

        JButton btn_open = new JButton("Открыть");
        btn_open.addActionListener(new Open_ActionListener());
        toolBar.add(btn_open);

        JButton btn_save = new JButton("Сохранить");
        btn_save.addActionListener(new Save_ActionListener());
        toolBar.add(btn_save);

        m_btnChange = new JButton("Редактирование");
        m_btnChange.addActionListener(new Change_ActionListener());
        toolBar.add(m_btnChange);

        m_btnRw = new JButton("Читать, сохранять");
        m_btnRw.addActionListener(new Rw_ActionListener());
        toolBar.add(m_btnRw);

        JButton btn_rollBack = new JButton("Отмена");
        btn_rollBack.addActionListener(new RollBack_ActionListener());
        toolBar.add(btn_rollBack);
        container.add(toolBar, BorderLayout.NORTH);

        // text editor:
        m_textArea = new JTextArea();
        m_textArea.setLineWrap(true);
        m_textArea.setWrapStyleWord(true);
        m_textArea.setEditable(m_canChange);

        JScrollPane textScrollPanel = new JScrollPane(m_textArea);
        textScrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        container.add(textScrollPanel, BorderLayout.CENTER);

        // status bar:
        m_status = new JLabel("Режимы: чтение, просмотр");
        m_status.setBorder(BorderFactory.createEtchedBorder());
        container.add(m_status, BorderLayout.SOUTH);

        this.pack();
        this.setVisible(true);
    }

    private JMenu fileMenu() {
        JMenu fileMenu = new JMenu("ФАЙЛ");

        JMenuItem openFileMenuItem = new JMenuItem("Открыть");
        openFileMenuItem.addActionListener(new Open_ActionListener());
        fileMenu.add(openFileMenuItem);

        JMenuItem saveFileMenuItem = new JMenuItem("Сохранить");
        saveFileMenuItem.addActionListener(new Save_ActionListener());
        fileMenu.add(saveFileMenuItem);

        JMenuItem exitMenuItem = new JMenuItem("Выйти");
        fileMenu.add(exitMenuItem);

        return fileMenu;
    }

    private JMenu OperationMenu() {
        JMenu fileMenu = new JMenu("ОПЦИИ");

        m_changeMenuItem = new JMenuItem("Редактирование");
        m_changeMenuItem.addActionListener(new Change_ActionListener());
        fileMenu.add(m_changeMenuItem);

        m_rwMenuItem = new JMenuItem("Читать, сохранять");
        m_rwMenuItem.addActionListener(new Rw_ActionListener());
        fileMenu.add(m_rwMenuItem);

        JMenuItem rollBack = new JMenuItem("Отмена");
        rollBack.addActionListener(new RollBack_ActionListener());
        fileMenu.add(rollBack);

        return fileMenu;
    }


    private class Change_ActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            m_canChange = !m_canChange;
            m_changeMenuItem.setText(m_canChange? "Просмотр" : "Редактирование");
            m_btnChange.setText(m_canChange? "Просмотр" : "Редактирование");
            m_textArea.setEditable(m_canChange);

            String s = m_status.getText();
            m_status.setText(s.replace(m_canChange? "просмотр" : "редактирование", m_canChange? "редактирование" : "просмотр"));
        }
    }

    private class Rw_ActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            m_canWrite = !m_canWrite;
            m_rwMenuItem.setText(m_canWrite? "Только чтение": "Читать, сохранять");
            m_btnRw.setText(m_canWrite? "Только чтение": "Читать, сохранять");

            String s = m_status.getText();
            m_status.setText(s.replace(m_canWrite? "чтение" : "запись" , m_canWrite? "запись" : "чтение"));
        }
    }

    private class RollBack_ActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            m_textArea.setText(m_buffer != null? m_buffer.toString() : "");
        }
    }

    private class Open_ActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            openFile();
        }
    }

    private class Save_ActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (m_file != null){
                if (!m_canWrite) {
                    JOptionPane.showMessageDialog(null, "Запрещено сохранять изменения в фай!");
                } else {
                    m_buffer = new StringBuilder(m_textArea.getText());
                    saveFile();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Файл не выбран!");
            }
        }
    }


    private void openFile() {
        JFileChooser fileChooser = new JFileChooser("C:");
        if (fileChooser.showOpenDialog(this) == JFileChooser.OPEN_DIALOG) {
            m_file = fileChooser.getSelectedFile();
            try (FileReader fr = new FileReader(m_file)) {
                BufferedReader br = new BufferedReader(fr);
                String buf;
                m_buffer = new StringBuilder();
                while ((buf = br.readLine()) != null) {
                    m_buffer.append(buf).append("\n");
                }
                m_textArea.setText(m_buffer.toString());
                m_textArea.setEditable(m_canChange);
                this.setTitle(m_file.getAbsolutePath() + " - Text editor");
                br.close();
            }
            catch (IOException e) {
                JOptionPane.showMessageDialog(this, e.getMessage() + "\nRead fault!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Операция с файлами отменена!");
        }

    }

    private  void saveFile() {
        JFileChooser fileChooser = new JFileChooser();
        // if(fileChooser.showSaveDialog(null) == JFileChooser.SAVE_DIALOG) {
        try(FileWriter fw = new FileWriter(m_file)) {
            fw.write(m_buffer.toString());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, e.getMessage() + "\nWrite fault!");
        }
        //}
    }
}
