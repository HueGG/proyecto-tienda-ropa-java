
package Vista;


import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import javax.swing.table.DefaultTableModel;

public class VentanaCliente extends javax.swing.JFrame {
    

    public VentanaCliente() {
        
        initComponents();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPestaniaCliente = new javax.swing.JTabbedPane();
        panelCompra = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        PanelCliente_Pruebas = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaCompraCliente = new javax.swing.JTable();
        jLabel17 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtIdCliente = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtNombreCompleto = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAreaDireccion = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        btnActualizarCliente = new javax.swing.JButton();
        txtPassword = new javax.swing.JTextField();
        panelProducto = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaProductoCliente = new javax.swing.JTable();
        btnActualizarTablaProductoCliente = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtIdProducto = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtNombreProducto = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtMarcaProducto = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtColorProducto = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtPrecioUnitarioProducto = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtEstatusProducto = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtStockProducto = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtCantidadComprar = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtPrecioFinal = new javax.swing.JFormattedTextField();
        btnComprar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelCompra.setBackground(new java.awt.Color(255, 255, 255));

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/logo_blancoGrande.png"))); // NOI18N

        jLabel19.setFont(new java.awt.Font("Verdana", 1, 48)); // NOI18N
        jLabel19.setText("Bienvinido a");

        javax.swing.GroupLayout panelCompraLayout = new javax.swing.GroupLayout(panelCompra);
        panelCompra.setLayout(panelCompraLayout);
        panelCompraLayout.setHorizontalGroup(
            panelCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCompraLayout.createSequentialGroup()
                .addGap(295, 295, 295)
                .addComponent(jLabel18)
                .addContainerGap(295, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCompraLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel19)
                .addGap(466, 466, 466))
        );
        panelCompraLayout.setVerticalGroup(
            panelCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCompraLayout.createSequentialGroup()
                .addContainerGap(119, Short.MAX_VALUE)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18)
                .addGap(61, 61, 61))
        );

        panelPestaniaCliente.addTab("Innovasport", panelCompra);

        PanelCliente_Pruebas.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setFont(new java.awt.Font("Verdana", 1, 48)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(18, 125, 194));
        jLabel7.setText("Mi perfil ");

        tablaCompraCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID Compra", "Producto", "Cantidad", "Precio total", "Fecha de compra", "Fecha de entrega"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tablaCompraCliente);

        jLabel17.setFont(new java.awt.Font("Century Gothic", 1, 22)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(18, 125, 194));
        jLabel17.setText("Mis Compras");

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/logo_fondoblanco_chico.PNG"))); // NOI18N

        jPanel1.setBackground(new java.awt.Color(229, 0, 0));
        jPanel1.setForeground(new java.awt.Color(229, 0, 0));

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("ID:");

        txtIdCliente.setBackground(new java.awt.Color(18, 125, 194));
        txtIdCliente.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        txtIdCliente.setForeground(new java.awt.Color(255, 255, 255));
        txtIdCliente.setEnabled(false);

        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nombre:");

        txtNombreCompleto.setBackground(new java.awt.Color(18, 125, 194));
        txtNombreCompleto.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        txtNombreCompleto.setEnabled(false);

        jLabel3.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Email:");
        jLabel3.setToolTipText("");

        txtCorreo.setBackground(new java.awt.Color(18, 125, 194));
        txtCorreo.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        txtCorreo.setEnabled(false);

        jLabel5.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Teléfono:");

        txtTelefono.setBackground(new java.awt.Color(18, 125, 194));
        txtTelefono.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyTyped(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Dirección:");

        txtAreaDireccion.setBackground(new java.awt.Color(18, 125, 194));
        txtAreaDireccion.setColumns(20);
        txtAreaDireccion.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        txtAreaDireccion.setRows(5);
        jScrollPane1.setViewportView(txtAreaDireccion);

        jLabel4.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Contraseña:");

        btnActualizarCliente.setText("Actualizar");

        txtPassword.setBackground(new java.awt.Color(18, 125, 194));
        txtPassword.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                                    .addComponent(txtTelefono)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(txtCorreo, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNombreCompleto, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                            .addComponent(txtIdCliente)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnActualizarCliente)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtIdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNombreCompleto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
                .addComponent(btnActualizarCliente)
                .addContainerGap())
        );

        javax.swing.GroupLayout PanelCliente_PruebasLayout = new javax.swing.GroupLayout(PanelCliente_Pruebas);
        PanelCliente_Pruebas.setLayout(PanelCliente_PruebasLayout);
        PanelCliente_PruebasLayout.setHorizontalGroup(
            PanelCliente_PruebasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelCliente_PruebasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 427, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(503, 503, 503))
            .addGroup(PanelCliente_PruebasLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(PanelCliente_PruebasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 818, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        PanelCliente_PruebasLayout.setVerticalGroup(
            PanelCliente_PruebasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelCliente_PruebasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelCliente_PruebasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20)
                    .addComponent(jLabel7))
                .addGap(30, 30, 30)
                .addGroup(PanelCliente_PruebasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelCliente_PruebasLayout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 436, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        panelPestaniaCliente.addTab("Mi perfil", PanelCliente_Pruebas);

        panelProducto.setBackground(new java.awt.Color(255, 255, 255));
        panelProducto.setForeground(new java.awt.Color(204, 204, 255));

        tablaProductoCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Nombre", "Categoria", "Precio", "Marca", "Color", "Estatus", "Stock"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaProductoCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaProductoClienteMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablaProductoCliente);

        btnActualizarTablaProductoCliente.setBackground(new java.awt.Color(18, 125, 194));
        btnActualizarTablaProductoCliente.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnActualizarTablaProductoCliente.setForeground(new java.awt.Color(255, 255, 255));
        btnActualizarTablaProductoCliente.setText("Actualizar tabla");
        btnActualizarTablaProductoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarTablaProductoClienteActionPerformed(evt);
            }
        });

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/logo_fondoblanco_chico.PNG"))); // NOI18N

        jPanel2.setBackground(new java.awt.Color(18, 125, 194));

        jLabel8.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Id producto:");

        txtIdProducto.setEditable(false);
        txtIdProducto.setBackground(new java.awt.Color(204, 204, 204));
        txtIdProducto.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Nombre:");

        txtNombreProducto.setEditable(false);
        txtNombreProducto.setBackground(new java.awt.Color(204, 204, 204));
        txtNombreProducto.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Marca:");

        txtMarcaProducto.setEditable(false);
        txtMarcaProducto.setBackground(new java.awt.Color(204, 204, 204));
        txtMarcaProducto.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Color:");

        txtColorProducto.setEditable(false);
        txtColorProducto.setBackground(new java.awt.Color(204, 204, 204));
        txtColorProducto.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Precio unitario:");
        jLabel10.setToolTipText("");

        txtPrecioUnitarioProducto.setEditable(false);
        txtPrecioUnitarioProducto.setBackground(new java.awt.Color(204, 204, 204));
        txtPrecioUnitarioProducto.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N

        jLabel16.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Estatus:");

        txtEstatusProducto.setEditable(false);
        txtEstatusProducto.setBackground(new java.awt.Color(204, 204, 204));
        txtEstatusProducto.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N

        jLabel15.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Stock:");

        txtStockProducto.setEditable(false);
        txtStockProducto.setBackground(new java.awt.Color(204, 204, 204));
        txtStockProducto.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Cantidad deseada:");

        txtCantidadComprar.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        txtCantidadComprar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCantidadComprarKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadComprarKeyTyped(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Precio final: $");

        txtPrecioFinal.setEditable(false);
        txtPrecioFinal.setBackground(new java.awt.Color(204, 255, 255));
        txtPrecioFinal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtPrecioFinal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrecioFinalActionPerformed(evt);
            }
        });

        btnComprar.setBackground(new java.awt.Color(255, 255, 255));
        btnComprar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnComprar.setText("Comprar");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(84, 84, 84)
                        .addComponent(txtMarcaProducto))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(94, 94, 94)
                        .addComponent(txtColorProducto))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addComponent(txtCantidadComprar))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                        .addComponent(txtPrecioFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel8))
                        .addGap(45, 45, 45)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtIdProducto)
                            .addComponent(txtNombreProducto)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel16)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtStockProducto)
                            .addComponent(txtEstatusProducto)
                            .addComponent(txtPrecioUnitarioProducto, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnComprar)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtIdProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtNombreProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtMarcaProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtColorProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtPrecioUnitarioProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtEstatusProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtStockProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtCantidadComprar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtPrecioFinal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(btnComprar)
                .addGap(26, 26, 26))
        );

        javax.swing.GroupLayout panelProductoLayout = new javax.swing.GroupLayout(panelProducto);
        panelProducto.setLayout(panelProductoLayout);
        panelProductoLayout.setHorizontalGroup(
            panelProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelProductoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel21)
                .addGap(507, 507, 507))
            .addGroup(panelProductoLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(panelProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 928, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnActualizarTablaProductoCliente, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        panelProductoLayout.setVerticalGroup(
            panelProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelProductoLayout.createSequentialGroup()
                .addGroup(panelProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelProductoLayout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addGap(35, 35, 35)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnActualizarTablaProductoCliente)
                        .addGap(22, 22, 22)))
                .addContainerGap(112, Short.MAX_VALUE))
        );

        panelPestaniaCliente.addTab("Productos", panelProducto);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(panelPestaniaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 1324, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(panelPestaniaCliente)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnActualizarTablaProductoClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarTablaProductoClienteActionPerformed
        //Modelo de tabla de productos
        /*
        DefaultTableModel modeloTablaProducto = new DefaultTableModel();
        tablaProductoCliente.setModel(modeloTablaProducto); //Se asigna modelo de tabla a tabla de productos
        
        //Codigo para llenar la tabla
        //Objetos para preparar conexion y recuperr informacion
        PreparedStatement ps = null;
        ResultSet rs = null;
        //Se intenta abrir la conexion
        ConexionTiendaBD_DOS conexionBD = new ConexionTiendaBD_DOS(); //Se crea objeto para conexioncon Base de datos
        //Connection nuevaConexion = conexionBD.getConnection(); //Se realiza la conexionc on la BD
        Connection nuevaConexion = conexionBD.getConnection(); //Se abre conexion con BD
        
        try {
             
             ps = nuevaConexion.prepareStatement("SELECT idProducto, nombre, idCategoria, precio, marca, color, stock FROM producto");
             rs = ps.executeQuery(); //Se recuperan los datos realizando la consulta
            //Modelo de tabla
            modeloTablaProducto.addColumn("ID Producto");
            modeloTablaProducto.addColumn("Nombre");
            modeloTablaProducto.addColumn("Categoria");
            modeloTablaProducto.addColumn("Precio");
            modeloTablaProducto.addColumn("Marca");
            modeloTablaProducto.addColumn("Color");  
            modeloTablaProducto.addColumn("Stock");
            
            //Calcular numero de columas recuperadas en la consulta
            ResultSetMetaData rsMD = rs.getMetaData();
            int cantidadColumnas = rsMD.getColumnCount(); //Se obtiene el numero de columnas
             
            //Llenado de tabla
             while (rs.next()){ ///Mientras sigan habiendo registros se añade una nueva fila
                 //Se crea arreglo de tipo objet
                 Object fila[] = new Object[cantidadColumnas]; //Arreglo de tipo Objet de 7 elementos
                                            //Cada arreglo almacena un registro de producto
                //Llenado de tabla
                for(int i = 0 ; i < cantidadColumnas; i++){
                    fila[i] = rs.getObject(i+1);
                }
                
                modeloTablaProducto.addRow(fila);//Se añade una fila al modelo de la tabla
             }
            
        } catch (Exception e) {
            System.err.println("Error en carga de tabla productos: "+e);
        }finally{
            //Se cierra la conexion con la BD
            try {
                nuevaConexion.close();
            } catch (Exception e) {
                System.err.println("ERROR AL CERRAR LA CONEXION: " + e);
            }
        }
        */
    }//GEN-LAST:event_btnActualizarTablaProductoClienteActionPerformed

    private void tablaProductoClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaProductoClienteMouseClicked
        //==========================================================
        //Función donde se deecta un clic en la tabla
        //=========================================================
        
        //Objetos para preparar conexion y recuperr informacion
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        //Se intenta realizar una consulta a la Base de datos
        ConexionTiendaBD_DOS conexionBD = new ConexionTiendaBD_DOS(); //Se crea objeto para conexioncon Base de datos
        //Connection nuevaConexion = conexionBD.getConnection(); //Se realiza la conexionc on la BD
        Connection nuevaConexion = conexionBD.getConnection(); //Se abre conexion con BD
        try {
            //Como se requiere conocer que fila se ha seleccionado de la tabla, se crea una variable de tipo int
            int fila = tablaProductoCliente.getSelectedRow(); //Se obtiene el numero de la fila seleccionada
            
            //Ahora que se conocer el numero de la fila, se obtiene el id del producto de la fila seleccionada
            String idProducto = tablaProductoCliente.getValueAt(fila, 0).toString(); //Selecciona el dato de la fila numero << fila >> en la columna 0
            
            //Ahora que ya se cuenta con el id del producto, se realiza una consulta
            ps = nuevaConexion.prepareStatement("SELECT idProducto, nombre, marca, color, precio, estatus, stock FROM producto WHERE idProducto = ?");
            
            ps.setInt(1, Integer.parseInt(idProducto)); 
            rs = ps.executeQuery(); //Se ejecuta la consulta y se guarda el registro
            
            //Se rellena el formulaario mietras se encuentren datos
            while(rs.next()){
                txtIdProducto.setText(String.valueOf(rs.getInt("idProducto")));
                txtNombreProducto.setText(rs.getString("nombre"));
                txtMarcaProducto.setText(rs.getString("marca"));
                txtColorProducto.setText(rs.getString("color"));
                txtPrecioUnitarioProducto.setText(String.valueOf(rs.getDouble("precio")));
                txtEstatusProducto.setText(rs.getString("estatus"));
                txtStockProducto.setText(String.valueOf(rs.getInt("stock")));
                txtCantidadComprar.setText(null); //Cada que se seleccione un nuevo producto, se vacia la cantidad de productos a comprar
                txtPrecioFinal.setValue(null); //Cada que se seleccione un nuevo prodcuto se vacia el precio final
                btnComprar.setEnabled(false);
                //Se habilitan el boton de compra y campo de cantidad a escoger si existe disponibilidad de producto
                if( !validarCampoEstatus() ){
                    //Si no hay disponibilidad de prodcuto, se inhabilitan los botnode se compra
                    txtCantidadComprar.setEnabled(false);
                    btnComprar.setEnabled(false);
                }else{
                    txtCantidadComprar.setEnabled(true);
                }
            }
            
        } catch (Exception e) {
            System.err.println("Error al seleccionar elemento de la tabla: "+e);
        }finally{
            try {
                nuevaConexion.close();//Se cierra la conexion con la BD
            } catch (Exception e) {
                System.err.println("Error al cerrar conexion a BD desde la tabla: "+e);
            }
        }
    }//GEN-LAST:event_tablaProductoClienteMouseClicked

    private void txtCantidadComprarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadComprarKeyTyped
        //VALIDACION DE SOLO NUMEROS ENTEROS COMO CANTIDAD DE PRODUCTO A COMPRAR
        char digito = evt.getKeyChar(); //Se obtiene el caracter digitado
        
        //Se valida que el caracter este dentro del rango de productos posibles a comprar
        if( ((digito < '0' || digito > '9')) && (digito != '\b') ){
            evt.consume(); // ignorar el evento de teclado
            System.out.println("CARACTER NO VALIDO");
            
        }else{
            
        }
    }//GEN-LAST:event_txtCantidadComprarKeyTyped

    private void txtCantidadComprarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadComprarKeyReleased
        //Evento cuando se suelte la tecla
        calcularPrecioFinalCompra();
    }//GEN-LAST:event_txtCantidadComprarKeyReleased

    private void txtPrecioFinalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecioFinalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioFinalActionPerformed

    private void txtTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyTyped
        char digito = evt.getKeyChar(); //Se obtiene el caracter digitado
        
        //Se valida que el caracter este dentro del rango de productos posibles a comprar
        if( ((digito < '0' || digito > '9')) && (digito != '\b') ){
            evt.consume(); // ignorar el evento de teclado
            System.out.println("CARACTER NO VALIDO");
            
        }else{
            
        }
    }//GEN-LAST:event_txtTelefonoKeyTyped
