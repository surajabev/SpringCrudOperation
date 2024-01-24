package com.Project.SpringAngular.Service;

import com.Project.SpringAngular.CustomerRepo.CustomerRepo;
import com.Project.SpringAngular.DTO.CustomerDTO;
import com.Project.SpringAngular.DTO.CustomerSaveDTO;
import com.Project.SpringAngular.DTO.CustomerUpdateDTO;
import com.Project.SpringAngular.Entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired

    private CustomerRepo customerRepo;


    @Override
    public String addCustomer(CustomerSaveDTO customerSaveDTO) {
        Customer customer = new Customer(

                customerSaveDTO.getCustomername(),
                customerSaveDTO.getCustomeraddress(),
                customerSaveDTO.getMobile()


        );

        customerRepo.save(customer);
        return customer.getCustomername();

    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> getCustomers = customerRepo.findAll();
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        for (Customer a : getCustomers) {

           CustomerDTO customerDTO=new CustomerDTO(


                   a.getCustomerid(),
                   a.getCustomername(),
                   a.getCustomeraddress(),
                   a.getMobile()

           );

           customerDTOList.add(customerDTO);

        }
        return customerDTOList;
    }


    @Override
    public String updateCustomers(CustomerUpdateDTO customerUpdateDTO) {
        if (customerRepo.existsById(customerUpdateDTO.getCustomerid())) {

            Customer customer = customerRepo.getById(customerUpdateDTO.getCustomerid());
            customer.setCustomername(customerUpdateDTO.getCustomername());
            customer.setCustomeraddress(customerUpdateDTO.getCustomeraddress());
            customer.setMobile(customerUpdateDTO.getMobile());
            customerRepo.save(customer);
        } else {
            System.out.println("Customer ID does not exist");
        }
        return null;
    }

    @Override
    public boolean deleteCustomer(int id) {
        if(customerRepo.existsById(id))
        {

            customerRepo.deleteById(id);

        }else

        {

            System.out.println("Customer Id not found");
        }


        return false;
    }

}
