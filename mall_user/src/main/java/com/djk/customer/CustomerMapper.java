package com.djk.customer;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by dujinkai on 2018/7/13.
 * 会员数据库接口
 */
@Mapper
public interface CustomerMapper {

    /**
     * 查询会员总数
     *
     * @param params 查询参数
     * @return 返回会员总数
     */
    int queryCustomerCount(Map<String, Object> params);

    /**
     * 查询会员信息
     *
     * @param params 查询会员信息
     * @return 返回会员信息
     */
    List<Customer> queryCustomers(Map<String, Object> params);

    /**
     * 新增会员
     *
     * @param customer 会员信息
     * @return 成功返回1 失败返回0
     */
    int addCustomer(Customer customer);

    /**
     * 删除会员信息
     *
     * @param ids 会员id集合
     * @return 成功》0 失败= 0
     */
    int deleteCustomers(long[] ids);

    /**
     * 根据会员id查询会员信息
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
     * 根据会员名称查询总数
     *
     * @param params 参数
     * @return 返回会员总数
     */
    int queryCountByName(Map<String, Object> params);

    /**
     * 根据手机号码查询会员总数
     *
     * @param params 手机号码
     * @return 返回会员总数
     */
    int queryCountByMobile(Map<String, Object> params);

    /**
     * 根据会员名称查询会员信息
     *
     * @param name 会员名称
     * @return 返回会员信息
     */
    Customer queryCustomerByName(String name);
}
