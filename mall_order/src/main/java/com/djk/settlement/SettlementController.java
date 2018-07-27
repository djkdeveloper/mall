package com.djk.settlement;

import com.djk.utils.LoginUtils;
import com.mysql.jdbc.log.LogUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by dujinkai on 2018/7/27.
 * 结算控制器
 */
@RestController
@RequestMapping("/settlement")
public class SettlementController {

    /**
     * 注入结算服务接口
     */
    @Autowired
    private SettlementService settlementService;

    /**
     * 注入
     * request代理对象
     */
    @Autowired
    private HttpServletRequest request;

    /**
     * 获得结算信息
     *
     * @param orderSettlementRequest 结算信息请求
     * @return 返回结算信息
     */
    @PostMapping
    public OrderSettlement orderSettlement(@RequestBody OrderSettlementRequest orderSettlementRequest) {
        orderSettlementRequest.setCustomerId(LoginUtils.getInstance().getUserId(request));
        return settlementService.orderSettlement(orderSettlementRequest);
    }
}
