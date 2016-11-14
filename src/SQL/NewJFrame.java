/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SQL;

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.prefs.Preferences;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.ComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
/**
 *
 * @author bmndo
 */
public class NewJFrame extends javax.swing.JFrame implements PropertyChangeListener {
    
    private File selectedFile = null;
    private File selectedFileEdit = null;
    private final String SERVER = "server";
    private final String USERNAME_SQL = "username";
    private final String PASSWORD_SQL = "password";
    private final String HOST = "host";
    private final String PORT = "port";
    private final String USERNAME_FTP = "usernameftp";
    private final String PASSWORD_FTP = "passwordftp";
    private String editTitle = "";
    private boolean sqlPassShowToggle = false;
    private boolean ftpPassShowToggle = false;
    private boolean initialized = false;
    private GetSQL sql = null;
    private Preferences prefs = null;
    private Connection conn = null;
    private boolean catUpdating = false;
    private int editId = -1;
    private boolean creating = false;
    private boolean updating = false;
    private boolean itemsUpdating = false;
    private boolean newCreated = false;
    private final int EDIT_PANE_INDEX = 0;
    private final int CREATE_PANE_INDEX = 1;
    private final int OPTIONS_PANE_INDEX = 2;
    /**
     * Creates new form NewJFrame
     */
    public NewJFrame() {
        prefs = Preferences.userNodeForPackage(SQL.NewJFrame.class);
        initComponents();
        try {
            SQLConnect();
        } catch(SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
    
    private boolean SQLConnect() throws SQLException {
        // Get the value of preferences;
        // default value is returned if the preference does not exist
        String serverDefault = "Server";
        String serverPrefValue = prefs.get(SERVER, serverDefault);
        String userDefault = "Username";
        String userPrefValue = prefs.get(USERNAME_SQL, userDefault);
        String passDefault = "Password";
        String passPrefValue = prefs.get(PASSWORD_SQL, passDefault);
        //Set fields according to preferences
        setServerField.setText(serverPrefValue);
        setSQLUsernameField.setText(userPrefValue);
        setSQLPasswordField.setText(passPrefValue);
        String hostDefault = "Host";
        String hostPrefValue = prefs.get(HOST, hostDefault);
        String portDefault = "Port";
        String portPrefValue = prefs.get(PORT, portDefault);
        String userFTPDefault = "Username";
        String userFTPPrefValue = prefs.get(USERNAME_FTP, userFTPDefault);
        String passFTPDefault = "Password";
        String passFTPPrefValue = prefs.get(PASSWORD_FTP, passFTPDefault);
        setHostField.setText(hostPrefValue);
        setPortField.setText(portPrefValue);
        setFTPUsernameField.setText(userFTPPrefValue);
        setFTPPasswordField.setText(passFTPPrefValue);
        //Connect to SQL server
        GetSQL sql = new GetSQL(serverPrefValue, userPrefValue, passPrefValue);
        //Handle connection failure
        if(!sql.connectionGood) {
            JOptionPane.showMessageDialog(warningPane, sql.exMessage.getMessage(), "SQL Server Connection Failed", JOptionPane.ERROR_MESSAGE);
            tabPane.setEnabledAt(EDIT_PANE_INDEX, false);
            tabPane.setEnabledAt(CREATE_PANE_INDEX, false);
            tabPane.setSelectedIndex(OPTIONS_PANE_INDEX);
            initialized = true;
            return false;
        } else {
            tabPane.setEnabledAt(EDIT_PANE_INDEX, true);
            tabPane.setEnabledAt(CREATE_PANE_INDEX, true);
            this.conn = sql.conn;
            updateAll();
            initialized = true;
            return true;
        }
    }
    
    private Statement createStatement() throws SQLException {
        Statement stmt = conn.createStatement();
        stmt.execute("USE fygarza_sep");
        return stmt;
    }
    
    private void updateAll() throws SQLException {
        //Set categories
        catUpdating = true;
        setEnabledAllEdit(false);
        ResultSet rs = createStatement().executeQuery("SELECT DISTINCT category FROM inventory");
        categoryList.removeAllItems();
        categoryListEdit.removeAllItems();
        categoryList.addItem("Select Category");
        categoryList.addItem("New Category");
        categoryListEdit.addItem("New Category");
        while(rs.next()) {
            categoryList.addItem(rs.getString("category"));
            categoryListEdit.addItem(rs.getString("category"));
        }
        categoryList.setSelectedItem(0);
        catUpdating = false;
        //Set items
        itemsUpdating = true;
        selectItemComboBox.removeAllItems();
        rs = createStatement().executeQuery("SELECT title FROM inventory");
        while(rs.next()) {
            selectItemComboBox.addItem(rs.getString("title"));
        }
        selectItemComboBox.addItem("Select Item");
        selectItemComboBox.setSelectedItem("Select Item");
        itemsUpdating = false;
    }
    
    private void setTabs(boolean enabled, int index) {
        tabPane.setEnabledAt(CREATE_PANE_INDEX, enabled);
        tabPane.setEnabledAt(OPTIONS_PANE_INDEX, enabled);
        tabPane.setEnabledAt(EDIT_PANE_INDEX, enabled);
        if(!enabled) {
            tabPane.setEnabledAt(index, true);
        }
        tabPane.setSelectedIndex(index);
    }
    
    private void setEnabledAllEdit(boolean enabled) {
        descriptionTextAreaEdit.setEnabled(enabled);
        Component[] arr = editPane.getComponents();
        for(Component component : arr) {
            component.setEnabled(enabled);
        }
        if(!enabled) {
            selectItemComboBox.setEnabled(true);
            searchByIDField.setEnabled(true);
            searchByIDButton.setEnabled(true);
            jLabel26.setEnabled(true);
            jLabel27.setEnabled(true);
        }
    }
    
    private void setEnabledAllCreate(boolean enabled) {
        Component[] arr = createPane.getComponents();
        for(Component component : arr) {
            component.setEnabled(enabled);
        }
    }
    
    private void populateData(int id) throws SQLException {
        ResultSet rs = createStatement().executeQuery("SELECT * FROM inventory WHERE id="+id);
        rs.next();
        searchByIDField.setText(String.valueOf(id));
        editTitle = rs.getString("title");
        titleFieldEdit.setText(editTitle);
        descriptionTextAreaEdit.setText(rs.getString("description"));
        String working;
        if(rs.getInt("working") == 1) {
            working = "Working";
        } else {
            working = "Nonworking";
        }
        workingComboBoxEdit.setSelectedItem(working);
        categoryListEdit.setSelectedItem(rs.getString("category"));
        timePeriodComboEdit.setSelectedItem(Integer.toString(rs.getInt("time_period")));
        availableSpinnerEdit.setValue((Integer)rs.getInt("num_available"));
        rentedSpinnerEdit.setValue((Integer)rs.getInt("num_rented"));
        editId = rs.getInt("id");
        filePathTextEdit.setText("No current selection");
        selectedFileEdit = null;
        imageLabelEdit.setIcon(null);
        imageLabelEdit.setText("No image");
        setEnabledAllEdit(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        warningPane = new javax.swing.JOptionPane();
        fileChooser = new javax.swing.JFileChooser();
        tabPane = new javax.swing.JTabbedPane();
        editPane = new javax.swing.JPanel();
        browseButtonEdit = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        filePathTextEdit = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        jSeparator11 = new javax.swing.JSeparator();
        jLabel21 = new javax.swing.JLabel();
        categoryListEdit = new javax.swing.JComboBox<>();
        jSeparator12 = new javax.swing.JSeparator();
        jLabel22 = new javax.swing.JLabel();
        timePeriodComboEdit = new javax.swing.JComboBox<>();
        jSeparator13 = new javax.swing.JSeparator();
        jLabel23 = new javax.swing.JLabel();
        availableSpinnerEdit = new javax.swing.JSpinner();
        jLabel24 = new javax.swing.JLabel();
        rentedSpinnerEdit = new javax.swing.JSpinner();
        jSeparator14 = new javax.swing.JSeparator();
        updateButton = new javax.swing.JButton();
        jSeparator15 = new javax.swing.JSeparator();
        jLabel26 = new javax.swing.JLabel();
        selectItemComboBox = new javax.swing.JComboBox<>();
        jLabel27 = new javax.swing.JLabel();
        searchByIDField = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        titleFieldEdit = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        descriptionTextAreaEdit = new javax.swing.JTextArea();
        jLabel31 = new javax.swing.JLabel();
        workingComboBoxEdit = new javax.swing.JComboBox<>();
        jSeparator17 = new javax.swing.JSeparator();
        jSeparator18 = new javax.swing.JSeparator();
        deleteButton = new javax.swing.JButton();
        searchByIDButton = new javax.swing.JButton();
        imageLabelEdit = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        uploadProgressBarEdit = new javax.swing.JProgressBar();
        createPane = new javax.swing.JPanel();
        browseButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        filePathText = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        titleField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        descriptionTextArea = new javax.swing.JTextArea();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        categoryList = new javax.swing.JComboBox<>();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        timePeriodCombo = new javax.swing.JComboBox<>();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel10 = new javax.swing.JLabel();
        availableSpinner = new javax.swing.JSpinner();
        jLabel11 = new javax.swing.JLabel();
        rentedSpinner = new javax.swing.JSpinner();
        jSeparator6 = new javax.swing.JSeparator();
        submitNewButton = new javax.swing.JButton();
        uploadProgressBar = new javax.swing.JProgressBar();
        jLabel16 = new javax.swing.JLabel();
        jSeparator16 = new javax.swing.JSeparator();
        jLabel28 = new javax.swing.JLabel();
        workingComboBox = new javax.swing.JComboBox<>();
        imageLabel = new javax.swing.JLabel();
        optionsPane = new javax.swing.JPanel();
        setSQLUsernameField = new javax.swing.JTextField();
        setServerField = new javax.swing.JTextField();
        setSQLPasswordField = new javax.swing.JPasswordField();
        prefSaveButtonSQL = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel12 = new javax.swing.JLabel();
        setHostField = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        setFTPUsernameField = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        setFTPPasswordField = new javax.swing.JPasswordField();
        prefSaveButtonFTP = new javax.swing.JButton();
        setPortField = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        refreshButton = new javax.swing.JButton();
        sqlShowPass = new javax.swing.JToggleButton();
        ftpShowPass = new javax.swing.JToggleButton();

        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setPreferredSize(new java.awt.Dimension(582, 582));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tabPane.setBackground(new java.awt.Color(255, 255, 255));
        tabPane.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tabPane.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tabPaneStateChanged(evt);
            }
        });

        editPane.setBackground(new java.awt.Color(255, 255, 255));

        browseButtonEdit.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        browseButtonEdit.setText("Browse...");
        browseButtonEdit.setToolTipText("Browse for image file");
        browseButtonEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseButtonEditActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel17.setText("Select item picture");

        filePathTextEdit.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        filePathTextEdit.setText("No current selection");

        jSeparator9.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator9.setToolTipText("");

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel21.setText("Choose Category");

        categoryListEdit.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        categoryListEdit.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Add new category" }));
        categoryListEdit.setToolTipText("");
        categoryListEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categoryListEditActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel22.setText("Choose Time Period");

        timePeriodComboEdit.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        timePeriodComboEdit.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1900", "1910", "1920", "1930", "1940", "1950", "1960", "1970", "1980", "1990", "2000", "2010" }));

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel23.setText("# Available");

        availableSpinnerEdit.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel24.setText("# Rented");

        rentedSpinnerEdit.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        updateButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        updateButton.setText("Update");
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel26.setText("Select Item");

        selectItemComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectItemComboBoxActionPerformed(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel27.setText("Select Item by ID");

        searchByIDField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchByIDFieldActionPerformed(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel29.setText("Enter title");

        titleFieldEdit.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        titleFieldEdit.setToolTipText("Type title here");
        titleFieldEdit.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                titleFieldEditFocusLost(evt);
            }
        });
        titleFieldEdit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                titleFieldEditKeyTyped(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel30.setText("Enter description");

        descriptionTextAreaEdit.setColumns(20);
        descriptionTextAreaEdit.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        descriptionTextAreaEdit.setLineWrap(true);
        descriptionTextAreaEdit.setRows(5);
        descriptionTextAreaEdit.setToolTipText("Type description here");
        descriptionTextAreaEdit.setWrapStyleWord(true);
        descriptionTextAreaEdit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                descriptionTextAreaEditKeyTyped(evt);
            }
        });
        jScrollPane3.setViewportView(descriptionTextAreaEdit);

        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel31.setText("Choose Working/Nonworking");

        workingComboBoxEdit.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        workingComboBoxEdit.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Working", "Nonworking" }));
        workingComboBoxEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                workingComboBoxEditActionPerformed(evt);
            }
        });

        deleteButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        deleteButton.setText("Delete");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        searchByIDButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        searchByIDButton.setText("Search");
        searchByIDButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchByIDButtonActionPerformed(evt);
            }
        });

        imageLabelEdit.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        imageLabelEdit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imageLabelEdit.setText("No Image");

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel19.setText("Upload Progress");

        uploadProgressBarEdit.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout editPaneLayout = new javax.swing.GroupLayout(editPane);
        editPane.setLayout(editPaneLayout);
        editPaneLayout.setHorizontalGroup(
            editPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(editPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(editPaneLayout.createSequentialGroup()
                        .addComponent(jSeparator15)
                        .addContainerGap())
                    .addGroup(editPaneLayout.createSequentialGroup()
                        .addGroup(editPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(editPaneLayout.createSequentialGroup()
                                .addGroup(editPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jSeparator12)
                                    .addGroup(editPaneLayout.createSequentialGroup()
                                        .addComponent(jLabel22)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(timePeriodComboEdit, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jSeparator13, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(editPaneLayout.createSequentialGroup()
                                        .addGroup(editPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel17)
                                            .addGroup(editPaneLayout.createSequentialGroup()
                                                .addComponent(filePathTextEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addGroup(editPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel18)
                                                    .addComponent(browseButtonEdit)))
                                            .addComponent(jSeparator11)
                                            .addGroup(editPaneLayout.createSequentialGroup()
                                                .addComponent(jLabel21)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(categoryListEdit, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGroup(editPaneLayout.createSequentialGroup()
                                                .addComponent(jLabel23)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(availableSpinnerEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel24)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(rentedSpinnerEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jSeparator14, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 1, Short.MAX_VALUE)))
                                .addGap(18, 18, 18)
                                .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(editPaneLayout.createSequentialGroup()
                                .addComponent(imageLabelEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGroup(editPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(editPaneLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(editPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(editPaneLayout.createSequentialGroup()
                                        .addGroup(editPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(editPaneLayout.createSequentialGroup()
                                                .addGap(17, 17, 17)
                                                .addGroup(editPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(editPaneLayout.createSequentialGroup()
                                                        .addGroup(editPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jLabel29)
                                                            .addComponent(jLabel30))
                                                        .addGap(0, 0, Short.MAX_VALUE))
                                                    .addComponent(titleFieldEdit)
                                                    .addComponent(jScrollPane3)))
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, editPaneLayout.createSequentialGroup()
                                                .addComponent(jLabel31)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(workingComboBoxEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jSeparator17, javax.swing.GroupLayout.Alignment.LEADING))
                                        .addGap(6, 6, 6))
                                    .addGroup(editPaneLayout.createSequentialGroup()
                                        .addGroup(editPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jSeparator18, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(editPaneLayout.createSequentialGroup()
                                                .addGap(54, 54, 54)
                                                .addGroup(editPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(updateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addContainerGap(21, Short.MAX_VALUE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editPaneLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(editPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                    .addComponent(uploadProgressBarEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel19))
                                .addGap(22, 22, 22))))))
            .addGroup(editPaneLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel26)
                .addGap(18, 18, 18)
                .addComponent(selectItemComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80)
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(searchByIDField, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(searchByIDButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        editPaneLayout.setVerticalGroup(
            editPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editPaneLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(editPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(selectItemComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26)
                    .addComponent(jLabel27)
                    .addComponent(searchByIDField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchByIDButton))
                .addGap(17, 17, 17)
                .addComponent(jSeparator15, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(editPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(editPaneLayout.createSequentialGroup()
                        .addComponent(jLabel29)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(titleFieldEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel30)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator18, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(editPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel31)
                            .addComponent(workingComboBoxEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator17, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(updateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel19)
                        .addGap(18, 18, 18)
                        .addComponent(uploadProgressBarEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(editPaneLayout.createSequentialGroup()
                        .addGroup(editPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(editPaneLayout.createSequentialGroup()
                                .addGroup(editPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(editPaneLayout.createSequentialGroup()
                                        .addComponent(jLabel17)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel18)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(filePathTextEdit))
                                    .addComponent(browseButtonEdit))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(editPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel21)
                                    .addComponent(categoryListEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jSeparator12, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(editPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel22)
                                    .addComponent(timePeriodComboEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jSeparator13, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(7, 7, 7)
                                .addGroup(editPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel23)
                                    .addComponent(availableSpinnerEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel24)
                                    .addComponent(rentedSpinnerEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator14, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(imageLabelEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        tabPane.addTab("Edit existing", editPane);

        createPane.setBackground(new java.awt.Color(255, 255, 255));

        browseButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        browseButton.setText("Browse...");
        browseButton.setToolTipText("Browse for image file");
        browseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseButtonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Select item picture");

        filePathText.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        filePathText.setText("No current selection");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator1.setToolTipText("");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Enter title");

        titleField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        titleField.setToolTipText("Type title here");
        titleField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                titleFieldFocusLost(evt);
            }
        });
        titleField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                titleFieldKeyTyped(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Enter description");

        descriptionTextArea.setColumns(20);
        descriptionTextArea.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        descriptionTextArea.setLineWrap(true);
        descriptionTextArea.setRows(5);
        descriptionTextArea.setToolTipText("Type description here");
        descriptionTextArea.setWrapStyleWord(true);
        descriptionTextArea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                descriptionTextAreaKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(descriptionTextArea);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Choose Category");

        categoryList.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        categoryList.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Category", "New Category" }));
        categoryList.setToolTipText("");
        categoryList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categoryListActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Choose Time Period");

        timePeriodCombo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        timePeriodCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Period", "1900", "1910", "1920", "1930", "1940", "1950", "1960", "1970", "1980", "1990", "2000", "2010" }));
        timePeriodCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timePeriodComboActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("# Available");

        availableSpinner.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("# Rented");

        rentedSpinner.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        submitNewButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        submitNewButton.setText("Create");
        submitNewButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitNewButtonActionPerformed(evt);
            }
        });

        uploadProgressBar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel16.setText("Upload Progress");

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel28.setText("Choose Working/Nonworking");

        workingComboBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        workingComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Item", "Working", "Nonworking" }));

        imageLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        imageLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imageLabel.setText("No Image");

        javax.swing.GroupLayout createPaneLayout = new javax.swing.GroupLayout(createPane);
        createPane.setLayout(createPaneLayout);
        createPaneLayout.setHorizontalGroup(
            createPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(createPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(createPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(createPaneLayout.createSequentialGroup()
                        .addComponent(imageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(createPaneLayout.createSequentialGroup()
                        .addGroup(createPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator4)
                            .addGroup(createPaneLayout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(timePeriodCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jSeparator5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(createPaneLayout.createSequentialGroup()
                                .addGroup(createPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel1)
                                    .addGroup(createPaneLayout.createSequentialGroup()
                                        .addComponent(filePathText, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addGroup(createPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2)
                                            .addComponent(browseButton)))
                                    .addComponent(jSeparator3, javax.swing.GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)
                                    .addGroup(createPaneLayout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(categoryList, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(createPaneLayout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(availableSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel11)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(rentedSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jSeparator6))
                                .addGap(0, 2, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(createPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(createPaneLayout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addGroup(createPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(createPaneLayout.createSequentialGroup()
                                        .addGroup(createPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel4))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(titleField)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)))
                            .addGroup(createPaneLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 19, Short.MAX_VALUE)
                                .addGroup(createPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(createPaneLayout.createSequentialGroup()
                                        .addComponent(jLabel28)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(workingComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(createPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                                        .addComponent(jSeparator16, javax.swing.GroupLayout.Alignment.TRAILING))
                                    .addGroup(createPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                        .addComponent(submitNewButton, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(uploadProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel16)))))
                        .addGap(20, 20, 20))))
        );
        createPaneLayout.setVerticalGroup(
            createPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(createPaneLayout.createSequentialGroup()
                .addGroup(createPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(createPaneLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(createPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(createPaneLayout.createSequentialGroup()
                                .addGroup(createPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(createPaneLayout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(filePathText))
                                    .addComponent(browseButton))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(createPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(categoryList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(createPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(timePeriodCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(7, 7, 7)
                                .addGroup(createPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(availableSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11)
                                    .addComponent(rentedSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(12, 12, 12)
                                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(createPaneLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(titleField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jSeparator16, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(createPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel28)
                                    .addComponent(workingComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(createPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(createPaneLayout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(submitNewButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel16)
                        .addGap(18, 18, 18)
                        .addComponent(uploadProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(128, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, createPaneLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(imageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        tabPane.addTab("Create new", createPane);

        optionsPane.setBackground(new java.awt.Color(255, 255, 255));

        setSQLUsernameField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        setSQLUsernameField.setText("Username");

        setServerField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        setServerField.setText("Server");
        setServerField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setServerFieldActionPerformed(evt);
            }
        });

        setSQLPasswordField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        setSQLPasswordField.setText("password");

        prefSaveButtonSQL.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        prefSaveButtonSQL.setText("Save SQL Settings");
        prefSaveButtonSQL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prefSaveButtonSQLActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Set Server");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Set Username");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Set Password");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("Set Host");

        setHostField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        setHostField.setText("Host");
        setHostField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setHostFieldActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("Set Username");

        setFTPUsernameField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        setFTPUsernameField.setText("Username");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setText("Set Password");

        setFTPPasswordField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        setFTPPasswordField.setText("password");

        prefSaveButtonFTP.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        prefSaveButtonFTP.setText("Save FTP Settings");
        prefSaveButtonFTP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prefSaveButtonFTPActionPerformed(evt);
            }
        });

        setPortField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        setPortField.setText("Port");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setText("Set Port");

        refreshButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        refreshButton.setText("Refresh");
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonActionPerformed(evt);
            }
        });

        sqlShowPass.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        sqlShowPass.setText("Show Password");
        sqlShowPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sqlShowPassActionPerformed(evt);
            }
        });

        ftpShowPass.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ftpShowPass.setText("Show Password");
        ftpShowPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ftpShowPassActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout optionsPaneLayout = new javax.swing.GroupLayout(optionsPane);
        optionsPane.setLayout(optionsPaneLayout);
        optionsPaneLayout.setHorizontalGroup(
            optionsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(optionsPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(optionsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator7, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(optionsPaneLayout.createSequentialGroup()
                        .addGroup(optionsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(optionsPaneLayout.createSequentialGroup()
                                .addGroup(optionsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(setSQLUsernameField, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8))
                                .addGap(18, 18, 18)
                                .addComponent(prefSaveButtonSQL))
                            .addGroup(optionsPaneLayout.createSequentialGroup()
                                .addComponent(setSQLPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(sqlShowPass))
                            .addComponent(setServerField, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(setPortField, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15)
                            .addComponent(setHostField, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)
                            .addComponent(setFTPUsernameField, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14)
                            .addGroup(optionsPaneLayout.createSequentialGroup()
                                .addGroup(optionsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(setFTPPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13))
                                .addGap(18, 18, 18)
                                .addGroup(optionsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(prefSaveButtonFTP)
                                    .addComponent(ftpShowPass))))
                        .addGap(0, 348, Short.MAX_VALUE))
                    .addComponent(jSeparator8))
                .addContainerGap())
            .addGroup(optionsPaneLayout.createSequentialGroup()
                .addGap(366, 366, 366)
                .addComponent(refreshButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        optionsPaneLayout.setVerticalGroup(
            optionsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(optionsPaneLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(setServerField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(optionsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(setSQLUsernameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(prefSaveButtonSQL))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(optionsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(optionsPaneLayout.createSequentialGroup()
                        .addComponent(setSQLPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(setHostField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(setPortField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(optionsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(prefSaveButtonFTP))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(setFTPUsernameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(optionsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(setFTPPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ftpShowPass))
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(refreshButton))
                    .addComponent(sqlShowPass))
                .addContainerGap(120, Short.MAX_VALUE))
        );

        tabPane.addTab("Options", optionsPane);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabPane)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabPane)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void prefSaveButtonSQLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prefSaveButtonSQLActionPerformed
        String serverPref = setServerField.getText().trim();
        String userPref = setSQLUsernameField.getText().trim();
        String passPref = setSQLPasswordField.getText();
        prefs.put(SERVER, serverPref);
        prefs.put(USERNAME_SQL, userPref);
        prefs.put(PASSWORD_SQL, passPref);
        try {
            SQLConnect();
        } catch(SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }//GEN-LAST:event_prefSaveButtonSQLActionPerformed

    private boolean isDuplicateTitle(String title, String exclude) {
        try {
            try {
                if(!conn.isValid(0)) {
                    SQLConnect();
                }
            } catch (SQLException e) {
                System.out.println("SQLException: " + e.getMessage());
                System.out.println("SQLState: " + e.getSQLState());
                System.out.println("VendorError: " + e.getErrorCode());
                JOptionPane.showMessageDialog(warningPane, "Critical SQL Error, restarting", "Warning", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }
            PreparedStatement stmt = conn.prepareStatement("SELECT title FROM inventory WHERE title=?");
            stmt.setString(1, title);
            ResultSet rs = stmt.executeQuery();
            if(rs.next() && !rs.getString("title").equals(exclude)) {
                return true;
            } else {return false;}
        } catch(SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            JOptionPane.showMessageDialog(warningPane, "Critical SQL Error, restarting", "Warning", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
            return true;
        }
    }
    
    private void clearCreate() {
        filePathText.setText("No current selection");
        selectedFile = null;
        titleField.setText("");
        descriptionTextArea.setText("");
        workingComboBox.setSelectedIndex(0);
        timePeriodCombo.setSelectedIndex(0);
        availableSpinner.setValue(0);
        rentedSpinner.setValue(0);
        workingComboBox.setSelectedIndex(0);
        imageLabel.setIcon(null);
        imageLabel.setText("No Image");
    }
      
    /**
     * Update the progress bar's state whenever the progress of upload changes.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("progress".equals(evt.getPropertyName())) {
            int progress = (Integer) evt.getNewValue();
            if(creating) {
                uploadProgressBar.setValue(progress);
            } else if(updating) {
                uploadProgressBarEdit.setValue(progress);
            } else {
                JOptionPane.showMessageDialog(warningPane, "Critical error with loading bar", "Warning", JOptionPane.ERROR_MESSAGE);
            }
            if(progress == 100) {
                if(creating) {
                    setEnabledAllCreate(true);
                    setTabs(true, CREATE_PANE_INDEX);
                    progress = 0;
                    uploadProgressBar.setValue(progress);
                    JOptionPane.showMessageDialog(warningPane, "Item Created", "Complete", JOptionPane.INFORMATION_MESSAGE);
                    creating = false;
                    newCreated = false;
                    clearCreate();
                } else if(updating) {
                    setEnabledAllEdit(true);
                    setTabs(true, EDIT_PANE_INDEX);
                    progress = 0;
                    uploadProgressBarEdit.setValue(progress);
                    JOptionPane.showMessageDialog(warningPane, "Item Updated", "Complete", JOptionPane.INFORMATION_MESSAGE);
                    updating = false;
                } else {
                    JOptionPane.showMessageDialog(warningPane, "Critical error with loading bar", "Warning", JOptionPane.ERROR_MESSAGE);
                }
                try {
                    if(initialized && tabPane.getSelectedIndex() == 0 || tabPane.getSelectedIndex() == 1) {
                        try {
                            if(!conn.isValid(0)) {
                                SQLConnect();
                            }
                        } catch (SQLException e) {
                            System.out.println("SQLException: " + e.getMessage());
                            System.out.println("SQLState: " + e.getSQLState());
                            System.out.println("VendorError: " + e.getErrorCode());
                            JOptionPane.showMessageDialog(warningPane, "Critical SQL Error, restarting", "Warning", JOptionPane.ERROR_MESSAGE);
                            System.exit(0);
                        }
                        updateAll();
                    }
                } catch(SQLException ex) {
                    // handle any errors
                    System.out.println("SQLException: " + ex.getMessage());
                    System.out.println("SQLState: " + ex.getSQLState());
                    System.out.println("VendorError: " + ex.getErrorCode());
                    JOptionPane.showMessageDialog(warningPane, "Critical SQL Error, restarting", "Warning", JOptionPane.ERROR_MESSAGE);
                    System.exit(0);
                }   
            }
        }
    }
    
    private void prefSaveButtonFTPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prefSaveButtonFTPActionPerformed
        String hostPref = setHostField.getText().trim();
        String portPref = setPortField.getText().trim();
        String userPref = setFTPUsernameField.getText().trim();
        String passPref = setFTPPasswordField.getText();
        prefs.put(HOST, hostPref);
        prefs.put(PORT, portPref);
        prefs.put(USERNAME_FTP, userPref);
        prefs.put(PASSWORD_FTP, passPref);
    }//GEN-LAST:event_prefSaveButtonFTPActionPerformed

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        try {
            SQLConnect();
        } catch(SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            JOptionPane.showMessageDialog(warningPane, "Critical SQL Error, restarting", "Warning", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }//GEN-LAST:event_refreshButtonActionPerformed

    private void browseButtonEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseButtonEditActionPerformed
        FileFilter filter = new FileNameExtensionFilter(".png, .jpg, .jpeg", "png", "jpg", "jpeg");
        fileChooser.addChoosableFileFilter(filter);
        int returnVal = fileChooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            selectedFileEdit = file;
            if(filter.accept(file)) {
                filePathTextEdit.setText(file.getAbsolutePath());
                try {
                    BufferedImage myPicture = ImageIO.read(file);
                    myPicture = (BufferedImage)getScaledImage(myPicture, imageLabelEdit.getWidth(), imageLabelEdit.getHeight());
                    imageLabelEdit.setIcon(new ImageIcon(myPicture));
                    imageLabelEdit.setText("");
                } catch(Exception e) {
                    JOptionPane.showMessageDialog(warningPane, "Error reading image", "Warning", JOptionPane.ERROR_MESSAGE);
                    System.err.println(e.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(warningPane, "Please select either a .png, .jpg, or .jpeg file", "Warning", JOptionPane.ERROR_MESSAGE);
                fileChooser.setCurrentDirectory(file);
                browseButtonEditActionPerformed(evt);
            }
        } else {
            System.out.println("File access cancelled by user.");
        }
    }//GEN-LAST:event_browseButtonEditActionPerformed

    /* private boolean checkInput(String s, JComponent focus) {
        Pattern p = Pattern.compile("[^A-Za-z0-9 ]");
        Matcher m = p.matcher(s);
        boolean b = m.find();
        if(b) {
            JOptionPane.showMessageDialog(warningPane, "Invalid input; please do not input any special characters", "Warning", JOptionPane.ERROR_MESSAGE);
            focus.requestFocusInWindow();
            return false;
        }
        return true;
    } */
    
    private void categoryListEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoryListEditActionPerformed
        if(!catUpdating && categoryListEdit.getSelectedItem().equals("New Category")) {
            categoryEditPrompt();
        }
    }//GEN-LAST:event_categoryListEditActionPerformed
    
    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        setEnabledAllEdit(false);
        setTabs(false, EDIT_PANE_INDEX);
        //Check that fields are valid
        if(titleFieldEdit.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(warningPane, "Please enter a title", "Invalid entry", JOptionPane.ERROR_MESSAGE);
            setEnabledAllEdit(true);
            setTabs(true, EDIT_PANE_INDEX);
            return;
        }
        if(descriptionTextAreaEdit.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(warningPane, "Please enter a description", "Invalid entry", JOptionPane.ERROR_MESSAGE);
            setEnabledAllEdit(true);
            setTabs(true, EDIT_PANE_INDEX);
            return;
        }
        if(categoryListEdit.getSelectedItem().equals("New Category")) {
            JOptionPane.showMessageDialog(warningPane, "Please select a category", "Invalid entry", JOptionPane.ERROR_MESSAGE);
            setEnabledAllEdit(true);
            setTabs(true, EDIT_PANE_INDEX);
            return;
        }
        String oldName = null;
        try {
            try {
                if(!conn.isValid(0)) {
                    SQLConnect();
                }
            } catch (SQLException e) {
                System.out.println("SQLException: " + e.getMessage());
                System.out.println("SQLState: " + e.getSQLState());
                System.out.println("VendorError: " + e.getErrorCode());
                JOptionPane.showMessageDialog(warningPane, "Critical SQL Error, restarting", "Warning", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }
            ResultSet rs2 = createStatement().executeQuery("SELECT picture_path FROM inventory WHERE id='"+editId+"'");
            if(rs2.next()) {
                oldName = rs2.getString("picture_path");
            } else {
                JOptionPane.showMessageDialog(warningPane, "Error getting SQL data", "Server error", JOptionPane.ERROR_MESSAGE);
                setEnabledAllEdit(true);
                setTabs(true, EDIT_PANE_INDEX);
                return;
            }
        } catch(SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            JOptionPane.showMessageDialog(warningPane, "Critical SQL Error, restarting", "Warning", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }       
        String fileName = titleFieldEdit.getText().toLowerCase();
        if(oldName != null) {
            try {
                fileName = URLEncoder.encode(fileName, "UTF-8");
                fileName += "."+((selectedFileEdit != null) ? (FilenameUtils.getExtension(selectedFileEdit.getAbsolutePath()).toLowerCase()) : (oldName.substring(oldName.indexOf(".")+1)));
            } catch(Exception e) {
                JOptionPane.showMessageDialog(warningPane, "Error encoding title. Please try a different title", "Internal error", JOptionPane.ERROR_MESSAGE);
                setEnabledAllEdit(true);
                setTabs(true, EDIT_PANE_INDEX);
                return;
            }
        }
        updating = true;
        boolean working;
        if(workingComboBoxEdit.getSelectedItem().equals("Working")) {
            working = true;
        } else {
            working = false;
        }
          
        //Modify SQL entry
        try {
            try {
                if(!conn.isValid(0)) {
                    SQLConnect();
                }
            } catch (SQLException e) {
                System.out.println("SQLException: " + e.getMessage());
                System.out.println("SQLState: " + e.getSQLState());
                System.out.println("VendorError: " + e.getErrorCode());
                JOptionPane.showMessageDialog(warningPane, "Critical SQL Error, restarting", "Warning", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }
            PreparedStatement stmt = conn.prepareStatement("UPDATE inventory SET title=?,description=?,category=?,time_period=?,picture_path=?,num_available=?,num_rented=?,working=? WHERE id=?");
            stmt.setString(1, titleFieldEdit.getText());
            stmt.setString(2, descriptionTextAreaEdit.getText());
            stmt.setString(3, (String)categoryListEdit.getSelectedItem());
            stmt.setInt(4, Integer.parseInt((String)timePeriodComboEdit.getSelectedItem()));
            stmt.setString(5, ("/images/inventory/"+fileName));
            stmt.setInt(6, (Integer)availableSpinnerEdit.getValue());
            stmt.setInt(7, (Integer)rentedSpinnerEdit.getValue());
            stmt.setBoolean(8, working);
            stmt.setInt(9, editId);
            stmt.executeUpdate();
        } catch(SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            JOptionPane.showMessageDialog(warningPane, "Critical SQL Error, restarting", "Warning", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        //Upload image via FTP
        if(selectedFileEdit != null) {
            String host = prefs.get(HOST, "127.0.0.1");
            int port = Integer.parseInt(prefs.get(PORT, "8080"));
            String username = prefs.get(USERNAME_FTP, "root");
            String password = prefs.get(PASSWORD_FTP, "");
            FTPClient ftp = new FTPClient();
            try {
                ftp.connect(host, port);
                int replyCode = ftp.getReplyCode();
                if (!FTPReply.isPositiveCompletion(replyCode)) {
                    JOptionPane.showMessageDialog(warningPane, "FTP Server refused connection", "Error", JOptionPane.ERROR_MESSAGE);
                    setEnabledAllEdit(true);
                    setTabs(true, EDIT_PANE_INDEX);
                    return;
                }

                boolean logged = ftp.login(username, password);
                if (!logged) {
                    // failed to login
                    ftp.disconnect();
                    JOptionPane.showMessageDialog(warningPane, "Could not login to server", "Error", JOptionPane.ERROR_MESSAGE);
                    setEnabledAllEdit(true);
                    setTabs(true, EDIT_PANE_INDEX);
                    return;
                }

                boolean deleted = ftp.deleteFile(oldName);
                if(!deleted) {
                    JOptionPane.showMessageDialog(warningPane, "Could not delete FTP", "Error", JOptionPane.ERROR_MESSAGE);
                    setEnabledAllEdit(true);
                    setTabs(true, EDIT_PANE_INDEX);
                    return;
                }

                ftp.enterLocalPassiveMode();

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(warningPane, "IO Error: "+ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            String uploadPath = "/images/inventory/";
            String filePath = selectedFileEdit.getAbsolutePath();
            File uploadFile = new File(filePath);
            uploadProgressBarEdit.setValue(0);
            UploadTask task = new UploadTask(host, port, username, password,
                uploadPath, uploadFile, fileName);
            task.addPropertyChangeListener(this);
            task.execute();
        } else {
            String host = prefs.get(HOST, "127.0.0.1");
            int port = Integer.parseInt(prefs.get(PORT, "8080"));
            String username = prefs.get(USERNAME_FTP, "root");
            String password = prefs.get(PASSWORD_FTP, "");
            String uploadPath = "/images/inventory/";
            renameTask task = new renameTask(host, port, username, password,
                uploadPath, oldName, fileName);
            task.execute();
            
            try {
                if(initialized && tabPane.getSelectedIndex() == 0 || tabPane.getSelectedIndex() == 1) {
                    try {
                        if(!conn.isValid(0)) {
                            SQLConnect();
                        }
                    } catch (SQLException e) {
                        System.out.println("SQLException: " + e.getMessage());
                        System.out.println("SQLState: " + e.getSQLState());
                        System.out.println("VendorError: " + e.getErrorCode());
                        JOptionPane.showMessageDialog(warningPane, "Critical SQL Error, restarting", "Warning", JOptionPane.ERROR_MESSAGE);
                        System.exit(0);
                    }
                    updateAll();
                }
            } catch(SQLException ex) {
                // handle any errors
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
                JOptionPane.showMessageDialog(warningPane, "Critical SQL Error, restarting", "Warning", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            } finally {
                updating = false;
                JOptionPane.showMessageDialog(warningPane, "Item Updated", "Complete", JOptionPane.INFORMATION_MESSAGE);
                setTabs(true, EDIT_PANE_INDEX);
            }
        }
    }//GEN-LAST:event_updateButtonActionPerformed

    private void titleFieldEditKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_titleFieldEditKeyTyped
        String text = titleField.getText();
        if(text.length() > 20) {
            JOptionPane.showMessageDialog(warningPane, "Please limit titles to 20 characters", "Warning", JOptionPane.ERROR_MESSAGE);
            titleField.setText(text.substring(0, text.length() - 1));
        }
    }//GEN-LAST:event_titleFieldEditKeyTyped

    private void descriptionTextAreaEditKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_descriptionTextAreaEditKeyTyped
        String text = descriptionTextArea.getText();
        if(text.length() > 150) {
            descriptionTextArea.setText(text.substring(0, text.length() - 1));
            JOptionPane.showMessageDialog(warningPane, "Please limit descriptions to 150 characters", "Warning", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_descriptionTextAreaEditKeyTyped

    private void searchByIDFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchByIDFieldActionPerformed
        
    }//GEN-LAST:event_searchByIDFieldActionPerformed

    private void selectItemComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectItemComboBoxActionPerformed
        if(itemsUpdating) {
            return;
        }
        if(((String)selectItemComboBox.getSelectedItem()).equals("Select Item")) {
            setEnabledAllEdit(false);
            setTabs(true, EDIT_PANE_INDEX);
            return;
        }
        try {
            try {
                if(!conn.isValid(0)) {
                    SQLConnect();
                }
            } catch (SQLException e) {
                System.out.println("SQLException: " + e.getMessage());
                System.out.println("SQLState: " + e.getSQLState());
                System.out.println("VendorError: " + e.getErrorCode());
                JOptionPane.showMessageDialog(warningPane, "Critical SQL Error, restarting", "Warning", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }
            ResultSet rs = createStatement().executeQuery("SELECT id FROM inventory WHERE title='"+(String)selectItemComboBox.getSelectedItem()+"'");
            if(rs.next()) {
                populateData(rs.getInt("id"));
            }
        } catch(SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            JOptionPane.showMessageDialog(warningPane, "Critical SQL Error, restarting", "Warning", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }            
    }//GEN-LAST:event_selectItemComboBoxActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        //Delete image
        boolean fullyDeleted  = true;
        boolean foundFile = false;
        String deletePath = "";
        try {
            try {
                if(!conn.isValid(0)) {
                    SQLConnect();
                }
            } catch (SQLException e) {
                System.out.println("SQLException: " + e.getMessage());
                System.out.println("SQLState: " + e.getSQLState());
                System.out.println("VendorError: " + e.getErrorCode());
                JOptionPane.showMessageDialog(warningPane, "Critical SQL Error, restarting", "Warning", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }
            ResultSet rs = createStatement().executeQuery("SELECT picture_path FROM inventory WHERE id="+editId);
            if(rs.next()) {
                deletePath = rs.getString("picture_path");
                System.out.println(deletePath);
                foundFile = true;
            }
        } catch(SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            JOptionPane.showMessageDialog(warningPane, "Critical SQL Error, restarting", "Warning", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        String host = prefs.get(HOST, "127.0.0.1");
        int port = Integer.parseInt(prefs.get(PORT, "8080"));
        String username = prefs.get(USERNAME_FTP, "root");
        String password = prefs.get(PASSWORD_FTP, "");
        FTPClient ftp = new FTPClient();
        try {
            ftp.connect(host, port);
            int replyCode = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(replyCode)) {
                JOptionPane.showMessageDialog(warningPane, "FTP Server refused connection", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
 
            boolean logged = ftp.login(username, password);
            if (!logged) {
                // failed to login
                ftp.disconnect();
                JOptionPane.showMessageDialog(warningPane, "Could not login to server", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if(foundFile) {
                boolean deleted = ftp.deleteFile(deletePath);
                if(!deleted) {
                    JOptionPane.showMessageDialog(warningPane, "Could not delete FTP", "Error", JOptionPane.ERROR_MESSAGE);
                    fullyDeleted = false;
                }
            }
            
            ftp.enterLocalPassiveMode();
 
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(warningPane, "IO Error: "+ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        //Delete SQL
        try {
            try {
                if(!conn.isValid(0)) {
                    SQLConnect();
                }
            } catch (SQLException e) {
                System.out.println("SQLException: " + e.getMessage());
                System.out.println("SQLState: " + e.getSQLState());
                System.out.println("VendorError: " + e.getErrorCode());
                JOptionPane.showMessageDialog(warningPane, "Critical SQL Error, restarting", "Warning", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }
            createStatement().executeUpdate("DELETE FROM inventory WHERE id="+editId);
        } catch(SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            JOptionPane.showMessageDialog(warningPane, "Critical SQL Error, restarting", "Warning", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        
        if(fullyDeleted) {
            JOptionPane.showMessageDialog(warningPane, "Item fully deleted", "Deletion Successful", JOptionPane.INFORMATION_MESSAGE);
            try {
                if(initialized && tabPane.getSelectedIndex() == 0 || tabPane.getSelectedIndex() == 1) {
                    try {
                        if(!conn.isValid(0)) {
                            SQLConnect();
                        }
                    } catch (SQLException e) {
                        System.out.println("SQLException: " + e.getMessage());
                        System.out.println("SQLState: " + e.getSQLState());
                        System.out.println("VendorError: " + e.getErrorCode());
                        JOptionPane.showMessageDialog(warningPane, "Critical SQL Error, restarting", "Warning", JOptionPane.ERROR_MESSAGE);
                        System.exit(0);
                    }
                    updateAll();
                }
            } catch(SQLException ex) {
                // handle any errors
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
                JOptionPane.showMessageDialog(warningPane, "Critical SQL Error, restarting", "Warning", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }
        } else { 
            JOptionPane.showMessageDialog(warningPane, "Failed to delete image. However, server entry was still removed", "Deletion Partially Successful", JOptionPane.INFORMATION_MESSAGE);
            try {
                try {
                    if(!conn.isValid(0)) {
                        SQLConnect();
                    }
                } catch (SQLException e) {
                    System.out.println("SQLException: " + e.getMessage());
                    System.out.println("SQLState: " + e.getSQLState());
                    System.out.println("VendorError: " + e.getErrorCode());
                    JOptionPane.showMessageDialog(warningPane, "Critical SQL Error, restarting", "Warning", JOptionPane.ERROR_MESSAGE);
                    System.exit(0);
                }
                updateAll();
            } catch(SQLException ex) {
                // handle any errors
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
                JOptionPane.showMessageDialog(warningPane, "Critical SQL Error, restarting", "Warning", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }
        }
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void setHostFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setHostFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_setHostFieldActionPerformed

    private void setServerFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setServerFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_setServerFieldActionPerformed

    private void sqlShowPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sqlShowPassActionPerformed
        if(!sqlPassShowToggle) {
            setSQLPasswordField.setEchoChar((char)0);
            sqlPassShowToggle = !sqlPassShowToggle;
        } else {
            setSQLPasswordField.setEchoChar('*');
            sqlPassShowToggle = !sqlPassShowToggle;
        }
    }//GEN-LAST:event_sqlShowPassActionPerformed

    private void ftpShowPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ftpShowPassActionPerformed
        if(!ftpPassShowToggle) {
            setFTPPasswordField.setEchoChar((char)0);
            ftpPassShowToggle = !ftpPassShowToggle;
        } else {
            setFTPPasswordField.setEchoChar('*');
            ftpPassShowToggle = !ftpPassShowToggle;
        }
    }//GEN-LAST:event_ftpShowPassActionPerformed

    private void searchByIDButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchByIDButtonActionPerformed
        int id = 0;
        try {
            id = Integer.parseInt((String)searchByIDField.getText().trim());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(warningPane, "Please enter a valid number ID", "Error", JOptionPane.ERROR_MESSAGE);
        }
        try {
            try {
                if(!conn.isValid(0)) {
                    SQLConnect();
                }
            } catch (SQLException e) {
                System.out.println("SQLException: " + e.getMessage());
                System.out.println("SQLState: " + e.getSQLState());
                System.out.println("VendorError: " + e.getErrorCode());
                JOptionPane.showMessageDialog(warningPane, "Critical SQL Error, restarting", "Warning", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }
            PreparedStatement stmt = conn.prepareStatement("SELECT id FROM inventory WHERE id=?");
            stmt.setInt(1, Integer.parseInt((String)searchByIDField.getText().trim()));
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                populateData(rs.getInt("id"));
            } else {
                JOptionPane.showMessageDialog(warningPane, "Invalid ID", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch(SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            JOptionPane.showMessageDialog(warningPane, "Critical SQL Error, restarting", "Warning", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }//GEN-LAST:event_searchByIDButtonActionPerformed

    private void tabPaneStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tabPaneStateChanged
        try {
            if(initialized && tabPane.getSelectedIndex() == 0 && newCreated) {
                try {
                    if(!conn.isValid(0)) {
                        SQLConnect();
                    }
                } catch (SQLException e) {
                    System.out.println("SQLException: " + e.getMessage());
                    System.out.println("SQLState: " + e.getSQLState());
                    System.out.println("VendorError: " + e.getErrorCode());
                    JOptionPane.showMessageDialog(warningPane, "Critical SQL Error, restarting", "Warning", JOptionPane.ERROR_MESSAGE);
                    System.exit(0);
                }   
                updateAll();
                newCreated = false;
            }
        } catch(SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            JOptionPane.showMessageDialog(warningPane, "Critical SQL Error, restarting", "Warning", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }//GEN-LAST:event_tabPaneStateChanged
    
    private void categoryPrompt() {
        String newCategory = JOptionPane.showInputDialog(warningPane, "Please enter new category", "New Category", JOptionPane.INFORMATION_MESSAGE);
            if(newCategory == null || newCategory.trim().equals("")) {
                categoryList.setSelectedIndex(0);
                return;
            }
            categoryList.addItem(newCategory);
            categoryList.setSelectedItem(newCategory);
    }
    
    private void categoryEditPrompt() {
        String newCategory = JOptionPane.showInputDialog(warningPane, "Please enter new category", "New Category", JOptionPane.INFORMATION_MESSAGE);
            if(newCategory == null || newCategory.trim().equals("")) {
                categoryListEdit.setSelectedIndex(0);
                return;
            }
            categoryListEdit.addItem(newCategory);
            categoryListEdit.setSelectedItem(newCategory);
    }
    
    private void workingComboBoxEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_workingComboBoxEditActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_workingComboBoxEditActionPerformed

    private void titleFieldEditFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_titleFieldEditFocusLost
        if(isDuplicateTitle(titleFieldEdit.getText().trim(), editTitle)) {
            JOptionPane.showMessageDialog(warningPane, "That title is already in use. Please create a different title.", "Warning", JOptionPane.ERROR_MESSAGE);
            titleFieldEdit.requestFocusInWindow();
        }
    }//GEN-LAST:event_titleFieldEditFocusLost

    private void submitNewButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitNewButtonActionPerformed
        setEnabledAllCreate(false);
        setTabs(false, CREATE_PANE_INDEX);
        //Check that fields are valid
        if(titleField.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(warningPane, "Please enter a title", "Invalid entry", JOptionPane.ERROR_MESSAGE);
            setEnabledAllCreate(true);
            setTabs(true, CREATE_PANE_INDEX);
            return;
        }
        if(isDuplicateTitle(titleField.getText().trim(), null)) {
            JOptionPane.showMessageDialog(warningPane, "That title is already in use. Please create a different title.", "Warning", JOptionPane.ERROR_MESSAGE);
            setEnabledAllCreate(true);
            setTabs(true, CREATE_PANE_INDEX);
            return;
        }
        if(descriptionTextArea.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(warningPane, "Please enter a description", "Invalid entry", JOptionPane.ERROR_MESSAGE);
            setEnabledAllCreate(true);
            setTabs(true, CREATE_PANE_INDEX);
            return;
        }
        if(selectedFile == null) {
            JOptionPane.showMessageDialog(warningPane, "Please enter an image file", "Invalid entry", JOptionPane.ERROR_MESSAGE);
            setEnabledAllCreate(true);
            setTabs(true, CREATE_PANE_INDEX);
            return;
        }
        if(categoryList.getSelectedItem().equals("New Category") || categoryList.getSelectedItem().equals("Select Category")) {
            JOptionPane.showMessageDialog(warningPane, "Please select a category", "Invalid entry", JOptionPane.ERROR_MESSAGE);
            setEnabledAllCreate(true);
            setTabs(true, CREATE_PANE_INDEX);
            return;
        }
        if(((Integer)availableSpinner.getValue() + (Integer)rentedSpinner.getValue()) == 0) {
            JOptionPane.showMessageDialog(warningPane, "Please enter a value for either #available or #rented", "Invalid entry", JOptionPane.ERROR_MESSAGE);
            setEnabledAllCreate(true);
            setTabs(true, CREATE_PANE_INDEX);
            return;
        }
        if(workingComboBox.getSelectedItem().equals("Select Item")) {
            JOptionPane.showMessageDialog(warningPane, "Please select working or nonworking", "Invalid entry", JOptionPane.ERROR_MESSAGE);
            setEnabledAllCreate(true);
            setTabs(true, CREATE_PANE_INDEX);
            return;
        }
        if(timePeriodCombo.getSelectedItem().equals("Select Period")) {
            JOptionPane.showMessageDialog(warningPane, "Please select a time period", "Invalid entry", JOptionPane.ERROR_MESSAGE);
            setEnabledAllCreate(true);
            setTabs(true, CREATE_PANE_INDEX);
            return;
        }
        String fileName = titleField.getText().toLowerCase();
        try {
            fileName = URLEncoder.encode(fileName, "UTF-8");
            fileName += "."+(FilenameUtils.getExtension(selectedFile.getAbsolutePath()).toLowerCase());
        } catch(Exception e) {
            JOptionPane.showMessageDialog(warningPane, "Error encoding title. Please try a different title", "Internal error", JOptionPane.ERROR_MESSAGE);
            setEnabledAllCreate(true);
            setTabs(true, CREATE_PANE_INDEX);
            return;
        }
        creating = true;
        boolean working;
        if(workingComboBox.getSelectedItem().equals("Working")) {
            working = true;
        } else {
            working = false;
        }
        //Add SQL entry
        try {
            try {
                if(!conn.isValid(0)) {
                    SQLConnect();
                }
            } catch (SQLException e) {
                System.out.println("SQLException: " + e.getMessage());
                System.out.println("SQLState: " + e.getSQLState());
                System.out.println("VendorError: " + e.getErrorCode());
                JOptionPane.showMessageDialog(warningPane, "Critical SQL Error, restarting", "Warning", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO inventory (title,description,category,time_period,picture_path,num_available,num_rented,working) "
                + "VALUES (?,?,?,?,?,?,?,?)");
            stmt.setString(1, titleField.getText());
            stmt.setString(2, descriptionTextArea.getText());
            stmt.setString(3, (String)categoryList.getSelectedItem());
            stmt.setInt(4, Integer.parseInt((String)timePeriodCombo.getSelectedItem()));
            stmt.setString(5, ("images/inventory/"+fileName));
            stmt.setInt(6, (Integer)availableSpinner.getValue());
            stmt.setInt(7, (Integer)rentedSpinner.getValue());
            stmt.setBoolean(8, working);
            stmt.executeUpdate();
        } catch(SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            JOptionPane.showMessageDialog(warningPane, "Critical SQL Error, restarting", "Warning", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        //Upload image via FTP
        String host = prefs.get(HOST, "127.0.0.1");
        int port = Integer.parseInt(prefs.get(PORT, "8080"));
        String username = prefs.get(USERNAME_FTP, "root");
        String password = prefs.get(PASSWORD_FTP, "");
        String uploadPath = "/images/inventory/";
        String filePath = selectedFile.getAbsolutePath();
        File uploadFile = new File(filePath);
        uploadProgressBar.setValue(0);
        UploadTask task = new UploadTask(host, port, username, password,
            uploadPath, uploadFile, fileName);
        task.addPropertyChangeListener(this);
        task.execute();
    }//GEN-LAST:event_submitNewButtonActionPerformed

    private void timePeriodComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timePeriodComboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_timePeriodComboActionPerformed

    private void categoryListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoryListActionPerformed
        if(!catUpdating && categoryList.getSelectedItem().equals("New Category")) {
            categoryPrompt();
        }
    }//GEN-LAST:event_categoryListActionPerformed

    private void descriptionTextAreaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_descriptionTextAreaKeyTyped
        String text = descriptionTextArea.getText();
        if(text.length() > 150) {
            descriptionTextArea.setText(text.substring(0, text.length() - 1));
            JOptionPane.showMessageDialog(warningPane, "Please limit descriptions to 150 characters", "Warning", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_descriptionTextAreaKeyTyped

    private void titleFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_titleFieldKeyTyped
        String text = titleField.getText();
        if(text.length() > 20) {
            JOptionPane.showMessageDialog(warningPane, "Please limit titles to 20 characters", "Warning", JOptionPane.ERROR_MESSAGE);
            titleField.setText(text.substring(0, text.length() - 1));
        }
    }//GEN-LAST:event_titleFieldKeyTyped

    private void titleFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_titleFieldFocusLost
        if(isDuplicateTitle(titleField.getText().trim(), null)) {
            JOptionPane.showMessageDialog(warningPane, "That title is already in use. Please create a different title.", "Warning", JOptionPane.ERROR_MESSAGE);
            titleField.requestFocusInWindow();
        }
    }//GEN-LAST:event_titleFieldFocusLost

    private void browseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseButtonActionPerformed
        FileFilter filter = new FileNameExtensionFilter(".png, .jpg, .jpeg", "png", "jpg", "jpeg");
        fileChooser.addChoosableFileFilter(filter);
        int returnVal = fileChooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            selectedFile = file;
            if(filter.accept(file)) {
                filePathText.setText(file.getAbsolutePath());
                try {
                    BufferedImage myPicture = ImageIO.read(file);
                    myPicture = (BufferedImage)getScaledImage(myPicture, imageLabel.getWidth(), imageLabel.getHeight());
                    imageLabel.setIcon(new ImageIcon(myPicture));
                    imageLabel.setText("");
                } catch(Exception e) {
                    JOptionPane.showMessageDialog(warningPane, "Error reading image", "Warning", JOptionPane.ERROR_MESSAGE);
                    System.err.println(e.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(warningPane, "Please select either a .png, .jpg, or .jpeg file", "Warning", JOptionPane.ERROR_MESSAGE);
                fileChooser.setCurrentDirectory(file);
                browseButtonActionPerformed(evt);
            }
        } else {
            System.out.println("File access cancelled by user.");
        }
    }//GEN-LAST:event_browseButtonActionPerformed
    
    private Image getScaledImage(Image srcImg, int w, int h){
        try {
            double imageRatio = srcImg.getWidth(rootPane)/srcImg.getHeight(rootPane);
            double labelRatio = ((double)w)/h;
            if(imageRatio >= labelRatio) {
                h = (int)(srcImg.getHeight(null)*((double)w/srcImg.getWidth(null)));
            } else {
                w = (int)(srcImg.getWidth(null)*((double)h/srcImg.getHeight(null)));
            }
        } catch(ArithmeticException e) {
            JOptionPane.showMessageDialog(warningPane, "Error with image: 0 height", "Warning", JOptionPane.ERROR_MESSAGE);
        } 
        AffineTransform trans = new AffineTransform();
        BufferedImage scaledImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = (Graphics2D)scaledImg.getGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();

        return scaledImg;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSpinner availableSpinner;
    private javax.swing.JSpinner availableSpinnerEdit;
    private javax.swing.JButton browseButton;
    private javax.swing.JButton browseButtonEdit;
    private javax.swing.JComboBox<String> categoryList;
    private javax.swing.JComboBox<String> categoryListEdit;
    private javax.swing.JPanel createPane;
    private javax.swing.JButton deleteButton;
    private javax.swing.JTextArea descriptionTextArea;
    private javax.swing.JTextArea descriptionTextAreaEdit;
    private javax.swing.JPanel editPane;
    private javax.swing.JFileChooser fileChooser;
    private javax.swing.JLabel filePathText;
    private javax.swing.JLabel filePathTextEdit;
    private javax.swing.JToggleButton ftpShowPass;
    private javax.swing.JLabel imageLabel;
    private javax.swing.JLabel imageLabelEdit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JSeparator jSeparator17;
    private javax.swing.JSeparator jSeparator18;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JPanel optionsPane;
    private javax.swing.JButton prefSaveButtonFTP;
    private javax.swing.JButton prefSaveButtonSQL;
    private javax.swing.JButton refreshButton;
    private javax.swing.JSpinner rentedSpinner;
    private javax.swing.JSpinner rentedSpinnerEdit;
    private javax.swing.JButton searchByIDButton;
    private javax.swing.JTextField searchByIDField;
    private javax.swing.JComboBox<String> selectItemComboBox;
    private javax.swing.JPasswordField setFTPPasswordField;
    private javax.swing.JTextField setFTPUsernameField;
    private javax.swing.JTextField setHostField;
    private javax.swing.JTextField setPortField;
    private javax.swing.JPasswordField setSQLPasswordField;
    private javax.swing.JTextField setSQLUsernameField;
    private javax.swing.JTextField setServerField;
    private javax.swing.JToggleButton sqlShowPass;
    private javax.swing.JButton submitNewButton;
    private javax.swing.JTabbedPane tabPane;
    private javax.swing.JComboBox<String> timePeriodCombo;
    private javax.swing.JComboBox<String> timePeriodComboEdit;
    private javax.swing.JTextField titleField;
    private javax.swing.JTextField titleFieldEdit;
    private javax.swing.JButton updateButton;
    private javax.swing.JProgressBar uploadProgressBar;
    private javax.swing.JProgressBar uploadProgressBarEdit;
    private javax.swing.JOptionPane warningPane;
    private javax.swing.JComboBox<String> workingComboBox;
    private javax.swing.JComboBox<String> workingComboBoxEdit;
    // End of variables declaration//GEN-END:variables
}
