package com.djk.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

/**
 * Created by dujinkai on 2018/7/24.
 * es 远程服务接口
 */
@FeignClient("mall-search")
public interface EsService {

    /**
     * 将商品放入es中
     *
     * @param esSpus 商品数据
     */
    @PostMapping("/es")
    @Async
    void addSpuToEs(List<EsSpu> esSpus);


    /**
     * 删除商品信息
     *
     * @param ids 商品id
     */
    @DeleteMapping("/es")
    @Async
    void delete(List<String> ids);


    /**
     * 修改商品信息
     *
     * @param esSpu 商品信息
     */
    @PutMapping("/es")
    @Async
    void update(EsSpu esSpu);
}
