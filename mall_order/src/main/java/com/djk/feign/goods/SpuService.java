package com.djk.feign.goods;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by dujinkai on 2018/7/27.
 * 商品远程服务接口
 */
@FeignClient("mall-goods")
public interface SpuService {

    /**
     * 扣除商品的库存
     *
     * @param spuStocks 商品库存
     * @return 成功返回1 失败返回 0
     */
    @DeleteMapping("/spu/stock")
    int reduceSpuStock(List<SpuStock> spuStocks);
}
