import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class MainApp extends JFrame {

    ArrayList<Building> buildingList = new ArrayList<>();

    DefaultTableModel tableModel;
    JTable table;

    JComboBox<String> typeCombo;
    JTextField nameField, addressField, floorsField, extraField, revenueField;
    JComboBox<BuildingStatus> statusCombo;
    JLabel extraLabel, revenueLabel;

    public MainApp() {
        setTitle("City Management System");
        setSize(860, 520);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel header = new JLabel("Welcome", SwingConstants.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 18));
        header.setBackground(Color.lightGray);
        header.setOpaque(true);
        header.setBorder(BorderFactory.createEmptyBorder(14, 0, 14, 0));
        add(header, BorderLayout.NORTH);

        String[] columns = {"Name", "Address", "Status", "Type"};
        tableModel = new DefaultTableModel(columns, 0) {
            public boolean isCellEditable(int row, int col) { return false; }
        };
        table = new JTable(tableModel);
        table.setRowHeight(24);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(440, 0));

        JPanel form = new JPanel(new GridLayout(9, 2, 8, 8));
        form.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));

        form.add(new JLabel("Type:"));
        typeCombo = new JComboBox<>(new String[]{"Hospital", "Cinema", "Apartment"});
        form.add(typeCombo);

        form.add(new JLabel("Name:"));
        nameField = new JTextField();
        form.add(nameField);

        form.add(new JLabel("Address:"));
        addressField = new JTextField();
        form.add(addressField);

        form.add(new JLabel("Floors:"));
        floorsField = new JTextField();
        form.add(floorsField);

        form.add(new JLabel("Status:"));
        statusCombo = new JComboBox<>(BuildingStatus.values());
        form.add(statusCombo);

        extraLabel = new JLabel("Number of Beds:");
        form.add(extraLabel);
        extraField = new JTextField();
        form.add(extraField);

        revenueLabel = new JLabel("Monthly Revenue:");
        form.add(revenueLabel);
        revenueField = new JTextField();
        revenueField.setEnabled(false);
        form.add(revenueField);

        JButton addBtn = new JButton("Add Building");
        addBtn.setBackground(Color.green);
        addBtn.setForeground(Color.WHITE);
        addBtn.setFont(new Font("Arial", Font.BOLD, 12));
        form.add(addBtn);

        JButton deleteBtn = new JButton("Delete Building");
        deleteBtn.setBackground(Color.red);
        deleteBtn.setForeground(Color.WHITE);
        deleteBtn.setFont(new Font("Arial", Font.BOLD, 12));
        form.add(deleteBtn);

        JPanel center = new JPanel(new BorderLayout());
        center.add(scrollPane, BorderLayout.CENTER);
        center.add(form, BorderLayout.EAST);
        add(center, BorderLayout.CENTER);

        typeCombo.addActionListener(e -> {
            String selected = (String) typeCombo.getSelectedItem();
            if (selected.equals("Hospital")) {
                extraLabel.setText("Number of Beds:");
                revenueField.setEnabled(false);
                revenueField.setText("");
            } else if (selected.equals("Cinema")) {
                extraLabel.setText("Number of Screens:");
                revenueField.setEnabled(true);
            } else {
                extraLabel.setText("Number of Units:");
                revenueField.setEnabled(true);
            }
        });

        addBtn.addActionListener(e -> {
            try {
                String name    = nameField.getText().trim();
                String address = addressField.getText().trim();
                String type    = (String) typeCombo.getSelectedItem();
                BuildingStatus status = (BuildingStatus) statusCombo.getSelectedItem();

                if (name.isEmpty() || address.isEmpty() || floorsField.getText().trim().isEmpty()
                        || extraField.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int floors = Integer.parseInt(floorsField.getText().trim());

                for (Building b : buildingList) {
                    if (b.getName().equalsIgnoreCase(name)) {
                        throw new InvalidDataException("Building name '" + name + "' already exists.");
                    }
                }

                Building newBuilding = null;
                if (type.equals("Hospital")) {
                    int beds = Integer.parseInt(extraField.getText().trim());
                    newBuilding = new Hospital(name, address, floors, status, beds);

                } else if (type.equals("Cinema")) {
                    int screens = Integer.parseInt(extraField.getText().trim());
                    double revenue = Double.parseDouble(revenueField.getText().trim());
                    newBuilding = new Cinema(name, address, floors, status, screens, revenue);

                } else {
                    int units = Integer.parseInt(extraField.getText().trim());
                    double revenue = Double.parseDouble(revenueField.getText().trim());
                    newBuilding = new Apartment(name, address, floors, status, units, revenue);
                }

                addToList(newBuilding);
                JOptionPane.showMessageDialog(this, name + " added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

                nameField.setText("");
                addressField.setText("");
                floorsField.setText("");
                extraField.setText("");
                revenueField.setText("");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Floors / numeric fields must be numbers!", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (InvalidDataException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Validation Failed", JOptionPane.ERROR_MESSAGE);
            }
        });

        deleteBtn.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select a building from the table first.", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            String deletedName = (String) tableModel.getValueAt(selectedRow, 0);
            buildingList.remove(selectedRow);
            tableModel.removeRow(selectedRow);
            JOptionPane.showMessageDialog(this, deletedName + " has been demolished.", "Deleted", JOptionPane.INFORMATION_MESSAGE);
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addToList(Building b) {
        buildingList.add(b);
        tableModel.addRow(new Object[]{
            b.getName(),
            b.getAddress(),
            b.getStatus(),
            b.getClass().getSimpleName()
        });
    }

    public static void main(String[] args) {
        new MainApp();
    }
}
