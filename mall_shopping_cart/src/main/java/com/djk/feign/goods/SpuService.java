package com.djk.feign.goods;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by dujinkai on 2018/7/26.
 * 商品远程服务接口
 */
@FeignClient("mall-goods")
public interface SpuService {

    /**
     * 根据商品id查询商品信息
     *
     * @param id 商品id
     * @return 返回商品信息
     */
    @RequestMapping(value = "/spu/{id}", method = RequestMethod.GET)
    Spu querySpuById(@PathVariable("id") long id);
}
