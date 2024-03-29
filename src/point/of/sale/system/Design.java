/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package point.of.sale.system;

import works.Sell;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JPanel;
import works.About;
import works.Customer;
import works.Product;
import works.Report;

/**
 *
 * @author Anonymous
 */
public class Design extends javax.swing.JFrame {

    /**
     * Creates new form Design
     */
    public Design() {
        
        initComponents();
        initialDesign();
        callSell();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        parent = new javax.swing.JPanel();
        sidebar = new javax.swing.JPanel();
        btn_sell_menu = new javax.swing.JPanel();
        ind_sell = new javax.swing.JPanel();
        icon_sell = new javax.swing.JLabel();
        label_sell = new javax.swing.JLabel();
        app_icon = new javax.swing.JLabel();
        btn_products_menu = new javax.swing.JPanel();
        ind_products = new javax.swing.JPanel();
        icon_products = new javax.swing.JLabel();
        label_products = new javax.swing.JLabel();
        btn_customers_menu = new javax.swing.JPanel();
        ind_customers = new javax.swing.JPanel();
        icon_customers = new javax.swing.JLabel();
        label_customers = new javax.swing.JLabel();
        btn_reports_menu = new javax.swing.JPanel();
        ind_reports = new javax.swing.JPanel();
        icon_reports = new javax.swing.JLabel();
        label_reports = new javax.swing.JLabel();
        btn_about_menu = new javax.swing.JPanel();
        ind_about = new javax.swing.JPanel();
        icon_about = new javax.swing.JLabel();
        label_about = new javax.swing.JLabel();
        panelMainScreen = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Point Of Sale System");
        setIconImages(null);
        setLocation(new java.awt.Point(100, 30));
        setName("MainFrame"); // NOI18N
        setResizable(false);

        parent.setBackground(new java.awt.Color(255, 255, 255));

        sidebar.setBackground(new java.awt.Color(76, 55, 217));

        btn_sell_menu.setBackground(new java.awt.Color(76, 55, 217));
        btn_sell_menu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_sell_menuMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_sell_menuMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_sell_menuMousePressed(evt);
            }
        });
        btn_sell_menu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ind_sell.setOpaque(false);
        ind_sell.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        btn_sell_menu.add(ind_sell, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 50));

        icon_sell.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        icon_sell.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/home.png"))); // NOI18N
        btn_sell_menu.add(icon_sell, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 50, 50));

        label_sell.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        label_sell.setForeground(new java.awt.Color(255, 255, 255));
        label_sell.setText("Sell");
        btn_sell_menu.add(label_sell, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 110, 30));

        app_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Icon2.png"))); // NOI18N

        btn_products_menu.setBackground(new java.awt.Color(76, 55, 217));
        btn_products_menu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_products_menuMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_products_menuMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_products_menuMousePressed(evt);
            }
        });
        btn_products_menu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ind_products.setOpaque(false);
        ind_products.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        btn_products_menu.add(ind_products, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 50));

        icon_products.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        icon_products.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/products.png"))); // NOI18N
        btn_products_menu.add(icon_products, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 50, 50));

        label_products.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        label_products.setForeground(new java.awt.Color(255, 255, 255));
        label_products.setText("Products");
        btn_products_menu.add(label_products, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 110, 30));

        btn_customers_menu.setBackground(new java.awt.Color(76, 55, 217));
        btn_customers_menu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_customers_menuMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_customers_menuMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_customers_menuMousePressed(evt);
            }
        });
        btn_customers_menu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ind_customers.setOpaque(false);
        ind_customers.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        btn_customers_menu.add(ind_customers, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 50));

        icon_customers.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        icon_customers.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/customer.png"))); // NOI18N
        btn_customers_menu.add(icon_customers, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 50, 50));

        label_customers.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        label_customers.setForeground(new java.awt.Color(255, 255, 255));
        label_customers.setText("Customers");
        btn_customers_menu.add(label_customers, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 110, 30));

        btn_reports_menu.setBackground(new java.awt.Color(76, 55, 217));
        btn_reports_menu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_reports_menuMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_reports_menuMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_reports_menuMousePressed(evt);
            }
        });
        btn_reports_menu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ind_reports.setOpaque(false);
        ind_reports.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        btn_reports_menu.add(ind_reports, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 50));

        icon_reports.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        icon_reports.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/report.png"))); // NOI18N
        btn_reports_menu.add(icon_reports, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 50, 50));

        label_reports.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        label_reports.setForeground(new java.awt.Color(255, 255, 255));
        label_reports.setText("Reports");
        btn_reports_menu.add(label_reports, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 110, 30));

        btn_about_menu.setBackground(new java.awt.Color(76, 55, 217));
        btn_about_menu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_about_menuMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_about_menuMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_about_menuMousePressed(evt);
            }
        });
        btn_about_menu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ind_about.setOpaque(false);
        ind_about.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        btn_about_menu.add(ind_about, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 50));

        icon_about.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        icon_about.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/about.png"))); // NOI18N
        btn_about_menu.add(icon_about, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 50, 50));

        label_about.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        label_about.setForeground(new java.awt.Color(255, 255, 255));
        label_about.setText("About");
        btn_about_menu.add(label_about, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 110, 30));

        javax.swing.GroupLayout sidebarLayout = new javax.swing.GroupLayout(sidebar);
        sidebar.setLayout(sidebarLayout);
        sidebarLayout.setHorizontalGroup(
            sidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn_sell_menu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_customers_menu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_reports_menu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_about_menu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_products_menu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(sidebarLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(app_icon)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        sidebarLayout.setVerticalGroup(
            sidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidebarLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(app_icon, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_sell_menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_products_menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addComponent(btn_customers_menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_reports_menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_about_menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(105, Short.MAX_VALUE))
        );

        panelMainScreen.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panelMainScreenLayout = new javax.swing.GroupLayout(panelMainScreen);
        panelMainScreen.setLayout(panelMainScreenLayout);
        panelMainScreenLayout.setHorizontalGroup(
            panelMainScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 836, Short.MAX_VALUE)
        );
        panelMainScreenLayout.setVerticalGroup(
            panelMainScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout parentLayout = new javax.swing.GroupLayout(parent);
        parent.setLayout(parentLayout);
        parentLayout.setHorizontalGroup(
            parentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(parentLayout.createSequentialGroup()
                .addComponent(sidebar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelMainScreen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        parentLayout.setVerticalGroup(
            parentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sidebar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelMainScreen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(parent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(parent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_sell_menuMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_sell_menuMousePressed
        // TODO add your handling code here:
        setColor(btn_sell_menu);
        resetColor(btn_customers_menu);
        resetColor(btn_products_menu);
        resetColor(btn_reports_menu);
        resetColor(btn_about_menu);
        
        ind_reports.setOpaque(false);
        ind_sell.setOpaque(true);
        ind_customers.setOpaque(false);
        ind_products.setOpaque(false);
        ind_about.setOpaque(false);
        
        callSell();
        
        
    }//GEN-LAST:event_btn_sell_menuMousePressed

    private void btn_products_menuMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_products_menuMousePressed
        // TODO add your handling code here:
        setColor(btn_products_menu);
        resetColor(btn_customers_menu);
        resetColor(btn_sell_menu);
        resetColor(btn_reports_menu);
        resetColor(btn_about_menu);
        
        ind_reports.setOpaque(false);
        ind_sell.setOpaque(false);
        ind_customers.setOpaque(false);
        ind_products.setOpaque(true);
        ind_about.setOpaque(false);
        
        callProducts();
        
    }//GEN-LAST:event_btn_products_menuMousePressed

    final void initialDesign(){
        // TODO add your handling code here:
        
        setColor(btn_sell_menu);
        resetColor(btn_customers_menu);
        resetColor(btn_products_menu);
        resetColor(btn_reports_menu);
        resetColor(btn_about_menu);
        ind_reports.setOpaque(false);
        ind_sell.setOpaque(true);
        ind_customers.setOpaque(false);
        ind_products.setOpaque(false);
        ind_about.setOpaque(false);
        
    }
    
    private void btn_customers_menuMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_customers_menuMousePressed
        // TODO add your handling code here:
        resetColor(btn_products_menu);
        setColor(btn_customers_menu);
        resetColor(btn_sell_menu);
        resetColor(btn_reports_menu);
        resetColor(btn_about_menu);
        ind_reports.setOpaque(false);
        ind_sell.setOpaque(false);
        ind_customers.setOpaque(true);
        ind_products.setOpaque(false);
        ind_about.setOpaque(false);
        
        callCustomers();
        
    }//GEN-LAST:event_btn_customers_menuMousePressed

    private void btn_sell_menuMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_sell_menuMouseEntered
        // TODO add your handling code here:
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        
    }//GEN-LAST:event_btn_sell_menuMouseEntered

    private void btn_sell_menuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_sell_menuMouseExited
        // TODO add your handling code here:
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_btn_sell_menuMouseExited

    private void btn_products_menuMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_products_menuMouseEntered
        // TODO add your handling code here:
       setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btn_products_menuMouseEntered

    private void btn_products_menuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_products_menuMouseExited
        // TODO add your handling code here:
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_btn_products_menuMouseExited

    private void btn_customers_menuMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_customers_menuMouseEntered
        // TODO add your handling code here:
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btn_customers_menuMouseEntered

    private void btn_customers_menuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_customers_menuMouseExited
        // TODO add your handling code here:
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_btn_customers_menuMouseExited

    private void btn_reports_menuMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_reports_menuMouseEntered
        // TODO add your handling code here:
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btn_reports_menuMouseEntered

    private void btn_reports_menuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_reports_menuMouseExited
        // TODO add your handling code here:
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_btn_reports_menuMouseExited

    private void btn_reports_menuMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_reports_menuMousePressed
        
        resetColor(btn_products_menu);
        resetColor(btn_customers_menu);
        resetColor(btn_sell_menu);
        resetColor(btn_about_menu);
        setColor(btn_reports_menu);
        ind_reports.setOpaque(true);
        ind_sell.setOpaque(false);
        ind_customers.setOpaque(false);
        ind_products.setOpaque(false);
        ind_about.setOpaque(false);
        
        callReports();
    }//GEN-LAST:event_btn_reports_menuMousePressed

    private void btn_about_menuMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_about_menuMouseEntered
        // TODO add your handling code here:
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btn_about_menuMouseEntered

    private void btn_about_menuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_about_menuMouseExited
        // TODO add your handling code here:
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_btn_about_menuMouseExited

    private void btn_about_menuMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_about_menuMousePressed
        // TODO add your handling code here:
        
        resetColor(btn_products_menu);
        resetColor(btn_customers_menu);
        resetColor(btn_sell_menu);
        setColor(btn_about_menu);
        resetColor(btn_reports_menu);
        ind_reports.setOpaque(false);
        ind_sell.setOpaque(false);
        ind_customers.setOpaque(false);
        ind_products.setOpaque(false);
        ind_about.setOpaque(true);
        
        callAbout();
    }//GEN-LAST:event_btn_about_menuMousePressed

    
    
    /**
     * This Method is for Sell Button
     */
    final void callSell(){
        
        panelMainScreen.removeAll();
        Sell obj = null;
        if(obj==null){
            obj = new Sell();
            obj.makeSellTable(panelMainScreen);
            obj.makeProductToSell(panelMainScreen);
            obj.makeCustomerVoucher(panelMainScreen);
        }
        
        else{
            obj.makeSellTable(panelMainScreen);
            obj.makeProductToSell(panelMainScreen);
            obj.makeCustomerVoucher(panelMainScreen);
        }
        panelMainScreen.updateUI();
        
    }
    
    /**
     * This Method is for Products Button
     */
    void callProducts(){
        panelMainScreen.removeAll();
        Product obj = null;
        if(obj==null){
            obj = new Product();
            obj.makeProductDesign(panelMainScreen);
            obj.makeProductTable(panelMainScreen);
        }
        else{
            obj.makeProductDesign(panelMainScreen);
            obj.makeProductTable(panelMainScreen);
        }
        panelMainScreen.updateUI();
    }
    
    
    /**
     * This Method is for Customers Button
     */
    void callCustomers(){
        panelMainScreen.removeAll();
        
        Customer obj = null;
        if(obj==null){
            obj = new Customer();
            obj.makeCustomerPanelDesign(panelMainScreen);
            obj.makeCustomerTable(panelMainScreen);
            obj.makeRightTable(panelMainScreen);
        }
        else{
            obj.makeCustomerPanelDesign(panelMainScreen);
            obj.makeCustomerTable(panelMainScreen);
            obj.makeRightTable(panelMainScreen);
        }
        
        
        panelMainScreen.updateUI();
    }
    
    /**
     * This Method is for Products Button
     */
    void callAbout(){
        panelMainScreen.removeAll();
        About obj = null;
        if(obj==null){
            obj = new About();
            obj.makeAboutPanel(panelMainScreen);
        }
        else{
            obj.makeAboutPanel(panelMainScreen);
        }
        panelMainScreen.updateUI();
    }
    
    /**
     * This Method is for Reports Button
     */
    void callReports(){
        panelMainScreen.removeAll();
        
        Report obj = null;
        if(obj==null){
            obj = new Report();
            obj.makeReportDesign(panelMainScreen);
        }
        else{
            obj.makeReportDesign(panelMainScreen);
        }
        panelMainScreen.updateUI();
    }
    
    
    
  
    
    //set and reset color
    void setColor(JPanel panel){
        panel.setBackground(new Color(135,112,225));
    }
    
    void resetColor(JPanel panel){
        panel.setBackground(new Color(76,41,211));
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Design.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Design.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Design.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Design.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Design().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel app_icon;
    private javax.swing.JPanel btn_about_menu;
    private javax.swing.JPanel btn_customers_menu;
    private javax.swing.JPanel btn_products_menu;
    private javax.swing.JPanel btn_reports_menu;
    private javax.swing.JPanel btn_sell_menu;
    private javax.swing.JLabel icon_about;
    private javax.swing.JLabel icon_customers;
    private javax.swing.JLabel icon_products;
    private javax.swing.JLabel icon_reports;
    private javax.swing.JLabel icon_sell;
    private javax.swing.JPanel ind_about;
    private javax.swing.JPanel ind_customers;
    private javax.swing.JPanel ind_products;
    private javax.swing.JPanel ind_reports;
    private javax.swing.JPanel ind_sell;
    private javax.swing.JLabel label_about;
    private javax.swing.JLabel label_customers;
    private javax.swing.JLabel label_products;
    private javax.swing.JLabel label_reports;
    private javax.swing.JLabel label_sell;
    private javax.swing.JPanel panelMainScreen;
    private javax.swing.JPanel parent;
    private javax.swing.JPanel sidebar;
    // End of variables declaration//GEN-END:variables

    
}
