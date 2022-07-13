package calculos;

import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class OTRO extends javax.swing.JFrame {

    int id_prod, id_plat;
    
    public OTRO(String id,String plataformaid){//,String producto,String marca,String pib,String costodolar,String tipocambio,String envioml,String porcentajeisrml,String porcentajeisr) {
        initComponents();
        this.setLocationRelativeTo(null);
        id_prod=Integer.parseInt(id);  
        id_plat=Integer.parseInt(plataformaid);  
        //String sql ="select idproducto,nombre,costo_dolar,tipo_cambio,importacion,gastos_fijos from producto where idproducto="+id_prod;
        String sql="select p.nombre,o.costo_dolar,o.tipo_cambio,o.importacion,o.gastos_fijos,estimacionisr,isrplataforma,o.costoPesos,envioPlataforma,porcentajeComision,pesosComision,pvsinIva,precioconIva,utilidadPesos,utilidadPorcentaje,t.nombre from producto p,operacionesproducto o,plataforma t where p.idproducto=o.idproducto and o.idplataforma=t.idplataforma and o.idproducto="+id_prod+" and o.idplataforma="+id_plat;
        try{
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while(rs.next()){
            lbl_producto.setText(rs.getString(16)+"/"+rs.getString(1));
            productoName.setText(rs.getString(1));
            txt8.setText(rs.getString(2));
            txt9.setText(rs.getString(3));
            txt15.setText(rs.getString(4));
            //a partir de aqui van los resultados de las operaciones
            if(!rs.getString(6).equals("0")){
                txt11.setText(rs.getString(6)); //ESTE
            }else{
                txt11.setText("");
            }
            
            if(!rs.getString(7).equals("0")){
                txt12.setText(rs.getString(7));
            }else{
                txt12.setText(""); //ESTE
            }
            
            if(!rs.getString(8).equals("0")){
                txt16.setText(rs.getString(8));
            }else{
                txt16.setText("");
            }
            
            if(!rs.getString(9).equals("0")){
                txt10.setText(rs.getString(9)); //ESTE
            }else{
                txt10.setText("");
            }
            if(!rs.getString(10).equals("0")){
                txt21.setText(rs.getString(10));
            }else{
                txt21.setText("");
            }
            
            if(!rs.getString(11).equals("0")){
                txt3.setText(rs.getString(11));
            }else{
                txt3.setText("");
            }
            
            if(!rs.getString(12).equals("0")){
                txt17.setText(rs.getString(12));
            }else{
                txt17.setText("");
            }
            
            if(!rs.getString(13).equals("0")){
                txt2.setText(rs.getString(13)); //ESTE
            }else{
                txt2.setText("");
            }
            
            if(!rs.getString(14).equals("0")){
                txt14.setText(rs.getString(14));
            }else{
                txt14.setText("");
            }
            
            if(!rs.getString(15).equals("0")){
                txt13.setText(rs.getString(15));
            }else{
                txt13.setText("");
            }
            
        }
        if((txt2.getText().equals(""))||(txt10.getText().equals(""))||(txt11.getText().equals(""))||(txt12.getText().equals(""))){  
        }else{ 
            Calcular();
        }
    }
    catch(Exception e){
        JOptionPane.showMessageDialog(null,e);
    }
    }
     conectar cc= new conectar ();
    Connection cn = cc.conexion();

    
    public void Calcular(){
        Double PCI1,PCI,COMISION,UTILIDAD,ISR,ISR1,UTILIDADPOR,PSI,DOLAR,TC,ENVIOML, GF, conc, CTF, calculo, ISRML, ISRML1, pesos, com, imp, imppor;
        Double ISRMENSUAL, UTINETA, UTINETAPOR;
        GF= 15.0;
        DOLAR = Double.parseDouble(txt8.getText());
        TC = Double.parseDouble(txt9.getText());
        ENVIOML = Double.parseDouble(txt10.getText());
        conc = DOLAR * TC;
        PCI = Double.parseDouble(txt2.getText());
        ISR = Double.parseDouble(txt11.getText());
        ISRML = Double.parseDouble(txt12.getText());
        com = Double.parseDouble(txt21.getText());
        imp = Double.parseDouble(txt15.getText());
        
        PSI= Math.round((PCI/1.16)*100.0)/100.0;//PCI / 1.16;
        //COMISION = (PCI * 0.13);
        //COMISION = (PCI * comision_select);
        COMISION = (PCI * com);
        imppor = imp * conc;
        CTF = conc + ENVIOML + GF + imppor ;
        calculo = (PSI-CTF-COMISION);
        UTILIDAD =  calculo;
        ISRML1= PSI * ISRML;
        ISR1 = UTILIDAD * ISR;
        ISRMENSUAL = ISR1 - ISRML1;
        UTINETA = UTILIDAD - ISR1;
        UTINETAPOR = (UTINETA / PSI)*100;
    
       // txt3.setText(Double.toString(COMISION));
         txt3.setText(Math.round(COMISION*100.0)/100.0+"");
       // txt1.setText(Math.round(UTILIDAD*100.0)/100.0+"");
        txt1.setText(Math.round(UTILIDAD*100.0)/100.0+"");
       // txt1.setText(Double.toString(UTILIDAD));
        //txt4.setText(Double.toString(ISR1));
        txt4.setText(Math.round(ISR1*100.0)/100.0+"");
       // txt5.setText(Double.toString(ISRML1));
        txt5.setText(Math.round(ISRML1*100.0)/100.0+"");
       // txt7.setText(Double.toString(ISRMENSUAL));
        txt7.setText(Math.round(ISRMENSUAL*100.0)/100.0+"");
        txt14.setText(Math.round(UTINETA*100.0)/100.0+"");
        txt13.setText(Math.round(UTINETAPOR*100.0)/100.0+"");
        //txt13.setText(Double.toString(UTINETAPOR));  
        txt16.setText(Math.round(conc*100.0)/100.0+"");  
        //txt17.setText(Double.toString(PSI)); 
        txt17.setText(Math.round(PSI*100.0)/100.0+"");  
        //txt20.setText(Double.toString(CTF));
        txt20.setText(Math.round(CTF*100.0)/100.0+""); 
        //txt22.setText(Double.toString(imppor));
         txt22.setText(Math.round(imppor*100.0)/100.0+"");
       
    }
        
    public void ActualizaProducto()throws SQLException{
        cc.conecta.setAutoCommit(false);
        PreparedStatement pst = null;
        try{
            pst = cn.prepareStatement("update producto set nombre='"+productoName.getText()+"' where idproducto="+id_prod);
            pst.executeUpdate();
            cc.conecta.commit();
        }catch (Exception e){
         cc.conecta.rollback();
         JOptionPane.showMessageDialog(null,e);
        }finally{
                if(pst != null){
                    pst.close();            
            }
        }
    }   
    public void ActualizaOperaciones()throws SQLException{
        cc.conecta.setAutoCommit(false);
        PreparedStatement pst = null;
        try{
            pst = cn.prepareStatement("update operacionesproducto set costo_dolar="+txt8.getText()+",tipo_cambio="+txt9.getText()+",importacion="+txt15.getText()+",estimacionisr="+txt11.getText()+",isrplataforma="+txt12.getText()+",costoPesos="+txt16.getText()+",envioPlataforma="+txt10.getText()+",porcentajeComision="+txt21.getText()+",pesosComision="+txt3.getText()+",pvsinIva="+txt17.getText()+",precioconIva="+txt2.getText()+",utilidadPesos="+txt14.getText()+",utilidadPorcentaje="+txt13.getText()+ "where idproducto="+id_prod+" and idplataforma="+id_plat);
            pst.executeUpdate();
            cc.conecta.commit();
        }catch (Exception e){
         cc.conecta.rollback();
         JOptionPane.showMessageDialog(null,e);
        }finally{
                if(pst != null){
                    pst.close();            
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt3 = new javax.swing.JTextField();
        txt4 = new javax.swing.JTextField();
        txt5 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txt7 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txt10 = new javax.swing.JTextField();
        txt12 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txt11 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txt20 = new javax.swing.JTextField();
        txt21 = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txt15 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        txt22 = new javax.swing.JTextField();
        txt23 = new javax.swing.JTextField();
        txt25 = new javax.swing.JTextField();
        txt26 = new javax.swing.JTextField();
        txt27 = new javax.swing.JTextField();
        txt28 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        lbl_producto = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        productoName = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txt8 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txt24 = new javax.swing.JTextField();
        txt9 = new javax.swing.JTextField();
        txt16 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txt17 = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txt29 = new javax.swing.JTextField();
        txt2 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txt1 = new javax.swing.JTextField();
        txt14 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txt13 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel34 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(178, 11, -1, 158));

        txt3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt3ActionPerformed(evt);
            }
        });
        jPanel1.add(txt3, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 230, 82, -1));

        txt4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt4ActionPerformed(evt);
            }
        });
        jPanel1.add(txt4, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 270, 82, -1));

        txt5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt5ActionPerformed(evt);
            }
        });
        jPanel1.add(txt5, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 310, 82, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Estimacion ISR");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 270, -1, -1));

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Comisión");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 230, -1, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("ISR Total");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 360, -1, -1));

        txt7.setForeground(new java.awt.Color(255, 255, 255));
        txt7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt7ActionPerformed(evt);
            }
        });
        jPanel1.add(txt7, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 360, 82, -1));

        jLabel12.setBackground(new java.awt.Color(255, 255, 255));
        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Envio plataforma");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 150, -1, -1));

        txt10.setBackground(new java.awt.Color(204, 204, 204));
        txt10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt10ActionPerformed(evt);
            }
        });
        txt10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt10KeyReleased(evt);
            }
        });
        jPanel1.add(txt10, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 120, 116, -1));

        txt12.setBackground(new java.awt.Color(204, 204, 204));
        txt12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt12ActionPerformed(evt);
            }
        });
        txt12.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt12KeyReleased(evt);
            }
        });
        jPanel1.add(txt12, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 310, 82, -1));

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("ISR Plataforma");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 310, -1, -1));

        txt11.setBackground(new java.awt.Color(204, 204, 204));
        txt11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt11ActionPerformed(evt);
            }
        });
        txt11.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt11KeyReleased(evt);
            }
        });
        jPanel1.add(txt11, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 270, 82, -1));

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Costo total fijo");
        jPanel1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 190, -1, -1));

        txt20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt20ActionPerformed(evt);
            }
        });
        jPanel1.add(txt20, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 190, 82, -1));

        txt21.setBackground(new java.awt.Color(204, 204, 204));
        txt21.setText("0.13");
        txt21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt21ActionPerformed(evt);
            }
        });
        txt21.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt21KeyReleased(evt);
            }
        });
        jPanel1.add(txt21, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 230, 67, -1));

        jLabel22.setBackground(new java.awt.Color(255, 255, 255));
        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Gastos fijos");
        jPanel1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 140, -1, -1));

        txt15.setBackground(new java.awt.Color(204, 204, 204));
        txt15.setText("  0.20");
        txt15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt15ActionPerformed(evt);
            }
        });
        txt15.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt15KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt15KeyReleased(evt);
            }
        });
        jPanel1.add(txt15, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 50, 116, -1));

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Importacíon");
        jPanel1.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 80, -1, -1));

        jLabel24.setBackground(new java.awt.Color(255, 255, 255));
        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("$15");
        jPanel1.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 120, -1, -1));

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("Importación");
        jPanel1.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 80, -1, -1));

        txt22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt22ActionPerformed(evt);
            }
        });
        jPanel1.add(txt22, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 50, 82, -1));

        txt23.setBackground(new java.awt.Color(204, 204, 204));
        txt23.setText("%");
        txt23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt23ActionPerformed(evt);
            }
        });
        txt23.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt23KeyReleased(evt);
            }
        });
        jPanel1.add(txt23, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 230, 26, -1));

        txt25.setBackground(new java.awt.Color(204, 204, 204));
        txt25.setText("$");
        txt25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt25ActionPerformed(evt);
            }
        });
        txt25.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt25KeyReleased(evt);
            }
        });
        jPanel1.add(txt25, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 120, 26, -1));

        txt26.setBackground(new java.awt.Color(204, 204, 204));
        txt26.setText("%");
        txt26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt26ActionPerformed(evt);
            }
        });
        txt26.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt26KeyReleased(evt);
            }
        });
        jPanel1.add(txt26, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 50, 26, -1));

        txt27.setBackground(new java.awt.Color(204, 204, 204));
        txt27.setText("%");
        txt27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt27ActionPerformed(evt);
            }
        });
        txt27.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt27KeyReleased(evt);
            }
        });
        jPanel1.add(txt27, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 310, 26, -1));

        txt28.setBackground(new java.awt.Color(204, 204, 204));
        txt28.setText("%");
        txt28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt28ActionPerformed(evt);
            }
        });
        txt28.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt28KeyReleased(evt);
            }
        });
        jPanel1.add(txt28, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 270, 26, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Comisión");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 230, -1, -1));

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel28.setText("$");
        jPanel1.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(738, 440, -1, -1));

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("$");
        jPanel1.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 310, -1, -1));

        jLabel30.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel30.setText("$");
        jPanel1.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(745, 481, -1, -1));

        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel31.setText("$");
        jPanel1.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(824, 335, -1, -1));

        jLabel32.setBackground(new java.awt.Color(255, 255, 255));
        jLabel32.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setText("$");
        jPanel1.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 80, -1, -1));

        jLabel33.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("$");
        jPanel1.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 190, -1, -1));

        jPanel2.setBackground(new java.awt.Color(255, 206, 7));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_producto.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lbl_producto.setText("PRODUCTO");
        jPanel7.add(lbl_producto, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Producto");
        jPanel7.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, -1, 20));

        productoName.setBackground(new java.awt.Color(204, 204, 204));
        jPanel7.add(productoName, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 160, 148, 30));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("Costo en dolar");
        jPanel7.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 170, -1, 20));

        txt8.setBackground(new java.awt.Color(204, 204, 204));
        txt8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt8ActionPerformed(evt);
            }
        });
        txt8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt8KeyReleased(evt);
            }
        });
        jPanel7.add(txt8, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 160, 100, 30));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Tipo de cambio");
        jPanel7.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, -1, -1));

        txt24.setBackground(new java.awt.Color(204, 204, 204));
        txt24.setText("$");
        txt24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt24ActionPerformed(evt);
            }
        });
        txt24.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt24KeyReleased(evt);
            }
        });
        jPanel7.add(txt24, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 200, 26, -1));

        txt9.setBackground(new java.awt.Color(204, 204, 204));
        txt9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt9ActionPerformed(evt);
            }
        });
        txt9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt9KeyReleased(evt);
            }
        });
        jPanel7.add(txt9, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 200, 116, -1));

        txt16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt16ActionPerformed(evt);
            }
        });
        jPanel7.add(txt16, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 200, 82, -1));

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel19.setText("Costo");
        jPanel7.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 200, -1, -1));

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel26.setText("$");
        jPanel7.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 200, -1, -1));

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel25.setText("PV sin iva");
        jPanel7.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 230, -1, -1));

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel18.setText("$");
        jPanel7.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 230, -1, -1));

        txt17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt17ActionPerformed(evt);
            }
        });
        jPanel7.add(txt17, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 230, 82, -1));

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel20.setText("PV con iva");
        jPanel7.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 230, -1, -1));

        txt29.setBackground(new java.awt.Color(204, 204, 204));
        txt29.setText("$");
        txt29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt29ActionPerformed(evt);
            }
        });
        txt29.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt29KeyReleased(evt);
            }
        });
        jPanel7.add(txt29, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 230, 26, -1));

        txt2.setBackground(new java.awt.Color(204, 204, 204));
        txt2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt2ActionPerformed(evt);
            }
        });
        txt2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt2KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt2KeyReleased(evt);
            }
        });
        jPanel7.add(txt2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 230, 116, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Utilidad");
        jPanel7.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, -1, -1));

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setText("$");
        jPanel7.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 260, -1, -1));

        txt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt1ActionPerformed(evt);
            }
        });
        jPanel7.add(txt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 260, 82, -1));

        txt14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt14ActionPerformed(evt);
            }
        });
        jPanel7.add(txt14, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 260, 82, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("$");
        jPanel7.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 260, -1, -1));

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel17.setText("Utilidad neta");
        jPanel7.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 260, -1, -1));

        txt13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt13ActionPerformed(evt);
            }
        });
        jPanel7.add(txt13, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 300, 89, -1));

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel16.setText("Utilidad neta ");
        jPanel7.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 300, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("%");
        jPanel7.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 300, -1, -1));

        jButton5.setText("REGRESAR");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel7.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 400, -1, -1));

        jButton4.setText("GUARDAR");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel7.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 400, -1, -1));

        jButton6.setText("CALCULAR");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel7.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 400, -1, -1));

        jLabel34.setIcon(new javax.swing.ImageIcon("C:\\Users\\angel\\Documents\\residencia lagunas\\descarga.png")); // NOI18N
        jPanel7.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 40, -1, -1));

        jPanel2.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 500, 480));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 530, 497));

        jLabel47.setForeground(new java.awt.Color(255, 255, 255));
        jLabel47.setIcon(new javax.swing.ImageIcon(getClass().getResource("/calculos/fondo.png"))); // NOI18N
        jPanel1.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 0, 540, 497));

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt1ActionPerformed

    private void txt2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt2ActionPerformed

    private void txt8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt8ActionPerformed

    private void txt9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt9ActionPerformed

    private void txt10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt10ActionPerformed

    private void txt13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt13ActionPerformed

    private void txt14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt14ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        //ConsultaPlataforma();
        //Calcular();
    }//GEN-LAST:event_formWindowOpened

    private void txt2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt2KeyReleased
        char c = (char) evt.getKeyCode();
        if(c == evt.VK_ENTER){
         try {
            //ActualizaProducto();
            Calcular();
        } catch (Exception ex) {
            Logger.getLogger(OTRO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    }//GEN-LAST:event_txt2KeyReleased

    private void txt2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt2KeyPressed
  
    }//GEN-LAST:event_txt2KeyPressed

    private void txt8KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt8KeyReleased
       char c = (char) evt.getKeyCode();
        if(c == evt.VK_ENTER){
         try {
            //ActualizaProducto();
            Calcular();
        } catch (Exception ex) {
            Logger.getLogger(OTRO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    }//GEN-LAST:event_txt8KeyReleased

    private void txt9KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt9KeyReleased
       char c = (char) evt.getKeyCode();
        if(c == evt.VK_ENTER){
         try {
            //ActualizaProducto();
            Calcular();
        } catch (Exception ex) {
            Logger.getLogger(OTRO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    }//GEN-LAST:event_txt9KeyReleased

    private void txt10KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt10KeyReleased
       char c = (char) evt.getKeyCode();
        if(c == evt.VK_ENTER){
         try {
            //ActualizaProducto();
            Calcular();
        } catch (Exception ex) {
            Logger.getLogger(OTRO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    }//GEN-LAST:event_txt10KeyReleased

    private void txt16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt16ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt16ActionPerformed

    private void txt17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt17ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt17ActionPerformed

    private void txt24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt24ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt24ActionPerformed

    private void txt24KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt24KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt24KeyReleased

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        
        //a.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
       if((txt2.getText().equals(""))||(txt10.getText().equals(""))||(txt11.getText().equals(""))||(txt12.getText().equals(""))){
            JOptionPane.showMessageDialog(null,"Es necesario llenar los campos sombreados");
        }else{
            Calcular();
            if((txt16.getText().equals(""))||(txt17.getText().equals(""))||(txt22.getText().equals(""))||(txt20.getText().equals(""))||(txt4.getText().equals(""))||(txt5.getText().equals(""))||(txt7.getText().equals(""))){
                JOptionPane.showMessageDialog(null,"ERROR! Hay valores vacíos");
            }else{
                try {
                    ActualizaProducto();
                    ActualizaOperaciones();
                } catch (SQLException ex) {
                    Logger.getLogger(OTRO.class.getName()).log(Level.SEVERE, null, ex);
                }   
            }
        }
         
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
         if((txt2.getText().equals(""))||(txt10.getText().equals(""))||(txt11.getText().equals(""))||(txt12.getText().equals(""))){
            JOptionPane.showMessageDialog(null,"Es necesario llenar los campos sombreados");
        }else{
            Calcular();
            try {
               // ActualizaProducto();
                ActualizaOperaciones();
            } catch (SQLException ex) {
                Logger.getLogger(OTRO.class.getName()).log(Level.SEVERE, null, ex);
            }           
        }
  /*Double PCI1,PCI,COMISION,UTILIDAD,ISR,ISR1,UTILIDADPOR,PSI,DOLAR,TC,ENVIOML, GF, conc, CTF, calculo, ISRML, ISRML1;
        Double ISRMENSUAL, UTINETA, UTINETAPOR;
        GF= 15.0;
        DOLAR = Double.parseDouble(txt8.getText());
        TC = Double.parseDouble(txt9.getText());
        ENVIOML = Double.parseDouble(txt10.getText());
        conc = DOLAR * TC;
        PCI = Double.parseDouble(txt2.getText());
        ISR = Double.parseDouble(txt11.getText());
        ISRML = Double.parseDouble(txt12.getText());
        
        PSI= PCI / 1.16;
        COMISION = (PCI * 0.13);
        CTF = conc + ENVIOML + GF;
        calculo = (PSI-CTF-COMISION);
        UTILIDAD =  calculo;
        ISRML1= PSI * ISRML;
        ISR1 = UTILIDAD * ISR;
        ISRMENSUAL = ISR1 - ISRML1;
        UTINETA = UTILIDAD - ISR1;
        UTINETAPOR = (UTINETA / PSI)*100;
        
        txt3.setText(Double.toString(COMISION));
        txt6.setText(Double.toString(PSI));
        txt1.setText(Double.toString(UTILIDAD));
        txt4.setText(Double.toString(ISR1));
        txt5.setText(Double.toString(ISRML1));
        txt7.setText(Double.toString(ISRMENSUAL));
        txt14.setText(Double.toString(UTINETA));
        txt13.setText(Double.toString(UTINETAPOR));
        */
        
        
        
    
    }//GEN-LAST:event_jButton6ActionPerformed

    private void txt29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt29ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt29ActionPerformed

    private void txt29KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt29KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt29KeyReleased

    private void txt7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt7ActionPerformed

    private void txt5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt5ActionPerformed

    private void txt12KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt12KeyReleased
        char c = (char) evt.getKeyCode();
        if(c == evt.VK_ENTER){
            try {
                //ActualizaProducto();
                Calcular();
            } catch (Exception ex) {
                Logger.getLogger(OTRO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_txt12KeyReleased

    private void txt12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt12ActionPerformed

    private void txt27KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt27KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt27KeyReleased

    private void txt27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt27ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt27ActionPerformed

    private void txt4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt4ActionPerformed

    private void txt11KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt11KeyReleased
        char c = (char) evt.getKeyCode();
        if(c == evt.VK_ENTER){
            try {
                //ActualizaProducto();
                Calcular();
            } catch (Exception ex) {
                Logger.getLogger(OTRO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_txt11KeyReleased

    private void txt11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt11ActionPerformed

    private void txt28KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt28KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt28KeyReleased

    private void txt28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt28ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt28ActionPerformed

    private void txt3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt3ActionPerformed

    private void txt23KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt23KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt23KeyReleased

    private void txt23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt23ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt23ActionPerformed

    private void txt21KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt21KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt21KeyReleased

    private void txt21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt21ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt21ActionPerformed

    private void txt20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt20ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt20ActionPerformed

    private void txt26KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt26KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt26KeyReleased

    private void txt26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt26ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt26ActionPerformed

    private void txt15KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt15KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt15KeyReleased

    private void txt15KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt15KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt15KeyPressed

    private void txt15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt15ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt15ActionPerformed

    private void txt22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt22ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt22ActionPerformed

    private void txt25KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt25KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt25KeyReleased

    private void txt25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt25ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt25ActionPerformed

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
            java.util.logging.Logger.getLogger(OTRO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OTRO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OTRO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OTRO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new OTRO("0","0").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
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
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JLabel lbl_producto;
    private javax.swing.JTextField productoName;
    private javax.swing.JTextField txt1;
    private javax.swing.JTextField txt10;
    private javax.swing.JTextField txt11;
    private javax.swing.JTextField txt12;
    private javax.swing.JTextField txt13;
    private javax.swing.JTextField txt14;
    private javax.swing.JTextField txt15;
    private javax.swing.JTextField txt16;
    private javax.swing.JTextField txt17;
    private javax.swing.JTextField txt2;
    private javax.swing.JTextField txt20;
    private javax.swing.JTextField txt21;
    private javax.swing.JTextField txt22;
    private javax.swing.JTextField txt23;
    private javax.swing.JTextField txt24;
    private javax.swing.JTextField txt25;
    private javax.swing.JTextField txt26;
    private javax.swing.JTextField txt27;
    private javax.swing.JTextField txt28;
    private javax.swing.JTextField txt29;
    private javax.swing.JTextField txt3;
    private javax.swing.JTextField txt4;
    private javax.swing.JTextField txt5;
    private javax.swing.JTextField txt7;
    private javax.swing.JTextField txt8;
    private javax.swing.JTextField txt9;
    // End of variables declaration//GEN-END:variables
}
