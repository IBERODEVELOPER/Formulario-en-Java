package edu.ibero.form;

/*Librerias a utilizar */
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

public class FormRecaudador extends javax.swing.JFrame {

    //Declaraciones para la conexion a la db
    private static Connection con;// Declaramos la conexion a mysql
    private static final String driver = "com.mysql.jdbc.Driver";// JDBC DRIVER Declaramos los datos de conexion a la bd el driver
    //url de la bd y ultimo el nombre de la base de datos y si se utiliza caracteres especiales
    private static final String url = "jdbc:mysql://localhost:3306/dbibero?characterEncoding=UTF-8";
    /*data base credenciales*/
    private static final String user = "root";//nombre de usuario de nuestra db
    private static final String pass = "12345";//contraseña de acceso a nuestra db

    //Variable y objetos 
    DriverManager driverManager;
    private static Statement s;
    private static ResultSet rs;
    private static PreparedStatement ps;
    //variables 
    private static String codigo, nombre;
    private int edad, mensualidad = 0;
    //creamos un objeto del modelo de la tabla
    DefaultTableModel model = new DefaultTableModel();

    public FormRecaudador() {
        initComponents();
        //ENCABEZADO DE LA TABLA JTABLE
        model.addColumn("CODIGO");
        model.addColumn("NOMBRE");
        model.addColumn("EDAD");
        model.addColumn("PRIMA");
        //AGREGAMOS LOS NOMBRES DE COLUMNAS
        jtableregistro.setModel(model);
        // Reseteamos a null la conexion a la bd
        con = null;
        try {
            //registramos la clase del driver
            Class.forName(driver);
            // abrimos la conexion a la bd indicando la url el usuario y el password
            con = (Connection) DriverManager.getConnection(url, user, pass);
            // Si la conexion fue exitosa mostramos un mensaje de conexion exitosa
            System.out.println("Conexion establecida");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error de conexion " + e);
        }
        //Metodo que lista los datos de la tabla de la DB
        listar();
    }
    //METODO LISTAR 
    public void listar() {
        //CREAMOS UNA INSTANCIA de un arreglo
        String[] registros = new String[4];

        try {
            // Preparamos la consulta
            s = con.createStatement();
            //mandamos estatement el query y lo almacenamos en resultset
            rs = s.executeQuery("select * from asegurado");
            // Recorremos el resultado, mientras haya registros para leer, y escribimos el resultado en pantalla.
            //obtenermos los datos de un resultset
            while (rs.next()) {
                //obetniendo los valores de las columnas y mostramos los datos obtenidos
                registros[0] = rs.getString(1);
                registros[1] = rs.getString(2);
                registros[2] = String.valueOf(rs.getInt(3));
                registros[3] = String.valueOf(rs.getInt(4));
                //agregamos los datos a la tabla.
                model.addRow(registros);

            }
            //agregamos el modelo a JTable
            jtableregistro.setModel(model);
        } catch (SQLException ex) {// mostramos un error si sucede algo en la db
            System.out.println("Error : " + ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        edtcodigo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        edtnombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        edtedad = new javax.swing.JTextField();
        btnguardar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtableregistro = new javax.swing.JTable();
        btnverdata = new javax.swing.JButton();
        btneditar = new javax.swing.JButton();
        btneliminar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("El Buen Recaudador");
        setBackground(new java.awt.Color(0, 153, 153));

        jLabel1.setText("CODIGO :");

        edtcodigo.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel2.setText("NOMBRE : ");

        edtnombre.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        edtnombre.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        jLabel3.setText("EDAD : ");

        edtedad.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        btnguardar.setText("REGISTRAR");
        btnguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarActionPerformed(evt);
            }
        });

