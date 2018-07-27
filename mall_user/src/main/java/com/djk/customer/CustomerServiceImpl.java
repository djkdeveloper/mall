package com.djk.customer;

import com.djk.utils.Log;
import com.djk.utils.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by dujinkai on 2018/7/13.
 * 会员服务接口实现
 */
@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

    /**
     * 注入会员数据库接口
     */
    @Autowired
    private CustomerMapper customerMapper;

    @Log
    @Override
    public PageHelper<Customer> queryCustomers(PageHelper<Customer> pageHelper, String name, String mobile) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        params.put("mobile", mobile);
        return pageHelper.setListDates(customerMapper.queryCustomers(pageHelper.getQueryParams(params, customerMapper.queryCustomerCount(params))));
    }

    @Log
    @Override
    public int addCustomer(Customer customer) {
        if (Objects.isNull(customer)) {
            log.error("addCustomer fail ...due to customer is null...");
            return 0;
        }
        customer.md5Password();

        // 判断手机号码或者用户名称是否存在 存在直接返回
        if (isCustomerNameExist(customer.getUsername(), 0)) {
            log.error("addCustomer fail due to username is exist...");
            return -1;
        }

        if (isMobileExist(customer.getMobile(), 0)) {
            log.error("addCustomer fail due to mobile is exist...");
            return -2;
        }

        return customerMapper.addCustomer(customer);
    }

    @Log
    @Override
    public int deleteCustomers(long[] ids) {
        if (ArrayUtils.isEmpty(ids)) {
            log.error("do not need to delete customers due to ids is empty...");
            return 0;
        }
        return customerMapper.deleteCustomers(ids);
    }

    @Log
    @Override
    public Customer queryCustomerById(long id) {
        // 查询用户信息
        Customer customer = customerMapper.queryCustomerById(id);
        if (Objects.isNull(customer)) {
            log.error("customer is not exist...");
            return customer;
        }
        return customer.cleanPassword();
    }

    @Log
    @Override
    public int updateCustomer(Customer customer) {
        if (Objects.isNull(customer)) {
            log.error("updateCustomer fail due to customer is null....");
            return 0;
        }

        //  如果会员名称存在直接返回
        if (isCustomerNameExist(customer.getUsername(), customer.getId())) {
            log.error("updateCustomer fail due to username is not exist..");
            return -1;
        }

        // 如果手机号码存在则直接返回
        if (isMobileExist(customer.getMobile(), customer.getId())) {
            log.error("updateCustomer fail due to mobile is not exist");
            return -2;
        }
        return customerMapper.updateCustomer(customer);
    }

    @Log
    @Override
    public Customer queryCustomerByName(String name) {
        return customerMapper.queryCustomerByName(name);
    }

    /**
     * 判断会员名称是否存在
     *
     * @param name 会员名称
     * @return 存在返回true 不存在返回false
     */
    private boolean isCustomerNameExist(String name, long id) {
        Map<String, Object> params = new HashMap<>();
        params.put("username", name);
        params.put("id", id);
        return customerMapper.queryCountByName(params) != 0;
    }

    /**
     * 判断手机号码是否存在
     *
     * @param mobile 手机号码
     * @return 存在返回true  不存在返回false
     */
    private boolean isMobileExist(String mobile, long id) {
        Map<String, Object> params = new HashMap<>();
        params.put("mobile", mobile);
        params.put("id", id);
        return customerMapper.queryCountByMobile(params) != 0;
    }
}