/**
 * 
 * @param args 
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
            java.util.logging.Logger.getLogger(VentanaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaCliente().setVisible(true);
            }
        });
    }
    
    /**
     * ===================================================================
     * ===================================================================
     * METODOS DE LA CLASE
     * ===================================================================
     * ===================================================================
     */
     private void calcularPrecioFinalCompra(){
         if((!txtCantidadComprar.getText().isEmpty()) ){//Se valida que la cantidad no este vacia
            //se valida que el valor este dentro del rango disponible
            int cantidadComprar;
            int stockDisponible;
            cantidadComprar = Integer.parseInt(txtCantidadComprar.getText());
            stockDisponible = Integer.parseInt(txtStockProducto.getText());
            if ( cantidadComprar > 0 && cantidadComprar <= stockDisponible ){
                double precioUnitario;
                double precioFinal;
                cantidadComprar = Integer.parseInt(txtCantidadComprar.getText());
                precioUnitario = Double.parseDouble(txtPrecioUnitarioProducto.getText());
                precioFinal = cantidadComprar * precioUnitario;
                //txtPrecioFinal_Beta.setText(String.valueOf(precioFinal));
                txtPrecioFinal.setValue(precioFinal);
                btnComprar.setEnabled(true);//Se habilita el bton de compra
            }else{
                //Si la cantidad no es valida, se deshabilita el boton de comprar y se vacia la caja de texto
                txtPrecioFinal.setValue(null);
                btnComprar.setEnabled(false);
            }
            
            
        }else{
            //Si la caja esta vacia se deshabilita el boton de compra y se vacia la caja de texto
            txtPrecioFinal.setValue(null);
            btnComprar.setEnabled(false);
        }
     }
     
     private boolean validarCampoEstatus(){
         if ( txtEstatusProducto.getText().isEmpty() ){
             return false; //Retorna falso si esta vacio
         }else{
             return (txtEstatusProducto.getText().toLowerCase()).equals("disponible"); //Retorna false o true dependiendo de si hay o no siponibilidad de produto
         }
     }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelCliente_Pruebas;
    public javax.swing.JButton btnActualizarCliente;
    public javax.swing.JButton btnActualizarTablaProductoCliente;
    public javax.swing.JButton btnComprar;
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
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel panelCompra;
    public javax.swing.JTabbedPane panelPestaniaCliente;
    public javax.swing.JPanel panelProducto;
    public javax.swing.JTable tablaCompraCliente;
    public javax.swing.JTable tablaProductoCliente;
    public javax.swing.JTextArea txtAreaDireccion;
    public javax.swing.JTextField txtCantidadComprar;
    public javax.swing.JTextField txtColorProducto;
    public javax.swing.JTextField txtCorreo;
    public javax.swing.JTextField txtEstatusProducto;
    public javax.swing.JTextField txtIdCliente;
    public javax.swing.JTextField txtIdProducto;
    public javax.swing.JTextField txtMarcaProducto;
    public javax.swing.JTextField txtNombreCompleto;
    public javax.swing.JTextField txtNombreProducto;
    public javax.swing.JTextField txtPassword;
    public javax.swing.JFormattedTextField txtPrecioFinal;
    public javax.swing.JTextField txtPrecioUnitarioProducto;
    public javax.swing.JTextField txtStockProducto;
    public javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
