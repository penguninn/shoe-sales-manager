/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.zzz.quanlibangiay.action;

import com.zzz.quanlibangiay.entity.Customer;
import com.zzz.quanlibangiay.entity.xml.CustomerXML;
import com.zzz.quanlibangiay.utils.FileUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ManageCustomer {

    private static final String FILE_NAME = "Customers.xml";
    private List<Customer> customerList;

    public ManageCustomer() {
        CustomerXML customerXML = (CustomerXML) FileUtils.readXMLFile(FILE_NAME, CustomerXML.class);
        if (customerXML == null || customerXML.getCustomers() == null) {
            customerList = new ArrayList<>();
        } else {
            customerList = customerXML.getCustomers();
        }
    }

    public List<Customer> getAllCustomers() {
        return customerList;
    }

    public Customer getCustomerById(int id) {
        for (Customer customer : customerList) {
            if (customer.getId() == id) return customer;
        }
        return null;
    }

    public boolean isPhoneExists(String phone, int excludeId) {
        for (Customer c : customerList) {
            if (c.getPhoneNumber().equals(phone) && c.getId() != excludeId) {
                return true;
            }
        }
        return false;
    }

    public Customer getCustomerByPhone(String phone) {
        for (Customer customer : customerList) {
            if (customer.getPhoneNumber().equals(phone)) {
                return customer;
            }
        }
        return null;
    }

    public boolean addCustomer(Customer customer) {
        customer.setId(getNextId());
        customer.setCreatedDate(new Date());
        customerList.add(customer);
        saveToFile();
        return true;
    }

    public boolean updateCustomer(Customer updated) {
        for (int i = 0; i < customerList.size(); i++) {
            if (customerList.get(i).getId() == updated.getId()) {
                customerList.set(i, updated);
                saveToFile();
                return true;
            }
        }
        return false;
    }

    public boolean deleteCustomer(int id) {
        Customer customer = getCustomerById(id);
        if (customer != null) {
            customerList.remove(customer);
            saveToFile();
            return true;
        }
        return false;
    }

    public List<Customer> searchCustomerByName(String keyword) {
        List<Customer> result = new ArrayList<>();
        for (Customer c : customerList) {
            if (c.getName().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(c);
            }
        }
        return result;
    }

    public List<Customer> searchCustomerByPhone(String keyword) {
        List<Customer> result = new ArrayList<>();
        for (Customer c : customerList) {
            if (c.getPhoneNumber().contains(keyword)) {
                result.add(c);
            }
        }
        return result;
    }

    private int getNextId() {
        int max = 0;
        for (Customer c : customerList) {
            if (c.getId() > max) {
                max = c.getId();
            }
        }
        return max + 1;
    }

    private void saveToFile() {
        CustomerXML customerXML = new CustomerXML();
        customerXML.setCustomers(customerList);
        FileUtils.writeXMLtoFile(FILE_NAME, customerXML);
    }
}
