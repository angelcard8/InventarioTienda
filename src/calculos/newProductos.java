package calculos;

import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class newProductos extends javax.swing.JFrame {
    List idmarca= new List();
    int marca_select;
    List idplataforma= new List();
    int plataforma_select;
    List idcategoria= new List();
    int categoria_select;
    List plataformaLista=new List();
    List nomPlataforma=new List();
    public newProductos() {
        initComponents();
        this.setLocationRelativeTo(null);
    }
    conectar cc= new conectar ();
    Connection cn = cc.conexion();
    
    public void ConsultaPlataforma(){
        String sql="select * from plataforma";
        String [] registro = new String [2];
        try{
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                registro[0]= rs.getString(1);
                registro[1]= rs.getString(2);
                String valor1 = String.valueOf(registro[0]);
                String valor2 = String.valueOf(registro[1]);
                idplataforma.add(valor1);
                plataformas.addItem(valor2);
                nomPlataforma.add(valor2);
            }
            if(plataformas.getItemCount()>0){
            plataforma_select= Integer.parseInt(idplataforma.getItem(0));
            System.out.println("ptma seleccionado: "+marca_select);
            }else{
                JOptionPane.showMessageDialog(null,"No hay Plataformas registradas!!");
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }
     }
    public void ConsultaCategoria(){
        String sql="select * from categoria";
        String [] registro = new String [2];
        try{
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                registro[0]= rs.getString(1);
                registro[1]= rs.getString(2);
                String valor1 = String.valueOf(registro[0]);
                String valor2 = String.valueOf(registro[1]);
                idcategoria.add(valor1);
                categorias.addItem(valor2);
                
            }
            if(categorias.getItemCount()>0){
            categoria_select= Integer.parseInt(idcategoria.getItem(0));
            //System.out.println("ptma seleccionado: "+categoria_select);
            }else{
                JOptionPane.showMessageDialog(null,"No hay Categorías registradas!!");
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }
     }
    public void ConsultaMarca(){
        String sql="select * from marca";
        String [] registro = new String [2];
        try{
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                registro[0]= rs.getString(1);
                registro[1]= rs.getString(2);
                String valor1 = String.valueOf(registro[0]);
                String valor2 = String.valueOf(registro[1]);
                idmarca.add(valor1);
                marcas.addItem(valor2);
            }
            if(marcas.getItemCount()>0){
            marca_select= Integer.parseInt(idmarca.getItem(0));
            System.out.println("ptma seleccionado: "+marca_select);
            }else{
                JOptionPane.showMessageDialog(null,"No hay Marcas registradas!!");
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }
     }
    /*public void obtieneCategorias( int plataforma){
        marcas.removeAllItems();
        idcategoria.removeAll();
        categoria_select = 0;
        String sql="select * from categoria where idplataforma="+plataforma;
        String [] registro = new String [3];
        try{
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                registro[0]= rs.getString(1);
                registro[1]= rs.getString(2);
                String valor1 = String.valueOf(registro[0]);
                String valor2 = String.valueOf(registro[1]);
                idcategoria.add(valor1);
                marcas.addItem(valor2);
            }
            if(marcas.getItemCount()>0){
            categoria_select= Integer.parseInt(idcategoria.getItem(0));
            System.out.println("cat seleccionado: "+categoria_select);
            }else{
            JOptionPane.showMessageDialog(null,"No hay Categorias registradas!!");
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }
     }*/
    
    
    
    public void insertar() throws SQLException{
        cc.conecta.setAutoCommit(false);
        PreparedStatement pst = null;
        if((producto.getText().equals(""))||(costodolar.getText().equals(""))||(tipocambio.getText().equals(""))||(importacion.getText().equals(""))||(gastosfijos.getText().equals(""))){
            JOptionPane.showMessageDialog(null,"Todos los campos deben estar llenos");
        }
        else{
            if(categoria_select<1){
                JOptionPane.showMessageDialog(null,"Debe Seleccionar la Categoria");
            }
            else{
                if(marca_select<1){
                    JOptionPane.showMessageDialog(null,"Debe Seleccionar la Marca");
                }else{
                    if (plataformaLista.getItemCount() > 0) {
                        try {
                            pst = cn.prepareStatement("INSERT INTO producto(nombre,idmarca,idcategoria) VALUES (?,?,?)",pst.RETURN_GENERATED_KEYS);
                            pst.setString(1,producto.getText ());
                            pst.setInt(2,marca_select);
                            pst.setInt(3,categoria_select);
                            /*pst.setString(4,costodolar.getText ());
                            pst.setString(5,tipocambio.getText ());
                            pst.setString(6,importacion.getText ());
                            pst.setString(7,gastosfijos.getText ());*/  
                            int affectedRows = pst.executeUpdate();
                            if (affectedRows == 0) {
                                throw new SQLException("No se pudo guardar");
                            }
                            ResultSet generatedKeys = pst.getGeneratedKeys();
                            if (generatedKeys.next()) {
                                insertaOperaciones(generatedKeys.getInt(1));
                                JOptionPane.showMessageDialog(null,"Producto Registrado Correcamente");
                                limpiaCampos();
                                eliminaTodo();
                            }               
                            cc.conecta.commit();               
                        }catch (Exception e){
                            cc.conecta.rollback();
                            JOptionPane.showMessageDialog(null,e);
                        }finally{
                            if(pst != null){
                            pst.close();}
                        }               
                    }else{
                        JOptionPane.showMessageDialog(null, "Debe de Seleccionar Plataformas");
                    }
                }
            }
        }
      }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listPlataformas = new javax.swing.JList<String>();
        jButton5 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        plataformas = new javax.swing.JComboBox<String>();
        jButton4 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        categorias = new javax.swing.JComboBox<String>();
        jLabel2 = new javax.swing.JLabel();
        marcas = new javax.swing.JComboBox<String>();
        jLabel3 = new javax.swing.JLabel();
        producto = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        costodolar = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        tipocambio = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        importacion = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        gastosfijos = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jPanel6 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        listPlataformas.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Plataformas Seleccionadas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18))); // NOI18N
        jScrollPane1.setViewportView(listPlataformas);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 20, 280, 410));

        jButton5.setText("Eliminar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 440, 280, -1));

        jPanel2.setBackground(new java.awt.Color(255, 206, 7));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setText("Plataforma");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, -1, -1));

        jPanel2.add(plataformas, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 90, 198, -1));

        jButton4.setText("Seleccionar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 90, 130, -1));

        jLabel5.setText("Categoría");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 120, -1, -1));

        categorias.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                categoriasItemStateChanged(evt);
            }
        });
        jPanel2.add(categorias, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 120, 198, -1));

        jLabel2.setText("Marca");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 150, -1, -1));

        marcas.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                marcasItemStateChanged(evt);
            }
        });
        jPanel2.add(marcas, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 150, 198, -1));

        jLabel3.setText("Producto");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 180, -1, -1));

        producto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productoActionPerformed(evt);
            }
        });
        jPanel2.add(producto, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 180, 198, -1));

        jLabel6.setText("Costo en Dolar");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 210, -1, -1));
        jPanel2.add(costodolar, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 210, 198, -1));

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setText("$");
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 240, -1, -1));
        jPanel2.add(tipocambio, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 240, 197, -1));

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel16.setText("%");
        jPanel2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 270, -1, -1));

        importacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importacionActionPerformed(evt);
            }
        });
        jPanel2.add(importacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 270, 197, -1));

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel17.setText("$");
        jPanel2.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 300, -1, -1));
        jPanel2.add(gastosfijos, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 300, 197, -1));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton3.setText("Consultar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel7.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 320, 160, 40));

        jButton1.setText("Registrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel7.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 320, 170, 40));

        jButton2.setText("Regresar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel7.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 370, 130, 40));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Copyrigth VESHOP 2022");
        jPanel7.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 430, -1, -1));

        jLabel23.setIcon(new javax.swing.ImageIcon("C:\\Users\\angel\\Documents\\residencia lagunas\\descarga.png")); // NOI18N
        jPanel7.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 10, 170, -1));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 25)); // NOI18N
        jLabel12.setText("Agregar nuevo producto");
        jPanel7.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jLabel7.setText("Tipo de Cambio");
        jPanel7.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, -1, -1));

        jLabel8.setText("Importación");
        jPanel7.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, -1, -1));

        jLabel11.setText("Gastos Fijos");
        jPanel7.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 290, -1, -1));

        jPanel2.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 500, 460));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 530, 497));

        jLabel13.setIcon(new javax.swing.ImageIcon("C:\\Users\\angel\\Downloads\\—Pngtree—background elegant black with gold_1576413.png")); // NOI18N
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 0, 385, 497));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(102, 102, 102));
        jLabel14.setText("Contraseña:");
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, -1, -1));

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(102, 102, 102));
        jLabel18.setText("Inicia sesion");
        jPanel3.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 90, -1, -1));

        jTextField1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jTextField1.setBorder(null);
        jPanel3.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, 370, 40));

        jPanel4.setBackground(new java.awt.Color(255, 206, 7));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 370, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 370, 10));

        jPanel5.setBackground(new java.awt.Color(255, 206, 7));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 370, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 300, 370, 10));

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(102, 102, 102));
        jLabel19.setText("Inicia sesion con la cuenta de empresa");
        jPanel3.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 120, -1, -1));

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(102, 102, 102));
        jLabel20.setText("Correo:");
        jPanel3.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, -1, -1));

        jPasswordField1.setText("jPasswordField1");
        jPasswordField1.setBorder(null);
        jPanel3.add(jPasswordField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, 370, 40));

        jPanel6.setBackground(new java.awt.Color(255, 206, 7));
        jPanel6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel6.setFocusTraversalPolicyProvider(true);
        jPanel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel6MouseClicked(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Inicia sesion");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addComponent(jLabel21)
                .addContainerGap(82, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 340, 260, 40));

        jLabel22.setIcon(new javax.swing.ImageIcon("C:\\Users\\angel\\Documents\\residencia lagunas\\descarga.png")); // NOI18N
        jPanel3.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 20, -1, -1));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 30, 450, 440));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 848, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 479, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
       ConsultaPlataforma();
       ConsultaCategoria();
       ConsultaMarca();
    }//GEN-LAST:event_formWindowOpened

    private void marcasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_marcasItemStateChanged
        if(idmarca.getItemCount()>0){
            marca_select= Integer.parseInt(idmarca.getItem(marcas.getSelectedIndex()));
            //obtieneCategorias(plataforma_select);
        }
    }//GEN-LAST:event_marcasItemStateChanged

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        tablaProductos a = new tablaProductos(0,"");
        a.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            insertar();
        } catch (SQLException ex) {
            Logger.getLogger(newProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void categoriasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_categoriasItemStateChanged
        if(idcategoria.getItemCount()>0){
            categoria_select= Integer.parseInt(idcategoria.getItem(categorias.getSelectedIndex()));
        }
    }//GEN-LAST:event_categoriasItemStateChanged

    private void importacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_importacionActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        boolean existe = false;
        for(int i=0; i<plataformaLista.getItemCount();i++)
        {
            int aux=Integer.parseInt(idplataforma.getItem(plataformas.getSelectedIndex()));
            if(aux == Integer.parseInt(plataformaLista.getItem(i))){
                existe=true;
                JOptionPane.showMessageDialog(null,"Ya Has Seleccionado este elemento");
                return;
            }
        }
        if(existe==false){
            plataformaLista.add(idplataforma.getItem(plataformas.getSelectedIndex()));
            //peso.add(txt_peso.getText());
            Mostrar();
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Plataformas a = new Plataformas();
        a.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void productoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_productoActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        if(plataformaLista.getItemCount()>0){

            if(listPlataformas.getSelectedIndex()>=0){
                int resp=JOptionPane.showConfirmDialog(null,"¿Esta seguro de Eliminar la Plataforma seleccionada ?","Mensaje de Confirmacion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if(resp==JOptionPane.YES_OPTION){
                    plataformaLista.remove(listPlataformas.getSelectedIndex());
                    Mostrar();
                }
            }else{
                JOptionPane.showMessageDialog(null,"Debe de seleccionar una Plataforma a eliminar");
            }
        }else{
            JOptionPane.showMessageDialog(null,"No hay Plataformas Seleccionadas");
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jPanel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel6MouseClicked
        Plataformas a = new Plataformas();
        a.setVisible(true);
        dispose();

    }//GEN-LAST:event_jPanel6MouseClicked

    public void Mostrar(){
        DefaultListModel caracteristiticas = new DefaultListModel();
        for(int i=0;i<plataformaLista.getItemCount();i++){
            caracteristiticas.addElement(nomPlataforma.getItem(Integer.parseInt(plataformaLista.getItem(i))-1));
        }
        listPlataformas.setModel(caracteristiticas);
    }
    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(newProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(newProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(newProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(newProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new newProductos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> categorias;
    private javax.swing.JTextField costodolar;
    private javax.swing.JTextField gastosfijos;
    private javax.swing.JTextField importacion;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
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
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JList<String> listPlataformas;
    private javax.swing.JComboBox<String> marcas;
    private javax.swing.JComboBox<String> plataformas;
    private javax.swing.JTextField producto;
    private javax.swing.JTextField tipocambio;
    // End of variables declaration//GEN-END:variables

    private void insertaOperaciones(int id) throws SQLException{
        String aux = "";
        for (int i = 0; i < plataformaLista.getItemCount(); i++)
        {
            if(i==0){
                aux=aux+"("+id+","+plataformaLista.getItem(i)+","+costodolar.getText()+","+tipocambio.getText()+","+importacion.getText()+","+gastosfijos.getText()+")";
            }else{
                aux=aux+",("+id+","+plataformaLista.getItem(i)+","+costodolar.getText()+","+tipocambio.getText()+","+importacion.getText()+","+gastosfijos.getText()+")";
            }
        }
        PreparedStatement pst = null;
            try {
                pst = cn.prepareStatement("INSERT INTO operacionesproducto(idproducto,idplataforma,costo_dolar,tipo_cambio,importacion,gastos_fijos) VALUES"+aux);
                pst.executeUpdate();                
               
            }catch (Exception e){

                JOptionPane.showMessageDialog(null,e);
            }finally{
                if(pst != null){
                    pst.close();}
            }
        }

    private void eliminaTodo() {
       plataformaLista.removeAll();
       Mostrar();
    }

    private void limpiaCampos() {
       producto.setText("");
       costodolar.setText("");
       tipocambio.setText("");
       importacion.setText("");
       gastosfijos.setText("");
    }
}
