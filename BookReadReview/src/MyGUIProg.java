import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class MyGUIProg{
    private static CardLayout cL;
    private static JPanel cP;
    private final String saveDirectoryPath = "C:\\Users\\Michael Gilbert\\Desktop\\GUI_for_Book_Reads\\BookReadReview";
    private File directory;
    private void createTextFile(File directory, JFrame frame, String fP, String Title, String Author, Integer Rating, String Comment) throws IOException {
        try (BufferedWriter w = new BufferedWriter(new FileWriter(fP))) {
            w.write("Book Title: " + Title + "\n");
            w.write("Name of Author: " + Author + "\n");
            w.write("Rating: " + Rating + "/5\n");
            w.write("Comment: " + Comment);
            JOptionPane.showMessageDialog(frame, "Text saved to file Succesfully");
        }catch (IOException ex){
            JOptionPane.showMessageDialog(frame, "Error while trying to save text to file: " + ex.getMessage());
        }
    }
    private void UpdateDirectory(){
        directory = new File(saveDirectoryPath);
        //Doesn't work However implement harder way to do so later?
    }
    private File[] getTxtFilesName(File directory){
        return directory.listFiles(((dir, name) -> name.endsWith(".txt")));
    }

    private String getFileName(File file) {
        String fileName = file.getName();
        int lastPeriod = fileName.lastIndexOf('.');
        return fileName.substring(0, lastPeriod);
    }
    public MyGUIProg(){
                JFrame frame = new JFrame("Book Reviews");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                cL = new CardLayout();
                cP = new JPanel(cL);
                directory = new File(saveDirectoryPath);
                File[] txtFiles = getTxtFilesName(directory);

                //Panel 1
                JPanel p1 = new JPanel();
                p1.setLayout(new BorderLayout());
                JLabel t1 = new JLabel("Home Page");
                p1.add(t1, BorderLayout.PAGE_START);
                t1.setHorizontalAlignment(SwingConstants.CENTER);

                JPanel Text = new JPanel();
                JLabel I1 = new JLabel("Bienvenue!");
                Text.add(I1);
                JLabel I2 = new JLabel("Make a Book Review");
                Text.add(I2);
                JLabel I3 = new JLabel("or");
                Text.add(I3);
                JLabel I4 = new JLabel("Check Your Already Made Reviews");
                Text.add(I4);

                p1.add(Text, BorderLayout.CENTER);

                JPanel BM = new JPanel(new FlowLayout());
                JButton b1 = new JButton("Make a Review");
                BM.add(b1);
                JButton b4 = new JButton("Look at Reviews");
                BM.add(b4);
                p1.add(BM, BorderLayout.PAGE_END);


                //Panel 2
                JPanel p2 = new JPanel();
                p2.setLayout(new BorderLayout());

                JLabel t2 = new JLabel("Make a Review");
                p2.add(t2, BorderLayout.PAGE_START);
                t2.setHorizontalAlignment(SwingConstants.CENTER);


                JButton b2 = new JButton("Save Review");
                JButton b3 = new JButton("Go Back to Home");

                JPanel bPE = new JPanel(new BorderLayout());
                bPE.add(b2, BorderLayout.PAGE_START);
                bPE.add(b3,BorderLayout.PAGE_END);
                p2.add(bPE,BorderLayout.PAGE_END);


                JPanel bPR = new JPanel();
                bPR.setLayout(new BoxLayout(bPR, BoxLayout.Y_AXIS));
                JPanel BA = new JPanel();
                BA.setLayout(new BoxLayout(BA, BoxLayout.Y_AXIS));

                JPanel BookName = new JPanel(new FlowLayout());
                JLabel sN = new JLabel("Name of Book");
                BookName.add(sN);
                JTextArea BN = new JTextArea(1, 20);
                BookName.add(new JScrollPane(BN));
                BA.add(BookName);

                JPanel AuthorName = new JPanel(new FlowLayout());
                JLabel sA = new JLabel("Name of Author");
                AuthorName.add(sA);
                JTextArea AN = new JTextArea(1, 19);
                AuthorName.add(new JScrollPane(AN));
                BA.add(AuthorName);
                bPR.add(BA, BorderLayout.PAGE_START);

                JPanel Rating = new JPanel(new FlowLayout());
                JLabel sL = new JLabel("Select Rating");
                Rating.add(sL);
                Integer[] nums = {null, 1, 2, 3, 4, 5};
                JComboBox<Integer> nCB = new JComboBox<>(nums);
                Rating.add(nCB);
                bPR.add(Rating, BorderLayout.CENTER);

                JPanel CP = new JPanel(new FlowLayout());
                JLabel CL = new JLabel("Comment");
                JTextArea CN = new JTextArea(2,22);
                CP.add(CL);
                CP.add(new JScrollPane(CN), BorderLayout.PAGE_END);
                bPR.add(CP);

                p2.add(bPR, BorderLayout.CENTER);


                // Panel 3
                JPanel p3 = new JPanel();
                p3.setLayout(new BorderLayout());
                JLabel t3 = new JLabel("Look at a Review");
                p3.add(t3, BorderLayout.PAGE_START);
                t3.setHorizontalAlignment(SwingConstants.CENTER);
                JButton b5 = new JButton("Go Back to Home");
                p3.add(b5, BorderLayout.PAGE_END);

                JPanel Txtbuttons = new JPanel(new FlowLayout());
                if (txtFiles != null && txtFiles.length > 0) {
                    for (File file : txtFiles) {
                        String f1 = getFileName(file);
                        JButton fB = new JButton(f1);
                        ActionListener bL2 = new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                try{
                                    BufferedReader Reader = new BufferedReader(new FileReader(file));
                                    StringBuilder contentBuilder = new StringBuilder();
                                    String line;
                                    while ((Reader.readLine() != null)){
                                        line = Reader.readLine();
                                        contentBuilder.append(line).append("\n");
                                    }
                                    Reader.close();

                                    String fileContents = contentBuilder.toString();

                                    JTextArea tA = new JTextArea(40, 40);
                                    tA.setText(fileContents);
                                    tA.setEditable(false);
                                    JScrollPane SP = new 
                                    JOptionPane.showMessageDialog(frame, SP);
                                } catch(IOException r){
                                    JOptionPane.showMessageDialog(frame, "Our Book Worms Lost that Book!!");
                                }
                            }
                        };
                        fB.addActionListener(bL2);
                        Txtbuttons.add(fB);
                    }
                    p3.add(Txtbuttons, BorderLayout.CENTER);
                }
                else{
                    JOptionPane.showMessageDialog(frame, "There are no .txt files");
                }





                cP.add(p1, "Home Page");
                cP.add(p2,"Make a Review");
                cP.add(p3,"Look at a Review");

                frame.getContentPane().add(cP);

                ActionListener bL1 = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(e.getSource() == b1) {
                            cL.show(cP, "Make a Review");
                        }
                        else if (e.getSource() == b3 || e.getSource() == b5){
                            cL.show(cP, "Home Page");
                        }
                        else if (e.getSource() == b2){
                            String Title = BN.getText();
                            String fP = Title + ".txt";
                            String Author = AN.getText();
                            String Comment = CN.getText();
                            Integer Rating = (Integer) nCB.getSelectedItem();
                            try {
                                createTextFile(directory, frame, fP, Title, Author, Rating, Comment);
                            } catch (IOException ex) {
                                JOptionPane.showMessageDialog(frame, "There was an Error Creating the File");
                            }
                            BN.setText("");
                            AN.setText("");
                            CN.setText("");
                            nCB.setSelectedItem(null);
                        }
                        else if(e.getSource() == b4){
                            cL.show(cP, "Look at a Review");
                            }
                        }
                    };

                b1.addActionListener(bL1);
                b2.addActionListener(bL1);
                b3.addActionListener(bL1);
                b4.addActionListener(bL1);
                b5.addActionListener(bL1);

                frame.setSize(400,300);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }

    public static void main(String[] args){
        SwingUtilities.invokeLater(()->{
            MyGUIProg myInterface = new MyGUIProg();
        });
    }
}