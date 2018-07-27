package com.djk.order;

import com.djk.utils.LoginUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by dujinkai on 2018/7/26.
 * 订单控制器
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    /**
     * 注入订单服务接口
     */
    @Autowired
    private OrderService orderService;

    /**
     * 注入request对象
     */
    @Autowired
    private HttpServletRequest request;

    /**
     * 提交订单
     *
     * @param submitParams 提交订单参数
     * @return -1 参数错误 1 成功 -2 商品库存不足
     */
    @PostMapping
    public int submitOrder(@RequestBody SubmitParams submitParams) {
        submitParams.setCustomerId(LoginUtils.getInstance().getUserId(request));
        return orderService.submitOrder(submitParams);
    }
}
