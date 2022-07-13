package calculos;

import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class tablaProductos extends javax.swing.JFrame {

    List idplataforma= new List();
    List idcategoria= new List();
    List idmarca = new List();
    
    List plataforma_id= new List();
    int plataforma_select,categoria_select;
    String nombreAux;
    boolean sinFiltroPlataforma=false;
    boolean sinFiltroCategoria=false;
    String querySql;
    public tablaProductos(int id,String nombrePlataforma) {
        initComponents();
        this.setLocationRelativeTo(null);
        String sql="";
        nombreAux=nombrePlataforma;
        if((id<1)&&(nombrePlataforma.equals(""))){
            //JOptionPane.showMessageDialog(null,"SIN FILTRO!!!!Id: "+id+", nombre: "+nombrePlataforma);
            lbl_plataforma.setText("");
            sql="select p.idproducto,o.idplataforma,p.nombre,c.nombre as categoria,m.nombre as marca,o.costo_dolar,o.costoPesos,o.envioPlataforma,o.porcentajeComision,o.pesosComision,o.precioconIva,o.utilidadPesos,o.utilidadPorcentaje from producto p, marca m,operacionesproducto o,categoria c where m.idmarca=p.idmarca and p.idproducto=o.idproducto and p.idcategoria=c.idcategoria";
        }else{
            lbl_plataforma.setText(nombrePlataforma);
            sql="select p.idproducto,o.idplataforma,p.nombre,c.nombre as categoria,m.nombre as marca,o.costo_dolar,o.costoPesos,o.envioPlataforma,o.porcentajeComision,o.pesosComision,o.precioconIva,o.utilidadPesos,o.utilidadPorcentaje from producto p, marca m,operacionesproducto o,categoria c where m.idmarca=p.idmarca and p.idproducto=o.idproducto and p.idcategoria=c.idcategoria and o.idplataforma="+id;
        }
       
        mostrardatos(sql);
        ConsultaPlataforma();
        for(int i=0;i<filtroPlataformas.getItemCount();i++){
        if(nombrePlataforma.equals(filtroPlataformas.getItemAt(i))){
            filtroPlataformas.setSelectedIndex(i);
        }
        }
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
        jButton2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        lbl_plataforma = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        filtroPlataformas = new javax.swing.JComboBox<String>();
        filtroCategorias = new javax.swing.JComboBox<String>();
        filtroMarcas = new javax.swing.JComboBox<String>();
        jButton3 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_productos = new javax.swing.JTable();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1383, 205, 0, 0));

        jPanel3.setBackground(new java.awt.Color(255, 206, 7));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(255, 173, 7));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("ELIMINAR");
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/calculos/iconos/icons8_delete_32px.png"))); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addGap(75, 75, 75))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel8))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, 240, 50));

        jPanel5.setBackground(new java.awt.Color(255, 173, 7));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("REGRESAR");
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/calculos/iconos/icons8_up_left_32px.png"))); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addGap(71, 71, 71))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel9))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 240, -1));

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 35)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("VVESHOP");
        jPanel3.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, -1, -1));

        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/calculos/iconos/icons8_shop_64px_1.png"))); // NOI18N
        jPanel3.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, 60, 50));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 240, 540));

        jPanel6.setBackground(new java.awt.Color(102, 102, 102));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 25)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("PLATAFORMA:");

        lbl_plataforma.setFont(new java.awt.Font("Segoe UI", 1, 25)); // NOI18N
        lbl_plataforma.setForeground(new java.awt.Color(255, 255, 255));
        lbl_plataforma.setText("PLATAFORMA");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl_plataforma)
                .addContainerGap(373, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(lbl_plataforma))
                .addContainerGap())
        );

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 40, 750, -1));

        jPanel7.setBackground(new java.awt.Color(204, 204, 204));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel12.setText("Filtros de busqueda:");

        filtroPlataformas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Todas las plataformas" }));
        filtroPlataformas.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                filtroPlataformasItemStateChanged(evt);
            }
        });

        filtroCategorias.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Todas la categorías" }));
        filtroCategorias.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                filtroCategoriasItemStateChanged(evt);
            }
        });

        filtroMarcas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Todas las marcas" }));

        jButton3.setText("Buscar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/calculos/iconos/icons8_search_32px.png"))); // NOI18N

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(filtroPlataformas, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(filtroCategorias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(filtroMarcas, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)))
                .addContainerGap(85, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(filtroPlataformas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(filtroCategorias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(filtroMarcas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton3))
                    .addComponent(jLabel3))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 120, 730, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("Copyrigth VESHOP 2022");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 10, -1, -1));

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

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 230, 720, 290));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 990, 530));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String sql="select p.idproducto,o.idplataforma,p.nombre,c.nombre as categoria,m.nombre as marca,o.costo_dolar,o.costoPesos,o.envioPlataforma,o.porcentajeComision,o.pesosComision,o.precioconIva,o.utilidadPesos,o.utilidadPorcentaje from producto p, marca m,operacionesproducto o,categoria c where m.idmarca=p.idmarca and p.idproducto=o.idproducto and p.idcategoria=c.idcategoria";
        mostrardatos(sql);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
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
    }//GEN-LAST:event_jLabel8MouseClicked

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

    private void tabla_productosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_productosMouseClicked
        try{
            int filaSeleccionada = tabla_productos.rowAtPoint(evt.getPoint());
            String plataformaid = plataforma_id.getItem(filaSeleccionada);
            String id=tabla_productos.getValueAt(filaSeleccionada, 0).toString();
            // String producto=tabla_productos.getValueAt(filaSeleccionada, 1).toString();
            //String costoDolar=tabla_productos.getValueAt(filaSeleccionada, 3).toString();
            /*String costoPesos=tabla_productos.getValueAt(filaSeleccionada, 4).toString();
            String envioPlataforma=tabla_productos.getValueAt(filaSeleccionada, 5).toString();
            String comisionPorcentaje=tabla_productos.getValueAt(filaSeleccionada, 6).toString();
            String comisionPesos=tabla_productos.getValueAt(filaSeleccionada,7).toString();
            String pibIva=tabla_productos.getValueAt(filaSeleccionada, 8).toString();
            String utilidadNetaPesos=tabla_productos.getValueAt(filaSeleccionada,9).toString();
            String utilidadNetaPorcentaje=tabla_productos.getValueAt(filaSeleccionada,10).toString();*/

            OTRO calculo = new OTRO(id,plataformaid);

            calculo.setVisible(true);
        }catch(Exception e){
            System.out.println(e);

        }
    }//GEN-LAST:event_tabla_productosMouseClicked

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
         Plataformas a = new Plataformas();
      a.setVisible(true);
      dispose();
    }//GEN-LAST:event_jLabel9MouseClicked

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
            java.util.logging.Logger.getLogger(tablaProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(tablaProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(tablaProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(tablaProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new tablaProductos(0,"").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> filtroCategorias;
    private javax.swing.JComboBox<String> filtroMarcas;
    private javax.swing.JComboBox<String> filtroPlataformas;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
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
