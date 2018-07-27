package com.djk.feign.goods;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by dujinkai on 2018/7/27.
 * 商品远程服务接口
 */
@FeignClient("mall-goods")
public interface SpuService {

    /**
     * 扣除商品的库存
     *
     * @param spuId 商品id
     * @param num   扣除的数量
     * @return 成功返回1 失败返回 0
     */
    @DeleteMapping("/spu/stock")
    int reduceSpuStock(@RequestParam("spuId") long spuId, @RequestParam("num") int num);
}
