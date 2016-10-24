package SQL;
 
import java.io.File;
import java.io.FileInputStream;
 
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
 
/**
 * Executes the file upload in a background thread and updates progress to
 * listeners that implement the java.beans.PropertyChangeListener interface.
 * @author www.codejava.net
 *
 */
public class renameTask extends SwingWorker<Void, Void> {
    private static final int BUFFER_SIZE = 4096;
     
    private String host;
    private int port;
    private String username;
    private String password;
    private String destDir;
    private String oldName;
    private String newName;
     
    public renameTask(String host, int port, String username, String password,
            String destDir, String oldName, String newName) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
        this.destDir = destDir;
        this.oldName = oldName;
        this.newName = newName;
    }
 
    /**
     * Executed in background thread
     */
    @Override
    protected Void doInBackground() throws Exception {
        FTPUtility util = new FTPUtility(host, port, username, password);
        try {
            util.connect();
            util.renameFile(destDir, oldName, newName);
             
            util.finish();
        } catch (FTPException ex) {
            JOptionPane.showMessageDialog(null, "Error renaming file: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);           
            ex.printStackTrace();;
            cancel(true);          
        } finally {
            util.disconnect();
        }
         
        return null;
    }
 
    /**
     * Executed in Swing's event dispatching thread
     */
    @Override
    protected void done() {
        if (!isCancelled()) {
            JOptionPane.showMessageDialog(null,
                    "File has been renamed successfully!", "Message",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }  
}