        jtableregistro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jtableregistro.setColumnSelectionAllowed(true);
        jtableregistro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtableregistroMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jtableregistro);
        jtableregistro.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        btnverdata.setText("ACTUALIZAR");
        btnverdata.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnverdataActionPerformed(evt);
            }
        });

        btneditar.setText("MODIFICAR");
        btneditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditarActionPerformed(evt);
            }
        });

        btneliminar.setText("ELIMINAR");
        btneliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneliminarActionPerformed(evt);
            }
        });

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jTextArea1.setForeground(new java.awt.Color(0, 153, 153));
        jTextArea1.setRows(5);
        jTextArea1.setText("Costo de Prima Mensual\nEDAD 0 - 20 Paga: S/.50\nEDAD 21 - 40 Paga: S/.120\nEDAD 41 - 60 Paga: S/.300\nEDAD 60 a más Paga: S/.450");
        jTextArea1.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        jTextArea1.setCaretColor(new java.awt.Color(204, 204, 204));
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(45, 45, 45)
                                .addComponent(edtedad))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2))
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(edtcodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(edtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnguardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnverdata, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btneditar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btneliminar))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 543, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(edtcodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(edtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(edtedad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnguardar)
                    .addComponent(btnverdata)
                    .addComponent(btneditar)
                    .addComponent(btneliminar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
        //capturamos los datos del formulario y
        //lo almcenamos en las variables
        codigo = edtcodigo.getText();
        nombre = edtnombre.getText();
        edad = Integer.parseInt(edtedad.getText());
        //segun la edad consultamos 
        //si edad es mayor o igual que 0 y
        //edad es menor igual que 20
        if (edad >= 0 && edad <= 20) {
            //entonces la mensualidad es 50.
            mensualidad = mensualidad + 50;
        } else if (edad >= 21 && edad <= 40) {
            mensualidad = mensualidad + 120;
        } else if (edad >= 41 && edad <= 60) {
            mensualidad = mensualidad + 300;
        } else {
            mensualidad = mensualidad + 450;
        }
        //variables para el parseo
        String edadres, mensualidadres;
        //cambiamos los valores int a string
        edadres = String.valueOf(edad);
        mensualidadres = String.valueOf(mensualidad);
        // Reseteamos a null la conexion a la preparedstatement
        ps = null;
        try {
            //realizamos la consulta
            ps = (PreparedStatement) con.prepareStatement("INSERT INTO asegurado(codasegurado,nombreasegurado,edadasegurado,mensualidadasegurado) "
                    + "VALUES (?,?,?,?)");
            //indicamos los valores a almacenar
            ps.setString(1, codigo);
            ps.setString(2, nombre);
            ps.setString(3, edadres);
            ps.setString(4, mensualidadres);
            // ejecuta la query
            ps.executeUpdate(); 
            //Mostramos en consola que se ingrese un dato
            System.out.println("Datos Registrados");
            //limpiamos las cajas de texto
            edtcodigo.setText("");
            edtnombre.setText("");
            edtedad.setText("");
            mensualidad = 0;
            //limpiamos los valores de la tabla
            model.getDataVector().removeAllElements();
            jtableregistro.updateUI();
            //listamos el contenido de la tabla
            listar();

        } catch (SQLException ex) {
            //si existe un error con el sql 
            System.out.println("Error de conexion " + ex);
        }
    }//GEN-LAST:event_btnguardarActionPerformed


    private void btnverdataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnverdataActionPerformed
        //limpiamos los elementos de la tabla
        model.getDataVector().removeAllElements();
        //actualizamos la vista
        jtableregistro.updateUI();
        //creamos un arreglos
        String[] registros = new String[4];
        try {
            // Preparamos la consulta
            s = con.createStatement();
            //mandamos estatement el query y lo almacenamos en resultset
            //codasegurado,nombreasegurado,edadasegurado,mensualidadasegurado
            rs = s.executeQuery("select * from asegurado");
            // Recorremos el resultado, mientras haya registros para leer, y escribimos el resultado en pantalla.
            //obtenermos los datos de un resultset
            while (rs.next()) {
                //obetniendo los valores de las columnas 
                //y almacenamos en el arreglo
                registros[0] = rs.getString(1);
                registros[1] = rs.getString(2);
                registros[2] = String.valueOf(rs.getInt(3));
                registros[3] = String.valueOf(rs.getInt(4));
                //agregamos las el arreglo al modelo
                model.addRow(registros);  }
            //agregamos el modelo a JTable
            jtableregistro.setModel(model);
        } catch (SQLException ex) {
            System.out.println("Error : " + ex);
        }
    }//GEN-LAST:event_btnverdataActionPerformed

    private void btneditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditarActionPerformed
        //limpiamos la tabla
        model.getDataVector().removeAllElements();
        jtableregistro.updateUI();
        //obtenemos los datos
        codigo = edtcodigo.getText();
        nombre = edtnombre.getText();
        edad = Integer.parseInt(edtedad.getText());
        if (edad >= 0 && edad <= 20) {
            mensualidad = mensualidad + 50;
        } else if (edad >= 21 && edad <= 40) {
            mensualidad = mensualidad + 120;
        } else if (edad >= 41 && edad <= 60) {
            mensualidad = mensualidad + 300;
        } else {
            mensualidad = mensualidad + 450;
        }
        String sql = "update asegurado set codasegurado=?,nombreasegurado = "
                + "?, edadasegurado = ?,mensualidadasegurado = ? where codasegurado ='" + codigo + "'";
        try {
            // create the java mysql update preparedstatement
            ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, codigo);
            ps.setString(2, nombre);
            ps.setInt(3, edad);
            ps.setInt(4, mensualidad);
            //execute the java preparedstatement
            ps.executeUpdate();
            //Mostramos en consola que se ingrese un dato
            System.out.println("Datos Actualizados");
            //limpiamos la caja de texto
            edtcodigo.setText("");
            edtnombre.setText("");
            edtedad.setText("");
            //limpiamos el valor del costo de la prima
            mensualidad = 0;
            //limpiamos el modelo
            model.getDataVector().removeAllElements();
            //actualizamos la vista
            jtableregistro.updateUI();
            //realizamos un listado de la db
            listar();
        } catch (SQLException ex) {
            System.out.println("Error " + ex);
        }
    }//GEN-LAST:event_btneditarActionPerformed

    private void jtableregistroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtableregistroMouseClicked
        int filaselecionada;
        filaselecionada = jtableregistro.getSelectedRow();
        edtcodigo.setText(jtableregistro.getValueAt(filaselecionada, 0).toString());
        edtnombre.setText(jtableregistro.getValueAt(filaselecionada, 1).toString());
        edtedad.setText(jtableregistro.getValueAt(filaselecionada, 2).toString());
    }//GEN-LAST:event_jtableregistroMouseClicked

    private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarActionPerformed
        //limpiamos la tabla
        model.getDataVector().removeAllElements();
        jtableregistro.updateUI();
        //ontenemos el codigo
        codigo = edtcodigo.getText();
        // create the java mysql update preparedstatement
        String sql = "delete from asegurado where codasegurado ='" + codigo + "'";
        try {
            // Preparamos la consulta
            s = con.createStatement();
            s.executeUpdate(sql);
            System.out.println("Usuario Eliminado" + codigo);
            listar();
        } catch (SQLException ex) {
            System.out.println(" Error :" + ex);
        }
    }//GEN-LAST:event_btneliminarActionPerformed

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormRecaudador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormRecaudador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormRecaudador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormRecaudador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormRecaudador().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btneditar;
    private javax.swing.JButton btneliminar;
    private javax.swing.JButton btnguardar;
    private javax.swing.JButton btnverdata;
    private javax.swing.JTextField edtcodigo;
    private javax.swing.JTextField edtedad;
    private javax.swing.JTextField edtnombre;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTable jtableregistro;
    // End of variables declaration//GEN-END:variables
}
