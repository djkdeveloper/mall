package com.djk.customer;

import com.djk.utils.PageHelper;

/**
 * Created by dujinkai on 2018/7/13.
 * 会员服务接口
 */
public interface CustomerService {

    /**
     * 分页查询会员信息
     *
     * @param pageHelper 分页帮助类
     * @param name       会员名称
     * @param mobile     手机号码
     * @return 返回会员信息
     */
    PageHelper<Customer> queryCustomers(PageHelper<Customer> pageHelper, String name, String mobile);

    /**
     * 新增会员信息
     *
     * @param customer 会员信息
     * @return 成功返回1 失败返回0 -1 用户名存在 -2 手机号码存在
     */
    int addCustomer(Customer customer);

    /**
     * 删除会员
     *
     * @param ids 会员id集合
     * @return 成功》0 失败= 0
     */
    int deleteCustomers(long[] ids);

    /**
     * 根据id查询会员信息
     *
     * @param id 会员id
     * @return 返回会员信息
     */
    Customer queryCustomerById(long id);

    /**
     * 更新会员信息
     *
     * @param customer 会员信息
     * @return 成功返回1 失败返回0
     */
    int updateCustomer(Customer customer);

    /**
     * 根据会员名称查询会员信息
     *
     * @param name 会员名称
     * @return 返回会员信息
     */
    Customer queryCustomerByName(String name);
}
