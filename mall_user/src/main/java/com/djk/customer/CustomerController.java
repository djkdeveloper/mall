package com.djk.customer;

import com.djk.utils.BaseResponse;
import com.djk.utils.PageHelper;
import lombok.experimental.Delegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Created by dujinkai on 2018/7/13.
 * 会员控制器
 */
@RestController
@RequestMapping("/customer")
public class CustomerController {

    /**
     * 注入会员服务接口
     */
    @Autowired
    private CustomerService customerService;

    /**
     * 分页查询会员信息
     *
     * @param pageHelper 会员帮助类
     * @param name       会员名称
     * @param mobile     会员手机
     * @return 返回会员信息
     */
    @PostMapping("/list")
    @PreAuthorize("hasAuthority('sys.customer.list.post')")
    public BaseResponse queryCustomers(PageHelper<Customer> pageHelper, String name, String mobile) {
        return BaseResponse.build(customerService.queryCustomers(pageHelper, name, mobile));
    }

    /**
     * 新增会员信息
     *
     * @param customer 会员信息
     * @return 成功返回1 失败返回 0
     */
    @PostMapping
    @PreAuthorize("hasAuthority('sys.customer.post')")
    public int addCustomer(@RequestBody Customer customer) {
        return customerService.addCustomer(customer);
    }

    /**
     * 删除会员信息
     *
     * @param ids 会员id集合
     * @return 成功》0 失败= 0
     */
    @DeleteMapping
    @PreAuthorize("hasAuthority('sys.customer.delete')")
    public int deleteCustomers(long[] ids) {
        return customerService.deleteCustomers(ids);
    }

    /**
     * 根据会员id查询会员信息
     *
     * @param id 会员id
     * @return 返回会员信息
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('sys.customer.query')")
    public Customer queryCustomerById(@PathVariable long id) {
        return customerService.queryCustomerById(id);
    }

    /**
     * 更新会员信息
     *
     * @param customer 会员信息
     * @return 成功返回1 失败返回0
     */
    @PutMapping
    @PreAuthorize("hasAuthority('sys.customer.put')")
    public int updateCustomer(@RequestBody Customer customer) {
        return customerService.updateCustomer(customer);
    }
}
