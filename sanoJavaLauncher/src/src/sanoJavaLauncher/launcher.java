/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanoJavaLauncher;

import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileSystemView;
import javax.swing.plaf.metal.MetalIconFactory;
import sano.Node;
import sano.RSML;
import sano.TextFile;

/**
 *
 * @author HowariS
 */
public class launcher extends javax.swing.JDialog {

    /**
     * Creates new form launcher
     */
    Dimension screenSize = null;
    Dimension mySize = null;
    Dimension myHiddenSize = null;

    boolean keep = false;
    boolean hidden = true;
    int delay = 1000; //milliseconds
    ActionListener taskPerformer = (ActionEvent evt) -> {
        realign();
    };

    Timer t = new Timer(delay, taskPerformer);
    Map<Object, Icon> icons = null;
    List<String> jListText = null;
    List<String> types = null;
    List<String> links = null;
    String desktopPath = System.getProperty("user.home");
    MyListRenderer ren = new MyListRenderer();

    public launcher() {
        this.setUndecorated(true);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(launcher.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(launcher.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(launcher.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(launcher.class.getName()).log(Level.SEVERE, null, ex);
        }
        icons = new HashMap<Object, Icon>();
        Image img = null;
        try {
            int ww = 40;
            int hh = 40;
            img = ImageIO.read(ClassLoader.getSystemResource("sanoJavaLauncher/res/app.png"));
            icons.put("application", toIcon(img, ww, hh));
            img = ImageIO.read(ClassLoader.getSystemResource("sanoJavaLauncher/res/dir.png"));
            icons.put("folder", toIcon(img, ww, hh));
            img = ImageIO.read(ClassLoader.getSystemResource("sanoJavaLauncher/res/web.png"));
            icons.put("webpage", toIcon(img, ww, hh));
            img = ImageIO.read(ClassLoader.getSystemResource("sanoJavaLauncher/res/home.png"));
            icons.put("home", toIcon(img, ww, hh));
        } catch (IOException ex) {
            Logger.getLogger(launcher.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error while loading icons!");
        }
        ren.icons = icons;
        initComponents();
        this.setOpacity((float) 1);
        loadList();
        setFocusable(true);
        realign();
        t.start();
    }

    void realign() {
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        mySize = new Dimension();
        mySize.height = screenSize.height;
        mySize.width = (int) (screenSize.width / 5) + 10;
        myHiddenSize = new Dimension();
        myHiddenSize.height = (int) (screenSize.height / 25);
        myHiddenSize.width = (int) (screenSize.width / 25);

        if (hidden) {
            this.setSize(myHiddenSize);
            this.setLocation(screenSize.width - 10, 0);
            this.setOpacity(((float) (0.01)));
        } else {
            this.setSize(mySize);
            this.setLocation(screenSize.width - mySize.width, 0);
            this.setOpacity(1);
        }
    }

    ImageIcon toIcon(Image img, int w, int h) {
        Image resImg = resizeImage(img, w, h);
        ImageIcon ic = new ImageIcon(resImg);
        return ic;
    }

    void wait(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ex) {
            Logger.getLogger(launcher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 430, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 298, Short.MAX_VALUE)
        );

        setAlwaysOnTop(true);
        setModalExclusionType(null);
        setResizable(false);
        setSize(new java.awt.Dimension(400, 300));
        setType(java.awt.Window.Type.POPUP);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        jList1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jList1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jList1.setSelectionBackground(new java.awt.Color(195, 195, 195));
        jList1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jList1MouseMoved(evt);
            }
        });
        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jList1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jList1MouseExited(evt);
            }
        });
        jList1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jList1KeyPressed(evt);
            }
        });
        jList1.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList1ValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jList1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

    }//GEN-LAST:event_formWindowOpened

    private void jList1ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList1ValueChanged

    }//GEN-LAST:event_jList1ValueChanged

    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked
        if (evt.getClickCount() == 2) {
            runIt();
        }
    }//GEN-LAST:event_jList1MouseClicked

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        AnswerKeyboard(evt);
    }//GEN-LAST:event_formKeyPressed

    private void jList1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jList1KeyPressed
        AnswerKeyboard(evt);
    }//GEN-LAST:event_jList1KeyPressed
    int activeSpotSize = 50;
    private void jList1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseEntered

        if (evt.getXOnScreen() > screenSize.width - myHiddenSize.width && evt.getYOnScreen() < myHiddenSize.width) {
            SHOW();
        }
    }//GEN-LAST:event_jList1MouseEntered

    private void jList1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseExited
        if (evt.getXOnScreen() < screenSize.width - 2 && evt.getYOnScreen() > 2) {
            HIDE();
        }
    }//GEN-LAST:event_jList1MouseExited

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
    }//GEN-LAST:event_formWindowActivated

    private void jList1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseMoved
    }//GEN-LAST:event_jList1MouseMoved

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog jDialog1;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    void AnswerKeyboard(java.awt.event.KeyEvent evt) {
        char c = (char) evt.getKeyChar();
        int ic = (int) evt.getKeyCode();
        if (('x' == c || 'X' == c || 'q' == c || 'Q' == c) && evt.isAltDown()) {
            System.exit(0);
        } else if (ic == KeyEvent.VK_F5) {
            loadList();
        } else if (ic == KeyEvent.VK_F1) {
            ShowConfig();
        } else if (ic == KeyEvent.VK_F4 && evt.isAltDown()) {
            System.exit(0);
        } else if (('s' == c || 'S' == c) && evt.isAltDown()) {
            SHOW();
        } else if (('h' == c || 'H' == c) && evt.isAltDown()) {
            HIDE();
        } else if (('k' == c || 'K' == c) && evt.isAltDown()) {
            keep = !keep;
        } else if (ic == KeyEvent.VK_ESCAPE) {
            HIDE();
        } else if (ic == KeyEvent.VK_ENTER) {
            runIt();
        }
    }

    void ShowConfig() {

        String links = desktopPath + "\\links.rsml";
        try {
            Runtime.getRuntime().exec("explorer.exe " + links);
        } catch (IOException ex) {
        }
    }

    void SHOW() {
        hidden = false;
        realign();
    }

    void HIDE() {
        if (keep) {
            return;
        }
        hidden = true;
        realign();
    }

    void runIt() {

        String path = "";
        String type = "";
        try {
            path = links.get(jList1.getSelectedIndex());
            type = types.get(jList1.getSelectedIndex());
        } catch (Exception e) {
        }

        switch (type) {

            case "a":
                try {

                    Runtime.getRuntime().exec(path);
                } catch (IOException ex) {
                }
                break;
            case "f":
                try {
                    Runtime.getRuntime().exec("explorer.exe " + path);
                } catch (IOException ex) {
                }
                break;
            case "w":
                try {
                    Desktop.getDesktop().browse(new URL(path).toURI());
                } catch (Exception e) {
                    //e.printStackTrace();
                }

                break;
        }
    }

    void loadList() {
        ren.mutex = true;
        jListText = new ArrayList<String>();
        types = new ArrayList<String>();
        links = new ArrayList<String>();
        TextFile t = null;
        String linksFile = desktopPath + "\\links.rsml";
        File tmpDir = new File(linksFile);
        boolean exists = tmpDir.exists();
        if (!exists) {
            try {
                sano.TextFile f = new sano.TextFile();
                f.open(linksFile, TextFile.Type.WRITE);
                f.writeLine("-r 	text = 'My shortcuts'\n\ttype='t'\n[]\n");
                f.writeLine("-r 	text = 'Google Chrome'\n\ttype='a'\n\tpath='C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe'\n[]\n");
                f.writeLine("-r 	text = 'Harddisk C:'\n\ttype='f'\n\tpath='C:\\'\n[]\n");
                f.writeLine("-r 	text = 'Linux.com'\n\ttype='w'\n\tpath='http://www.linux.com'\n[]\n");
                f.close();
            } catch (IOException ex) {
                Logger.getLogger(launcher.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        try {
            RSML rsml = new RSML();
            rsml.read(desktopPath + "\\links.rsml");
            Node n = null;
            for (int i = 0; i < rsml.root.children.size(); i++) {
                n = rsml.root.children.get(i);
                String type = n.getAttributeByName(n, "type").toString();
                links.add(n.getAttributeByName(n, "path").toString());
                if (type.compareToIgnoreCase("W") == 0) {
                    jListText.add("<html>" + "<p><u>" + n.getAttributeByName(n, "text").toString() + "</u></p></html>");
                    type = "w";
                } else if (type.compareToIgnoreCase("T") == 0) {
                    jListText.add("<html>" + "<p><b>" + n.getAttributeByName(n, "text").toString() + "</b></p></html>");
                    type = "t";
                } else if (type.compareToIgnoreCase("A") == 0) {
                    jListText.add("<html>" + "<p>" + n.getAttributeByName(n, "text").toString() + "</p></html>");
                    type = "a";
                } else if (type.compareToIgnoreCase("F") == 0) {
                    jListText.add("<html>" + "<p>" + n.getAttributeByName(n, "text").toString() + "</p></html>");
                    type = "f";
                }
                types.add(type);
            }

            ren.types = types;
            ren.links = links;
            jList1.setCellRenderer(ren);

            String[] text = new String[jListText.size()];
            text = jListText.toArray(text);
            jList1.setListData(text);
            //jList1.setFixedCellHeight( 45);
            ren.mutex = false;
        } catch (IOException ex) {
        }

    }

    private Image resizeImage(Image srcImg, int w, int h) {
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();

        return resizedImg;
    }

}

class MyListRenderer extends DefaultListCellRenderer {

    public boolean mutex = false;
    public List<String> types = null;
    public List<String> links = null;

    public Map<Object, Icon> icons = null;

    public Component getListCellRendererComponent(JList list,
            Object value, int index, boolean isSelected,
            boolean cellHasFocus) {

        super.getListCellRendererComponent(list, value, index,
                isSelected, cellHasFocus);

        if (mutex) {
            return this;
        }

        JLabel label
                = (JLabel) super.getListCellRendererComponent(list,
                        value, index, isSelected, cellHasFocus);
        Icon icon = null;
        String s = links.get(index);
        File file = new File(s);

        switch (types.get(index)) {
            case "t":
                setBackground(new Color(0xaa, 0x00, 0x00));
                icon = icons.get("home");
                setForeground(Color.white);
                setFont(new Font("Arial", Font.ITALIC + Font.BOLD, 14));
                setPreferredSize(new Dimension(100, 40));
                break;
            case "f":
                setForeground(new Color(0x40, 0x40, 0x40));
                icon = icons.get("folder");
                setFont(new Font("Arial", Font.PLAIN, 10));
                setPreferredSize(new Dimension(100, 40));
                break;
            case "w":
                setForeground(new Color(0, 0x95, 0xff));
                icon = icons.get("webpage");
                setFont(new Font("Arial", Font.ROMAN_BASELINE, 10));
                setPreferredSize(new Dimension(100, 40));
                break;
            case "a":
                setForeground(new Color(0x40, 0x40, 0x40));
                setFont(new Font("Arial", Font.PLAIN, 10));
                icon = icons.get("application");
                setPreferredSize(new Dimension(100, 40));
                break;
            default:
                setForeground(Color.lightGray);
                break;

        }

        label.setIcon(icon);

        return (this);
    }
}
