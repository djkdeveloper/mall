package com.djk.feign.customer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by dujinkai on 2018/7/26.
 * 会员服务接口
 */
@FeignClient("mall-user")
public interface CustomerService {


    /**
     * 根据id查询会员信息
     *
     * @param id 会员id
     * @return 返回会员信息
     */
    @RequestMapping(value = "/customer/{id}", method = RequestMethod.GET)
    Customer queryCustomerById(@PathVariable("id") long id);
}
