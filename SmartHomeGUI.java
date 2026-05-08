package ev_kontrol_20_proje;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Member 1
 * Smart Home OS - Central Graphical User Interface (GUI)
 * Includes Advanced User Session State Management
 */
public class SmartHomeGUI extends JFrame {
    
    // --- ENCAPSULATION: Private UI Components ---
    private CardLayout cardLayout; 
    private JPanel mainPanel;      
    private UserManager userManager; 
    private SmartHomeHub hub;        

    // --- ACTIVE USER MEMORY ---
    private Map<Smartdevice, boolean[]> ocakHafizasi = new HashMap<>();
    private Map<Smartdevice, Integer> airfryerHafizasi = new HashMap<>();
    private Map<Smartdevice, String> supurgeHafizasi = new HashMap<>();

    // --- MULTI-VERSE SESSION MANAGEMENT ---
    private String loggedInUsername = null;
    private Map<String, UserSessionState> userSessions = new HashMap<>();

    // İç Sınıf: Kullanıcının evinin anlık fotoğrafını (Snapshot) tutar
    // HATA BURADAYDI, DEĞİŞKEN İSİMLERİ DÜZELTİLDİ:
    private class UserSessionState {
        Map<Smartdevice, boolean[]> ocakHafizasi = new HashMap<>();
        Map<Smartdevice, Integer> airfryerHafizasi = new HashMap<>();
        Map<Smartdevice, String> supurgeHafizasi = new HashMap<>();
        
        Map<Smartdevice, Boolean> onOffStates = new HashMap<>();
        Map<Smartdevice, Integer> intStates = new HashMap<>();
        Map<Smartdevice, String> stringStates = new HashMap<>();
    }

    // --- MODERN COLOR PALETTE ---
    private final Color bgDark = new Color(30, 33, 36);
    private final Color panelDark = new Color(43, 48, 53);
    private final Color textLight = new Color(230, 230, 230);
    private final Color accentColor = new Color(0, 153, 255);
    private final Color successColor = new Color(46, 204, 113);
    private final Color dangerColor = new Color(231, 76, 60);

