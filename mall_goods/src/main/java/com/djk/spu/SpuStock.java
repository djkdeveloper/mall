package com.djk.spu;

import lombok.Data;

/**
 * Created by dujinkai on 2018/8/1.
 * 商品库存
 */
@Data
public class SpuStock {

    /**
     * 商品id
     */
    private long spuId;

    /**
     * 扣除的库存
     */
    private int num;
}
