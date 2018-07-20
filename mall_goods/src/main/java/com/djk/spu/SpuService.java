package com.djk.spu;

import com.djk.utils.PageHelper;

/**
 * Created by dujinkai on 2018/7/12.
 * 商品服务接口
 */
public interface SpuService {

    /**
     * 分页查询商品信息
     *
     * @param pageHelper 分类帮助类
     * @param name       商品名称
     * @return 返回商品信息
     */
    PageHelper<Spu> querySpus(PageHelper<Spu> pageHelper, String name);

    /**
     * 添加商品
     *
     * @param spu 商品信息
     * @return 成功返回1 失败返回0
     */
    int addSpu(Spu spu);

    /**
     * 删除商品
     *
     * @param ids 商品id集合
     * @return 成功》0 失败=0
     */
    int deleteSpus(long[] ids);

    /**
     * 根据商品id查询商品信息
     *
     * @param id 商品id
     * @return 返回商品信息
     */
    Spu queryById(long id);

    /**
     * 更新商品信息
     *
     * @param spu 商品信息
     * @return 成功1 失败=0
     */
    int updateSpu(Spu spu);
}
