package calculos;

import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class eliminaProductos extends javax.swing.JFrame {

    List idplataforma= new List();
    List idcategoria= new List();
    List idmarca = new List();
    
    List plataforma_id= new List();
    int plataforma_select,categoria_select;
    String nombreAux;
    boolean sinFiltroPlataforma=false;
    boolean sinFiltroCategoria=false;
    String querySql;
    public eliminaProductos() {
        initComponents();
        this.setLocationRelativeTo(null);
        String sql="select p.idproducto,o.idplataforma,p.nombre,c.nombre as categoria,m.nombre as marca,o.costo_dolar,o.costoPesos,o.envioPlataforma,o.porcentajeComision,o.pesosComision,o.precioconIva,o.utilidadPesos,o.utilidadPorcentaje from producto p, marca m,operacionesproducto o,categoria c where m.idmarca=p.idmarca and p.idproducto=o.idproducto and p.idcategoria=c.idcategoria";
        mostrardatos(sql);
        ConsultaPlataforma();
        ConsultaCategoria();
        ConsultaMarca();    
    }
    conectar cc= new conectar ();
    Connection cn = cc.conexion();
    
    public void mostrardatos(String query){
    plataforma_id.removeAll();
    System.out.println(query);
    //String sql="select p.idproducto,p.nombre,m.nombre,p.costo_dolar,p.tipo_cambio,p.importacion,p.gastos_fijos from producto p, marca m where m.idmarca=p.idmarca;";
    String [] titulos={"idprod","Nombre","Costo Dolar","Costo Pesos","Envío Plataforma","Comision %","Comision $","Precio con IVA","Utilidad Neta $","Utilidad Neta %"};
    String [] registros = new String[11];
    DefaultTableModel modelo=new DefaultTableModel(null,titulos);
    try{
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery(query);
        while(rs.next()){
                 registros[0]= rs.getString(1);
                 plataforma_id.add(rs.getString(2));
                 registros[1]= rs.getString(3);
                 registros[2]= rs.getString(6);
                 registros[3]= rs.getString(7);
                 registros[4]= rs.getString(8);
                 registros[5]= rs.getString(9);
                 registros[6]= rs.getString(10);
                 registros[7]= rs.getString(11);
                 registros[8]= rs.getString(12);
                 registros[9]= rs.getString(13);
                 //registros[10]= rs.getString(11);
                 modelo.addRow(registros);
        }
        tabla_productos.setModel(modelo);  
    }
    catch(Exception e){
        JOptionPane.showMessageDialog(null,e);
    }
    }
    public void ConsultaPlataforma(){
        String sql="select * from plataforma";
        String [] registro = new String [2];
        try{
            //filtroPlataformas.addItem("Todas las Plataformas");
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                registro[0]= rs.getString(1);
                registro[1]= rs.getString(2);
                String valor1 = String.valueOf(registro[0]);
                String valor2 = String.valueOf(registro[1]);
                idplataforma.add(valor1);
                filtroPlataformas.addItem(valor2);
                
            }
            if(filtroPlataformas.getItemCount()>1){
            plataforma_select= Integer.parseInt(idplataforma.getItem(0));
            System.out.println("desde tablaProductos seleccionado: "+plataforma_select);
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
                filtroCategorias.addItem(valor2);
            }
            if(filtroCategorias.getItemCount()>1){
            //categoria_select= Integer.parseInt(idcategoria.getItem(0));
            //System.out.println("ptma seleccionado: "+marca_select);
            }else{
                JOptionPane.showMessageDialog(null,"No hay Categorias registradas!!");
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
                filtroMarcas.addItem(valor2);
            }
            if(filtroMarcas.getItemCount()>1){
            //categoria_select= Integer.parseInt(idcategoria.getItem(0));
            //System.out.println("ptma seleccionado: "+marca_select);
            }else{
                JOptionPane.showMessageDialog(null,"No hay Categorias registradas!!");
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }
     }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        filtroPlataformas = new javax.swing.JComboBox<String>();
        jButton3 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        filtroCategorias = new javax.swing.JComboBox<String>();
        jLabel3 = new javax.swing.JLabel();
        filtroMarcas = new javax.swing.JComboBox<String>();
        jLabel4 = new javax.swing.JLabel();
        lbl_plataforma = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_productos = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/calculos/Logopng.png"))); // NOI18N

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Filtros de Busqueda"));

        filtroPlataformas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Todas las plataformas" }));
        filtroPlataformas.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                filtroPlataformasItemStateChanged(evt);
            }
        });

        jButton3.setText("Buscar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel2.setText("Plataformas");

        filtroCategorias.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Todas la categorías" }));
        filtroCategorias.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                filtroCategoriasItemStateChanged(evt);
            }
        });

        jLabel3.setText("Categorías");

        filtroMarcas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Todas las marcas" }));

        jLabel4.setText("Marcas");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(filtroPlataformas, 0, 288, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addComponent(filtroCategorias, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addComponent(filtroMarcas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel4))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filtroPlataformas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filtroCategorias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(filtroMarcas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3))
        );

        lbl_plataforma.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbl_plataforma.setText("plataforma");

        tabla_productos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {}
            },
            new String [] {

            }
        ));
        tabla_productos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_productosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla_productos);

        jButton1.setText("REGRESAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton4.setText("ELIMINAR");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(287, 287, 287))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(114, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(77, 77, 77))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(lbl_plataforma)
                        .addGap(573, 573, 573))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(356, 356, 356))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(171, 414, Short.MAX_VALUE)
                        .addComponent(lbl_plataforma)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton4)
                                .addGap(108, 108, 108))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)))))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void filtroPlataformasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_filtroPlataformasItemStateChanged
        if(filtroPlataformas.getItemCount()>1){
            System.out.println("item plat: "+filtroPlataformas.getSelectedIndex());
            if(filtroPlataformas.getSelectedIndex()==0){
                sinFiltroPlataforma=true;
                //System.out.println("sin filtro plataforma");

            }else{
                sinFiltroPlataforma=false;
                plataforma_select= Integer.parseInt(idplataforma.getItem(filtroPlataformas.getSelectedIndex()-1));

            }
        }
        //obtieneCategorias(plataforma_select);
        //}
    }//GEN-LAST:event_filtroPlataformasItemStateChanged

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        String sql="select p.idproducto,o.idplataforma,p.nombre,c.nombre as categoria,m.nombre as marca,o.costo_dolar,o.costoPesos,o.envioPlataforma,o.porcentajeComision,o.pesosComision,o.precioconIva,o.utilidadPesos,o.utilidadPorcentaje from producto p, marca m,operacionesproducto o,categoria c where m.idmarca=p.idmarca and p.idproducto=o.idproducto and p.idcategoria=c.idcategoria";
        /* if(!sinFiltroPlataforma){
            sql=sql+" and o.idplataforma="+plataforma_select;
        }
        if(!sinFiltroCategoria){
            sql=sql+" and c.idcategoria="+categoria_select;
        }*/
        if(filtroPlataformas.getSelectedIndex()!=0){
            int select_plataforma= Integer.parseInt(idplataforma.getItem(filtroPlataformas.getSelectedIndex()-1));
            sql=sql+" and o.idplataforma="+select_plataforma;
        }
        if(filtroCategorias.getSelectedIndex()!=0){
            int select_categoria= Integer.parseInt(idcategoria.getItem(filtroCategorias.getSelectedIndex()-1));
            sql=sql+" and c.idcategoria="+select_categoria;
        }

        if(filtroMarcas.getSelectedIndex()!=0){
            int select_marca= Integer.parseInt(idmarca.getItem(filtroMarcas.getSelectedIndex()-1));
            sql=sql+" and p.idmarca="+select_marca;
        }

        mostrardatos(sql);
        lbl_plataforma.setText(""+filtroPlataformas.getSelectedItem());
    }//GEN-LAST:event_jButton3ActionPerformed

    private void filtroCategoriasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_filtroCategoriasItemStateChanged
        if(filtroCategorias.getItemCount()>1){
            System.out.println("item cate: "+filtroCategorias.getSelectedIndex());
            if(filtroCategorias.getSelectedIndex()==0){
                sinFiltroCategoria=true;
                //System.out.println("sin filtro plataforma");

            }else{
                sinFiltroCategoria=false;
                categoria_select= Integer.parseInt(idcategoria.getItem(filtroCategorias.getSelectedIndex()-1));

            }
        }
    }//GEN-LAST:event_filtroCategoriasItemStateChanged

    private void tabla_productosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_productosMouseClicked
        try{
            int filaSeleccionada = tabla_productos.rowAtPoint(evt.getPoint());
            String plataformaid = plataforma_id.getItem(filaSeleccionada);
            String id=tabla_productos.getValueAt(filaSeleccionada, 0).toString();
            System.out.println("La fila seleccionada: "+filaSeleccionada+" corresponde al id: "+id+" y la plataforma: "+plataformaid);
            // String producto=tabla_productos.getValueAt(filaSeleccionada, 1).toString();
            //String costoDolar=tabla_productos.getValueAt(filaSeleccionada, 3).toString();
            /*String costoPesos=tabla_productos.getValueAt(filaSeleccionada, 4).toString();
            String envioPlataforma=tabla_productos.getValueAt(filaSeleccionada, 5).toString();
            String comisionPorcentaje=tabla_productos.getValueAt(filaSeleccionada, 6).toString();
            String comisionPesos=tabla_productos.getValueAt(filaSeleccionada,7).toString();
            String pibIva=tabla_productos.getValueAt(filaSeleccionada, 8).toString();
            String utilidadNetaPesos=tabla_productos.getValueAt(filaSeleccionada,9).toString();
            String utilidadNetaPorcentaje=tabla_productos.getValueAt(filaSeleccionada,10).toString();*/

            //OTRO calculo = new OTRO(id,plataformaid);

            //calculo.setVisible(true);
        }catch(Exception e){
            System.out.println(e);

        }
    }//GEN-LAST:event_tabla_productosMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Plataformas a = new Plataformas();
        a.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        int fila = tabla_productos.getSelectedRow();
        if(fila<0){
            JOptionPane.showMessageDialog(null,"Debe de seleccionar un producto a eliminar");
        }else{
            int c=JOptionPane.showConfirmDialog(null,"¿Esta seguro de eliminar la el producto seleccionado?","Mensaje de Confirmacion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(c==JOptionPane.YES_OPTION){
                try{
                    String plataformaid = plataforma_id.getItem(fila);
                    String id=tabla_productos.getValueAt(fila, 0).toString();
                    eliminar(id,plataformaid);
                    mostrardatos("select p.idproducto,o.idplataforma,p.nombre,c.nombre as categoria,m.nombre as marca,o.costo_dolar,o.costoPesos,o.envioPlataforma,o.porcentajeComision,o.pesosComision,o.precioconIva,o.utilidadPesos,o.utilidadPorcentaje from producto p, marca m,operacionesproducto o,categoria c where m.idmarca=p.idmarca and p.idproducto=o.idproducto and p.idcategoria=c.idcategoria");
                }catch (Exception e){
                    JOptionPane.showMessageDialog(null,e);
                }
            }
       }
       //JOptionPane.showMessageDialog(null,fila);
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(eliminaProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(eliminaProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(eliminaProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(eliminaProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new eliminaProductos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> filtroCategorias;
    private javax.swing.JComboBox<String> filtroMarcas;
    private javax.swing.JComboBox<String> filtroPlataformas;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_plataforma;
    private javax.swing.JTable tabla_productos;
    // End of variables declaration//GEN-END:variables

    private void eliminar(String idproducto,String id_plataforma) {
        PreparedStatement pst = null;
        try{
            pst = cn.prepareStatement("delete from operacionesProducto where idproducto="+idproducto+" and idplataforma="+id_plataforma);
            pst.executeUpdate();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
}