    public SmartHomeGUI() {
        userManager = new UserManager();
        hub = SmartHomeHub.getInstance(); 
        loadMockDevices();

        setTitle("SmartHome OS - Central Control Panel");
        setSize(900, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setLocationRelativeTo(null); 

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        mainPanel.setBackground(bgDark);

        mainPanel.add(createLoginPanel(), "Login");
        mainPanel.add(createMainMenuPanel(), "MainMenu");

        add(mainPanel);
        cardLayout.show(mainPanel, "Login");
    }

    private void loadMockDevices() {
        if (hub.getTotalDeviceCount() == 0) {
            hub.addDevice(new Airfryer());
            hub.addDevice(new Ocak());
            hub.addDevice(new Buzdolabi());
            hub.addDevice(new Kettle());
            hub.addDevice(new KahveMakinesi());
            hub.addDevice(new RobotSupurge());
            hub.addDevice(new HavaTemizleyici());
            hub.addDevice(new Derindondurucu());
            hub.addDevice(new Televizyon("TV_01", "Samsung 4K TV", "Living Room"));
            hub.addDevice(new SesSistemi());
            hub.addDevice(new AkilliLamba("L_01", "Living Room"));
            hub.addDevice(new SeritLamba("L_02", "Bedroom", 150));
            hub.addDevice(new AkilliKamera("CAM_01", "Front Door"));
            hub.addDevice(new AkilliKilit());
            hub.addDevice(new SiberGuvenlikBotu());
        }
    }

    // --- 1. AUTHENTICATION SCREEN ---
    private JPanel createLoginPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(bgDark);

        JPanel loginBox = new JPanel(new GridLayout(5, 1, 10, 15));
        loginBox.setBackground(panelDark);
        loginBox.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(accentColor, 2, true),
            new EmptyBorder(40, 50, 40, 50)
        ));

        JLabel title = new JLabel("System Login", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 26));
        title.setForeground(textLight);

        JTextField userField = new JTextField();
        userField.setBorder(BorderFactory.createTitledBorder("Username"));
        JPasswordField passField = new JPasswordField();
        passField.setBorder(BorderFactory.createTitledBorder("Password"));

        JButton loginBtn = createButton("Login", accentColor);
        JButton registerBtn = createButton("Register", panelDark);
        registerBtn.setBorder(BorderFactory.createLineBorder(textLight, 1));

        loginBtn.addActionListener(e -> {
            try {
                String username = userField.getText().trim();
                if (userManager.loginUser(username, new String(passField.getPassword()))) {
                    loadUserSession(username); // KULLANICIYA ÖZEL EVRENİ YÜKLE
                    cardLayout.show(mainPanel, "MainMenu"); 
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid credentials!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) { JOptionPane.showMessageDialog(this, ex.getMessage()); }
        });

        registerBtn.addActionListener(e -> {
            try {
                String u = userField.getText().trim();
                String p = new String(passField.getPassword()).trim();
                if (u.isEmpty() || p.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Fields cannot be empty!", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                userManager.registerUser(u, p);
                JOptionPane.showMessageDialog(this, "Registration Successful! Logging in...", "Success", JOptionPane.INFORMATION_MESSAGE);
                
                loadUserSession(u); // YENİ KULLANICIYA SIFIR EVREN OLUŞTUR
                cardLayout.show(mainPanel, "MainMenu");
            } catch (Exception ex) { 
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Registration Error", JOptionPane.ERROR_MESSAGE); 
            }
        });

        loginBox.add(title);
        loginBox.add(userField);
        loginBox.add(passField);
        loginBox.add(loginBtn);
        loginBox.add(registerBtn);
        panel.add(loginBox);
        return panel;
    }

    // --- 2. MAIN DASHBOARD ---
    private JPanel createMainMenuPanel() {
        JPanel panel = new JPanel(new BorderLayout(20, 20));
        panel.setBackground(bgDark);
        panel.setBorder(new EmptyBorder(30, 30, 30, 30));

        JLabel titleLabel = new JLabel("Smart Categories", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));
        titleLabel.setForeground(textLight);
        panel.add(titleLabel, BorderLayout.NORTH);

        JPanel gridPanel = new JPanel(new GridLayout(2, 3, 20, 20));
        gridPanel.setBackground(bgDark);

        gridPanel.add(createCategoryButton("Kitchen & Cooking", "Mutfak", "mutfak ve pisirme.jpeg"));
        gridPanel.add(createCategoryButton("Cleaning & Climate", "Temizlik", "temizlik.jpeg"));
        gridPanel.add(createCategoryButton("Entertainment", "Eglence", "ev eğlence sistemi.jpeg"));
        gridPanel.add(createCategoryButton("Security Systems", "Guvenlik", "guvenlik.jpeg"));
        gridPanel.add(createCategoryButton("Lighting Controls", "Aydinlatma", "aydınlatma.jpeg"));

        JButton logoutBtn = createButton("Log Out", dangerColor);
        logoutBtn.addActionListener(e -> {
            saveCurrentSession(); // ÇIKMADAN ÖNCE EVİN FOTOĞRAFINI ÇEK VE KAYDET
            resetAllDevices();    // SONRA FİZİKSEL EVİ SIFIRLA
            loggedInUsername = null;
            cardLayout.show(mainPanel, "Login");
        });
        gridPanel.add(logoutBtn);

        panel.add(gridPanel, BorderLayout.CENTER);
        return panel;
    }

    private JButton createCategoryButton(String title, String categoryCode, String imagePath) {
        JButton btn = new JButton(title);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btn.setBackground(panelDark);
        btn.setForeground(textLight);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createLineBorder(accentColor, 2, true));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        try {
            ImageIcon originalIcon = new ImageIcon(imagePath);
            if (originalIcon.getIconWidth() > -1) {
                Image scaledImage = originalIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
                btn.setIcon(new ImageIcon(scaledImage));
                btn.setHorizontalTextPosition(SwingConstants.CENTER);
                btn.setVerticalTextPosition(SwingConstants.BOTTOM);
                btn.setIconTextGap(15);
            }
        } catch (Exception ex) {}

        btn.addActionListener(e -> openCategoryScreen(title, categoryCode));
        return btn;
    }

    // --- 3. DYNAMIC SUB-MENU (Category View) ---
    private void openCategoryScreen(String title, String categoryCode) {
        JPanel catPanel = new JPanel(new BorderLayout(15, 15));
        catPanel.setBackground(bgDark);
        catPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(bgDark);
        
        JButton backBtn = createButton("<< Back", panelDark);
        backBtn.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        backBtn.addActionListener(e -> cardLayout.show(mainPanel, "MainMenu"));
        
        JLabel headerLabel = new JLabel(title, SwingConstants.CENTER);
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 26));
        headerLabel.setForeground(accentColor);
        
        topPanel.add(backBtn, BorderLayout.WEST);
        topPanel.add(headerLabel, BorderLayout.CENTER);
        catPanel.add(topPanel, BorderLayout.NORTH);

        JPanel devicesPanel = new JPanel();
        devicesPanel.setLayout(new BoxLayout(devicesPanel, BoxLayout.Y_AXIS));
        devicesPanel.setBackground(bgDark);

        for (Smartdevice device : hub.getDeviceList()) {
            boolean isMatch = false;
            switch (categoryCode) {
                case "Mutfak":
                    if (device instanceof CookingDevice || device instanceof DrinkingDevice || device instanceof Buzdolabi) isMatch = true;
                    break;
                case "Temizlik":
                    if (device instanceof CleaningDevice || (device instanceof SicaklikDevice && !(device instanceof Buzdolabi))) isMatch = true;
                    break;
                case "Eglence":
                    if (device instanceof TechDevice) isMatch = true;
                    break;
                case "Guvenlik":
                    if (device instanceof SecurityDevice) isMatch = true;
                    break;
                case "Aydinlatma":
                    if (device instanceof LightingDevice) isMatch = true;
                    break;
            }

            if (isMatch) {
                devicesPanel.add(createDeviceCard(device, title, categoryCode));
                devicesPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            }
        }

        JScrollPane scrollPane = new JScrollPane(devicesPanel);
        scrollPane.setBorder(null);
        scrollPane.getViewport().setBackground(bgDark);
        catPanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(catPanel, "CurrentCategory");
        cardLayout.show(mainPanel, "CurrentCategory");
    }

    // --- DEVICE CARD COMPONENT ---
    private JPanel createDeviceCard(Smartdevice device, String title, String categoryCode) {
        JPanel card = new JPanel(new BorderLayout(10, 10));
        card.setBackground(panelDark);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.GRAY, 1, true),
            new EmptyBorder(15, 15, 15, 15)
        ));

        String statusText = "Off";
        if (device instanceof LightingDevice) {
            int b = ((LightingDevice)device).getBrightness();
            if (b > 0) {
                if (device instanceof SeritLamba) statusText = "Brightness: " + b + "% | Color: " + ((SeritLamba)device).getColorCode();
                else statusText = "Brightness: " + b + "%";
            }
        } else if (device instanceof CookingDevice) {
            if (device instanceof Ocak && ((CookingDevice)device).getIsCooking()) {
                statusText = "Active (" + ((Ocak)device).getActiveBurners() + " Burners On)";
            } else if (device.getClass().getSimpleName().equals("Airfryer") && ((CookingDevice)device).getIsCooking()) {
                statusText = "Running (" + airfryerHafizasi.getOrDefault(device, 180) + "°C)";
            } else {
                statusText = ((CookingDevice)device).getIsCooking() ? "Running" : "Standby";
            }
        } else if (device instanceof CleaningDevice) {
            if (device instanceof RobotSupurge && ((CleaningDevice)device).getIsCleaning()) {
                statusText = "Cleaning (" + supurgeHafizasi.getOrDefault(device, "Whole House") + ")";
            } else {
                statusText = ((CleaningDevice)device).getIsCleaning() ? "Cleaning" : "Standby";
            }
        } else if (device instanceof DrinkingDevice) {
            if (device instanceof KahveMakinesi && ((DrinkingDevice)device).getIsBrewing()) {
                statusText = "Brewing (" + ((KahveMakinesi)device).getCoffeeType() + ")";
            } else {
                statusText = ((DrinkingDevice)device).getIsBrewing() ? "Brewing" : "Standby";
            }
        } else if (device instanceof TechDevice) {
            if (device instanceof Televizyon && ((TechDevice)device).getIsPlaying()) {
                statusText = "Active (Vol: " + ((Televizyon)device).getVolumeLevel() + "%)";
            } else {
                statusText = ((TechDevice)device).getIsPlaying() ? "Active" : "Standby";
            }
        } else if (device instanceof SecurityDevice) {
            statusText = ((SecurityDevice)device).getIsSecurityActive() ? "Armed" : "Disarmed";
        } else if (device instanceof SicaklikDevice) {
            statusText = "Compressor: " + ((SicaklikDevice)device).getCompressorStatus();
        }
        
        JLabel nameLabel = new JLabel(device.getDeviceName() + " (" + statusText + ")");
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        nameLabel.setForeground(textLight);
        card.add(nameLabel, BorderLayout.CENTER);

        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        actionPanel.setBackground(panelDark);

        JButton startBtn = createButton("Start / Configure", successColor);
        JButton stopBtn = createButton("Stop", dangerColor);

        startBtn.addActionListener(e -> {
            try {
                if (device instanceof Ocak) {
                    JPanel ocakPanel = new JPanel(new GridLayout(2, 2, 15, 15));
                    ocakPanel.setPreferredSize(new Dimension(280, 280));
                    ocakPanel.setBackground(bgDark);
                    ocakPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

                    JToggleButton[] burners = new JToggleButton[4];
                    String[] positions = {"Top Left", "Top Right", "Bottom Left", "Bottom Right"};
                    
                    ImageIcon fireIcon = null;
                    try {
                        ImageIcon originalFire = new ImageIcon("ates.png"); 
                        if (originalFire.getIconWidth() > -1) {
                            Image scaledFire = originalFire.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
                            fireIcon = new ImageIcon(scaledFire);
                        }
                    } catch (Exception ex) {}

                    final ImageIcon finalFireIcon = fireIcon;
                    boolean[] durum = ocakHafizasi.getOrDefault(device, new boolean[4]);

                    for (int i = 0; i < 4; i++) {
                        burners[i] = new JToggleButton();
                        burners[i].setFont(new Font("Segoe UI", Font.BOLD, 14));
                        burners[i].setFocusPainted(false);
                        burners[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
                        burners[i].setHorizontalTextPosition(SwingConstants.CENTER);
                        burners[i].setVerticalTextPosition(SwingConstants.BOTTOM);
                        
                        final String pos = positions[i];

                        if (durum[i]) {
                            burners[i].setSelected(true);
                            burners[i].setBackground(dangerColor);
                            burners[i].setForeground(Color.WHITE);
                            burners[i].setText(pos); 
                            if (finalFireIcon != null) burners[i].setIcon(finalFireIcon); 
                        } else {
                            burners[i].setSelected(false);
                            burners[i].setBackground(panelDark);
                            burners[i].setForeground(textLight);
                            burners[i].setText(pos + " (Off)");
                            burners[i].setIcon(null);
                        }

                        JToggleButton btn = burners[i];
                        btn.addActionListener(ev -> {
                            if (btn.isSelected()) {
                                btn.setBackground(dangerColor);
                                btn.setForeground(Color.WHITE);
                                btn.setText(pos);
                                if (finalFireIcon != null) btn.setIcon(finalFireIcon);
                            } else {
                                btn.setBackground(panelDark);
                                btn.setForeground(textLight);
                                btn.setText(pos + " (Off)");
                                btn.setIcon(null);
                            }
                        });
                        ocakPanel.add(burners[i]);
                    }

                    int result = JOptionPane.showConfirmDialog(this, ocakPanel, "Select Burners to Ignite", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                    if (result == JOptionPane.OK_OPTION) {
                        int activeCount = 0;
                        for (int i = 0; i < 4; i++) {
                            durum[i] = burners[i].isSelected();
                            if (durum[i]) activeCount++;
                        }
                        if (activeCount == 0) {
                            JOptionPane.showMessageDialog(this, "No burners selected. Operation cancelled.");
                            return;
                        }
                        ocakHafizasi.put(device, durum);
                        ((Ocak) device).setActiveBurners(activeCount);
                        ((Cookable) device).startCooking();
                    } else return; 
                }
                else if (device.getClass().getSimpleName().equals("Airfryer")) {
                    int prevTemp = airfryerHafizasi.getOrDefault(device, 180); 
                    JSlider tempSlider = new JSlider(80, 220, prevTemp);
                    tempSlider.setMajorTickSpacing(20);
                    tempSlider.setMinorTickSpacing(10);
                    tempSlider.setPaintTicks(true);
                    tempSlider.setPaintLabels(true);

                    JLabel valueLabel = new JLabel("Temperature: " + prevTemp + "°C", SwingConstants.CENTER);
                    valueLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
                    tempSlider.addChangeListener(ce -> valueLabel.setText("Temperature: " + tempSlider.getValue() + "°C"));

                    JPanel panel = new JPanel(new BorderLayout(10, 10));
                    panel.add(new JLabel("Set Airfryer Temperature:"), BorderLayout.NORTH);
                    panel.add(tempSlider, BorderLayout.CENTER);
                    panel.add(valueLabel, BorderLayout.SOUTH);

                    if (JOptionPane.showConfirmDialog(this, panel, "Airfryer Settings", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE) == JOptionPane.OK_OPTION) {
                        airfryerHafizasi.put(device, tempSlider.getValue());
                        ((CookingDevice) device).startCooking();
                    } else return;
                }
                else if (device instanceof CookingDevice) {
                    ((Cookable) device).startCooking();
                }
                else if (device instanceof KahveMakinesi) {
                    KahveMakinesi km = (KahveMakinesi) device;
                    String[] coffees = {"Filter Coffee", "Espresso", "Americano", "Latte", "Cappuccino", "Turkish Coffee"};
                    JComboBox<String> coffeeBox = new JComboBox<>(coffees);
                    coffeeBox.setSelectedItem(km.getCoffeeType());
                    coffeeBox.setFont(new Font("Segoe UI", Font.PLAIN, 16));

                    if (JOptionPane.showConfirmDialog(this, coffeeBox, "Select Coffee Type", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE) == JOptionPane.OK_OPTION) {
                        km.setCoffeeType((String) coffeeBox.getSelectedItem());
                        km.startBrewing();
                    } else return;
                }
                else if (device instanceof DrinkingDevice) {
                    ((Drinkable) device).startBrewing();
                }
                else if (device instanceof RobotSupurge) {
                    String[] rooms = {"Whole House", "Living Room", "Kitchen", "Bedroom", "Hallway", "Kids Room"};
                    JComboBox<String> roomBox = new JComboBox<>(rooms);
                    roomBox.setSelectedItem(supurgeHafizasi.getOrDefault(device, "Whole House"));
                    roomBox.setFont(new Font("Segoe UI", Font.PLAIN, 16));

                    if (JOptionPane.showConfirmDialog(this, roomBox, "Select Area to Clean", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE) == JOptionPane.OK_OPTION) {
                        String secim = (String) roomBox.getSelectedItem();
                        supurgeHafizasi.put(device, secim);
                        ((Cleanable) device).startCleaning();
                    } else return;
                }
                else if (device instanceof CleaningDevice) {
                    ((Cleanable) device).startCleaning();
                }
                else if (device instanceof Televizyon) {
                    Televizyon tv = (Televizyon) device;
                    JSlider volSlider = new JSlider(0, 100, tv.getVolumeLevel());
                    volSlider.setMajorTickSpacing(25);
                    volSlider.setMinorTickSpacing(5);
                    volSlider.setPaintTicks(true);
                    volSlider.setPaintLabels(true);

                    JLabel valueLabel = new JLabel("Volume Level: " + volSlider.getValue() + "%", SwingConstants.CENTER);
                    valueLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
                    volSlider.addChangeListener(ce -> valueLabel.setText("Volume Level: " + volSlider.getValue() + "%"));

                    JPanel panel = new JPanel(new BorderLayout(10, 10));
                    panel.add(new JLabel("Set TV Volume:"), BorderLayout.NORTH);
                    panel.add(volSlider, BorderLayout.CENTER);
                    panel.add(valueLabel, BorderLayout.SOUTH);

                    if (JOptionPane.showConfirmDialog(this, panel, "TV Settings", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE) == JOptionPane.OK_OPTION) {
                        tv.setVolumeLevel(volSlider.getValue());
                        tv.startPlaying();
                    } else return;
                }
                else if (device instanceof TechDevice) {
                    ((Playable) device).startPlaying();
                }
                else if (device instanceof SeritLamba) {
                    SeritLamba led = (SeritLamba) device;
                    JPanel panel = new JPanel(new GridLayout(2, 1, 15, 15));
                    
                    JButton colorBtn = new JButton("Change Color (Current: " + led.getColorCode() + ")");
                    colorBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    colorBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
                    
                    String[] newColor = {led.getColorCode()}; 
                    
                    colorBtn.addActionListener(ev -> {
                        Color c = JColorChooser.showDialog(this, "RGB Strip LED Color", Color.WHITE);
                        if (c != null) {
                            String hex = String.format("#%02x%02x%02x", c.getRed(), c.getGreen(), c.getBlue());
                            newColor[0] = hex;
                            colorBtn.setText("Selected Color: " + hex);
                            colorBtn.setBackground(c);
                            double luminance = 0.299 * c.getRed() + 0.587 * c.getGreen() + 0.114 * c.getBlue();
                            colorBtn.setForeground(luminance > 128 ? Color.BLACK : Color.WHITE);
                        }
                    });

                    JSlider lightSlider = new JSlider(0, 100, led.getBrightness());
                    lightSlider.setMajorTickSpacing(25);
                    lightSlider.setPaintTicks(true);
                    lightSlider.setPaintLabels(true);

                    JPanel sliderPanel = new JPanel(new BorderLayout());
                    JLabel valLabel = new JLabel("Brightness: " + led.getBrightness() + "%", SwingConstants.CENTER);
                    lightSlider.addChangeListener(ce -> valLabel.setText("Brightness: " + lightSlider.getValue() + "%"));
                    sliderPanel.add(new JLabel("Intensity:"), BorderLayout.NORTH);
                    sliderPanel.add(lightSlider, BorderLayout.CENTER);
                    sliderPanel.add(valLabel, BorderLayout.SOUTH);

                    panel.add(colorBtn);
                    panel.add(sliderPanel);

                    if (JOptionPane.showConfirmDialog(this, panel, "LED Strip Settings", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE) == JOptionPane.OK_OPTION) {
                        led.setColorCode(newColor[0]);
                        led.setBrightness(lightSlider.getValue());
                    } else return;
                }
                else if (device instanceof LightingDevice) {
                    LightingDevice light = (LightingDevice) device;
                    JSlider lightSlider = new JSlider(0, 100, light.getBrightness());
                    lightSlider.setMajorTickSpacing(25);
                    lightSlider.setMinorTickSpacing(5);
                    lightSlider.setPaintTicks(true);
                    lightSlider.setPaintLabels(true);

                    JLabel valueLabel = new JLabel("Brightness: " + light.getBrightness() + "%", SwingConstants.CENTER);
                    valueLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
                    
                    lightSlider.addChangeListener(ce -> valueLabel.setText("Brightness: " + lightSlider.getValue() + "%"));

                    JPanel sliderPanel = new JPanel(new BorderLayout(10, 10));
                    sliderPanel.add(new JLabel("Adjust Brightness Level:"), BorderLayout.NORTH);
                    sliderPanel.add(lightSlider, BorderLayout.CENTER);
                    sliderPanel.add(valueLabel, BorderLayout.SOUTH);

                    if (JOptionPane.showConfirmDialog(this, sliderPanel, "Lighting Settings", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE) == JOptionPane.OK_OPTION) {
                        light.setBrightness(lightSlider.getValue());
                    } else return; 
                }
                else if (device instanceof SecurityDevice) {
                    ((Securable) device).activateSecurity();
                }
                else if (device instanceof SicaklikDevice) {
                     ((SicaklikDevice) device).setCompressorStatus("Running");
                }
                
                openCategoryScreen(title, categoryCode); 
            } catch (Exception ex) { JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); }
        });

        stopBtn.addActionListener(e -> {
            if (device instanceof CookingDevice) {
                ((Cookable) device).stopCooking();
                if (device instanceof Ocak) ocakHafizasi.put(device, new boolean[4]); 
            }
            else if (device instanceof CleaningDevice) ((Cleanable) device).stopCleaning();
            else if (device instanceof DrinkingDevice) ((Drinkable) device).stopBrewing();
            else if (device instanceof TechDevice) ((Playable) device).stopPlaying();
            else if (device instanceof SecurityDevice) ((Securable) device).deactivateSecurity();
            else if (device instanceof LightingDevice) ((LightingDevice) device).setBrightness(0);
            else if (device instanceof SicaklikDevice) ((SicaklikDevice) device).setCompressorStatus("Standby");
            
            openCategoryScreen(title, categoryCode); 
        });

        actionPanel.add(startBtn);
        actionPanel.add(stopBtn);
        card.add(actionPanel, BorderLayout.EAST);

        return card;
    }

    private JButton createButton(String text, Color bgColor) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setBackground(bgColor);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    // --- STATE MANAGEMENT YARDIMCI METODLARI ---

    /**
     * Session Save: Çıkış yaparken kullanıcının özel ayarlarını ve cihaz durumlarını kaydeder.
     */
    private void saveCurrentSession() {
        if (loggedInUsername == null) return;
        
        UserSessionState state = new UserSessionState();
        
        state.ocakHafizasi.putAll(ocakHafizasi);
        state.airfryerHafizasi.putAll(airfryerHafizasi);
        state.supurgeHafizasi.putAll(supurgeHafizasi);
        
        for (Smartdevice device : hub.getDeviceList()) {
            if (device instanceof CookingDevice) state.onOffStates.put(device, ((CookingDevice) device).getIsCooking());
            else if (device instanceof CleaningDevice) state.onOffStates.put(device, ((CleaningDevice) device).getIsCleaning());
            else if (device instanceof DrinkingDevice) state.onOffStates.put(device, ((DrinkingDevice) device).getIsBrewing());
            else if (device instanceof TechDevice) state.onOffStates.put(device, ((TechDevice) device).getIsPlaying());
            else if (device instanceof SecurityDevice) state.onOffStates.put(device, ((SecurityDevice) device).getIsSecurityActive());
            
            if (device instanceof LightingDevice) state.intStates.put(device, ((LightingDevice)device).getBrightness());
            if (device instanceof SeritLamba) state.stringStates.put(device, ((SeritLamba)device).getColorCode());
            if (device instanceof Televizyon) state.intStates.put(device, ((Televizyon)device).getVolumeLevel());
            if (device instanceof KahveMakinesi) state.stringStates.put(device, ((KahveMakinesi)device).getCoffeeType());
            if (device instanceof SicaklikDevice) state.stringStates.put(device, ((SicaklikDevice)device).getCompressorStatus());
        }
        
        userSessions.put(loggedInUsername, state);
    }

    /**
     * Session Load: Kullanıcı giriş yaptığında eski ayarlarını hafızadan çekip fiziksel cihazlara uygular.
     */
    private void loadUserSession(String username) {
        loggedInUsername = username;
        resetAllDevices(); // Önce evi herkes için tertemiz sıfırla
        
        UserSessionState state = userSessions.get(username);
        if (state == null) return; // Kullanıcı ilk defa giriyorsa ev kapalı kalsın
        
        // GUI hafızalarını geri yükle
        ocakHafizasi.putAll(state.ocakHafizasi);
        airfryerHafizasi.putAll(state.airfryerHafizasi);
        supurgeHafizasi.putAll(state.supurgeHafizasi);
        
        // Fiziksel cihazlara komut göndererek eski durumlarına getir
        for (Smartdevice device : hub.getDeviceList()) {
            if (device instanceof LightingDevice) ((LightingDevice)device).setBrightness(state.intStates.getOrDefault(device, 0));
            if (device instanceof SeritLamba) ((SeritLamba)device).setColorCode(state.stringStates.getOrDefault(device, "#FFFFFF"));
            if (device instanceof Televizyon) ((Televizyon)device).setVolumeLevel(state.intStates.getOrDefault(device, 0));
            if (device instanceof KahveMakinesi) ((KahveMakinesi)device).setCoffeeType(state.stringStates.getOrDefault(device, "Filter Coffee"));
            if (device instanceof SicaklikDevice) ((SicaklikDevice)device).setCompressorStatus(state.stringStates.getOrDefault(device, "Standby"));
            
            Boolean isOn = state.onOffStates.getOrDefault(device, false);
            if (isOn) {
                try {
                    if (device instanceof CookingDevice) ((Cookable) device).startCooking();
                    else if (device instanceof CleaningDevice) ((Cleanable) device).startCleaning();
                    else if (device instanceof DrinkingDevice) ((Drinkable) device).startBrewing();
                    else if (device instanceof TechDevice) ((Playable) device).startPlaying();
                    else if (device instanceof SecurityDevice) ((Securable) device).activateSecurity();
                    
                    if (device instanceof SicaklikDevice) ((SicaklikDevice)device).setCompressorStatus(state.stringStates.getOrDefault(device, "Running"));
                } catch (Exception e) {}
            }
        }
    }

    /**
     * Physical Reset: Tüm arayüz hafızasını siler ve fiziksel cihazları kapatır.
     */
    private void resetAllDevices() {
        ocakHafizasi.clear();
        airfryerHafizasi.clear();
        supurgeHafizasi.clear();

        for (Smartdevice device : hub.getDeviceList()) {
            if (device instanceof CookingDevice) ((Cookable) device).stopCooking();
            else if (device instanceof CleaningDevice) ((Cleanable) device).stopCleaning();
            else if (device instanceof DrinkingDevice) ((Drinkable) device).stopBrewing();
            else if (device instanceof TechDevice) ((Playable) device).stopPlaying();
            else if (device instanceof SecurityDevice) ((Securable) device).deactivateSecurity();
            else if (device instanceof LightingDevice) ((LightingDevice) device).setBrightness(0);
            else if (device instanceof SicaklikDevice) ((SicaklikDevice) device).setCompressorStatus("Standby");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SmartHomeGUI().setVisible(true));
    }
}